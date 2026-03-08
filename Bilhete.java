public class Bilhete {
    private Usuario usuario;
    private Sala sala;
    private int indiceDaSessao;

    public Bilhete(Usuario usuario, Sala sala, int indiceDaSessao){
        this.usuario = usuario;
        this.sala = sala;
        this.indiceDaSessao = indiceDaSessao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /*teste ----------------------------------------------------------------------- */

    public String getUser() {
        return usuario.getUser();
    }

    public String getCpf() {
        return usuario.getCpf();
    }

    /*----------------------------------------------------------------------------- */

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    /*----------------------------------------------------------------------------- */

    public String getHorario() {
        return sessao.getHorario();
    }

    public String getNomeDoFilme() {
        return sessao.getFilme().getNome();
    }

    /*----------------------------------------------------------------------------- */
    
}