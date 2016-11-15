import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;



public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField email;
	private JTextField password;
	private JTextField confirmPassword;
	private JTextField textFieldFullName;
	private JTextField phone, screenameField;
	public List<User> users;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		users = new ArrayList<User>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setBounds(190, 21, 61, 16);
		contentPane.add(lblSignUp);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(36, 63, 61, 16);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(36, 104, 61, 16);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(36, 145, 134, 16);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(36, 186, 117, 16);
		contentPane.add(lblFullName);
		
		JLabel lblScreenameoptional = new JLabel("Screename ");
		lblScreenameoptional.setBounds(36, 238, 134, 16);
		contentPane.add(lblScreenameoptional);
		
		JLabel lblPhone = new JLabel("Phone Number");
		lblPhone.setBounds(36, 275, 134, 16);
		contentPane.add(lblPhone);
		
		email = new JTextField();
		email.setBounds(210, 57, 213, 28);
		contentPane.add(email);
		email.setColumns(10);
		
		password = new JTextField();
		password.setBounds(210, 98, 213, 28);
		contentPane.add(password);
		password.setColumns(10);
		
		confirmPassword = new JTextField();
		confirmPassword.setColumns(10);
		confirmPassword.setBounds(210, 139, 213, 28);
		contentPane.add(confirmPassword);
		
		textFieldFullName = new JTextField();
		textFieldFullName.setColumns(10);
		textFieldFullName.setBounds(210, 180, 213, 28);
		contentPane.add(textFieldFullName);
		
		screenameField = new JTextField();
		screenameField.setColumns(10);
		screenameField.setBounds(210, 232, 213, 28);
		contentPane.add(screenameField);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(210, 275, 213, 28);
		contentPane.add(phone);
		
		final JCheckBox chckbxManager = new JCheckBox("Manager");
		chckbxManager.setBounds(42, 310, 128, 23);
		contentPane.add(chckbxManager);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(154, 401, 117, 29);
		contentPane.add(btnSubmit);
		btnSubmit.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  
				  
					User newUser;
					if(password.getText().equals(confirmPassword.getText()) && 
							!email.getText().equals("") &&
							!password.getText().equals("") &&
							!textFieldFullName.getText().equals("")){
						newUser = new User(email.getText(), textFieldFullName.getText(), password.getText(),screenameField.getText(), phone.getText(), chckbxManager.isSelected() );

						OracleDatabase.addUserToDatabase(newUser);
						System.out.println(newUser.email + password.getText());
						JFrame MainPage =new LogInPage();
		                MainPage.setVisible(true);
		                setVisible(false);
					}
					else{
						JButton source = (JButton) e.getSource();
						JOptionPane.showMessageDialog(source, source.getText()
								+ " please fill all forms");
						
					}
				  
			  }
			});
	}
}
