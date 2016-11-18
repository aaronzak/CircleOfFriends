import java.awt.BorderLayout;
import java.awt.EventQueue;

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
		
		JButton btnInactiveUsers = new JButton("Inactive Users");
		btnInactiveUsers.setBounds(20, 88, 137, 29);
		contentPane.add(btnInactiveUsers);
		
		JButton btnTopMessages = new JButton("Top Messages");
		btnTopMessages.setBounds(20, 147, 137, 29);
		contentPane.add(btnTopMessages);
		
		JButton btnCompleteReport = new JButton("Complete Report");
		btnCompleteReport.setBounds(20, 186, 154, 29);
		contentPane.add(btnCompleteReport);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(20, 263, 137, 29);
		contentPane.add(btnRegister);
	}

}
