package gui;

import model.CupomPromocional;
import javax.swing.*;
import java.awt.*;

public class TelaDesconto extends JFrame {

    private TelaProdutos telaProdutos;
    private CupomPromocional cupomAplicado;
    private String perfilSelecionado;

    public TelaDesconto(TelaProdutos telaProdutos) {

        this.telaProdutos = telaProdutos;
        this.cupomAplicado = null;

        setTitle("Descontos e Cupons");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 20));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblPerfil = new JLabel("Perfil:");
        String[] opcoesPerfil = {"Nenhum", "Estudante", "Crítico"};
        JComboBox<String> comboPerfil = new JComboBox<>(opcoesPerfil);

        JLabel lblCupom = new JLabel("Cupom Promocional:");
        JTextField txtCupom = new JTextField();
        txtCupom.setToolTipText("Deixe em branco se não possuir");

        JButton btnAvancar = new JButton("Avançar para Checkout");

        btnAvancar.addActionListener(e -> {
            perfilSelecionado = (String) comboPerfil.getSelectedItem();
            
            String textoCupom = txtCupom.getText().trim().toUpperCase(); 

            if (!textoCupom.isEmpty()) {
                try {
                    cupomAplicado = CupomPromocional.valueOf(textoCupom);
                    
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, 
                        "Cupom inválido! Verifique o código ou deixe o campo em branco.", 
                        "Aviso", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            TelaCheckout telaCheckout = new TelaCheckout(this);
            telaCheckout.setVisible(true);
            
            this.dispose();
        });

        painel.add(lblPerfil);
        painel.add(comboPerfil);
        
        painel.add(lblCupom);
        painel.add(txtCupom);
        
        painel.add(new JLabel(""));
        painel.add(btnAvancar);

        add(painel);
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
}