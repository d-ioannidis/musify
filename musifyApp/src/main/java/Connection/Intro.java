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
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.GRAY);
		frame.getContentPane().setForeground(Color.GRAY);
		frame.setBounds(100, 100, 450, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnUserLogin = new JButton("Log in as User");
		btnUserLogin.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\buttons\\login.png"));
		btnUserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                Login.main(null);
			}
		});
		btnUserLogin.setBounds(114, 116, 199, 44);
		frame.getContentPane().add(btnUserLogin);
		
		JButton btnAdminLogin = new JButton("Log in as Admin");
		btnAdminLogin.setIcon(new ImageIcon("C:\\Users\\KYVOS\\eclipse-workspace\\musify-main\\musifyApp\\src\\main\\java\\buttons\\login.png"));
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
                Admin.main(null);
			}
		});
		btnAdminLogin.setBounds(114, 186, 199, 44);
		frame.getContentPane().add(btnAdminLogin);
		
		JButton btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon(Intro.class.getResource("/buttons/close.png")));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setBounds(10, 292, 44, 44);
		frame.getContentPane().add(btnClose);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Intro.class.getResource("/Images/logoMain.png")));
		lblNewLabel_1.setBounds(0, 0, 434, 347);
		frame.getContentPane().add(lblNewLabel_1);
	}
}

