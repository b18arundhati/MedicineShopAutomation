import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JTextArea;

public class CashReceipt extends JFrame {

	private JPanel contentPane;
	private JTextField txtDate;
	private JTextField txtAmount;
	private JTextArea txtItems;
	
	public static Date date;
	public static String name;
	public static int quantity;
	public static int unit_price;
	public static Date expiry;
	public static String batch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CashReceipt frame = new CashReceipt();
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
	public CashReceipt() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCuriosityMedicineShop = new JLabel("CURIOSITY MEDICINE SHOP");
		lblCuriosityMedicineShop.setFont(new Font("Century Schoolbook L", Font.BOLD, 18));
		lblCuriosityMedicineShop.setHorizontalAlignment(SwingConstants.CENTER);
		lblCuriosityMedicineShop.setBounds(96, 12, 354, 15);
		contentPane.add(lblCuriosityMedicineShop);
		
		JLabel lblCashMemo = new JLabel("CASH MEMO");
		lblCashMemo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCashMemo.setBounds(140, 55, 276, 15);
		contentPane.add(lblCashMemo);
		
		JLabel lblDate = new JLabel("DATE: ");
		lblDate.setBounds(189, 86, 70, 15);
		contentPane.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setBounds(265, 82, 114, 19);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
				
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		txtDate.setText(format.format(date));
		
		JLabel lblItems = new JLabel("ITEMS :");
		lblItems.setBounds(68, 130, 70, 15);
		contentPane.add(lblItems);
		
		JLabel lblTotalAmount = new JLabel("TOTAL AMOUNT");
		lblTotalAmount.setBounds(189, 360, 126, 15);
		contentPane.add(lblTotalAmount);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(336, 358, 114, 19);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		int total = quantity * unit_price;
		txtAmount.setText(String.valueOf(total));
		
		txtItems = new JTextArea();
		txtItems.setEditable(false);
		txtItems.setBounds(140, 150, 276, 184);
		txtItems.append("Name: " + name + "\n");
		txtItems.append("\n");
		txtItems.append(String.valueOf(quantity) + " units @ Rs. " + String.valueOf(unit_price) + "\n");
		txtItems.append("\n");
		txtItems.append("Batch Number: " + batch + "\n");
		txtItems.append("\n");
		txtItems.append("Expiry Date: " + format.format(expiry));
		contentPane.add(txtItems); 
	}
}
