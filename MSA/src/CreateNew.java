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
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;


public class CreateNew extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNew frame = new CreateNew();
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
	public CreateNew() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 564, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#AFEEEE"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewMedicineRecord = new JLabel("New Medicine Record");
		lblNewMedicineRecord.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblNewMedicineRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewMedicineRecord.setBounds(106, 11, 383, 51);
		contentPane.add(lblNewMedicineRecord);
		
		JLabel lblTradeName = new JLabel("Trade Name:");
		lblTradeName.setBounds(41, 73, 137, 25);
		contentPane.add(lblTradeName);
		
		JLabel lblGenericName = new JLabel("Generic Name:");
		lblGenericName.setBounds(41, 109, 137, 25);
		contentPane.add(lblGenericName);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(41, 145, 137, 25);
		contentPane.add(lblDescription);
		
		JLabel lblQuantityPresent = new JLabel("Quantity Present:");
		lblQuantityPresent.setBounds(41, 181, 137, 25);
		contentPane.add(lblQuantityPresent);
		
		JLabel lblThresholdValue = new JLabel("Threshold Value:");
		lblThresholdValue.setBounds(41, 216, 137, 25);
		contentPane.add(lblThresholdValue);
		
		textField_1 = new JTextField();
		textField_1.setBounds(257, 73, 232, 23);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(257, 110, 232, 23);
		contentPane.add(textField_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(257, 146, 232, 23);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(257, 182, 232, 23);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(257, 217, 232, 23);
		contentPane.add(textField_6);
		
		JButton btnCreateNewRecord = new JButton("Create New Record");
		btnCreateNewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					int count=0;
					char Code[] = new char[2];
					String s;
					
					Code[0] = textField_1.getText().toCharArray()[0];
					Code[1] = textField_1.getText().toCharArray()[1];
					s = String.valueOf(Code);
					String compare = s.concat("%");
					
					Connection con = getConnection();
					
					
					Statement stmnt = con.createStatement();
					ResultSet r = stmnt.executeQuery("SELECT * from medicines WHERE Code_Number LIKE '"+compare+"'");

					while (r.next()) {
						count ++;
					}
					count++;
					
					//s = s.concat(textField_3.getText());
					s = s.concat(String.valueOf(count));
					//System.out.println("code: " + s);
					PreparedStatement new_rec = con.prepareStatement("INSERT INTO medicines (Code_Number, Trade_Name, Generic_Name, Description, Quantity, Threshold_Value) VALUES ('"+s+"', '"+textField_1.getText()+"','"+textField_2.getText()+"', '"+textField_4.getText()+"', "+Integer.parseInt(textField_5.getText())+", "+Integer.parseInt(textField_6.getText())+")");
					
					new_rec.executeUpdate();
					JOptionPane.showMessageDialog(null, "Code Number " + s);
					JOptionPane.showMessageDialog(null, "SUCCESS!");
					
					}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
					finally{
						//System.out.println("Success!");
					}
			}
		});
		btnCreateNewRecord.setBounds(160, 288, 216, 48);
		contentPane.add(btnCreateNewRecord);
		
	}

	public static Connection getConnection() throws Exception{
		try{
			String Driver = "com.mysql.jdbc.Driver";
			//String url = "jdbc:mysql://127.0.0.1:3306/msa";
			String url = "jdbc:mysql://localhost/medicines";
			String username = "root";
			String password = "131260";
			//String password = "curiosity";
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