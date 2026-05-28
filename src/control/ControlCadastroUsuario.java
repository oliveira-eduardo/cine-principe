package control;

import data.UsuariosData;
import model.Usuario;

public class ControlCadastroUsuario {

    public ControlCadastroUsuario() {
    }

    public Usuario registarUsuario(String user, String cpf, String senha, int idade, 
                                   String sexo, String email, String nomeCartao, 
                                   String numCartao, String cvv) throws IllegalArgumentException {
        
        if (user.isEmpty() || cpf.isEmpty() || senha.isEmpty() || sexo.isEmpty() || 
            email.isEmpty() || nomeCartao.isEmpty() || numCartao.isEmpty() || cvv.isEmpty()) {
            throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
        }

        Usuario novoUsuario = new Usuario(user, cpf, senha, idade, sexo, email, nomeCartao, numCartao, cvv);
        
        UsuariosData.inserir(novoUsuario);

        return novoUsuario;
    }
}