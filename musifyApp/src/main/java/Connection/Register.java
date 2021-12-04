import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class Register {

	private JFrame frame;
	private JTextField textFieldUsername;
	private JTextField textFieldFirstname;
	private JTextField textFieldLastname;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private Database obj = new Database();
	
	private String[] specialCharactersArray = {"@","*","(",")","[","]","/",";",":","|","{","}","-","_","^","&","%"};
	private String[] specialCharactersArrayEmail = {"*","(",")","[","]","/",";",":","|","{","}","-","_","^","&","%"};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void getData() {
		username = textFieldUsername.getText();
		password = String.valueOf(passwordField.getPassword());
		firstname = textFieldFirstname.getText();
		lastname = textFieldLastname.getText();
		email = textFieldEmail.getText();
	}
	
	public Boolean checkBlanks() {
		Boolean flag_blanks = true;
		
		if (textFieldUsername.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: fields cannot be blank!");
			flag_blanks = false;
		} else if (passwordField.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Error: fields cannot be blank!");
			flag_blanks = false;
		} else if (textFieldFirstname.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: fields cannot be blank!");
			flag_blanks = false;
		} else if (textFieldLastname.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: fields cannot be blank!");
			flag_blanks = false;
		} else if (textFieldEmail.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Error: fields cannot be blank!");
			flag_blanks = false;
		}
		return flag_blanks; 
	}
	
	public Boolean checkForSpecialCharacters() {
		Boolean flag = true;
		
		for (int i = 0; i < specialCharactersArray.length; i++) {
			if (textFieldUsername.getText().contains(specialCharactersArray[i])) {
				flag = false;
			} else if (String.valueOf(passwordField.getPassword()).contains(specialCharactersArray[i])) {
				flag = false;
			} else if (textFieldFirstname.getText().contains(specialCharactersArray[i])) {
				flag = false;
			} else if (textFieldLastname.getText().contains(specialCharactersArray[i])) {
				flag = false;
			}
		}
		
		for (int i = 0; i < specialCharactersArrayEmail.length; i++) {
			if (textFieldEmail.getText().contains(specialCharactersArrayEmail[i])) {
				flag = false;
			} 
		}
		
		if (flag == false) {
			JOptionPane.showMessageDialog(null, "Error: fields cannot contain special characters!");
		} 
		return flag;
	}

	/**
	 * Create the application.
	 */
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 450, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeMessage = new JLabel("Welcome");
		lblWelcomeMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWelcomeMessage.setBounds(156, 8, 125, 22);
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblWelcomeMessage);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(96, 38, 55, 14);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(156, 35, 125, 20);
		textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(98, 63, 53, 14);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 60, 125, 20);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(passwordField);
		
		JLabel lblFirstname = new JLabel("Firstname: ");
		lblFirstname.setBounds(97, 88, 54, 14);
		lblFirstname.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblFirstname);
		
		textFieldFirstname = new JTextField();
		textFieldFirstname.setBounds(156, 85, 125, 20);
		textFieldFirstname.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(textFieldFirstname);
		textFieldFirstname.setColumns(10);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setBounds(101, 113, 50, 14);
		lblLastname.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblLastname);
		
		textFieldLastname = new JTextField();
		textFieldLastname.setBounds(156, 110, 125, 20);
		textFieldLastname.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(textFieldLastname);
		textFieldLastname.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(120, 138, 31, 14);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(156, 135, 125, 20);
		textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musifyApp\\musify\\musifyApp\\src\\main\\java\\buttons\\back.png"));
		btnClose.setBounds(10, 266, 43, 30);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		frame.getContentPane().add(btnClose);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(334, 266, 90, 30);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean flag_search, flag_blanks, flag_specialChar;
				
				flag_blanks = checkBlanks(); //Check if textfields are empty
				flag_specialChar = checkForSpecialCharacters(); //Check if textfields contain special characters (email field can still use @)
				getData(); //Save data from textfields to variables
				
				textFieldUsername.setText("");
				textFieldFirstname.setText("");
				textFieldLastname.setText("");
				textFieldEmail.setText("");
				passwordField.setText("");
				
				if (flag_blanks == true && flag_specialChar == true) {
					flag_search = obj.searchData(username, email);
					if (flag_search == true) {
						obj.addData(email, firstname, lastname, username, password); //Save data from variables to the database
						JOptionPane.showMessageDialog(null, "Registration successful.");
					} else {
						JOptionPane.showMessageDialog(null, "Error: User already exists!");
					}
				} 
			}
		});
		frame.getContentPane().add(btnRegister);
		
		JLabel lblLoginForm = new JLabel("Click here to Login instead");
		lblLoginForm.setBounds(119, 300, 205, 14);
		frame.getContentPane().add(lblLoginForm);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musifyApp\\musify\\musifyApp\\src\\main\\java\\Images\\logoMain.png"));
		lblNewLabel.setBounds(0, 0, 434, 325);
		frame.getContentPane().add(lblNewLabel);
	}
}
