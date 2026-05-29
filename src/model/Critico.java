package model;
import java.util.ArrayList;

import data.CriticasData;
import repository.Salas;
import service.Critica;

public class Critico extends Usuario{

    private String origem;

    public Critico(String user, String cpf, String senha, int idade, String sexo, String email, String nome_do_cartao, String numero_do_cartao, String codigo_verificador_do_cartao, String origem){
        super(user, cpf, senha, idade, sexo, email, nome_do_cartao, numero_do_cartao, codigo_verificador_do_cartao);
        this.origem = origem;
    }

    public void setOrigem(String origem){
        this.origem = origem;
    }

    public String getOrigem(){
        return this.origem;
    }

    public void atribuirCritica(Filme filme, double nota, String nomeDaCritica, String textoComentario) {
        
        Critica novaCritica = new Critica();
         
        novaCritica.setNota(nota);
            
        int novaQtd = filme.getQuantidade_criticos() + 1;
        filme.setQuantidade_criticos(novaQtd);
            
        double novaSoma = filme.getSomaDasNotas() + nota;
        filme.setSomaDasNotas(novaSoma);
            
        double novaMedia = novaSoma / novaQtd;
        filme.setMedia(novaMedia);

        novaCritica.setComentario(textoComentario);

        int posicao = filme.getContadorCriticas();
        if (posicao < 100) {

            Critica[] vetorDoFilme = filme.getCriticas();
            vetorDoFilme[posicao] = novaCritica;
            
            filme.setContadorCriticas(posicao + 1);
        }

        novaCritica.setNome_critica(nomeDaCritica);
        novaCritica.setOrigem(this.getOrigem());

        CriticasData.inserir(filme.getId(), novaCritica, nota);
    }

    public double comprarBilhetes(ArrayList<Bilhete> bilhetes){
        if(bilhetes.size() < 3) return 0;

        double valorDoFilme = bilhetes.get(0).getSessao().getFilme().getValor();

        Salas tipoDeSala = Salas.obterPorTipo(bilhetes.get(0).getSala().getNomeDaSala());
        double multiplicadorDaSala = tipoDeSala.getMultiplicadorValor();
        
        return (bilhetes.size() - 2) * (valorDoFilme * multiplicadorDaSala);
    }
}
