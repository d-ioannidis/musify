package Connection;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class Rock {
	private JFrame frame;
	private JTable tableRock;
	private Database database = new Database();
	protected static String YTlink = null;

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Rock window = new Rock();
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
	public Rock() {
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
		
		tableRock = new JTable();
		tableRock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = e.getPoint();
				int row = tableRock.rowAtPoint(point);
				int col = tableRock.columnAtPoint(point);
				String artist_nickname = tableRock.getModel().getValueAt(row, 0).toString();
				String track = tableRock.getModel().getValueAt(row, 1).toString();   
				//String category = tableRock.getModel().getValueAt(row, 2).toString();
				
				YTlink = database.PlayYTSong(track);
				database.SearchDataArtist(artist_nickname);
			}
		});
		tableRock.setModel(database.SelectRock());
		//table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableRock.getColumnModel().getColumn(2).setMaxWidth(50);
		tableRock.setBounds(425, 69, 357, 473);
		panel.add(tableRock);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(494, 11, 248, 47);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rock Songs");
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
		
		
		JLabel PlayButton = new JLabel("");
		PlayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (YTlink != null) {
					Database.openWebpage(YTlink);
				}
				else {
					JOptionPane.showMessageDialog(null, "You didn't select a song or the song selected doesn't have a Youtube link.");
				}
			}
		});
		PlayButton.setIcon(new ImageIcon(Trap.class.getResource("/buttons/play.png")));
		PlayButton.setBounds(425, 577, 46, 41);
		panel.add(PlayButton);
		
		
		
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
	
