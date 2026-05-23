package model;
import service.Sessao;

public class Bilhete {
    private Usuario usuario;
    private Sala sala;
    private int indiceDaSessao;
    private String cadeira;

    public Bilhete(Usuario usuario, Sala sala, int indiceDaSessao, String cadeira){
        this.usuario = usuario;
        this.sala = sala;
        this.indiceDaSessao = indiceDaSessao;
        this.cadeira = cadeira;
    }

    public int getIndiceDaSessao() {
        return indiceDaSessao;
    }

    public void setIndiceDaSessao(int indiceDaSessao) {
        this.indiceDaSessao = indiceDaSessao;
    }

    public String getCadeira() {
        return cadeira;
    }

    public void setCadeira(String cadeira) {
        this.cadeira = cadeira;
    }

    /*usuário ----------------------------------------------------------------------------- */

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*itens dentro de usuário ----------------------------------------------------------------------- */

    public String getUser() {
        return usuario.getUser();
    }

    public String getCpf() {
        return usuario.getCpf();
    }

    /*sala ----------------------------------------------------------------------------- */

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /*itenas dentro de sala ----------------------------------------------------------------------------- */

    public String getNomeDaSala() {
        return sala.getNomeDaSala();
    }

    public Sessao getSessao() {
        return sala.getSessoes()[indiceDaSessao];
    }

    public String getHorario() {
        return sala.getSessoes()[indiceDaSessao].getHorario();
    }

    public float getValor() {
        return sala.getSessoes()[indiceDaSessao].getFilme().getValor();
    }

    public String getNomeDoFilme() {
        return sala.getSessoes()[indiceDaSessao].getFilme().getNome();
    }

    /*metodos ----------------------------------------------------------------------------- */
    
    //incrementar posteriormente
    public float calcularPreco(char tipoDoBilhete) {
        if(tipoDoBilhete == 'i') {
            return getValor();
        } else {
            return getValor()/2;
        }
    }

    //incrementar posteriormente
    public String gerarBilhete() {
        return "Nome: " + getUser() + " Cpf: " + getCpf() + " Sala: " + getNomeDaSala()
         + " Sessao: " + (indiceDaSessao+1) + " Filme: " + getNomeDoFilme() + " Valor: " + getValor()
         + " Cadeira: " + getCadeira();
    }

}