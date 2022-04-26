package DesktopApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class HolidayRequest {

	public static int employeeId;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeId = id;
					HolidayRequest window = new HolidayRequest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public HolidayRequest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Holiday Request");
		lblNewLabel.setBounds(180, 33, 84, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Reason :");
		lblNewLabel_1.setBounds(33, 91, 84, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("From Date :");
		lblNewLabel_2.setBounds(33, 150, 84, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblToDate = new JLabel("To Date :");
		lblToDate.setBounds(33, 199, 84, 13);
		frame.getContentPane().add(lblToDate);
		
		JTextArea textAreaReason = new JTextArea();
		textAreaReason.setBounds(127, 85, 299, 36);
		frame.getContentPane().add(textAreaReason);
		
		JDateChooser dateChooserFromDate = new JDateChooser();
		dateChooserFromDate.setBounds(127, 150, 299, 19);
		frame.getContentPane().add(dateChooserFromDate);
		
		JDateChooser dateChooserToDate = new JDateChooser();
		dateChooserToDate.setBounds(127, 199, 299, 19);
		frame.getContentPane().add(dateChooserToDate);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dateChooserFromDate.getDate()!=null && dateChooserToDate.getDate()!=null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String fromDate= formatter.format(dateChooserFromDate.getDate());
					String toDate= formatter.format(dateChooserToDate.getDate());
					String reason = textAreaReason.getText();
					createNewHolidayRequest(reason,fromDate,toDate);
				}
			}
		});
		btnSubmit.setBounds(161, 232, 85, 21);
		frame.getContentPane().add(btnSubmit);
	}
	
	public boolean createNewHolidayRequest(String reason,String fdate,String tdate){
		try {
			 String url = "http://localhost:8180/HolidayManagement/EmployeeService";
			 URL obj = new URL(url);
			 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			 con.setRequestMethod("GET");
			 con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");
			 var empId = employeeId;
			 String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service/\">\r\n"
			 		+ "   <soapenv:Header/>\r\n"
			 		+ "   <soapenv:Body>\r\n"
			 		+ "      <ser:addNewHolidayRequest>\r\n"
			 		+ "         <arg0>"+reason+"</arg0>\r\n"
			 		+ "         <arg1>"+fdate+"</arg1>\r\n"
			 		+ "         <arg2>"+tdate+"</arg2>\r\n"
			 		+ "         <arg3>"+empId+"</arg3>\r\n"
			 		+ "      </ser:addNewHolidayRequest>\r\n"
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
			 String output = response.toString().substring(response.toString().indexOf(toBeSearched) + toBeSearched.length());
			 String result = output.split("</return>")[0];
			 System.out.println("response:" + response.toString());
			 if(result.equals("true")) {
				 return true;
			 }
			 else
				 return false;
			 } 
		catch (Exception e) {
			 System.out.println(e);
			 return false;
		}
	}
}
