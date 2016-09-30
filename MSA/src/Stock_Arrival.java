import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Stock_Arrival extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodeNumber;
	private JTextField txtBatchnumber;
	private JTextField txtQuantity;
	private JTextField txtExpiryDate;
	private JTextField txtPurchasingPrice;
	private JTextField txtVendorId;
	private JTextField txtSellingPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stock_Arrival frame = new Stock_Arrival();
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
	public Stock_Arrival() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 480, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#BC8F8F"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStockArrival = new JLabel("STOCK ARRIVAL");
		lblStockArrival.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblStockArrival.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockArrival.setBounds(86, 12, 281, 35);
		contentPane.add(lblStockArrival);
		
		JLabel lblCodeNumber = new JLabel("MEDICINE'S CODE NUMBER :");
		lblCodeNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodeNumber.setBounds(46, 59, 193, 15);
		contentPane.add(lblCodeNumber);
		
		JLabel lblBatchNumber = new JLabel("BATCH NUMBER:");
		lblBatchNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblBatchNumber.setBounds(46, 101, 183, 15);
		contentPane.add(lblBatchNumber);
		
		JLabel lblVendorsId = new JLabel("VENDOR'S ID:");
		lblVendorsId.setHorizontalAlignment(SwingConstants.CENTER);
		lblVendorsId.setBounds(53, 135, 173, 15);
		contentPane.add(lblVendorsId);
		
		JLabel lblPerUnitPurchasing = new JLabel("PER UNIT PURCHASING PRICE:");
		lblPerUnitPurchasing.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerUnitPurchasing.setBounds(36, 175, 210, 15);
		contentPane.add(lblPerUnitPurchasing);
		
		JLabel lblExpiryDate = new JLabel("EXPIRY DATE:");
		lblExpiryDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblExpiryDate.setBounds(86, 202, 132, 24);
		contentPane.add(lblExpiryDate);
		
		JLabel lblQuantityOfNewStock = new JLabel("QUANTITY OF NEW STOCK:");
		lblQuantityOfNewStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantityOfNewStock.setBounds(53, 236, 193, 24);
		contentPane.add(lblQuantityOfNewStock);
		
		txtCodeNumber = new JTextField();
		txtCodeNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodeNumber.setBounds(265, 55, 160, 24);
		contentPane.add(txtCodeNumber);
		txtCodeNumber.setColumns(10);
		
		txtBatchnumber = new JTextField();
		txtBatchnumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtBatchnumber.setBounds(265, 97, 160, 24);
		contentPane.add(txtBatchnumber);
		txtBatchnumber.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(265, 237, 160, 24);
		contentPane.add(txtQuantity);
		
		txtExpiryDate = new JTextField();
		txtExpiryDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtExpiryDate.setColumns(10);
		txtExpiryDate.setBounds(265, 203, 160, 24);
		contentPane.add(txtExpiryDate);
		
		txtPurchasingPrice = new JTextField();
		txtPurchasingPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPurchasingPrice.setColumns(10);
		txtPurchasingPrice.setBounds(265, 166, 160, 24);
		contentPane.add(txtPurchasingPrice);
		
		txtVendorId = new JTextField();
		txtVendorId.setHorizontalAlignment(SwingConstants.CENTER);
		txtVendorId.setColumns(10);
		txtVendorId.setBounds(265, 131, 160, 24);
		contentPane.add(txtVendorId);
		
		JButton btnUpdateStock = new JButton("UPDATE STOCK");
		btnUpdateStock.addActionListener(update_stock);
		btnUpdateStock.setBounds(146, 337, 153, 25);
		
		contentPane.add(btnUpdateStock);
		
		JLabel lblSellingPrice = new JLabel("PER UNIT SELLING PRICE:");
		lblSellingPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblSellingPrice.setBounds(46, 272, 203, 24);
		contentPane.add(lblSellingPrice);
		
		txtSellingPrice = new JTextField();
		txtSellingPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtSellingPrice.setBounds(265, 273, 160, 24);
		contentPane.add(txtSellingPrice);
		txtSellingPrice.setColumns(10);
	}
	
		ActionListener update_stock = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					Connection connect = getConnection();
					/*
					char Code[] = new char[2];
					String s;
					
					Code[0] = txtCodeNumber.getText().toCharArray()[0];
					Code[1] = txtCodeNumber.getText().toCharArray()[1];
					s = String.valueOf(Code);
					String temp = s.concat("%");
					
					int count = 0;
					*/
					String code_number = txtCodeNumber.getText();
					Statement stmnt = connect.createStatement();
					ResultSet r = stmnt.executeQuery("SELECT * from medicines WHERE Code_Number = '"+code_number+"'");

					/*
					while (r.next()) {
						count++;
						//count = r.getInt("count(*)");
					}
					count++;
					
					s = s.concat(txtBatchnumber.getText());
					s = s.concat(String.valueOf(count));
					System.out.println("the code word is: "+s);
					*/
					r.beforeFirst();
					
					int current_stock;
					if (! r.next()){
						//no such a record exists
						JOptionPane.showMessageDialog(null,"Please create the medicine record before updating stock.","NOTE!", JOptionPane.INFORMATION_MESSAGE);
						throw new Exception("Stock not updated!");
					}else{
						current_stock = r.getInt("Quantity");
					}
					
					
					
					try{
											
						Statement query = connect.createStatement();
						ResultSet result = query.executeQuery("SELECT * FROM vendor WHERE Vendor_ID = '"+txtVendorId.getText()+"'");
						result.beforeFirst();
						if (! result.next()){
							JOptionPane.showMessageDialog(null,"Please create the vendor's record before updating stock.","NOTE!", JOptionPane.INFORMATION_MESSAGE);
							throw new Exception("INVALID UPDATE!");
						}
						
						
						PreparedStatement statement = connect.prepareStatement("INSERT INTO stock (Code_Number, Batch_Number, Expiry_Date, Quantity, Per_Unit_Purchasing_Price, Per_Unit_Selling_Price, Vendor_ID, Stock_Arrival_Date) VALUES ('"+code_number+"', '"+txtBatchnumber.getText()+"', ? , "+Integer.parseInt(txtQuantity.getText())+", "+Integer.parseInt(txtPurchasingPrice.getText())+", "+Integer.parseInt(txtSellingPrice.getText())+", '"+txtVendorId.getText()+"',? )");
						//ResultSet result = query.executeQuery("INSERT INTO stock (Code_Number, Batch_Number, Expiry_Date, Quantity, Per_Unit_Purchasing_Price, Per_Unit_Selling_Price, Vendor_ID, Stock_Arrival_Date) VALUES ('"+code_number+"', '"+txtBatchnumber.getText()+"', ? , "+Integer.parseInt(txtQuantity.getText())+", "+Integer.parseInt(txtPurchasingPrice.getText())+", "+Integer.parseInt(txtSellingPrice.getText())+", '"+txtVendorId.getText()+"',? )"); 
						DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
						Date date = new Date();
						java.sql.Date supply_date = new java.sql.Date(date.getTime());
						Date expiry_date = format.parse(txtExpiryDate.getText());
						java.sql.Date expiry = new java.sql.Date(expiry_date.getTime());
						statement.setDate(1, expiry);
						statement.setDate(2, supply_date);
						statement.executeUpdate();
						
						System.out.println("Stock table updated!");
						
						try{
							PreparedStatement newstatement = connect.prepareStatement("UPDATE medicines SET Quantity = "+(current_stock + Integer.parseInt(txtQuantity.getText()))+" WHERE Code_Number = '"+code_number+"'");
							newstatement.executeUpdate();
							
							//System.out.println("Medicine table updated!");
						}catch(Exception e){
							JOptionPane.showMessageDialog(null,"FAILED TO UPDATE medicines DATABASE","ERROR: ", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						
						//print_cheque();
						int price = Integer.parseInt(txtQuantity.getText()) * Integer.parseInt(txtPurchasingPrice.getText());
						Statement query1 = connect.createStatement();
						ResultSet result1 = query1.executeQuery("SELECT * FROM vendor WHERE Vendor_ID = '"+txtVendorId.getText()+"'");
						result1.beforeFirst();
						
						if (result1.next()){
							Cheque.vendorname = result1.getString("Name");
							//System.out.println("vendorname set!");
						}
						
						Cheque.price = price;
						Cheque cheque = new Cheque();
						cheque.setVisible(true);//print the cheque
					//}
										
									
					}catch(Exception e){
						JOptionPane.showMessageDialog(null,"STOCK NOT UPDATED!",e.getMessage(),JOptionPane.ERROR_MESSAGE);
					}
					

					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e.getMessage(),"", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		};
		
			
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
				JOptionPane.showMessageDialog(null, e);
			}
			return null;
		}
		
			
}
