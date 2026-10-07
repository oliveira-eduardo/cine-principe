package gui;

import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.Deque;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/** Mantém a aplicação em uma única janela e substitui apenas sua interface. */
public final class Navegacao {

    private static JFrame janela;
    private static JFrame atual;
    private static final Deque<JFrame> historico = new ArrayDeque<>();

    private Navegacao() {
    }

    public static void iniciar(JFrame tela) {
        if (janela == null) {
            janela = new JFrame();
            atual = tela;
            janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            janela.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {
                    if (historico.isEmpty()) {
                        fechar();
                    } else {
                        voltar();
                    }
                }
            });
            mostrar(tela);
            return;
        }
        exibir(tela);
    }

    public static void exibir(JFrame tela) {
        if (janela == null) {
            iniciar(tela);
            return;
        }

        if (atual != null && atual != tela) {
            historico.push(atual);
        }
        atual = tela;
        mostrar(tela);
    }

    public static void voltar() {
        if (!historico.isEmpty()) {
            atual = historico.pop();
            mostrar(atual);
        }
    }

    private static void mostrar(JFrame tela) {
        janela.setTitle(tela.getTitle());
        janela.setContentPane(tela.getContentPane());
        janela.setJMenuBar(tela.getJMenuBar());

        Dimension tamanho = tela.getSize();
        janela.setSize(tamanho);
        janela.setMinimumSize(tela.getMinimumSize());
        janela.setLocationRelativeTo(null);
        janela.revalidate();
        janela.repaint();
        janela.setVisible(true);
    }

    public static void fechar() {
        if (janela != null) {
            janela.dispose();
            janela = null;
            atual = null;
            historico.clear();
        }
    }
}
