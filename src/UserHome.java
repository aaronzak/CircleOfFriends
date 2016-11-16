import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;


public class UserHome extends LogInPage {

	private JPanel contentPane;
	private JTextField addFriendTextField;
	public User currentUser;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserHome frame = new UserHome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public UserHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String email = OracleDatabase.getSession();
		JLabel lblCurrentuser = new JLabel(email);
		lblCurrentuser.setBounds(168, 6, 61, 16);
		contentPane.add(lblCurrentuser);
		
		JButton btnAddFriend = new JButton("Add Friend");
		btnAddFriend.setBounds(34, 68, 117, 29);
		contentPane.add(btnAddFriend);
		btnAddFriend.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  
				  OracleDatabase.addFriend(addFriendTextField.getText());
			  }
			});
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(168, 73, 61, 16);
		contentPane.add(lblEmail);
		
		addFriendTextField = new JTextField();
		addFriendTextField.setBounds(214, 67, 230, 28);
		contentPane.add(addFriendTextField);
		addFriendTextField.setColumns(10);
		
		currentUser = OracleDatabase.getUserFromEmail(email);
	}
	
	

}
