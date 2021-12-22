package API;

import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Toolkit;

public class DiscogsForm {

	private JFrame frame;
	private JTextField txtArtist;
	private JTextField txtTrack;
	private URL url = null;
	private Image image = null;
	private JLabel lblArtistImage = new JLabel();
	private JLabel lblArtistName = new JLabel();
	private JTextArea txtProfile = new JTextArea();
	private DiscogsDBMetadata discogs = new DiscogsDBMetadata();
	private JTable tableAlbum;
	private JTextField txtGenre;
	private JTextField txtYear;
	private JTextField txtCountry;
	private JTextField txtPage;
	private JTextField txtPerpage;
	private JLabel lblAlbumTitleVal = new JLabel("");
	private JLabel lblAlbumImage = new JLabel("");
	private JLabel lblGenreVal = new JLabel("");
	private JLabel lblReleasedVal = new JLabel("");
	private JLabel lblCountryVal = new JLabel("");
	private JTable tableTracklist;
	private JTable tableArtists;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiscogsForm window = new DiscogsForm();
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
	public DiscogsForm() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { 
		
		// 1. Get the size of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    	    
		frame = new JFrame();		
		
		frame.setTitle("Musify App 1.0");        
        frame.setSize((screenSize.width*67)/100, (screenSize.height*64)/100);
		
		// 2. Calculates the position where the CenteredJFrame
        // should be paced on the screen.
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 962, 92);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblArtist = new JLabel("Artist:");
		lblArtist.setFont(new Font("Dialog", Font.BOLD, 16));
		lblArtist.setBounds(206, 54, 71, 28);
		panel.add(lblArtist);
		
		txtArtist = new JTextField();
		txtArtist.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtArtist.setBounds(271, 52, 225, 34);
		panel.add(txtArtist);
		txtArtist.setColumns(10);
		
		JLabel lblTrack = new JLabel("Track:");
		lblTrack.setFont(new Font("Dialog", Font.BOLD, 16));
		lblTrack.setBounds(206, 12, 71, 28);
		panel.add(lblTrack);
		
