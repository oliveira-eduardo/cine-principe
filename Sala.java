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
        String resultado = "";
        int contador = 1;

        for (int i = 0; i < 7; i++) {
            if (this.sessoes[i].getFilme() != null) {
                resultado += "| Sessão " + contador + ": " + this.sessoes[i] + " ";
                contador++; 
            }
        }
        return resultado + "|";
        }
}
