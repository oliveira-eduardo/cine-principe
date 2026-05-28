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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControlSistema {

    private TelaSistema tela;

    public ControlSistema(TelaSistema tela) {
        this.tela = tela;
    }

    public void abrirCadastroUsuario() {
        tela.setBotoesHabilitados(false); 

        TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
        
        telaCadastro.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                tela.setBotoesHabilitados(true); 
            }
        });

        telaCadastro.setVisible(true);
    }

    public void alterarUsuario(String identificador) {
        if (identificador != null && !identificador.trim().isEmpty()) {
            Usuario userEncontrado = UsuariosData.pegar(identificador);

            if (userEncontrado != null) {
                TelaAlterarUsuario telaAlterar = new TelaAlterarUsuario(tela.getUsuarioLogado(), userEncontrado);
                telaAlterar.setVisible(true);
            } else {
                tela.exibirMensagemErro("Usuário não encontrado!");
            }
        }
    }

    public void excluirUsuario(String identificador) {
        if (identificador != null && !identificador.trim().isEmpty()) {
            if (tela.pedirConfirmacao("Deseja excluir '" + identificador + "'?")) {
                if (tela.getUsuarioLogado() instanceof Administrador) {
                    Administrador admin = (Administrador) tela.getUsuarioLogado();
                    admin.excluirUsuario(identificador);
                    tela.exibirMensagemSucesso("Usuário excluído com sucesso!");
                } else {
                    tela.exibirMensagemErro("Você não tem permissão para esta ação.");
                }
            }
        }
    }

    public void abrirCadastroFilme() {
        tela.setBotoesHabilitados(false); 

        TelaCadastroFilme telaCadastro = new TelaCadastroFilme(tela.getUsuarioLogado());
        
        telaCadastro.addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                tela.setBotoesHabilitados(true); 
            }
        });

        telaCadastro.setVisible(true);
    }

    public void alterarFilme(String nomeFilme) {
        if (nomeFilme != null && !nomeFilme.trim().isEmpty()) {
            Filme filmeEncontrado = FilmeData.pegar(nomeFilme);

            if (filmeEncontrado != null) {
                TelaAlterarFilme telaAlterar = new TelaAlterarFilme(tela.getUsuarioLogado(), filmeEncontrado);
                telaAlterar.setVisible(true);
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
                    if (tela.getUsuarioLogado() instanceof GerenciaFilme) {
                        GerenciaFilme gerente = (GerenciaFilme) tela.getUsuarioLogado();
                        gerente.excluirFilme(filmeEncontrado);
                        tela.exibirMensagemSucesso("Filme excluído com sucesso!");
                    } else {
                        tela.exibirMensagemErro("Você não tem permissão para excluir filmes.");
                    }
                }
            } else {
                tela.exibirMensagemErro("Filme não encontrado!");
            }
        }
    }

    public void deslogar() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        tela.dispose();
    }
}