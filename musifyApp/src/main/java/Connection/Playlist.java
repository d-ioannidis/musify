package Connection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Playlist {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Playlist window = new Playlist();
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
	public Playlist() {
		initialize();
	}
	

	
	


	
	
	
	

	 // Initialize the contents of the frame.
	 
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 749, 636);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(Color.DARK_GRAY);
		panel_1_1.setBounds(0, 0, 733, 597);
		frame.getContentPane().add(panel_1_1);
		
		JLabel img = new JLabel("");
		img.setHorizontalAlignment(SwingConstants.CENTER);
		img.setBounds(0, 0, 410, 523);
		panel_1_1.add(img);
		img.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musifyApp\\src\\main\\java\\Images\\logoMain.png"));
		
		table = new JTable();
		table.setBounds(420, 59, 303, 464);
		panel_1_1.add(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(420, 11, 262, 37);
		panel_1_1.add(panel);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Your Playlist");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBackground(Color.MAGENTA);
		lblNewLabel_1.setFont(new Font("Dubai", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 0, 242, 37);
		lblNewLabel_1.setToolTipText("");
		panel.add(lblNewLabel_1);
		
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musifyApp\\src\\main\\java\\buttons\\back.png"));
		btnBack.setBounds(21, 539, 63, 47);
		panel_1_1.add(btnBack);
		btnBack.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				FormMusify.main(null);
			
			}
		});
		btnBack.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(275, 59, 150, 464);
		panel_1_1.add(panel_1);
	}

}
