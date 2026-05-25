package gui;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Cinema POO - Início");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Painel principal 
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel lblTitulo = new JLabel("Bem-vindo ao Cinema POO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Selecione uma opção para continuar");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel exclusivo para os botões (GridLayout para ficarem do mesmo tamanho)
        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 0, 15));
        painelBotoes.setMaximumSize(new Dimension(250, 100)); // Limita o tamanho do bloco de botões

        JButton btnLogin = new JButton("Fazer Login");
        JButton btnCadastro = new JButton("Criar Nova Conta");

        btnLogin.putClientProperty("JButton.buttonType", "className");

        // --- AÇÕES DOS BOTÕES ---

        btnLogin.addActionListener(e -> {
            btnCadastro.setEnabled(false);
            btnLogin.setEnabled(false); 

            TelaLogin telaLogin = new TelaLogin();
            
            // Ouvinte para reativar os botões caso a tela de login seja fechada
            telaLogin.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    btnCadastro.setEnabled(true);
                    btnLogin.setEnabled(true);
                }
            });
            
            telaLogin.setVisible(true);
        });

        btnCadastro.addActionListener(e -> {
            btnLogin.setEnabled(false);
            btnCadastro.setEnabled(false);

            TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
            
            
            telaCadastro.addWindowListener(new java.awt.event.WindowAdapter() { //reativar os botões após terminar ou cancelar o cadastro
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    btnLogin.setEnabled(true);
                    btnCadastro.setEnabled(true);
                }
            });

            telaCadastro.setVisible(true);
        });


        painelBotoes.add(btnLogin);
        painelBotoes.add(btnCadastro);

        painelPrincipal.add(lblTitulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 5)));
        painelPrincipal.add(lblSubtitulo);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 40))); // Espaço antes dos botões
        painelPrincipal.add(painelBotoes);

        add(painelPrincipal);
    }
}


// Import na main + o flatlaf
    //import view.TelaPrincipal;
    //import javax.swing.SwingUtilities;
    //import javax.swing.UIManager;
    //import com.formdev.flatlaf.FlatDarkLaf se for o preto
