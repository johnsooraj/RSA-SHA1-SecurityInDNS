package com.security.swingpages;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.security.controller.SecurityController;
import com.security.database.DNSDatabaseConnection;
import com.security.database.Loginvalidation;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField swingusername;
	private JTextField swingpassword;
	static Connection connection;
	static LoginPage frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginPage();
					frame.setVisible(true);
					connection = DNSDatabaseConnection.getDatabaseConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		swingusername = new JTextField();
		swingusername.setBounds(271, 44, 336, 43);
		contentPane.add(swingusername);
		swingusername.setColumns(10);

		swingpassword = new JTextField();
		swingpassword.setColumns(10);
		swingpassword.setBounds(271, 110, 336, 43);
		contentPane.add(swingpassword);

		JButton btnNewButton = new JButton("SecureLogin");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent login) {
				String username = swingusername.getText();
				String password = swingpassword.getText();
				if (!username.isEmpty() && !password.isEmpty()) {
					if (Loginvalidation.loginValidation(username, password, connection)) {
						System.out.println("USER LOGIN SUCCESS");
						frame.setVisible(false);
						new SecurityController();
					} else {
						JOptionPane.showMessageDialog(null, "Error in username and password");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Enter username and password");
				}

			}
		});
		btnNewButton.setBounds(271, 188, 162, 43);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("User Name :");
		lblNewLabel.setBounds(68, 58, 154, 14);
		contentPane.add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(68, 124, 154, 14);
		contentPane.add(lblPassword);
	}

}
