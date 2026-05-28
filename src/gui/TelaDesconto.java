package gui;

import control.ControlDesconto;
import model.CupomPromocional;
import javax.swing.*;
import java.awt.*;

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

        setTitle("Descontos e Cupons");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 10, 20));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblPerfil = new JLabel("Perfil:");
        String[] opcoesPerfil = {"Nenhum", "Estudante", "Crítico"};
        comboPerfil = new JComboBox<>(opcoesPerfil);

        JLabel lblCupom = new JLabel("Cupom Promocional:");
        txtCupom = new JTextField();
        txtCupom.setToolTipText("Deixe em branco se não possuir");

        painelCampos.add(lblPerfil);
        painelCampos.add(comboPerfil);
        painelCampos.add(lblCupom);
        painelCampos.add(txtCupom);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        JButton btnVoltar = new JButton("Voltar");
        JButton btnAvancar = new JButton("Avançar para Checkout");

        btnVoltar.addActionListener(e -> control.voltar());
        btnAvancar.addActionListener(e -> control.avancar());

        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnAvancar);
        
        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
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