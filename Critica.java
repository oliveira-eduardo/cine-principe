public class Critica {
    private String  nome_critica;
    private String origem;
    private String comentario;

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

    public Critica(String nome_critica, String origem, String comentario){
        this.nome_critica=nome_critica;
        this.origem=origem;
        this.comentario=comentario;


    }
}
