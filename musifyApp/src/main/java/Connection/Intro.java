package Connection;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Intro {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Intro window = new Intro();
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
	public Intro() {
		initialize();
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBackground(Color.GRAY);
		frame.getContentPane().setForeground(Color.GRAY);
		frame.setBounds(100, 100, 450, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnUserLogin = new JButton("Log in as User");
		btnUserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                Login.main(null);
			}
		});
		btnUserLogin.setBounds(114, 159, 199, 44);
		frame.getContentPane().add(btnUserLogin);
		
		JButton btnAdminLogin = new JButton("Log in as Admin");
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                Admin.main(null);
			}
		});
		btnAdminLogin.setBounds(114, 235, 199, 44);
		frame.getContentPane().add(btnAdminLogin);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Intro.class.getResource("/Images/musifyLogoNew.png")));
		lblNewLabel.setBounds(-47, 11, 425, 107);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon(Intro.class.getResource("/buttons/close.png")));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setBounds(10, 292, 59, 44);
		frame.getContentPane().add(btnClose);
	}
}
