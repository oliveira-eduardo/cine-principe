package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import control.ControlCheckout;
import model.Bilhete;
import model.CupomPromocional;
import model.Usuario;

public class TelaCheckout extends JFrame {

    private ControlCheckout control;
    private TelaDesconto telaDesconto;
    
    private ArrayList<Bilhete> bilhetes;
    private CupomPromocional cupom;
    private String perfil;
    private double valorTotalProdutos;
    
    private Usuario usuarioFinal;
    private double valorTotalBilhetes;
    private double valorFinalCalculado;

    public TelaCheckout(TelaDesconto telaDesconto) {
        this.telaDesconto = telaDesconto;
        
        this.bilhetes = telaDesconto.getTelaProdutos().getTelaCadeira().getBilhetes();
        this.valorTotalProdutos = telaDesconto.getTelaProdutos().getValorTotalProdutos();
        this.cupom = telaDesconto.getCupomAplicado();
        this.perfil = telaDesconto.getPerfilSelecionado();

        this.control = new ControlCheckout(this);

        setTitle("CINE PRÍNCIPE - Checkout");
        setSize(460, 500); 
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

        
        JPanel painelBase = new JPanel(new BorderLayout());
        painelBase.setBackground(fundoCreme);
        painelBase.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        
        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);
        parteSuperior.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaLinhaSutil),
            BorderFactory.createEmptyBorder(0, 0, 15, 0)
        ));

        JLabel lblTitulo = new JLabel("CHECKOUT", JLabel.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.PLAIN, 20));
        lblTitulo.setForeground(grafiteTexto);
        parteSuperior.add(lblTitulo, BorderLayout.CENTER);
        painelBase.add(parteSuperior, BorderLayout.NORTH);

        
        JPanel painelCentralConteudo = new JPanel();
        painelCentralConteudo.setLayout(new BoxLayout(painelCentralConteudo, BoxLayout.Y_AXIS));
        painelCentralConteudo.setOpaque(false);

        
        painelCentralConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel lblSecaoResumo = new JLabel("RESUMO DO PEDIDO");
        lblSecaoResumo.setFont(new Font("Arial", Font.BOLD, 11));
        lblSecaoResumo.setForeground(cinzaMuted);
        lblSecaoResumo.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelCentralConteudo.add(lblSecaoResumo);
        painelCentralConteudo.add(Box.createRigidArea(new Dimension(0, 15)));

        
        JPanel linhaIngressos = new JPanel(new BorderLayout());
        linhaIngressos.setOpaque(false);
        linhaIngressos.setMaximumSize(new Dimension(400, 30));
        JLabel lblTxtIngressos = new JLabel("Valor dos Ingressos");
        lblTxtIngressos.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTxtIngressos.setForeground(grafiteTexto);
        JLabel lblValorIngressos = new JLabel(String.format("R$ %.2f", valorTotalBilhetes));
        lblValorIngressos.setFont(new Font("Arial", Font.BOLD, 14));
        lblValorIngressos.setForeground(grafiteTexto);
        linhaIngressos.add(lblTxtIngressos, BorderLayout.WEST);
        linhaIngressos.add(lblValorIngressos, BorderLayout.EAST);

        
        JPanel linhaProdutos = new JPanel(new BorderLayout());
        linhaProdutos.setOpaque(false);
        linhaProdutos.setMaximumSize(new Dimension(400, 30));
        JLabel lblTxtProdutos = new JLabel("Valor dos Produtos / Snacks");
        lblTxtProdutos.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTxtProdutos.setForeground(grafiteTexto);
        JLabel lblValorProdutos = new JLabel(String.format("R$ %.2f", valorTotalProdutos));
        lblValorProdutos.setFont(new Font("Arial", Font.BOLD, 14));
        lblValorProdutos.setForeground(grafiteTexto);
        linhaProdutos.add(lblTxtProdutos, BorderLayout.WEST);
        linhaProdutos.add(lblValorProdutos, BorderLayout.EAST);

        
        JPanel linhaCupom = new JPanel(new BorderLayout());
        linhaCupom.setOpaque(false);
        linhaCupom.setMaximumSize(new Dimension(400, 30));
        JLabel lblTxtCupom = new JLabel("Cupom Aplicado");
        lblTxtCupom.setFont(new Font("Arial", Font.PLAIN, 14));
        lblTxtCupom.setForeground(cinzaMuted);
        String txtCupom = (cupom != null) ? cupom.name() : "Nenhum";
        JLabel lblCupomVal = new JLabel(txtCupom);
        lblCupomVal.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCupomVal.setForeground(cinzaMuted);
        linhaCupom.add(lblTxtCupom, BorderLayout.WEST);
        linhaCupom.add(lblCupomVal, BorderLayout.EAST);

        
        JPanel linhaDivisoria = new JPanel();
        linhaDivisoria.setOpaque(false);
        linhaDivisoria.setMaximumSize(new Dimension(400, 20));
        linhaDivisoria.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, cinzaLinhaSutil));

        
        JPanel linhaTotal = new JPanel(new BorderLayout());
        linhaTotal.setOpaque(false);
        linhaTotal.setMaximumSize(new Dimension(400, 40));
        JLabel lblTxtTotal = new JLabel("TOTAL COM DESCONTOS");
        lblTxtTotal.setFont(new Font("Arial", Font.BOLD, 13));
        lblTxtTotal.setForeground(grafiteTexto);
        JLabel lblValorFinal = new JLabel(String.format("R$ %.2f", valorFinalCalculado));
        lblValorFinal.setFont(new Font("Arial", Font.BOLD, 18));
        lblValorFinal.setForeground(terracotaDestaque);
        linhaTotal.add(lblTxtTotal, BorderLayout.WEST);
        linhaTotal.add(lblValorFinal, BorderLayout.EAST);

        
        painelCentralConteudo.add(linhaIngressos);
        painelCentralConteudo.add(Box.createRigidArea(new Dimension(0, 8)));
        painelCentralConteudo.add(linhaProdutos);
        painelCentralConteudo.add(Box.createRigidArea(new Dimension(0, 8)));
        painelCentralConteudo.add(linhaCupom);
        painelCentralConteudo.add(Box.createRigidArea(new Dimension(0, 12)));
        painelCentralConteudo.add(linhaDivisoria);
        painelCentralConteudo.add(Box.createRigidArea(new Dimension(0, 8)));
        painelCentralConteudo.add(linhaTotal);

        painelBase.add(painelCentralConteudo, BorderLayout.CENTER);

        
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setOpaque(false);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));

        
        JButton btnFinalizar = new JButton("FINALIZAR PAGAMENTO");
        btnFinalizar.setMaximumSize(new Dimension(400, 44));
        btnFinalizar.setPreferredSize(new Dimension(400, 44));
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 12));
        btnFinalizar.setBackground(terracotaDestaque);
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setBorderPainted(false);
        btnFinalizar.putClientProperty("JButton.buttonType", "square");
        btnFinalizar.putClientProperty("Component.arc", 8);
        btnFinalizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFinalizar.addActionListener(e -> control.finalizarCompra());

        
        JButton btnCancelar = new JButton("CANCELAR COMPRA");
        btnCancelar.setMaximumSize(new Dimension(400, 42));
        btnCancelar.setPreferredSize(new Dimension(400, 42));
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 11));
        btnCancelar.setForeground(grafiteTexto);
        btnCancelar.setBackground(fundoCreme); 
        btnCancelar.setContentAreaFilled(false); 
        btnCancelar.setFocusPainted(false);
        btnCancelar.putClientProperty("JButton.buttonType", "square");
        btnCancelar.putClientProperty("Component.arc", 8);
        btnCancelar.setBorder(new LineBorder(grafiteTexto, 1, true));
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCancelar.addActionListener(e -> control.cancelarCompra());

        painelBotoes.add(btnFinalizar);
        painelBotoes.add(Box.createRigidArea(new Dimension(0, 12))); 
        painelBotoes.add(btnCancelar);

        painelBase.add(painelBotoes, BorderLayout.SOUTH);
        add(painelBase);
    }

    public void exibirRecibo(String textoRecibo) {
        Color fundoCreme = new Color(244, 240, 233);       
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaLinhaSutil = new Color(215, 210, 202);

        
        JTextArea txtAreaRecibo = new JTextArea(textoRecibo);
        txtAreaRecibo.setEditable(false);
        txtAreaRecibo.setFont(new Font("Arial", Font.PLAIN, 13));
        txtAreaRecibo.setBackground(fundoCreme);
        txtAreaRecibo.setForeground(grafiteTexto);
        txtAreaRecibo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        
        JScrollPane scrollPane = new JScrollPane(txtAreaRecibo);
        scrollPane.setBorder(new LineBorder(cinzaLinhaSutil, 1, true));
        scrollPane.setPreferredSize(new Dimension(340, 260));

        
        JOptionPane.showMessageDialog(this, 
            scrollPane, 
            "Pagamento Aprovado", 
            JOptionPane.INFORMATION_MESSAGE); 
    }

    public Usuario getUsuarioFinal() {
        return usuarioFinal;
    }

    public void setUsuarioFinal(Usuario usuarioFinal) {
        this.usuarioFinal = usuarioFinal;
    }

    public ArrayList<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public void setBilhetes(ArrayList<Bilhete> bilhetes) {
        this.bilhetes = bilhetes;
    }

    public String getPerfil() {
        return perfil;
    }

    public CupomPromocional getCupom() {
        return cupom;
    }

    public double getValorTotalProdutos() {
        return valorTotalProdutos;
    }

    public void setValorTotalProdutos(double valorTotalProdutos) {
        this.valorTotalProdutos = valorTotalProdutos;
    }
    
    public double getValorTotalBilhetes() {
        return valorTotalBilhetes;
    }

    public void setValorTotalBilhetes(double valorTotalBilhetes) {
        this.valorTotalBilhetes = valorTotalBilhetes;
    }

    public double getValorFinalCalculado() {
        return valorFinalCalculado;
    }

    public void setValorFinalCalculado(double valorFinalCalculado) {
        this.valorFinalCalculado = valorFinalCalculado;
    }

    public TelaDesconto getTelaDesconto() {
        return telaDesconto;
    }

    public void setTelaDesconto(TelaDesconto telaDesconto) {
        this.telaDesconto = telaDesconto;
    }
}