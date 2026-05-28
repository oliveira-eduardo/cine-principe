package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import control.ControlFilme;
import service.Sessao;

public class TelaFilmes extends JFrame {

    private TelaSalas salasCine;
    private Sessao sessao;
    private ControlFilme controlador; 

    public TelaFilmes(TelaSalas salas) {
        this.salasCine = salas;
        this.controlador = new ControlFilme(this); 
        
        setTitle("CINE PRÍNCIPE - Programação");
        setSize(915, 585); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        mostrarFilmes();
    }

    private void mostrarFilmes() {
        
        Color fundoCreme = new Color(244, 240, 233);       
        Color terracotaDestaque = new Color(166, 84, 55);  
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaMuted = new Color(125, 125, 125);       
        Color cinzaLinhaSutil = new Color(225, 220, 212); 

        
        JPanel janelaFilme = new JPanel(new BorderLayout(10, 10));
        janelaFilme.setBackground(fundoCreme);
        janelaFilme.setBorder(BorderFactory.createEmptyBorder(25, 35, 20, 35));

        
        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);

        JPanel painelLogout = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelLogout.setOpaque(false);

        JButton Logout = new JButton("DESLOGAR");
        Logout.setFont(new Font("Arial", Font.BOLD, 10));
        Logout.setForeground(terracotaDestaque);
        Logout.setBackground(fundoCreme);
        Logout.setFocusPainted(false);
        Logout.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 10));
        Logout.putClientProperty("JButton.buttonType", "square");

        Logout.addActionListener(e -> {
            controlador.deslogar();
        });
        painelLogout.add(Logout);

        JLabel tituloFilme = new JLabel("F I L M E S  E M  C A R T A Z", JLabel.CENTER);
        tituloFilme.setFont(new Font("Serif", Font.PLAIN, 22));
        tituloFilme.setForeground(grafiteTexto);

        Component spacerHeader = Box.createRigidArea(new Dimension(80, 0));

        parteSuperior.add(painelLogout, BorderLayout.WEST);
        parteSuperior.add(tituloFilme, BorderLayout.CENTER);
        parteSuperior.add(spacerHeader, BorderLayout.EAST);
        
        parteSuperior.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaLinhaSutil),
            BorderFactory.createEmptyBorder(0, 0, 20, 0)
        ));

        janelaFilme.add(parteSuperior, BorderLayout.NORTH);

        
        JButton voltar = new JButton("VOLTAR AS SALAS");
        voltar.setFont(new Font("Arial", Font.BOLD, 11));
        voltar.setForeground(grafiteTexto);
        voltar.setBackground(fundoCreme);
        voltar.setFocusPainted(false);
        voltar.putClientProperty("JButton.buttonType", "square");
        voltar.putClientProperty("Component.arc", 6);
        voltar.setBorder(new LineBorder(grafiteTexto, 1, true));
        voltar.setPreferredSize(new Dimension(150, 35));

        voltar.addActionListener(evt -> {
            controlador.voltarParaSalas();
        });

        JPanel painelBaixo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
        painelBaixo.setOpaque(false);
        painelBaixo.add(voltar);

        janelaFilme.add(painelBaixo, BorderLayout.SOUTH);

        
        JPanel gradeFilmes = new JPanel(new GridLayout(0, 2, 35, 35));
        gradeFilmes.setOpaque(false);

        for (int i = 0; i < salasCine.getSession().length; i++) {
            if (salasCine.getSession()[i] != null && salasCine.getSession()[i].getFilme() != null) {
                final int index = i;
                
                JButton botaoFilme = new JButton();
                botaoFilme.setLayout(new BorderLayout(22, 0));
                botaoFilme.setPreferredSize(new Dimension(390, 180));                     
                botaoFilme.setFocusPainted(false);
                
                
                botaoFilme.setBackground(fundoCreme); 
                botaoFilme.putClientProperty("JButton.buttonType", "square");
                botaoSalaArcProperty(botaoFilme, 4); 
                
                
                Border margemInterna = BorderFactory.createEmptyBorder(12, 12, 16, 12);
                botaoFilme.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaLinhaSutil), 
                    margemInterna
                ));
                
                
                if (salasCine.getSession()[i].getFilme().getNomeImagem() != null) {
                    ImageIcon imagem = new ImageIcon(salasCine.getSession()[i].getFilme().getNomeImagem());
                    Image imagemRed = gertScaledCardImage(imagem, 95, 150); 
                    JLabel infoFoto = new JLabel(new ImageIcon(imagemRed));
                    botaoFilme.add(infoFoto, BorderLayout.WEST);
                }

                
                JPanel infoFilme = new JPanel();
                infoFilme.setLayout(new BoxLayout(infoFilme, BoxLayout.Y_AXIS));
                infoFilme.setOpaque(false);

                JLabel Nome = new JLabel(salasCine.getSession()[i].getFilme().getNome().toUpperCase());
                Nome.setFont(new Font("Serif", Font.BOLD, 16)); 
                Nome.setForeground(grafiteTexto); 

                JLabel Genero = new JLabel(salasCine.getSession()[i].getFilme().getSinopse());
                Genero.setFont(new Font("Arial", Font.PLAIN, 12));
                Genero.setForeground(cinzaMuted); 

                JLabel Duracao = new JLabel(salasCine.getSession()[i].getFilme().getDuracao() + " min");
                Duracao.setFont(new Font("Arial", Font.PLAIN, 12));
                Duracao.setForeground(cinzaMuted);

                JLabel valor = new JLabel("Ingresso: R$ " + salasCine.getSession()[i].getFilme().getValor());
                valor.setFont(new Font("Arial", Font.PLAIN, 12));
                valor.setForeground(grafiteTexto); 

                JLabel hora = new JLabel("Horário: " + salasCine.getSession()[i].getHorario());
                hora.setFont(new Font("Arial", Font.BOLD, 13));
                hora.setForeground(terracotaDestaque); 

                
                infoFilme.add(Box.createVerticalGlue());
                infoFilme.add(Nome);
                infoFilme.add(Box.createRigidArea(new Dimension(0, 5)));
                infoFilme.add(Genero);
                infoFilme.add(Box.createRigidArea(new Dimension(0, 2)));
                infoFilme.add(Duracao);
                infoFilme.add(Box.createRigidArea(new Dimension(0, 2)));
                infoFilme.add(valor);
                infoFilme.add(Box.createRigidArea(new Dimension(0, 8)));
                infoFilme.add(hora);
                infoFilme.add(Box.createVerticalGlue());
                
                botaoFilme.add(infoFilme, BorderLayout.CENTER);

                botaoFilme.addActionListener(e -> {
                    controlador.selecionarFilme(index);
                });
                
                gradeFilmes.add(botaoFilme);
            }
        }

        
        JScrollPane painelRolagem = new JScrollPane(gradeFilmes);
        
        
        painelRolagem.setBorder(BorderFactory.createEmptyBorder()); 
        
        
        painelRolagem.setBackground(fundoCreme);
        painelRolagem.getViewport().setBackground(fundoCreme);
        painelRolagem.getVerticalScrollBar().setBackground(fundoCreme);
        
        
        painelRolagem.getVerticalScrollBar().setUnitIncrement(16); 

        janelaFilme.add(painelRolagem, BorderLayout.CENTER);
        add(janelaFilme);
    }

    private Image gertScaledCardImage(ImageIcon src, int w, int h) {
        return src.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
    }
    
    private void botaoSalaArcProperty(JButton btn, int val) {
        btn.putClientProperty("Component.arc", val);
    }

    public void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Deu erro na operação", JOptionPane.ERROR_MESSAGE);
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