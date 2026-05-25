package model;
import repository.GerenciaFilme;

public class Administrador extends Base implements GerenciaFilme {
    private double salario;
    private int ID;

    public Administrador(String nome, int idade, String email, double salario, int ID){
        super(nome, idade, email);
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

    @Override
    public void adicionarUsuario(Usuario usuario){
        System.out.println("Usuário adicionado ao sistema.");
    }

    @Override
    public void alterarUsuario(Usuario usuario){
        System.out.println("Usuário alterado no sistema.");
    }

    public void excluirUsuario(){
        System.out.println("Usuário excluído do sistema.");
    }

    @Override
    public void incluirFilme(Filme filme){
        System.out.println("Filme incluído no catálogo.");
    }
    @Override
    public void excluirFilme(Filme filme){
        System.out.println("Filme excluído do catálogo.");
    }

    @Override
    public void alterarFilme(Filme filme){
        System.out.println("Filme alterado.");
    }
}
