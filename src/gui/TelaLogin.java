package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.ControlLogin;

public class TelaLogin extends JFrame {

    private ControlLogin controlador; 

    public TelaLogin() {
        this.controlador = new ControlLogin(this); 
        
        setTitle("Acesso ao Sistema");
        setSize(350, 340);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel painelCampos = new JPanel(new GridLayout(4, 1));
        JLabel lblUser = new JLabel("Usuário ou CPF:");
        JTextField txtUser = new JTextField();

        JLabel lblSenha = new JLabel("Senha:");
        JPasswordField txtSenha = new JPasswordField();

        painelCampos.add(lblUser);
        painelCampos.add(txtUser);
        painelCampos.add(lblSenha);
        painelCampos.add(txtSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEntrar.setBackground(new Color(45, 48, 50));
        
        
        btnEntrar.addActionListener(e -> {
            String login = txtUser.getText();
            String senha = new String(txtSenha.getPassword());
            controlador.autenticar(login, senha);
        });

        JLabel lblTitulo = new JLabel("Bem-vindo ao Cinema POO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 15));
        lblTitulo.setForeground(Color.WHITE);

        painel.add(lblTitulo, BorderLayout.NORTH);
        painel.add(painelCampos);
        painel.add(btnEntrar);

        add(painel);
    }

    public void exibirMensagemAviso(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public void exibirMensagemInformativa(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibirMensagemErro(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }
}