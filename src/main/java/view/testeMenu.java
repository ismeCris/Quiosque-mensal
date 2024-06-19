package view;
import com.formdev.flatlaf.FlatLightLaf;

import controller.FuncionarioController;
import model.entities.FuncionariosEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testeMenu extends JFrame {
	EntityManager em = Persistence.   createEntityManagerFactory("bancoQuiosque").createEntityManager();
    private FuncionarioController funcionarioController;

    private static final long serialVersionUID = 1L;
    
    public static void main(String[] args) {
        // Aplicar o look-and-feel FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        // Executar a aplicação
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new testeMenu().setVisible(true);
            }
        });
    }

    public testeMenu() {
        // Configurações da janela principal
        setTitle("Exemplo de Menu Lateral com Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Painel do menu lateral
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.LIGHT_GRAY);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botões do menu lateral
        JButton btn1 = createMenuButton("Opção 1");
        JButton btn2 = createMenuButton("Opção 2");
        JButton btn3 = createMenuButton("Opção 3");

        // Adicionando os botões ao menu
        menuPanel.add(btn1);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre botões
        menuPanel.add(btn2);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçamento entre botões
        menuPanel.add(btn3);

        // Painel principal onde o conteúdo será exibido
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());
        contentPanel.setBackground(Color.WHITE);

        // Adicionando painéis de conteúdo para cada opção
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Cadastrar Funcionario"));
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Conteúdo da Opção 2"));
        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Conteúdo da Opção 3"));

        contentPanel.add(panel1, "1");
        contentPanel.add(panel2, "2");
        contentPanel.add(panel3, "3");

        // Ações dos botões para trocar o conteúdo
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "1");
                // Chama o método para criar o painel de cadastro de usuário apenas quando necessário
                if (contentPanel.getComponentCount() == 3) { // Verifica se o painel já está adicionado
                    criarUsuarioPanel(contentPanel);
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "2");
            }
        });

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "3");
            }
        });

        // Adicionando o menu lateral e o painel de conteúdo ao painel principal
        mainPanel.add(menuPanel, BorderLayout.WEST);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Adicionando o painel principal à janela
        getContentPane().add(mainPanel);
    }

    // Método para criar botões de menu lateral
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setPreferredSize(new Dimension(200, 30)); // Define o tamanho preferido dos botões
        button.setMaximumSize(new Dimension(200, 30));  // Define o tamanho máximo dos botões
        return button;
    }

    // Método para configurar o painel de cadastro de usuário
 // Método para configurar o painel de cadastro de usuário
    private void criarUsuarioPanel(JPanel contentPanel) {
        JPanel criarUsuarioPanel = new JPanel();
        criarUsuarioPanel.setBackground(new Color(183, 219, 219));
        criarUsuarioPanel.setLayout(null); // Usando layout absoluto para posicionar os componentes

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(50, 50, 80, 14);
        criarUsuarioPanel.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(140, 47, 200, 20);
        criarUsuarioPanel.add(txtNome);
        txtNome.setColumns(10);

        // Adicione os outros campos (Email, Senha, Telefone, etc.) da mesma maneira...

        // Botão Salvar
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(250, 200, 100, 30);
        criarUsuarioPanel.add(btnSalvar);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText();
                // Obtenha os outros campos da mesma maneira...

                FuncionariosEntity novoFuncionario = new FuncionariosEntity();
                novoFuncionario.setNome(nome);
                // Preencha o novo funcionário com os dados dos campos

                try {
                    funcionarioController.createFuncionario(novoFuncionario);
                    JOptionPane.showMessageDialog(criarUsuarioPanel, "Novo usuário criado com sucesso.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(criarUsuarioPanel, "Erro ao criar novo usuário: " + ex.getMessage());
                }
            }
        });

        // Botão Limpar Campos
        JButton btnLimparCampos = new JButton("Limpar Campos");
        btnLimparCampos.setBounds(100, 200, 120, 30);
        criarUsuarioPanel.add(btnLimparCampos);
        btnLimparCampos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtNome.setText("");
                // Limpe os outros campos da mesma maneira...
            }
        });

        JLabel lblNewLabel = new JLabel("Cadastrar Novo Usuario");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(100, 10, 239, 14);
        criarUsuarioPanel.add(lblNewLabel);

        // Adicionando o painel ao contentPanel com CardLayout
        contentPanel.add(criarUsuarioPanel, "criarUsuarioPanel");
    }

    
}

