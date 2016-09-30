import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;

public class Search_Result extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodeNumber;
	private JTextField txtTradeName;
	private JTextField txtGenericName;
	private JTextField txtStock;
	
	public static String code_number;
	public static String trade_name;
	public static String generic_name;
	public static int stock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search_Result frame = new Search_Result();
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
	public Search_Result() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.decode("#FFEFD5"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearchResult = new JLabel("SEARCH RESULT");
		lblSearchResult.setFont(new Font("SansSerif", Font.BOLD, 17));
		lblSearchResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchResult.setBounds(83, 12, 288, 15);
		contentPane.add(lblSearchResult);
		
		JLabel lblCodeNumber = new JLabel("CODE NUMBER:");
		lblCodeNumber.setBounds(66, 62, 124, 15);
		contentPane.add(lblCodeNumber);
		
		txtCodeNumber = new JTextField();
		txtCodeNumber.setEditable(false);
		txtCodeNumber.setBounds(243, 60, 158, 19);
		txtCodeNumber.setText(code_number);
		contentPane.add(txtCodeNumber);
		txtCodeNumber.setColumns(10);
		
		JLabel lblTradeName = new JLabel("TRADE NAME:");
		lblTradeName.setBounds(66, 100, 116, 15);
		contentPane.add(lblTradeName);
		
		txtTradeName = new JTextField();
		txtTradeName.setEditable(false);
		txtTradeName.setBounds(243, 98, 158, 19);
		txtTradeName.setText(trade_name);
		contentPane.add(txtTradeName);
		txtTradeName.setColumns(10);
		
		JLabel lblGenericName = new JLabel("GENERIC NAME:");
		lblGenericName.setBounds(66, 137, 124, 15);
		contentPane.add(lblGenericName);
		
		txtGenericName = new JTextField();
		txtGenericName.setEditable(false);
		txtGenericName.setBounds(243, 135, 158, 19);
		txtGenericName.setText(generic_name);
		contentPane.add(txtGenericName);
		txtGenericName.setColumns(10);
		
		JLabel lblUnitsInCurrent = new JLabel("UNITS IN CURRENT STOCK :");
		lblUnitsInCurrent.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnitsInCurrent.setBounds(26, 180, 207, 15);
		contentPane.add(lblUnitsInCurrent);
		
		txtStock = new JTextField();
		txtStock.setEditable(false);
		txtStock.setBounds(243, 178, 158, 19);
		txtStock.setText(String.valueOf(stock));
		contentPane.add(txtStock);
		txtStock.setColumns(10);
	}
}
