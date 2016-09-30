import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JTextField txtTradeName;
	private JTextField txtGenericName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 516);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.decode("#FFFFE0"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCuriosity = new JLabel("CURIOSITY");
		lblCuriosity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuriosity.setFont(new Font("Kristen ITC", Font.BOLD, 26));
		lblCuriosity.setBounds(77, 24, 457, 62);
		contentPane.add(lblCuriosity);
		
		JLabel lblMedicineShopAutomation = new JLabel("Medicine Shop Automation");
		lblMedicineShopAutomation.setFont(new Font("Kristen ITC", Font.PLAIN, 21));
		lblMedicineShopAutomation.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicineShopAutomation.setBounds(138, 77, 332, 62);
		contentPane.add(lblMedicineShopAutomation);
		
		JLabel lbl1 = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/cart.png")).getImage();
		lbl1.setIcon(new ImageIcon(img));
		lbl1.setBounds(10, 24, 101, 104);
		contentPane.add(lbl1);
		
		JLabel lbl2 = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("/bottle.png")).getImage();
		lbl2.setIcon(new ImageIcon(img2));
		lbl2.setBounds(499, 24, 95, 104);
		contentPane.add(lbl2);
		
		JButton btnNewButton = new JButton("Items to order");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.decode("#A0522D"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					ShowOrder n = new ShowOrder();
					n.setVisible(true);
				}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
			}
		});
		btnNewButton.setBounds(33, 150, 154, 46);
		contentPane.add(btnNewButton);
		
		JButton btnExpiredItemsTo = new JButton("Expired Items to be Replaced");
		btnExpiredItemsTo.setForeground(Color.WHITE);
		btnExpiredItemsTo.setBackground(Color.decode("#A0522D"));
		btnExpiredItemsTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				try{
					ShowExp exp = new ShowExp();
					exp.setVisible(true);
				}catch(Exception e){}
			}
		});
		btnExpiredItemsTo.setBounds(197, 150, 223, 46);
		contentPane.add(btnExpiredItemsTo);
		
		JButton btnNewRecord = new JButton("New Record");
		btnNewRecord.setForeground(Color.WHITE);
		btnNewRecord.setBackground(Color.decode("#A0522D"));
		btnNewRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] choices = { "Medicine Record", "Vendor Record"};
			    String input = (String) JOptionPane.showInputDialog(null, "What sort of record do you wish to create?",
			        "New Record", JOptionPane.QUESTION_MESSAGE, null, choices, // Array of choices
			    	choices[0]); // Initial choice
			    
			    if(input.compareToIgnoreCase("Medicine Record")==0){
			    	CreateNew n = new CreateNew();
			    	n.setVisible(true);
			    }
			    
			    else if(input.compareToIgnoreCase("Vendor Record")==0){
			    	CreateNewVnd v = new CreateNewVnd();
			    	v.setVisible(true);
			    }
			}
		});
		btnNewRecord.setBounds(430, 150, 143, 46);
		contentPane.add(btnNewRecord);
		
		JLabel lblSearchForMedicine = new JLabel("Search for Medicine:");
		lblSearchForMedicine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSearchForMedicine.setBounds(89, 217, 272, 34);
		contentPane.add(lblSearchForMedicine);
		
		txtTradeName = new JTextField();
		txtTradeName.setBounds(289, 262, 143, 27);
		contentPane.add(txtTradeName);
		txtTradeName.setColumns(10);
		
		txtGenericName = new JTextField();
		txtGenericName.setColumns(10);
		txtGenericName.setBounds(289, 299, 143, 27);
		contentPane.add(txtGenericName);
		
		JRadioButton rdbtnByTradename = new JRadioButton("By Trade Name");
		rdbtnByTradename.setForeground(Color.BLACK);
		rdbtnByTradename.setBackground(Color.decode("#FFFFE0"));
		rdbtnByTradename.setBounds(138, 258, 143, 34);
		contentPane.add(rdbtnByTradename);
		
		JRadioButton rdbtnByGenericName = new JRadioButton("By Generic Name");
		rdbtnByGenericName.setForeground(Color.BLACK);
		rdbtnByGenericName.setBackground(Color.decode("#FFFFE0"));
		rdbtnByGenericName.setBounds(138, 295, 144, 34);
		contentPane.add(rdbtnByGenericName);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnByGenericName);
		group.add(rdbtnByTradename);
		
		JButton btnGo = new JButton("GO");
		btnGo.setForeground(Color.WHITE);
		btnGo.setBackground(Color.decode("#A0522D"));
		btnGo.setBounds(473, 272, 95, 46);
		btnGo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (rdbtnByGenericName.isSelected()){
					search_by_generic_name(txtGenericName.getText());
				}
				else if (rdbtnByTradename.isSelected()){
					search_by_trade_name(txtTradeName.getText());
				}
				
			}
			
		});
		contentPane.add(btnGo);
		
		JButton btnSellItem = new JButton("Sell Item");
		btnSellItem.setForeground(Color.WHITE);
		btnSellItem.setBackground(Color.decode("#A0522D"));
		btnSellItem.setBounds(33, 350, 165, 46);
		btnSellItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Sale_Item sell = new Sale_Item();
				sell.setVisible(true);
			}
			
		});
		contentPane.add(btnSellItem);
		
		JButton btnDeleteRecord = new JButton("Delete Record");
		btnDeleteRecord.setForeground(Color.WHITE);
		btnDeleteRecord.setBackground(Color.decode("#A0522D"));
		btnDeleteRecord.setBounds(222, 350, 165, 46);
		btnDeleteRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String[] choices = { "Medicine Record", "Vendor Record"};
			    String input = (String) JOptionPane.showInputDialog(null, "What sort of record do you wish to delete?",
			        "New Record", JOptionPane.QUESTION_MESSAGE, null, choices, // Array of choices
			    	choices[0]); // Initial choice
			    
			    if(input.compareToIgnoreCase("Medicine Record")==0){
			    	try{
						String ip_cd = JOptionPane.showInputDialog(null, "Which medicine record do you wish to delete? (Enter Code Number)");
						Connection con = getConnection();
						PreparedStatement new_rec = con.prepareStatement("DELETE FROM medicines where Code_Number = ?");
						new_rec.setString(1, ip_cd);
						
						new_rec.executeUpdate();
						}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
						finally{
							//System.out.println("Success!");
							JOptionPane.showMessageDialog(null, "Deleted!");
						}
			    }
			    
			    else if(input.compareToIgnoreCase("Vendor Record")==0){
			    	try{
						String ip_cd = JOptionPane.showInputDialog(null, "Which vendor record do you widh to delete? (Enter Vendor Code)");
						Connection con = getConnection();
						PreparedStatement new_rec = con.prepareStatement("DELETE FROM vendor where Vendor_ID = ?");
						new_rec.setString(1, ip_cd);
								
						new_rec.executeUpdate();
						JOptionPane.showMessageDialog(null, "Deleted!");
						}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
			    }
			}
		});
		contentPane.add(btnDeleteRecord);
		
		JButton btnStockArrival = new JButton("Stock Arrival");
		btnStockArrival.setForeground(Color.WHITE);
		btnStockArrival.setBackground(Color.decode("#A0522D"));
		btnStockArrival.setBounds(408, 350, 165, 46);
		btnStockArrival.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Stock_Arrival stock = new Stock_Arrival();
				stock.setVisible(true);
			}
			
		});
		contentPane.add(btnStockArrival);
		
		JButton btnRevenueprofitpayment = new JButton("Revenue/Profit/Payment");
		btnRevenueprofitpayment.setForeground(Color.WHITE);
		btnRevenueprofitpayment.setBackground(Color.decode("#A0522D"));
		btnRevenueprofitpayment.setBounds(323, 418, 223, 46);
		btnRevenueprofitpayment.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Revenue_Profit generate = new Revenue_Profit();
				generate.setVisible(true);
			}
			
		});
		contentPane.add(btnRevenueprofitpayment);
		
		JButton btnDisplayRecords = new JButton("Display Records");
		btnDisplayRecords.setForeground(Color.WHITE);
		btnDisplayRecords.setBackground(Color.decode("#A0522D"));
		btnDisplayRecords.setBounds(70, 418, 223, 46);
		btnDisplayRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String[] choices = { "Medicines", "Vendors"};	
					String input = (String) JOptionPane.showInputDialog(null, "Which category of records do you wish to view?",
							"New Record", JOptionPane.QUESTION_MESSAGE, null, choices, // Array of choices
							choices[0]); // Initial choice
			    
					if(input.compareToIgnoreCase("Medicines")==0){
						ShowAll n = new ShowAll();
						n.setVisible(true);
					}
			    
					else if(input.compareToIgnoreCase("Vendors")==0){
						ShowAllVnd v = new ShowAllVnd();
						v.setVisible(true);
					}
				}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
			}
		});
		contentPane.add(btnDisplayRecords);
	}
	
	public void search_by_generic_name(String name){
		try{
			Connection connect = getConnection();
			
			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM medicines WHERE Generic_Name = '"+name+"'");
			
			result.beforeFirst();
			
			if (result.next()){
				Search_Result.code_number = result.getString("Code_Number");
				Search_Result.generic_name = name;
				Search_Result.trade_name = result.getString("Trade_Name");
				Search_Result.stock = result.getInt("Quantity");
				
				Search_Result show_result = new Search_Result();
				show_result.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null,"Sorry. No such record exists.","NOTE!", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e,"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void search_by_trade_name(String name){
		try{
			Connection connect = getConnection();
			
			Statement statement = connect.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM medicines WHERE Trade_Name = '"+name+"'");
			
			result.beforeFirst();
			
			if (result.next()){
				Search_Result.code_number = result.getString("Code_Number");
				Search_Result.trade_name = name;
				Search_Result.generic_name = result.getString("Generic_Name");
				Search_Result.stock = result.getInt("Quantity");
				
				Search_Result show_result = new Search_Result();
				show_result.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null,"Sorry. No such record exists.","NOTE!", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e,"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static Connection getConnection() throws Exception{
		try{
			String Driver = "com.mysql.jdbc.Driver";
			//String url = "jdbc:mysql://localhost/medicines";
			String url = "jdbc:mysql://127.0.0.1:3306/msa";
			String username = "root";
			//String password = "131260";
			String password = "curiosity";
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