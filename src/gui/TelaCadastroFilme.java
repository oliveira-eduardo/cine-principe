package gui;

import model.Base;
import model.Filme;
import repository.GerenciaFilme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaCadastroFilme extends JFrame {

    private Base usuarioLogado;

    public TelaCadastroFilme(Base usuarioLogado) {
        this.usuarioLogado = usuarioLogado;

        setTitle("Cadastro de Filme");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JTextField txtNome = new JTextField();
        JTextField txtDuracao = new JTextField(); 
        JTextField txtSinopse = new JTextField(); 
        JTextField txtValor = new JTextField();   
        JTextField txtNomeImagem = new JTextField(); 

        JButton btnCadastrar = new JButton("Cadastrar Filme");

        btnCadastrar.addActionListener((ActionEvent e) -> {
            try {
                String nome = txtNome.getText();
                String duracao = txtDuracao.getText();
                String sinopse = txtSinopse.getText();
                String nomeImagem = txtNomeImagem.getText();
                
                // Pega o texto do valor e troca vírgula por ponto para evitar erros no Float.parseFloat
                String valorStr = txtValor.getText().replace(",", "."); 

                if (nome.isEmpty() || duracao.isEmpty() || valorStr.isEmpty() || sinopse.isEmpty() || nomeImagem.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                float valor = Float.parseFloat(valorStr);

                Filme novoFilme = new Filme(0, nome, duracao, sinopse, valor, nomeImagem);

                GerenciaFilme gerente = (GerenciaFilme) usuarioLogado;
                gerente.incluirFilme(novoFilme); 

                JOptionPane.showMessageDialog(this, 
                    "Filme '" + novoFilme.getNome() + "' cadastrado com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um valor numérico válido para o ingresso (Ex: 25.50).", "Erro de Formatação", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao processar os dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        painel.add(new JLabel("Nome do Filme:"));           painel.add(txtNome);
        painel.add(new JLabel("Duração (ex: 120 min):"));   painel.add(txtDuracao);
        painel.add(new JLabel("Sinopse:"));                 painel.add(txtSinopse);
        painel.add(new JLabel("Valor do Ingresso (R$):"));  painel.add(txtValor);
        painel.add(new JLabel("Nome da Imagem (Arquivo):"));painel.add(txtNomeImagem);
        
        painel.add(new JLabel(""));
        painel.add(btnCadastrar);

        add(painel);
    }
}