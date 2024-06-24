package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import controller.ClientesController;
import controller.FuncionarioController;
import controller.QuiosqueController;
import controller.ReservasController;
import model.entities.ClientesEntity;
import model.entities.FuncionariosEntity;
import model.entities.QuiosqueEntity;
import model.entities.ReservasEntity;
import model.repositories.ResevasRepository;
import model.service.ReservaService;

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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import java.sql.Date;



public class MenuPrincipal extends JFrame {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("bancoQuiosque");
	EntityManager em = emf.createEntityManager();
	
	 private ReservasController reservaController;
	 private FuncionarioController funcionarioController;
	 ClientesController clientesController = new ClientesController(em);
	 QuiosqueController quiosqueController = new QuiosqueController(em);
	 
	 
	 
	 
	private JComboBox<String> comboBoxQuiosque;
	private JComboBox<String> comboBoxCliente;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtSenha;
    private JTextField txtTelefone;
    private JTextField num;
    private JTextField local;
    private JTextField txtDiaria;
    private JTextField txtTotal;
    private Connection conn;
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserFim;
    
 

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
        quiosqueController = new QuiosqueController(em); 
        clientesController = new ClientesController(em);
        
        dateChooserInicio = new JDateChooser();
        dateChooserFim = new JDateChooser();
        comboBoxQuiosque = new JComboBox<>();
        comboBoxCliente = new JComboBox<>();
        txtDiaria = new JTextField();
        txtTotal = new JTextField();
      
        ResevasRepository reservaRepository = new ResevasRepository(em);
        ReservaService reservaService = new ReservaService(reservaRepository);
        reservaController = new ReservasController(reservaService);
    
