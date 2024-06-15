package view;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class MenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel criarUsuarioPanel;
    private JTextField txtNome;
    private JTextField txtEmail;
    private CardLayout cardLayout;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
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
    public MenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 586);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(215, 235, 255));
        setJMenuBar(menuBar);
        
        JMenu mnPrincipal = new JMenu("Menu Principal");
        menuBar.add(mnPrincipal);
        
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Menu");
        mnPrincipal.add(mntmNewMenuItem_3);
        
        JMenu mnFuncionario = new JMenu("Gerenciar Funcionarios");
        menuBar.add(mnFuncionario);
        
        JMenuItem miCriarUsuario = new JMenuItem("Criar Usuário");
        mnFuncionario.add(miCriarUsuario);
        miCriarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainelCriarUsuario();
            }
        });
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Listar");
        mnFuncionario.add(mntmNewMenuItem_1);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("excluir");
        mnFuncionario.add(mntmNewMenuItem);
        
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Editar");
        mnFuncionario.add(mntmNewMenuItem_2);
        
        JMenu mnQuiosque = new JMenu("Gerenciar Quiosques");
        menuBar.add(mnQuiosque);
        
        JMenu mnReservas = new JMenu("Gerenciar Reservas");
        menuBar.add(mnReservas);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        setContentPane(contentPane);
        
        JPanel defaultPanel = new JPanel();
        contentPane.add(defaultPanel, "default");

        criarUsuarioPanel = new JPanel();
        criarUsuarioPanel.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(289, 57, 80, 14);
        criarUsuarioPanel.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(354, 54, 200, 20);
        criarUsuarioPanel.add(txtNome);
        txtNome.setColumns(10);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(289, 145, 80, 14);
        criarUsuarioPanel.add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setBounds(354, 98, 200, 20);
        criarUsuarioPanel.add(txtEmail);
        txtEmail.setColumns(10);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(382, 424, 100, 23);
        criarUsuarioPanel.add(btnSalvar);
        
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarUsuario();
            }
        });

        contentPane.add(criarUsuarioPanel, "criarUsuarioPanel");
        
        JLabel lblSenha = new JLabel("senha");
        lblSenha.setBounds(289, 101, 80, 14);
        criarUsuarioPanel.add(lblSenha);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(354, 142, 200, 20);
        criarUsuarioPanel.add(textField);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setToolTipText("");
        comboBox.setBounds(354, 246, 205, 22);
        criarUsuarioPanel.add(comboBox);
        
        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setBounds(289, 250, 80, 14);
        criarUsuarioPanel.add(lblCargo);
        
        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(289, 195, 80, 14);
        criarUsuarioPanel.add(lblTelefone);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(354, 192, 200, 20);
        criarUsuarioPanel.add(textField_1);
    }

    /**
     * Método para mostrar o painel de criação de usuário.
     */
    protected void mostrarPainelCriarUsuario() {
        cardLayout.show(contentPane, "criarUsuarioPanel");
    }

    /**
     * Método para salvar o usuário.
     */
    protected void salvarUsuario() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        // Implementar a lógica de salvar usuário
        JOptionPane.showMessageDialog(this, "Usuário " + nome + " com email " + email + " criado com sucesso!");
        txtNome.setText("");
        txtEmail.setText("");
        cardLayout.show(contentPane, "default");
    }
}
