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


public class UserHome extends JFrame {

	private JPanel contentPane;
	private JTextField addFriendTextField, topicTextField;
	public User currentUser;
	private Long offset;

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
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		String email = OracleDatabase.getSession();
		offset = OracleDatabase.getTime();
		
		contentPane.setLayout(null);
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
				  
				  OracleDatabase.addFriend(currentUser,addFriendTextField.getText());
			  }
			});
		
		JButton btnCheckRequests = new JButton("Friend Requests");
		btnCheckRequests.setBounds(460, 68, 127, 29);
		contentPane.add(btnCheckRequests);
		btnCheckRequests.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  
				  OracleDatabase.checkRequests(currentUser);
			  }
			});
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(168, 73, 61, 16);
		contentPane.add(lblEmail);
		
		addFriendTextField = new JTextField();
		addFriendTextField.setBounds(214, 67, 230, 28);
		contentPane.add(addFriendTextField);
		addFriendTextField.setColumns(10);
		
		JButton btnMessage = new JButton("Message");
		btnMessage.setBounds(34, 123, 143, 29);
		contentPane.add(btnMessage);
		
		btnMessage.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  OracleDatabase.viewConversations(currentUser.email);
				  JFrame Message =new PrivateMessage();
                  Message.setVisible(true);
			  }
			});
		topicTextField = new JTextField();
		topicTextField.setBounds(214, 183, 230, 28);
		contentPane.add(topicTextField);
		topicTextField.setColumns(10);
		
		JButton btnAddTopic = new JButton("Add Topic");
		btnAddTopic.setBounds(34, 183, 143, 29);
		contentPane.add(btnAddTopic);
		btnAddTopic.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				 OracleDatabase.addTopic( currentUser.email, topicTextField.getText());
			  }
			});
		
		JButton btnGroupChat = new JButton("Group Chats");
		btnGroupChat.setBounds(34, 230, 143, 29);
		contentPane.add(btnGroupChat);
		
		JButton btnCircle = new JButton("Circle");
		btnCircle.setBounds(34, 271, 143, 29);
		contentPane.add(btnCircle);
		btnCircle.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  
				  JFrame Circle =new Circle();
                  Circle.setVisible(true);
				  
			  }
			});
		
		JButton btnViewFriends = new JButton("View Friends");
		btnViewFriends.setBounds(34, 325, 143, 29);
		contentPane.add(btnViewFriends);
		btnViewFriends.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  OracleDatabase.viewFriends(currentUser.email);
				  
				  
			  }
			});
		
		btnGroupChat.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  OracleDatabase.viewChatgroups(currentUser.email);
				  JFrame ChatGroup =new ChatGroup();
				  ChatGroup.setVisible(true);
				  
			  }
			});
		
		JButton btnBrowseMessages = new JButton("Browse Messages");
		btnBrowseMessages.setBounds(34, 375, 143, 29);
		contentPane.add(btnBrowseMessages);
		btnBrowseMessages.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  
				  JFrame Browse =new BrowseMessages();
				  Browse.setVisible(true);
				  
			  }
			});
		
		JButton btnManager = new JButton("Manager");
		btnManager.setBounds(234, 375, 143, 29);
		contentPane.add(btnManager);
		btnManager.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  
				  
				  
			  }
			});
		
		
		currentUser = OracleDatabase.getUserFromEmail(email);
		if(!currentUser.manager){
			btnManager.setVisible(false);
		}
	}
	
	

}
