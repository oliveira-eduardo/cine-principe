package model;
import java.sql.DriverManager;
import java.sql.SQLException;
import repository.GerenciaFilme;

public class Funcionario extends Base implements GerenciaFilme {

    double salario;
    String url = "jdbc:sqlite:Usuarios.db";

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
    public void adicionarUsuario(Usuario usuario) {
        
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        
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