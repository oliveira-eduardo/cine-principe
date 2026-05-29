package gui;

import control.ControlCadastroFilme;
import model.Base;
import javax.swing.*;
import java.awt.*;

public class TelaCadastroFilme extends JFrame {

    private ControlCadastroFilme control;
    private Base usuarioLogado;

    private JTextField txtNome;
    private JTextField txtDuracao;
    private JTextField txtSinopse;
    private JTextField txtValor;

    public TelaCadastroFilme(Base usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        
        this.control = new ControlCadastroFilme(this);

        setTitle("Cadastro de Filme");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        txtNome = new JTextField();
        txtDuracao = new JTextField(); 
        txtSinopse = new JTextField(); 
        txtValor = new JTextField();

        JButton btnCadastrar = new JButton("Cadastrar Filme");

        btnCadastrar.addActionListener(e -> control.cadastrarFilme());

        painel.add(new JLabel("Nome do Filme:"));           painel.add(txtNome);
        painel.add(new JLabel("Duração (ex: 120 min):"));   painel.add(txtDuracao);
        painel.add(new JLabel("Sinopse:"));                 painel.add(txtSinopse);
        painel.add(new JLabel("Valor do Ingresso (R$):"));  painel.add(txtValor);
        
        painel.add(new JLabel(""));
        painel.add(btnCadastrar);

        add(painel);
    }


    public Base getUsuarioLogado() {
        return usuarioLogado;
    }

    public JTextField getTxtNome() {
        return txtNome;
    }

    public JTextField getTxtDuracao() {
        return txtDuracao;
    }

    public JTextField getTxtSinopse() {
        return txtSinopse;
    }

    public JTextField getTxtValor() {
        return txtValor;
    }

    public void exibirMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exibirMensagemAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    public void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}