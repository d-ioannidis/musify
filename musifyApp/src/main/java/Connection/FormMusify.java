package Connection;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.Thread;
import javazoom.jl.player.Player;

public class FormMusify{

	private JFrame frame;
	private JTextField textFieldNickN;
	private JTextField textFieldName;
	private JTextField textFieldSurName;
	private JTextField textFieldBirth;
	private JTextField textFieldNation;
	private JTextField textFieldFirstSong;
	private JTextField textFieldSearchArtist;
	private JTextField textFieldSearchSong;
	private JLabel lblImage = new JLabel("");
	private JLabel lblSignedIn = new JLabel("");
	private JLabel songName = new JLabel("");
	private static JTable table = new JTable();
	private static Database database = new Database();
	private List<Favourites> favourites = new ArrayList<>();
	private List<Playlist> playlist = new ArrayList<>();
	private File myFile;
	private String filename,filePath;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private Player MP;
	private int LT;
	private int TL;

	private static String user_id = null;
	private static String user_fullname = null;
	private static String[] args = null;

	private List<Artist> artists = new ArrayList<>();
	//private Component btnCreateNewPlaylist;
	
	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					setUser_id(args[0]);
					setUser_fullname(args[1] + " " + args[2]);
					setArgs(args);
					
					FormMusify window = new FormMusify();
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
	public FormMusify() {
		initialize();
		table.setModel(database.selectDataArtist());
		//table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		
		JButton btnClose = new JButton("");
		btnClose.setBounds(10, 633, 47, 39);
		frame.getContentPane().add(btnClose);
		btnClose.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musifyApp\\src\\main\\java\\buttons\\close.png"));
		
		
		JButton btnAbout = new JButton("About");
		btnAbout.setBounds(714, 633, 128, 39);
		frame.getContentPane().add(btnAbout);
		btnAbout.setIcon(new ImageIcon(FormMusify.class.getResource("/buttons/about.png")));
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(844, 633, 128, 39);
		frame.getContentPane().add(btnLogOut);
		btnLogOut.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musifyApp\\src\\main\\java\\buttons\\logout.png"));
		
		textFieldSearchSong = new JTextField();
		textFieldSearchSong.setBounds(340, 83, 421, 20);
		frame.getContentPane().add(textFieldSearchSong);
		textFieldSearchSong.setColumns(10);
		
		JLabel Previous = new JLabel("");
		Previous.setIcon(new ImageIcon(FormMusify.class.getResource("/buttons/previous.png")));
		Previous.setBounds(121, 639, 27, 27);
		frame.getContentPane().add(Previous);
		
		JLabel Pause = new JLabel("");
		Pause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					LT = fis.available();
					MP.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		Pause.setIcon(new ImageIcon(FormMusify.class.getResource("/buttons/pause.png")));
		Pause.setBounds(158, 634, 35, 38);
		frame.getContentPane().add(Pause);
		
		JLabel Start = new JLabel("");
		Start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				Runnable runnablePlay = new Runnable() {
			        @Override
			        public void run() {
			            try {
			            	if (LT != 0) {
			            		fis = new FileInputStream(myFile);
				            	fis.skip(TL-LT);
				            	bis = new BufferedInputStream(fis);
				            	MP = new Player(bis);
				            	MP.play();
			            	}
			            	else {
			            		fis = new FileInputStream(myFile);
				            	bis = new BufferedInputStream(fis);
				            	MP = new Player(bis);
				            	try {
									TL = fis.available();
									MP.play();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
			            	}
			            } catch (Exception e) {
			                e.printStackTrace();
			            }
			        }
			    };
			    if (filename != null) {
			    	if (MP != null) {
			    		MP.close();
			    	}
			    	Thread task = new Thread(runnablePlay);
			    	task.start();
                    songName.setText("Now playing : " + filename);
                } else {
                    songName.setText("No File was selected!");
                }
			}
		});
		Start.setIcon(new ImageIcon(FormMusify.class.getResource("/buttons/play.png")));
		Start.setBounds(203, 634, 35, 38);
		frame.getContentPane().add(Start);
		
		JLabel Next = new JLabel("");
		Next.setIcon(new ImageIcon(FormMusify.class.getResource("/buttons/next.png")));
		Next.setBounds(248, 639, 27, 27);
		frame.getContentPane().add(Next);
		
		JButton btnNewButton_1 = new JButton("Add Songs");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Mp3 files", "mp3");
				fs.setFileFilter(filter);
				int r =fs.showOpenDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					myFile = fs.getSelectedFile();
	                filename = fs.getSelectedFile().getName();
	                filePath = fs.getSelectedFile().getPath();
	                songName.setText("File Selected : " + filename);
	                TL = 0;
	                LT = 0;
				}
			}
		});
		btnNewButton_1.setBounds(315, 641, 100, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		songName.setBounds(425, 645, 279, 14);
		frame.getContentPane().add(songName);
		
		textFieldSearchSong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            	 super.keyTyped(e);
            }
        });
		
		textFieldSearchSong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                table.setRowSorter(sorter);
                String text = textFieldSearchSong.getText();
                if(text.length() == 0) {
                   sorter.setRowFilter(null);
                } else {
                	
                   try { 
                	   // (?i) means case insensitive search
                      sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                   }
                   catch(PatternSyntaxException pse) {
                         System.out.println("Bad regex pattern");
                   }
                 }
            }
        });
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Musify"+ System.lineSeparator()+ "Kostinas Dimitrios 4609"+ System.lineSeparator()
                +"Kofidis Georgios 4665"+ System.lineSeparator()+ "Karapiliafis Georgios 4679"+ System.lineSeparator()+ "Daras Dimitrios 4585"
                + System.lineSeparator()+ "Ioannidis Dimitrios 4578"+ System.lineSeparator()+ "Poptsis Nikolaos 4598"+ System.lineSeparator()+ "Palouktsoglou Meletios 4636");
			}
		});
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Musify"+ System.lineSeparator()+ "Kostinas Dimitrios 4609"+ System.lineSeparator()
                +"Kofidis Georgios 4665"+ System.lineSeparator()+ "Karapiliafis Georgios 4679"+ System.lineSeparator()+ "Daras Dimitrios 4585"
                + System.lineSeparator()+ "Ioannidis Dimitrios 4578"+ System.lineSeparator()+ "Poptsis Nikolaos 4598"+ System.lineSeparator()+ "Palouktsoglou Meletios 4636");
			}
		});
		
		for (int i=2; i<=3; i++) {
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(i).setMaxWidth(50);
		}
		displayFavourites();
		displayPlaylist();

		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		displayFavourites();
	}
	
	public void displayFavourites() {
	    favourites = database.getFavourites();
	    int fav_size = favourites.size();
	    
	    //System.out.print(favourites.size());	
	    	
	    	for (int j = 0; j < fav_size; j++) {
	    		
	    		String favNickname = favourites.get(j).getArtistNickname().toLowerCase().trim();
	    		String favTrack = favourites.get(j).getTrack().toLowerCase().trim();
	    		
	    		for (int i = 0; i < table.getRowCount(); i++) {  // Loop through the rows	 
	    	    	String tblNickname = table.getValueAt(i, 0).toString().toLowerCase().trim();
	    	    	String tblTrack = table.getValueAt(i, 1).toString().toLowerCase().trim();
	    		
	    	    	if ( favNickname.equals(tblNickname) && favTrack.equals(tblTrack) ) {	    			
	    	    		table.setValueAt("\u2764", i, 2);				
	    			
	    	    	}
	    		
	    		}
	    		
		    }	    	
	}
	public void displayPlaylist() {
	    playlist = database.getPlaylist();
	    int playlist_size = playlist.size();
	    	    	
	    	for (int j = 0; j < playlist_size; j++) {
	    		
	    		String playlistNickname = playlist.get(j).getArtistNickname().toLowerCase().trim();
	    		String playlistTrack = playlist.get(j).getTrack().toLowerCase().trim();
	    		
	    		for (int i = 0; i < table.getRowCount(); i++) {  // Loop through the rows	 
	    	    	String tblNickname = table.getValueAt(i, 0).toString().toLowerCase().trim();
	    	    	String tblTrack = table.getValueAt(i, 1).toString().toLowerCase().trim();
	    		
	    	    	if ( playlistNickname.equals(tblNickname) && playlistTrack.equals(tblTrack) ) {	    			
	    	    		table.setValueAt("\u266B", i, 3);				
	    			
	    	    	}
	    		
	    		}
	    		
		    }	    	
	}
	
	public void searchArtist(String artist_nickname) {	
		
    	for (Artist artist : artists) {
            if (artist.getNickname().equals(artist_nickname)) {                
                textFieldNickN.setText(artist.getNickname());
				textFieldName.setText(artist.getFirstname());
				textFieldSurName.setText(artist.getLastname());
				textFieldBirth.setText(artist.getBirthday());
				textFieldNation.setText(artist.getNationality());
				textFieldFirstSong.setText(artist.getFirst_track_date());										         
									
				Blob blob = artist.getPhoto();		                  
            	ImageIcon icon = null;
            	
            	if (blob != null) {
					try {
						icon = new ImageIcon(blob.getBytes(1, (int) blob.length()));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}            	             	
	            	Image scaledPic = icon.getImage().getScaledInstance(250, -1, Image.SCALE_SMOOTH);
	            	lblImage.setIcon( new ImageIcon(scaledPic));
            	}
            }
        }
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
    	artists = database.getArtists();
    	    	
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		lblSignedIn.setBounds(10, 0, 309, 15);
		
		lblSignedIn.setForeground(Color.DARK_GRAY);
		lblSignedIn.setText("Signed in as " + getUser_fullname());
		frame.getContentPane().add(lblSignedIn);
		
		JPanel panelBio = new JPanel();
		panelBio.setBounds(0, 21, 330, 596);
		panelBio.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panelBio);
		panelBio.setLayout(null);
		
		
		lblImage.setBounds(10, 36, 284, 199);
		panelBio.add(lblImage);
		
		JLabel lblBio = new JLabel("\u0392\u03B9\u03BF\u03B3\u03C1\u03B1\u03C6\u03B9\u03BA\u03CC");
		lblBio.setForeground(Color.WHITE);
		lblBio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBio.setBackground(new Color(0, 128, 0));
		lblBio.setBounds(103, 235, 100, 14);
		panelBio.add(lblBio);
		
		JLabel lblNickName = new JLabel("\u03A8\u03B5\u03C5\u03B4\u03CE\u03BD\u03C5\u03BC\u03BF");
		lblNickName.setForeground(Color.WHITE);
		lblNickName.setBounds(10, 263, 140, 14);
		panelBio.add(lblNickName);
		
		JLabel lblName = new JLabel("\u038C\u03BD\u03BF\u03BC\u03B1");
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(10, 300, 140, 14);
		panelBio.add(lblName);
		
		JLabel lblSurname = new JLabel("\u0395\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF");
		lblSurname.setForeground(Color.WHITE);
		lblSurname.setBounds(10, 325, 140, 14);
		panelBio.add(lblSurname);
		
		JLabel lblBirthDate = new JLabel("\u0397\u03BC.\u0393\u03AD\u03BD\u03B7\u03C3\u03B7\u03C2");
		lblBirthDate.setForeground(Color.WHITE);
		lblBirthDate.setBounds(10, 356, 140, 14);
		panelBio.add(lblBirthDate);
		
		JLabel lblFirstSong = new JLabel("\u0397\u03BC.\u03A0\u03C1\u03CE\u03C4\u03B7\u03C2 \u039A\u03C5\u03BA\u03BB.");
		lblFirstSong.setForeground(Color.WHITE);
		lblFirstSong.setBounds(10, 387, 114, 14);
		panelBio.add(lblFirstSong);
		
		textFieldNickN = new JTextField();
		textFieldNickN.setBounds(154, 260, 125, 20);
		panelBio.add(textFieldNickN);
		textFieldNickN.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(154, 291, 125, 20);
		panelBio.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurName = new JTextField();
		textFieldSurName.setBounds(154, 322, 125, 20);
		panelBio.add(textFieldSurName);
		textFieldSurName.setColumns(10);
		
		textFieldBirth = new JTextField();
		textFieldBirth.setBounds(154, 353, 125, 20);
		panelBio.add(textFieldBirth);
		textFieldBirth.setColumns(10);
		
		textFieldNation = new JTextField();
		textFieldNation.setBounds(154, 384, 125, 20);
		panelBio.add(textFieldNation);
		textFieldNation.setColumns(10);
		
		textFieldFirstSong = new JTextField();
		textFieldFirstSong.setBounds(154, 415, 125, 20);
		panelBio.add(textFieldFirstSong);
		textFieldFirstSong.setColumns(10);
		
		textFieldSearchArtist = new JTextField();
		textFieldSearchArtist.setBounds(10, 11, 147, 20);
		panelBio.add(textFieldSearchArtist);
		textFieldSearchArtist.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchArtist(textFieldSearchArtist.getText());				
			}
		});
		btnNewButton.setBounds(179, 8, 100, 25);
		panelBio.add(btnNewButton);
		
		JLabel lblNationality = new JLabel("\u0395\u03B8\u03BD\u03B9\u03BA\u03CC\u03C4\u03B7\u03C4\u03B1");
		lblNationality.setBounds(10, 418, 114, 14);
		panelBio.add(lblNationality);
		lblNationality.setForeground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 446, 309, 124);
		panelBio.add(panel_2);
		panel_2.setBackground(Color.GRAY);
		panel_2.setLayout(null);
		
		JButton btnFavourites = new JButton("Show Favourites");
		btnFavourites.setBounds(49, 68, 214, 33);
		panel_2.add(btnFavourites);
		btnFavourites.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musifyApp\\src\\main\\java\\buttons\\quaver.png"));
		
		JButton btnCreateNewPlaylist = new JButton("Create New Playlist");
		btnCreateNewPlaylist.setBounds(49, 24, 214, 33);
		panel_2.add(btnCreateNewPlaylist);
		btnCreateNewPlaylist.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musifyApp\\src\\main\\java\\buttons\\quaver.png"));
		btnCreateNewPlaylist.addActionListener(new ActionListener () {
			 public void actionPerformed(ActionEvent e) {
				 frame.dispose();
				 FormPlaylist.main(getArgs());
		 }
		});
		btnFavourites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				FormFavourites.main(getArgs());
			}
		});
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(329, 0, 441, 83);
		lblLogo.setIcon(new ImageIcon(FormMusify.class.getResource("/Images/musifyLogoNew.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblLogo);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(329, 83, 441, 39);
		panel_1.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textFieldSearchSong = new JTextField();
		textFieldSearchSong.setBounds(10, 11, 421, 20);
		panel_1.add(textFieldSearchSong);
		textFieldSearchSong.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(329, 117, 441, 500);
		panel_3.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		table = new JTable();	
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Point point = e.getPoint();
				int row = table.rowAtPoint(point);
				int col = table.columnAtPoint(point);
				String artist_nickname = table.getModel().getValueAt(row, 0).toString();
				String track = table.getModel().getValueAt(row, 1).toString();	        
				searchArtist(artist_nickname);
	
				if (col == 2) {
					
					if(table.getModel().getValueAt(row, col) == "\u2764") {						
						
						database.deleteFavourite(
								Integer.parseInt(getUser_id()), 
								artist_nickname, 
								track);
						
						displayFavourites();
						table.getModel().setValueAt("",row,col);
						
					}
					else {						
						
						database.addFavourite(Integer.parseInt(getUser_id()),artist_nickname,track);
						
						displayFavourites();
						table.getModel().setValueAt("\u2764",row,col);
					}
				}
				
				if (col == 3) {
					
					if(table.getModel().getValueAt(row, col) == "\u266B") {						
						
						database.deletePlaylist(
								Integer.parseInt(getUser_id()),
								artist_nickname,
								track);
						
						displayPlaylist();
						table.getModel().setValueAt("",row,col);
						
					}
					else {						
						
						database.addToPlaylist(
								Integer.parseInt(getUser_id()), 
								artist_nickname, 
								track);
						
						displayPlaylist();
						table.getModel().setValueAt("\u266B",row,col);
					}
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);		
		scrollPane.setBounds(0, 11, 441, 483);
		panel_3.add(scrollPane);
		frame.setBounds(100, 100, 998, 722);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textFieldSearchSong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
		
		textFieldSearchSong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                table.setRowSorter(sorter);
                String text = textFieldSearchSong.getText();
                if(text.length() == 0) {
                   sorter.setRowFilter(null);
                } else {
                	
                   try { 
                	   // (?i) means case insensitive search
                      sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                   }
                   catch(PatternSyntaxException pse) {
                         System.out.println("Bad regex pattern");
                   }
                 }
            }
        });
	}

	public static String getUser_id() {
		return user_id;
	}

	public static void setUser_id(String user_id) {
		FormMusify.user_id = user_id;
	}

	public static String getUser_fullname() {
		return user_fullname;
	}

	public static void setUser_fullname(String user_fullname) {
		FormMusify.user_fullname = user_fullname;
	}
	
	public static String[] getArgs() {
		return args;
	}
	
	public static void setArgs(String[] args) {
		FormMusify.args = args;
	}

}

