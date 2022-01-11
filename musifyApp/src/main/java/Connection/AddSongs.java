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
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        //Label track name
        JLabel trName = new JLabel("Track name");
        trName.setFont(new Font("Tahoma", Font.PLAIN, 13));
        trName.setBounds(20,46,100,25);
        frame.getContentPane().add(trName);
        //Close button
        JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnClose.setBounds(20,230,100,25);
		frame.getContentPane().add(btnClose);
		
  
       
        //Previous button update to InsertPhoto.java
       JButton btnPrev = new JButton("Previous");
       btnPrev.setBounds(370, 230, 100, 25);
       btnPrev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 frame.dispose();
	                InsertDataArtist.main(null);
				
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
       lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 13));
       lblCategory.setBounds(20, 93, 100, 25);
       frame.getContentPane().add(lblCategory);
       
       textFieldCategory = new JTextField();
       textFieldCategory.setBounds(155, 94, 180, 25);
       frame.getContentPane().add(textFieldCategory);
       textFieldCategory.setColumns(10);
       
       JLabel lblAddaSong = new JLabel("Add A Song");
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
       btnReturnToIntro.setBounds(167, 229, 129, 25);
       frame.getContentPane().add(btnReturnToIntro);
       
       JLabel lblLinkOfTrack = new JLabel("Link of track");
       lblLinkOfTrack.setFont(new Font("Tahoma", Font.PLAIN, 13));
       lblLinkOfTrack.setBounds(20, 142, 69, 14);
       frame.getContentPane().add(lblLinkOfTrack);
       
       textFieldLinks = new JTextField();
       textFieldLinks.setBounds(155, 140, 180, 20);
       frame.getContentPane().add(textFieldLinks);
       textFieldLinks.setColumns(10);
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
	
	
	

