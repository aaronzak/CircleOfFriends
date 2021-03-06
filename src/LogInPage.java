import java.awt.BorderLayout;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class LogInPage extends JFrame  {

	private JPanel contentPane;
	private JTextField textField, passwordField, dateField;
	public User currentUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInPage frame = new LogInPage();
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
	public LogInPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log In:");
		lblLogIn.setBounds(44, 89, 61, 16);
		contentPane.add(lblLogIn);
		
		textField = new JTextField();
		textField.setBounds(117, 83, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(117, 123, 134, 28);
		contentPane.add(passwordField);
		
		dateField = new JTextField();
		dateField.setBounds(11, 183, 134, 28);
		contentPane.add(dateField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(33, 129, 73, 16);
		contentPane.add(lblPassword);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(33, 231, 117, 29);
		contentPane.add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  
				  JFrame SignUp =new SignUp();
                  SignUp.setVisible(true);
                  setVisible(false);
			  }
			});
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setBounds(163, 321, 117, 29);
		contentPane.add(btnLogIn);
		btnLogIn.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  if(!dateField.getText().equals("")){
				  long offset;
				  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
					try {
						Date date = simpleDateFormat.parse(dateField.getText());
						offset = date.getTime() - System.currentTimeMillis();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						offset = 0;
						e1.printStackTrace();
					}
					
					OracleDatabase.setTime(offset);
				  }
				  User user = OracleDatabase.logInUser(textField.getText(), passwordField.getText());
				  if(user!= null){
					  

					  UserHome Home =new UserHome();
	                  Home.setVisible(true);
	                  setVisible(false);
				  }
				  
			  }
			  
			});
		
		JLabel lblMyCircle = new JLabel("My Circle");
		lblMyCircle.setFont(lblMyCircle.getFont().deriveFont(18.0f));
		lblMyCircle.setBounds(117, 6, 105, 44);
		contentPane.add(lblMyCircle);
		

		
	}


	
}
