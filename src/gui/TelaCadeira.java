package gui;

import java.util.ArrayList;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import exceptions.VendasException;
import model.Bilhete;
import service.Sessao;

public class TelaCadeira extends JFrame {

    private TelaFilmes telafilmes;
    private Sessao sessaoAtual;
    private int[][] matrizCadeiras;
    private ArrayList<Bilhete> bilhetes = new ArrayList<Bilhete>();
    private ArrayList<int[]> coordenadasEscolhidas = new ArrayList<int[]>();

    public TelaCadeira(TelaFilmes telafilmes) {
        this.telafilmes = telafilmes;
        this.sessaoAtual = telafilmes.getSessao();
        this.matrizCadeiras = sessaoAtual.getCadeira();
        try { //coloca o modo escuro direto
            UIManager.setLookAndFeel(new FlatDarkLaf());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //cria a janela base 
        setTitle("Cadeiras disponiveis:");
        setSize(850, 600);
        // mudar para DISPOSE_ON_CLOSE depois dos testes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mostrarCadeiras();
    }

    private void mostrarCadeiras() {
        JPanel janela = new JPanel(new BorderLayout(10, 10));
        janela.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel painelSuperior = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Escolha os assentos: " + sessaoAtual.getFilme().getNome(), JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        painelSuperior.add(titulo, BorderLayout.CENTER);
        janela.add(painelSuperior, BorderLayout.NORTH);

        JPanel painelProjecao = new JPanel(new BorderLayout());
        painelProjecao.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        JLabel Tela = new JLabel("       TELA DO CINEMA          ", JLabel.CENTER);
        Tela.setFont(new Font("Arial", Font.BOLD, 16));
        Tela.setForeground(Color.LIGHT_GRAY);

        painelProjecao.add(Tela, BorderLayout.CENTER);

        JPanel topoAgrupado = new JPanel(new GridLayout(2, 1));
        topoAgrupado.add(painelSuperior);
        topoAgrupado.add(painelProjecao);

        janela.add(topoAgrupado, BorderLayout.NORTH);

        JPanel painelMapa = new JPanel(new GridLayout(11, 16, 6, 6));
        painelMapa.setOpaque(false);

        char[] letrasLinhas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 16; j++) {

                if (i == 0 && j == 0) {
                    painelMapa.add(new JLabel(""));
                    continue;
                }

                if (i == 0) {
                    JLabel labelNumero = new JLabel(String.valueOf(j), JLabel.CENTER);
                    labelNumero.setFont(new Font("Arial", Font.BOLD, 11));
                    labelNumero.setForeground(Color.LIGHT_GRAY);
                    painelMapa.add(labelNumero);
                    continue;
                }

                if (j == 0) {
                    JLabel labelLetra = new JLabel(String.valueOf(letrasLinhas[i - 1]), JLabel.CENTER);
                    labelLetra.setFont(new Font("Arial", Font.BOLD, 12));
                    labelLetra.setForeground(Color.LIGHT_GRAY);
                    painelMapa.add(labelLetra);
                    continue;
                }

                final int linhaMatriz = i - 1;
                final int colunaMatriz = j - 1;

                String nomeAssento = letrasLinhas[linhaMatriz] + String.valueOf(colunaMatriz + 1);
                JButton botaoCadeira = new JButton("");

                if (matrizCadeiras[linhaMatriz][colunaMatriz] == 1) {
                    botaoCadeira.setBackground(new Color(60, 60, 60));
                } else {
                    botaoCadeira.setBackground(new Color(160, 160, 160));
                }

                botaoCadeira.addActionListener(e -> {
                    try {
                        if (matrizCadeiras[linhaMatriz][colunaMatriz] == 1) {
                            throw new VendasException("A poltrona " + nomeAssento + " já foi selecionada");
                        }

                        if (sessaoAtual.escolhaCadeira(linhaMatriz, colunaMatriz)) {
                            botaoCadeira.setBackground(new Color(230, 126, 34));

                            int[] coordenadas = new int[2];

                            
                            coordenadas[0] = linhaMatriz;  
                            coordenadas[1] = colunaMatriz; 

                            coordenadasEscolhidas.add(coordenadas);

                            Bilhete novoBilhete = new Bilhete();
                            novoBilhete.setUsuario(telafilmes.getSalasCine().getBilheteSala().getUsuario());
                            novoBilhete.setSala(telafilmes.getSalasCine().getBilheteSala().getSala());
                            novoBilhete.setIndiceDaSessao(telafilmes.getSalasCine().getBilheteSala().getIndiceDaSessao());
                            novoBilhete.setCadeira(nomeAssento);
                            bilhetes.add(novoBilhete);

                        }

                    } catch (VendasException ex) {
                        JOptionPane.showMessageDialog(this, ex.getMessage(), "Assento Indisponível", JOptionPane.WARNING_MESSAGE);
                    }
                });

                painelMapa.add(botaoCadeira);
            }
        }
        janela.add(painelMapa, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new BorderLayout());

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            for (int i = 0; i < coordenadasEscolhidas.size(); i++) {
                int[] coord = coordenadasEscolhidas.get(i);

                int linha = coord[0];
                int coluna = coord[1];
                sessaoAtual.limparCadeira(linha, coluna);
            }
            bilhetes.clear();
            coordenadasEscolhidas.clear();
            telafilmes.setVisible(true);
            this.dispose();
        });

        JButton botaoAvancar = new JButton("Avançar para os Snacks");
        botaoAvancar.addActionListener(e -> {
            try {
                if (bilhetes.isEmpty()) {
                    throw new VendasException("Não é possível avançar, escolha pelo menos 1 assento");
                }
                TelaProdutos lanches = new TelaProdutos(this);
                lanches.setVisible(true);
                this.setVisible(false);
            } catch (VendasException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Nenhum Assento Selecionado", JOptionPane.WARNING_MESSAGE);
            }
        });

        painelBotoes.add(botaoVoltar, BorderLayout.WEST);
        painelBotoes.add(botaoAvancar, BorderLayout.EAST);
        janela.add(painelBotoes, BorderLayout.SOUTH);

        add(janela);
    }

    public TelaFilmes getTelafilmes() {
        return telafilmes;
    }

    public void setTelafilmes(TelaFilmes telafilmes) {
        this.telafilmes = telafilmes;
    }

    public ArrayList<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public void setBilhetes(ArrayList<Bilhete> bilhetes) {
        this.bilhetes = bilhetes;
    }
}
