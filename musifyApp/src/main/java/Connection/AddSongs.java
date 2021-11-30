import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddSongs {
	public static void getData() {
        Holder = Import.getText();
    }
static Database obj = new Database();

	private static String Holder;
	private  static JTextField Import;	
  public static void main(String[] args) {
		
		
		//new frame
		final JFrame frame = new JFrame();
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        //Label track name
        JLabel trName = new JLabel("Track name");
        trName.setBounds(10,30,180,25);
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
		
  
       
        //Previous button
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
       Import = new JTextField();
       Import.setBounds(100,30,180,25);
       frame.getContentPane().add(Import);
       
       
       
       //Import button
       JButton btnImport = new JButton("Import");
       btnImport.setBounds(320,30, 100, 25);
       btnImport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				SaveToDB();
			}
				
        });
       frame.getContentPane().add(btnImport);	
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
			}
        
       //Save to db method
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
       
	}
	
	
	




