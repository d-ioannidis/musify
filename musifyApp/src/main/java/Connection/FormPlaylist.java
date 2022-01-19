package Connection;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//import org.w3c.dom.events.MouseEvent;

public class FormPlaylist {

	protected static String YTlink = null;
	private JFrame frame;
	private JTable tablePlaylist;
	private Database database = new Database();

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPlaylist window = new FormPlaylist();
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
	public FormPlaylist() {
		initialize();
		frame.setLocationRelativeTo(null);
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
		img.setBounds(0, 0, 421, 523);
		panel_1_1.add(img);
		img.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\Images\\logoMain.png"));
		
		tablePlaylist = new JTable();
		tablePlaylist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point point = e.getPoint();
				int row = tablePlaylist.rowAtPoint(point);
				int col = tablePlaylist.columnAtPoint(point);
				String artist_nickname = tablePlaylist.getModel().getValueAt(row, 0).toString();
				String track = tablePlaylist.getModel().getValueAt(row, 1).toString();   
				String category = tablePlaylist.getModel().getValueAt(row, 2).toString();
				
				YTlink = database.PlayYTSong(track);
				database.SearchDataArtist(artist_nickname);
				
			}
		});
		tablePlaylist.setModel(database.selectPlaylist());
		//tablePlaylist.getColumnModel().getColumn(3).setPreferredWidth(50);
		tablePlaylist.getColumnModel().getColumn(2).setMaxWidth(50);
		tablePlaylist.setBounds(420, 59, 303, 464);
		panel_1_1.add(tablePlaylist);
		
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
		btnBack.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\buttons\\close.png"));
		btnBack.setBounds(21, 539, 63, 47);
		panel_1_1.add(btnBack);
		btnBack.setFont(new Font("Dubai", Font.PLAIN, 14));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				FormMusify.main(FormMusify.getArgs());
			
			}
		});
		btnBack.setBackground(Color.WHITE);
	JLabel Start = new JLabel("");
	Start.setIcon(new ImageIcon(FormPlaylist.class.getResource("/buttons/play.png")));
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
	Start.setBounds(364, 534, 46, 32);
	panel_1_1.add(Start);
	JButton btnDelPlaylist = new JButton("Delete Playlist");
	btnDelPlaylist.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			database.deletePlaylist1();
		}
	});
	btnDelPlaylist.setBounds(105, 543, 121, 23);
	panel_1_1.add(btnDelPlaylist);
	

	
}
}