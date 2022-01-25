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
import java.awt.Color;
import javax.swing.ImageIcon;

public class Categories2 {
public static void main(String[] args) {
		
	//Frame
	
		final JFrame frame = new JFrame();	
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	// Label
		
		JLabel Categories = new JLabel("Categories");
		Categories.setFont(new Font("Tahoma", Font.BOLD, 20));
		Categories.setBounds(170,10,180,25);
		frame.getContentPane().add(Categories);
		
	// to koympi close	
		
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
		
		//to koumpi poy se odigei stin katigoria trap
		
		JButton btnTrap = new JButton("Trap");
		btnTrap.setBounds(170,60,110,25);
		btnTrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			Trap.main(FormMusify.getArgs());
			
			}
		});
		frame.getContentPane().add(btnTrap);

		//to koumpi poy se odigei stin katigoria HipHop
		
		JButton btnHip = new JButton("HipHop");
		btnHip.setBounds(170,110,110,25);
		btnHip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			HipHop.main(FormMusify.getArgs());
			
			}
		});
		frame.getContentPane().add(btnHip);

		//to koumpi poy se odigei stin katigoria rock
		
		JButton btnRock = new JButton("Rock");
		btnRock.setBounds(170,160,110,25);
		btnRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
		Rock.main(FormMusify.getArgs());
			
			}
		});
		frame.getContentPane().add(btnRock);

		//to koumpi poy se odigei stin katigoria pop
		
		JButton btnPop = new JButton("Pop");
		btnPop.setBounds(170,210,110,25);
		btnPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Pop.main(FormMusify.getArgs());
			
			}
		});
		frame.getContentPane().add(btnPop);

		//to koumpi poy se odigei stin katigoria Greek pop
		
		JButton btnGrPop = new JButton("Greek Pop");
		btnGrPop.setBounds(170,260,110,25);
		btnGrPop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GreekPop.main(FormMusify.getArgs());
			
			}
		});
		frame.getContentPane().add(btnGrPop);

		//button back
				JButton btnBack = new JButton("");
				btnBack.setIcon(new ImageIcon("C:\\Projects\\musifyApp\\src\\main\\java\\buttons\\back.png"));
				btnBack.setBounds(20, 277, 63, 47);
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						FormMusify.main(FormMusify.getArgs());
					}
				});
				btnBack.setFont(new Font("Dubai", Font.PLAIN, 14));
				frame.getContentPane().add(btnBack);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
}
}