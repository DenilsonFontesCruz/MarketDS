package com.markert.view;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.markert.DAO.CategoryDAO;
import com.markert.DAO.ClientDAO;
import com.markert.DAO.OrderDAO;
import com.markert.DAO.ProductDAO;
import com.markert.DAO.SellerDAO;
import com.markert.DTO.CategoryDTO;
import com.markert.DTO.ClientDTO;
import com.markert.DTO.OrderDTO;
import com.markert.DTO.ProductDTO;
import com.markert.DTO.SellerDTO;
import com.markert.Enums.PayMethods;

import javax.swing.JFormattedTextField;

public class OrderEDIT extends JFrame {

	public static String id;
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JFormattedTextField textField3;
	private JButton addButton;
	private JButton removeButton;
	private JComboBox<SellerDTO> comboBox;
	private JComboBox<ClientDTO> comboBox2;
	private JComboBox<PayMethods> comboBox3;
	private List<SellerDTO> sellerList = new ArrayList<SellerDTO>();
	private List<ClientDTO> clientList = new ArrayList<ClientDTO>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderEDIT frame = new OrderEDIT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public OrderEDIT() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(110, 100, 453, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Adicionar Pedido");
		title.setBounds(20, 12, 414, 27);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(title);
		
		JLabel label1 = new JLabel("METODO");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setFont(new Font("Verdana", Font.PLAIN, 15));
		label1.setBounds(10, 60, 90, 30);
		
		contentPane.add(label1);
		
		comboBox3 = new JComboBox(PayMethods.values());
		comboBox3.setBounds(110, 60, 250, 30);
		contentPane.add(comboBox3);
		
		JLabel label2 = new JLabel("CLIENTE");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setFont(new Font("Verdana", Font.PLAIN, 15));
		label2.setBounds(28, 100, 72, 30);
		
		contentPane.add(label2);
		
		comboBox2 = new JComboBox<ClientDTO>();
		comboBox2.setModel(new DefaultComboBoxModel<ClientDTO>(new ClientDTO[] {
				new ClientDTO("Selecione")
		}));
		comboBox2.setBounds(110, 100, 250, 30);
		loadCombobox2();
		contentPane.add(comboBox2);
		
		JLabel label4 = new JLabel("VENDEDOR");
		label4.setHorizontalAlignment(SwingConstants.RIGHT);
		label4.setFont(new Font("Verdana", Font.PLAIN, 15));
		label4.setBounds(10, 140, 90, 30);
		
		contentPane.add(label4);
		
		comboBox = new JComboBox<SellerDTO>();
		comboBox.setModel(new DefaultComboBoxModel<SellerDTO>(new SellerDTO[] {
				new SellerDTO("Selecione")
		}));
		comboBox.setBounds(110, 140, 250, 30);
		loadCombobox();
		contentPane.add(comboBox);
		
		addButton = new JButton("EDITAR");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO dao = new OrderDAO();
				OrderDTO dto = new OrderDTO();
				
				SellerDTO seller = (SellerDTO) comboBox.getSelectedItem();
				
				if(seller.getId() != 0) {
				dto.setSeller(seller.getId());
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um Vendendor Correto");
				}
				
				ClientDTO client = (ClientDTO) comboBox2.getSelectedItem();
				
				if(client.getId() != 0) {
				dto.setClient(client.getId());
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um Cliente Correto");
				}
				
				PayMethods payMethod = (PayMethods) comboBox3.getSelectedItem();
				
				dto.setPayMethod(payMethod.getCod());
				
				dto.setId(id);
				
				dao.update(dto);
				
			}
		});
		addButton.setBounds(235, 180, 125, 26);
		contentPane.add(addButton);
		
		removeButton = new JButton("CANCELAR");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container frame = removeButton.getParent();
				do
					frame = frame.getParent();
				while(!(frame instanceof JFrame));
				((JFrame) frame).dispose();
			}
		});
		removeButton.setBounds(110, 180, 125, 26);
		contentPane.add(removeButton);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(52, 221, -32, 20);
		contentPane.add(formattedTextField);
		
		String[] columnsName = {"NOME", "QUANTIDADE"};
		String[][] data = {{null, null}};
		contentPane.setLayout(null);
		
	}
	
	public void loadCombobox() {
		SellerDAO dao = new SellerDAO();
		
		sellerList = dao.findAll();
		
		for(SellerDTO i : sellerList) {
		comboBox.addItem(i);
		}
		
	}
	
	public void loadCombobox2() {
		
		ClientDAO dao = new ClientDAO();
		
		clientList = dao.findAll();
		
		for(ClientDTO i : clientList) {
		comboBox2.addItem(i);
		}
		
	}
	
	
	
}
