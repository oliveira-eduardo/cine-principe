package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.TelaCadastroUsuario;
import gui.TelaLogin;
import gui.TelaPrincipal;

public class ControlPrincipal {
    private TelaPrincipal tela;

    public ControlPrincipal(TelaPrincipal tela) {
        this.tela = tela;
    }

    public void abrirLogin() {

        tela.configurarBotoesAtivos(false);

        TelaLogin telaLogin = new TelaLogin();
        
        telaLogin.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                tela.configurarBotoesAtivos(true);
            }
        });
        
        telaLogin.setVisible(true);
    }

    public void abrirCadastro() {
        tela.configurarBotoesAtivos(false);

        TelaCadastroUsuario telaCadastro = new TelaCadastroUsuario();
        
        telaCadastro.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                tela.configurarBotoesAtivos(true);
            }
        });

        telaCadastro.setVisible(true);
    }
}