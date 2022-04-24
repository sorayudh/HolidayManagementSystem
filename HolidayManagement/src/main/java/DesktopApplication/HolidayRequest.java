package DesktopApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class HolidayRequest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
					try {
						String fromDate= formatter.format(dateChooserFromDate.getDate());
						String toDate= formatter.format(dateChooserToDate.getDate());
						
						var fdate = formatter.parse(fromDate);
						var tdate = formatter.parse(toDate);
						
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSubmit.setBounds(161, 232, 85, 21);
		frame.getContentPane().add(btnSubmit);
	}
	
}
