package com.markert.view;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.table.DefaultTableModel;

import com.markert.DAO.CategoryDAO;
import com.markert.DAO.OrderDAO;
import com.markert.DAO.ProductDAO;
import com.markert.DAO.SellerDAO;
import com.markert.DTO.CategoryDTO;
import com.markert.DTO.OrderDTO;
import com.markert.DTO.ProductDTO;
import com.markert.DTO.ProductItemDTO;
import com.markert.DTO.SellerDTO;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;

public class ItemADD extends JFrame {

	private JPanel contentPane;
	public static String orderID;
	private JTextField textField1;
	private JComboBox<ProductDTO> comboBox;
	private JTable table;
	private JButton addButton;
	private JButton removeButton;
	private List<ProductDTO> productList = new ArrayList<ProductDTO>();
	private JButton removeButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemADD frame = new ItemADD();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ItemADD() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Adicionar Item");
		title.setBounds(10, 12, 414, 27);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(title);
		
		JLabel label1 = new JLabel("QUANT");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setFont(new Font("Verdana", Font.PLAIN, 15));
		label1.setBounds(10, 60, 80, 30);
		
		contentPane.add(label1);
		
		textField1 = new JTextField();
		textField1.setBounds(100, 60, 250, 30);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel label2 = new JLabel("PRODUTO");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setFont(new Font("Verdana", Font.PLAIN, 15));
		label2.setBounds(10, 100, 80, 30);
		
		contentPane.add(label2);
		
		comboBox = new JComboBox<ProductDTO>();
		comboBox.setModel(new DefaultComboBoxModel<ProductDTO>(new ProductDTO[] {
				new ProductDTO("Selecione")
		}));
		comboBox.setBounds(100, 100, 250, 30);
		loadCombobox();
		contentPane.add(comboBox);
		
		addButton = new JButton("ADCIONAR");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDAO dao = new OrderDAO();
				ProductItemDTO dto = new ProductItemDTO();
				
				dto.setQuantity(Integer.parseInt(textField1.getText()));
				dto.setOrder(orderID);
				
				ProductDTO item = (ProductDTO) comboBox.getSelectedItem();
				
				if(item.getId() != 0) {
					dto.setProduct(item.getId());
					}
					else {
						JOptionPane.showMessageDialog(null, "Selecione um Produto Correto");
					}
				
				dao.addProductItem(dto);
				loadTable();
				clearCamps();
			}
		});
		addButton.setBounds(225, 140, 125, 26);
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
		removeButton.setBounds(100, 140, 125, 26);
		contentPane.add(removeButton);
		
		String[] columnsName = {"ID", "PRODUTO", "QUANTIDADE"};
		String[][] data = {{null, null}};
		table = new JTable();
		table.setModel(new DefaultTableModel(data, columnsName));
		contentPane.setLayout(null);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBounds(10, 189, 414, 293);
		
		contentPane.add(tablePane);
		
		removeButton_1 = new JButton("DELETAR");
		removeButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				loadTable();
			}
		});
		removeButton_1.setBounds(10, 488, 414, 26);
		contentPane.add(removeButton_1);
		
		loadTable();
	}

	public void loadCombobox() {
		ProductDAO dao = new ProductDAO();
		
		productList = dao.findAll();
		
		for(ProductDTO i : productList) {
		comboBox.addItem(i);
		}
	}
	
	public void loadTable() {
		try {
            
           DefaultTableModel model = (DefaultTableModel) table.getModel();
            
           OrderDAO dao = new OrderDAO();
           
           ProductDAO proDao = new ProductDAO();
            
            List<ProductItemDTO> list = dao.findAllItems(orderID);
            
            model.setNumRows(0);
            
            for(int i = 0; i < list.size(); i++) {
                model.addRow(new Object[] {
                	list.get(i).getId(),
                	proDao.findById(list.get(i).getProduct()),
                    list.get(i).getQuantity()
                });
            }
          
        }
        catch(Exception err) {
            JOptionPane.showMessageDialog(null, "Error message: " + err.getMessage());
        }
	}
	
	public void delete() {
		OrderDAO dao = new OrderDAO();

		Integer selected = table.getSelectedRow();

		dao.deleteItem((Integer) table.getModel().getValueAt(selected, 0));

	}
	
	public void clearCamps() {
		textField1.setText("");
		comboBox.setSelectedIndex(0);
	}
}
