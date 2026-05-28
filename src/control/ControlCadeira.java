package control;

import exceptions.VendasException;
import gui.TelaCadeira;
import gui.TelaProdutos;
import model.Bilhete;

public class ControlCadeira {
    private TelaCadeira tela;

    public ControlCadeira(TelaCadeira tela) {
        this.tela = tela;
    }

    public boolean selecionarAssento(int linha, int coluna, String nomeAssento) {
        try {
            
            if (tela.getMatrizCadeiras()[linha][coluna] == 1) {
                throw new VendasException("A poltrona " + nomeAssento + " já foi selecionada");
            }

            
            if (tela.getSessaoAtual().escolhaCadeira(linha, coluna)) {
                
                int[] coordenadas = {linha, coluna};
                tela.getCoordenadasEscolhidas().add(coordenadas);

                Bilhete novoBilhete = new Bilhete();
                novoBilhete.setUsuario(tela.getTelafilmes().getSalasCine().getBilheteSala().getUsuario());
                novoBilhete.setSala(tela.getTelafilmes().getSalasCine().getBilheteSala().getSala());
                novoBilhete.setIndiceDaSessao(tela.getTelafilmes().getSalasCine().getBilheteSala().getIndiceDaSessao());
                novoBilhete.setCadeira(nomeAssento);
                
                tela.getBilhetes().add(novoBilhete);
                
                return true; 
            }
        } catch (VendasException ex) {
            tela.exibirMensagemAviso(ex.getMessage(), "Assento Indisponível");
        }
        return false; 
    }

    public void voltar() {
        for (int i = 0; i < tela.getCoordenadasEscolhidas().size(); i++) {
            int[] coord = tela.getCoordenadasEscolhidas().get(i);
            int linha = coord[0];
            int coluna = coord[1];
            tela.getSessaoAtual().limparCadeira(linha, coluna);
        }
        tela.getBilhetes().clear();
        tela.getCoordenadasEscolhidas().clear();
        
        tela.getTelafilmes().setVisible(true);
        tela.dispose();
    }

    public void avancarParaSnacks() {
        try {
            if (tela.getBilhetes().isEmpty()) {
                throw new VendasException("Não é possível avançar, escolha pelo menos 1 assento");
            }
            TelaProdutos lanches = new TelaProdutos(tela);
            lanches.setVisible(true);
            tela.setVisible(false);
        } catch (VendasException ex) {
            tela.exibirMensagemAviso(ex.getMessage(), "Nenhum Assento Selecionado");
        }
    }
}