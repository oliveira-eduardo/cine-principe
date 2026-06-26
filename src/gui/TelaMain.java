package gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import resources.DatabaseReadyCheck;

public class TelaMain {

    public static void main(String[] args) {

        DatabaseReadyCheck.verificarEInicializarBancos();
        
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf()); // Ou FlatLightLaf()
        } catch (Exception ex) {
            System.err.println("Falha ao inicializar o FlatLaf");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaPrincipal tela = new TelaPrincipal();
                tela.setVisible(true);
            }
        });

    }
}