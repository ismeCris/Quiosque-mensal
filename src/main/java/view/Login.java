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
import java.awt.Font;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtnome;
    private JTextField txtSenha;
    private FuncionariosRepository funcionariosRepository;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
            new Login().setVisible(true);
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
        contentPane.setBackground(new Color(0x82A69C));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel_1_2 = new JLabel("Login");
        lblNewLabel_1_2.setFont(new Font("Constantia", Font.PLAIN, 28));
        lblNewLabel_1_2.setBounds(294, 100, 136, 64);
        contentPane.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\Tablet login-cuate (1).png"));
        lblNewLabel.setBounds(551, 133, 483, 416);
        contentPane.add(lblNewLabel);

     // Crie um TransparentPanel com bordas arredondadas e transparência
        TransparentTextField panel = new TransparentTextField(0.8f); // 50% de transparência
        panel.setBackground(new Color(0xDBDDB6)); // Cor de fundo #F3EFDE
        panel.setBounds(176, 79, 695, 421);
        contentPane.add(panel);
        panel.setLayout(null);
        panel.setBorder(null); // Remove a borda do JPanel
        panel.setOpaque(false); // Torna o fundo do JPanel transparente
        
        JLabel lbNome = new JLabel("Nome:");
        lbNome.setBounds(67, 102, 46, 14);
        panel.add(lbNome);
        
        txtnome = new JTextField();
        txtnome.setForeground(new Color(0, 0, 0));
        txtnome.setBounds(67, 127, 268, 34);
        panel.add(txtnome);
        txtnome.setBackground(new Color(237, 228, 228));
        txtnome.setColumns(10);

    
        JLabel lbSenha = new JLabel("Senha:");
        lbSenha.setBounds(67, 201, 46, 14);
        panel.add(lbSenha);
        
        txtSenha = new JTextField();
        txtSenha.setBounds(67, 218, 268, 34);
        panel.add(txtSenha);
        txtSenha.setColumns(10);
        txtSenha.setBackground(new Color(237, 228, 228));

       

        JButton btnNewButton = new JButton("Entrar");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBounds(116, 303, 157, 34);
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtnome.getText();
                String senha = txtSenha.getText();

                FuncionariosEntity funcionario = funcionariosRepository.login(nome, senha);

                if (funcionario != null) {
                    new MenuPrincipal().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Nome ou senha inválidos.");
                    limparCampos();
                }
            }
        });
        btnNewButton.setBackground(new Color(33, 65, 65));
    }

    
    private void limparCampos() {
    	txtnome.setText("");
    	txtSenha.setText("");
    }
}
