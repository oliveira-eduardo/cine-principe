package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.ControlCritica;
import model.Critico;
import model.Filme;

public class TelaCritica extends JFrame {

    private Critico critico;
    private Filme filme;
    private ControlCritica controller;

    
    public TelaCritica(Critico critico, Filme filme) {
        this.critico = critico;
        this.filme = filme;
        this.controller = new ControlCritica(); 

        setTitle("CINE PRÍNCIPE - Avaliação da Crítica");
        setSize(480, 520); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        
        
        Color fundoCreme = new Color(244, 240, 233);       
        Color terracotaDestaque = new Color(166, 84, 55);  
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaLinhaSutil = new Color(215, 210, 202);

        
        JPanel painelBase = new JPanel(new BorderLayout(0, 15));
        painelBase.setBackground(fundoCreme);
        painelBase.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        
        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);
        parteSuperior.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaLinhaSutil),
            BorderFactory.createEmptyBorder(0, 0, 12, 0)
        ));

        JLabel lblTituloTela = new JLabel("ESCREVER CRÍTICA", JLabel.CENTER);
        lblTituloTela.setFont(new Font("Serif", Font.PLAIN, 16));
        lblTituloTela.setForeground(grafiteTexto);
        parteSuperior.add(lblTituloTela, BorderLayout.CENTER);
        painelBase.add(parteSuperior, BorderLayout.NORTH);

        
        JPanel painelFormulario = new JPanel(new BorderLayout(0, 15));
        painelFormulario.setOpaque(false);

        
        JPanel painelCamposTop = new JPanel(new GridLayout(4, 2, 12, 12));
        painelCamposTop.setOpaque(false);

        
        JLabel lblValorFilme = new JLabel(filme.getNome());
        lblValorFilme.setFont(new Font("Arial", Font.PLAIN, 14));
        lblValorFilme.setForeground(terracotaDestaque);

        JLabel lblValorOrigem = new JLabel(critico.getOrigem());
        lblValorOrigem.setFont(new Font("Arial", Font.ITALIC, 13));
        lblValorOrigem.setForeground(grafiteTexto);

        
        JTextField txtTitulo = new JTextField();
        JTextField txtNota = new JTextField();
        txtNota.setToolTipText("Ex: 4.5 ou 5");

        
        JTextField[] camposTexto = {txtTitulo, txtNota};
        for (JTextField txt : camposTexto) {
            txt.setFont(new Font("Arial", Font.PLAIN, 13));
            txt.setForeground(grafiteTexto);
            txt.setBackground(Color.WHITE);
            txt.putClientProperty("Component.arc", 6);
            txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(cinzaLinhaSutil, 1, true),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
            ));
        }

        
        JLabel lblFilme = criarLabelForm("Filme Avaliado:", grafiteTexto);
        JLabel lblOrigem = criarLabelForm("Sua Origem:", grafiteTexto);
        JLabel lblTituloCritica = criarLabelForm("Título da Crítica:", grafiteTexto);
        JLabel lblNota = criarLabelForm("Nota (0 a 5):", grafiteTexto);

        painelCamposTop.add(lblFilme);         painelCamposTop.add(lblValorFilme);
        painelCamposTop.add(lblOrigem);        painelCamposTop.add(lblValorOrigem);
        painelCamposTop.add(lblTituloCritica); painelCamposTop.add(txtTitulo);
        painelCamposTop.add(lblNota);          painelCamposTop.add(txtNota);

        
        JPanel painelComentario = new JPanel(new BorderLayout(0, 5));
        painelComentario.setOpaque(false);
        
        JLabel lblComentario = criarLabelForm("Comentário / Review:", grafiteTexto);
        
        JTextArea txtAreaComentario = new JTextArea(6, 20);
        txtAreaComentario.setFont(new Font("Arial", Font.PLAIN, 13));
        txtAreaComentario.setForeground(grafiteTexto);
        txtAreaComentario.setLineWrap(true);
        txtAreaComentario.setWrapStyleWord(true);
        txtAreaComentario.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        JScrollPane scrollComentario = new JScrollPane(txtAreaComentario);
        scrollComentario.setBorder(new LineBorder(cinzaLinhaSutil, 1, true));
        scrollComentario.putClientProperty("Component.arc", 6);

        painelComentario.add(lblComentario, BorderLayout.NORTH);
        painelComentario.add(scrollComentario, BorderLayout.CENTER);

        
        painelFormulario.add(painelCamposTop, BorderLayout.NORTH);
        painelFormulario.add(painelComentario, BorderLayout.CENTER);
        
        painelBase.add(painelFormulario, BorderLayout.CENTER);

        
        JButton btnPublicar = new JButton("PUBLICAR CRÍTICA");
        btnPublicar.setFont(new Font("Arial", Font.BOLD, 12));
        btnPublicar.setForeground(Color.WHITE);
        btnPublicar.setBackground(terracotaDestaque);
        btnPublicar.setFocusPainted(false);
        btnPublicar.setBorderPainted(false);
        btnPublicar.putClientProperty("JButton.buttonType", "square");
        btnPublicar.putClientProperty("Component.arc", 6);
        btnPublicar.setPreferredSize(new Dimension(0, 40));

        btnPublicar.addActionListener((ActionEvent e) -> {
            String titulo = txtTitulo.getText();
            String notaStr = txtNota.getText();
            String comentario = txtAreaComentario.getText();

            
            String resultado = controller.salvarCritica(critico, filme, titulo, comentario, notaStr);

            if (resultado.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Crítica publicada com sucesso!\nObrigado por avaliar '" + filme.getNome() + "'.", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, resultado, "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        painelBase.add(btnPublicar, BorderLayout.SOUTH);
        add(painelBase);
    }

    
    private JLabel criarLabelForm(String texto, Color corTexto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(corTexto);
        return label;
    }
}