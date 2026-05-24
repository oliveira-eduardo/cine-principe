package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

// Só o basico por enquanto

public class TelaPrincipal extends JFrame {
    public TelaPrincipal(){
        setTitle("Cinema POO");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // so pra centralizar

        inicializarComponentes();
    }
    private void inicializarComponentes() {
        // Painel principal
        JPanel painel = new JPanel(new BorderLayout());
        
        JLabel inicial = new JLabel("Bem-vindo Cinema POO", JLabel.CENTER);
        painel.add(inicial, BorderLayout.CENTER);

        add(painel); // tem que colocar o painel na janela
    }
}


// Import na main + o flatlaf
    //import view.TelaPrincipal;
    //import javax.swing.SwingUtilities;
    //import javax.swing.UIManager;
    //import com.formdev.flatlaf.FlatDarkLaf se for o preto
