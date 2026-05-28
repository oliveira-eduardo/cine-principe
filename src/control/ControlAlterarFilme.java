package control;

import gui.TelaAlterarFilme;
import model.Base;
import model.Filme;
import repository.GerenciaFilme;

public class ControlAlterarFilme {
    private TelaAlterarFilme tela;

    public ControlAlterarFilme(TelaAlterarFilme tela) {
        this.tela = tela;
    }

    public boolean salvarAlteracoes(String nome, String duracao, String sinopse, String valorStr, String nomeImagem, Filme filme, Base usuario) {
        try {
            String valorFormatado = valorStr.replace(",", "."); 

            if (nome.isEmpty() || duracao.isEmpty() || valorFormatado.isEmpty()) {
                tela.exibirMensagemAviso("Nome, Duração e Valor são obrigatórios.", "Aviso");
                return false;
            }

            float valor;
            try {
                valor = Float.parseFloat(valorFormatado);
            } catch (NumberFormatException ex) {
                tela.exibirMensagemErro("Digite um valor numérico válido para o ingresso (Ex: 25.50).", "Erro de Formatação");
                return false;
            }

            filme.setNome(nome);
            filme.setDuracao(duracao);
            filme.setSinopse(sinopse);
            filme.setValor(valor);
            filme.setNomeImagem(nomeImagem);

            GerenciaFilme gerente = (GerenciaFilme) usuario;
            gerente.incluirFilme(filme); 

            tela.exibirMensagemInformativa("Filme alterado com sucesso!", "Sucesso");
            return true;

        } catch (Exception ex) {
            tela.exibirMensagemErro("Erro ao processar as alterações: " + ex.getMessage(), "Erro");
            return false;
        }
    }
}