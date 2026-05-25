package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("Acesso ao Sistema");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela

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

        // Autenticação 
        btnEntrar.addActionListener(e -> {
            String login = txtUser.getText();
            String senha = new String(txtSenha.getPassword());

            if (login.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // ta faltando add o banco de dados aq
            try {
                if (login.equals("admin")) {
                    JOptionPane.showMessageDialog(this, "Bem-vindo, Administrador!");
                    //TelaSistema telaSistema = new TelaSistema();
                    //telaSistema.setVisible(true);
                    this.dispose(); 
                } else if (login.equals("func")) {
                    JOptionPane.showMessageDialog(this, "Bem-vindo, Funcionário!");
                    //TelaSistema telaSistema = new TelaSistema();
                    //telaSistema.setVisible(true);
                    this.dispose();
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Bem-vindo, Usuário!");
                    //TelaPrincipal telaUsuario = new TelaPrincipal();
                    //telaUsuario.setVisible(true);
                    this.dispose(); 
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
            }
        });

        painel.add(new JLabel("Bem-vindo ao Cinema POO", JLabel.CENTER));
        painel.add(painelCampos);
        painel.add(btnEntrar);

        add(painel);
    }
}