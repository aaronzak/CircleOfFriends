import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;


public class BrowseMessages extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea txtrTopics;
	private JCheckBox chckbxIncludeAll;
	private JLabel lblUsers;
	private JTextField emailSearch;
	private JTextField txtTopicsearc;
	private JTextField textField_1;
	private JTextField txtTotal;
	private JLabel lblRecentMessage;
	private JLabel lblTotalMessages;
	private JButton btnSearchusers;
	private JLabel lblEmail;
	private JLabel lblTopics;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrowseMessages frame = new BrowseMessages();
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
	public BrowseMessages() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText("7");
		textField.setBounds(6, 51, 110, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		 txtrTopics = new JTextArea();
		txtrTopics.setText("topics");
		txtrTopics.setBounds(6, 91, 438, 75);
		contentPane.add(txtrTopics);
		
		 chckbxIncludeAll = new JCheckBox("Include all");
		chckbxIncludeAll.setBounds(316, 178, 128, 23);
		contentPane.add(chckbxIncludeAll);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(327, 222, 117, 29);
		contentPane.add(btnSearch);
		
		lblUsers = new JLabel("Users");
		lblUsers.setBounds(163, 263, 84, 16);
		contentPane.add(lblUsers);
		
		emailSearch = new JTextField();
		emailSearch.setText("");
		emailSearch.setBounds(6, 298, 438, 28);
		contentPane.add(emailSearch);
		emailSearch.setColumns(10);
		
		txtTopicsearc = new JTextField();
		txtTopicsearc.setText("");
		txtTopicsearc.setBounds(6, 344, 438, 28);
		contentPane.add(txtTopicsearc);
		txtTopicsearc.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("");
		textField_1.setBounds(6, 389, 134, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setText("");
		txtTotal.setBounds(211, 389, 134, 28);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		lblRecentMessage = new JLabel("Recent Message");
		lblRecentMessage.setBounds(6, 429, 134, 16);
		contentPane.add(lblRecentMessage);
		
		lblTotalMessages = new JLabel("Total messages");
		lblTotalMessages.setBounds(211, 429, 134, 16);
		contentPane.add(lblTotalMessages);
		
		btnSearchusers = new JButton("Search Users");
		btnSearchusers.setBounds(294, 493, 150, 29);
		contentPane.add(btnSearchusers);
		btnSearchusers.addActionListener(new ActionListener()
		{
			
			
			  public void actionPerformed(ActionEvent e)
			  {
				  if(!emailSearch.getText().equals("")){
					  BrowseDB.searchUserEmail(emailSearch.getText());
				  }
				  else if(!txtTopicsearc.getText().equals("")){
					  ArrayList<String> topics = new ArrayList<String>();
						
						String delims = "[,]+";
						String[] topicArray = txtTopicsearc.getText().split(delims);
						for(int i = 0; i< topicArray.length; i++){
							topics.add(topicArray[i]);
						}
					  BrowseDB.searchUserTopic(topics);
				  }
				  else if(!textField_1.getText().equals("")){
					  BrowseDB.searchUserRecent(Integer.parseInt(textField_1.getText()));
				  }
				  else if(!txtTotal.getText().equals("")){
					  BrowseDB.searchUserTotal(Integer.parseInt(txtTotal.getText()));
				  }
				  
			  }
			});
		
		lblEmail = new JLabel("email");
		lblEmail.setBounds(6, 281, 61, 16);
		contentPane.add(lblEmail);
		
		lblTopics = new JLabel("topics");
		lblTopics.setBounds(6, 325, 61, 16);
		contentPane.add(lblTopics);
		
		btnSearch.addActionListener(new ActionListener()
		{
			
				
			  public void actionPerformed(ActionEvent e)
			  {
				  ArrayList<String> topics = new ArrayList<String>();
					
					String delims = "[,]+";
					

					if(!txtrTopics.getText().equals("")){
						String[] topicArray = txtrTopics.getText().split(delims);
						for(int i = 0; i< topicArray.length; i++){
							
							topics.add(topicArray[i]);
						}
				 BrowseDB.searchMessages(topics, Integer.parseInt(textField.getText()),chckbxIncludeAll.isSelected() );
					}
			  }
			});
	}

}
