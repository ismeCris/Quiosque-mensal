package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 624);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSenha_1 = new JLabel(",");
		lblSenha_1.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\ac4d5ef6eb4cb1d27665bc206d8d43e3.jpg"));
		lblSenha_1.setBounds(512, -63, 539, 685);
		contentPane.add(lblSenha_1);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setBackground(new Color(248, 208, 73));
		btnNewButton.setBounds(138, 415, 157, 34);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBackground(new Color(119, 187, 255));
		textField.setBounds(88, 216, 268, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\image-removebg-preview (46) (4).png"));
		lblNewLabel.setBounds(168, 11, 138, 142);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(119, 187, 255));
		textField_1.setBounds(88, 295, 268, 34);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(87, 196, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Senha");
		lblNewLabel_1_1.setBounds(88, 272, 46, 14);
		contentPane.add(lblNewLabel_1_1);
	}
}
