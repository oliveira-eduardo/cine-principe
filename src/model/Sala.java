package model;
import java.util.Random;

import data.FilmeData;
import service.Sessao;
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

    public Sala(String nomeDaSala){
        this.nomeDaSala = nomeDaSala;
        Random gerador = new Random();
        String[] horarios = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};
        int contador = 0;
        int tamBanco = FilmeData.contarFilmes();
        
        if(tamBanco == 0){
            for(int i = 0; i <7; i++){
                Filme fLocal = enviarFilme(i);
                FilmeData.inserir(fLocal);
            }
        }

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

        filmes [0] = new Filme("Agente Secreto", "2h40m", "Thriller/Crime", 25.0f, "src/imagens/O-AGENTE-SECRETO-Cartaz-WEB-717x1024-1-aspect-ratio-255-376-950523602.png");
        filmes [1] = new Filme("Hamnet", "2h 5m", "Tragédia/Drama", 30.0f,"src/imagens/Hamnet-movie-poster-1317530855.png");
        filmes [2] = new Filme("Valor Sentimental", "2h 13m", "Drama/Tragico", 20.0f,"src/imagens/ValorSentimental.jpg");
        filmes [3] = new Filme("Uma Batalha Após a Outra", "2h 42m", "Drama/Ação e suspense", 22.0f,"src/imagens/uma-batalha-apos-a-outra.jpg");
        filmes [4] = new Filme("Sonhos de Trem", "1h 43m", " Drama", 18.0f,"src/imagens/Sonhos_de_Trem.jpg");
        filmes [5] = new Filme("Pecadores", "2h 17m", "Terror/Ação", 20.0f,"src/imagens/Pecadores.jpg");
        filmes [6] = new Filme("A única saida", "2h 19m", "Comédia/Thriller", 25.0f,"src/imagens/NoOtherChoice.jpg");

        return filmes[num];
    }

    public String mostrarSala(){
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
