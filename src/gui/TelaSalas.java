package gui;

import java.awt.BorderLayout;
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

    private void inicializarComponentes(){
        JPanel janela = new JPanel(new BorderLayout(5, 5));
        janela.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        //Titulo que aparece de forma centralizada na tela
        JLabel titulo = new JLabel("Selecione uma sala", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        //Adicionao o titulo na janela na parte de cima
        janela.add(titulo, BorderLayout.NORTH);
        add(janela);

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
                    // salva no bilhete o index da sessao e a sala
                    bilheteSala.setSala(salasCine[index]);
                    bilheteSala.setIndiceDaSessao(index);
                    Sessao [] session = salasCine[index].getSessoes();
                    //chama a funcao para mostrar os filmes
                    TelaFilmes mostrarFilme = new TelaFilmes(session, bilheteSala);
                    // deixa visivel a tela para mostrar sessao
                    mostrarFilme.setVisible(true);
                    //fecha a tela de salas
                    this.dispose();
                });
                //adiciona o botao de sala
                salasGrade.add(botaoSala);
            }
        }
        //aqui adiciona tudo na janela de Sala, cada botão criado
        janela.add(salasGrade, BorderLayout.CENTER);
        add(janela);
    }
}
