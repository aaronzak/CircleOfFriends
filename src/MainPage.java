import java.awt.BorderLayout;
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


public class MainPage extends JFrame  {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(117, 123, 134, 28);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(33, 129, 73, 16);
		contentPane.add(lblPassword);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(33, 181, 117, 29);
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
		btnLogIn.setBounds(163, 181, 117, 29);
		contentPane.add(btnLogIn);
		
		JLabel lblMyCircle = new JLabel("My Circle");
		lblMyCircle.setFont(lblMyCircle.getFont().deriveFont(18.0f));
		lblMyCircle.setBounds(117, 6, 105, 44);
		contentPane.add(lblMyCircle);
	}


	
}
