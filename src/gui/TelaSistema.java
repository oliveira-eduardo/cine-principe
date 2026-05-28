package gui;

import control.ControlSistema;
import javax.swing.*;
import java.awt.*;

import model.Base;
import model.Administrador;

public class TelaSistema extends JFrame {

    private ControlSistema control;
    private Base usuarioLogado;

    private JButton btnAddUsuario;
    private JButton btnAltUsuario;
    private JButton btnAddFilme;
    private JButton btnAltFilme;
    private JButton btnExcluirUsuario;
    private JButton btnExcluirFilme;
    private JButton btnSair;

    public TelaSistema(Base usuario) {
        this.usuarioLogado = usuario;
        
        this.control = new ControlSistema(this); 
        
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

        btnAddUsuario = new JButton("Adicionar Usuário");
        btnAltUsuario = new JButton("Alterar Usuário");
        btnAddFilme = new JButton("Adicionar Filme");
        btnAltFilme = new JButton("Alterar Filme");
        btnExcluirFilme = new JButton("Excluir Filme");
        btnSair = new JButton("Sair / Logout");

        btnAddUsuario.addActionListener(e -> control.abrirCadastroUsuario());

        btnAltUsuario.addActionListener(e -> {
            String usuarioDigitado = JOptionPane.showInputDialog(this,
                    "Digite o Login ou CPF do usuário que deseja ALTERAR:");
            control.alterarUsuario(usuarioDigitado);
        });

        btnAddFilme.addActionListener(e -> control.abrirCadastroFilme());

        btnAltFilme.addActionListener(e -> {
            String filmeDigitado = JOptionPane.showInputDialog(this,
                    "Digite o nome do filme que deseja ALTERAR:");
            control.alterarFilme(filmeDigitado);
        });

        btnExcluirFilme.addActionListener(e -> {
            String filmeDigitado = JOptionPane.showInputDialog(this, "Digite o nome do filme a EXCLUIR:");
            control.excluirFilme(filmeDigitado);
        });

        btnSair.addActionListener(e -> control.deslogar());

        painelBotoes.add(btnAddUsuario);
        painelBotoes.add(btnAltUsuario);
        painelBotoes.add(btnAddFilme);
        painelBotoes.add(btnAltFilme);
        painelBotoes.add(btnExcluirFilme);

        if (usuarioLogado instanceof Administrador) {
            btnExcluirUsuario = new JButton("Excluir Usuário");
            
            btnExcluirUsuario.addActionListener(e -> {
                String usuarioDigitado = JOptionPane.showInputDialog(this, "Digite o identificador do usuário a EXCLUIR:");
                control.excluirUsuario(usuarioDigitado);
            });

            painelBotoes.add(btnExcluirUsuario);
        }

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);
        painelPrincipal.add(btnSair, BorderLayout.SOUTH);

        add(painelPrincipal);
    }
    
    public Base getUsuarioLogado() {
        return this.usuarioLogado;
    }

    public void setBotoesHabilitados(boolean status) {
        btnAddUsuario.setEnabled(status);
        btnAltUsuario.setEnabled(status);
        btnAddFilme.setEnabled(status);
        btnAltFilme.setEnabled(status);
        btnExcluirFilme.setEnabled(status);
        
        if (btnExcluirUsuario != null) {
            btnExcluirUsuario.setEnabled(status);
        }
    }

    public void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public void exibirMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean pedirConfirmacao(String mensagem) {
        int confirmacao = JOptionPane.showConfirmDialog(this, mensagem, "Confirmar", JOptionPane.YES_NO_OPTION);
        return confirmacao == JOptionPane.YES_OPTION;
    }
}