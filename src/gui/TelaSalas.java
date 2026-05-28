package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.ControlSalas; 
import model.Bilhete;
import model.Sala;
import service.Sessao;

public class TelaSalas extends JFrame {
    private Bilhete bilheteSala;
    private Sala[] salasCine;
    private Sessao[] session;
    private ControlSalas controlador; 

    public TelaSalas(Sala[] salas, Bilhete bilhete) {
        this.salasCine = salas;
        this.bilheteSala = bilhete;
        this.controlador = new ControlSalas(this);
        
        setTitle("CINE PRÍNCIPE - Salas");
        setSize(700, 500); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    public TelaSalas() {
    }

    private void inicializarComponentes() {
        
        Color fundoCreme = new Color(244, 240, 233);       
        Color terracotaDestaque = new Color(166, 84, 55);  
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaBorda = new Color(210, 205, 195);

        
        JPanel janela = new JPanel(new BorderLayout());
        janela.setBackground(fundoCreme);
        janela.setBorder(BorderFactory.createEmptyBorder(25, 45, 45, 45));

        
        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);

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

        JLabel titulo = new JLabel("E S C O L H A  U M A  S A L A", JLabel.CENTER);
        titulo.setFont(new Font("Serif", Font.PLAIN, 22)); 
        titulo.setForeground(grafiteTexto);

        Component spacer = Box.createRigidArea(new Dimension(80, 0));

        parteSuperior.add(Logout, BorderLayout.WEST);
        parteSuperior.add(titulo, BorderLayout.CENTER);
        parteSuperior.add(spacer, BorderLayout.EAST);
        
        parteSuperior.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(215, 210, 200)),
            BorderFactory.createEmptyBorder(0, 0, 20, 0)
        ));

        janela.add(parteSuperior, BorderLayout.NORTH);   

        
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setOpaque(false);

        
        JPanel salasGrade = new JPanel(new GridLayout(0, 2, 25, 25));
        salasGrade.setOpaque(false);
        
        salasGrade.setMaximumSize(new Dimension(480, 180)); 

        for (int i = 0; i < salasCine.length; i++) {
            if (salasCine[i] != null) {
                final int index = i;
                
                JButton botaoSala = new JButton(salasCine[i].getNomeDaSala().toUpperCase());
                botaoSala.setFocusPainted(false);
                
                botaoSala.setBackground(Color.WHITE);
                botaoSala.setForeground(grafiteTexto);
                botaoSala.setFont(new Font("Arial", Font.BOLD, 13));
                
                
                botaoSala.putClientProperty("JButton.buttonType", "square");
                botaoSala.putClientProperty("Component.arc", 8);
                
                botaoSala.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(cinzaBorda, 1, true),
                    BorderFactory.createEmptyBorder(20, 10, 20, 10) 
                ));
                
                botaoSala.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        botaoSala.setBackground(new Color(250, 248, 245));
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        botaoSala.setBackground(Color.WHITE);
                    }
                });

                botaoSala.addActionListener(e -> {
                    controlador.selecionarSala(index);
                });
                
                salasGrade.add(botaoSala);
            }
        }
        
        painelCentral.add(Box.createVerticalGlue());
        painelCentral.add(salasGrade);
        painelCentral.add(Box.createVerticalGlue());

        janela.add(painelCentral, BorderLayout.CENTER);
        add(janela);
    }

    public Bilhete getBilheteSala() {
        return bilheteSala;
    }

    public void setBilheteSala(Bilhete bilheteSala) {
        this.bilheteSala = bilheteSala;
    }

    public Sala[] getSalasCine() {
        return salasCine;
    }

    public void setSalasCine(Sala[] salasCine) {
        this.salasCine = salasCine;
    }

    public Sessao[] getSession() {
        return session;
    }

    public void setSession(Sessao[] session) {
        this.session = session;
    }
}