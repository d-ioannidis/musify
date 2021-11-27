package Connection;

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

public class Admin {

	public static void main(String[] args) {
		
		final JFrame frame = new JFrame();	
		frame.setBounds(100, 100, 500, 317);
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
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {
					
					//Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
					
					String username = userText.getText();
					String password = String.valueOf(passText.getPassword());
					
					
					Statement stm = con.createStatement();
					
					String sql = "select USERNAME, PASSWORD from admin where USERNAME='"+username+"' and PASSWORD='"+password+"'";
					ResultSet rs = stm.executeQuery(sql);
					
					
					if(((ResultSet) rs).next()) {
						JOptionPane.showMessageDialog(null, "Welcome");
						frame.dispose();
						InsertDataArtist.main(null);
					}else {
						JOptionPane.showMessageDialog(null, "username or password is wrong...");
						userText.setText("");
						passText.setText("");
					}
					
					con.close();
					
					
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
			}
		});
		btnEnter.setBounds(390,230,80,25);
		frame.getContentPane().add(btnEnter);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnClose.setBounds(10,10,80,25);
		frame.getContentPane().add(btnClose);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passText.setText("");
			}
		});
		btnReset.setBounds(10,230,80,25);
		frame.getContentPane().add(btnReset);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
