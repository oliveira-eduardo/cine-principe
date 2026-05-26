package gui;

import java.awt.BorderLayout;
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

import model.Bilhete;
import model.Sala;
import service.Sessao;

public class TelaSalas extends JFrame {
    private Bilhete bilheteSala;
    private Sala [] salasCine;
    private Sessao [] session;
    public TelaSalas(Sala [] salas,Bilhete bilhete){
        this.salasCine = salas;
        this.bilheteSala = bilhete;
        try { //coloca o modo escuro direto
            UIManager.setLookAndFeel(new FlatDarkLaf());
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //cria a janela base 
        setTitle("Salas - Cinema POO");
        setSize(600, 450);
        // mudar para DISPOSE_ON_CLOSE depois dos testes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }
    public TelaSalas(){

    }
    private void inicializarComponentes(){
        JPanel janela = new JPanel(new BorderLayout(5, 5));
        janela.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);
        

        //Titulo que aparece de forma centralizada na tela
        JLabel titulo = new JLabel("Selecione uma sala", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        
        

        JPanel painelLogout = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
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
        parteSuperior.add(titulo, BorderLayout.CENTER);

        janela.add(parteSuperior, BorderLayout.NORTH);   

        //Cria uma painel para colocas as salas
        JPanel salasGrade = new JPanel(new GridLayout(0, 3, 15, 15));
        salasGrade.setOpaque(false);

        for(int i = 0; i<salasCine.length; i++){
            //um loop para criar um botao para cada sala que não estiver vazia
            if(salasCine[i] != null){
                final int index = i;
                //cria o botão da sala com o nome dela 
                JButton botaoSala = new JButton(salasCine[i].getNomeDaSala());
                botaoSala.setPreferredSize(new java.awt.Dimension(120, 60));
                botaoSala.addActionListener(e ->{
                    // se clicar no botao sala vai para area das sessoe que mostra os filmes
                    // salva no bilhete o index da sala
                    this.setSession(salasCine[index].getSessoes());
                    this.bilheteSala.setSala(salasCine[index]);
                    //chama a funcao para mostrar os filmes
                    TelaFilmes mostrarFilme = new TelaFilmes(this);
                    // deixa visivel a tela para mostrar sessao
                    mostrarFilme.setVisible(true);
                    //fecha a tela de salas
                    this.setVisible(false);
                });
                //adiciona o botao de sala
                salasGrade.add(botaoSala);
            }
        }
        
        //aqui adiciona tudo na janela de Sala, cada botão criado
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
