import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login_MSA extends JFrame {

	private JPanel contentPane;
	private JPasswordField pwdPassword;
	private JTextField txtUsername;
	
	private String username = "MSA";
	private String password = "12345";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_MSA frame = new Login_MSA();
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
	public Login_MSA() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#FAEBD7"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setFont(new Font("Arial", Font.PLAIN, 15));
		lblUserLogin.setBounds(250, 28, 188, 31);
		contentPane.add(lblUserLogin);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(24, 28, 148, 150);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(192, 78, 80, 23);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(192, 116, 80, 23);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String readusername = txtUsername.getText();
				String readpassword = String.valueOf(pwdPassword.getPassword());
				
				if (readusername.equals(username) && readpassword.equals(password)){
					Menu menu = new Menu();
					menu.setVisible(true);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Incorrect username or password", "ERROR IN LOGIN", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(133, 189, 212, 31);
		contentPane.add(btnNewButton);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setEchoChar('*');
		pwdPassword.setBounds(290, 116, 167, 23);
		contentPane.add(pwdPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(290, 80, 167, 23);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
	}
}