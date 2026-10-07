package control;

import gui.TelaSistema;
import gui.TelaCadastroUsuario;
import gui.TelaAlterarUsuario;
import gui.TelaCadastroFilme;
import gui.TelaAlterarFilme;
import gui.TelaLogin;

import model.Administrador;
import model.Usuario;
import model.Filme;
import repository.GerenciaFilme;
import data.UsuariosData;
import data.FilmeData;

import gui.Navegacao;

public class ControlSistema {

    private TelaSistema tela;

    public ControlSistema(TelaSistema tela) {
        this.tela = tela;
    }

    public void abrirCadastroUsuario() {
        TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
        Navegacao.exibir(telaCadastro);
    }

    public void alterarUsuario(String identificador) {
        if (identificador != null && !identificador.trim().isEmpty()) {
            Usuario userEncontrado = UsuariosData.pegar(identificador);

            if (userEncontrado != null) {
                TelaAlterarUsuario telaAlterar = new TelaAlterarUsuario(tela.getUsuarioLogado(), userEncontrado);
                Navegacao.exibir(telaAlterar);
            } else {
                tela.exibirMensagemErro("Usuário não encontrado!");
            }
        }
    }

    public void excluirUsuario(String identificador) {
        if (identificador != null && !identificador.trim().isEmpty()) {
            if (UsuariosData.pegar(identificador) != null) {
                if (tela.pedirConfirmacao("Deseja excluir '" + identificador + "'?")) {
                    Administrador admin = (Administrador) tela.getUsuarioLogado();
                    admin.excluirUsuario(identificador);
                    tela.exibirMensagemSucesso("Usuário excluído com sucesso!");
                }
            } else {
                tela.exibirMensagemErro("Usuário não ecnontrado.");
            }
        }
    }

    public void abrirCadastroFilme() {
        TelaCadastroFilme telaCadastro = new TelaCadastroFilme(tela.getUsuarioLogado());
        Navegacao.exibir(telaCadastro);
    }

    public void alterarFilme(String nomeFilme) {
        if (nomeFilme != null && !nomeFilme.trim().isEmpty()) {
            Filme filmeEncontrado = FilmeData.pegar(nomeFilme);

            if (filmeEncontrado != null) {
                TelaAlterarFilme telaAlterar = new TelaAlterarFilme(tela.getUsuarioLogado(), filmeEncontrado);
                Navegacao.exibir(telaAlterar);
            } else {
                tela.exibirMensagemErro("Filme não encontrado!");
            }
        }
    }

    public void excluirFilme(String nomeFilme) {
        if (nomeFilme != null && !nomeFilme.trim().isEmpty()) {
            Filme filmeEncontrado = FilmeData.pegar(nomeFilme);
            
            if (filmeEncontrado != null) {
                if (tela.pedirConfirmacao("Deseja excluir o filme '" + nomeFilme + "'?")) {
                    GerenciaFilme gerente = (GerenciaFilme) tela.getUsuarioLogado();
                    gerente.excluirFilme(filmeEncontrado);
                    tela.exibirMensagemSucesso("Filme excluído com sucesso!");
                }
            } else {
                tela.exibirMensagemErro("Filme não encontrado!");
            }
        }
    }

    public void deslogar() {
        TelaLogin telaLogin = new TelaLogin();
        Navegacao.exibir(telaLogin);
    }
}
