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
    JTextField textName;
    JTextField textLname;
    JTextField textNickname;
    JTextField Bday;
    JTextField ftn;
    JTextField Nation;
     
    public InsertPhoto(){
    	
        
    	textName = new JTextField("Name");
        textName.setBounds(120,270,100,20);

        textLname = new JTextField("Lastname");
        textLname.setBounds(230,270,100,20);
        
        textNickname = new JTextField("Nickname");
        textNickname.setBounds(340,270,100,20);
    
        Bday = new JTextField("Birthday");
        Bday.setBounds(450,270,100,20);
        
        ftn = new JTextField("First Track Date");
        ftn.setBounds(560,270,100,20);
        
        Nation = new JTextField("Nationality");
        Nation.setBounds(670,270,100,20);
        
    btnInsert = new JButton("Insert");
    btnInsert.setBounds(10,310,100,30);
    
    button2 = new JButton("Search");
    button2.setBounds(10, 270, 100, 30);
   
    label = new JLabel();
    label.setBounds(10,11,670,250);   
  
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
               PreparedStatement ps = con.prepareStatement("insert into artist(NAME,LASTNAME,NICKNAME,BIRTHDAY,FIRST_TRACK_DATE,NATIONALITY,PHOTO_ARTIST) values(?,?,?,?,?,?,?)");
               InputStream is = new FileInputStream(new File(s));   
               ps.setString(1, textName.getText());
               ps.setString(2, textLname.getText());
               ps.setString(3, textNickname.getText());
               ps.setString(4, Bday.getText());
               ps.setString(5, ftn.getText());
               ps.setString(6, Nation.getText());
               ps.setBlob(7,is);
               ps.executeUpdate();
               JOptionPane.showMessageDialog(null, "Data Inserted");
           }catch(Exception ex){
               ex.printStackTrace();
           }
       }
    });
    getContentPane().add(textName);
    getContentPane().add(textLname);
    getContentPane().add(textNickname);
    getContentPane().add(Bday);
    getContentPane().add(ftn);
    getContentPane().add(Nation);
    getContentPane().add(label);
    getContentPane().add(btnInsert);
    getContentPane().add(button2);
    getContentPane().setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800,420);
    setVisible(true);
    }
    
    //Method To Resize The ImageIcon
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