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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Connection.Database;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class DiscogsForm extends JFrame {
	
	private DiscogsAPIv2 discogs_api = new DiscogsAPIv2();

	private JFrame frame;
	private JTextField txtArtist;
	private JTextField txtTrack;
	private URL url = null;
	private Image image = null;
	private JLabel lblArtistImage = new JLabel();
	private JLabel lblArtistName = new JLabel();
	private JTextArea txtProfile = new JTextArea();
	private JTextArea txtAlbumTitleVal = new JTextArea();
	private JTable tableAlbum;
	private JTextField txtGenre;
	private JTextField txtYear;
	private JTextField txtCountry;
	private JTextField txtPage;
	private JTextField txtPerpage;
	private JLabel lblAlbumImage = new JLabel("");
	private JLabel lblGenreVal = new JLabel("");
	private JLabel lblReleasedVal = new JLabel("");
	private JLabel lblCountryVal = new JLabel("");
	private JTable tableTracklist;
	private JTable tableArtists;
	private JButton btnPlay = new JButton("Play");
	
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
        //frame.setSize((screenSize.width*67)/100, (screenSize.height*64)/100);
        frame.setSize(1320,700);
		
		// 2. Calculates the position where the CenteredJFrame
        // should be paced on the screen.
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        //frame.setLocation(x, y);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 962, 92);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblArtist = new JLabel("Artist:");
		lblArtist.setFont(new Font("Dialog", Font.BOLD, 14));
		lblArtist.setBounds(167, 54, 71, 28);
		panel.add(lblArtist);
		
		txtArtist = new JTextField();
		txtArtist.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtArtist.setBounds(239, 52, 228, 34);
		panel.add(txtArtist);
		txtArtist.setColumns(10);
		
		JLabel lblTrack = new JLabel("Track:");
		lblTrack.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTrack.setBounds(167, 12, 71, 28);
		panel.add(lblTrack);
		
		txtTrack = new JTextField();
		txtTrack.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtTrack.setColumns(10);
		txtTrack.setBounds(239, 10, 228, 34);
		panel.add(txtTrack);
		
		final JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Dialog", Font.BOLD, 14));
		btnSearch.addMouseListener(new MouseAdapter() {			
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String page = txtPage.getText().toLowerCase().trim();
				String per_page = txtPerpage.getText().toLowerCase().trim();
				String track = txtTrack.getText().toLowerCase().trim();
				String artist = txtArtist.getText().toLowerCase().trim();
				String genre = txtGenre.getText().toLowerCase().trim();
				String country = txtCountry.getText().toLowerCase().trim();
				String year = txtYear.getText().toLowerCase().trim();
				
				
				
				if ( 
						!track.equals("") || !artist.equals("") ||  
						!genre.equals("") || !country.equals("") || !year.equals("")
						) {				
				
					// Search Discogs Database
					discogs_api.clearResults();
					
					try {
						discogs_api.search(
								page,		// Page 
								per_page,	// Results Per Page 
								track,		// Track
								artist,		// Artist
								genre,		// Genre
								country,	// Country
								year		// Year
								);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
						
						
						/*
						discogs_api.getArtistInfo(artist);
						
						for (Map.Entry<String, DiscogsArtist> entry : discogs_api.getArtists().entrySet()) {		        	 
				        	 discogs_api.getArtistReleases( entry.getValue().id );
						}
						*/
						
						/*
						String searchOptions = discogs_api.setSearchOptions(
								page,		// Page 
								per_page,	// Results Per Page 
								query,		// Query String
								artist,		// Artist
								genre,		// Genre
								country,	// Country
								year		// Year
								);
						discogs_api.search(searchOptions);
						*/
						
					
					
					tableArtists.setModel(discogs_api.getArtistsTableModel());
					tableArtists.removeColumn(tableArtists.getColumnModel().getColumn(0));
					tableArtists.removeColumn(tableArtists.getColumnModel().getColumn(2));
					tableArtists.removeColumn(tableArtists.getColumnModel().getColumn(2));
					tableArtists.removeColumn(tableArtists.getColumnModel().getColumn(2));
					tableArtists.setRowSelectionInterval(0, 0);
					
					
					String artist_id = tableArtists.getModel().getValueAt(tableArtists.getSelectedRow(),0).toString();
					
					tableAlbum.setModel(discogs_api.getAlbumsTableModel(artist_id));
					
					tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
					tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
					tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
					tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
					tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
					tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
					
					
					DefaultTableCellRenderer leftRendererTitle = new DefaultTableCellRenderer();
					leftRendererTitle.setHorizontalAlignment(SwingConstants.LEFT);
					tableAlbum.getColumnModel().getColumn(0).setCellRenderer(leftRendererTitle);				
					tableAlbum.getColumnModel().getColumn(0).setMinWidth(500);
					
					DefaultTableCellRenderer leftRendererGenre = new DefaultTableCellRenderer();
					leftRendererGenre.setHorizontalAlignment(SwingConstants.LEFT);
					tableAlbum.getColumnModel().getColumn(1).setCellRenderer(leftRendererGenre);				
					tableAlbum.getColumnModel().getColumn(1).setMinWidth(150);
					
					
					DefaultTableCellRenderer centerRendererYear = new DefaultTableCellRenderer();
					centerRendererYear.setHorizontalAlignment(SwingConstants.CENTER);
					tableAlbum.getColumnModel().getColumn(2).setCellRenderer(centerRendererYear);
					tableAlbum.getColumnModel().getColumn(2).setMinWidth(40);
					
					if( tableAlbum.getModel().getRowCount() > 0 ) {
						
						tableAlbum.setRowSelectionInterval(0, 0);		
					
						displayArtist();
						displayAlbum();
						displayTrackList();
					}
					
					
					
				}		
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnSearch.setText("Searching...");
				btnSearch.setEnabled (false);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnSearch.setText("Search");
				btnSearch.setEnabled (true);
			}
		});
		btnSearch.setBounds(805, 47, 132, 39);
		panel.add(btnSearch);
		
		
		
		JLabel lblGenre = new JLabel("Genre:");
		lblGenre.setFont(new Font("Dialog", Font.BOLD, 14));
		lblGenre.setBounds(485, 12, 81, 28);
		panel.add(lblGenre);
		
		txtGenre = new JTextField();
		txtGenre.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtGenre.setColumns(10);
		txtGenre.setBounds(576, 10, 172, 34);
		panel.add(txtGenre);
		
		JLabel lblYear = new JLabel("Year:");
		lblYear.setFont(new Font("Dialog", Font.BOLD, 14));
		lblYear.setBounds(758, 12, 48, 28);
		panel.add(lblYear);
		
		txtYear = new JTextField();
		txtYear.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtYear.setColumns(10);
		txtYear.setBounds(805, 11, 132, 34);
		panel.add(txtYear);
		
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCountry.setBounds(485, 54, 81, 28);
		panel.add(lblCountry);
		
		txtCountry = new JTextField();
		txtCountry.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCountry.setColumns(10);
		txtCountry.setBounds(576, 52, 174, 34);
		panel.add(txtCountry);
		
		JLabel lblPage = new JLabel("Page:");
		lblPage.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPage.setBounds(12, 12, 71, 28);
		panel.add(lblPage);
		
		txtPage = new JTextField();
		txtPage.setHorizontalAlignment(SwingConstants.CENTER);
		txtPage.setText("1");
		txtPage.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPage.setColumns(10);
		txtPage.setBounds(101, 10, 48, 34);
		panel.add(txtPage);
		
		JLabel lblPerpage = new JLabel("Per Page:");
		lblPerpage.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPerpage.setBounds(12, 54, 104, 28);
		panel.add(lblPerpage);
		
		txtPerpage = new JTextField();
		txtPerpage.setHorizontalAlignment(SwingConstants.CENTER);
		txtPerpage.setText("6");
		txtPerpage.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPerpage.setColumns(10);
		txtPerpage.setBounds(101, 52, 48, 34);
		panel.add(txtPerpage);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setBounds(12, 116, 976, 536);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		lblArtistImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistImage.setBackground(new Color(255, 255, 255));
		
		
		lblArtistImage.setBounds(12, 59, 373, 183);
		mainPanel.add(lblArtistImage);
		
		
		lblArtistName.setHorizontalAlignment(SwingConstants.CENTER);
		lblArtistName.setFont(new Font("Dialog", Font.BOLD, 20));
		lblArtistName.setBounds(12, 12, 373, 43);
		mainPanel.add(lblArtistName);
		txtProfile.setLineWrap(true);
		txtProfile.setEditable(false);
		txtProfile.setWrapStyleWord(true);
		txtProfile.setFont(new Font("Dialog", Font.PLAIN, 16));		
		
		txtProfile.setBackground(new Color(255, 255, 255));
		txtProfile.setRows(10);
		txtProfile.setBounds(541, 75, 499, 310);
		
		JLabel lblAlbumLabel = new JLabel(" Albums");
		lblAlbumLabel.setBounds(0, 381, 976, 32);
		mainPanel.add(lblAlbumLabel);
		lblAlbumLabel.setForeground(Color.WHITE);
		lblAlbumLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblAlbumLabel.setOpaque(true);
		lblAlbumLabel.setBackground(new Color(0, 128, 128));
		lblAlbumLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		
		JScrollPane scrollPaneProfile = new JScrollPane(txtProfile);
		scrollPaneProfile.setBounds(395, 11, 577, 242);
		mainPanel.add(scrollPaneProfile);
		
		JPanel panelAlbums = new JPanel();
		panelAlbums.setBounds(0, 414, 976, 122);
		mainPanel.add(panelAlbums);
		panelAlbums.setLayout(null);
		
		tableAlbum = new JTable();
		tableAlbum.setFont(new Font("Dialog", Font.PLAIN, 14));
		
		JScrollPane scrollPaneAlbums = new JScrollPane(tableAlbum);
		scrollPaneAlbums.setBounds(0, 0, 976, 122);
		
		panelAlbums.add(scrollPaneAlbums);
		
		JLabel lblARtistLabel = new JLabel(" Artists");
		lblARtistLabel.setOpaque(true);
		lblARtistLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblARtistLabel.setForeground(Color.WHITE);
		lblARtistLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblARtistLabel.setBackground(new Color(0, 128, 128));
		lblARtistLabel.setBounds(0, 254, 976, 32);
		mainPanel.add(lblARtistLabel);
		
		JPanel panelArtists = new JPanel();
		panelArtists.setBounds(0, 284, 976, 96);
		mainPanel.add(panelArtists);
		panelArtists.setLayout(null);
		
		tableArtists = new JTable();
		tableArtists.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String artist_id = tableArtists.getModel().getValueAt(
						tableArtists.getSelectedRow(),0).toString();
				//System.out.println(artist_id);
				
				DefaultTableModel model = (DefaultTableModel) tableAlbum.getModel();
				model.setRowCount(0);
				model.setColumnCount(0);
				
				tableAlbum.setModel(discogs_api.getAlbumsTableModel(artist_id));
				
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				
				
				DefaultTableCellRenderer leftRendererTitle = new DefaultTableCellRenderer();
				leftRendererTitle.setHorizontalAlignment(SwingConstants.LEFT);
				tableAlbum.getColumnModel().getColumn(0).setCellRenderer(leftRendererTitle);				
				tableAlbum.getColumnModel().getColumn(0).setMinWidth(500);
				
				DefaultTableCellRenderer leftRendererGenre = new DefaultTableCellRenderer();
				leftRendererGenre.setHorizontalAlignment(SwingConstants.LEFT);
				tableAlbum.getColumnModel().getColumn(1).setCellRenderer(leftRendererGenre);				
				tableAlbum.getColumnModel().getColumn(1).setMinWidth(150);				
				
				DefaultTableCellRenderer centerRendererYear = new DefaultTableCellRenderer();
				centerRendererYear.setHorizontalAlignment(SwingConstants.CENTER);
				tableAlbum.getColumnModel().getColumn(2).setCellRenderer(centerRendererYear);
				tableAlbum.getColumnModel().getColumn(2).setMinWidth(40);
				
				//System.out.println("RowCount: " + tableAlbum.getModel().getRowCount());
				
				if( tableAlbum.getModel().getRowCount() > 0 ) {
					
					tableAlbum.setRowSelectionInterval(0, 0);		
				
					displayArtist();
					displayAlbum();
					displayTrackList();
				}
				
				/*
				tableAlbum.setModel(discogs_api.getAlbumsTableModel(artist_id));
				
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				tableAlbum.removeColumn(tableAlbum.getColumnModel().getColumn(0));
				
				
				DefaultTableCellRenderer leftRendererTitle = new DefaultTableCellRenderer();
				leftRendererTitle.setHorizontalAlignment(SwingConstants.LEFT);
				tableAlbum.getColumnModel().getColumn(0).setCellRenderer(leftRendererTitle);				
				tableAlbum.getColumnModel().getColumn(0).setMinWidth(500);
				
				DefaultTableCellRenderer leftRendererGenre = new DefaultTableCellRenderer();
				leftRendererGenre.setHorizontalAlignment(SwingConstants.LEFT);
				tableAlbum.getColumnModel().getColumn(1).setCellRenderer(leftRendererGenre);				
				tableAlbum.getColumnModel().getColumn(1).setMinWidth(150);
				
				
				DefaultTableCellRenderer centerRendererYear = new DefaultTableCellRenderer();
				centerRendererYear.setHorizontalAlignment(SwingConstants.CENTER);
				tableAlbum.getColumnModel().getColumn(2).setCellRenderer(centerRendererYear);
				tableAlbum.getColumnModel().getColumn(2).setMinWidth(40);
				displayAlbum();
				displayTrackList();
				*/
			}
		});
		JScrollPane scrollPaneArtists = new JScrollPane(tableArtists);
		scrollPaneArtists.setBounds(0, 5, 976, 91);
		panelArtists.add(scrollPaneArtists);	
		
				
		tableAlbum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				
				
				displayAlbum();
				displayTrackList();			
				
			}
		});
		lblAlbumImage.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblAlbumImage.setBounds(998, 12, 269, 148);
		frame.getContentPane().add(lblAlbumImage);
		
		JLabel lblLabel = new JLabel("Genre:");
		lblLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLabel.setBounds(998, 235, 64, 26);
		frame.getContentPane().add(lblLabel);
		
		
		lblGenreVal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblGenreVal.setBounds(1100, 235, 149, 26);
		frame.getContentPane().add(lblGenreVal);
		
		JLabel lblReleased = new JLabel("Released:");
		lblReleased.setFont(new Font("Dialog", Font.BOLD, 14));
		lblReleased.setBounds(998, 260, 82, 26);
		frame.getContentPane().add(lblReleased);
		
		lblReleasedVal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblReleasedVal.setBounds(1100, 260, 149, 26);
		frame.getContentPane().add(lblReleasedVal);
		
		JLabel lblAlbumCountry = new JLabel("Country:");
		lblAlbumCountry.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAlbumCountry.setBounds(998, 285, 82, 26);
		frame.getContentPane().add(lblAlbumCountry);
		
		lblCountryVal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCountryVal.setBounds(1100, 290, 129, 21);
		frame.getContentPane().add(lblCountryVal);
		
		JLabel lblTrackList = new JLabel(" Tracklist");
		lblTrackList.setOpaque(true);
		lblTrackList.setHorizontalAlignment(SwingConstants.LEFT);
		lblTrackList.setForeground(Color.WHITE);
		lblTrackList.setFont(new Font("Dialog", Font.BOLD, 18));
		lblTrackList.setBackground(new Color(0, 128, 128));
		lblTrackList.setBounds(998, 323, 269, 40);
		frame.getContentPane().add(lblTrackList);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(998, 365, 269, 231);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		tableTracklist = new JTable();
		tableTracklist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Enable or Disable Play Button
				String uri = tableTracklist.getModel().getValueAt(tableTracklist.getSelectedRow(),3).toString();
				
				if (uri.isEmpty() || uri.isBlank() || uri == null) {
					btnPlay.setEnabled(false);
				}
				else {
					btnPlay.setEnabled(true);
				}
			}
		});
		JScrollPane scrollPaneTracklist = new JScrollPane(tableTracklist);
		scrollPaneTracklist.setBounds(0, 0, panel_3.getWidth(), panel_3.getHeight());
		panel_3.add(scrollPaneTracklist);
		
		JButton btnSaveTracks = new JButton("Add Tracks");
		btnSaveTracks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Database db = new Database();
				
				
				Integer row_count = tableTracklist.getModel().getRowCount();
				
				if (row_count > 0) {
					String track_name = null;
					String track_genre = lblGenreVal.getText();
					String links = null;
					
					for(int i=0; i < row_count; i++) {
						track_name = tableTracklist.getModel().
								getValueAt(i,2).toString();
						links = tableTracklist.getModel().getValueAt(i, 3).toString();
						
						db.addTracks(track_name, track_genre, links);				
					}				
				}
				
			}
		});
		btnSaveTracks.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSaveTracks.setBounds(997, 607, 95, 34);
		frame.getContentPane().add(btnSaveTracks);
		
		JButton btnSaveArtist = new JButton("Add Artist");
		btnSaveArtist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Database db = new Database();
				
				String artist_name = tableArtists.getModel().getValueAt(tableArtists.getSelectedRow(),1).toString();
				String artist_profile = tableArtists.getModel().getValueAt(tableArtists.getSelectedRow(),2).toString();
				String artist_thumb = tableArtists.getModel().getValueAt(tableArtists.getSelectedRow(),3).toString();
						 
				db.addArtist(artist_name, artist_profile, artist_thumb);
				
			}
		});
		btnSaveArtist.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSaveArtist.setBounds(1088, 607, 95, 34);
		frame.getContentPane().add(btnSaveArtist);
		txtAlbumTitleVal.setForeground(new Color(0, 0, 0));
		txtAlbumTitleVal.setWrapStyleWord(true);
		txtAlbumTitleVal.setBackground(new Color(250, 235, 215));
		txtAlbumTitleVal.setFont(new Font("Dialog", Font.BOLD, 14));
		txtAlbumTitleVal.setRows(2);
		txtAlbumTitleVal.setLineWrap(true);
		txtAlbumTitleVal.setEditable(false);
		txtAlbumTitleVal.setBounds(0, 0, 1, 15);
		frame.getContentPane().add(txtAlbumTitleVal);
		
		JScrollPane scrollPane = new JScrollPane(txtAlbumTitleVal);
		scrollPane.setBounds(1000, 162, 267, 72);
		frame.getContentPane().add(scrollPane);
		
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Play Youtube video
				String uri = tableTracklist.getModel().getValueAt(
						tableTracklist.getSelectedRow(),3).toString();
				
				YouTubeViewer youtube = new YouTubeViewer(uri);					
			}
		});
		btnPlay.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnPlay.setBounds(1180, 607, 88, 34);
		btnPlay.setEnabled(false);
		frame.getContentPane().add(btnPlay);
	}
	// Custom methods
	
	private void displayArtist() {
		
		String artist_name = tableArtists.getModel().getValueAt(tableArtists.getSelectedRow(),1).toString();
		String artist_profile = tableArtists.getModel().getValueAt(tableArtists.getSelectedRow(),2).toString();
		String artist_thumb = tableArtists.getModel().getValueAt(tableArtists.getSelectedRow(),3).toString();
		
		try {
			
			url = new URL(artist_thumb);
			image = ImageIO.read(url);
			lblArtistImage.setIcon(new ImageIcon(image));
			
			lblArtistName.setText(artist_name);
			txtProfile.setText(artist_profile);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private void displayAlbum() {
		
		
		txtAlbumTitleVal.setText(tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),6).toString());				
		lblReleasedVal.setText(tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),8).toString());
		lblGenreVal.setText(tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),7).toString());
		lblCountryVal.setText(tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),9).toString());
		
		
		String img = tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),4).toString().trim().equals("") 
				  ? "https://via.placeholder.com/150" 
				  : tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),4).toString().trim();
		
		try {
			url = new URL(img);
			image = ImageIO.read(url);					
			lblAlbumImage.setIcon(new ImageIcon(image));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
	}
	
	private void displayTrackList() {
		
		String album_id = tableAlbum.getModel().getValueAt(tableAlbum.getSelectedRow(),0).toString();
		tableTracklist.setModel(discogs_api.getTrackListTableModel(album_id));
		tableTracklist.removeColumn(tableTracklist.getColumnModel().getColumn(0));
		tableTracklist.removeColumn(tableTracklist.getColumnModel().getColumn(0));
		tableTracklist.setRowSelectionInterval(0, 0);
		
	}
}
