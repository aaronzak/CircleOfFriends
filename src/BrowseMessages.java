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


public class BrowseMessages extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea txtrTopics;
	private JCheckBox chckbxIncludeAll;
	
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
		setBounds(100, 100, 450, 300);
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
