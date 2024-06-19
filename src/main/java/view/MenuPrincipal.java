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

import com.formdev.flatlaf.FlatLightLaf;

import controller.FuncionarioController;
import controller.QuiosqueController;
import model.entities.FuncionariosEntity;
import model.entities.QuiosqueEntity;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
	 private QuiosqueController quiosqueController;

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
    	 // Aplicar o look-and-feel FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        // Executar a aplicação
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    /**
     * Create the frame.
     */
    public MenuPrincipal() {
    	funcionarioController = new FuncionarioController();
        quiosqueController = new QuiosqueController(); 
    	
    	
    	
    	setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 586);
     

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

        JMenuItem miListarUsuario = new JMenuItem("Gerenciar");
        mnFuncionario.add(miListarUsuario);
        miListarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("listarUsuarioPanel");
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

        JMenuItem miEditarQuiosque = new JMenuItem("Gerenciar");
        mnQuiosque.add(miEditarQuiosque);
        miEditarQuiosque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("gerenciarQuiosquePanel");
            }
        });

        JMenu mnClientes = new JMenu("Gerenciar Clientes");
        menuBar.add(mnClientes);

        JMenuItem miCriarCliente = new JMenuItem("Criar");
        mnClientes.add(miCriarCliente);
        miCriarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("criarClientePanel");
            }
        });

        JMenuItem miEditarCliente = new JMenuItem("Gerenciar");
        mnClientes.add(miEditarCliente);
        miEditarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPainel("gerenciarClientePanel");
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
        criarQuiosquePanel();
        gerenciarQuiosquePanel();
        criarClientePanel();
        gerenciarClientePanel();
        fazerReservaPanel();
        listarReservaPanel();

        cardLayout.show(contentPane, "homePanel"); 
    }

    private void mostrarPainel(String panelName) {
        cardLayout.show(contentPane, panelName);
    }

    private void homePanel() {
        JPanel homePanel = new JPanel();
        homePanel.setBackground(new Color(255, 255, 255));
        homePanel.setLayout(null);

        JLabel lblWelcome = new JLabel("Bem-vinda(o)");
        lblWelcome.setFont(new Font("SansSerif", Font.PLAIN, 56));
        lblWelcome.setBounds(248, 48, 444, 68);
        homePanel.add(lblWelcome);

        contentPane.add(homePanel, "homePanel");
    }

  //============================ FUNCIONARIO   =====================================================================================================
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
        cbCargo.setModel(new DefaultComboBoxModel<String>(new String[]{"Caixa", "Atendente","Administrador"}));
        cbCargo.setBounds(370, 319, 200, 22);
        criarUsuarioPanel.add(cbCargo);

        JButton btnSalvar = new JButton("Salvar"); 
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
            			
            			JOptionPane.showMessageDialog(null, "Novo usuário criado com sucesso.");
            		}
            		
            	}catch (Exception ex) {
            		 ex.printStackTrace(); // Log the stack trace
                     if (em.getTransaction().isActive()) {
                         em.getTransaction().rollback();
                     }
                     JOptionPane.showMessageDialog(null, "Erro ao criar novo usuário: " + ex.getMessage());
				}
            }
        });

        contentPane.add(criarUsuarioPanel, "criarUsuarioPanel");
        
        JButton btnLimparCampos = new JButton("Limpar Campos");
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
     * Listar editar e excluir funcionarios
     */
    private void listarUsuarioPanel() {
        JPanel listarUsuarioPanel = new JPanel();
        listarUsuarioPanel.setBackground(new Color(255, 255, 255));
        listarUsuarioPanel.setLayout(null);

        JLabel lblListarUsuarios = new JLabel("Lista de Usuários");
        lblListarUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblListarUsuarios.setBounds(343, 11, 200, 20);
        listarUsuarioPanel.add(lblListarUsuarios);

        // Criar o modelo da tabela com colunas definidas
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Cargo");
        tableModel.addColumn("Contato");

        // Preencher a tabela com os dados dos usuários
        List<FuncionariosEntity> usuarios = funcionarioController.findAll();
        if (usuarios != null) {
            for (FuncionariosEntity usuario : usuarios) {
                Object[] rowData = {usuario.getId(), usuario.getNome(), usuario.getCargo(), usuario.getTelefone()};
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
        
        JButton btnEditar_1 = new JButton("Editar");
        btnEditar_1.addActionListener(new ActionListener() {
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
        btnEditar_1.setBackground(new Color(183, 219, 219));
        btnEditar_1.setBounds(240, 404, 120, 23);
        listarUsuarioPanel.add(btnEditar_1);
        
        JButton btnEditar_1_1 = new JButton("Excluir");
        btnEditar_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		  // Verificar se alguma linha está selecionada na tabela
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(listarUsuarioPanel, "Por favor, selecione um usuário para excluir.");
                    return;
                }

                // Obter o ID do usuário selecionado
                long userId = (long) table.getValueAt(selectedRow, 0);

                // Confirmar a exclusão com o usuário
                int option = JOptionPane.showConfirmDialog(listarUsuarioPanel, "Tem certeza que deseja excluir este usuário?",
                                                           "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (option != JOptionPane.YES_OPTION) {
                    return;
                }

                // Tentar excluir o usuário
                try {
                    funcionarioController.deleteFuncionarioById(userId);
                    JOptionPane.showMessageDialog(listarUsuarioPanel, "Usuário excluído com sucesso.");
                    
                    // Atualizar a lista de usuários na tabela após a exclusão
                    tableModel.removeRow(selectedRow);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(listarUsuarioPanel, "Erro ao excluir usuário: " + ex.getMessage());
                }
            }
        });
        btnEditar_1_1.setBackground(new Color(183, 219, 219));
        btnEditar_1_1.setBounds(414, 404, 120, 23);
        listarUsuarioPanel.add(btnEditar_1_1);
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

        JLabel Lbnum = new JLabel("Numero");
        Lbnum.setBounds(266, 99, 46, 14);
        criarQuiosquePanel.add(Lbnum);

        num = new JTextField();
        num.setBounds(337, 96, 188, 20);
        criarQuiosquePanel.add(num);
        num.setColumns(10);

        JLabel lbLocalidade = new JLabel("Localidade");
        lbLocalidade.setBounds(266, 146, 96, 14);
        criarQuiosquePanel.add(lbLocalidade);

        local = new JTextField();
        local.setBounds(337, 143, 188, 20);
        criarQuiosquePanel.add(local);
        local.setColumns(10);

        JLabel lbcapacidade = new JLabel("capacidade");
        lbcapacidade.setBounds(265, 188, 86, 14);
        criarQuiosquePanel.add(lbcapacidade);

        JSpinner capacidade = new JSpinner();
        capacidade.setBounds(337, 185, 188, 20);
        criarQuiosquePanel.add(capacidade);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(265, 233, 86, 14);
        criarQuiosquePanel.add(lblStatus);

        JComboBox<String> cbStatus = new JComboBox<>();
        cbStatus.setModel(new DefaultComboBoxModel<>(new String[]{"livre", "ocupado"}));
        cbStatus.setBounds(337, 229, 188, 22);
        criarQuiosquePanel.add(cbStatus);

        JButton btnNewButton_1 = new JButton("Salvar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numero = num.getText();
                String localidade = local.getText();
                int capacidadeValor = (Integer) capacidade.getValue();
                String status = (String) cbStatus.getSelectedItem();

                QuiosqueEntity novoquiosque = new QuiosqueEntity();
                novoquiosque.setNumero(Integer.parseInt(numero));
                novoquiosque.setLocalidade(localidade);
                novoquiosque.setCapacidade(capacidadeValor);
                novoquiosque.setDisponibilidadeStatus(null);

                try {
                    if (!em.getTransaction().isActive()) {
                        em.getTransaction().begin();
                    }
                    quiosqueController.createQuiosque(novoquiosque);
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Novo Quiosque criado com sucesso.");
                    limparCamposquiosque();
                } catch (Exception ex) {
                    ex.printStackTrace(); // Log the stack trace
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    JOptionPane.showMessageDialog(null, "Erro ao criar novo Quiosque");
                }
            }
        });
        btnNewButton_1.setBounds(432, 353, 105, 23);
        criarQuiosquePanel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Limpar campo");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCamposquiosque();
            }
        });
        btnNewButton_2.setBounds(245, 353, 113, 23);
        criarQuiosquePanel.add(btnNewButton_2);
    }

    private void limparCamposquiosque() {
        num.setText("");
        local.setText("");
    }

    private void gerenciarQuiosquePanel() {
        JPanel gerenciarQuiosquePanel = new JPanel();
        gerenciarQuiosquePanel.setBackground(new Color(255, 255, 255));
        gerenciarQuiosquePanel.setLayout(null);

        JLabel lblListarQuiosques = new JLabel("Lista de Quiosques");
        lblListarQuiosques.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblListarQuiosques.setBounds(343, 11, 200, 20);
        gerenciarQuiosquePanel.add(lblListarQuiosques);

        // Criar o modelo da tabela com colunas definidas
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Número");
        tableModel.addColumn("Localização");
        tableModel.addColumn("Disponivel");

        List<QuiosqueEntity> quiosques = quiosqueController.findAll();
        if (quiosques != null) {
            for (QuiosqueEntity quiosque : quiosques) {
                Object[] rowData = {quiosque.getId(), quiosque.getNumero(), quiosque.getLocalidade(), quiosque.getDisponibilidadeStatus()};
                tableModel.addRow(rowData);
            }
        }

        // Criar a tabela com o modelo criado
        JTable table = new JTable(tableModel);

        // Configurar tamanho e posição da tabela dentro do JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 85, 880, 300);
        gerenciarQuiosquePanel.add(scrollPane);

        JTextField txtId = new JTextField();
        txtId.setBounds(50, 50, 620, 23);
        gerenciarQuiosquePanel.add(txtId);
        txtId.setColumns(10);

        // Botão para buscar por ID
        JButton btnBuscarPorId = new JButton("Buscar por ID");
        btnBuscarPorId.setBackground(new Color(183, 219, 219));
        btnBuscarPorId.setBounds(680, 50, 120, 23);
        gerenciarQuiosquePanel.add(btnBuscarPorId);
        btnBuscarPorId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ação ao clicar no botão buscar
                String idStr = txtId.getText().trim();
                if (!idStr.isEmpty()) {
                    try {
                        Long id = Long.parseLong(idStr);
                        QuiosqueEntity quiosque = quiosqueController.findQuiosqueById(id);
                        if (quiosque != null) {
                            // Limpar a tabela antes de adicionar o resultado da busca
                            tableModel.setRowCount(0);
                            // Adicionar o quiosque encontrado à tabela
                            Object[] rowData = {quiosque.getId(), quiosque.getNumero(), quiosque.getLocalidade()};
                            tableModel.addRow(rowData);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum quiosque encontrado com o ID informado.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID deve ser um número válido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, informe um ID para buscar.");
                }
            }
        });

        JButton btnListarTodos = new JButton("Atualizar");
        btnListarTodos.setBackground(new Color(183, 219, 219));
        btnListarTodos.setBounds(810, 50, 120, 23);
        gerenciarQuiosquePanel.add(btnListarTodos);
        btnListarTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                List<QuiosqueEntity> quiosques = quiosqueController.findAll();
                if (quiosques != null) {
                    for (QuiosqueEntity quiosque : quiosques) {
                        Object[] rowData = {quiosque.getId(), quiosque.getNumero(), quiosque.getLocalidade()};
                        tableModel.addRow(rowData);
                    }
                }
            }
        });

        // Adicionar o painel ao contentPane
        contentPane.add(gerenciarQuiosquePanel, "gerenciarQuiosquePanel");

        // Botão Editar
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(183, 219, 219));
        btnEditar.setBounds(240, 404, 120, 23);
        gerenciarQuiosquePanel.add(btnEditar);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar se uma linha da tabela está selecionada
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um quiosque para editar.");
                    return;
                }

                // Obter o ID do quiosque selecionado
                Long id = (Long) table.getValueAt(selectedRow, 0);

                // Chamar método para abrir a janela de edição com o ID
                editarQuiosque(id);
            }
        });

     // Botão Excluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(183, 219, 219));
        btnExcluir.setBounds(414, 404, 120, 23);
        gerenciarQuiosquePanel.add(btnExcluir);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar se alguma linha está selecionada na tabela
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(gerenciarQuiosquePanel, "Por favor, selecione um Quiosque para excluir.");
                    return;
                }

                // Obter o ID do quiosque selecionado
                Long id = (Long) table.getValueAt(selectedRow, 0);

                // Confirmar a exclusão com o usuário
                int option = JOptionPane.showConfirmDialog(gerenciarQuiosquePanel, "Tem certeza que deseja excluir este Quiosque?",
                                                           "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (option != JOptionPane.YES_OPTION) {
                    return;
                }

                try {
                    if (!em.getTransaction().isActive()) {
                        em.getTransaction().begin();
                    }

                    // Excluir o quiosque usando o controlador
                    quiosqueController.deleteQuiosque(id);
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Quiosque excluído com sucesso.");

                    // Atualizar a tabela após a exclusão
                    tableModel.removeRow(selectedRow);
                } catch (Exception ex) {
                    ex.printStackTrace(); // Log do stack trace para debug
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o Quiosque: " + ex.getMessage());
                }
            }
        });


    }

    private void editarQuiosque(long id) {
        QuiosqueEntity quiosque = quiosqueController.findQuiosqueById(id);
        if (quiosque != null) {
            // Criar um novo JDialog para a janela de edição
            JDialog dialog = new JDialog();
            dialog.setTitle("Editar Quiosque");
            dialog.setSize(400, 300);
            dialog.setModal(true); // Torna o diálogo modal para bloquear interações com outras janelas

            JPanel editarPanel = new JPanel();
            editarPanel.setLayout(null);

            JLabel lblEditarQuiosque = new JLabel("Editar Quiosque");
            lblEditarQuiosque.setBounds(50, 20, 200, 14);
            editarPanel.add(lblEditarQuiosque);

            JLabel lblNumero = new JLabel("Número:");
            lblNumero.setBounds(50, 50, 80, 14);
            editarPanel.add(lblNumero);

            JTextField txtNumeroEdit = new JTextField(String.valueOf(quiosque.getNumero()));
            txtNumeroEdit.setBounds(140, 47, 200, 20);
            editarPanel.add(txtNumeroEdit);

            JLabel lblLocalidade = new JLabel("Localidade:");
            lblLocalidade.setBounds(50, 80, 80, 14);
            editarPanel.add(lblLocalidade);

            JTextField txtLocalidadeEdit = new JTextField(quiosque.getLocalidade());
            txtLocalidadeEdit.setBounds(140, 77, 200, 20);
            editarPanel.add(txtLocalidadeEdit);

            JLabel lblCapacidade = new JLabel("Capacidade:");
            lblCapacidade.setBounds(50, 110, 80, 14);
            editarPanel.add(lblCapacidade);

            JSpinner spnCapacidadeEdit = new JSpinner(new SpinnerNumberModel(quiosque.getCapacidade(), 0, Integer.MAX_VALUE, 1));
            spnCapacidadeEdit.setBounds(140, 107, 200, 20);
            editarPanel.add(spnCapacidadeEdit);

            JLabel lblStatus = new JLabel("Status:");
            lblStatus.setBounds(50, 140, 80, 14);
            editarPanel.add(lblStatus);

            JComboBox<String> cbStatusEdit = new JComboBox<>(new String[]{"livre", "ocupado"});
            cbStatusEdit.setSelectedItem(quiosque.getDisponibilidadeStatus());
            cbStatusEdit.setBounds(140, 137, 200, 20);
            editarPanel.add(cbStatusEdit);

            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Atualizar os dados do quiosque com os novos valores
                    quiosque.setNumero(Integer.parseInt(txtNumeroEdit.getText()));
                    quiosque.setLocalidade(txtLocalidadeEdit.getText());
                    quiosque.setCapacidade((Integer) spnCapacidadeEdit.getValue());
                    quiosque.setDisponibilidadeStatus(cbStatusEdit.getSelectedItem().equals("livre"));

                    // Chamar o método do controlador para atualizar no banco de dados
                    try {
                        if (!em.getTransaction().isActive()) {
                            em.getTransaction().begin();
                        }
                        quiosqueController.updateQuiosque(quiosque);
                        em.getTransaction().commit();
                        JOptionPane.showMessageDialog(null, "Quiosque atualizado com sucesso.");
                        gerenciarQuiosquePanel(); // Atualizar a tabela de listagem de quiosques após a edição
                        dialog.dispose(); // Fechar o dialog após salvar
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        if (em.getTransaction().isActive()) {
                            em.getTransaction().rollback();
                        }
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar quiosque: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Quiosque não encontrado.");
        }
    }


  
    private void criarClientePanel() {
    	
    }
    
    private void gerenciarClientePanel() {
    	
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
 