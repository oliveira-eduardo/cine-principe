package gui;

import data.FilmeData;
import model.Sala;

public class Teste_Tela {

    public static void main(String[] args) {
        //     try {
        //        UIManager.setLookAndFeel(new FlatDarkLaf()); // Ou FlatLightLaf()
        //     } catch (Exception ex) {
        //         System.err.println("Falha ao inicializar o FlatLaf");
        //     }

        // SwingUtilities.invokeLater(new Runnable() {
        //         @Override
        //         public void run() {
        //             TelaPrincipal tela = new TelaPrincipal();
        //             tela.setVisible(true);
        //         }
        //     });
        // Cria o vetor fixo com a quantidade de salas do seu cinema
        FilmeData.connect();
        Sala[] minhasSalas = new Sala[4];
        minhasSalas[0] = new Sala("Sala 01 - IMAX");
        minhasSalas[1] = new Sala("Sala 02 - Sala 3D");
        minhasSalas[2] = new Sala("Sala 03 - Sala comum");

        TelaSala inicial = new TelaSala(minhasSalas);
        inicial.setVisible(true);

    }
}
