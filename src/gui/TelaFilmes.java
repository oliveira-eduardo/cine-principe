package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.time.LocalTime;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.formdev.flatlaf.FlatDarkLaf;

import exceptions.VendasException;
import service.Sessao;

public class TelaFilmes extends JFrame {

    private TelaSalas salasCine;
    private Sessao sessao;

    public TelaFilmes(TelaSalas salas) {
        this.salasCine = salas;
        
        try { //coloca o modo escuro direto
            UIManager.setLookAndFeel(new FlatDarkLaf());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //cria a janela base 
        setTitle("Filmes da Sessão:");
        setSize(850, 550); 
        // mudar para DISPOSE_ON_CLOSE depois dos testes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        mostrarFilmes();
    }

    private void mostrarFilmes() {
        JPanel janelaFilme = new JPanel(new BorderLayout(10, 10));
        janelaFilme.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel tituloFilme = new JLabel("Filmes em Cartaz", JLabel.CENTER);
        tituloFilme.setFont(new Font("Arial", Font.BOLD, 18));
        tituloFilme.setForeground(Color.WHITE);

        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);

        JPanel painelLogout = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelLogout.setOpaque(false);

        JButton Logout = new JButton("Deslogar da Conta");
        Logout.setFont(new Font("Arial", Font.PLAIN, 12));
        
        
        Logout.putClientProperty("JButton.buttonType", "toolBarButton");
        Logout.setForeground(Color.LIGHT_GRAY);

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
        voltar.setFont(new Font("Arial", Font.BOLD, 12));
        voltar.putClientProperty("JButton.arc", 8); 

        JPanel painelBaixo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
        painelBaixo.setOpaque(false);
        painelBaixo.add(voltar);

        janelaFilme.add(painelBaixo, BorderLayout.SOUTH);

        // Grade de filmes organizada em 2 colunas
        JPanel gradeFilmes = new JPanel(new GridLayout(0, 2, 20, 25));
        gradeFilmes.setOpaque(false);

        for (int i = 0; i < salasCine.getSession().length; i++) {
            if (salasCine.getSession()[i] != null && salasCine.getSession()[i].getFilme() != null) {
                final int index = i;
                
                JButton botaoFilme = new JButton();
                botaoFilme.setLayout(new BorderLayout(18, 0));
                botaoFilme.setPreferredSize(new Dimension(380, 180));                       
                botaoFilme.setBackground(new Color(45, 48, 50));             
                
                
                Border margemInterna = BorderFactory.createEmptyBorder(15, 15, 15, 15);
                botaoFilme.setBorder(BorderFactory.createCompoundBorder(botaoFilme.getBorder(), margemInterna));
                

                if (salasCine.getSession()[i].getFilme().getNomeImagem() != null) {
                    ImageIcon imagem = new ImageIcon(salasCine.getSession()[i].getFilme().getNomeImagem());
                    Image imagemRed = imagem.getImage().getScaledInstance(85, 140, Image.SCALE_SMOOTH);
                    JLabel infoFoto = new JLabel(new ImageIcon(imagemRed));
                    botaoFilme.add(infoFoto, BorderLayout.WEST);
                }

                JPanel infoFilme = new JPanel(new GridLayout(5, 1, 2, 2));
                infoFilme.setOpaque(false);

                JLabel Nome = new JLabel(salasCine.getSession()[i].getFilme().getNome());
                Nome.setFont(new Font("Arial", Font.BOLD, 16));
                Nome.setForeground(Color.WHITE); 

                JLabel Genero = new JLabel("Gênero: " + salasCine.getSession()[i].getFilme().getSinopse());
                Genero.setFont(new Font("Arial", Font.PLAIN, 13));
                Genero.setForeground(new Color(180, 185, 190)); 

                JLabel Duracao = new JLabel("Duração: " + salasCine.getSession()[i].getFilme().getDuracao());
                Duracao.setFont(new Font("Arial", Font.PLAIN, 13));
                Duracao.setForeground(new Color(180, 185, 190));

                JLabel hora = new JLabel("Horário: " + salasCine.getSession()[i].getHorario());
                hora.setFont(new Font("Arial", Font.BOLD, 13));
                hora.setForeground(new Color(241, 196, 15)); 

                JLabel valor = new JLabel("Valor: " + salasCine.getSession()[i].getFilme().getValor() + "$");
                valor.setFont(new Font("Arial", Font.BOLD, 13));
                valor.setForeground(new Color(241, 196, 15)); 

                infoFilme.add(Nome);
                infoFilme.add(Genero);
                infoFilme.add(Duracao);
                infoFilme.add(valor);
                infoFilme.add(hora);
                botaoFilme.add(infoFilme, BorderLayout.CENTER);

                botaoFilme.addActionListener(e -> {
                    try {
                        LocalTime horarioAtual = LocalTime.now();
                        String horario = salasCine.getSession()[index].getHorario();
                        LocalTime horarioSessao = LocalTime.parse(horario);

                        if (horarioAtual.isAfter(horarioSessao)) {
                            throw new VendasException("Erro ao escolher o filme");
                        }

                        salasCine.getBilheteSala().setIndiceDaSessao(index);
                        this.setSessao(salasCine.getSession()[index]);
                        TelaCadeira cadeiras = new TelaCadeira(this);
                        cadeiras.setVisible(true);
                        this.setVisible(false);
                    } catch (VendasException ex) {
                        JOptionPane.showMessageDialog(this, "Não é possível comprar o bilhete, o filme já não está mais sendo exibido.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                });
                
                voltar.addActionListener(evt -> {
                    salasCine.setVisible(true);
                    this.dispose();
                });
                
                gradeFilmes.add(botaoFilme);
            }
        }

        JScrollPane painelRolagem = new JScrollPane(gradeFilmes);
        painelRolagem.setBorder(null);
        painelRolagem.setOpaque(false);
        painelRolagem.getViewport().setOpaque(false);

        janelaFilme.add(painelRolagem, BorderLayout.CENTER);
        add(janelaFilme);
    }

    public TelaSalas getSalasCine() {
        return salasCine;
    }

    public void setSalasCine(TelaSalas salasCine) {
        this.salasCine = salasCine;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
}