package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.ControlSistema;
import model.Administrador;
import model.Base;

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
        
        setTitle("CINE PRÍNCIPE - Painel Administrativo"); 
        setSize(480, 420); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        
        Color fundoCreme = new Color(244, 240, 233);       
        Color terracotaDestaque = new Color(166, 84, 55);  
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaLinhaSutil = new Color(215, 210, 202);

        
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 15));
        painelPrincipal.setBackground(fundoCreme);
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        
        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);
        parteSuperior.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaLinhaSutil),
            BorderFactory.createEmptyBorder(0, 0, 15, 0)
        ));

        JLabel lblTitulo = new JLabel("MENU PRINCIPAL - GERENCIAMENTO", JLabel.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.PLAIN, 18));
        lblTitulo.setForeground(grafiteTexto);
        parteSuperior.add(lblTitulo, BorderLayout.CENTER);
        painelPrincipal.add(parteSuperior, BorderLayout.NORTH);

        
        JPanel painelBotoes = new JPanel(new GridLayout(0, 2, 12, 12));
        painelBotoes.setOpaque(false);

        btnAddUsuario = new JButton("Adicionar Usuário");
        btnAltUsuario = new JButton("Alterar Usuário");
        btnAddFilme = new JButton("Adicionar Filme");
        btnAltFilme = new JButton("Alterar Filme");
        btnExcluirFilme = new JButton("Excluir Filme");

        
        JButton[] botoesDados = {btnAddUsuario, btnAltUsuario, btnAddFilme, btnAltFilme, btnExcluirFilme};
        for (JButton btn : botoesDados) {
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.setForeground(grafiteTexto);
            btn.setBackground(fundoCreme);
            btn.setFocusPainted(false);
            btn.putClientProperty("JButton.buttonType", "square");
            btn.putClientProperty("Component.arc", 6);
            btn.setBorder(new LineBorder(grafiteTexto, 1, true));
        }

        
        btnAddUsuario.addActionListener(e -> control.abrirCadastroUsuario());

        btnAltUsuario.addActionListener(e -> {
            String usuarioDigitado = JOptionPane.showInputDialog(this,
                    "Digite o Login do usuário que deseja ALTERAR:");
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

        painelBotoes.add(btnAddUsuario);
        painelBotoes.add(btnAltUsuario);
        painelBotoes.add(btnAddFilme);
        painelBotoes.add(btnAltFilme);
        painelBotoes.add(btnExcluirFilme);

        
        if (usuarioLogado instanceof Administrador) {
            btnExcluirUsuario = new JButton("Excluir Usuário");
            btnExcluirUsuario.setFont(new Font("Arial", Font.BOLD, 12));
            btnExcluirUsuario.setForeground(grafiteTexto);
            btnExcluirUsuario.setBackground(fundoCreme);
            btnExcluirUsuario.setFocusPainted(false);
            btnExcluirUsuario.putClientProperty("JButton.buttonType", "square");
            btnExcluirUsuario.putClientProperty("Component.arc", 6);
            btnExcluirUsuario.setBorder(new LineBorder(grafiteTexto, 1, true));
            
            btnExcluirUsuario.addActionListener(e -> {
                String usuarioDigitado = JOptionPane.showInputDialog(this, "Digite o nome do usuário a EXCLUIR:");
                control.excluirUsuario(usuarioDigitado);
            });

            painelBotoes.add(btnExcluirUsuario);
        }

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);

        
        btnSair = new JButton("SAIR / LOGOUT");
        btnSair.setFont(new Font("Arial", Font.BOLD, 12));
        btnSair.setForeground(Color.WHITE);
        btnSair.setBackground(terracotaDestaque);
        btnSair.setFocusPainted(false);
        btnSair.setBorderPainted(false);
        btnSair.putClientProperty("JButton.buttonType", "square");
        btnSair.putClientProperty("Component.arc", 6);
        btnSair.setPreferredSize(new Dimension(0, 40));
        
        btnSair.addActionListener(e -> control.deslogar());
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