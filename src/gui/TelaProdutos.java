package gui;

import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

import control.ControlProdutos;
import model.Produtos;

public class TelaProdutos extends JFrame {

    private TelaCadeira telaCadeira;
    private JSpinner[] spinnersQuantidade;
    private JLabel lblTotal;
    
    private ControlProdutos controller;

    public TelaProdutos(TelaCadeira telaCadeira) {
        this.telaCadeira = telaCadeira;
        this.controller = new ControlProdutos();

        setTitle("CINE PRÍNCIPE - Snacks");
        setSize(550, 480); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        
        Color fundoCreme = new Color(244, 240, 233);       
        Color terracotaDestaque = new Color(166, 84, 55);  
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaMuted = new Color(125, 125, 125);       
        Color cinzaLinhaSutil = new Color(225, 220, 212);

        setLayout(new BorderLayout());
        getContentPane().setBackground(fundoCreme);

        
        JLabel lblTitulo = new JLabel("Adicione os produtos:", JLabel.CENTER);
        lblTitulo.setFont(new Font("Serif", Font.PLAIN, 20));
        lblTitulo.setForeground(grafiteTexto);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 15, 0));
        add(lblTitulo, BorderLayout.NORTH);

        
        Produtos[] listaProdutos = Produtos.values();
        spinnersQuantidade = new JSpinner[listaProdutos.length]; 

        JPanel painelProdutos = new JPanel(new GridLayout(listaProdutos.length, 1, 5, 10));
        painelProdutos.setBackground(fundoCreme);
        painelProdutos.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        for (int i = 0; i < listaProdutos.length; i++) {
            Produtos produto = listaProdutos[i];

            JPanel painelItem = new JPanel(new BorderLayout());
            painelItem.setBackground(fundoCreme);
            painelItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaLinhaSutil));

            String nome = produto.name().substring(0, 1).toUpperCase() + produto.name().substring(1).toLowerCase();
            JLabel lblNomePreco = new JLabel(String.format("%s - R$ %.2f", nome.toUpperCase(), produto.getPreco()));
            lblNomePreco.setFont(new Font("Arial", Font.BOLD, 14));
            lblNomePreco.setForeground(grafiteTexto);

            
            SpinnerModel modeloSpinner = new SpinnerNumberModel(0, 0, 10, 1);
            JSpinner spinner = new JSpinner(modeloSpinner);
            spinner.setPreferredSize(new Dimension(60, 30));
            
            
            spinner.setBackground(Color.WHITE);
            if (spinner.getEditor() instanceof JSpinner.DefaultEditor) {
                JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
                editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
                editor.getTextField().setFont(new Font("Arial", Font.BOLD, 13));
                editor.getTextField().setForeground(grafiteTexto);
            }
            
            spinner.addChangeListener(e -> recalcularTotal());
            spinnersQuantidade[i] = spinner;

            painelItem.add(lblNomePreco, BorderLayout.WEST);
            painelItem.add(spinner, BorderLayout.EAST);
            painelProdutos.add(painelItem);
        }

        
        JScrollPane scrollPane = new JScrollPane(painelProdutos);
        scrollPane.setBorder(null);
        scrollPane.setBackground(fundoCreme);
        scrollPane.getViewport().setBackground(fundoCreme);
        scrollPane.getVerticalScrollBar().setBackground(fundoCreme);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel painelRodape = new JPanel(new BorderLayout(10, 10));
        painelRodape.setBackground(fundoCreme);
        painelRodape.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        lblTotal = new JLabel("Subtotal: R$ 0,00");
        lblTotal.setFont(new Font("Arial", Font.BOLD, 15));
        lblTotal.setForeground(grafiteTexto);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        painelBotoes.setOpaque(false);

        JButton btnVoltar = new JButton("Voltar aos Assentos");
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 12));
        btnVoltar.setForeground(grafiteTexto);
        btnVoltar.setBackground(fundoCreme);
        btnVoltar.setFocusPainted(false);
        btnVoltar.putClientProperty("JButton.buttonType", "square");
        btnVoltar.putClientProperty("Component.arc", 6);
        btnVoltar.setBorder(new LineBorder(grafiteTexto, 1, true));
        btnVoltar.setPreferredSize(new Dimension(160, 35));

        btnVoltar.addActionListener(e -> {
            telaCadeira.setVisible(true);
            this.dispose();
        });

        JButton btnAvancar = new JButton("Ir para Pagamento");
        btnAvancar.setFont(new Font("Arial", Font.BOLD, 12));
        btnAvancar.setForeground(Color.WHITE);
        btnAvancar.setBackground(terracotaDestaque); 
        btnAvancar.setFocusPainted(false);
        btnAvancar.setBorderPainted(false);
        btnAvancar.putClientProperty("JButton.buttonType", "square");
        btnAvancar.putClientProperty("Component.arc", 6);
        btnAvancar.setPreferredSize(new Dimension(150, 35));

        btnAvancar.addActionListener(e -> {
            salvarSelecao();
            
            double total = controller.getValorTotalProdutos(); 
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