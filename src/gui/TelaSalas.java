package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

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
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        try { // coloca o modo escuro direto
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        setTitle("Salas - Cinema POO");
        setSize(650, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    public TelaSalas() {
    }

    private void inicializarComponentes() {
        JPanel janela = new JPanel(new BorderLayout(5, 5));
        janela.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);

        // Titulo que aparece na tela
        JLabel titulo = new JLabel("Selecione uma sala", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));       

        JPanel painelLogout = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        painelLogout.setOpaque(false);

        JButton Logout = new JButton("Deslogar da Conta");
        Logout.setFont(new Font("Arial", Font.PLAIN, 12));

        
        Logout.addActionListener(e -> {
            controlador.deslogar();
        });
        painelLogout.add(Logout);

        parteSuperior.add(painelLogout, BorderLayout.WEST);
        parteSuperior.add(titulo, BorderLayout.CENTER);

        janela.add(parteSuperior, BorderLayout.NORTH);   

        // Cria uma painel para colocar as salas
        JPanel salasGrade = new JPanel(new GridLayout(0, 4, 5, 15));
        salasGrade.setOpaque(false);

        for (int i = 0; i < salasCine.length; i++) {
            // um loop para criar um botao para cada sala que não estiver vazia
            if (salasCine[i] != null) {
                final int index = i;
                
                // cria o botão da sala com o nome dela 
                JButton botaoSala = new JButton(salasCine[i].getNomeDaSala());
                botaoSala.setPreferredSize(new java.awt.Dimension(120, 60));
                botaoSala.setFocusPainted(false);
                botaoSala.setBackground(new Color(45, 48, 50));
                botaoSala.setFont(new Font("Arial", Font.BOLD, 20));

                
                botaoSala.addActionListener(e -> {
                    controlador.selecionarSala(index);
                });
                
                
                salasGrade.add(botaoSala);
            }
        }
        
        
        janela.add(salasGrade, BorderLayout.CENTER);
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