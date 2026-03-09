public class Bilhete {
    private Usuario usuario;
    private Sala sala;
    private int indiceDaSessao;

    public Bilhete(Usuario usuario, Sala sala, int indiceDaSessao){
        this.usuario = usuario;
        this.sala = sala;
        this.indiceDaSessao = indiceDaSessao;
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
        return "Nome: " + getUser() + " Cpf: " + getCpf() + " Sessao: " + (indiceDaSessao+1) + " Filme: " + getNomeDoFilme() + " Valor: " + getValor();
    }

}