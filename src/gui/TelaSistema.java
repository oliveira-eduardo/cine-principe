package gui;

import javax.swing.*;
import java.awt.*;

import model.Base;
import model.Administrador;
import model.Usuario;
import repository.GerenciaFilme;
import model.Filme;
import data.UsuariosData;
import data.FilmeData;

public class TelaSistema extends JFrame {

    private Base usuarioLogado;

    public TelaSistema(Base usuario) {
        this.usuarioLogado = usuario;
        
        setTitle("Painel do Sistema - " + usuarioLogado.getNome()); 
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Menu Principal", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(0, 2, 10, 10));

        JButton btnAddUsuario = new JButton("Adicionar Usuário");
        JButton btnAltUsuario = new JButton("Alterar Usuário");
        JButton btnAddFilme = new JButton("Adicionar Filme");
        JButton btnAltFilme = new JButton("Alterar Filme");
        JButton btnExcluirUsuario = new JButton("Excluir Usuário");
        JButton btnExcluirFilme = new JButton("Excluir Filme");

        btnAddUsuario.addActionListener(e -> {
            btnAddUsuario.setEnabled(false);
            btnAltUsuario.setEnabled(false);
            btnAddFilme.setEnabled(false);
            btnAltFilme.setEnabled(false);
            btnExcluirFilme.setEnabled(false);
            if (usuarioLogado instanceof Administrador) {
                btnExcluirUsuario.setEnabled(false);
            }

            TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
            
            telaCadastro.addWindowListener(new java.awt.event.WindowAdapter() { 
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    btnAddUsuario.setEnabled(true);
                    btnAltUsuario.setEnabled(true);
                    btnAddFilme.setEnabled(true);
                    btnAltFilme.setEnabled(true);
                    btnExcluirFilme.setEnabled(true);
                    if (usuarioLogado instanceof Administrador) {
                        btnExcluirUsuario.setEnabled(true);
                    }
                }
            });

            telaCadastro.setVisible(true);
        });

        btnAltUsuario.addActionListener(e -> {
            String usuarioDigitado = JOptionPane.showInputDialog(this,
                    "Digite o Login ou CPF do usuário que deseja ALTERAR:");

            if (usuarioDigitado != null && !usuarioDigitado.trim().isEmpty()) {

                Usuario userEncontrado = UsuariosData.pegar(usuarioDigitado);

                if (userEncontrado != null) {
                    TelaAlterarUsuario telaAlterar = new TelaAlterarUsuario(usuarioLogado, userEncontrado);
                    telaAlterar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAddFilme.addActionListener(e -> {
            btnAddUsuario.setEnabled(false);
            btnAltUsuario.setEnabled(false);
            btnAddFilme.setEnabled(false);
            btnAltFilme.setEnabled(false);
            btnExcluirFilme.setEnabled(false);
            if (usuarioLogado instanceof Administrador) {
                btnExcluirUsuario.setEnabled(false);
            }

            TelaCadastroFilme telaCadastro = new TelaCadastroFilme(usuarioLogado);
            
            telaCadastro.addWindowListener(new java.awt.event.WindowAdapter() { 
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    btnAddUsuario.setEnabled(true);
                    btnAltUsuario.setEnabled(true);
                    btnAddFilme.setEnabled(true);
                    btnAltFilme.setEnabled(true);
                    btnExcluirFilme.setEnabled(true);
                    if (usuarioLogado instanceof Administrador) {
                        btnExcluirUsuario.setEnabled(true);
                    }
                }
            });

            telaCadastro.setVisible(true);
        });

        btnAltFilme.addActionListener(e -> {
            String filmeDigitado = JOptionPane.showInputDialog(this,
                    "Digite o nome do filme que deseja ALTERAR:");

            if (filmeDigitado != null && !filmeDigitado.trim().isEmpty()) {

                Filme filmeEncontrado = FilmeData.pegar(filmeDigitado);

                if (filmeEncontrado != null) {
                    TelaAlterarFilme telaAlterar = new TelaAlterarFilme(usuarioLogado, filmeEncontrado);
                    telaAlterar.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Filme não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnExcluirFilme.addActionListener(e -> {
            String filmeDigitado = JOptionPane.showInputDialog(this, "Digite o nome do filme a EXCLUIR:");
                if (filmeDigitado != null && !filmeDigitado.trim().isEmpty()) {
                    Filme filmeEncontrado = FilmeData.pegar(filmeDigitado);
                    int confirmacao = JOptionPane.showConfirmDialog(this, 
                            "Deseja excluir '" + filmeDigitado + "'?", 
                            "Confirmar", JOptionPane.YES_NO_OPTION);
                            
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        GerenciaFilme gerente = (GerenciaFilme) usuarioLogado;
                        gerente.excluirFilme(filmeEncontrado);
                        JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
                    }
                }
        });

        painelBotoes.add(btnAddUsuario);
        painelBotoes.add(btnAltUsuario);
        painelBotoes.add(btnAddFilme);
        painelBotoes.add(btnAltFilme);
        painelBotoes.add(btnExcluirFilme);

        if (usuarioLogado instanceof Administrador) {
            btnExcluirUsuario.addActionListener(e -> {
                String usuarioDigitado = JOptionPane.showInputDialog(this, "Digite o identificador do usuário a EXCLUIR:");
                if (usuarioDigitado != null && !usuarioDigitado.trim().isEmpty()) {
                    int confirmacao = JOptionPane.showConfirmDialog(this, 
                            "Deseja excluir '" + usuarioDigitado + "'?", 
                            "Confirmar", JOptionPane.YES_NO_OPTION);
                            
                    if (confirmacao == JOptionPane.YES_OPTION) {
                        Administrador admin = (Administrador) usuarioLogado;
                        admin.excluirUsuario(usuarioDigitado);
                        JOptionPane.showMessageDialog(this, "Excluído com sucesso!");
                    }
                }
            });

            painelBotoes.add(btnExcluirUsuario);
        }

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        JButton btnSair = new JButton("Sair / Logout");
        btnSair.addActionListener(e -> {
            this.dispose(); 
            new TelaLogin().setVisible(true); 
        });
        painelPrincipal.add(btnSair, BorderLayout.SOUTH);

        add(painelPrincipal);
    }
}