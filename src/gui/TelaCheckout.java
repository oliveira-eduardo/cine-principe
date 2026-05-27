package gui;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaCheckout extends JFrame {

    private TelaDesconto telaDesconto;
    private Usuario usuarioFinal;
    private ArrayList<Bilhete> bilhetes;
    private CupomPromocional cupom;
    private String perfil;
    private double valorTotalProdutos;
    private double valorTotalBilhetes;
    private double valorFinalCalculado;

    public TelaCheckout(TelaDesconto telaDesconto) {
        
        this.telaDesconto = telaDesconto;
        this.bilhetes = telaDesconto.getTelaProdutos().getTelaCadeira().getBilhetes();
        this.valorTotalProdutos = telaDesconto.getTelaProdutos().getValorTotalProdutos();
        this.cupom = telaDesconto.getCupomAplicado();
        this.perfil = telaDesconto.getPerfilSelecionado();

        Usuario usuarioBase = bilhetes.get(0).getUsuario(); 
        if (perfil.equals("Estudante")) {
            this.usuarioFinal = new Estudante(usuarioBase.getUser(), usuarioBase.getCpf(), usuarioBase.getSenha(), usuarioBase.getIdade(), usuarioBase.getSexo(), usuarioBase.getEmail(), usuarioBase.getNome_do_cartao(), usuarioBase.getNumero_do_cartao(), usuarioBase.getCodigo_verificador_do_cartao());
        } else if (perfil.equals("Crítico")) {
            this.usuarioFinal = new Critico(usuarioBase.getUser(), usuarioBase.getCpf(), usuarioBase.getSenha(), usuarioBase.getIdade(), usuarioBase.getSexo(), usuarioBase.getEmail(), usuarioBase.getNome_do_cartao(), usuarioBase.getNumero_do_cartao(), usuarioBase.getCodigo_verificador_do_cartao(), "ANCINE");
        } else {
            this.usuarioFinal = usuarioBase;
        }

        valorTotalBilhetes = usuarioFinal.comprarBilhetes(bilhetes);
        if(cupom != null) {
            valorFinalCalculado = usuarioFinal.realizarCompra(valorTotalBilhetes, valorTotalProdutos, cupom);
        } else {
            valorFinalCalculado = usuarioFinal.realizarCompra(valorTotalBilhetes, valorTotalProdutos);
        }

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
        
        btnFinalizar.addActionListener(e -> {
            
            if (usuarioFinal instanceof Critico) {
                // TelaCritica telaCritica = new TelaCritica(compraAtual);
                // telaCritica.setVisible(true);
                this.dispose();
                
            } else {
                String numCartao = usuarioFinal.getNumero_do_cartao();
                String cartaoMascarado = "****";
                
                if (numCartao != null && numCartao.length() >= 4) {
                    cartaoMascarado = "********" + numCartao.substring(numCartao.length() - 4);
                }

                StringBuilder recibo = new StringBuilder();
                recibo.append("Compra realizada com sucesso!\n");
                recibo.append("Cobrado no cartão de final: ").append(cartaoMascarado).append("\n\n");
                recibo.append("------------------------------\n");

                for (int i = 0; i < bilhetes.size(); i++) {
                    Bilhete b = bilhetes.get(i);
                    recibo.append("Bilhete #").append(i + 1).append("\n");
                    recibo.append("Usuário: ").append(b.getUsuario().getUser()).append("\n");
                    recibo.append("Sala: ").append(b.getSala().getNomeDaSala()).append("\n");
                    recibo.append("Sessão: ").append(b.getIndiceDaSessao() + 1).append("\n");
                    recibo.append("Cadeira: ").append(b.getCadeira()).append("\n");
                    recibo.append("------------------------------\n");
                }

                JTextArea txtAreaRecibo = new JTextArea(recibo.toString());
                txtAreaRecibo.setEditable(false);
                txtAreaRecibo.setFont(new Font("Monospaced", Font.PLAIN, 12));
                
                JScrollPane scrollPane = new JScrollPane(txtAreaRecibo);
                scrollPane.setPreferredSize(new Dimension(300, 250));

                JOptionPane.showMessageDialog(this, 
                    scrollPane, 
                    "Pagamento Aprovado", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Retorna ao menu principal ou fecha o fluxo
                this.dispose(); 
            }
        });

        painel.add(btnFinalizar, BorderLayout.SOUTH);
        add(painel);
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

    public double getValorTotalProdutos() {
        return valorTotalProdutos;
    }

    public void setValorTotalProdutos(double valorTotalProdutos) {
        this.valorTotalProdutos = valorTotalProdutos;
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