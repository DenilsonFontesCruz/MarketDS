package com.markert.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import com.markert.DAO.CategoryDAO;
import com.markert.DAO.ClientDAO;
import com.markert.DAO.OrderDAO;
import com.markert.DAO.ProductDAO;
import com.markert.DAO.SellerDAO;
import com.markert.DTO.OrderDTO;
import com.markert.DTO.ProductDTO;
import com.markert.DTO.SellerDTO;
import com.markert.Enums.PayMethods;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class ProductView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductView frame = new ProductView();
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
	public ProductView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1000, 420);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(1000, 420));
		setContentPane(contentPane);
		
		String[] columnsName = {"ID", "NOME", "CATEGORIA", "PREÇO", "DATA DE VALIDADE", "QRCODE"};
		String[][] data = {{null, null, null, null, null, null}};
		table = new JTable();
		table.setModel(new DefaultTableModel(data, columnsName));
		contentPane.setLayout(null);
		JScrollPane tablePane = new JScrollPane(table);
		tablePane.setBounds(10, 72, 964, 259);
		
		contentPane.add(tablePane);
		
		JLabel titleLabel = new JLabel("Produtos");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Verdana", Font.PLAIN, 28));
		titleLabel.setBounds(10, 11, 964, 50);
		contentPane.add(titleLabel);
		
		textField = new JTextField();
		textField.setBounds(460, 330, 514, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(" Pesquisar por Nome");
		
		JButton addButton = new JButton("ADICIONAR");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame adicionar = new ProductADD();
				adicionar.setVisible(true);
			}
		});
		addButton.setBounds(10, 330, 90, 30);
		addButton.setBorder(null);
		addButton.setBackground(new Color(200,200,200));
		addButton.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(addButton);
		
		JButton removeButton = new JButton("REMOVER");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
				loadTable(null);
			}
		});
		removeButton.setBounds(100, 330, 90, 30);
		removeButton.setBorder(null);
		removeButton.setBackground(new Color(200,200,200));
		removeButton.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(removeButton);
		
		JButton updateButton = new JButton("MUDAR");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change();
			}
		});
		updateButton.setBounds(280, 330, 90, 30);
		updateButton.setBorder(null);
		updateButton.setBackground(new Color(200,200,200));
		updateButton.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(updateButton);
		
		JButton deleteButton = new JButton("QUEIMA");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fire();
				loadTable(null);
			}
		});
		deleteButton.setBounds(190, 330, 90, 30);		
		deleteButton.setBorder(null);
		deleteButton.setBackground(new Color(200,200,200));
		deleteButton.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(deleteButton);
		
		JButton listButton = new JButton("PESQUISAR");
		listButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				
				loadTable(textField.getText());
			}
		});
		listButton.setBounds(370, 330, 90, 30);
		listButton.setBorder(null);
		listButton.setBackground(new Color(200,200,200));
		listButton.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(listButton);
		
		loadTable(null);
	}
	
	public void loadTable(String filter) {
		try {
            
           DefaultTableModel model = (DefaultTableModel) table.getModel();
            
           ProductDAO dao = new ProductDAO();
            
           List<ProductDTO> list;
           
           if(filter == null || filter.equals("")) {
        	   list = dao.findAll();
           }
           else {
        	   list = dao.findFilterList(filter);
           }
            
            model.setNumRows(0);
            
            for(int i = 0; i < list.size(); i++) {
                model.addRow(new Object[] {
            		list.get(i).getId(),
            		list.get(i).getName(),
            		CategoryDAO.findById(list.get(i).getCategory()),
            		list.get(i).getPrice(),
            		list.get(i).getExperitionDate(),
            		list.get(i).getQRcode()
                });
            }
          
        }
        catch(Exception err) {
            JOptionPane.showMessageDialog(null, "Error message: " + err.getMessage());
        }
	}
	
	public void delete() {
		ProductDAO dao = new ProductDAO();
		
		Integer selected = table.getSelectedRow();

		dao.delete((Integer) table.getModel().getValueAt(selected, 0));
	}
	
	public void fire() {
		ProductDAO dao = new ProductDAO();
		
		int resp = JOptionPane.showConfirmDialog(null, "DESEJA DELETAR TODOS OS ITEMS");
		
		if(resp == 0) {
			dao.deleteAll();
		}
	}
	
	public void change() {
		ProductDAO dao = new ProductDAO();
		
		Integer selected = table.getSelectedRow();
	
		DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		ProductDTO dto = new ProductDTO();
		
		dto.setId((Integer) table.getModel().getValueAt(selected, 0));
		dto.setName((String) table.getModel().getValueAt(selected, 1));
		dto.setPrice((Double) table.getModel().getValueAt(selected, 3));
		dto.setExperitionDate((Date) table.getModel().getValueAt(selected, 4));

		ProductEDIT.proDto = dto;
		JFrame edit = new ProductEDIT();
		edit.setVisible(true);
	}
}
