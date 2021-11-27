package Connection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;


public class FormFavourites {

	private JFrame frame;
	private JTable tableFavourites;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormFavourites window = new FormFavourites();
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
	public FormFavourites() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 698, 536);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\Images\\musifyLogoNew.png"));
		lblLogo.setBounds(124, 11, 439, 50);
		frame.getContentPane().add(lblLogo);
		
		
		//button back
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Dubai", Font.PLAIN, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//FormMusify.main(null);
			}
		});
		btnBack.setBounds(27, 453, 89, 33);
		frame.getContentPane().add(btnBack);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 109, 662, 336);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		tableFavourites = new JTable();
		tableFavourites.setBounds(10, 11, 642, 314);
		panel.add(tableFavourites);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(244, 60, 173, 38);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Favourite Songs List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dubai", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setBounds(10, 11, 153, 14);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 11, 152, 16);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		//button Close
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Dubai", Font.PLAIN, 11));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setBounds(583, 453, 89, 33);
		frame.getContentPane().add(btnClose);
	}
}