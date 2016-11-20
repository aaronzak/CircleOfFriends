import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;


public class Manager extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager frame = new Manager();
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
	public Manager() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnActiveUsers = new JButton("Active Users");
		btnActiveUsers.setBounds(20, 40, 137, 29);
		contentPane.add(btnActiveUsers);
		btnActiveUsers.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  BrowseDB.getTop3ActiveUsers();
			  }
			});
		
		JButton btnInactiveUsers = new JButton("Inactive Users");
		btnInactiveUsers.setBounds(20, 88, 137, 29);
		contentPane.add(btnInactiveUsers);
		btnInactiveUsers.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  BrowseDB.searchInactiveUsers();
			  }
			});
		
		JButton btnTopMessages = new JButton("Top Messages");
		btnTopMessages.setBounds(20, 147, 137, 29);
		contentPane.add(btnTopMessages);
		
		JButton btnCompleteReport = new JButton("Complete Report");
		btnCompleteReport.setBounds(20, 186, 154, 29);
		contentPane.add(btnCompleteReport);
		btnCompleteReport.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  System.out.println("Total new messages " + BrowseDB.totalMessages());
				  System.out.println("Total message reads " + BrowseDB.totalReads());
				  System.out.println("Average reads per message " + (double) BrowseDB.totalReads()/BrowseDB.totalMessages());
				  System.out.println("Top 3 messages "); 
				  System.out.println("Top 3 users " ); BrowseDB.getTop3ActiveUsers();
				  System.out.println("Inactive users: "); BrowseDB.searchInactiveUsers();
				  System.out.println("Recent message by topic"); BrowseDB.getMostRecentMessageTopic();
			  }
			});
		
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(20, 263, 137, 29);
		contentPane.add(btnRegister);
		btnRegister.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				  
				  JFrame SignUp =new SignUp();
                SignUp.setVisible(true);
			  }
			});
	}

}
