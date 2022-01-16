package Connection;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Point;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FormFavourites {
	
	protected static String YTlink = null;
	private JFrame frame;
	private JTable tableFavourites;
	private Database database = new Database();

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void main(final String[] args) {
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
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 768, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 792, 635);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		tableFavourites = new JTable();
		tableFavourites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = e.getPoint();
				int row = tableFavourites.rowAtPoint(point);
				int col = tableFavourites.columnAtPoint(point);
				String artist_nickname = tableFavourites.getModel().getValueAt(row, 0).toString();
				String track = tableFavourites.getModel().getValueAt(row, 1).toString();   
				String category = tableFavourites.getModel().getValueAt(row, 2).toString();
				
				YTlink = database.PlayYTSong(track);
				database.SearchDataArtist(artist_nickname);
			}
		});
		tableFavourites.setModel(database.selectFavourites());
		//table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		tableFavourites.getColumnModel().getColumn(2).setMaxWidth(50);
		tableFavourites.setBounds(425, 69, 317, 444);
		panel.add(tableFavourites);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(494, 11, 248, 47);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your Favourite Songs List");
		lblNewLabel.setBounds(10, 0, 234, 47);
		panel_2.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dubai", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLACK);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\Images\\logoMain.png"));
		lblNewLabel_1.setBounds(0, 0, 426, 517);
		panel.add(lblNewLabel_1);
		
		
		//button back
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\buttons\\back.png"));
		btnBack.setBounds(10, 540, 63, 47);
		panel.add(btnBack);
		btnBack.setFont(new Font("Dubai", Font.PLAIN, 14));
		
		JLabel Start = new JLabel("");
		Start.setIcon(new ImageIcon(FormFavourites.class.getResource("/buttons/play.png")));
		Start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				//Database.play();
				
				if (YTlink != null) {
					Database.openWebpage(YTlink);
				}
				else {
					JOptionPane.showMessageDialog(null, "You didn't select a song or the song selected doesn't have a Youtube link.");
				}
			}
		});
		Start.setBounds(399, 540, 37, 37);
		panel.add(Start);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				FormMusify.main(FormMusify.getArgs());
			}
		});
	}
}