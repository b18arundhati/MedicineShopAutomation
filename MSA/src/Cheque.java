import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cheque extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	private JTextField txtAmount;
	private JTextField txtVendorname;
	private JTextField txtSignature;
	private JLabel lblStateBankOf;
	
	public static int price;
	public static String vendorname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cheque frame = new Cheque();
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
	public Cheque() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 275);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblChequeHeader = new JLabel("Cheque number: ________________");
		lblChequeHeader.setBounds(12, 35, 227, 39);
		contentPane.add(lblChequeHeader);
		
		JLabel lblDate = new JLabel("Date: ");
		lblDate.setBounds(268, 35, 43, 27);
		contentPane.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setEditable(false);
		txtDate.setBounds(312, 39, 114, 19);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		txtDate.setText(dateFormat.format(date));
		
		JLabel lblPayment = new JLabel("To pay a sum of Rs. ");
		lblPayment.setBounds(45, 87, 154, 39);
		contentPane.add(lblPayment);
		
		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		txtAmount.setBounds(204, 97, 114, 19);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		txtAmount.setText(String.valueOf(price));
		
		JLabel lblTo = new JLabel("to");
		lblTo.setBounds(335, 99, 70, 15);
		contentPane.add(lblTo);
		
		txtVendorname = new JTextField();
		txtVendorname.setEditable(false);
		txtVendorname.setHorizontalAlignment(SwingConstants.CENTER);
		txtVendorname.setBounds(45, 138, 360, 19);
		contentPane.add(txtVendorname);
		txtVendorname.setColumns(10);
		txtVendorname.setText(vendorname);
		
		txtSignature = new JTextField();
		txtSignature.setBounds(291, 185, 114, 19);
		contentPane.add(txtSignature);
		txtSignature.setColumns(10);
		txtSignature.setEditable(false);
		
		JLabel lblSignature = new JLabel("Signature");
		lblSignature.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignature.setBounds(301, 216, 104, 19);
		contentPane.add(lblSignature);
		
		lblStateBankOf = new JLabel("State Bank Of India");
		lblStateBankOf.setFont(new Font("Serif", Font.BOLD, 16));
		lblStateBankOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblStateBankOf.setBounds(128, 0, 211, 27);
		contentPane.add(lblStateBankOf);
	}
}