		txtTrack = new JTextField();
		txtTrack.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtTrack.setColumns(10);
		txtTrack.setBounds(271, 10, 225, 34);
		panel.add(txtTrack);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 24));
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Search Discogs Database
				try {
					discogs.advancedSearch(
							txtPage.getText().toLowerCase().trim(),		// page (current page)
							txtPerpage.getText().toLowerCase().trim(),	// per_page (results per page) 
							txtArtist.getText().toLowerCase().trim(),	// artist 
							txtTrack.getText().toLowerCase().trim(),	// track 
							txtYear.getText().toLowerCase().trim(),		// year 
							txtGenre.getText().toLowerCase().trim(),	// genre 
							txtCountry.getText().toLowerCase().trim()	// country
							);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				tableArtists.setModel(discogs.getArtistTableModel());
				
				displayArtist(0);				
				
				String artist_id = tableArtists.getModel().getValueAt(0,0).toString();
				tableAlbum.setModel(discogs.getAlbumTableModel(artist_id));
				
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				
				DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
				leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				tableAlbum.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);				
				tableAlbum.getColumnModel().getColumn(0).setMinWidth(350);
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				tableAlbum.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				tableAlbum.getColumnModel().getColumn(1).setMaxWidth(50);
				
				tableAlbum.setRowSelectionInterval(0, 0);
				displayAlbum(0);
				String albumID = tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),0).toString();
				displayTrackList(albumID);
					
			}
		});
		btnSearch.setBounds(805, 47, 132, 39);
		panel.add(btnSearch);
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setFont(new Font("Dialog", Font.BOLD, 16));
		lblGenre.setBounds(506, 12, 71, 28);
		panel.add(lblGenre);
		
		txtGenre = new JTextField();
		txtGenre.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtGenre.setColumns(10);
		txtGenre.setBounds(576, 10, 172, 34);
		panel.add(txtGenre);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Dialog", Font.BOLD, 16));
		lblYear.setBounds(758, 12, 71, 28);
		panel.add(lblYear);
		
		txtYear = new JTextField();
		txtYear.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtYear.setColumns(10);
		txtYear.setBounds(805, 11, 132, 34);
		panel.add(txtYear);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCountry.setBounds(506, 54, 86, 28);
		panel.add(lblCountry);
		
		txtCountry = new JTextField();
		txtCountry.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtCountry.setColumns(10);
		txtCountry.setBounds(576, 52, 174, 34);
		panel.add(txtCountry);
		
		JLabel lblPage = new JLabel("Page:");
		lblPage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPage.setBounds(12, 12, 71, 28);
		panel.add(lblPage);
		
		txtPage = new JTextField();
		txtPage.setHorizontalAlignment(SwingConstants.CENTER);
		txtPage.setText("1");
		txtPage.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtPage.setColumns(10);
		txtPage.setBounds(113, 10, 78, 34);
		panel.add(txtPage);
		
		JLabel lblPerpage = new JLabel("Per Page:");
		lblPerpage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPerpage.setBounds(12, 54, 104, 28);
		panel.add(lblPerpage);
		
		txtPerpage = new JTextField();
		txtPerpage.setHorizontalAlignment(SwingConstants.CENTER);
		txtPerpage.setText("5");
		txtPerpage.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtPerpage.setColumns(10);
		txtPerpage.setBounds(110, 52, 78, 34);
		panel.add(txtPerpage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(12, 116, 976, 423);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		lblArtistImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistImage.setBackground(new Color(255, 255, 255));
		
		
		lblArtistImage.setBounds(12, 12, 373, 289);
		panel_1.add(lblArtistImage);
		
		
		lblArtistName.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistName.setFont(new Font("Dialog", Font.BOLD, 20));
		lblArtistName.setBounds(12, 12, 373, 43);
		panel_1.add(lblArtistName);
		txtProfile.setWrapStyleWord(true);
		txtProfile.setFont(new Font("Dialog", Font.PLAIN, 16));
		txtProfile.setLineWrap(true);
		
		
		txtProfile.setBackground(new Color(255, 255, 255));
		txtProfile.setRows(30);
		txtProfile.setBounds(541, 75, 499, 310);
		//panel_1.add(txtProfile);
		
		JLabel lblNewLabel = new JLabel(" Albums");
		lblNewLabel.setBounds(0, 312, 1052, 30);
		panel_1.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		
		JScrollPane scrollPane_1 = new JScrollPane(txtProfile);
		scrollPane_1.setBounds(395, 11, 577, 297);
		panel_1.add(scrollPane_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 344, 976, 79);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		tableAlbum = new JTable();
		tableAlbum.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane(tableAlbum);
		scrollPane.setBounds(0, 0, panel_2.getWidth(), panel_2.getHeight());
		
		panel_2.add(scrollPane);
		
		tableAlbum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblAlbumTitleVal.setText(tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),3).toString());
				lblReleasedVal.setText(tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),4).toString());
				lblGenreVal.setText(tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),5).toString());
				lblCountryVal.setText(tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),6).toString());
				
				try {
					url = new URL(tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),2).toString());
					image = ImageIO.read(url);					
					lblAlbumImage.setIcon(new ImageIcon(image));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				String albumID = tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),0).toString();
				displayTrackList(albumID);
			}
		});
		lblAlbumImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblAlbumImage.setBounds(998, 12, 262, 144);
		frame.getContentPane().add(lblAlbumImage);
		lblAlbumTitleVal.setHorizontalAlignment(SwingConstants.CENTER);
				
		lblAlbumTitleVal.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAlbumTitleVal.setBounds(998, 172, 269, 34);
		frame.getContentPane().add(lblAlbumTitleVal);
		
		JLabel lblLabel = new JLabel("Genre:");
		lblLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLabel.setBounds(997, 217, 64, 34);
		frame.getContentPane().add(lblLabel);
		
		
		lblGenreVal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblGenreVal.setBounds(1077, 217, 159, 34);
		frame.getContentPane().add(lblGenreVal);
		
		JLabel lblReleased = new JLabel("Released:");
		lblReleased.setFont(new Font("Dialog", Font.BOLD, 14));
		lblReleased.setBounds(998, 252, 82, 34);
		frame.getContentPane().add(lblReleased);
		
		lblReleasedVal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblReleasedVal.setBounds(1077, 252, 159, 36);
		frame.getContentPane().add(lblReleasedVal);
		
		JLabel lblAlbumCountry = new JLabel("Country:");
		lblAlbumCountry.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAlbumCountry.setBounds(998, 285, 82, 26);
		frame.getContentPane().add(lblAlbumCountry);
		
		lblCountryVal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCountryVal.setBounds(1077, 284, 129, 27);
		frame.getContentPane().add(lblCountryVal);
		
		JLabel lblTrackList = new JLabel(" Tracklist");
		lblTrackList.setOpaque(true);
		lblTrackList.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrackList.setForeground(Color.WHITE);
		lblTrackList.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTrackList.setBackground(new Color(0, 128, 128));
		lblTrackList.setBounds(998, 330, 262, 30);
		frame.getContentPane().add(lblTrackList);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(998, 365, 269, 231);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		tableTracklist = new JTable();
		JScrollPane scrollPaneTracklist = new JScrollPane(tableTracklist);
		scrollPaneTracklist.setBounds(0, 0, panel_3.getWidth(), panel_3.getHeight());
		panel_3.add(scrollPaneTracklist);
		
		JLabel lblArtists = new JLabel(" Artists");
		lblArtists.setOpaque(true);
		lblArtists.setHorizontalAlignment(SwingConstants.LEFT);
		lblArtists.setForeground(Color.WHITE);
		lblArtists.setFont(new Font("Dialog", Font.BOLD, 18));
		lblArtists.setBackground(new Color(0, 128, 128));
		lblArtists.setBounds(12, 541, 976, 30);
		frame.getContentPane().add(lblArtists);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 573, 976, 66);
		frame.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		tableArtists = new JTable();
		tableArtists.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Synchronize Artists with Albums
				
				String artist_id = tableArtists.getModel().getValueAt(tableArtists.getSelectedRow(),0).toString();
				tableAlbum.setModel(discogs.getAlbumTableModel(artist_id));
				
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				
				DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
				leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
				tableAlbum.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);				
				tableAlbum.getColumnModel().getColumn(0).setMinWidth(350);
				
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				tableAlbum.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				tableAlbum.getColumnModel().getColumn(1).setMaxWidth(50);
				
				tableAlbum.setRowSelectionInterval(0, 0);
				displayAlbum(tableAlbum.getSelectedRow());
				String albumID = tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),0).toString();
				displayTrackList(albumID);
				displayArtist(tableArtists.getSelectedRow());
				
			}
		});
				
		JScrollPane scrollPaneArtists = new JScrollPane(tableArtists);
		scrollPaneArtists.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPaneArtists.setBounds(0, 0, panel_4.getWidth(), panel_4.getHeight());
		panel_4.add(scrollPaneArtists);
		
		JButton btnAddTo = new JButton("Add TrackList");
		btnAddTo.setFont(new Font("Dialog", Font.BOLD, 11));
		btnAddTo.setBounds(997, 607, 121, 34);
		frame.getContentPane().add(btnAddTo);
		
		JButton btnAddToFavorites = new JButton("Add to Favorites");
		btnAddToFavorites.setFont(new Font("Dialog", Font.BOLD, 11));
		btnAddToFavorites.setBounds(1128, 607, 138, 34);
		frame.getContentPane().add(btnAddToFavorites);
		
	}
	
	// Custom methods
	
	private void displayArtist(int selectedRow) {
		
		try {
			url = new URL(tableArtists.getModel().getValueAt(selectedRow,5).toString());
			image = ImageIO.read(url);
			lblArtistImage.setIcon(new ImageIcon(image));
			
			lblArtistName.setText(tableArtists.getModel().getValueAt(selectedRow,1).toString());
			txtProfile.setText(tableArtists.getModel().getValueAt(selectedRow,2).toString());
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void displayAlbum(int selectedRow) {
		lblAlbumTitleVal.setText(tableAlbum.getModel().getValueAt(selectedRow,3).toString());
		lblReleasedVal.setText(tableAlbum.getModel().getValueAt(selectedRow,4).toString());
		lblGenreVal.setText(tableAlbum.getModel().getValueAt(selectedRow,5).toString());
		lblCountryVal.setText(tableAlbum.getModel().getValueAt(selectedRow,6).toString());
		//lblLabelVal.setText(table.getModel().getValueAt(table.getSelectedRow(),7).toString());
		
		try {
			url = new URL(tableAlbum.getModel().getValueAt(selectedRow,2).toString());
			image = ImageIO.read(url);					
			lblAlbumImage.setIcon(new ImageIcon(image));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void displayTrackList(String albumID) {
		
		tableTracklist.setModel(discogs.getTrackListTableModel(albumID));
		
		tableTracklist.removeColumn(tableTracklist.getColumnModel().getColumn(0));
		
		DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
		centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);
		tableTracklist.getColumnModel().getColumn(0).setCellRenderer(centerRenderer2);				
		tableTracklist.getColumnModel().getColumn(0).setMinWidth(50);				
		
		DefaultTableCellRenderer leftRenderer2 = new DefaultTableCellRenderer();
		leftRenderer2.setHorizontalAlignment(SwingConstants.LEFT);
		tableTracklist.getColumnModel().getColumn(1).setCellRenderer(leftRenderer2);
		tableTracklist.getColumnModel().getColumn(1).setMinWidth(350);
	}
}
