package control;

import java.time.LocalTime;

import exceptions.VendasException;
import gui.TelaCadeira;
import gui.TelaFilmes;
import service.Sessao;

public class ControlFilme {
    private TelaFilmes tela;

    public ControlFilme(TelaFilmes tela) {
        this.tela = tela;
    }

    public void selecionarFilme(int index) {
        try {
            Sessao[] sessoes = tela.getSalasCine().getSession();
            Sessao sessaoSelecionada = sessoes[index];
            
            
            LocalTime horarioAtual = LocalTime.now();
            LocalTime horarioSessao = LocalTime.parse(sessaoSelecionada.getHorario());

            if (horarioAtual.isAfter(horarioSessao)) {
                throw new VendasException("Não é possível comprar o bilhete, o filme já não está mais sendo exibido.");
            }

            
            tela.getSalasCine().getBilheteSala().setIndiceDaSessao(index);
            tela.setSessao(sessaoSelecionada);
            
            
            TelaCadeira cadeiras = new TelaCadeira(tela);
            cadeiras.setVisible(true);
            tela.setVisible(false);

        } catch (VendasException ex) {
            tela.exibirMensagemErro(ex.getMessage());
        }
    }
    public void voltarParaSalas() {
        tela.getSalasCine().setVisible(true);
        tela.dispose();
    }
    
    public void deslogar() {
        gui.TelaPrincipal telaPrincipal = new gui.TelaPrincipal();
        telaPrincipal.setVisible(true);
        tela.dispose();
    }
}