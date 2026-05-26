package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import model.Bilhete;
import model.Sala;
import service.Sessao;

public class TelaFilmes extends JFrame {

    private Sessao[] sessao;
    private Bilhete bilhete;
    private Sala [] salasCine;

    public TelaFilmes(Sessao[] sessao, Bilhete bilhete, Sala [] salasCine) {
        this.sessao = sessao;
        this.bilhete = bilhete;
        this.salasCine = salasCine;
        try { //coloca o modo escuro direto
            UIManager.setLookAndFeel(new FlatDarkLaf());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //cria a janela base 
        setTitle("Filmes da Sessão:");
        setSize(800, 500);
        // mudar para DISPOSE_ON_CLOSE depois dos testes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mostrarFilmes();
    }

    private void mostrarFilmes() {
        JPanel janelaFilme = new JPanel(new BorderLayout());
        janelaFilme.removeAll();

        JLabel tituloFilme = new JLabel(" Filmes em Cartaz ", JLabel.CENTER);
        tituloFilme.setFont(new Font("Arial", Font.BOLD, 16));
        
        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);

        
        JPanel painelLogout = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelLogout.setOpaque(false);
        
        JButton Logout = new JButton("Deslogar da Conta");
        Logout.setFont(new Font("Arial",Font.PLAIN, 12));

        Logout.addActionListener(e -> {
            TelaPrincipal telaPrincipal = new TelaPrincipal();
            telaPrincipal.setVisible(true);
            this.dispose();
        });
        painelLogout.add(Logout);        
        
        parteSuperior.add(painelLogout, BorderLayout.WEST);
        parteSuperior.add(tituloFilme, BorderLayout.CENTER);

        janelaFilme.add(parteSuperior, BorderLayout.NORTH);

        
        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> {
            TelaSalas sala = new TelaSalas(salasCine, bilhete);
            sala.setVisible(true);
            this.dispose();
        });

        JPanel painelBaixo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBaixo.add(voltar);

        janelaFilme.add(painelBaixo, BorderLayout.SOUTH);

        JPanel gradeFilmes = new JPanel(new GridLayout(0, 2, 15, 25));

        for (int i = 0; i < sessao.length; i++) {
            if (sessao[i] != null && sessao[i].getFilme() != null) {
                final int index = i;
                JButton botaoFilme = new JButton();
                botaoFilme.setLayout(new BorderLayout(15, 0));
                botaoFilme.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 10));
                botaoFilme.setPreferredSize(new Dimension(200,180));
                if (sessao[i].getFilme().getNomeImagem() != null) {
                    ImageIcon imagem = new ImageIcon(sessao[i].getFilme().getNomeImagem());
                    java.awt.Image imagemRed = imagem.getImage().getScaledInstance(80, 115, Image.SCALE_SMOOTH);
                    JLabel infoFoto = new JLabel(new ImageIcon(imagemRed));
                    botaoFilme.add(infoFoto, BorderLayout.WEST);
                }
                JPanel infoFilme = new JPanel(new GridLayout(3,1,5,5));
                infoFilme.setOpaque(false);

                JLabel Nome = new JLabel(sessao[i].getFilme().getNome());
                Nome.setFont(new Font("Arial", Font.BOLD, 16));
                
                JLabel Genero = new JLabel("Genero: " + sessao[i].getFilme().getSinopse());
                Genero.setFont(new Font("Arial", Font.BOLD, 12));

                JLabel Duracao = new JLabel("Duração: " + sessao[i].getFilme().getDuracao());
                Duracao.setFont(new Font("Arial", Font.BOLD, 12));

                infoFilme.add(Nome);
                infoFilme.add(Genero);
                infoFilme.add(Duracao);
                botaoFilme.add(infoFilme, BorderLayout.CENTER);

                botaoFilme.addActionListener(e -> {
                    bilhete.setIndiceDaSessao(index);
                    TelaCadeira cadeiras = new TelaCadeira(sessao[index]);
                    cadeiras.setVisible(true);
                    this.dispose();

                }
                );
                gradeFilmes.add(botaoFilme);
            }
        }
        janelaFilme.add(gradeFilmes, BorderLayout.CENTER);
       JScrollPane painelRolagem = new JScrollPane(gradeFilmes);

        painelRolagem.setBorder(null);
        painelRolagem.setOpaque(false);
        painelRolagem.getViewport().setOpaque(false);

        janelaFilme.add(painelRolagem, BorderLayout.CENTER); 
        
        add(janelaFilme);

    }
}
