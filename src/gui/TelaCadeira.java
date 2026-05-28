package gui;

import java.util.ArrayList;
import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

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

        
        setTitle("CINE PRÍNCIPE - Assentos");
        setSize(850, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);
        mostrarCadeiras();
    }

    private void mostrarCadeiras() {
        
        Color fundoCreme = new Color(244, 240, 233);       
        Color terracotaDestaque = new Color(166, 84, 55);  
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaMuted = new Color(130, 130, 130);       
        Color assentoOcupado = new Color(185, 180, 172); 

        
        JPanel janela = new JPanel(new BorderLayout(10, 10));
        janela.setBackground(fundoCreme);
        janela.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.setOpaque(false);
        
        JLabel titulo = new JLabel("Escolha os assentos: " + sessaoAtual.getFilme().getNome(), JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 18));
        titulo.setForeground(grafiteTexto);
        painelSuperior.add(titulo, BorderLayout.CENTER);

        JPanel painelProjecao = new JPanel(new BorderLayout());
        painelProjecao.setOpaque(false);
        painelProjecao.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        JLabel Tela = new JLabel("——————————  T E L A  D O  C I N E M A  ——————————", JLabel.CENTER);
        Tela.setFont(new Font("Arial", Font.BOLD, 12));
        Tela.setForeground(cinzaMuted);
        painelProjecao.add(Tela, BorderLayout.CENTER);

        JPanel topoAgrupado = new JPanel(new GridLayout(2, 1));
        topoAgrupado.setOpaque(false);
        topoAgrupado.add(painelSuperior);
        topoAgrupado.add(painelProjecao);

        janela.add(topoAgrupado, BorderLayout.NORTH);

        
        JPanel salasGradeContainer = new JPanel(new BorderLayout());
        salasGradeContainer.setOpaque(false);
        
        JPanel painelMapa = new JPanel(new GridLayout(11, 16, 6, 6));
        painelMapa.setOpaque(false);

        char[] letrasLinhas = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 16; j++) {

                if (i == 0 && j == 0) {
                    JPanel espacoVazio = new JPanel();
                    espacoVazio.setOpaque(false);
                    painelMapa.add(espacoVazio);
                    continue;
                }

                if (i == 0) {
                    JLabel labelNumero = new JLabel(String.valueOf(j), JLabel.CENTER);
                    labelNumero.setFont(new Font("Arial", Font.BOLD, 11));
                    labelNumero.setForeground(cinzaMuted);
                    painelMapa.add(labelNumero);
                    continue;
                }

                if (j == 0) {
                    JLabel labelLetra = new JLabel(String.valueOf(letrasLinhas[i - 1]), JLabel.CENTER);
                    labelLetra.setFont(new Font("Arial", Font.BOLD, 12));
                    labelLetra.setForeground(cinzaMuted);
                    painelMapa.add(labelLetra);
                    continue;
                }

                
                final int linhaMatriz = i - 1;
                final int colunaMatriz = j - 1;

                String nomeAssento = letrasLinhas[linhaMatriz] + String.valueOf(colunaMatriz + 1);
                JButton botaoCadeira = new JButton("");
                botaoCadeira.setFocusPainted(false);
                
                
                botaoCadeira.putClientProperty("JButton.buttonType", "square");
                botaoCadeira.putClientProperty("Component.arc", 6);

                
                if (matrizCadeiras[linhaMatriz][colunaMatriz] == 1) {
                    botaoCadeira.setBackground(assentoOcupado); 
                    botaoCadeira.setBorder(BorderFactory.createEmptyBorder());
                } else {
                    botaoCadeira.setBackground(Color.WHITE); 
                    botaoCadeira.setBorder(new LineBorder(new Color(210, 205, 195), 1, true));
                }

                botaoCadeira.addActionListener(e -> {
                    
                    boolean sucesso = controlador.selecionarAssento(linhaMatriz, colunaMatriz, nomeAssento);
                    if (sucesso) {
                        botaoCadeira.setBackground(terracotaDestaque); 
                        botaoCadeira.setBorder(BorderFactory.createEmptyBorder());
                    }
                });

                painelMapa.add(botaoCadeira);
            }
        }
        
        salasGradeContainer.add(painelMapa, BorderLayout.CENTER);
        janela.add(salasGradeContainer, BorderLayout.CENTER);

        
        JPanel painelBotoes = new JPanel(new BorderLayout());
        painelBotoes.setOpaque(false);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoVoltar.setForeground(grafiteTexto);
        botaoVoltar.setBackground(fundoCreme);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.putClientProperty("JButton.buttonType", "square");
        botaoVoltar.putClientProperty("Component.arc", 6);
        botaoVoltar.setBorder(new LineBorder(grafiteTexto, 1, true));
        botaoVoltar.setPreferredSize(new Dimension(100, 35));

        botaoVoltar.addActionListener(e -> {
            controlador.voltar(); 
        });

        JButton botaoAvancar = new JButton("Avançar para os Snacks");
        botaoAvancar.setFont(new Font("Arial", Font.BOLD, 12));
        botaoAvancar.setForeground(Color.WHITE);
        botaoAvancar.setBackground(terracotaDestaque); 
        botaoAvancar.setFocusPainted(false);
        botaoAvancar.setBorderPainted(false);
        botaoAvancar.putClientProperty("JButton.buttonType", "square");
        botaoAvancar.putClientProperty("Component.arc", 8);
        botaoAvancar.setPreferredSize(new Dimension(200, 35));

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