import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;


public class Circle extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnViewCircle = new JButton("View Circle");
		btnViewCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//shows the messages in mycircle
			}
		});
		btnViewCircle.setBounds(6, 25, 154, 29);
		contentPane.add(btnViewCircle);
		
		JTextArea txtrText = new JTextArea();
		txtrText.setText("");
		txtrText.setBounds(6, 70, 538, 103);
		contentPane.add(txtrText);
		
		JLabel lblText = new JLabel("Text");
		lblText.setBounds(244, 52, 61, 16);
		contentPane.add(lblText);
		
		JLabel lblTopics = new JLabel("Topics");
		lblTopics.setBounds(244, 172, 61, 16);
		contentPane.add(lblTopics);
		
		JTextArea textArea = new JTextArea();
		textArea.setText("");
		textArea.setBounds(6, 196, 538, 103);
		contentPane.add(textArea);
		
		JLabel lblRecipients = new JLabel("Recipients");
		lblRecipients.setBounds(233, 310, 89, 16);
		contentPane.add(lblRecipients);
		
		JTextArea txtrRecipients = new JTextArea();
		txtrRecipients.setText("");
		txtrRecipients.setBounds(6, 331, 538, 121);
		contentPane.add(txtrRecipients);
		
		JButton btnSend = new JButton("Send");
		btnSend.setBounds(400, 475, 144, 29);
		contentPane.add(btnSend);
	}

}
