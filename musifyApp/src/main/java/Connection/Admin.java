import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Admin {

	private static Database obj = new Database();
	private static String username;
	private static String password;
	
	public static void main(String[] args) {
		
		final JFrame frame = new JFrame();	
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 500, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Administrator = new JLabel("Administrator");
		Administrator.setFont(new Font("Tahoma", Font.BOLD, 20));
		Administrator.setBounds(170,10,180,25);
		frame.getContentPane().add(Administrator);
		
		JLabel label = new JLabel("Username : ");
		label.setBounds(70,100,80,25);
		frame.getContentPane().add(label);
		
		final JTextField userText = new JTextField(20);
		userText.setBounds(170,100,165,25);
		frame.getContentPane().add(userText);
		
		JLabel label1 = new JLabel("Password : ");
		label1.setBounds(70,150,80,25);
		frame.getContentPane().add(label1);
		
		final JPasswordField passText = new JPasswordField(20);
		passText.setBounds(170,150,165,25);
		frame.getContentPane().add(passText);
		
		JButton btnEnter = new JButton("Login");
		btnEnter.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\buttons\\login.png"));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag_admin;
				
				username = userText.getText();
				password = String.valueOf(passText.getPassword());
				 
				if(flag_admin = obj.existingAdmin(username) == true) {
					obj.loginAdmin(username, password);
					JOptionPane.showMessageDialog(null, "Welcome");
					frame.dispose();
					InsertPhoto.main(null);
				}
				else {
					JOptionPane.showMessageDialog(null, "Username or Password is wrong...");
				}
			}
		});
		btnEnter.setBounds(368,380,106,25);
		frame.getContentPane().add(btnEnter);
		
		JButton btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\buttons\\close.png"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnClose.setBounds(10,10,45,39);
		frame.getContentPane().add(btnClose);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passText.setText("");
			}
		});
		btnReset.setBounds(10,380,80,25);
		frame.getContentPane().add(btnReset);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\Images\\logoMain.png"));
		lblNewLabel.setBounds(0, 0, 484, 416);
		frame.getContentPane().add(lblNewLabel);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}