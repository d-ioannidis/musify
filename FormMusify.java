import java.awt.EventQueue;

import java.awt.*;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.Blob;
import java.sql.SQLException;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private static JTable table = new JTable();
	private static Database database = new Database();
	
	private List<Artist> artists = new ArrayList<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMusify window = new FormMusify();
					window.frame.setVisible(true);
					
					table.setModel(database.selectDataArtist());	
							
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
	}
	
	
	public void searchArtist(String artist_nickname) {	
		
		//System.out.print(artists.get(0).getNickname());
		
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


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
    	artists = database.getArtists();
    	
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 0, 255));
		frame.getContentPane().setLayout(null);
		
		JPanel panelBio = new JPanel();
		panelBio.setBackground(new Color(30, 144, 255));
		panelBio.setBounds(10, 46, 294, 425);
		frame.getContentPane().add(panelBio);
		panelBio.setLayout(null);
		
		
		lblImage.setBounds(10, 36, 284, 201);
		panelBio.add(lblImage);
		
		JLabel lblBio = new JLabel("\u0392\u03B9\u03BF\u03B3\u03C1\u03B1\u03C6\u03B9\u03BA\u03CC");
		lblBio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBio.setBackground(new Color(0, 128, 0));
		lblBio.setBounds(100, 248, 100, 14);
		panelBio.add(lblBio);
		
		JLabel lblNickName = new JLabel("\u03A8\u03B5\u03C5\u03B4\u03CE\u03BD\u03C5\u03BC\u03BF");
		lblNickName.setBounds(10, 273, 140, 14);
		panelBio.add(lblNickName);
		
		JLabel lblName = new JLabel("\u038C\u03BD\u03BF\u03BC\u03B1");
		lblName.setBounds(10, 298, 140, 14);
		panelBio.add(lblName);
		
		JLabel lblSurname = new JLabel("\u0395\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF");
		lblSurname.setBounds(10, 323, 140, 14);
		panelBio.add(lblSurname);
		
		JLabel lblBirthDate = new JLabel("\u0397\u03BC.\u0393\u03AD\u03BD\u03B7\u03C3\u03B7\u03C2");
		lblBirthDate.setBounds(10, 348, 140, 14);
		panelBio.add(lblBirthDate);
		
		JLabel lblNationality = new JLabel("\u0395\u03B8\u03BD\u03B9\u03BA\u03CC\u03C4\u03B7\u03C4\u03B1");
		lblNationality.setBounds(10, 373, 114, 14);
		panelBio.add(lblNationality);
		
		JLabel lblFirstSong = new JLabel("\u0397\u03BC.\u03A0\u03C1\u03CE\u03C4\u03B7\u03C2 \u039A\u03C5\u03BA\u03BB.");
		lblFirstSong.setBounds(10, 398, 114, 14);
		panelBio.add(lblFirstSong);
		
		textFieldNickN = new JTextField();
		textFieldNickN.setBounds(154, 270, 125, 20);
		panelBio.add(textFieldNickN);
		textFieldNickN.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(154, 295, 125, 20);
		panelBio.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurName = new JTextField();
		textFieldSurName.setBounds(154, 320, 125, 20);
		panelBio.add(textFieldSurName);
		textFieldSurName.setColumns(10);
		
		textFieldBirth = new JTextField();
		textFieldBirth.setBounds(154, 345, 125, 20);
		panelBio.add(textFieldBirth);
		textFieldBirth.setColumns(10);
		
		textFieldNation = new JTextField();
		textFieldNation.setBounds(154, 370, 125, 20);
		panelBio.add(textFieldNation);
		textFieldNation.setColumns(10);
		
		textFieldFirstSong = new JTextField();
		textFieldFirstSong.setBounds(154, 395, 125, 20);
		panelBio.add(textFieldFirstSong);
		textFieldFirstSong.setColumns(10);
		
		textFieldSearchArtist = new JTextField();
		textFieldSearchArtist.setBounds(10, 11, 168, 20);
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
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FormMusify.class.getResource("/Images/musifyLogoNew.png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(329, 11, 441, 50);
		frame.getContentPane().add(lblLogo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setBounds(329, 72, 441, 39);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textFieldSearchSong = new JTextField();
		textFieldSearchSong.setBounds(10, 11, 421, 20);
		panel_1.add(textFieldSearchSong);
		textFieldSearchSong.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(30, 144, 255));
		panel_3.setBounds(329, 122, 441, 491);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		/*table = new JTable();		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//System.out.print(artists.get(0).getNickname());
				
				Point point = e.getPoint();
				int column = table.columnAtPoint(point);
				int row = table.rowAtPoint(point);								
				table.setSelectionBackground(Color.YELLOW);
				searchArtist(table.getModel().getValueAt(row, 0).toString());
			}
		});*/
		
		JScrollPane scrollPane = new JScrollPane(table);		
		scrollPane.setBounds(0, 0, panel_3.getWidth(), panel_3.getHeight());
		panel_3.add(scrollPane);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setBounds(27, 624, 89, 23);
		frame.getContentPane().add(btnClose);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		btnLogOut.setBounds(883, 624, 89, 23);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Musify"+ System.lineSeparator()+ "Κωστινάς Δημήτριος 4609"+ System.lineSeparator()
                +"Κωφίδης Γεώργιος 4665"+ System.lineSeparator()+ "Καραπιλιάφης Γεώργιος 4679"+ System.lineSeparator()+ "Δαράς Δημήτριος 4585"
                + System.lineSeparator()+ "Ιωαννίδης Δημήτριος 4578"+ System.lineSeparator()+ "Πόπτσης Νικόλαος 4598"+ System.lineSeparator()+ "Παλουκτσόγλου Μελέτιος 4636");
			}
		});
		btnAbout.setBounds(784, 624, 89, 23);
		frame.getContentPane().add(btnAbout);
		frame.setBounds(100, 100, 998, 697);
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
}
