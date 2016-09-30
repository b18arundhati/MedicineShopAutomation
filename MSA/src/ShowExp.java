import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;
import java.util.Vector;

public class ShowExp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowExp frame = new ShowExp();
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
	public ShowExp() throws Exception{
		Date curr_date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(curr_date);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1000, 500);
		
		Connection con = getConnection();
	    Statement stmt = con.createStatement();
	    ResultSet r = stmt.executeQuery("select medicines.* from medicines, stock where stock.Expiry_Date < '"+s+"' AND stock.Code_Number = medicines.Code_Number");

	    PreparedStatement new_s = con.prepareStatement("UPDATE medicines SET medicines.Quantity = medicines.Quantity - (SELECT Quantity FROM stock WHERE medicines.Code_Number = stock.Code_Number) WHERE  EXISTS (SELECT quantity FROM stock WHERE stock.Expiry_Date < '"+s+"' AND stock.Code_Number = medicines.Code_Number);");
		new_s.executeUpdate();
	    //new_s = con.prepareStatement("UPDATE stock SET Quantity = 0 WHERE stock.Expiry_Date < '"+s+"'");
	    //new_s.executeUpdate();
		
	    JTable table = new JTable(buildTableModel(r));
   
	    JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 1000, 400);
        getContentPane().add(scrollPane);
	}

	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
	public static Connection getConnection() throws Exception{
		try{
			String Driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://127.0.0.1:3306/msa";
			//String url = "jdbc:mysql://localhost/medicines";
			String username = "root";
			String password = "curiosity";
			//String password = "131260";
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
