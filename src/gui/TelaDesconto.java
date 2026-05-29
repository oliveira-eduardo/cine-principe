package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.ControlDesconto;
import model.CupomPromocional;

public class TelaDesconto extends JFrame {

    private ControlDesconto control;
    private TelaProdutos telaProdutos;
    private CupomPromocional cupomAplicado;
    private String perfilSelecionado;

    private JComboBox<String> comboPerfil;
    private JTextField txtCupom;

    public TelaDesconto(TelaProdutos telaProdutos) {
        this.telaProdutos = telaProdutos;
        this.cupomAplicado = null;
        
        this.control = new ControlDesconto(this);

        setTitle("CINE PRÍNCIPE - Benefícios");
        setSize(460, 320); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        
        Color fundoCreme = new Color(244, 240, 233);       
        Color terracotaDestaque = new Color(166, 84, 55);  
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaMuted = new Color(125, 125, 125);       
        Color cinzaLinhaSutil = new Color(215, 210, 202);

        
        JPanel janelaBase = new JPanel(new BorderLayout());
        janelaBase.setBackground(fundoCreme);
        janelaBase.setBorder(BorderFactory.createEmptyBorder(25, 30, 20, 30));

        
        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);
        parteSuperior.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaLinhaSutil),
            BorderFactory.createEmptyBorder(0, 0, 15, 0)
        ));

        JLabel lblTitulo = new JLabel("DESCONTOS E CUPONS", JLabel.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.PLAIN, 18));
        lblTitulo.setForeground(grafiteTexto);
        parteSuperior.add(lblTitulo, BorderLayout.CENTER);
        janelaBase.add(parteSuperior, BorderLayout.NORTH);

        
        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 15, 25)); 
        painelCampos.setOpaque(false);
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));

        JLabel lblPerfil = new JLabel("Perfil do Cliente:");
        lblPerfil.setFont(new Font("Arial", Font.BOLD, 13));
        lblPerfil.setForeground(grafiteTexto);

        String[] opcoesPerfil = {"Nenhum", "Estudante", "Crítico"};
        comboPerfil = new JComboBox<>(opcoesPerfil);
        comboPerfil.setFont(new Font("Arial", Font.PLAIN, 13));
        comboPerfil.setForeground(grafiteTexto);
        comboPerfil.setBackground(Color.WHITE);
        comboPerfil.putClientProperty("Component.arc", 6); 

        JLabel lblCupom = new JLabel("Cupom Promocional:");
        lblCupom.setFont(new Font("Arial", Font.BOLD, 13));
        lblCupom.setForeground(grafiteTexto);

        txtCupom = new JTextField();
        txtCupom.setFont(new Font("Arial", Font.PLAIN, 13));
        txtCupom.setForeground(grafiteTexto);
        txtCupom.setBackground(Color.WHITE);
        txtCupom.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(cinzaLinhaSutil, 1, true),
            BorderFactory.createEmptyBorder(5, 8, 5, 8) 
        ));
        txtCupom.putClientProperty("Component.arc", 6);
        txtCupom.setToolTipText("Deixe em branco se não possuir");

        
        painelCampos.add(lblPerfil);
        painelCampos.add(comboPerfil);
        painelCampos.add(lblCupom);
        painelCampos.add(txtCupom);
        
        janelaBase.add(painelCampos, BorderLayout.CENTER);

        
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 10));
        painelBotoes.setOpaque(false);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 12));
        btnVoltar.setForeground(grafiteTexto);
        btnVoltar.setBackground(fundoCreme);
        btnVoltar.setFocusPainted(false);
        btnVoltar.putClientProperty("JButton.buttonType", "square");
        btnVoltar.putClientProperty("Component.arc", 6);
        btnVoltar.setBorder(new LineBorder(grafiteTexto, 1, true)); 
        btnVoltar.setPreferredSize(new Dimension(90, 35));

        JButton btnAvancar = new JButton("Avançar para Checkout");
        btnAvancar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAvancar.setForeground(Color.WHITE);
        btnAvancar.setBackground(terracotaDestaque); 
        btnAvancar.setFocusPainted(false);
        btnAvancar.setBorderPainted(false);
        btnAvancar.putClientProperty("JButton.buttonType", "square");
        btnAvancar.putClientProperty("Component.arc", 6);
        btnAvancar.setPreferredSize(new Dimension(180, 35));

        
        btnVoltar.addActionListener(e -> control.voltar());
        btnAvancar.addActionListener(e -> control.avancar(telaProdutos.getTelaCadeira().getBilhetes().get(0).getUsuario()));

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnAvancar);
        
        janelaBase.add(painelBotoes, BorderLayout.SOUTH);
        add(janelaBase);
    }

    
    public TelaProdutos getTelaProdutos() {
        return telaProdutos;
    }

    public void setTelaProdutos(TelaProdutos telaProdutos) {
        this.telaProdutos = telaProdutos;
    }

    public CupomPromocional getCupomAplicado() {
        return cupomAplicado;
    }

    public void setCupomAplicado(CupomPromocional cupomAplicado) {
        this.cupomAplicado = cupomAplicado;
    }

    public String getPerfilSelecionado() {
        return perfilSelecionado;
    }

    public void setPerfilSelecionado(String perfilSelecionado) {
        this.perfilSelecionado = perfilSelecionado;
    }

    public JComboBox<String> getComboPerfil() {
        return comboPerfil;
    }

    public JTextField getTxtCupom() {
        return txtCupom;
    }

    public void exibirMensagemAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}