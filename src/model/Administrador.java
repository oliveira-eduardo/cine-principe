package model;

import data.*;
import repository.GerenciaFilme;

public class Administrador extends Base implements GerenciaFilme {
    private double salario;
    private int ID;

    public Administrador(String nome, int idade, String email, String senha, double salario, int ID){
        super(nome, idade, email, senha);
        this.salario = salario;
        this.ID = ID;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public void adicionarUsuario(Usuario usuario){
        UsuariosData.inserir(usuario);
    }

    public void alterarUsuario(Usuario usuario){
        UsuariosData.alterar(usuario);
    }

    public void excluirUsuario(String user){
        UsuariosData.apagar(user);
    }

    public void incluirFilme(Filme filme){
        FilmeData.inserir(filme);
    }
    
    public void excluirFilme(Filme filme){
        FilmeData.apagar(filme.getId());
    }

    public void alterarFilme(Filme filme){
        FilmeData.alterar(filme);
    }
}
