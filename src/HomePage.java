import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;


public class HomePage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
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
	User currentUser;
	private JTextField textField;
	public HomePage() {
		initialize();
	}
	public HomePage(String user) {
		initialize();
	} 

	private User getUser(){
		User myUser = null;
		return myUser;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCurrentuser = new JLabel("current_user");
		lblCurrentuser.setBounds(238, 42, 61, 16);
		frame.getContentPane().add(lblCurrentuser);
		
		JButton btnAddFriend = new JButton("Add Friend");
		btnAddFriend.setBounds(59, 97, 117, 29);
		frame.getContentPane().add(btnAddFriend);
		
		textField = new JTextField();
		textField.setBounds(293, 96, 262, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(220, 102, 61, 16);
		frame.getContentPane().add(lblEmail);
	}
}
