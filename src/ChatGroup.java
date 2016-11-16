import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;


public class ChatGroup extends JFrame {

	private JPanel contentPane;
	private JTextField txtChatgroupname;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		txtChatgroupname.setBounds(231, 572, 276, 28);
		contentPane.add(txtChatgroupname);
		txtChatgroupname.setColumns(10);
		
		JButton btnCreateChatgroup = new JButton("Create Chatgroup");
		btnCreateChatgroup.setBounds(6, 673, 173, 29);
		contentPane.add(btnCreateChatgroup);
	}

}
