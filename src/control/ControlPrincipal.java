package control;

import gui.TelaCadastroUsuario;
import gui.TelaLogin;
import gui.Navegacao;
import gui.TelaPrincipal;

public class ControlPrincipal {
    private TelaPrincipal tela;

    public ControlPrincipal(TelaPrincipal tela) {
        this.tela = tela;
    }

    public void abrirLogin() {

        TelaLogin telaLogin = new TelaLogin();
        Navegacao.exibir(telaLogin);
    }

    public void abrirCadastro() {
        TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
        Navegacao.exibir(telaCadastro);
    }
}
