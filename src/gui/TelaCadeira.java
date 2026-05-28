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

import control.ControlCadeira; 
import model.Bilhete;
import service.Sessao;

public class TelaCadeira extends JFrame {

    private TelaFilmes telafilmes;
    private Sessao sessaoAtual;
    private int[][] matrizCadeiras;
    private ArrayList<Bilhete> bilhetes = new ArrayList<Bilhete>();
    private ArrayList<int[]> coordenadasEscolhidas = new ArrayList<int[]>();
    private ControlCadeira controlador; 

    public TelaCadeira(TelaFilmes telafilmes) {
        this.telafilmes = telafilmes;
        this.sessaoAtual = telafilmes.getSessao();
        this.matrizCadeiras = sessaoAtual.getCadeira();
        this.controlador = new ControlCadeira(this); 

        try { // coloca o modo escuro direto
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        setTitle("Cadeiras disponiveis:");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
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
                    boolean sucesso = controlador.selecionarAssento(linhaMatriz, colunaMatriz, nomeAssento);
                    if (sucesso) {
                        botaoCadeira.setBackground(new Color(230, 126, 34)); 
                    }
                });

                painelMapa.add(botaoCadeira);
            }
        }
        janela.add(painelMapa, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new BorderLayout());

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(e -> {
            controlador.voltar();
        });

        JButton botaoAvancar = new JButton("Avançar para os Snacks");
        botaoAvancar.addActionListener(e -> {
            controlador.avancarParaSnacks();
        });

        painelBotoes.add(botaoVoltar, BorderLayout.WEST);
        painelBotoes.add(botaoAvancar, BorderLayout.EAST);
        janela.add(painelBotoes, BorderLayout.SOUTH);

        add(janela);
    }

    public void exibirMensagemAviso(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    // Getters e Setters para comunicação limpa com o Controlador
    public TelaFilmes getTelafilmes() {
        return telafilmes;
    }

    public void setTelafilmes(TelaFilmes telafilmes) {
        this.telafilmes = telafilmes;
    }

    public Sessao getSessaoAtual() {
        return sessaoAtual;
    }

    public int[][] getMatrizCadeiras() {
        return matrizCadeiras;
    }

    public ArrayList<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public ArrayList<int[]> getCoordenadasEscolhidas() {
        return coordenadasEscolhidas;
    }
}