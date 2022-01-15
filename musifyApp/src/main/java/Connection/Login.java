package Connection;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.mysql.cj.xdevapi.Result;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;


public class Login implements KeyListener {
	
	Connection con = null;
	PreparedStatement pst =null;
	Result rs =null;	

	private JFrame frame;
	//private JPanel contentPane;
	private JTextField textFieldUserN;

	private JPasswordField passwordField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Login() {
		initialize();
		frame.setLocationRelativeTo(null);
		}

	private void initialize(){
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 520, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		JLabel lbLogin = new JLabel("Login");
		lbLogin.setForeground(Color.WHITE);
		lbLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbLogin.setBounds(192, 11, 122, 31);
		frame.getContentPane().add(lbLogin);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(33, 119, 87, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPass = new JLabel("Password :");
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPass.setBounds(33, 166, 87, 14);
		frame.getContentPane().add(lblPass);
		
		textFieldUserN = new JTextField();
		textFieldUserN.setBounds(150, 118, 155, 20);
		frame.getContentPane().add(textFieldUserN);
		textFieldUserN.setColumns(10);
		
		JButton btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\buttons\\close.png"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnClose.setBounds(10,11,43,39);
		frame.getContentPane().add(btnClose);
		
		JButton btnForget = new JButton("Forgot your password?");
		btnForget.setBounds(163, 461, 167, 23);
		frame.getContentPane().add(btnForget);

		
		JButton btnEnter = new JButton("Login");
		btnEnter.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\buttons\\login.png"));
		frame.getRootPane().setDefaultButton(btnEnter);
		
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try {
					
					//Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
					
					String username = textFieldUserN.getText();
					String password = String.valueOf(passwordField.getPassword());
					
					
					Statement stm = con.createStatement();
					
					String sql = "select ID, NAME, SURNAME, USERNAME, PASSWORD from register where USERNAME='"+username+"' and PASSWORD='"+password+"'";
					ResultSet rs = stm.executeQuery(sql);
					
					
					if(((ResultSet) rs).next()) {						
						JOptionPane.showMessageDialog(null, "Welcome");
						frame.dispose();
						String[] user = {rs.getString("ID"), rs.getString("NAME"), rs.getString("SURNAME")};
						FormMusify.main(user);
					}else {
						JOptionPane.showMessageDialog(null, "username or password is wrong...");
						textFieldUserN.setText("");
						passwordField.setText("");
					}
					
					con.close();
					
					
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
			}
		});
		btnEnter.setBounds(388, 461, 99, 23);
		frame.getContentPane().add(btnEnter);
		
		JButton btnNewUser = new JButton("Register");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Register.main(null);
			}
		});
		btnNewUser.setBounds(398, 11, 89, 23);
		frame.getContentPane().add(btnNewUser);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUserN.setText("");
				passwordField.setText("");
			}
		});
		btnReset.setBounds(10, 461, 89, 23);
		frame.getContentPane().add(btnReset);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 165, 155, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\Images\\logoMain.png"));
		lblNewLabel.setBounds(0, 0, 504, 495);
		frame.getContentPane().add(lblNewLabel);
		
        btnForget.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                FormForgot.main(null);
            }
        });
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
		try {
			
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
			
			String username = textFieldUserN.getText();
			String password = String.valueOf(passwordField.getPassword());
			
			
			Statement stm = con.createStatement();
			
			String sql = "select ID, NAME, SURNAME, USERNAME, PASSWORD from register where USERNAME='"+username+"' and PASSWORD='"+password+"'";
			ResultSet rs = stm.executeQuery(sql);
			
			
			if(((ResultSet) rs).next()) {						
				JOptionPane.showMessageDialog(null, "Welcome");
				frame.dispose();
				String[] user = {rs.getString("ID"), rs.getString("NAME"), rs.getString("SURNAME")};
				FormMusify.main(user);
			}else {
				JOptionPane.showMessageDialog(null, "username or password is wrong...");
				textFieldUserN.setText("");
				passwordField.setText("");
			}
			
			con.close();
			
			
		}catch(Exception e1) {
			System.out.println(e1.getMessage());
		}
		
	}
	}
	
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
