package gui;

import gui.TelaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarkLaf;

public class Teste_Tela {
    public static void main(String[] args) {
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
