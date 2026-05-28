package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
        
        setTitle("CINE PRÍNCIPE - Acesso");
        setSize(520, 580); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        
        Color fundoEscuroCine = new Color(19, 21, 20);      
        Color terracotaDestaque = new Color(166, 84, 55);   
        Color cremeTextoClaro = new Color(228, 222, 210);   
        Color cinzaMuted = new Color(110, 115, 112);          

        JPanel painelJanela = new JPanel(new BorderLayout());
        painelJanela.setBackground(fundoEscuroCine);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(45, 50, 45, 50));
        painelCentral.setOpaque(false);

        
        JLabel lblLogo = new JLabel("C I N E P R Í N C I P E");
        lblLogo.setFont(new Font("Serif", Font.PLAIN, 32)); 
        lblLogo.setForeground(cremeTextoClaro);
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("B E M - V I N D O  D E  V O L T A");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 10));
        lblSubtitulo.setForeground(cinzaMuted);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new BoxLayout(painelFormulario, BoxLayout.Y_AXIS));
        painelFormulario.setOpaque(false);
        painelFormulario.setMaximumSize(new Dimension(320, 180));

        
        JLabel lblUser = new JLabel("Nome:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 10));
        lblUser.setForeground(cinzaMuted);
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField txtUser = new JTextField();
        txtUser.setMaximumSize(new Dimension(320, 38)); 
        txtUser.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUser.setForeground(cremeTextoClaro);
        txtUser.setBackground(fundoEscuroCine); 
        txtUser.setCaretColor(cremeTextoClaro);
        txtUser.setHorizontalAlignment(JTextField.CENTER); 
        
        txtUser.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaMuted),
            BorderFactory.createEmptyBorder(4, 4, 6, 4)
        ));

        
        JLabel lblSenha = new JLabel("SENHA");
        lblSenha.setFont(new Font("Arial", Font.BOLD, 10));
        lblSenha.setForeground(cinzaMuted);
        lblSenha.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPasswordField txtSenha = new JPasswordField();
        txtSenha.setMaximumSize(new Dimension(320, 38));
        txtSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSenha.setForeground(cremeTextoClaro);
        txtSenha.setBackground(fundoEscuroCine);
        txtSenha.setCaretColor(cremeTextoClaro);
        txtSenha.setHorizontalAlignment(JPasswordField.CENTER);
        txtSenha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaMuted),
            BorderFactory.createEmptyBorder(4, 4, 6, 4)
        ));

        
        painelFormulario.add(lblUser);
        painelFormulario.add(Box.createRigidArea(new Dimension(0, 6)));
        painelFormulario.add(txtUser);
        painelFormulario.add(Box.createRigidArea(new Dimension(0, 24))); 
        painelFormulario.add(lblSenha);
        painelFormulario.add(Box.createRigidArea(new Dimension(0, 6)));
        painelFormulario.add(txtSenha);

        
        JButton btnEntrar = new JButton("ENTRAR");
        btnEntrar.setMaximumSize(new Dimension(320, 46)); 
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEntrar.setBackground(terracotaDestaque);
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFocusPainted(false);
        btnEntrar.putClientProperty("JButton.buttonType", "square"); 
        btnEntrar.putClientProperty("Component.arc", 8); 
     
        btnEntrar.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnEntrar.addActionListener(e -> {
            String login = txtUser.getText();
            String senha = new String(txtSenha.getPassword());
            controlador.autenticar(login, senha);
        });

    
        painelCentral.add(Box.createVerticalGlue());
        painelCentral.add(lblLogo);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        painelCentral.add(lblSubtitulo);
        painelCentral.add(Box.createVerticalGlue()); 
        painelCentral.add(painelFormulario);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 45))); 
        painelCentral.add(btnEntrar);
        painelCentral.add(Box.createVerticalGlue());

        painelJanela.add(painelCentral, BorderLayout.CENTER);
        add(painelJanela);
    }

    public void exibirMensagemAviso(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public void exibirMensagemInformativa(String message, String titulo) {
        JOptionPane.showMessageDialog(this, message, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibirMensagemErro(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }
}