public class Filme {
    private String nome;
    private String duracao;
    private String sinopse;
    private String valor;
    
    public Filme(String nome, String duracao, String sinopse, String valor) {
        this.nome = nome;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.valor = valor;
    }
    public String getNome() {
        return nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString(){
        return  "Filme: " + this.nome + " Duração: " + this.duracao + " Genero: " + this.sinopse + " Valor: " + this.valor + "\n";
    }
}