import java.awt.EventQueue;

import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.Image;
import javax.swing.*;

public class InsertPhoto {

	private JFrame frame;
    private JLabel label_1;
    private String s;
    private JTextField textName;
    private JTextField textLname;
    private JTextField textNickname;
    private JTextField Bday;
    private JTextField ftn;
    private JTextField Nation;
    private JButton BackButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertPhoto window = new InsertPhoto();
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
	public InsertPhoto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 816, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		BackButton = new JButton("Back");
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		BackButton.setBounds(10, 350, 100, 30);
		frame.getContentPane().add(BackButton);
		
		textName = new JTextField("Name");
		textName.setBounds(120, 270, 100, 20);
		frame.getContentPane().add(textName);
		
		textLname = new JTextField("Lastname");
		textLname.setBounds(230, 270, 100, 20);
		frame.getContentPane().add(textLname);
		
		textNickname = new JTextField("Nickname");
		textNickname.setBounds(340, 270, 100, 20);
		frame.getContentPane().add(textNickname);
		
		Bday = new JTextField("Birthday");
		Bday.setBounds(450, 270, 100, 20);
		frame.getContentPane().add(Bday);
		
		ftn = new JTextField("First Track Date");
		ftn.setBounds(560, 270, 100, 20);
		frame.getContentPane().add(ftn);
		
		Nation = new JTextField("Nationality");
		Nation.setBounds(670, 270, 100, 20);
		frame.getContentPane().add(Nation);
		
		label_1 = new JLabel();
		label_1.setBounds(57, 11, 670, 250);
		frame.getContentPane().add(label_1);
		
		JButton InsertButton = new JButton("Insert");
		InsertButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
		               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","N123456789");
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
		InsertButton.setBounds(10, 310, 100, 30);
		frame.getContentPane().add(InsertButton);
		
		JButton SearchButton = new JButton("Search");
		SearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		         fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		         FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg","gif","png");
		         fileChooser.addChoosableFileFilter(filter);
		         int result = fileChooser.showSaveDialog(null);
		         if(result == JFileChooser.APPROVE_OPTION){
		             File selectedFile = fileChooser.getSelectedFile();
		             String path = selectedFile.getAbsolutePath();
		             label_1.setIcon(ResizeImage(path));
		             s = path;
		              }
		         else if(result == JFileChooser.CANCEL_OPTION){
		             System.out.println("No Data");
		         }
			}
		});
		SearchButton.setBounds(10, 270, 100, 30);
		frame.getContentPane().add(SearchButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(InsertPhoto.class.getResource("/Images/logoMain.png")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(0, 0, 800, 391);
		frame.getContentPane().add(lblNewLabel_1);
	}
	
	public ImageIcon ResizeImage(String imgPath){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(label_1.getWidth(), label_1.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }

}
