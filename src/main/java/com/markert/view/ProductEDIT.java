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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.markert.DAO.CategoryDAO;
import com.markert.DAO.ProductDAO;
import com.markert.DTO.CategoryDTO;
import com.markert.DTO.ProductDTO;
import javax.swing.JFormattedTextField;

public class ProductEDIT extends JFrame {

	public static Integer id;
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JFormattedTextField textField3;
	private JButton addButton;
	private JButton removeButton;
	private JComboBox<CategoryDTO> comboBox;
	private List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductEDIT frame = new ProductEDIT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	public ProductEDIT() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(110, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Adicionar Produto");
		title.setBounds(20, 12, 414, 27);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(title);
		
		JLabel label1 = new JLabel("NOME");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setFont(new Font("Verdana", Font.PLAIN, 15));
		label1.setBounds(40, 60, 60, 30);
		
		contentPane.add(label1);
		
		textField1 = new JTextField();
		textField1.setBounds(110, 60, 250, 30);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel label2 = new JLabel("PREÇO");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setFont(new Font("Verdana", Font.PLAIN, 15));
		label2.setBounds(40, 100, 60, 30);
		
		contentPane.add(label2);
		
		textField2 = new JTextField();	
		textField2.setBounds(110, 100, 250, 30);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		JLabel label3 = new JLabel("VALIDADE");
		label3.setHorizontalAlignment(SwingConstants.RIGHT);
		label3.setFont(new Font("Verdana", Font.PLAIN, 15));
		label3.setBounds(20, 140, 80, 30);
		
		contentPane.add(label3);
		
		try {
			textField3 = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		textField3.setBounds(110, 140, 250, 30);
		contentPane.add(textField3);
		textField3.setColumns(10);
		
		JLabel label4 = new JLabel("CATEGORIA");
		label4.setHorizontalAlignment(SwingConstants.RIGHT);
		label4.setFont(new Font("Verdana", Font.PLAIN, 15));
		label4.setBounds(10, 180, 90, 30);
		
		contentPane.add(label4);
		
		comboBox = new JComboBox<CategoryDTO>();
		comboBox.setModel(new DefaultComboBoxModel<CategoryDTO>(new CategoryDTO[] {
				new CategoryDTO("Selecione")
		}));
		comboBox.setBounds(110, 180, 250, 30);
		loadCombobox();
		contentPane.add(comboBox);
		
		addButton = new JButton("EDITAR");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO dao = new ProductDAO();
				ProductDTO dto = new ProductDTO();
				DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				dto.setName(textField1.getText());
				if(!textField2.getText().isEmpty()) {
					dto.setPrice(Double.parseDouble(textField2.getText()));
				}
				else {
					dto.setPrice(0);
				}
				
				try {
					dto.setExperitionDate(dataFormat.parse(textField3.getText()));
				} catch (ParseException e1) {
					e1.printStackTrace();
					Date date = new Date();
					dto.setExperitionDate(date);
				}
				
				CategoryDTO item = (CategoryDTO) comboBox.getSelectedItem();
				
				if(item.getId() != 0) {
				dto.setCategory(item.getId());
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um item Correto");
				}
				
				dto.setId(id);
				
				dao.update(dto);
			}
		});
		addButton.setBounds(235, 220, 125, 26);
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
		removeButton.setBounds(110, 220, 125, 26);
		contentPane.add(removeButton);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(52, 221, -32, 20);
		contentPane.add(formattedTextField);
		
		
	}
	
	public void loadCombobox() {
		CategoryDAO dao = new CategoryDAO();
		
		categoryList = dao.findAll();
		
		for(CategoryDTO i : categoryList) {
		comboBox.addItem(i);
		}
	}
}
