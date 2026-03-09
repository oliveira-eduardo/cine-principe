public class Sala {
    private String nomeDaSala;
    private Sessao[] sessoes = new Sessao[7];

    public Sala(String nomeDaSala, Sessao[] sessoes){
        this.nomeDaSala = nomeDaSala;
        this.sessoes = sessoes;
    }

    public Sessao[] getSessoes() {
        return sessoes;
    }

    public void setSessoes(Sessao[] sessoes) {
        this.sessoes = sessoes;
    }

    public String getNomeDaSala() {
        return nomeDaSala;
    }

    public void setNomeDaSala(String nomeDaSala) {
        this.nomeDaSala = nomeDaSala;
    }

    //exibirSessoes(recebe a sala que a pessoa tem interesse)

    @Override
    public String toString(){
        String resultado = "";
        int contador = 1;

        for (int i = 0; i < this.sessoes.length; i++) {
            
            if (this.sessoes[i] != null) {
                resultado += "| Sessão " + contador + ": " + this.sessoes[i] + " ";
                contador++; 
            }
        }
        return resultado + "| \n";
        }
}
