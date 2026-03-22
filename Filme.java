public class Filme {
    private String nome;
    private String duracao;
    private String sinopse;
    private Float valor;
    private Double nota;
    private int quantidade_criticos;
    private double somaDasNotas = 0.0;
    private double media = 0.0;


    public Filme(String nome, String duracao, String sinopse, float valor, double nota, int quantidade_criticos, double somaDasNotas, double media) {
        this.nome = nome;
        this.duracao = duracao;
        this.sinopse = sinopse;
        this.valor = valor;
        this.nota = nota;
        this.quantidade_criticos = quantidade_criticos;
        this.somaDasNotas = somaDasNotas;
        this.media = media;
    }
    
    private Critica[] criticas = new Critica[100];
    private int contadorCriticas = 0;

    

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

    public double getSomaDasNotas() {
        return somaDasNotas;
    }

    public void setSomaDasNotas(double somaDasNotas) {
        this.somaDasNotas = somaDasNotas;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public Critica[] getCriticas() {
        return criticas;
    }

    public void setCriticas(Critica[] criticas) {
        this.criticas = criticas;
    }

    public int getContadorCriticas() {
        return contadorCriticas;
    }

    public void setContadorCriticas(int contadorCriticas) {
        this.contadorCriticas = contadorCriticas;
    }


    public String mostrarFilme(){
        return  "Filme: " + this.nome + " Duração: " + this.duracao + " Genero: " + this.sinopse + " Valor: " + this.valor + "\n";
    }
}