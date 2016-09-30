import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JButton;

public class Sale_Item extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodeNumber;
	private JTextField txtBatchNumber;
	private JTextField txtUnitsSold;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sale_Item frame = new Sale_Item();
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
	public Sale_Item() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#FFDEAD"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSaleDetails = new JLabel("SALE DETAILS");
		lblSaleDetails.setFont(new Font("Droid Serif", Font.BOLD, 16));
		lblSaleDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaleDetails.setBounds(89, 12, 263, 20);
		contentPane.add(lblSaleDetails);
		
		JLabel lblMedicineCodeNumber = new JLabel("MEDICINE CODE NUMBER:");
		lblMedicineCodeNumber.setBounds(50, 58, 174, 15);
		contentPane.add(lblMedicineCodeNumber);
		
		txtCodeNumber = new JTextField();
		txtCodeNumber.setBounds(280, 56, 114, 19);
		contentPane.add(txtCodeNumber);
		txtCodeNumber.setColumns(10);
		
		JLabel lblBatchNumber = new JLabel("BATCH NUMBER :");
		lblBatchNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblBatchNumber.setBounds(56, 101, 168, 15);
		contentPane.add(lblBatchNumber);
		
		txtBatchNumber = new JTextField();
		txtBatchNumber.setBounds(280, 99, 114, 19);
		contentPane.add(txtBatchNumber);
		txtBatchNumber.setColumns(10);
		
		JLabel lblUnitsSold = new JLabel("UNITS SOLD :");
		lblUnitsSold.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnitsSold.setBounds(50, 139, 174, 20);
		contentPane.add(lblUnitsSold);
		
		txtUnitsSold = new JTextField();
		txtUnitsSold.setBounds(280, 140, 114, 19);
		contentPane.add(txtUnitsSold);
		txtUnitsSold.setColumns(10);
		
		JButton btnCashReceipt = new JButton("CASH RECEIPT");
		btnCashReceipt.setBounds(153, 206, 149, 25);
		btnCashReceipt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			//initialize the static variables here
				UpdateDB();
				
				
			}
			
		});
		contentPane.add(btnCashReceipt);
	}
	
	public void UpdateDB(){
		try{
			Connection connect = getConnection();
			Date date = new Date(); //today's date
			java.sql.Date sql_date = new java.sql.Date(date.getTime());
			
			String medicine_code = txtCodeNumber.getText();
			String batch_number = txtBatchNumber.getText();
			int units_sold = Integer.parseInt(txtUnitsSold.getText());
			
			CashReceipt.batch = batch_number;
			CashReceipt.quantity = units_sold;
			CashReceipt.date = date;
			
			Statement query = connect.createStatement();
			ResultSet result = query.executeQuery("SELECT * FROM stock WHERE ( Code_Number = '"+medicine_code+"' AND Batch_Number = '"+batch_number+"')"); 
			
			if (result.next()){
				
				//check if enough in stock to sell
				int current_quant = result.getInt("Quantity");
				if (current_quant < Integer.parseInt(txtUnitsSold.getText())){
					JOptionPane.showMessageDialog(null,"Not enough stocks present for this Batch Number!\n"+ current_quant + "units of this batch number are available." ,"ERROR",JOptionPane.ERROR_MESSAGE);
					throw new Exception();
				}
							
				int purchasing_price = result.getInt("Per_Unit_Purchasing_Price");
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date expiry_date = format.parse(format.format(result.getDate("Expiry_Date")));
				java.sql.Date sql_expiry_date = new java.sql.Date(expiry_date.getTime());
				int selling_price = result.getInt("Per_Unit_Selling_Price");
				try{
					PreparedStatement statement = connect.prepareStatement("INSERT INTO sales (Date_of_Sale, Code_Number, Batch_Number,Expiry_Date, Per_Unit_Selling_Price, Quantity_Sold, Per_Unit_Purchasing_Price) VALUES (?,'"+medicine_code+"','"+batch_number+"',?,"+selling_price+", "+units_sold+" ,"+purchasing_price+")");
					statement.setDate(1, sql_date); //
					statement.setDate(2, sql_expiry_date); // 
					statement.executeUpdate();
							
					//System.out.println("Updated successfully!");
					//if sale table updated successfully
					try{
						
						PreparedStatement newstatement = connect.prepareStatement("UPDATE stock SET Quantity = "+(current_quant - units_sold)+" WHERE ( Code_Number = '"+medicine_code+"' AND Batch_Number = '"+batch_number+"')");
						newstatement.executeUpdate();
						
						//System.out.println("Stocks table Updated!");
						
						Statement newquery = connect.createStatement();
						ResultSet medresult = newquery.executeQuery("SELECT * FROM medicines WHERE Code_Number = '"+medicine_code+"'");
						
						medresult.beforeFirst();
						if (medresult.next()){
							int total_stock = medresult.getInt("Quantity");
							int final_stock = total_stock - units_sold;
							
							PreparedStatement updatestatement = connect.prepareStatement("UPDATE medicines SET Quantity = "+final_stock+" WHERE Code_Number = '"+medicine_code+"'");
							updatestatement.executeUpdate();
							
							//System.out.println("medicines table updated!");
						}
						
						String trade_name = medresult.getString("Trade_Name");
						
						CashReceipt.name = trade_name;
						CashReceipt.unit_price = selling_price;
						CashReceipt.expiry = expiry_date;
						
						//if stocks updated also, finally print cash receipt for the customer
						CashReceipt receipt = new CashReceipt();
						receipt.setVisible(true);
						
						
					}catch(Exception e){
						JOptionPane.showMessageDialog(null,"Could not update medicine stocks' database!","ERROR",JOptionPane.ERROR_MESSAGE);
					}
								
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Could not update sales' database!","ERROR",JOptionPane.ERROR_MESSAGE);
					
					//System.out.println(e);
				}
							
			}else{
				JOptionPane.showMessageDialog(null,"No such record in database.","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		
						
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"SALE ABORTED!","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static Connection getConnection() throws Exception{
		try{
			String Driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost/medicines";
			String username = "root";
			String password = "131260";
			Class.forName(Driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			//System.out.println("Connected!");
			//JOptionPane.showMessageDialog(null, "Connected!");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
		
		return null;
	}
}
