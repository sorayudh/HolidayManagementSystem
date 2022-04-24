package DesktopApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeApp {

	private JFrame frmEmployeeLogin;
	private JTextField tfUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeApp window = new EmployeeApp();
					window.frmEmployeeLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmployeeApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEmployeeLogin = new JFrame();
		frmEmployeeLogin.setTitle("Employee App");
		frmEmployeeLogin.setBounds(100, 100, 450, 300);
		frmEmployeeLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEmployeeLogin = new JLabel("Employee Login");
		lblEmployeeLogin.setBounds(157, 5, 71, 13);
		lblEmployeeLogin.setLabelFor(lblEmployeeLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 36, 100, 13);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 128, 100, 13);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(120, 33, 306, 19);
		tfUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 125, 306, 19);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfUsername.getText() != null && String.valueOf(passwordField.getPassword()) !=null) {
					var isValidUser = checkValidUser(tfUsername.getText(),String.valueOf(passwordField.getPassword()));
					if(isValidUser) {
						JOptionPane.showMessageDialog(null, "Login Success");
						tfUsername.setText(null);
						passwordField.setText(null);
						HolidayRequest hr = new HolidayRequest();
						hr.main(null);
					}
					else {
						showErrorMessage();
					}
				}
				else {
					showErrorMessage();
				}
			}
		});
		btnLogin.setBounds(10, 200, 416, 21);
		frmEmployeeLogin.getContentPane().setLayout(null);
		frmEmployeeLogin.getContentPane().add(lblEmployeeLogin);
		frmEmployeeLogin.getContentPane().add(lblUsername);
		frmEmployeeLogin.getContentPane().add(tfUsername);
		frmEmployeeLogin.getContentPane().add(lblPassword);
		frmEmployeeLogin.getContentPane().add(passwordField);
		frmEmployeeLogin.getContentPane().add(btnLogin);
	}
	
	public boolean checkValidUser(String username,String password) {
		if (username.equals("admin") && password.equals("admin"))
			return true;
		else
			return false;
	}
	public void showErrorMessage() {
		JOptionPane.showMessageDialog(null,"Invalid Login Details","Login Error",JOptionPane.ERROR_MESSAGE);
		tfUsername.setText(null);
		passwordField.setText(null);
		
	}
}
