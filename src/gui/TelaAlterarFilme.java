package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.ControlAlterarFilme;
import model.Base;
import model.Filme;

public class TelaAlterarFilme extends JFrame {

    private Filme filme;
    private Base usuario;
    private ControlAlterarFilme controlador; 

    public TelaAlterarFilme(Base usuarioLogado, Filme filmeSelecionado) {
        this.filme = filmeSelecionado;
        this.usuario = usuarioLogado;
        this.controlador = new ControlAlterarFilme(this);
        
        setTitle("Alterar Filme - " + filme.getNome());
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painel = new JPanel(new GridLayout(7, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JTextField txtId = new JTextField();
        JTextField txtNome = new JTextField();
        JTextField txtDuracao = new JTextField(); 
        JTextField txtSinopse = new JTextField(); 
        JTextField txtValor = new JTextField();   
        JTextField txtNomeImagem = new JTextField(); 

        txtId.setText(String.valueOf(filme.getId()));
        txtId.setEditable(false);
        txtId.setToolTipText("O ID é gerado pelo banco e não pode ser alterado.");
        
        txtNome.setText(filme.getNome());
        txtDuracao.setText(filme.getDuracao());
        txtSinopse.setText(filme.getSinopse());
        txtValor.setText(String.valueOf(filme.getValor()));
        txtNomeImagem.setText(filme.getNomeImagem());

        JButton btnSalvar = new JButton("Salvar Alterações");

        btnSalvar.addActionListener((ActionEvent e) -> {
            boolean sucesso = controlador.salvarAlteracoes(
                txtNome.getText(),
                txtDuracao.getText(),
                txtSinopse.getText(),
                txtValor.getText(),
                txtNomeImagem.getText(),
                this.filme,
                this.usuario
            );

            if (sucesso) {
                dispose(); 
            }
        });

        painel.add(new JLabel("ID (Não editável):"));       painel.add(txtId);
        painel.add(new JLabel("Nome do Filme:"));           painel.add(txtNome);
        painel.add(new JLabel("Duração:"));                 painel.add(txtDuracao);
        painel.add(new JLabel("Sinopse:"));                 painel.add(txtSinopse);
        painel.add(new JLabel("Valor do Ingresso (R$):"));  painel.add(txtValor);
        painel.add(new JLabel("Nome da Imagem:"));          painel.add(txtNomeImagem);
        
        painel.add(new JLabel(""));
        painel.add(btnSalvar);

        add(painel);
    }

    // Métodos utilitários de renderização de caixa de mensagem (padrão MVC)
    public void exibirMensagemAviso(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public void exibirMensagemErro(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void exibirMensagemInformativa(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}