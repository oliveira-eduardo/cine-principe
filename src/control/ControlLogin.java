package control;

import data.AdministradoresData;
import data.CinemaDados;
import data.CriticosData;
import data.FilmeData;
import data.FuncionariosData;
import data.UsuariosData;
import gui.TelaLogin;
import gui.TelaSalas;
import gui.TelaSistema;
import model.Administrador;
import model.Bilhete;
import model.Funcionario;
import model.Sala;
import model.Usuario;

public class ControlLogin {
    private TelaLogin tela;

    public ControlLogin(TelaLogin tela) {
        this.tela = tela;
    }

    public void autenticar(String login, String senha) {
        if (login.isEmpty() || senha.isEmpty()) {
            tela.exibirMensagemAviso("Preencha todos os campos.", "Aviso");
            return;
        }

        try {
            if (AdministradoresData.pegar(login) != null) {
                Administrador adm = AdministradoresData.pegar(login);

                if (!senha.equals(adm.getSenha())) {
                    throw new Exception("Senha incorreta");
                }

                tela.exibirMensagemInformativa("Bem-vindo, " + adm.getNome() + "!", "Sucesso");
                TelaSistema telaSistema = new TelaSistema(adm);
                telaSistema.setVisible(true);
                tela.dispose();
                
            } else if (FuncionariosData.pegar(login) != null) {
                Funcionario func = FuncionariosData.pegar(login);

                if (!senha.equals(func.getSenha())) {
                    throw new Exception("Senha incorreta");
                }

                tela.exibirMensagemInformativa("Bem-vindo, " + func.getNome() + "!", "Sucesso");
                TelaSistema telaSistema = new TelaSistema(func);
                telaSistema.setVisible(true);
                tela.dispose();

            } else {
                Usuario usuario = UsuariosData.pegar(login);
                if (usuario == null) {
                    usuario = CriticosData.pegar(login);
                    if(usuario == null) 
                        throw new Exception("Login nao reconhecido");
                }
                if (!senha.equals(usuario.getSenha())) {
                    throw new Exception("Senha incorreta");
                }

                tela.exibirMensagemInformativa("Bem-vindo, " + usuario.getUser() + "!", "Sucesso");
                FilmeData.connect();
                
                Sala[] minhasSalas = CinemaDados.getSalas();
                
                Bilhete bilhete = new Bilhete();
                bilhete.setUsuario(usuario);
                
                TelaSalas telaSalas = new TelaSalas(minhasSalas, bilhete);
                telaSalas.setVisible(true);
                tela.dispose();
            }

        } catch (Exception ex) {
            tela.exibirMensagemErro("Usuário ou senha inválidos.", "Erro de Autenticação");
        }
    }
}