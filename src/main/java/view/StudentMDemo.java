package view;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StudentMDemo  extends JFrame implements ActionListener {
    private Container c;
    private JLabel label1, label2, label3, label4, label5;
    private JTextField tf1, tf2, tf3, tf4;
    private JButton btn1, btn2, btn3, btn4;
    private Font font;
    private JScrollPane scroll;
    private JTable table;
    private DefaultTableModel model;
    private String[] cols = {"ID", "Nome", "Email", "Telefone", "Cargo"};
    private String[] rows = new String[5];

    StudentMDemo () {
        b();
    }

    public void b() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.ORANGE);

        font = new Font("Arial", Font.BOLD, 16);

        label1 = new JLabel();
        label1.setText("Gerenciamento de Funcionários");
        label1.setFont(font);
        label1.setBounds(140, 10, 300, 50);
        c.add(label1);

        label2 = new JLabel("ID:  ");
        label2.setBounds(10, 80, 100, 30);
        label2.setFont(font);
        c.add(label2);

        tf1 = new JTextField();
        tf1.setBounds(110, 80, 200, 30);
        tf1.setFont(font);
        c.add(tf1);

        btn1 = new JButton("Adicionar");
        btn1.setBounds(400, 80, 120, 30);
        btn1.setFont(font);
        c.add(btn1);

        label3 = new JLabel("Nome:  ");
        label3.setBounds(10, 130, 100, 30);
        label3.setFont(font);
        c.add(label3);

        tf2 = new JTextField();
        tf2.setBounds(110, 130, 200, 30);
        tf2.setFont(font);
        c.add(tf2);

        btn2 = new JButton("Atualizar");
        btn2.setBounds(400, 130, 120, 30);
        btn2.setFont(font);
        c.add(btn2);

        label4 = new JLabel("Email:  ");
        label4.setBounds(10, 180, 100, 30);
        label4.setFont(font);
        c.add(label4);

        tf3 = new JTextField();
        tf3.setBounds(110, 180, 200, 30);
        tf3.setFont(font);
        c.add(tf3);

        btn3 = new JButton("Excluir");
        btn3.setBounds(400, 180, 120, 30);
        btn3.setFont(font);
        c.add(btn3);

        label5 = new JLabel("Telefone:  ");
        label5.setBounds(10, 230, 100, 30);
        label5.setFont(font);
        c.add(label5);

        tf4 = new JTextField();
        tf4.setBounds(110, 230, 200, 30);
        tf4.setFont(font);
        c.add(tf4);

        btn4 = new JButton("Limpar");
        btn4.setBounds(400, 230, 120, 30);
        btn4.setFont(font);
        c.add(btn4);

        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);
        table.setModel(model);
        table.setFont(font);
        table.setSelectionBackground(Color.pink);
        table.setBackground(Color.white);
        table.setRowHeight(30);

        scroll = new JScrollPane(table);
        scroll.setBounds(10, 280, 750, 350);
        c.add(scroll);

        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int numberOfRow = table.getSelectedRow();
                if (numberOfRow != -1) {
                    tf1.setText(model.getValueAt(numberOfRow, 0).toString());
                    tf2.setText(model.getValueAt(numberOfRow, 1).toString());
                    tf3.setText(model.getValueAt(numberOfRow, 2).toString());
                    tf4.setText(model.getValueAt(numberOfRow, 3).toString());
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            // Adicionar funcionário
            rows[0] = tf1.getText();
            rows[1] = tf2.getText();
            rows[2] = tf3.getText();
            rows[3] = tf4.getText();
            // Exemplo: Adicionar linha com dados fictícios
            rows[4] = "Cargo Exemplo";
            model.addRow(rows);
        } else if (e.getSource() == btn2) {
            // Atualizar funcionário
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.setValueAt(tf1.getText(), selectedRow, 0);
                model.setValueAt(tf2.getText(), selectedRow, 1);
                model.setValueAt(tf3.getText(), selectedRow, 2);
                model.setValueAt(tf4.getText(), selectedRow, 3);
                // Exemplo: Atualizar o cargo para uma string fixa
                model.setValueAt("Cargo Atualizado", selectedRow, 4);
            }
        } else if (e.getSource() == btn3) {
            // Excluir funcionário
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
            }
        } else if (e.getSource() == btn4) {
            // Limpar campos
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
        }
    }

    public static void main(String[] args) {
    	StudentMDemo  frame = new StudentMDemo ();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Sistema de Gerenciamento de Funcionários");
    }
}
