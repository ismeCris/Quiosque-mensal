package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import model.entities.FuncionariosEntity;
import model.repositories.FuncionariosRepository;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private FuncionariosRepository funcionariosRepository;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	 try {
             UIManager.setLookAndFeel(new FlatLightLaf());
         } catch (UnsupportedLookAndFeelException ex) {
             ex.printStackTrace();
         }

         // Executar a aplicação
         SwingUtilities.invokeLater(new Runnable() {
             public void run() {
                 new Login().setVisible(true);
             }
         });
    }

    /**
     * Create the frame.
     */
    public Login() {

        funcionariosRepository = new FuncionariosRepository();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1050, 624);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(179, 217, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(266, 57, 414, 448);
        contentPane.add(panel);
        panel.setLayout(null);
        
                JLabel lblNewLabel = new JLabel("");
                lblNewLabel.setBounds(123, 0, 138, 142);
                panel.add(lblNewLabel);
                lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\recorrer (3).png"));
                
                        textField = new JTextField();
                        textField.setBounds(67, 189, 268, 34);
                        panel.add(textField);
                        textField.setBackground(new Color(119, 187, 255));
                        textField.setColumns(10);
                        
                                JLabel lblNewLabel_1 = new JLabel("Nome:");
                                lblNewLabel_1.setBounds(67, 160, 46, 14);
                                panel.add(lblNewLabel_1);
                                
                                        textField_1 = new JTextField();
                                        textField_1.setBounds(67, 275, 268, 34);
                                        panel.add(textField_1);
                                        textField_1.setColumns(10);
                                        textField_1.setBackground(new Color(119, 187, 255));
                                        
                                                JLabel lblNewLabel_1_1 = new JLabel("Senha");
                                                lblNewLabel_1_1.setBounds(67, 250, 46, 14);
                                                panel.add(lblNewLabel_1_1);
                                                
                                                        JButton btnNewButton = new JButton("Entrar");
                                                        btnNewButton.setBounds(138, 377, 157, 34);
                                                        panel.add(btnNewButton);
                                                        btnNewButton.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent e) {
                                                                String nome = textField.getText();
                                                                String senha = textField_1.getText(); // Corrected to get text from textField_1

                                                                FuncionariosEntity funcionario = funcionariosRepository.login(nome, senha);

                                                                if (funcionario != null) {
                                                                    new MenuPrincipal().setVisible(true);
                                                                    dispose(); // fecha o login 
                                                                } else {
                                                                    JOptionPane.showMessageDialog(null, "Nome ou senha Invalidos.");
                                                                    limparCampos();
                                                                }
                                                            }
                                                        });
                                                        btnNewButton.setBackground(new Color(248, 208, 73));
    }
    
    private void limparCampos() {
    	textField.setText("");
    	textField_1.setText("");
    }
}