    	setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 586);
     

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(61, 106, 131));
        setJMenuBar(menuBar);

        JButton btnHome = new JButton("Inicio");
        btnHome.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\lar.png"));
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
            	  cardLayout.show(contentPane, "listarReservaPanel");
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
        homePanel.setBackground(new Color(207, 224, 233));
        homePanel.setLayout(null);

        getContentPane().add(homePanel, "homePanel");

        // Add a button to exit
        JButton btnNewButton = new JButton("Sair");
        btnNewButton.setBackground(new Color(43, 85, 85));
        btnNewButton.addActionListener(e -> {
        	  Login loginForm = new Login();
              loginForm.setVisible(true);
        });
        btnNewButton.setIcon(new ImageIcon("C:\\Users\\Cristiely\\Downloads\\sair.png"));
        btnNewButton.setBounds(846, 455, 98, 36);
        homePanel.add(btnNewButton);

        // Add the bar chart
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBackground(new Color(61, 106, 131));
        chartPanel.setPreferredSize(new Dimension(600, 400));
        chartPanel.setBounds(150, 100, 600, 400); // Adjust the position and size as necessary
        homePanel.add(chartPanel);

        contentPane.add(homePanel, "homePanel");
    }

    // Method to create the dataset for the chart
    private CategoryDataset createDataset() {
        List<ReservasEntity> reservas = fetchReservasFromDatabase();
        return createDatasetFromReservas(reservas);
    }

    // Method to fetch reservations from the database
    private List<ReservasEntity> fetchReservasFromDatabase() {
        return em.createQuery("SELECT r FROM ReservasEntity r", ReservasEntity.class).getResultList();
    }

    // Method to create the dataset from reservations
    private CategoryDataset createDatasetFromReservas(List<ReservasEntity> reservas) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int[] monthlyReservations = new int[12];

        for (ReservasEntity reserva : reservas) {
            int month = reserva.getDataInicio().getMonthValue() - 1; // Assuming getDataInicio returns a LocalDate
            if (month >= 0 && month < 12) {
                monthlyReservations[month]++;
            }
        }

        String[] months = { "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez" };

        for (int i = 0; i < monthlyReservations.length; i++) {
            dataset.addValue(monthlyReservations[i], "Reservas", months[i]);
        }

        return dataset;
    }

    // Method to create the bar chart
    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Reservas por Mês",         // Chart title
                "Mês",                      // X-axis label
                "Quantidade",               // Y-axis label
                dataset,                    // Data for the chart
                PlotOrientation.VERTICAL,   // Chart orientation
                true,                       // Include legend
                true,                       // Include tooltips
                false                       // URLs?
        );
        chart.setBackgroundPaint(Color.white);
        return chart;
    }
  //============================ FUNCIONARIO   =====================================================================================================
    private void criarUsuarioPanel() {
        JPanel criarUsuarioPanel = new JPanel();
        criarUsuarioPanel.setBackground(new Color(207, 224, 233));
        criarUsuarioPanel.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(280, 150, 80, 14);
        criarUsuarioPanel.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBackground(new Color(255, 245, 240));
        txtNome.setBounds(370, 147, 200, 20);
        criarUsuarioPanel.add(txtNome);
        txtNome.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(280, 192, 80, 14);
        criarUsuarioPanel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBackground(new Color(255, 245, 240));
        txtEmail.setBounds(370, 189, 200, 20);
        criarUsuarioPanel.add(txtEmail);
        txtEmail.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(280, 229, 80, 14);
        criarUsuarioPanel.add(lblSenha);

        txtSenha = new JTextField();
        txtSenha.setBackground(new Color(255, 245, 240));
        txtSenha.setBounds(370, 226, 200, 20);
        criarUsuarioPanel.add(txtSenha);
        txtSenha.setColumns(10);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(280, 271, 80, 14);
        criarUsuarioPanel.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBackground(new Color(255, 245, 240));
        txtTelefone.setBounds(370, 268, 200, 20);
        criarUsuarioPanel.add(txtTelefone);
        txtTelefone.setColumns(10);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(280, 323, 80, 14);
        criarUsuarioPanel.add(lblCargo);

        JComboBox<String> cbCargo = new JComboBox<>();
        cbCargo.setBackground(new Color(255, 245, 240));
        cbCargo.setModel(new DefaultComboBoxModel<String>(new String[]{"Caixa", "Atendente","Administrador"}));
        cbCargo.setBounds(370, 319, 200, 22);
        criarUsuarioPanel.add(cbCargo);

        JButton btnSalvar = new JButton("Salvar"); 
        btnSalvar.setBackground(new Color(61, 106, 131));
        btnSalvar.setBounds(470, 423, 100, 30); 
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
        btnLimparCampos.setBackground(new Color(61, 106, 131));
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
        listarUsuarioPanel.setBackground(new Color(207, 224, 233));
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
        txtId.setBackground(new Color(255, 245, 240));
        txtId.setBounds(50, 50, 620, 23);
        listarUsuarioPanel.add(txtId);
        txtId.setColumns(10);

        // Botão para buscar por ID
        JButton btnBuscarPorId = new JButton("Buscar por ID");
        btnBuscarPorId.setBackground(new Color(61, 106, 131));
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
                            Object[] rowData = {usuario.getId(), usuario.getNome(), usuario.getCargo(), usuario.getTelefone()};
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


        JButton btnListarTodos = new JButton("Atualizar");
        btnListarTodos.setBackground(new Color(61, 106, 131));
        btnListarTodos.setBounds(810, 50, 120, 23);
        listarUsuarioPanel.add(btnListarTodos);
        btnListarTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                List<FuncionariosEntity> usuarios = funcionarioController.findAll();
                if (usuarios != null) {
                    for (FuncionariosEntity usuario : usuarios) {
                        Object[] rowData = {usuario.getId(), usuario.getNome(), usuario.getCargo(), usuario.getTelefone()};
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
        btnEditar_1.setBackground(new Color(61, 106, 131));
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
        btnEditar_1_1.setBackground(new Color(61, 106, 131));
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
        criarQuiosquePanel.setBackground(new Color(207, 224, 233));
        criarQuiosquePanel.setLayout(null);

        JLabel lblCriarQuiosque = new JLabel("Criar Quiosque");
        lblCriarQuiosque.setBounds(337, 25, 200, 14);
        criarQuiosquePanel.add(lblCriarQuiosque);

        contentPane.add(criarQuiosquePanel, "criarQuiosquePanel");

        JLabel Lbnum = new JLabel("Numero");
        Lbnum.setBounds(266, 99, 46, 14);
        criarQuiosquePanel.add(Lbnum);

        num = new JTextField();
        num.setBackground(new Color(255, 245, 240));
        num.setBounds(337, 96, 188, 20);
        criarQuiosquePanel.add(num);
        num.setColumns(10);

        JLabel lbLocalidade = new JLabel("Localidade");
        lbLocalidade.setBounds(266, 146, 96, 14);
        criarQuiosquePanel.add(lbLocalidade);

        local = new JTextField();
        local.setBackground(new Color(255, 245, 240));
        local.setBounds(337, 143, 188, 20);
        criarQuiosquePanel.add(local);
        local.setColumns(10);

        JLabel lbcapacidade = new JLabel("capacidade");
        lbcapacidade.setBounds(265, 188, 86, 14);
        criarQuiosquePanel.add(lbcapacidade);

        JSpinner capacidade = new JSpinner();
        capacidade.setBackground(new Color(255, 245, 240));
        capacidade.setBounds(337, 185, 188, 20);
        criarQuiosquePanel.add(capacidade);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(265, 233, 86, 14);
        criarQuiosquePanel.add(lblStatus);

        JComboBox<String> cbStatus = new JComboBox<>();
        cbStatus.setBackground(new Color(255, 245, 240));
        cbStatus.setModel(new DefaultComboBoxModel<>(new String[]{"Ativo", "Desativado"}));
        cbStatus.setBounds(337, 229, 188, 22);
        criarQuiosquePanel.add(cbStatus);

        JButton btnNewButton_1 = new JButton("Salvar");
        btnNewButton_1.setBackground(new Color(61, 106, 131));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String numero = num.getText();
                String localidade = local.getText();
                int capacidadeValor = (Integer) capacidade.getValue();
                String status = (String) cbStatus.getSelectedItem();

                Boolean disponibilidadeStatus = null;
                if (status.equals("livre")) {
                    disponibilidadeStatus = true;
                } else if (status.equals("ocupado")) {
                    disponibilidadeStatus = false;
                }

                QuiosqueEntity novoQuiosque = new QuiosqueEntity();
                novoQuiosque.setNumero(Integer.parseInt(numero));
                novoQuiosque.setLocalidade(localidade);
                novoQuiosque.setCapacidade(capacidadeValor);
                novoQuiosque.setDisponibilidadeStatus(disponibilidadeStatus);

                try {
                    if (!em.getTransaction().isActive()) {
                        em.getTransaction().begin();
                    }
                    quiosqueController.createQuiosque(novoQuiosque);
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
        btnNewButton_2.setBackground(new Color(61, 106, 131));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	limparCamposquiosque();
            }
        });
        btnNewButton_2.setBounds(245, 353, 113, 23);
        criarQuiosquePanel.add(btnNewButton_2);

        // Adicionar o painel ao contentPane
        contentPane.add(criarQuiosquePanel, "criarQuiosquePanel");
    }

    private void limparCamposquiosque() {
        num.setText("");
        local.setText("");
    }

    private void gerenciarQuiosquePanel() {
        JPanel gerenciarQuiosquePanel = new JPanel();
        gerenciarQuiosquePanel.setBackground(new Color(207, 224, 233));
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
                String disponibilidade = quiosque.getDisponibilidadeStatus() ? "Disponível" : "Ocupado";
                Object[] rowData = {quiosque.getId(), quiosque.getNumero(), quiosque.getLocalidade(), disponibilidade};
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
        txtId.setBackground(new Color(255, 245, 240));
        txtId.setBounds(50, 50, 620, 23);
        gerenciarQuiosquePanel.add(txtId);
        txtId.setColumns(10);

        // Botão para buscar por ID
        JButton btnBuscarPorId = new JButton("Buscar por ID");
        btnBuscarPorId.setBackground(new Color(61, 106, 131));
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
        btnListarTodos.setBackground(new Color(61, 106, 131));
        btnListarTodos.setBounds(810, 50, 120, 23);
        gerenciarQuiosquePanel.add(btnListarTodos);
        btnListarTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(0);
                List<QuiosqueEntity> quiosques = quiosqueController.findAll();
                if (quiosques != null) {
                    for (QuiosqueEntity quiosque : quiosques) {
                        String disponibilidade = quiosque.getDisponibilidadeStatus() ? "Ativo" : "Desativado";
                        Object[] rowData = {quiosque.getId(), quiosque.getNumero(), quiosque.getLocalidade(), disponibilidade};
                        tableModel.addRow(rowData);
                    }
                }
            }
        });

       

        // Botão Editar
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(61, 106, 131));
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
        btnExcluir.setBackground(new Color(61, 106, 131));
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
        // Adicionar o painel ao contentPane
        contentPane.add(gerenciarQuiosquePanel, "gerenciarQuiosquePanel");

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

            JComboBox<String> cbStatusEdit = new JComboBox<>(new String[]{"Ativo", "Desativado"});
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

  //============================ Clientes   =====================================================================================================
    private void criarClientePanel() {
        JPanel criarClientePanel = new JPanel();
        criarClientePanel.setBackground(new Color(207, 224, 233));
        criarClientePanel.setLayout(null);

        JLabel lblCriarCliente = new JLabel("Criar Cliente");
        lblCriarCliente.setBounds(374, 24, 200, 14);
        criarClientePanel.add(lblCriarCliente);

        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(266, 99, 46, 14);
        criarClientePanel.add(lblNome);

        JTextField nome = new JTextField();
        nome.setBackground(new Color(255, 245, 240));
        nome.setBounds(337, 96, 188, 20);
        criarClientePanel.add(nome);
        nome.setColumns(10);

        JLabel lblTelefone = new JLabel("Telefone");
        lblTelefone.setBounds(266, 146, 96, 14);
        criarClientePanel.add(lblTelefone);

        JTextField telefone = new JTextField();
        telefone.setBackground(new Color(255, 245, 240));
        telefone.setBounds(337, 143, 188, 20);
        criarClientePanel.add(telefone);
        telefone.setColumns(10);

        JLabel lblCpf = new JLabel("CPF");
        lblCpf.setBounds(266, 188, 86, 14);
        criarClientePanel.add(lblCpf);

        JTextField cpf = new JTextField();
        cpf.setBackground(new Color(255, 245, 240));
        cpf.setBounds(337, 185, 188, 20);
        criarClientePanel.add(cpf);
        cpf.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(266, 233, 86, 14);
        criarClientePanel.add(lblEmail);

        JTextField email = new JTextField();
        email.setBackground(new Color(255, 245, 240));
        email.setBounds(337, 231, 188, 20);
        criarClientePanel.add(email);
        email.setColumns(10);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(266, 276, 86, 14);
        criarClientePanel.add(lblStatus);

        JComboBox<String> cbStatus = new JComboBox<>();
        cbStatus.setBackground(new Color(255, 245, 240));
        cbStatus.setModel(new DefaultComboBoxModel<>(new String[]{"Ativo", "Desativado"}));
        cbStatus.setBounds(337, 272, 188, 22);
        criarClientePanel.add(cbStatus);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(61, 106, 131));
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeCliente = nome.getText();
                String telefoneCliente = telefone.getText();
                String cpfCliente = cpf.getText();
                String emailCliente = email.getText();
                String statusCliente = (String) cbStatus.getSelectedItem();

                boolean userStatus = "Ativo".equals(statusCliente);

                // Verifica se já existe um cliente com o mesmo CPF
                boolean cpfExists = clientesController.verificarClientePorCPF(cpfCliente);

                if (cpfExists) {
                    JOptionPane.showMessageDialog(null, "Já existe um cliente com o mesmo CPF.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return; // Não continua com a criação do cliente
                }

                ClientesEntity novoCliente = new ClientesEntity();
                novoCliente.setNome(nomeCliente);
                novoCliente.setTelefone(telefoneCliente);
                novoCliente.setCpf(cpfCliente);
                novoCliente.setEmail(emailCliente);
                novoCliente.setUserStatus(userStatus);

                try {
                    if (!em.getTransaction().isActive()) {
                        em.getTransaction().begin();
                    }
                    clientesController.createCliente(novoCliente); // Certifique-se de que createCliente está disponível
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Novo Cliente criado com sucesso.");
                    limparCamposCliente();
                } catch (Exception ex) {
                    ex.printStackTrace(); // Log the stack trace
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    JOptionPane.showMessageDialog(null, "Erro ao criar novo Cliente", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        btnSalvar.setBounds(432, 353, 105, 23);
        criarClientePanel.add(btnSalvar);

        JButton btnLimpar = new JButton("Limpar campo");
        btnLimpar.setBackground(new Color(61, 106, 131));
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCamposCliente();
            }
        });
        btnLimpar.setBounds(245, 353, 113, 23);
        criarClientePanel.add(btnLimpar);
        
        // Adicionar o painel ao contentPane
        contentPane.add(criarClientePanel, "criarClientePanel");
    }

    private void limparCamposCliente() {
    	 //nome.setText("");
         //telefone.setText("");
        // cpf.setText("");
       //  email.setText("");
        // cbStatus.setSelectedIndex(0);
    }

    private void gerenciarClientePanel() {
        JPanel gerenciarClientePanel = new JPanel();
        gerenciarClientePanel.setBackground(new Color(207, 224, 233));
        gerenciarClientePanel.setLayout(null);

        JLabel lblListarClientes = new JLabel("Lista de Clientes");
        lblListarClientes.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblListarClientes.setBounds(343, 11, 200, 20);
        gerenciarClientePanel.add(lblListarClientes);

        // Criar o modelo da tabela com colunas definidas
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Contato");
        tableModel.addColumn("Status");

        List<ClientesEntity> clientes = clientesController.getAllClientes();
        if (clientes != null) {
            for (ClientesEntity cliente : clientes) {
                Object[] rowData = {
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getTelefone(),
                    cliente.getUserStatus() ? "Ativo" : "Desativado"
                };
                tableModel.addRow(rowData);
            }
        }

        // Criar a tabela com o modelo criado
        JTable table = new JTable(tableModel);

        // Configurar tamanho e posição da tabela dentro do JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 85, 880, 300);
        gerenciarClientePanel.add(scrollPane);

        JTextField txtId = new JTextField();
        txtId.setBackground(new Color(255, 245, 240));
        txtId.setBounds(50, 50, 620, 23);
        gerenciarClientePanel.add(txtId);
        txtId.setColumns(10);

        // Botão para buscar por ID
        JButton btnBuscarPorId = new JButton("Buscar por ID");
        btnBuscarPorId.setBackground(new Color(61, 106, 131));
        btnBuscarPorId.setBounds(680, 50, 120, 23);
        gerenciarClientePanel.add(btnBuscarPorId);
        btnBuscarPorId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ação ao clicar no botão buscar
                String idStr = txtId.getText().trim();
                if (!idStr.isEmpty()) {
                    try {
                        Long id = Long.parseLong(idStr);
                        ClientesEntity cliente = clientesController.getClienteById(id);
                        if (cliente != null) {
                            // Limpar a tabela antes de adicionar o resultado da busca
                            tableModel.setRowCount(0);
                            // Adicionar o cliente encontrado à tabela
                            Object[] rowData = {cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getUserStatus() ? "Ativo" : "Desativado"};
                            tableModel.addRow(rowData);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado com o ID informado.");
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
        btnListarTodos.setBackground(new Color(61, 106, 131));
        btnListarTodos.setBounds(810, 50, 120, 23);
        gerenciarClientePanel.add(btnListarTodos);
        btnListarTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Atualizar a tabela com todos os clientes
                tableModel.setRowCount(0);
                List<ClientesEntity> clientes = clientesController.getAllClientes();
                if (clientes != null) {
                    for (ClientesEntity cliente : clientes) {
                        Object[] rowData = {cliente.getId(), cliente.getNome(), cliente.getTelefone(), cliente.getUserStatus() ? "Ativo" : "Desativado"};
                        tableModel.addRow(rowData);
                    }
                }
            }
        });

        // Botão Editar
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBackground(new Color(61, 106, 131));
        btnEditar.setBounds(240, 404, 120, 23);
        gerenciarClientePanel.add(btnEditar);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar se uma linha da tabela está selecionada
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um cliente para editar.");
                    return;
                }

                // Obter o ID do cliente selecionado
                Long id = (Long) table.getValueAt(selectedRow, 0);

                // Chamar método para abrir a janela de edição com o ID
                editarCliente(id);
            }
        });

        // Botão Excluir
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBackground(new Color(61, 106, 131));
        btnExcluir.setBounds(414, 404, 120, 23);
        gerenciarClientePanel.add(btnExcluir);
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Verificar se alguma linha está selecionada na tabela
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(gerenciarClientePanel, "Por favor, selecione um cliente para excluir.");
                    return;
                }

                // Obter o ID do cliente selecionado
                Long id = (Long) table.getValueAt(selectedRow, 0);

                // Confirmar a exclusão com o usuário
                int option = JOptionPane.showConfirmDialog(gerenciarClientePanel, "Tem certeza que deseja excluir este cliente?",
                        "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                if (option != JOptionPane.YES_OPTION) {
                    return;
                }

                try {
                    if (!em.getTransaction().isActive()) {
                        em.getTransaction().begin();
                    }

                    // Excluir o cliente usando o controlador
                    clientesController.deleteCliente(id);
                    em.getTransaction().commit();
                    JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");

                    // Atualizar a tabela após a exclusão
                    tableModel.removeRow(selectedRow);
                } catch (Exception ex) {
                    ex.printStackTrace(); // Log do stack trace para debug
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente: " + ex.getMessage());
                }
            }
        });

        // Adicionar o painel ao contentPane
        contentPane.add(gerenciarClientePanel, "gerenciarClientePanel");
    }

    private void editarCliente(long id) {
        // Buscar o cliente pelo ID
        ClientesEntity cliente = clientesController.getClienteById(id);

        if (cliente != null) {
            // Criar um novo JDialog para a janela de edição
            JDialog dialog = new JDialog();
            dialog.setTitle("Editar Cliente");
            dialog.setSize(400, 300);
            dialog.setModal(true); // Tornar o diálogo modal para bloquear interações com outras janelas

            JPanel editarPanel = new JPanel();
            editarPanel.setLayout(null);

            JLabel lblEditarCliente = new JLabel("Editar Cliente");
            lblEditarCliente.setBounds(50, 20, 200, 14);
            editarPanel.add(lblEditarCliente);

            JLabel lblNome = new JLabel("Nome:");
            lblNome.setBounds(50, 50, 80, 14);
            editarPanel.add(lblNome);

            JTextField txtNomeEdit = new JTextField(cliente.getNome());
            txtNomeEdit.setBounds(140, 47, 200, 20);
            editarPanel.add(txtNomeEdit);

            JLabel lblTelefone = new JLabel("Telefone:");
            lblTelefone.setBounds(50, 80, 80, 14);
            editarPanel.add(lblTelefone);

            JTextField txtTelefoneEdit = new JTextField(cliente.getTelefone());
            txtTelefoneEdit.setBounds(140, 77, 200, 20);
            editarPanel.add(txtTelefoneEdit);

            JLabel lblCpf = new JLabel("CPF:");
            lblCpf.setBounds(50, 110, 80, 14);
            editarPanel.add(lblCpf);

            JTextField txtCpfEdit = new JTextField(cliente.getCpf());
            txtCpfEdit.setBounds(140, 107, 200, 20);
            editarPanel.add(txtCpfEdit);

            JLabel lblStatus = new JLabel("Status:");
            lblStatus.setBounds(50, 140, 80, 14);
            editarPanel.add(lblStatus);

            // Criar um JComboBox com os valores de status
            JComboBox<String> cbStatusEdit = new JComboBox<>(new String[]{"Ativo", "Desativado"});
            cbStatusEdit.setSelectedItem(cliente.getUserStatus() ? "Ativo" : "Desativado");
            cbStatusEdit.setBounds(140, 137, 200, 20);
            editarPanel.add(cbStatusEdit);

            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Atualizar os dados do cliente com os novos valores
                    cliente.setNome(txtNomeEdit.getText());
                    cliente.setTelefone(txtTelefoneEdit.getText());
                    cliente.setCpf(txtCpfEdit.getText());

                    // Atualizar o status com base no item selecionado no JComboBox
                    boolean novoStatus = "Ativo".equals(cbStatusEdit.getSelectedItem());
                    cliente.setUserStatus(novoStatus);

                    // Chamar o método do controlador para atualizar no banco de dados
                    try {
                        if (!em.getTransaction().isActive()) {
                            em.getTransaction().begin();
                        }
                        clientesController.updateCliente(cliente);
                        em.getTransaction().commit();
                        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso.");
                        dialog.dispose(); // Fechar o dialog após salvar
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        if (em.getTransaction().isActive()) {
                            em.getTransaction().rollback();
                        }
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
        }
    }


  //============================ RESERVAS   =====================================================================================================
    private void fazerReservaPanel() {
        JPanel fazerReservaPanel = new JPanel();
        fazerReservaPanel.setBackground(new Color(61, 106, 131));
        fazerReservaPanel.setLayout(null);

        JLabel lblFazerReserva = new JLabel("Fazer Reserva");
        lblFazerReserva.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblFazerReserva.setForeground(new Color(255, 255, 255));
        lblFazerReserva.setBounds(365, 11, 200, 24);
        fazerReservaPanel.add(lblFazerReserva);

        contentPane.add(fazerReservaPanel, "fazerReservaPanel");

        JPanel panel = new JPanel();
        panel.setBackground(new Color(207, 224, 233));
        panel.setBounds(105, 46, 752, 420);
        fazerReservaPanel.add(panel);
        panel.setLayout(null);

        JLabel lblInicio = new JLabel("Início");
        lblInicio.setBounds(81, 53, 46, 14);
        panel.add(lblInicio);

        JDateChooser dateChooserInicio = new JDateChooser();
        dateChooserInicio.setBounds(137, 47, 146, 20);
        panel.add(dateChooserInicio);

        JLabel lblFim = new JLabel("Fim");
        lblFim.setBounds(425, 53, 46, 14);
        panel.add(lblFim);

        JDateChooser dateChooserFim = new JDateChooser();
        dateChooserFim.setBounds(481, 47, 146, 20);
        panel.add(dateChooserFim);

        JLabel lblQuiosque = new JLabel("Quiosque");
        lblQuiosque.setBounds(81, 112, 100, 14);
        panel.add(lblQuiosque);

        comboBoxQuiosque = new JComboBox<>();
        comboBoxQuiosque.setBackground(new Color(255, 245, 240));
        comboBoxQuiosque.setBounds(137, 108, 149, 22);
        panel.add(comboBoxQuiosque);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(81, 170, 100, 14);
        panel.add(lblCliente);

        comboBoxCliente = new JComboBox<>();
        comboBoxCliente.setBackground(new Color(255, 245, 240));
        comboBoxCliente.setBounds(136, 166, 149, 22);
        panel.add(comboBoxCliente);

        JLabel lblDiaria = new JLabel("Diária");
        lblDiaria.setBounds(81, 227, 45, 14);
        panel.add(lblDiaria);

        txtDiaria = new JTextField();
        txtDiaria.setBackground(new Color(255, 245, 240));
        txtDiaria.setBounds(136, 224, 150, 20);
        panel.add(txtDiaria);
        txtDiaria.setColumns(10);

        JLabel lblTotal = new JLabel("Total");
        lblTotal.setBounds(457, 227, 46, 14);
        panel.add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBackground(new Color(255, 245, 240));
        txtTotal.setColumns(10);
        txtTotal.setBounds(539, 224, 117, 20);
        txtTotal.setEditable(false);
        panel.add(txtTotal);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(61, 106, 131));
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate inicio = dateChooserInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate fim = dateChooserFim.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    String quiosqueNome = (String) comboBoxQuiosque.getSelectedItem();
                    String clienteNome = (String) comboBoxCliente.getSelectedItem();
                    String diariaStr = txtDiaria.getText();
                    String totalStr = txtTotal.getText();

                    if (quiosqueNome.isEmpty() || clienteNome.isEmpty() || diariaStr.isEmpty() || totalStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
                        return;
                    }

                    // Obter o número do quiosque selecionado
                    Integer numeroQuiosque = Integer.parseInt(quiosqueNome); // Supondo que o nome é na verdade o número do quiosque

                    // Obter o QuiosqueEntity correto pelo número
                    QuiosqueEntity quiosque = quiosqueController.encontrarQuiosquePorNumero(numeroQuiosque);

                    // Verificar se o quiosque já está alugado na data
                    if (reservaController.isQuiosqueAlugadoNoPeriodo(quiosque, inicio, fim, null)) {
                        JOptionPane.showMessageDialog(null, "Quiosque já está alugado no período selecionado.");
                        return;
                    }

                    // Obter o ClientesEntity correto
                    ClientesEntity cliente = clientesController.encontrarClientePorNome(clienteNome);

                    // Converter String para BigDecimal
                    BigDecimal diaria = new BigDecimal(diariaStr);
                    BigDecimal total = new BigDecimal(totalStr);

                    // Criar uma nova reserva
                    ReservasEntity novaReserva = new ReservasEntity();
                    novaReserva.setDataInicio(inicio);
                    novaReserva.setDataFim(fim);
                    novaReserva.setQuiosque(quiosque);
                    novaReserva.setCliente(cliente);
                    novaReserva.setPrecoDiaria(diaria);
                    novaReserva.setValorTotal(total);

                    if (!em.getTransaction().isActive()) {
                        em.getTransaction().begin();

                        // Suas operações dentro da transação
                        em.persist(novaReserva); // Persistir a nova reserva
                        em.getTransaction().commit();
                        JOptionPane.showMessageDialog(null, "Reserva criada com sucesso.");

                        // Limpar campos após cadastro
                        limparCampoReserva();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    if (em.getTransaction().isActive()) {
                        em.getTransaction().rollback();
                    }
                    JOptionPane.showMessageDialog(null, "Erro ao criar reserva: " + ex.getMessage());
                }
            }
        });


        btnCadastrar.setBounds(629, 386, 89, 23);
        panel.add(btnCadastrar);

        JButton btnLimparCampos = new JButton("Limpar campos");
        btnLimparCampos.setBackground(new Color(61, 106, 131));
        btnLimparCampos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampoReserva();
            }
        });
        btnLimparCampos.setBounds(502, 386, 104, 23);
        panel.add(btnLimparCampos);

        // Adicionar itens vazios aos comboboxes
        comboBoxQuiosque.addItem("");
        comboBoxCliente.addItem("");

        // Carregar dados iniciais dos comboboxes
        carregarDados(comboBoxQuiosque, comboBoxCliente);

        // Adicionar DocumentListener ao campo txtDiaria para calcular o total automaticamente
        txtDiaria.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                calcularTotal();
            }

            public void removeUpdate(DocumentEvent e) {
                calcularTotal();
            }

            public void insertUpdate(DocumentEvent e) {
                calcularTotal();
            }

  
            	private void calcularTotal() {
                    try {
                        LocalDate inicio = dateChooserInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        LocalDate fim = dateChooserFim.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        long dias = ChronoUnit.DAYS.between(inicio, fim) + 1; // Incluindo o primeiro dia
                        double diaria = Double.parseDouble(txtDiaria.getText());
                        double total = dias * diaria;
                        txtTotal.setText(String.valueOf(total));
                    } catch (Exception ex) {
                        txtTotal.setText("");
                    }
                
            }
        });
        
        fazerReservaPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
            	carregarDados(comboBoxQuiosque, comboBoxCliente); // Atualizar dados ao exibir o painel
            }
        });

    }


    private void limparCampoReserva() {
        try {
            dateChooserInicio.setDate(null);
            dateChooserFim.setDate(null);
            comboBoxQuiosque.setSelectedIndex(0);
            comboBoxCliente.setSelectedIndex(0);
            txtDiaria.setText("");
            txtTotal.setText("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void carregarDados(JComboBox<String> comboBoxQuiosque, JComboBox<String> comboBoxCliente) {
        try {
            // Carregar clientes ativos
            List<ClientesEntity> clientes = clientesController.findClientesByUserStatus(true);

            SwingUtilities.invokeLater(() -> {
                comboBoxCliente.removeAllItems();
                comboBoxCliente.addItem(""); // Adicionar um item vazio opcionalmente
                for (ClientesEntity cliente : clientes) {
                    comboBoxCliente.addItem(cliente.getNome());
                }
            });

            // Carregar quiosques disponíveis
            List<QuiosqueEntity> quiosques = quiosqueController.findQuiosquesByDisponibilidadeStatus(true);

            SwingUtilities.invokeLater(() -> {
                comboBoxQuiosque.removeAllItems();
                comboBoxQuiosque.addItem(""); // Adicionar um item vazio opcionalmente
                for (QuiosqueEntity quiosque : quiosques) {
                    comboBoxQuiosque.addItem(String.valueOf(quiosque.getNumero()));
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + ex.getMessage());
        }
    }

   
    private void listarReservaPanel() {
    	 JPanel listarReservaPanel = new JPanel();
    	 listarReservaPanel.setBackground(new Color(207, 224, 233));
    	 listarReservaPanel.setLayout(null);

         JLabel lblListarUsuarios = new JLabel("Lista de Reservas");
         lblListarUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 17));
         lblListarUsuarios.setBounds(343, 11, 200, 20);
         listarReservaPanel.add(lblListarUsuarios);
         
      // Criar o modelo da tabela com colunas definidas
         DefaultTableModel tableModel = new DefaultTableModel();
         tableModel.addColumn("ID");
         tableModel.addColumn("Inicio");
         tableModel.addColumn("Fim");
         tableModel.addColumn("Quiosque");
         tableModel.addColumn("Cliente");
         tableModel.addColumn("Total");

         List<ReservasEntity> reservas = reservaController.encontrarTodasReservas();
         if (reservas != null) {
             for (ReservasEntity reserva : reservas) {
            	 String quiosqueNumero = String.valueOf(reserva.getQuiosque().getNumero());
                 String clienteNome = reserva.getCliente().getNome(); 
                 Object[] rowData = {reserva.getId(), reserva.getDataInicio(), reserva.getDataFim(), quiosqueNumero, clienteNome, reserva.getValorTotal()};
                 tableModel.addRow(rowData);
             }
         }

         // Criar a tabela com o modelo criado
         JTable table = new JTable(tableModel);

         // Configurar tamanho e posição da tabela dentro do JScrollPane
         JScrollPane scrollPane = new JScrollPane(table);
         scrollPane.setBounds(50, 85, 880, 300);
         listarReservaPanel.add(scrollPane);
         
         JDateChooser dateChooserInicioFiltro = new JDateChooser();
         dateChooserInicioFiltro.setBounds(105, 54, 150, 20);
         listarReservaPanel.add(dateChooserInicioFiltro);

         JDateChooser dateChooserFimFiltro = new JDateChooser();
         dateChooserFimFiltro.setBounds(343, 54, 150, 20);
         listarReservaPanel.add(dateChooserFimFiltro);

         

         JButton btnListarTodos = new JButton("Atualizar");
         btnListarTodos.setBackground(new Color(61, 106, 131));
         btnListarTodos.setBounds(810, 50, 120, 23);
         listarReservaPanel.add(btnListarTodos);
         btnListarTodos.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 tableModel.setRowCount(0);
                 List<ReservasEntity> reservas = reservaController.encontrarTodasReservas();
                 if (reservas != null) {
                	  for (ReservasEntity reserva : reservas) {
                     	 String quiosqueNumero = String.valueOf(reserva.getQuiosque().getNumero());
                          String clienteNome = reserva.getCliente().getNome(); 
                          Object[] rowData = {reserva.getId(), reserva.getDataInicio(), reserva.getDataFim(), quiosqueNumero, clienteNome, reserva.getValorTotal()};
                          tableModel.addRow(rowData);
                      }
                 }
             }
         });
         
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
                 EdicaoReserva( id);
         	}
         });
         btnEditar_1.setBackground(new Color(61, 106, 131));
         btnEditar_1.setBounds(240, 404, 120, 23);
         listarReservaPanel.add(btnEditar_1);
         
         JButton btnEditar_1_1 = new JButton("Excluir");
         btnEditar_1_1.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		  // Verificar se alguma linha está selecionada na tabela
                 int selectedRow = table.getSelectedRow();
                 if (selectedRow == -1) {
                     JOptionPane.showMessageDialog(listarReservaPanel, "Por favor, selecione uma reserva para excluir.");
                     return;
                 }

                 // Obter o ID do usuário selecionado
                 long userId = (long) table.getValueAt(selectedRow, 0);

                 // Confirmar a exclusão com o usuário
                 int option = JOptionPane.showConfirmDialog(listarReservaPanel, "Tem certeza que deseja excluir este reserva?",
                                                            "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                 if (option != JOptionPane.YES_OPTION) {
                     return;
                 }

                 // Tentar excluir o usuário
                 try {
                     reservaController.excluirReserva(userId);
                     JOptionPane.showMessageDialog(listarReservaPanel, "reserva excluído com sucesso.");
                     
                     // Atualizar a lista de usuários na tabela após a exclusão
                     tableModel.removeRow(selectedRow);
                 } catch (Exception ex) {
                     JOptionPane.showMessageDialog(listarReservaPanel, "Erro ao excluir reserva: " + ex.getMessage());
                 }
             }
         });
         btnEditar_1_1.setBackground(new Color(61, 106, 131));
         btnEditar_1_1.setBounds(414, 404, 120, 23);
         listarReservaPanel.add(btnEditar_1_1);
         
      // Adiciona o painel ao contentPane
         contentPane.add(listarReservaPanel, "listarReservaPanel");
         
         JButton btnBuscar = new JButton("Buscar");
         btnBuscar.addActionListener(new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {
        	        // Obter as datas dos JDateChooser
        	        java.util.Date inicioUtil = dateChooserInicioFiltro.getDate();
        	        java.util.Date fimUtil = dateChooserFimFiltro.getDate();

        	        // Verificar se as datas são diferentes de null
        	        if (inicioUtil == null || fimUtil == null) {
        	            JOptionPane.showMessageDialog(listarReservaPanel, "Por favor, selecione uma data de início e uma data de fim.");
        	            return; // Encerra o método caso alguma data seja nula
        	        }

        	        // Converter java.util.Date para java.sql.Date (se necessário)
        	        java.sql.Date inicio = new java.sql.Date(inicioUtil.getTime());
        	        java.sql.Date fim = new java.sql.Date(fimUtil.getTime());

        	        // Limpar a tabela antes de preenchê-la novamente
        	        tableModel.setRowCount(0);

        	        // Buscar reservas no período selecionado
        	        List<ReservasEntity> reservas = reservaController.encontrarReservasPorData(inicio.toLocalDate(), fim.toLocalDate());
        	        if (reservas != null) {
        	            for (ReservasEntity reserva : reservas) {
        	                String quiosqueNumero = String.valueOf(reserva.getQuiosque().getNumero());
        	                String clienteNome = reserva.getCliente().getNome();
        	                Object[] rowData = {reserva.getId(), reserva.getDataInicio(), reserva.getDataFim(), quiosqueNumero, clienteNome, reserva.getValorTotal()};
        	                tableModel.addRow(rowData);
        	            }
        	        }
        	    }
        	});


         btnBuscar.setBackground(new Color(61, 106, 131));
         btnBuscar.setBounds(503, 51, 120, 23);
         listarReservaPanel.add(btnBuscar);
         
         JLabel lblNewLabel_1 = new JLabel("Inicio");
         lblNewLabel_1.setBounds(49, 54, 46, 14);
         listarReservaPanel.add(lblNewLabel_1);
         
         JLabel lblNewLabel_1_1 = new JLabel("fim");
         lblNewLabel_1_1.setBounds(287, 60, 46, 14);
         listarReservaPanel.add(lblNewLabel_1_1);
        
     }
    
    private void EdicaoReserva(Long id) {
        ReservasEntity reserva = reservaController.encontrarReservaPorId(id);
        if (reserva != null) {
            JDialog dialog = new JDialog();
            dialog.setTitle("Editar Reserva");
            dialog.setSize(400, 300);
            dialog.setModal(true);

            JPanel editarPanel = new JPanel();
            editarPanel.setLayout(null);

            JLabel lblEditarReserva = new JLabel("Editar Reserva");
            lblEditarReserva.setBounds(50, 20, 200, 14);
            editarPanel.add(lblEditarReserva);

            JLabel lblQuiosque = new JLabel("Quiosque:");
            lblQuiosque.setBounds(50, 50, 80, 14);
            editarPanel.add(lblQuiosque);

            JComboBox<String> cbQuiosqueEdit = new JComboBox<>();
            for (QuiosqueEntity quiosque : quiosqueController.findQuiosquesByDisponibilidadeStatus(true)) {
                cbQuiosqueEdit.addItem(String.valueOf(quiosque.getNumero()));
            }
            cbQuiosqueEdit.setSelectedItem(String.valueOf(reserva.getQuiosque().getNumero()));
            cbQuiosqueEdit.setBounds(140, 47, 200, 20);
            editarPanel.add(cbQuiosqueEdit);

            JLabel lblCliente = new JLabel("Cliente:");
            lblCliente.setBounds(50, 80, 80, 14);
            editarPanel.add(lblCliente);

            JComboBox<String> cbClienteEdit = new JComboBox<>();
            for (ClientesEntity cliente : clientesController.findClientesByUserStatus(true)) {
                cbClienteEdit.addItem(cliente.getNome());
            }
            cbClienteEdit.setSelectedItem(reserva.getCliente().getNome());
            cbClienteEdit.setBounds(140, 77, 200, 20);
            editarPanel.add(cbClienteEdit);

            JLabel lblDataInicio = new JLabel("Data Início:");
            lblDataInicio.setBounds(50, 110, 80, 14);
            editarPanel.add(lblDataInicio);

            JDateChooser dateChooserInicioEdit = new JDateChooser(Date.from(reserva.getDataInicio().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            dateChooserInicioEdit.setBounds(140, 107, 200, 20);
            editarPanel.add(dateChooserInicioEdit);

            JLabel lblDataFim = new JLabel("Data Fim:");
            lblDataFim.setBounds(50, 140, 80, 14);
            editarPanel.add(lblDataFim);

            JDateChooser dateChooserFimEdit = new JDateChooser(Date.from(reserva.getDataFim().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            dateChooserFimEdit.setBounds(140, 137, 200, 20);
            editarPanel.add(dateChooserFimEdit);

            JLabel lblValorTotal = new JLabel("Valor Total:");
            lblValorTotal.setBounds(50, 170, 80, 14);
            editarPanel.add(lblValorTotal);

            JTextField txtValorTotalEdit = new JTextField(reserva.getValorTotal().toString());
            txtValorTotalEdit.setBounds(140, 167, 200, 20);
            editarPanel.add(txtValorTotalEdit);

            JButton btnSalvar = new JButton("Salvar");
            btnSalvar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        reserva.setQuiosque(quiosqueController.encontrarQuiosquePorNumero(Integer.parseInt((String) cbQuiosqueEdit.getSelectedItem())));
                        reserva.setCliente(clientesController.encontrarClientePorNome((String) cbClienteEdit.getSelectedItem()));

                        java.util.Date dataInicioUtilDate = dateChooserInicioEdit.getDate();
                        java.sql.Date dataInicioSqlDate = new java.sql.Date(dataInicioUtilDate.getTime());
                        reserva.setDataInicio(dataInicioSqlDate.toLocalDate());

                        java.util.Date dataFimUtilDate = dateChooserFimEdit.getDate();
                        java.sql.Date dataFimSqlDate = new java.sql.Date(dataFimUtilDate.getTime());
                        reserva.setDataFim(dataFimSqlDate.toLocalDate());

                        BigDecimal valorTotal = new BigDecimal(txtValorTotalEdit.getText());
                        reserva.setValorTotal(valorTotal);

                        QuiosqueEntity quiosque = reserva.getQuiosque();
                        LocalDate inicio = reserva.getDataInicio();
                        LocalDate fim = reserva.getDataFim();

                        if (reservaController.isQuiosqueAlugadoNoPeriodo(quiosque, inicio, fim, reserva.getId())) {
                            JOptionPane.showMessageDialog(null, "Quiosque já está alugado no período selecionado.");
                            return;
                        }

                        reservaController.atualizarReserva(reserva);
                        JOptionPane.showMessageDialog(null, "Reserva atualizada com sucesso.");
                        dialog.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao atualizar reserva: " + ex.getMessage());
                    }
                }
            });
            btnSalvar.setBounds(50, 200, 100, 23);
            editarPanel.add(btnSalvar);

            JButton btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
                }
            });
            btnCancelar.setBounds(160, 200, 100, 23);
            editarPanel.add(btnCancelar);

            dialog.getContentPane().add(editarPanel);
            dialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Reserva não encontrada.");
        }
    }


}
 