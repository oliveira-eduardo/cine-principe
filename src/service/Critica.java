package service;
public class Critica {
    private String  nome_critica;
    private String origem;
    private String comentario;
    private double nota;

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getNome_critica() {
        return nome_critica;
    }

    public void setNome_critica(String nome_critica) {
        this.nome_critica = nome_critica;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Critica(String nome_critica, String origem, String comentario, double nota){
        this.nome_critica=nome_critica;
        this.origem=origem;
        this.comentario=comentario;
        this.nota = nota;
    }

    public Critica() {
    }
}
