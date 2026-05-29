package model;
import java.util.ArrayList;
import repository.Salas;

public class Estudante extends Usuario{
    public Estudante(String user, String cpf, String senha, int idade, String sexo, String email, String nome_do_cartao, String numero_do_cartao, String codigo_verificador_do_cartao) {
        super(user, cpf, senha, idade, sexo, email, nome_do_cartao, numero_do_cartao, codigo_verificador_do_cartao);
    }

    public double comprarBilhetes(ArrayList<Bilhete> bilhetes){
        double valorDoFilme = bilhetes.get(0).getValor();

        Salas tipoDeSala = Salas.obterPorTipo(bilhetes.get(0).getNomeDaSala());
        double multiplicadorDaSala = tipoDeSala.getMultiplicadorValor();
        
        return (bilhetes.size() * (valorDoFilme * multiplicadorDaSala))/2;
    }
}