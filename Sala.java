import java.util.Random;
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
    public Sala(){
        this.nomeDaSala = "teste";
        Random gerador = new Random();
        String[] horarios = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};
        int contador = 0;

        for(int i = 0; i < 7; i++){
            int quantidadeSessoes = gerador.nextInt(8);       
            if(quantidadeSessoes < 7){
                sessoes [contador] = new Sessao(enviarFilme(quantidadeSessoes), horarios[i]);
                contador ++;
            }
        }
    }


    private Filme enviarFilme(int num){ //não colocar public java reclama 
        Filme [] filmes = new Filme [7];

        filmes [0] = new Filme("Agente Secreto", "2h40m", "Thriller/Crime", 25.0f);
        filmes [1] = new Filme("Hamnet", "2h 5m", "Tragédia/Drama", 30.0f);
        filmes [2] = new Filme("Valor Sentimental", "2h 13m", "Drama/Tragicomedy", 20.0f);
        filmes [3] = new Filme("Uma Batalha Após a Outra", "2h 42m", "Drama/Ação e suspense", 22.0f);
        filmes [4] = new Filme("Sonhos de Trem", "1h 43m", " Drama", 18.0f);
        filmes [5] = new Filme("Pecadores", "2h 17", "Terror/Ação", 20.0f);
        filmes [6] = new Filme("A única saida", "2h 19m", "Comédia/Thriller", 25.0f);

        return filmes[num];
    }

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
