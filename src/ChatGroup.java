import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;


public class ChatGroup extends JFrame {

	private JPanel contentPane;
	private JTextField txtChatgroupname, txtChatgroupDuration,txtChatgroupInvite,
	txtChatgroupAccept, txtOpenChatgroupname;
	private User currentUser;

	private String currentChatgroup;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatGroup frame = new ChatGroup();
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
	public ChatGroup() {
		

		String cEmail = OracleDatabase.getSession();
		currentUser = OracleDatabase.getUserFromEmail(cEmail);
		currentChatgroup = null;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JLabel lblCurrentChat = new JLabel("");
		lblCurrentChat.setBounds(168, 6, 61, 16);
		contentPane.add(lblCurrentChat);
		
		JList list = new JList();
		list.setBounds(6, 148, 588, 312);
		contentPane.add(list);
		
		JButton btnViewInvitations = new JButton("View Invitations");
		btnViewInvitations.setBounds(6, 628, 173, 29);
		contentPane.add(btnViewInvitations);
		
		JButton btnOpenChatgroup = new JButton("Open Chatgroup");
		btnOpenChatgroup.setBounds(6, 572, 173, 29);
		contentPane.add(btnOpenChatgroup);
		btnOpenChatgroup.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				 if(OracleDatabase.checkUserInChatgroup(currentUser.email, txtOpenChatgroupname.getText())){
					 currentChatgroup = txtOpenChatgroupname.getText();
					 lblCurrentChat.setText(currentChatgroup);
				 }
			  }
			});
		
		
		btnViewInvitations.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				 OracleDatabase.viewChatgroupInvites(currentUser.email);
			  }
			});
		
		txtOpenChatgroupname = new JTextField();
		txtOpenChatgroupname.setText("chatgroup_name");
		txtOpenChatgroupname.setBounds(255, 572, 276, 28);
		contentPane.add(txtOpenChatgroupname);
		txtOpenChatgroupname.setColumns(10);
		
		txtChatgroupname = new JTextField();
		txtChatgroupname.setText("chatgroup_name");
		txtChatgroupname.setBounds(255, 673, 276, 28);
		contentPane.add(txtChatgroupname);
		txtChatgroupname.setColumns(10);
		
		txtChatgroupDuration = new JTextField();
		txtChatgroupDuration.setText("7");
		txtChatgroupDuration.setBounds(215, 673, 40, 28);
		contentPane.add(txtChatgroupDuration);
		txtChatgroupDuration.setColumns(10);
		
		JButton btnCreateChatgroup = new JButton("Create Chatgroup");
		btnCreateChatgroup.setBounds(6, 673, 173, 29);
		contentPane.add(btnCreateChatgroup);
		btnCreateChatgroup.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  OracleDatabase.createChatgroup(currentUser, txtChatgroupname.getText(),
						  Integer.parseInt(txtChatgroupDuration.getText()));
			  }
			});
		
		JButton btnInviteChatgroup = new JButton("Invite to Chatgroup");
		btnInviteChatgroup.setBounds(6, 723, 173, 29);
		contentPane.add(btnInviteChatgroup);
		
		txtChatgroupInvite = new JTextField();
		txtChatgroupInvite.setText("");
		txtChatgroupInvite.setBounds(215, 723, 240, 28);
		contentPane.add(txtChatgroupInvite);
		txtChatgroupInvite.setColumns(10);
		btnInviteChatgroup.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  if(currentChatgroup != null){
					  OracleDatabase.inviteToChatgroup(currentUser, txtChatgroupInvite.getText(), currentChatgroup);
				  }
				  else{
					  System.out.println("First open chatgroup");
				  }
			  }
			});
		txtChatgroupAccept = new JTextField();
		txtChatgroupAccept.setText("");
		txtChatgroupAccept.setBounds(215, 773, 240, 28);
		contentPane.add(txtChatgroupAccept);
		txtChatgroupAccept.setColumns(10); 
		
		JButton btnAcceptChatgroup = new JButton("Accept Invite");
		btnAcceptChatgroup.setBounds(6, 773, 173, 29);
		contentPane.add(btnAcceptChatgroup);
		btnAcceptChatgroup.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  OracleDatabase.acceptChatgroup(currentUser.email, txtChatgroupAccept.getText());
			  }
			});
		
		
	}

}
