import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;


public class Circle extends JFrame {

	private JPanel contentPane;
	private User currentUser;
	private JTextArea textArea, txtrRecipients, txtrText;
	private JCheckBox isPublicBox;
	private String cEmail;
	private JTextField txtDelete;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Circle frame = new Circle();
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
	public Circle() {

		 cEmail = OracleDatabase.getSession();
		currentUser = OracleDatabase.getUserFromEmail(cEmail);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewCircle = new JButton("View Circle");
		btnViewCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CircleDB.viewCircle(cEmail);
			}
		});
		btnViewCircle.setBounds(6, 25, 154, 29);
		contentPane.add(btnViewCircle);
		
		 txtrText = new JTextArea();
		txtrText.setText("");
		txtrText.setBounds(6, 70, 538, 103);
		contentPane.add(txtrText);
		
		JLabel lblText = new JLabel("Text");
		lblText.setBounds(244, 52, 61, 16);
		contentPane.add(lblText);
		
		JLabel lblTopics = new JLabel("Topics");
		lblTopics.setBounds(244, 172, 61, 16);
		contentPane.add(lblTopics);
		
		textArea = new JTextArea();
		textArea.setText("");
		textArea.setBounds(6, 196, 538, 103);
		contentPane.add(textArea);
		
		JLabel lblRecipients = new JLabel("Recipients");
		lblRecipients.setBounds(233, 310, 89, 16);
		contentPane.add(lblRecipients);
		
		 txtrRecipients = new JTextArea();
		txtrRecipients.setText("");
		txtrRecipients.setBounds(6, 331, 538, 121);
		contentPane.add(txtrRecipients);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(400, 475, 144, 29);
		contentPane.add(btnSend);
		
		txtDelete = new JTextField();
		txtDelete.setText("");
		txtDelete.setBounds(185, 525, 300, 30);
		contentPane.add(txtDelete);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(10, 525, 144, 29);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CircleDB.deleteMessage(cEmail, Integer.parseInt(txtDelete.getText()));
			}
		});
		
		 isPublicBox = new JCheckBox("Public");
		isPublicBox.setBounds(340, 475, 60, 25);
		contentPane.add(isPublicBox);
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> recipients = new ArrayList<String>();
				ArrayList<String> topics = new ArrayList<String>();
				
				String delims = "[,]+";
				String[] recArray = txtrRecipients.getText().split(delims);
				if(!txtrRecipients.getText().equals("")){
				for(int i = 0; i< recArray.length; i++){
					recipients.add(recArray[i]);
				}
				}
				else recipients = null;

				if(!textArea.getText().equals("")){
					String[] topicArray = textArea.getText().split(delims);
					for(int i = 0; i< topicArray.length; i++){
						topics.add(topicArray[i]);
					}
					
					
					CircleDB.createCircleMessage(currentUser.email, recipients, topics, txtrText.getText(), isPublicBox.isSelected());
				
				}
			}
		});
	}

}
