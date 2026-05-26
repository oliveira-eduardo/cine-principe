package gui;

import model.Administrador;
import model.Funcionario;
import model.Usuario;
import javax.swing.*;

import data.*;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("Acesso ao Sistema");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            
            try {
                if (AdministradoresData.pegar(login) != null) {
                    //melhorar essa parte, tá fazendo duas consultas ao banco de dados
                    Administrador adm = AdministradoresData.pegar(login);
                    
                    if(!senha.equals(adm.getSenha())) {
                        throw new Exception("Senha incorreta");
                    }

                    JOptionPane.showMessageDialog(this, "Bem-vindo, " + adm.getNome() + "!");
                    TelaSistema telaSistema = new TelaSistema(adm);
                    telaSistema.setVisible(true);
                    this.dispose(); 
                } else if (FuncionariosData.pegar(login) != null) {
                    //melhorar essa parte, tá fazendo duas consultas ao banco de dados
                    Funcionario func = FuncionariosData.pegar(login);
                    
                    if(!senha.equals(func.getSenha())) {
                        throw new Exception("Senha incorreta");
                    }

                    JOptionPane.showMessageDialog(this, "Bem-vindo, " + func.getNome() + "!");
                    TelaSistema telaSistema = new TelaSistema(func);
                    telaSistema.setVisible(true);
                    this.dispose();
                    
                } else {
                    Usuario usuario = UsuariosData.pegar(login);
                    if(usuario == null) {
                        throw new Exception("Login nao reconhecido");
                    }
                    if(!senha.equals(usuario.getSenha())) {
                        throw new Exception("Senha incorreta");
                    }

                    JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuario.getUser() + "!");
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