public class Estudante extends Usuario{
    public Estudante(String user, String cpf, String senha, int idade, String sexo, String email, String nome_do_cartao, String numero_do_cartao, String codigo_verificador_do_cartao) {
        super(user, cpf, senha, idade, sexo, email, nome_do_cartao, numero_do_cartao, codigo_verificador_do_cartao);
    }

    //implementar depois da reorganização do código
    public Compra realizarCompra(Sessao sessao) {
         return new Compra(sessao);
    }
}
