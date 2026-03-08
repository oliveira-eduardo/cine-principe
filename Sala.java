public class Sala {
    private Sessao[] sessoes = new Sessao[7];

    public Sala(Sessao[] sessoes){
        this.sessoes = sessoes;
    }

    public Sessao[] getSessoes() {
        return sessoes;
    }

    public void setSessoes(Sessao[] sessoes) {
        this.sessoes = sessoes;
    }

    @Override
    public String toString(){
        return "|Sessão 1:" +this.sessoes[0] + "| Sessão 2:" + this.sessoes[1] + "| Sessão 3:" + this.sessoes[2] + "| Sessão 4:" + this.sessoes[3] +
        "| Sessões 5:" + this.sessoes[4] + "| Sessões 6:" + this.sessoes[5] + " |Sessões 7:" + this.sessoes[6];
    }
}
