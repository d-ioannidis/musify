package Connection;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.event.MenuKeyEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Font;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormMusify1 {

	private JFrame frame;
	private JTextField textFieldNickN;
	private JTextField textFieldName;
	private JTextField textFieldSurName;
	private JTextField textFieldBirth;
	private JTextField textFieldNation;
	private JTextField textFieldFirstSong;
	private JTextField textFieldFamousSong;
	private JTextField textFieldSearchArtist;
	private JTextField textFieldSearchSong;
	private static JTable table = new JTable();
	private static JScrollPane pane = new JScrollPane(table);
    private static JFrame f = new JFrame("Populate JTable from Database");
    private static JPanel panel = new JPanel();
	private static DB obj = new DB();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormMusify1 window = new FormMusify1();
					window.frame.setVisible(true);
					
					table.setModel(obj.selectDataArtist());	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FormMusify1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 0, 255));
		frame.getContentPane().setLayout(null);
		
		JPanel panelBio = new JPanel();
		panelBio.setBackground(new Color(30, 144, 255));
		panelBio.setBounds(10, 123, 294, 374);
		frame.getContentPane().add(panelBio);
		panelBio.setLayout(null);
		
		JLabel lblImage = new JLabel("New label");
		lblImage.setBounds(77, 42, 125, 108);
		panelBio.add(lblImage);
		
		JLabel lblBio = new JLabel("\u0392\u03B9\u03BF\u03B3\u03C1\u03B1\u03C6\u03B9\u03BA\u03CC");
		lblBio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBio.setBackground(new Color(0, 128, 0));
		lblBio.setBounds(100, 156, 100, 14);
		panelBio.add(lblBio);
		
		JLabel lblNickName = new JLabel("\u03A8\u03B5\u03C5\u03B4\u03CE\u03BD\u03C5\u03BC\u03BF");
		lblNickName.setBounds(10, 181, 140, 14);
		panelBio.add(lblNickName);
		
		JLabel lblName = new JLabel("\u038C\u03BD\u03BF\u03BC\u03B1");
		lblName.setBounds(10, 206, 140, 14);
		panelBio.add(lblName);
		
		JLabel lblSurname = new JLabel("\u0395\u03C0\u03CE\u03BD\u03C5\u03BC\u03BF");
		lblSurname.setBounds(10, 231, 140, 14);
		panelBio.add(lblSurname);
		
		JLabel lblBirthDate = new JLabel("\u0397\u03BC.\u0393\u03AD\u03BD\u03B7\u03C3\u03B7\u03C2");
		lblBirthDate.setBounds(10, 256, 140, 14);
		panelBio.add(lblBirthDate);
		
		JLabel lblNationality = new JLabel("\u0395\u03B8\u03BD\u03B9\u03BA\u03CC\u03C4\u03B7\u03C4\u03B1");
		lblNationality.setBounds(10, 281, 114, 14);
		panelBio.add(lblNationality);
		
		JLabel lblFirstSong = new JLabel("\u0397\u03BC.\u03A0\u03C1\u03CE\u03C4\u03B7\u03C2 \u039A\u03C5\u03BA\u03BB.");
		lblFirstSong.setBounds(10, 306, 114, 14);
		panelBio.add(lblFirstSong);
		
		JLabel lblFamousSong = new JLabel("\u0394\u03B9\u03B1\u03C3\u03B7\u03BC\u03CC\u03C4\u03B5\u03C1\u03BF \u03A4\u03C1\u03B1\u03B3\u03BF\u03CD\u03B4\u03B9");
		lblFamousSong.setBounds(10, 331, 114, 14);
		panelBio.add(lblFamousSong);
		
		textFieldNickN = new JTextField();
		textFieldNickN.setBounds(154, 178, 125, 20);
		panelBio.add(textFieldNickN);
		textFieldNickN.setColumns(10);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(154, 203, 125, 20);
		panelBio.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurName = new JTextField();
		textFieldSurName.setBounds(154, 228, 125, 20);
		panelBio.add(textFieldSurName);
		textFieldSurName.setColumns(10);
		
		textFieldBirth = new JTextField();
		textFieldBirth.setBounds(154, 253, 125, 20);
		panelBio.add(textFieldBirth);
		textFieldBirth.setColumns(10);
		
		textFieldNation = new JTextField();
		textFieldNation.setBounds(154, 278, 125, 20);
		panelBio.add(textFieldNation);
		textFieldNation.setColumns(10);
		
		textFieldFirstSong = new JTextField();
		textFieldFirstSong.setBounds(154, 303, 125, 20);
		panelBio.add(textFieldFirstSong);
		textFieldFirstSong.setColumns(10);
		
		textFieldFamousSong = new JTextField();
		textFieldFamousSong.setBounds(154, 328, 125, 20);
		panelBio.add(textFieldFamousSong);
		textFieldFamousSong.setColumns(10);
		
		textFieldSearchArtist = new JTextField();
		textFieldSearchArtist.setBounds(10, 11, 269, 20);
		panelBio.add(textFieldSearchArtist);
		textFieldSearchArtist.setColumns(10);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(FormMusify1.class.getResource("/Images/musifyLogoNew.png")));
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
		
		table = new JTable();		
		
		JScrollPane scrollPane = new JScrollPane(table);		
		scrollPane.setBounds(0, 0, panel_3.getWidth(), panel_3.getHeight());
		panel_3.add(scrollPane);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(27, 624, 89, 23);
		frame.getContentPane().add(btnClose);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(883, 624, 89, 23);
		frame.getContentPane().add(btnLogOut);
		
		JButton btnAbout = new JButton("About");
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
