import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class InsertPhoto extends JFrame{
    JButton btnInsert ;
    JButton button2;
    JLabel label;
    String s;
     
    public InsertPhoto(){
   

    
    btnInsert = new JButton("Insert");
    btnInsert.setBounds(200,300,100,30);
    
    button2 = new JButton("Search");
    button2.setBounds(80, 300, 100, 30);
   
    label = new JLabel();
    label.setBounds(10,10,670,250);   
  
    //button to browse the image into jlabel
    button2.addActionListener(new ActionListener(){
        @Override
     public void actionPerformed(ActionEvent e){
         JFileChooser fileChooser = new JFileChooser();
         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
         fileChooser.addChoosableFileFilter(filter);
         int result = fileChooser.showSaveDialog(null);
         if(result == JFileChooser.APPROVE_OPTION){
             File selectedFile = fileChooser.getSelectedFile();
             String path = selectedFile.getAbsolutePath();
             label.setIcon(ResizeImage(path));
             s = path;
              }
         else if(result == JFileChooser.CANCEL_OPTION){
             System.out.println("No Data");
         }
     }
    });

    //button to insert image and some data into mysql database
    btnInsert.addActionListener(new ActionListener(){
    
       @Override
       public void actionPerformed(ActionEvent e){
           try{
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Sarap4610_Kof4665_Ioan4578_Alex4631");
               PreparedStatement ps = con.prepareStatement("insert into artist(PHOTO_ARTIST) values(?)");
               InputStream is = new FileInputStream(new File(s));        
               ps.setBlob(1,is);
               ps.executeUpdate();
               JOptionPane.showMessageDialog(null, "Data Inserted");
           }catch(Exception ex){
               ex.printStackTrace();
           }
       }
    });

    getContentPane().add(label);
    getContentPane().add(btnInsert);
    getContentPane().add(button2);
    getContentPane().setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(700,420);
    setVisible(true);
    }
    
    //Methode To Resize The ImageIcon
    public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
 
    public static void main(String[] args){
        new InsertPhoto();
    }
   }