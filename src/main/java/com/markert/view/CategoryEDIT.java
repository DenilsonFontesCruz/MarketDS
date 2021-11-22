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

import com.markert.DAO.CategoryDAO;
import com.markert.DTO.CategoryDTO;

public class CategoryEDIT extends JFrame {

	public static CategoryDTO catDto;
	private JPanel contentPane;
	private JTextField textField1;
	private JButton addButton;
	private JButton removeButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryEDIT frame = new CategoryEDIT();
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
	public CategoryEDIT() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Adicionar Categoria");
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
		
		addButton = new JButton("EDITAR");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CategoryDAO dao = new CategoryDAO();
				CategoryDTO dto = new CategoryDTO();
				
				dto.setName(textField1.getText());
				
				dto.setId(catDto.getId());
				
				dao.update(dto);
			}
		});
		addButton.setBounds(225, 100, 125, 26);
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
		removeButton.setBounds(100, 100, 125, 26);
		contentPane.add(removeButton);
		
		textField1.setText(catDto.getName());
	}

}
