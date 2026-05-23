package model;
import repository.GerenciaFilme;

public class Funcionario extends Base implements GerenciaFilme {

    double salario;

    public Funcionario(String nome, int idade, String email, double salario) {
        super(nome, idade, email);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    @Override
    public void adicionarUsuario(Base usuario) {

    }
    @Override
    public void alterarUsuario(Base usuario) {

    }
    @Override
    public void incluirFilme(Filme filme) {

    }
    @Override
    public void alterarFilme(Filme filme) {

    }
    @Override
    public void excluirFilme(Filme filme) {
        
    }

}