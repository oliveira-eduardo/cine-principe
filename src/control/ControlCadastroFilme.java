package control;

import gui.TelaCadastroFilme;
import model.Filme;
import repository.GerenciaFilme;

public class ControlCadastroFilme {

    private TelaCadastroFilme tela;

    public ControlCadastroFilme(TelaCadastroFilme tela) {
        this.tela = tela;
    }

    public void cadastrarFilme() {
        try {
            String nome = tela.getTxtNome().getText().trim();
            String duracao = tela.getTxtDuracao().getText().trim();
            String sinopse = tela.getTxtSinopse().getText().trim();
            String nomeImagem = "src/imagens/placeholder.jpeg";
            
            String valorStr = tela.getTxtValor().getText().trim().replace(",", "."); 

            if (nome.isEmpty() || duracao.isEmpty() || valorStr.isEmpty() || sinopse.isEmpty()) {
                tela.exibirMensagemAviso("Todos os campos devem ser preenchidos.");
                return;
            }

            float valor = Float.parseFloat(valorStr);

            Filme novoFilme = new Filme(0, nome, duracao, sinopse, valor, nomeImagem);

            if (tela.getUsuarioLogado() instanceof GerenciaFilme) {
                GerenciaFilme gerente = (GerenciaFilme) tela.getUsuarioLogado();
                gerente.incluirFilme(novoFilme); 

                tela.exibirMensagemSucesso("Filme '" + novoFilme.getNome() + "' cadastrado com sucesso!");
                tela.dispose();
            } else {
                tela.exibirMensagemErro("Você não possui permissão para cadastrar filmes.");
            }

        } catch (NumberFormatException ex) {
            tela.exibirMensagemErro("Digite um valor numérico válido para o ingresso (Ex: 25.50).");
        } catch (Exception ex) {
            tela.exibirMensagemErro("Erro ao processar os dados: " + ex.getMessage());
        }
    }
}