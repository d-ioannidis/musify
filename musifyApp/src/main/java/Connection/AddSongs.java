import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddSongs {

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
       JTextField Import = new JTextField("");
       Import.setBounds(100,30,180,25);
       frame.getContentPane().add(Import);
       
       //Import button
       JButton btnImport = new JButton("Import");
       btnImport.setBounds(320,30, 100, 25);
       btnImport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 frame.dispose();
	                InsertDataArtist.main(null);
				
			}
        });
       frame.getContentPane().add(btnImport);
       
       frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	
	

}

