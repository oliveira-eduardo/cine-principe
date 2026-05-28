package control;

import gui.TelaAlterarUsuario;
import model.Base;
import model.Usuario;

public class ControlAlterarUsuario {
    private TelaAlterarUsuario tela;

    public ControlAlterarUsuario(TelaAlterarUsuario tela) {
        this.tela = tela;
    }

    public boolean salvarAlteracoes(String user, String senha, int idade, String sexo, String email, 
                                    String nomeCartao, String numCartao, String cvv, Usuario usuario, Base funcionario) {
        try {
            if (user.isEmpty() || senha.isEmpty()) {
                tela.exibirMensagemAviso("Usuário e Senha são obrigatórios.", "Aviso");
                return false;
            }

            usuario.setUser(user);
            usuario.setSenha(senha);
            usuario.setIdade(idade);
            usuario.setSexo(sexo);
            usuario.setEmail(email);
            usuario.setNome_do_cartao(nomeCartao);
            usuario.setNumero_do_cartao(numCartao);
            usuario.setCodigo_verificador_do_cartao(cvv);

            
            funcionario.alterarUsuario(usuario);

            tela.exibirMensagemInformativa("Dados alterados com sucesso!", "Sucesso");
            return true;

        } catch (Exception ex) {
            tela.exibirMensagemErro("Erro ao processar as alterações.", "Erro");
            return false;
        }
    }
}