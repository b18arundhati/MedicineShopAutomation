import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;


public class CreateNewVnd extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewVnd frame = new CreateNewVnd();
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
	public CreateNewVnd() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#AFEEEE"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewMedicineRecord = new JLabel("New Vendor Record");
		lblNewMedicineRecord.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblNewMedicineRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewMedicineRecord.setBounds(10, 11, 383, 51);
		contentPane.add(lblNewMedicineRecord);
		
		JLabel lblTradeName = new JLabel("Name:");
		lblTradeName.setBounds(41, 73, 103, 25);
		contentPane.add(lblTradeName);
		
		JLabel lblGenericName = new JLabel("Address:");
		lblGenericName.setBounds(41, 109, 103, 25);
		contentPane.add(lblGenericName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(154, 74, 206, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(154, 110, 206, 23);
		contentPane.add(textField_2);
		
		JButton btnCreateNewRecord = new JButton("Create New Record");
		btnCreateNewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String tn, gn, bn, dsc, s, ed;
				//int q, tv, sp, pp;
				
				try{
					int count=0;
					char Code[] = new char[2];
					String s;
					
					Code[0] = textField_1.getText().toCharArray()[0];
					Code[1] = textField_1.getText().toCharArray()[1];
					s = String.valueOf(Code);
					
					Connection con = getConnection();
					
					Statement stmnt = con.createStatement();
					ResultSet r = stmnt.executeQuery("SELECT * from vendor");

					while (r.next()) {
						count++;
					}
					count++;
					
					s = s.concat(String.valueOf(count));
					
					//System.out.println("Code number: " + s);
					//JOptionPane.showMessageDialog(null, String.valueOf(count) + s);
					
					PreparedStatement new_rec = con.prepareStatement("INSERT INTO vendor (Vendor_ID, Name, Address) VALUES ('"+s+"', '"+textField_1.getText()+"','"+textField_2.getText()+"')");
					
					new_rec.executeUpdate();
					JOptionPane.showMessageDialog(null, "Vendor ID: " + s);
					JOptionPane.showMessageDialog(null, "Success!");
					}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
					finally{
						//System.out.println("Success!");
					}
			}
		});
		btnCreateNewRecord.setBounds(93, 167, 216, 48);
		contentPane.add(btnCreateNewRecord);
		
	}

	public static Connection getConnection() throws Exception{
		try{
			String Driver = "com.mysql.jdbc.Driver";
			//String url = "jdbc:mysql://127.0.0.1:3306/msa";
			String url = "jdbc:mysql://localhost/medicines";
			String username = "root";
			//String password = "curiosity";
			String password = "131260";
			Class.forName(Driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			//System.out.println("Connected!");
			//JOptionPane.showMessageDialog(null, "Connected!");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
}