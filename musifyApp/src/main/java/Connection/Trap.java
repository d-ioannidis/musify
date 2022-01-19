package Connection;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Trap {
	private JFrame frame;
	private JTable tableTrap;
	private Database database = new Database();

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Trap window = new Trap();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Trap() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 808, 674);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 792, 635);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		tableTrap = new JTable();
		tableTrap.setModel(database.SelectTrap());
		//table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableTrap.getColumnModel().getColumn(2).setMaxWidth(50);
		tableTrap.setBounds(425, 69, 357, 473);
		panel.add(tableTrap);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(494, 11, 248, 47);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trap Songs");
		lblNewLabel.setBounds(10, 0, 234, 47);
		panel_2.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dubai", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\Images\\logoMain.png"));
		lblNewLabel_1.setBounds(0, 0, 447, 572);
		panel.add(lblNewLabel_1);
		
		
		//button back
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\buttons\\back.png"));
		btnBack.setBounds(20, 577, 63, 47);
		panel.add(btnBack);
		btnBack.setFont(new Font("Dubai", Font.PLAIN, 14));
		
		//button Close
		JButton btnClose = new JButton("");
		btnClose.setBounds(719, 577, 63, 47);
		panel.add(btnClose);
		btnClose.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\buttons\\close.png"));
		btnClose.setFont(new Font("Dubai", Font.PLAIN, 11));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Categories2.main(FormMusify.getArgs());
			}
		});
		frame.setLocationRelativeTo(null);
	}
}
