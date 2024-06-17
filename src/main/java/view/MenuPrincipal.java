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
import javax.swing.JDialog;

import java.awt.Font;
import javax.swing.JTable;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.JSpinner;



public class MenuPrincipal extends JFrame {
	EntityManager em = Persistence.   createEntityManagerFactory("bancoQuiosque").createEntityManager();

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
    private JTextField num;
    private JTextField local;

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
        homePanel.setBackground(new Color(183, 219, 219));
        homePanel.setLayout(null);
        
        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\image-removebg-preview (43).png"));
        lblNewLabel_6.setBounds(504, 83, 358, 429);
        homePanel.add(lblNewLabel_6);

        JLabel lblWelcome = new JLabel("Bem-vinda(o)");
        lblWelcome.setFont(new Font("SansSerif", Font.PLAIN, 56));
        lblWelcome.setBounds(248, 48, 444, 68);
        homePanel.add(lblWelcome);

        contentPane.add(homePanel, "homePanel");
        
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\image-removebg-preview (52).png"));
        lblNewLabel_5.setBounds(0, 11, 1012, 559);
        homePanel.add(lblNewLabel_5);
    }

  //============================ FUNCIONARIO   =====================================================================================================
    private void criarUsuarioPanel() {
        JPanel criarUsuarioPanel = new JPanel();
        criarUsuarioPanel.setBackground(new Color(183, 219, 219));
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
        cbCargo.setModel(new DefaultComboBoxModel<String>(new String[]{"Caixa", "Atendente","Administrador"}));
        cbCargo.setBounds(370, 319, 200, 22);
        criarUsuarioPanel.add(cbCargo);

        RoundButton btnSalvar = new RoundButton("Salvar", 20); 
        btnSalvar.setBackground(new Color(255, 242, 168));
        btnSalvar.setBounds(454, 423, 100, 30); 
        criarUsuarioPanel.add(btnSalvar);
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String nome = txtNome.getText();
            	String email = txtEmail.getText();
            	String senha = txtSenha.getText();
            	String telefone = txtTelefone.getText();
            	 String cargo = (String) cbCargo.getSelectedItem();  
            	 
            	FuncionariosEntity novofuncionario = new FuncionariosEntity();
            	
            	novofuncionario.setNome(nome);
            	novofuncionario.setEmail(email);
            	novofuncionario.setSenha(senha);
            	novofuncionario.setTelefone(telefone);
            	novofuncionario.setCargo(cargo);
            	
            	try {
            		if(!em.getTransaction().isActive()) {
            			funcionarioController.createFuncionario(novofuncionario);
            			
            			//JOptionPane.showMessageDialog(criarUsuarioPanel.this, "Novo usuário criado com sucesso.");
            		}
            		
            	}catch (Exception ex) {
            		 ex.printStackTrace(); // Log the stack trace
                     if (em.getTransaction().isActive()) {
                         em.getTransaction().rollback();
                     }
                     //JOptionPane.showMessageDialog(criarUsuarioPanel.this, "Erro ao criar novo usuário: " + ex.getMessage());
				}
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
  
    
    /**
     * Listar funcionarios
     */
    private void listarUsuarioPanel() {
        JPanel listarUsuarioPanel = new JPanel();
        listarUsuarioPanel.setBackground(new Color(196, 225, 225));
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
        btnBuscarPorId.setBackground(new Color(183, 219, 219));
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
        btnListarTodos.setBackground(new Color(183, 219, 219));
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

    /**
     * excluir funcionario
     */

    private void excluirUsuarioPanel() {
        JPanel excluirUsuarioPanel = new JPanel();
        excluirUsuarioPanel.setBackground(new Color(196, 225, 225));
        excluirUsuarioPanel.setLayout(null);

        JLabel lblExcluirUsuario = new JLabel("Excluir Usuário");
        lblExcluirUsuario.setBounds(361, 11, 200, 14);
        excluirUsuarioPanel.add(lblExcluirUsuario);

        contentPane.add(excluirUsuarioPanel, "excluirUsuarioPanel");

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
        excluirUsuarioPanel.add(scrollPane);

        JTextField txtId = new JTextField();
        txtId.setBounds(50, 50, 620, 23);
        excluirUsuarioPanel.add(txtId);
        txtId.setColumns(10);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar se alguma linha está selecionada na tabela
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(excluirUsuarioPanel, "Por favor, selecione um usuário para excluir.");
                    return;
                }

                // Obter o ID do usuário selecionado
                long userId = (long) table.getValueAt(selectedRow, 0);

                // Confirmar a exclusão com o usuário
                int option = JOptionPane.showConfirmDialog(excluirUsuarioPanel, "Tem certeza que deseja excluir este usuário?",
                                                           "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (option != JOptionPane.YES_OPTION) {
                    return;
                }

                // Tentar excluir o usuário
                try {
                    funcionarioController.deleteFuncionarioById(userId);
                    JOptionPane.showMessageDialog(excluirUsuarioPanel, "Usuário excluído com sucesso.");
                    
                    // Atualizar a lista de usuários na tabela após a exclusão
                    tableModel.removeRow(selectedRow);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(excluirUsuarioPanel, "Erro ao excluir usuário: " + ex.getMessage());
                }
            }
        });
        btnExcluir.setBounds(410, 389, 89, 23);
        excluirUsuarioPanel.add(btnExcluir);
    }

    
    /**
     * editar funcionairo
     */

    private void editarUsuarioPanel() {
        JPanel editarUsuarioPanel = new JPanel();
        editarUsuarioPanel.setLayout(null);

        JLabel lblEditarUsuario = new JLabel("Editar Usuário");
        lblEditarUsuario.setBounds(302, 23, 200, 14);
        editarUsuarioPanel.add(lblEditarUsuario);

        contentPane.add(editarUsuarioPanel, "editarUsuarioPanel");

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
        editarUsuarioPanel.add(scrollPane);

        JTextField txtId = new JTextField();
        txtId.setBounds(50, 50, 620, 23);
        editarUsuarioPanel.add(txtId);
        txtId.setColumns(10);

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar se uma linha da tabela está selecionada
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um usuário para editar.");
                    return;
                }

                // Obter o ID do usuário selecionado
                Long id = (Long) table.getValueAt(selectedRow, 0);

                // Chamar método para abrir a janela de edição com o ID
                abrirJanelaEdicao(id);
            }
        });
        btnEditar.setBackground(new Color(194, 224, 224));
        btnEditar.setBounds(810, 50, 120, 23);
        editarUsuarioPanel.add(btnEditar);

        // Botão para buscar por ID
        JButton btnBuscarPorId = new JButton("Buscar por ID");
        btnBuscarPorId.setBackground(new Color(183, 219, 219));
        btnBuscarPorId.setBounds(680, 50, 120, 23);
        editarUsuarioPanel.add(btnBuscarPorId);
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
    }

    
    private void abrirJanelaEdicao(Long id) {
        FuncionariosEntity usuario = funcionarioController.findFuncionarioById(id);
        if (usuario != null) {
            // Criar um novo JDialog para a janela de edição
            JDialog dialog = new JDialog();
            dialog.setTitle("Editar Usuário");
            dialog.setSize(400, 300);
            dialog.setModal(true); // Torna o diálogo modal para bloquear interações com outras janelas

            JPanel editarPanel = new JPanel();
            editarPanel.setLayout(null);

            JLabel lblEditarUsuario = new JLabel("Editar Usuário");
            lblEditarUsuario.setBounds(50, 20, 200, 14);
            editarPanel.add(lblEditarUsuario);

            JLabel lblNome = new JLabel("Nome:");
            lblNome.setBounds(50, 50, 80, 14);
            editarPanel.add(lblNome);

            JTextField txtNomeEdit = new JTextField(usuario.getNome());
            txtNomeEdit.setBounds(140, 47, 200, 20);
            editarPanel.add(txtNomeEdit);

            JLabel lblEmail = new JLabel("Email:");
            lblEmail.setBounds(50, 80, 80, 14);
            editarPanel.add(lblEmail);

            JTextField txtEmailEdit = new JTextField(usuario.getEmail());
            txtEmailEdit.setBounds(140, 77, 200, 20);
            editarPanel.add(txtEmailEdit);

            JLabel lblSenha = new JLabel("Senha:");
            lblSenha.setBounds(50, 110, 80, 14);
            editarPanel.add(lblSenha);

            JTextField txtSenhaEdit = new JTextField(usuario.getSenha());
            txtSenhaEdit.setBounds(140, 107, 200, 20);
            editarPanel.add(txtSenhaEdit);

            JLabel lblTelefone = new JLabel("Telefone:");
            lblTelefone.setBounds(50, 140, 80, 14);
            editarPanel.add(lblTelefone);

            JTextField txtTelefoneEdit = new JTextField(usuario.getTelefone());
            txtTelefoneEdit.setBounds(140, 137, 200, 20);
            editarPanel.add(txtTelefoneEdit);

            JLabel lblCargo = new JLabel("Cargo:");
            lblCargo.setBounds(50, 170, 80, 14);
            editarPanel.add(lblCargo);

            JComboBox<String> cbCargoEdit = new JComboBox<>();
            cbCargoEdit.setModel(new DefaultComboBoxModel<String>(new String[]{"Caixa", "Atendente", "Administrador"}));
            cbCargoEdit.setSelectedItem(usuario.getCargo());
            cbCargoEdit.setBounds(140, 167, 200, 20);
            editarPanel.add(cbCargoEdit);

            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Atualizar os dados do usuário com os novos valores
                    usuario.setNome(txtNomeEdit.getText());
                    usuario.setEmail(txtEmailEdit.getText());
                    usuario.setSenha(txtSenhaEdit.getText());
                    usuario.setTelefone(txtTelefoneEdit.getText());
                    usuario.setCargo((String) cbCargoEdit.getSelectedItem());

                    // Chamar o método do controlador para atualizar no banco de dados
                    try {
                        funcionarioController.updateFuncionario(usuario);
                        JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso.");
                        // Atualizar a tabela de listagem de usuários após a edição
                        listarUsuarioPanel();
                        dialog.dispose(); // Fechar o dialog após salvar
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar usuário: " + ex.getMessage());
                    }
                }
            });
            btnSalvar.setBounds(50, 200, 100, 23);
            editarPanel.add(btnSalvar);

            JButton btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose(); // Fechar o dialog ao cancelar
                }
            });
            btnCancelar.setBounds(160, 200, 100, 23);
            editarPanel.add(btnCancelar);

            // Adicionar o painel de edição ao dialog
            dialog.getContentPane().add(editarPanel);
            // Exibir o dialog
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
        }
    }


