package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.LineBorder;

import control.ControlPrincipal;

public class TelaPrincipal extends JFrame {

    private JButton btnLogin;
    private JButton btnCadastro;
    private ControlPrincipal controlador; 

    public TelaPrincipal() {
        this.controlador = new ControlPrincipal(this); 
        
        setTitle("CINEMA PRÍNCIPE - Início");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        
        Color fundoCreme = new Color(244, 240, 233);       
        Color terracotaDestaque = new Color(166, 84, 55);  
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaMuted = new Color(115, 115, 115);      

        
        JPanel painelJanela = new JPanel(new BorderLayout());
        painelJanela.setBackground(fundoCreme);

        
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setOpaque(false);

      
        JLabel lblTitulo = new JLabel(" C I N E  P R Í N C I P E");
        lblTitulo.setFont(new Font("Serif", Font.PLAIN, 32)); 
        lblTitulo.setForeground(grafiteTexto);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        JLabel lblSubtitulo = new JLabel("B E M - V I N D O");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSubtitulo.setForeground(cinzaMuted);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 0, 14));
        painelBotoes.setMaximumSize(new Dimension(280, 110)); 
        painelBotoes.setOpaque(false);

        btnLogin = new JButton("ENTRAR");
        btnCadastro = new JButton("CRIAR CONTA");

        
        
        
        btnLogin.setBackground(terracotaDestaque);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 12));
        btnLogin.setFocusPainted(false);
        
        btnLogin.putClientProperty("JButton.buttonType", "square"); 
        btnLogin.putClientProperty("Component.arc", 8); 
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        
        btnCadastro.setBackground(fundoCreme);
        btnCadastro.setForeground(grafiteTexto);
        btnCadastro.setFont(new Font("Arial", Font.BOLD, 12));
        btnCadastro.setFocusPainted(false);
        
        btnCadastro.putClientProperty("JButton.buttonType", "square");
        btnCadastro.putClientProperty("Component.arc", 8);
        btnCadastro.setBorder(new LineBorder(grafiteTexto, 1, true)); 

        btnLogin.addActionListener(e -> {
            controlador.abrirLogin();
        });

        btnCadastro.addActionListener(e -> {
            controlador.abrirCadastro();
        });

        painelBotoes.add(btnLogin);
        painelBotoes.add(btnCadastro);

        
        painelCentral.add(Box.createVerticalGlue());
        painelCentral.add(lblTitulo);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 6)));
        painelCentral.add(lblSubtitulo);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 50))); 
        painelCentral.add(painelBotoes);
        painelCentral.add(Box.createVerticalGlue());

        painelJanela.add(painelCentral, BorderLayout.CENTER);
        add(painelJanela);
    }

    public void configurarBotoesAtivos(boolean ativos) {
        btnLogin.setEnabled(ativos);
        btnCadastro.setEnabled(ativos);
    }
}