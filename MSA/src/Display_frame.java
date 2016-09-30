import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Display_frame extends JFrame {

	private JPanel contentPane;
	private JTextField txtHeader;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display_frame frame = new Display_frame();
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
	public Display_frame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtHeader = new JTextField();
		txtHeader.setHorizontalAlignment(SwingConstants.CENTER);
		txtHeader.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtHeader.setEditable(false);
		txtHeader.setBounds(12, 12, 426, 40);
		contentPane.add(txtHeader);
		txtHeader.setColumns(10);
		
		JTextArea txtDisplay = new JTextArea();
		txtDisplay.setWrapStyleWord(true);
		txtDisplay.setLineWrap(true);
		txtDisplay.setEditable(false);
		txtDisplay.setBounds(49, 95, 352, 203);
		contentPane.add(txtDisplay);
	}
}
