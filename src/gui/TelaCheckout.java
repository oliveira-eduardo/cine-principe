package gui;

import control.ControlCheckout;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        setTitle("Checkout - Resumo da Compra");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel painelResumo = new JPanel(new GridLayout(7, 1, 5, 5));
        
        JLabel lblTitulo = new JLabel("Resumo do seu pedido:", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel lblValorIngressos = new JLabel(String.format("Valor dos Ingressos: R$ %.2f", valorTotalBilhetes));
        JLabel lblValorProdutos = new JLabel(String.format("Valor dos Produtos: R$ %.2f", valorTotalProdutos));
        
        String txtCupom = (cupom != null) ? cupom.name() : "Nenhum";
        JLabel lblCupom = new JLabel("Cupom aplicado: " + txtCupom);
        
        JLabel lblValorFinal = new JLabel(String.format("Valor Final: R$ %.2f", valorFinalCalculado));
        lblValorFinal.setFont(new Font("Arial", Font.BOLD, 14));

        painelResumo.add(lblTitulo);
        painelResumo.add(lblValorIngressos);
        painelResumo.add(lblValorProdutos);
        painelResumo.add(lblCupom);
        painelResumo.add(new JLabel("--------------------------------------------------"));
        painelResumo.add(lblValorFinal);
        
        painel.add(painelResumo, BorderLayout.CENTER);

        JButton btnFinalizar = new JButton("Finalizar Compra");
        
        btnFinalizar.addActionListener(e -> control.finalizarCompra());

        painel.add(btnFinalizar, BorderLayout.SOUTH);
        add(painel);
    }

    public void exibirRecibo(String textoRecibo) {
        JTextArea txtAreaRecibo = new JTextArea(textoRecibo);
        txtAreaRecibo.setEditable(false);
        txtAreaRecibo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(txtAreaRecibo);
        scrollPane.setPreferredSize(new Dimension(300, 250));

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