package control;
import gui.TelaFilmes;
import gui.TelaSalas;

public class ControlSalas {
    private TelaSalas tela;

    public ControlSalas(TelaSalas tela) {
        this.tela = tela;
    }

    public void selecionarSala(int index) {

        tela.setSession(tela.getSalasCine()[index].getSessoes());
        tela.getBilheteSala().setSala(tela.getSalasCine()[index]);

        TelaFilmes mostrarFilme = new TelaFilmes(tela);
        mostrarFilme.setVisible(true);
        tela.setVisible(false);
    }

    public void deslogar() {
        tela.dispose();
    }
}