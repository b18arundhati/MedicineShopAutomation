import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class Revenue_Profit extends JFrame {

	private JPanel contentPane;
	private JDatePickerImpl startdatePicker;
	private JDatePickerImpl enddatePicker;
	private JRadioButton rdbtnRevenue;
	private JRadioButton rdbtnProfit;
	private JRadioButton rdbtnVendor;
	private JButton btnGenerate;
	
	
	private JTextField txtHeader;
	private JFrame frame;
	private JTextArea txtDisplay;
	private JScrollPane addscrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Revenue_Profit frame = new Revenue_Profit();
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
	public Revenue_Profit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UtilDateModel startmodel = new UtilDateModel();
		UtilDateModel endmodel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl startdatePanel = new JDatePanelImpl(startmodel, p);
        startdatePicker = new JDatePickerImpl(startdatePanel, new DateLabelFormatter());
        JDatePanelImpl enddatePanel = new JDatePanelImpl(endmodel, p);
        enddatePicker = new JDatePickerImpl(enddatePanel, new DateLabelFormatter());
        startdatePicker.setBounds(50,85,148,25);
        enddatePicker.setBounds(240,85,148,25);
        
        contentPane.add(startdatePicker);
        contentPane.add(enddatePicker);
        
        JLabel lblStartDate = new JLabel("START DATE");
        lblStartDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblStartDate.setBounds(50, 52, 148, 15);
        contentPane.add(lblStartDate);
        
        JLabel lblEndDate = new JLabel("END DATE");
        lblEndDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblEndDate.setBounds(240, 52, 140, 15);
        contentPane.add(lblEndDate);
        
        rdbtnRevenue = new JRadioButton("REVENUE FOR THE PERIOD");
        rdbtnRevenue.setBounds(50, 160, 338, 23);
        contentPane.add(rdbtnRevenue);
        
        rdbtnProfit = new JRadioButton("PROFIT FOR THE PERIOD");
        rdbtnProfit.setBounds(50, 187, 338, 23);
        contentPane.add(rdbtnProfit);
        
        rdbtnVendor = new JRadioButton("VENDOR-WISE PAYMENTS FOR THE PERIOD");
        rdbtnVendor.setBounds(50, 214, 338, 23);
        contentPane.add(rdbtnVendor);
        
        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnRevenue);
        group.add(rdbtnProfit);
        group.add(rdbtnVendor);
        
        btnGenerate = new JButton("GENERATE");
        btnGenerate.setBounds(165, 265, 117, 25);
        btnGenerate.addActionListener(generate);
        contentPane.add(btnGenerate);
		
	}
	
	ActionListener generate = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try{
				Date startdate = (Date) startdatePicker.getModel().getValue();
				Date enddate = (Date) enddatePicker.getModel().getValue();
				
				if (enddate.before(startdate)){
					throw new Exception("Please choose the dates properly.");
				}
				else if (enddate.after(startdate) || enddate.equals(startdate)){
					if (rdbtnRevenue.isSelected()){
						generate_revenue();
					}
					else if (rdbtnProfit.isSelected()){
						generate_profit();
					}
					else if (rdbtnVendor.isSelected()){
						//generate vendor-wise payments
						generate_vendorwise_payments();
						
					}
				}
				
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e,"ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	};
	
	public void generate_vendorwise_payments(){
		try{
			Connection connect = getConnection();
			
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date start_date = (Date) startdatePicker.getModel().getValue();
			java.sql.Date sql_startdate = new java.sql.Date(start_date.getTime());
			Date end_date = (Date) enddatePicker.getModel().getValue();
			java.sql.Date sql_enddate = new java.sql.Date(end_date.getTime());
			
			Display_frame();
			txtHeader.setText("Vendor-wise payments :");
			txtDisplay.append("\tfrom " + format.format(start_date) + " to " + format.format(end_date) + "\n");
			
			Statement query = connect.createStatement();
			
			boolean writeflag = false;
			
			ResultSet vendor_result = query.executeQuery("SELECT * FROM vendor ORDER BY Vendor_ID");
			while (vendor_result.next()){
				String ID = vendor_result.getString("Vendor_ID");
				PreparedStatement statement = connect.prepareStatement("SELECT * FROM stock WHERE (stock.Vendor_ID = '"+ID+"' AND (stock.Stock_Arrival_Date >= ? AND stock.Stock_Arrival_Date <= ?))");
				statement.setDate(1, sql_startdate);
				statement.setDate(2, sql_enddate);
				ResultSet result = statement.executeQuery();
				int payment = 0;
				while (result.next()){
					payment += result.getInt("Quantity") * result.getInt("Per_Unit_Purchasing_Price");
				}
				if (payment > 0){
					writeflag = true;
					txtDisplay.append("ID: " + vendor_result.getString("Vendor_ID") + " : " + vendor_result.getString("Name") + " : paid Rs. " + payment + "\n");
					//System.out.println(vendor_result.getString("Vendor_ID") + " : " + vendor_result.getString("Name") + " : paid Rs. " + payment);
				}
				
			}
			if (!writeflag){
				txtDisplay.append("No payment records available.");
			}
			
			frame.setVisible(true);
			//System.out.println("done");
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void generate_revenue(){
		try{
			Connection connect = getConnection();
			
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date start_date = (Date) startdatePicker.getModel().getValue();
			java.sql.Date sql_startdate = new java.sql.Date(start_date.getTime());
			Date end_date = (Date) enddatePicker.getModel().getValue();
			java.sql.Date sql_enddate = new java.sql.Date(end_date.getTime());
			
			Display_frame();
			txtHeader.setText("SHOP'S REVENUE :");
			txtDisplay.append("\tfrom " + format.format(start_date) + " to " + format.format(end_date) + "\n");
			
			PreparedStatement newquery = connect.prepareStatement("SELECT * FROM sales WHERE (sales.Date_of_Sale >= ? AND sales.Date_of_Sale <= ?)");
			newquery.setDate(1, sql_startdate);
			newquery.setDate(2, sql_enddate);
			ResultSet newresult = newquery.executeQuery();
			
			int earned = 0;
			while (newresult.next()){
				earned += newresult.getInt("Quantity_Sold") * newresult.getInt("Per_Unit_Selling_Price");
			}
			
			txtDisplay.append("\n\nTotal revenue generated : Rs. " + earned);
			
			frame.setVisible(true);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public void generate_profit(){
		try{
			Connection connect = getConnection();
			
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date start_date = (Date) startdatePicker.getModel().getValue();
			java.sql.Date sql_startdate = new java.sql.Date(start_date.getTime());
			Date end_date = (Date) enddatePicker.getModel().getValue();
			java.sql.Date sql_enddate = new java.sql.Date(end_date.getTime());
			
			Display_frame();
			txtHeader.setText("SHOP'S PROFIT :");
			txtDisplay.append("\tfrom " + format.format(start_date) + " to " + format.format(end_date) + "\n");
			
			PreparedStatement query = connect.prepareStatement("SELECT * FROM stock WHERE (stock.Stock_Arrival_Date >= ? AND stock.Stock_Arrival_Date <= ?)");
			query.setDate(1, sql_startdate);
			query.setDate(2, sql_enddate);
			ResultSet result = query.executeQuery();
			
			int spent = 0;
			while (result.next()){
				spent += result.getInt("Quantity") * result.getInt("Per_Unit_Purchasing_Price");
			}
			
			PreparedStatement newquery = connect.prepareStatement("SELECT * FROM sales WHERE (sales.Date_of_Sale >= ? AND sales.Date_of_Sale <= ?)");
			newquery.setDate(1, sql_startdate);
			newquery.setDate(2, sql_enddate);
			ResultSet newresult = newquery.executeQuery();
			
			int earned = 0;
			while (newresult.next()){
				earned += newresult.getInt("Quantity_Sold") * newresult.getInt("Per_Unit_Selling_Price");
			}
			
			int profit = earned - spent;
			
			txtDisplay.append("\n\nTotal profit : Rs. " + profit);
			frame.setVisible(true);
			
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e, "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public class DateLabelFormatter extends AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
	}
	
	public void Display_frame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 364);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtHeader = new JTextField();
		txtHeader.setHorizontalAlignment(SwingConstants.CENTER);
		txtHeader.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtHeader.setEditable(false);
		txtHeader.setBounds(12, 12, 426, 40);
		contentPane.add(txtHeader);
		txtHeader.setColumns(10);
		
		txtDisplay = new JTextArea();
		txtDisplay.setWrapStyleWord(true);
		txtDisplay.setLineWrap(true);
		txtDisplay.setEditable(false);
		txtDisplay.setBounds(49, 95, 352, 203);
		contentPane.add(txtDisplay);
		
		
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
			JOptionPane.showMessageDialog(null, e);
		}
		return null;
	}
}
