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

    public void atribuirNota(double nota, Filme filme) {
        if (nota >= 0 && nota <= 10) {
            
            int novaQtd = filme.getQuantidade_criticos() + 1;
            filme.setQuantidade_criticos(novaQtd);
            
            double novaSoma = filme.getSomaDasNotas() + nota;
            filme.setSomaDasNotas(novaSoma);
            
            double novaMedia = novaSoma / novaQtd;
            filme.setMedia(novaMedia);
        }
    }

    public void atribuirCritica(String textoCritica, Filme filme) {
        
        Critica novaCritica = new Critica();
        novaCritica.setComentario(textoCritica);

        int posicao = filme.getContadorCriticas();
        if (posicao < 100) {

            Critica[] vetorDoFilme = filme.getCriticas();
            vetorDoFilme[posicao] = novaCritica;
            
            filme.setContadorCriticas(posicao + 1);
        }
    }

    //fazer um metodo exclusivo comprar bilhete;
}