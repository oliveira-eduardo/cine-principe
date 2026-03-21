public class Critico extends Usuario{

    private String origem;

    public Critico(String user, String cpf, String senha, int idade, String sexo, String email, String nome_do_cartao, String numero_do_cartao, String codigo_verificador_do_cartao, String origem){
        super(user, cpf, senha, idade, sexo, email, nome_do_cartao, numero_do_cartao, codigo_verificador_do_cartao);
        this.origem = origem;
    }

    public void setOrigem(String origem){
        this.origem = origem;
    }

    public String getOrigem(){
        return this.origem;
    }


    //fazer um metodo exclusivo comprar bilhete;
}