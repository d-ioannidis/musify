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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;


public class Login{
	
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
	}

	private void initialize(){
		frame = new JFrame();
		frame.setBounds(100, 100, 520, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbLogin = new JLabel("Login");
		lbLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbLogin.setBounds(243, 21, 122, 31);
		frame.getContentPane().add(lbLogin);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(33, 119, 87, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPass = new JLabel("Password :");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPass.setBounds(33, 166, 87, 14);
		frame.getContentPane().add(lblPass);
		
		textFieldUserN = new JTextField();
		textFieldUserN.setBounds(150, 118, 155, 20);
		frame.getContentPane().add(textFieldUserN);
		textFieldUserN.setColumns(10);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		btnClose.setBounds(21,0,89,23);
		frame.getContentPane().add(btnClose);
		
		JButton btnForget = new JButton("Forgot your password?");
		btnForget.setBounds(178, 302, 167, 23);
		frame.getContentPane().add(btnForget);

		
		JButton btnEnter = new JButton("Login");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try {
					
					//Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Sarap4610_Kof4665_Ioan4578_Alex4631");
					
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
		btnEnter.setBounds(408, 302, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		JButton btnNewUser = new JButton("Register");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Register.main(null);
			}
		});
		btnNewUser.setBounds(398, 0, 89, 23);
		frame.getContentPane().add(btnNewUser);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUserN.setText("");
				passwordField.setText("");
			}
		});
		btnReset.setBounds(10, 302, 89, 23);
		frame.getContentPane().add(btnReset);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 165, 155, 20);
		frame.getContentPane().add(passwordField);
		
        btnForget.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                FormForgot.main(null);
            }
        });
	}
}

