package model;
import repository.Salas;

public class Estudante extends Usuario{
    public Estudante(String user, String cpf, String senha, int idade, String sexo, String email, String nome_do_cartao, String numero_do_cartao, String codigo_verificador_do_cartao) {
        super(user, cpf, senha, idade, sexo, email, nome_do_cartao, numero_do_cartao, codigo_verificador_do_cartao);
    }

    //implementar depois da reorganização do código
    @Override
    public double comprarBilhete(int numBilhetes, Filme filme, Salas salas){
        return numBilhetes * ( (filme.getValor()/2) * salas.getMultiplicadorValor());
    }
    @Override
    public double comprarBilhete(int numBilhetes, Filme filme, Salas salas, CupomPromocional cupom){
        return numBilhetes * ((filme.getValor()/2) * salas.getMultiplicadorValor()) * cupom.getDesconto();
    }
}
