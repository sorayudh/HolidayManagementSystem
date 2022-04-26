package DesktopApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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
					var employeeId = checkValidUser(tfUsername.getText(),String.valueOf(passwordField.getPassword()));
					if(employeeId!=0) {
						tfUsername.setText(null);
						passwordField.setText(null);
						HolidayRequest hr = new HolidayRequest();
						hr.main(null,employeeId);
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
	
	public int checkValidUser(String username,String password) {
		
		try {
			 String url = "http://localhost:8180/HolidayManagement/EmployeeService";
			 URL obj = new URL(url);
			 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 con.setRequestMethod("GET");
			 con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
			 
			 String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service/\">\r\n"
			 		+ "   <soapenv:Header/>\r\n"
			 		+ "   <soapenv:Body>\r\n"
			 		+ "      <ser:verifyLoginUser>\r\n"
			 		+ "         <arg0>"+username+"</arg0>\r\n"
			 		+ "         <arg1>"+password+"</arg1>\r\n"
			 		+ "      </ser:verifyLoginUser>\r\n"
			 		+ "   </soapenv:Body>\r\n"
			 		+ "</soapenv:Envelope>";
			 con.setDoOutput(true);
			 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			 wr.writeBytes(xml);
			 wr.flush();
			 wr.close();
			 String responseStatus = con.getResponseMessage();
			 System.out.println(responseStatus);
			 BufferedReader in = new BufferedReader(new InputStreamReader(
			 con.getInputStream()));
			 String inputLine;
			 StringBuffer response = new StringBuffer();
			 while ((inputLine = in.readLine()) != null) {
			 response.append(inputLine);
			 }
			 in.close();
			 String toBeSearched = "<return>";
			 String id = response.toString().substring(response.toString().indexOf(toBeSearched) + toBeSearched.length());
			 String employeeId = id.split("</return>")[0];
			 System.out.println("response:" + response.toString());
			 if(employeeId.equals("0")) {
				 return 0;
			 }
			 else
				 return Integer.parseInt(employeeId);
			 } 
		catch (Exception e) {
			 System.out.println(e);
			 return 0;
		}
		
		
	}
	public void showErrorMessage() {
		JOptionPane.showMessageDialog(null,"Invalid Login Details","Login Error",JOptionPane.ERROR_MESSAGE);
		tfUsername.setText(null);
		passwordField.setText(null);
		
	}
}
