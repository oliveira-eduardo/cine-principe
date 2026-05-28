package gui;

import model.Produtos;
import control.ControlProdutos; 

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TelaProdutos extends JFrame {

    private TelaCadeira telaCadeira;
    private JSpinner[] spinnersQuantidade;
    private JLabel lblTotal;
    
    private ControlProdutos controller;

    public TelaProdutos(TelaCadeira telaCadeira) {
        this.telaCadeira = telaCadeira;
        this.controller = new ControlProdutos();

        setTitle("Produtos Disponiveis");
        setSize(550, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Adicione os produtos:", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(lblTitulo, BorderLayout.NORTH);

        Produtos[] listaProdutos = Produtos.values();
        spinnersQuantidade = new JSpinner[listaProdutos.length]; 

        JPanel painelProdutos = new JPanel(new GridLayout(listaProdutos.length, 1, 5, 5));
        painelProdutos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (int i = 0; i < listaProdutos.length; i++) {
            Produtos produto = listaProdutos[i];

            JPanel painelItem = new JPanel(new BorderLayout());
            painelItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

            String nome = produto.name().substring(0, 1).toUpperCase() + produto.name().substring(1).toLowerCase();
            JLabel lblNomePreco = new JLabel(String.format("%s - R$ %.2f", nome, produto.getPreco()));
            lblNomePreco.setFont(new Font("Arial", Font.PLAIN, 15));

            SpinnerModel modeloSpinner = new SpinnerNumberModel(0, 0, 10, 1);
            JSpinner spinner = new JSpinner(modeloSpinner);
            spinner.setPreferredSize(new Dimension(60, 30));
            
            spinner.addChangeListener(e -> recalcularTotal());
            spinnersQuantidade[i] = spinner;

            painelItem.add(lblNomePreco, BorderLayout.WEST);
            painelItem.add(spinner, BorderLayout.EAST);
            painelProdutos.add(painelItem);
        }

        JScrollPane scrollPane = new JScrollPane(painelProdutos);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelRodape = new JPanel(new BorderLayout(10,10));
        painelRodape.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        lblTotal = new JLabel("Subtotal: R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton btnVoltar = new JButton("Voltar aos Assentos");
        btnVoltar.addActionListener(e -> {
            telaCadeira.setVisible(true);
            this.dispose();
        });

        JButton btnAvancar = new JButton("Ir para Pagamento");
        btnAvancar.addActionListener(e -> {
            salvarSelecao();
            
            double total = controller.getValorTotalProdutos(); // Pega do control
            if(total > 0) {
                JOptionPane.showMessageDialog(this, 
                    "Produtos confirmados no valor de R$ " + String.format("%.2f", total), 
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }
            
            TelaDesconto telaDesconto = new TelaDesconto(this);
            telaDesconto.setVisible(true);
            this.setVisible(false);
        });

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnAvancar);

        painelRodape.add(lblTotal, BorderLayout.WEST);
        painelRodape.add(painelBotoes, BorderLayout.EAST);
        add(painelRodape, BorderLayout.SOUTH);
    }

    private void recalcularTotal() {
        int[] quantidades = extrairQuantidadesDosSpinners();
        
        double totalAtualizado = controller.calcularSubtotal(quantidades);
        lblTotal.setText(String.format("Subtotal: R$ %.2f", totalAtualizado));
    }

    private void salvarSelecao() { 
        int[] quantidades = extrairQuantidadesDosSpinners();
        controller.processarSelecao(quantidades);
    }
    
    private int[] extrairQuantidadesDosSpinners() {
        int[] quantidades = new int[spinnersQuantidade.length];
        for (int i = 0; i < spinnersQuantidade.length; i++) {
            quantidades[i] = (int) spinnersQuantidade[i].getValue();
        }
        return quantidades;
    }

    public Map<Produtos, Integer> getItensSelecionados() {
        return controller.getItensSelecionados();
    }
    
    public double getValorTotalProdutos() {
        return controller.getValorTotalProdutos();
    }

    public TelaCadeira getTelaCadeira() {
        return telaCadeira;
    }

    public void setTelaCadeira(TelaCadeira telaCadeira) {
        this.telaCadeira = telaCadeira;
    }
}