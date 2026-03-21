public class Filme {
    private String nome;
    private String duracao;
    private String sinopse;
    private Float valor;
    private Double nota;
    private int quantidade_criticos;
    
    public Filme(String nome, String duracao, String sinopse, float valor) {
        this.nome = nome;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.valor = valor;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public int getQuantidade_criticos() {
        return quantidade_criticos;
    }

    public void setQuantidade_criticos(int quantidade_criticos) {
        this.quantidade_criticos = quantidade_criticos;
    }

    public String mostrarFilme(){
        return  "Filme: " + this.nome + " Duração: " + this.duracao + " Genero: " + this.sinopse + " Valor: " + this.valor + "\n";
    }
}