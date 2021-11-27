package Connection;

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
		frame.setBounds(100, 100, 450, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblWelcomeMessage = new JLabel("Welcome");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblWelcomeMessage = new GridBagConstraints();
		gbc_lblWelcomeMessage.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcomeMessage.gridx = 3;
		gbc_lblWelcomeMessage.gridy = 0;
		frame.getContentPane().add(lblWelcomeMessage, gbc_lblWelcomeMessage);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 5;
		gbc_panel_1.gridy = 0;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.gridx = 2;
		gbc_lblUsername.gridy = 1;
		frame.getContentPane().add(lblUsername, gbc_lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textFieldUsername = new GridBagConstraints();
		gbc_textFieldUsername.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUsername.gridx = 3;
		gbc_textFieldUsername.gridy = 1;
		frame.getContentPane().add(textFieldUsername, gbc_textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.gridx = 2;
		gbc_lblPassword.gridy = 2;
		frame.getContentPane().add(lblPassword, gbc_lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 3;
		gbc_passwordField.gridy = 2;
		frame.getContentPane().add(passwordField, gbc_passwordField);
		
		JLabel lblFirstname = new JLabel("Firstname: ");
		lblFirstname.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblFirstname = new GridBagConstraints();
		gbc_lblFirstname.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstname.anchor = GridBagConstraints.EAST;
		gbc_lblFirstname.gridx = 2;
		gbc_lblFirstname.gridy = 3;
		frame.getContentPane().add(lblFirstname, gbc_lblFirstname);
		
		textFieldFirstname = new JTextField();
		textFieldFirstname.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textFieldFirstname = new GridBagConstraints();
		gbc_textFieldFirstname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFirstname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFirstname.gridx = 3;
		gbc_textFieldFirstname.gridy = 3;
		frame.getContentPane().add(textFieldFirstname, gbc_textFieldFirstname);
		textFieldFirstname.setColumns(10);
		
		JLabel lblLastname = new JLabel("Lastname:");
		lblLastname.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblLastname = new GridBagConstraints();
		gbc_lblLastname.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastname.anchor = GridBagConstraints.EAST;
		gbc_lblLastname.gridx = 2;
		gbc_lblLastname.gridy = 4;
		frame.getContentPane().add(lblLastname, gbc_lblLastname);
		
		textFieldLastname = new JTextField();
		textFieldLastname.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textFieldLastname = new GridBagConstraints();
		gbc_textFieldLastname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLastname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLastname.gridx = 3;
		gbc_textFieldLastname.gridy = 4;
		frame.getContentPane().add(textFieldLastname, gbc_textFieldLastname);
		textFieldLastname.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 5;
		frame.getContentPane().add(lblEmail, gbc_lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textFieldEmail = new GridBagConstraints();
		gbc_textFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmail.gridx = 3;
		gbc_textFieldEmail.gridy = 5;
		frame.getContentPane().add(textFieldEmail, gbc_textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnClose = new JButton("Back");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login.main(null);
			}
		});
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 5, 5);
		gbc_btnClose.gridx = 2;
		gbc_btnClose.gridy = 10;
		frame.getContentPane().add(btnClose, gbc_btnClose);
		
		JButton btnRegister = new JButton("Register");
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
		
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegister.gridx = 3;
		gbc_btnRegister.gridy = 10;
		frame.getContentPane().add(btnRegister, gbc_btnRegister);
		
		JLabel lblLoginForm = new JLabel("Click here to Login instead");
		GridBagConstraints gbc_lblLoginForm = new GridBagConstraints();
		gbc_lblLoginForm.insets = new Insets(0, 0, 0, 5);
		gbc_lblLoginForm.gridx = 3;
		gbc_lblLoginForm.gridy = 11;
		frame.getContentPane().add(lblLoginForm, gbc_lblLoginForm);
	}
}