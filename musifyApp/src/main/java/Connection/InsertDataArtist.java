package Connection;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JFileChooser;

public class InsertDataArtist {

	private JFrame frame;
	private JTextField textFieldName;
	private JTextField textFieldLastname;
	private JTextField textFieldNickname;
	private JTextField textFieldBirthday;
	private JTextField textFieldFirstTrackDate;
	private JTextField textFieldNationality;
	private JTextField textFieldPhotoArtist;
	
	private String name;
	private String lastname;
	private String nickname;
	private String birthday;
	private String first_track_date;
	private String nationality;
	private Blob photo_artist;
	
	private Database obj = new Database();
	

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertDataArtist window = new InsertDataArtist();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// getData
	public void getData() {
		name = textFieldName.getText();
		lastname = textFieldLastname.getText();
		nickname = textFieldNickname.getText();
		birthday = textFieldBirthday.getText();
		first_track_date = textFieldFirstTrackDate.getText();
		nationality = textFieldNationality.getText();
		//photo_artist = textFieldPhotoArtist.getText();
	}

	/**
	 * Create the application.
	 */
	public InsertDataArtist() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 554, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInsertArtist = new JLabel("Insert An Artist");
		lblInsertArtist.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInsertArtist.setBounds(197, 11, 135, 14);
		frame.getContentPane().add(lblInsertArtist);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(100, 49, 46, 14);
		frame.getContentPane().add(lblName);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLastname.setBounds(100, 86, 62, 14);
		frame.getContentPane().add(lblLastname);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNickname.setBounds(100, 120, 62, 14);
		frame.getContentPane().add(lblNickname);
		
		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBirthday.setBounds(100, 155, 46, 14);
		frame.getContentPane().add(lblBirthday);
		
		JLabel lblFirstTrackDate = new JLabel("First Track Date");
		lblFirstTrackDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFirstTrackDate.setBounds(100, 193, 95, 14);
		frame.getContentPane().add(lblFirstTrackDate);
		
		JLabel lblNationality = new JLabel("Nationality");
		lblNationality.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNationality.setBounds(100, 235, 84, 14);
		frame.getContentPane().add(lblNationality);
		
		JLabel lblPhotoArtist = new JLabel("Photo Artist");
		lblPhotoArtist.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPhotoArtist.setBounds(100, 273, 95, 14);
		frame.getContentPane().add(lblPhotoArtist);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(203, 47, 86, 20);
		frame.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldLastname = new JTextField();
		textFieldLastname.setBounds(203, 84, 86, 20);
		frame.getContentPane().add(textFieldLastname);
		textFieldLastname.setColumns(10);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(203, 118, 86, 20);
		frame.getContentPane().add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		textFieldBirthday = new JTextField();
		textFieldBirthday.setBounds(203, 153, 86, 20);
		frame.getContentPane().add(textFieldBirthday);
		textFieldBirthday.setColumns(10);
		
		textFieldFirstTrackDate = new JTextField();
		textFieldFirstTrackDate.setBounds(203, 191, 86, 20);
		frame.getContentPane().add(textFieldFirstTrackDate);
		textFieldFirstTrackDate.setColumns(10);
		
		textFieldNationality = new JTextField();
		textFieldNationality.setBounds(203, 233, 86, 20);
		frame.getContentPane().add(textFieldNationality);
		textFieldNationality.setColumns(10);
		
		textFieldPhotoArtist = new JTextField();
		textFieldPhotoArtist.setBounds(203, 271, 86, 20);
		frame.getContentPane().add(textFieldPhotoArtist);
		textFieldPhotoArtist.setColumns(10);
		
		final JButton btnImportAPhoto = new JButton("Import A Photo");
		btnImportAPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            JFileChooser fc = new JFileChooser();
	            fc.setCurrentDirectory(new java.io.File("C:/"));
	            fc.setDialogTitle("File Browser.");
	            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	            if (fc.showOpenDialog(btnImportAPhoto) == JFileChooser.APPROVE_OPTION){
	                textFieldPhotoArtist.setText(fc.getSelectedFile().getAbsolutePath());
	            }
			}
		});
		btnImportAPhoto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnImportAPhoto.setBounds(330, 270, 124, 23);
		frame.getContentPane().add(btnImportAPhoto);
		
		JButton btnInsertData = new JButton("Insert Data");
		btnInsertData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Boolean flag_search;
				
				getData();
				if(flag_search = obj.existingArtist(nickname) == true) {
					obj.insertDataArtist(name, lastname, nickname, birthday, first_track_date, nationality, 
							photo_artist);
					JOptionPane.showMessageDialog(null, "Successful artist addition");
				}
				else {
					JOptionPane.showMessageDialog(null, "Error : Artist already exists");
				}
			}
		});
		btnInsertData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsertData.setBounds(186, 340, 135, 23);
		frame.getContentPane().add(btnInsertData);
		
		JButton btnAddSongs = new JButton("Add Songs");
		btnAddSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AddSongs.main(null);
			}
			});
			
		btnAddSongs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddSongs.setBounds(420, 341, 108, 23);
		frame.getContentPane().add(btnAddSongs);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldName.setText("");
				textFieldLastname.setText("");
				textFieldNickname.setText("");
				textFieldBirthday.setText("");
				textFieldFirstTrackDate.setText("");
				textFieldNationality.setText("");
				textFieldPhotoArtist.setText("");
			}
		});
		
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClear.setBounds(10, 341, 89, 23);
		frame.getContentPane().add(btnClear);
	}
}
