package gui;

import model.Base;
import model.Filme;
import repository.GerenciaFilme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaAlterarFilme extends JFrame {

    private Filme filme;
    private Base usuario;

    public TelaAlterarFilme(Base usuarioLogado, Filme filmeSelecionado) {
        this.filme = filmeSelecionado;
        this.usuario = usuarioLogado;
        
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
            try {
                String nome = txtNome.getText();
                String duracao = txtDuracao.getText();
                String sinopse = txtSinopse.getText();
                String nomeImagem = txtNomeImagem.getText();
                
                String valorStr = txtValor.getText().replace(",", "."); 

                if (nome.isEmpty() || duracao.isEmpty() || valorStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Nome, Duração e Valor são obrigatórios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                float valor = Float.parseFloat(valorStr);

                filme.setNome(nome);
                filme.setDuracao(duracao);
                filme.setSinopse(sinopse);
                filme.setValor(valor);
                filme.setNomeImagem(nomeImagem);

                GerenciaFilme gerente = (GerenciaFilme) usuario;
                gerente.incluirFilme(filme); 

                JOptionPane.showMessageDialog(this, "Filme alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose(); 

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um valor numérico válido para o ingresso (Ex: 25.50).", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao processar as alterações: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
}