//============================ QUIOSQUE   =====================================================================================================
    private void criarQuiosquePanel() {
        JPanel criarQuiosquePanel = new JPanel();
        criarQuiosquePanel.setLayout(null);

        JLabel lblCriarQuiosque = new JLabel("Criar Quiosque");
        lblCriarQuiosque.setBounds(337, 25, 200, 14);
        criarQuiosquePanel.add(lblCriarQuiosque);

        contentPane.add(criarQuiosquePanel, "criarQuiosquePanel");
        
        JLabel lblNewLabel_1 = new JLabel("Numero");
        lblNewLabel_1.setBounds(266, 99, 46, 14);
        criarQuiosquePanel.add(lblNewLabel_1);
        
        num = new JTextField();
        num.setBounds(337, 96, 188, 20);
        criarQuiosquePanel.add(num);
        num.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Localidade");
        lblNewLabel_2.setBounds(266, 146, 96, 14);
        criarQuiosquePanel.add(lblNewLabel_2);
        
        local = new JTextField();
        local.setBounds(337, 143, 188, 20);
        criarQuiosquePanel.add(local);
        local.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("capacidade");
        lblNewLabel_3.setBounds(265, 188, 86, 14);
        criarQuiosquePanel.add(lblNewLabel_3);
        
        JSpinner spinner = new JSpinner();
        spinner.setBounds(337, 185, 188, 20);
        criarQuiosquePanel.add(spinner);
        
        JLabel lblNewLabel_4 = new JLabel("Status");
        lblNewLabel_4.setBounds(266, 226, 46, 14);
        criarQuiosquePanel.add(lblNewLabel_4);
        
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(337, 222, 188, 22);
        criarQuiosquePanel.add(comboBox);
        
        JButton btnNewButton_1 = new JButton("Salvar");
        btnNewButton_1.setBounds(432, 353, 105, 23);
        criarQuiosquePanel.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Limpar campo");
        btnNewButton_2.setBounds(245, 353, 113, 23);
        criarQuiosquePanel.add(btnNewButton_2);
    }

    private void editarQuiosquePanel() {
        JPanel editarQuiosquePanel = new JPanel();
        editarQuiosquePanel.setLayout(null);

        JLabel lblEditarQuiosque = new JLabel("Editar Quiosque");
        lblEditarQuiosque.setBounds(353, 11, 200, 14);
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
  //============================ RESERVAS   =====================================================================================================
    private void fazerReservaPanel() {
        JPanel fazerReservaPanel = new JPanel();
        fazerReservaPanel.setLayout(null);

        JLabel lblFazerReserva = new JLabel("Fazer Reserva");
        lblFazerReserva.setBounds(351, 11, 200, 14);
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

}
 