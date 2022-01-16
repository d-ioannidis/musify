package Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AddSongs {
	
	private static Database obj = new Database();
	private  static JTextField track_name;	
	private static JTextField textFieldCategory;
	private static JTextField textFieldLinks;
	
	private static String track;
	private static String category;
	private static String links;
	
	public static void getData() {
		track = track_name.getText();
		category = textFieldCategory.getText();
		links = textFieldLinks.getText();
	}
	
	
  public static void main(String[] args) {
		
		
		//new frame
		final JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setSize(500,390);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        //Label track name
        JLabel trName = new JLabel("Track name");
        trName.setForeground(Color.WHITE);
        trName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        trName.setBounds(20,46,100,25);
        frame.getContentPane().add(trName);
        //Close button
        JButton btnClose = new JButton("");
        btnClose.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\buttons\\close.png"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnClose.setBounds(20,301,43,39);
		frame.getContentPane().add(btnClose);
		
  
       
        //Previous button update to InsertPhoto.java
       JButton btnPrev = new JButton("");
       btnPrev.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\buttons\\back.png"));
       btnPrev.setBounds(427, 301, 43, 39);
       btnPrev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 frame.dispose();
	                InsertPhoto.main(null);
				
			}
        });
       frame.getContentPane().add(btnPrev);
       
       //TextField Import
       track_name = new JTextField();
       track_name.setBounds(155,47,180,25);
       frame.getContentPane().add(track_name);
       
       
       
       //Import button update to Database.java
       JButton btnImport = new JButton("Import");
       btnImport.setBounds(196,171, 100, 25);
       btnImport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Boolean flag_existingSong;
				
				getData();
				if(flag_existingSong = obj.existingSong(track, links) == true) {
					obj.insertTrackLinkAndCategory(track, links, category);
					JOptionPane.showMessageDialog(null, "Successful track addition");
				}
				else {
					JOptionPane.showMessageDialog(null, "Error : Track already exists");
				}
			}
				
        });
       frame.getContentPane().add(btnImport);	
       
       JLabel lblCategory = new JLabel("Category");
       lblCategory.setForeground(Color.WHITE);
       lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
       lblCategory.setBounds(20, 93, 100, 25);
       frame.getContentPane().add(lblCategory);
       
       textFieldCategory = new JTextField();
       textFieldCategory.setBounds(155, 94, 180, 25);
       frame.getContentPane().add(textFieldCategory);
       textFieldCategory.setColumns(10);
       
       JLabel lblAddaSong = new JLabel("Add A Song");
       lblAddaSong.setForeground(Color.WHITE);
       lblAddaSong.setFont(new Font("Tahoma", Font.BOLD, 16));
       lblAddaSong.setBounds(190, 11, 118, 25);
       frame.getContentPane().add(lblAddaSong);
       
       JButton btnReturnToIntro = new JButton("Return to Intro");
       btnReturnToIntro.addActionListener(new ActionListener() {
    	   public void actionPerformed(ActionEvent e) {
    		   frame.dispose();
    		   Intro.main(null);
    	   }
       });
       btnReturnToIntro.setBounds(179, 315, 129, 25);
       frame.getContentPane().add(btnReturnToIntro);
       
       JLabel lblLinkOfTrack = new JLabel("Link of track");
       lblLinkOfTrack.setForeground(Color.WHITE);
       lblLinkOfTrack.setFont(new Font("Tahoma", Font.PLAIN, 13));
       lblLinkOfTrack.setBounds(20, 142, 69, 14);
       frame.getContentPane().add(lblLinkOfTrack);
       
       textFieldLinks = new JTextField();
       textFieldLinks.setBounds(155, 140, 180, 20);
       frame.getContentPane().add(textFieldLinks);
       textFieldLinks.setColumns(10);
       
       JLabel lblNewLabel = new JLabel("");
       lblNewLabel.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\Images\\logoMain.png"));
       lblNewLabel.setBounds(0, 0, 484, 351);
       frame.getContentPane().add(lblNewLabel);
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
			}
        
       //Save to db method
  /*
  public static void SaveToDB() {
	
	  getData();
	  Boolean flag_search;
	  //Existing song check
	  if(flag_search = obj.existingTrack(Holder) == true) {
		  
	 //insert to database
	  try {
		  Database.InsertData("INSERT INTO tracks(track_name)"+"VALUES ('"+ Import.getText() +"')");
		  JOptionPane.showMessageDialog(null, "Succesfully submitted!");
		  }catch(Exception ex) {
			  JOptionPane.showMessageDialog(null, "Failed submitted"); 
			  
			  }
	  //clear textfield after import
	  Import.setText("");
  }
	  else {
		  JOptionPane.showMessageDialog(null, "Error : The song allready exist");
		  Import.setText("");
	  }
  }
  */
	}
	
	
	

