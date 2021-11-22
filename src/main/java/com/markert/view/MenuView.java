package com.markert.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.markert.DAO.CategoryDAO;
import com.markert.DAO.ClientDAO;
import com.markert.DAO.ProductDAO;
import com.markert.DTO.CategoryDTO;
import com.markert.DTO.ProductItemDTO;

public class MenuView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView frame = new MenuView();
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
	public MenuView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 387, 365);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(1000, 420));
		setContentPane(contentPane);
		
		String[] columnsName = {"ID", "NOME"};
		String[][] data = {{null, null}};
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("MENU");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Verdana", Font.PLAIN, 28));
		titleLabel.setBounds(10, 11, 351, 50);
		contentPane.add(titleLabel);
		
		JButton listButton = new JButton("CATEGORIA");
		listButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame adicionar = new CategoryView();
				adicionar.setVisible(true);
			}
		});
		listButton.setBounds(109, 118, 135, 35);
		listButton.setBorder(null);
		listButton.setBackground(new Color(200,200,200));
		listButton.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		contentPane.add(listButton);
		
		JButton listButton_1 = new JButton("VENDEDOR");
		listButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame adicionar = new SellerView();
				adicionar.setVisible(true);
			}
		});
		listButton_1.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		listButton_1.setBorder(null);
		listButton_1.setBackground(new Color(200, 200, 200));
		listButton_1.setBounds(109, 164, 135, 35);
		contentPane.add(listButton_1);
		
		JButton listButton_1_1 = new JButton("PRODUTO");
		listButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame adicionar = new ProductView();
				adicionar.setVisible(true);
			}
		});
		listButton_1_1.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		listButton_1_1.setBorder(null);
		listButton_1_1.setBackground(new Color(200, 200, 200));
		listButton_1_1.setBounds(109, 210, 135, 35);
		contentPane.add(listButton_1_1);
		
		JButton listButton_2 = new JButton("CLIENTE");
		listButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame adicionar = new ClientView();
				adicionar.setVisible(true);
			}
		});
		listButton_2.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		listButton_2.setBorder(null);
		listButton_2.setBackground(new Color(200, 200, 200));
		listButton_2.setBounds(109, 72, 135, 35);
		contentPane.add(listButton_2);
		
		JButton listButton_1_2 = new JButton("PEDIDO");
		listButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame adicionar = new OrderView();
				adicionar.setVisible(true);
			}
		});
		listButton_1_2.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		listButton_1_2.setBorder(null);
		listButton_1_2.setBackground(new Color(200, 200, 200));
		listButton_1_2.setBounds(109, 256, 135, 35);
		contentPane.add(listButton_1_2);
		
	}
	
}
