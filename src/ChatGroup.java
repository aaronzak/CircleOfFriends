import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;


public class ChatGroup extends JFrame {

	private JPanel contentPane;
	private JTextField txtChatgroupname, txtChatgroupDuration;
	private User currentUser;

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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBounds(6, 48, 588, 512);
		contentPane.add(list);
		
		JButton btnViewInvitations = new JButton("View Invitations");
		btnViewInvitations.setBounds(6, 628, 173, 29);
		contentPane.add(btnViewInvitations);
		
		JButton btnOpenChatgroup = new JButton("Open Chatgroup");
		btnOpenChatgroup.setBounds(6, 572, 173, 29);
		contentPane.add(btnOpenChatgroup);
		
		txtChatgroupname = new JTextField();
		txtChatgroupname.setText("chatgroup_name");
		txtChatgroupname.setBounds(255, 673, 276, 28);
		contentPane.add(txtChatgroupname);
		txtChatgroupname.setColumns(10);
		
		txtChatgroupDuration = new JTextField();
		txtChatgroupDuration.setText("dur");
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
		
	}

}
