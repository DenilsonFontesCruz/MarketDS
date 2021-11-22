package com.markert.view;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.markert.DAO.SellerDAO;
import com.markert.DTO.SellerDTO;

public class SellerEDIT extends JFrame {

	public static SellerDTO selDto;
	private JPanel contentPane;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JButton addButton;
	private JButton removeButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SellerEDIT frame = new SellerEDIT();
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
	public SellerEDIT() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Adicionar Vendedor");
		title.setBounds(10, 12, 414, 27);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(title);
		
		JLabel label1 = new JLabel("NOME");
		label1.setHorizontalAlignment(SwingConstants.RIGHT);
		label1.setFont(new Font("Verdana", Font.PLAIN, 15));
		label1.setBounds(30, 60, 60, 30);
		
		contentPane.add(label1);
		
		textField1 = new JTextField();
		textField1.setBounds(100, 60, 250, 30);
		contentPane.add(textField1);
		textField1.setColumns(10);
		
		JLabel label2 = new JLabel("EMAIL");
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setFont(new Font("Verdana", Font.PLAIN, 15));
		label2.setBounds(30, 100, 60, 30);
		
		contentPane.add(label2);
		
		textField2 = new JTextField();
		textField2.setBounds(100, 100, 250, 30);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		JLabel label3 = new JLabel("SENHA");
		label3.setHorizontalAlignment(SwingConstants.RIGHT);
		label3.setFont(new Font("Verdana", Font.PLAIN, 15));
		label3.setBounds(30, 140, 60, 30);
		
		contentPane.add(label3);
		
		textField3 = new JTextField();
		textField3.setBounds(100, 140, 250, 30);
		contentPane.add(textField3);
		textField3.setColumns(10);
		
		addButton = new JButton("EDITAR");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellerDAO dao = new SellerDAO();
				SellerDTO dto = new SellerDTO();
				
				dto.setName(textField1.getText());
				dto.setEmail(textField2.getText());
				dto.setPassword(textField3.getText());
				
				dto.setId(selDto.getId());
				
				dao.update(dto);
			}
		});
		addButton.setBounds(225, 180, 125, 26);
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
		removeButton.setBounds(100, 180, 125, 26);
		contentPane.add(removeButton);
		
		textField1.setText(selDto.getName());
		textField2.setText(selDto.getEmail());
	}

}
