import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;


public class PrivateMessage extends JFrame {

	private JPanel contentPane;
	private JTextField txtUseremail;
	private JTextField textField;
	private JButton btnSendMessage;
	private JButton btnDeleteMessage;
	private JTextField delete_message_textfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrivateMessage frame = new PrivateMessage();
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
	public PrivateMessage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterUser = new JLabel("Enter User");
		lblEnterUser.setBounds(35, 29, 97, 16);
		contentPane.add(lblEnterUser);
		
		txtUseremail = new JTextField();
		txtUseremail.setText("user_email");
		txtUseremail.setBounds(110, 23, 230, 28);
		contentPane.add(txtUseremail);
		txtUseremail.setColumns(10);
		
		JButton btnFindUser = new JButton("Find User");
		btnFindUser.setBounds(346, 24, 98, 29);
		contentPane.add(btnFindUser);
		
		JList list = new JList();
		list.setBounds(6, 63, 588, 290);
		contentPane.add(list);
		
		textField = new JTextField();
		textField.setBounds(6, 390, 527, 121);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSendMessage = new JButton("Send Message");
		btnSendMessage.setBounds(416, 523, 117, 29);
		contentPane.add(btnSendMessage);
		
		btnDeleteMessage = new JButton("Delete Message");
		btnDeleteMessage.setBounds(6, 594, 142, 29);
		contentPane.add(btnDeleteMessage);
		
		delete_message_textfield = new JTextField();
		delete_message_textfield.setBounds(206, 593, 327, 28);
		contentPane.add(delete_message_textfield);
		delete_message_textfield.setColumns(10);
	}
}
