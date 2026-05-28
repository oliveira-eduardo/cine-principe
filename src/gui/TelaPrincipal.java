package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font; 
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.ControlPrincipal;

public class TelaPrincipal extends JFrame {

    private JButton btnLogin;
    private JButton btnCadastro;
    private ControlPrincipal controlador; 

    public TelaPrincipal() {
        this.controlador = new ControlPrincipal(this); 
        
        setTitle("Cinema POO - Início");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel lblTitulo = new JLabel("Bem-vindo ao Cinema POO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Selecione uma opção para continuar");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 0, 15));
        painelBotoes.setMaximumSize(new Dimension(250, 100)); 

        btnLogin = new JButton("Fazer Login");
        btnCadastro = new JButton("Criar Nova Conta");

        btnLogin.putClientProperty("JButton.buttonType", "className");

        btnLogin.addActionListener(e -> {
            controlador.abrirLogin();
        });

        btnCadastro.addActionListener(e -> {
            controlador.abrirCadastro();
        });

        painelBotoes.add(btnLogin);
        painelBotoes.add(btnCadastro);

        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        painelPrincipal.add(lblSubtitulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 40))); 
        painelPrincipal.add(painelBotoes);

        add(painelPrincipal);
    }


    public void configurarBotoesAtivos(boolean ativos) {
        btnLogin.setEnabled(ativos);
        btnCadastro.setEnabled(ativos);
    }
}