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
    
    public void adicionarUsuario(Base usuario) {

    }
    
    public void alterarUsuario(Base usuario) {

    }

    public void incluirFilme(Filme filme) {

    }

    public void alterarFilme(Filme filme) {

    }

    public void excluirFilme(Filme filme) {
        
    }

}