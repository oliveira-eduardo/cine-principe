package model;

import data.*;
import repository.GerenciaFilme;

public class Funcionario extends Base implements GerenciaFilme {

    private double salario;

    public Funcionario(String nome, int idade, String email, double salario, String senha) {
        super(nome, idade, email, senha);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public void adicionarUsuario(Usuario usuario) {
        UsuariosData.inserir(usuario);
    }

    public void alterarUsuario(Usuario usuario) {
        UsuariosData.alterar(usuario);
    }
    
    public void incluirFilme(Filme filme) {
        FilmeData.inserir(filme);
    }
    
    public void alterarFilme(Filme filme) {
        FilmeData.alterar(filme);
    }
    
    public void excluirFilme(Filme filme) {
        FilmeData.apagar(filme.getId());
    }

}