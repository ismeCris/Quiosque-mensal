package view;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.FuncionarioController;
import model.entities.FuncionariosEntity;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ImageIcon;



public class MenuPrincipal extends JFrame {
	 private FuncionarioController funcionarioController;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtSenha;
    private JTextField txtTelefone;
    private JTable table;
    private JTextField textField;

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
    	setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 586);
        funcionarioController = new FuncionarioController();

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(215, 235, 255));
        setJMenuBar(menuBar);

        // Add "Home" button to the menu bar
        JButton btnHome = new JButton("Inicio");
        menuBar.add(btnHome);
        btnHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("homePanel");
            }
        });

        JMenu mnFuncionario = new JMenu("Gerenciar Funcionarios");
        menuBar.add(mnFuncionario);

        JMenuItem miCriarUsuario = new JMenuItem("Criar Usuário");
        mnFuncionario.add(miCriarUsuario);
        miCriarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("criarUsuarioPanel");
            }
        });

        JMenuItem miListarUsuario = new JMenuItem("Buscar");
        mnFuncionario.add(miListarUsuario);
        miListarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("listarUsuarioPanel");
            }
        });

        JMenuItem miExcluirUsuario = new JMenuItem("Excluir");
        mnFuncionario.add(miExcluirUsuario);
        miExcluirUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("excluirUsuarioPanel");
            }
        });

        JMenuItem miEditarUsuario = new JMenuItem("Editar");
        mnFuncionario.add(miEditarUsuario);
        miEditarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("editarUsuarioPanel");
            }
        });

        JMenu mnQuiosque = new JMenu("Gerenciar Quiosques");
        menuBar.add(mnQuiosque);

        JMenuItem miCriarQuiosque = new JMenuItem("Criar");
        mnQuiosque.add(miCriarQuiosque);
        miCriarQuiosque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("criarQuiosquePanel");
            }
        });

        JMenuItem miEditarQuiosque = new JMenuItem("Editar");
        mnQuiosque.add(miEditarQuiosque);
        miEditarQuiosque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("editarQuiosquePanel");
            }
        });

        JMenuItem miBuscarQuiosque = new JMenuItem("Buscar");
        mnQuiosque.add(miBuscarQuiosque);
        miBuscarQuiosque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("buscarQuiosquePanel");
            }
        });

        JMenuItem miExcluirQuiosque = new JMenuItem("Excluir");
        mnQuiosque.add(miExcluirQuiosque);
        miExcluirQuiosque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("excluirQuiosquePanel");
            }
        });

        JMenu mnReservas = new JMenu("Gerenciar Reservas");
        menuBar.add(mnReservas);

        JMenuItem miFazerReserva = new JMenuItem("Fazer reserva");
        mnReservas.add(miFazerReserva);
        miFazerReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("fazerReservaPanel");
            }
        });

        JMenuItem miListarReserva = new JMenuItem("Listar");
        mnReservas.add(miListarReserva);
        miListarReserva.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 listarUsuarioPanel();
            	  cardLayout.show(contentPane, "listarUsuarioPanel");
            }
        });

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);
        setContentPane(contentPane);

        homePanel(); // Initialize Home Panel

        criarUsuarioPanel();
        listarUsuarioPanel();
        excluirUsuarioPanel();
        editarUsuarioPanel();
        criarQuiosquePanel();
        editarQuiosquePanel();
        buscarQuiosquePanel();
        excluirQuiosquePanel();
        fazerReservaPanel();
        listarReservaPanel();

        cardLayout.show(contentPane, "homePanel"); 
    }

    private void mostrarPainel(String panelName) {
        cardLayout.show(contentPane, panelName);
    }

    private void homePanel() {
        JPanel homePanel = new JPanel();
        homePanel.setLayout(null);

        JLabel lblWelcome = new JLabel("Bem-vindo ");
        lblWelcome.setFont(new Font("SansSerif", Font.PLAIN, 56));
        lblWelcome.setBounds(325, 78, 300, 68);
        homePanel.add(lblWelcome);

        contentPane.add(homePanel, "homePanel");
        
        JLabel lblNewLabel_1 = new JLabel(",");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\106bf080d95bae41fa7cb0ae72ecddd0 (1).jpg"));
        lblNewLabel_1.setBounds(10, 43, 969, 539);
        homePanel.add(lblNewLabel_1);
    }

    private void criarUsuarioPanel() {
        JPanel criarUsuarioPanel = new JPanel();
        criarUsuarioPanel.setBackground(new Color(255, 255, 255));
        criarUsuarioPanel.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(280, 150, 80, 14);
        criarUsuarioPanel.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(370, 147, 200, 20);
        criarUsuarioPanel.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(280, 192, 80, 14);
        criarUsuarioPanel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(370, 189, 200, 20);
        criarUsuarioPanel.add(txtEmail);
        txtEmail.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(280, 229, 80, 14);
        criarUsuarioPanel.add(lblSenha);

        txtSenha = new JTextField();
        txtSenha.setBounds(370, 226, 200, 20);
        criarUsuarioPanel.add(txtSenha);
        txtSenha.setColumns(10);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(280, 271, 80, 14);
        criarUsuarioPanel.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(370, 268, 200, 20);
        criarUsuarioPanel.add(txtTelefone);
        txtTelefone.setColumns(10);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(280, 323, 80, 14);
        criarUsuarioPanel.add(lblCargo);

        JComboBox<String> cbCargo = new JComboBox<>();
        cbCargo.setBounds(370, 319, 200, 22);
        criarUsuarioPanel.add(cbCargo);

        RoundButton btnSalvar = new RoundButton("Salvar", 20); 
        btnSalvar.setBackground(new Color(196, 225, 225));
        btnSalvar.setBounds(454, 423, 100, 30); 
        criarUsuarioPanel.add(btnSalvar);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salvarUsuario();
            }
        });

        contentPane.add(criarUsuarioPanel, "criarUsuarioPanel");
        
        RoundButton btnLimparCampos = new RoundButton("Limpar Campos", 20);
        btnLimparCampos.setBackground(new Color(196, 225, 255));
        btnLimparCampos.setBounds(300, 423, 100, 30); 
        criarUsuarioPanel.add(btnLimparCampos);
        btnLimparCampos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    
        
        JLabel lblNewLabel = new JLabel("Cadastrar Novo Usuario");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(338, 54, 239, 14);
        criarUsuarioPanel.add(lblNewLabel);
    }

    protected void limparCampos() {
        txtNome.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtTelefone.setText("");
    }
  
    private void listarUsuarioPanel() {
        JPanel listarUsuarioPanel = new JPanel();
        listarUsuarioPanel.setLayout(null);

        JLabel lblListarUsuarios = new JLabel("Lista de Usuários");
        lblListarUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblListarUsuarios.setBounds(343, 11, 200, 20);
        listarUsuarioPanel.add(lblListarUsuarios);

        // Criar o modelo da tabela com colunas definidas
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");

        // Preencher a tabela com os dados dos usuários
        List<FuncionariosEntity> usuarios = funcionarioController.findAll();
        if (usuarios != null) {
            for (FuncionariosEntity usuario : usuarios) {
                Object[] rowData = {usuario.getId(), usuario.getNome()};
                tableModel.addRow(rowData);
            }
        }

        // Criar a tabela com o modelo criado
        JTable table = new JTable(tableModel);

        // Configurar tamanho e posição da tabela dentro do JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 85, 880, 300);
        listarUsuarioPanel.add(scrollPane);

        JTextField txtId = new JTextField();
        txtId.setBounds(50, 50, 620, 23);
        listarUsuarioPanel.add(txtId);
        txtId.setColumns(10);

        // Botão para buscar por ID
        JButton btnBuscarPorId = new JButton("Buscar por ID");
        btnBuscarPorId.setBounds(680, 50, 120, 23);
        listarUsuarioPanel.add(btnBuscarPorId);
        btnBuscarPorId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ação ao clicar no botão buscar
                String idStr = txtId.getText().trim();
                if (!idStr.isEmpty()) {
                    try {
                        Long id = Long.parseLong(idStr);
                        FuncionariosEntity usuario = funcionarioController.findFuncionarioById(id);
                        if (usuario != null) {
                            // Limpar a tabela antes de adicionar o resultado da busca
                            tableModel.setRowCount(0);
                            // Adicionar o usuário encontrado à tabela
                            Object[] rowData = {usuario.getId(), usuario.getNome()};
                            tableModel.addRow(rowData);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado com o ID informado.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID deve ser um número válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, informe um ID para buscar.");
                }
            }
        });


        JButton btnListarTodos = new JButton("Listar Todos");
        btnListarTodos.setBounds(810, 50, 120, 23);
        listarUsuarioPanel.add(btnListarTodos);
        btnListarTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                List<FuncionariosEntity> usuarios = funcionarioController.findAll();
                if (usuarios != null) {
                    for (FuncionariosEntity usuario : usuarios) {
                        Object[] rowData = {usuario.getId(), usuario.getNome()};
                        tableModel.addRow(rowData);
                    }
                }
            }
        });

        // Adicionar o painel ao contentPane
        contentPane.add(listarUsuarioPanel, "listarUsuarioPanel");
    }


    private void excluirUsuarioPanel() {
        JPanel excluirUsuarioPanel = new JPanel();
        excluirUsuarioPanel.setLayout(null);

        JLabel lblExcluirUsuario = new JLabel("Excluir Usuario");
        lblExcluirUsuario.setBounds(289, 57, 200, 14);
        excluirUsuarioPanel.add(lblExcluirUsuario);

        contentPane.add(excluirUsuarioPanel, "excluirUsuarioPanel");
    }

    private void editarUsuarioPanel() {
        JPanel editarUsuarioPanel = new JPanel();
        editarUsuarioPanel.setLayout(null);

        JLabel lblEditarUsuario = new JLabel("Editar Usuario");
        lblEditarUsuario.setBounds(289, 57, 200, 14);
        editarUsuarioPanel.add(lblEditarUsuario);

        contentPane.add(editarUsuarioPanel, "editarUsuarioPanel");
    }

    private void criarQuiosquePanel() {
        JPanel criarQuiosquePanel = new JPanel();
        criarQuiosquePanel.setLayout(null);

        JLabel lblCriarQuiosque = new JLabel("Criar Quiosque");
        lblCriarQuiosque.setBounds(289, 57, 200, 14);
        criarQuiosquePanel.add(lblCriarQuiosque);

        contentPane.add(criarQuiosquePanel, "criarQuiosquePanel");
    }

    private void editarQuiosquePanel() {
        JPanel editarQuiosquePanel = new JPanel();
        editarQuiosquePanel.setLayout(null);

        JLabel lblEditarQuiosque = new JLabel("Editar Quiosque");
        lblEditarQuiosque.setBounds(289, 57, 200, 14);
        editarQuiosquePanel.add(lblEditarQuiosque);

        contentPane.add(editarQuiosquePanel, "editarQuiosquePanel");
    }

    private void buscarQuiosquePanel() {
        JPanel buscarQuiosquePanel = new JPanel();
        buscarQuiosquePanel.setLayout(null);

        JLabel lblBuscarQuiosque = new JLabel("Buscar Quiosque");
        lblBuscarQuiosque.setBounds(289, 57, 200, 14);
        buscarQuiosquePanel.add(lblBuscarQuiosque);


        contentPane.add(buscarQuiosquePanel, "buscarQuiosquePanel");
    }

    private void excluirQuiosquePanel() {
        JPanel excluirQuiosquePanel = new JPanel();
        excluirQuiosquePanel.setLayout(null);

        JLabel lblExcluirQuiosque = new JLabel("Excluir Quiosque");
        lblExcluirQuiosque.setBounds(289, 57, 200, 14);
        excluirQuiosquePanel.add(lblExcluirQuiosque);


        contentPane.add(excluirQuiosquePanel, "excluirQuiosquePanel");
    }

    private void fazerReservaPanel() {
        JPanel fazerReservaPanel = new JPanel();
        fazerReservaPanel.setLayout(null);

        JLabel lblFazerReserva = new JLabel("Fazer Reserva");
        lblFazerReserva.setBounds(289, 57, 200, 14);
        fazerReservaPanel.add(lblFazerReserva);

        contentPane.add(fazerReservaPanel, "fazerReservaPanel");
    }

    private void listarReservaPanel() {
        JPanel listarReservaPanel = new JPanel();
        listarReservaPanel.setLayout(null);

        JLabel lblListarReserva = new JLabel("Listar Reservas");
        lblListarReserva.setBounds(289, 57, 200, 14);
        listarReservaPanel.add(lblListarReserva);


        contentPane.add(listarReservaPanel, "listarReservaPanel");
    }

    /**
     * savar usuario
     */
    protected void salvarUsuario() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        // Add logic to save the user here
        JOptionPane.showMessageDialog(this, "Usuário " + nome + " com email " + email + " criado com sucesso!");
        txtNome.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtTelefone.setText("");
        cardLayout.show(contentPane, "homePanel");
    }
}
