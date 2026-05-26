import data.FilmeData;
import model.Filme;

public class Teste{

    public static void main(String[] args) {
        Filme teste = new Filme("Agente Secreto", "2h40m", "Thriller/Crime", 25.0f,"");
        Filme teste2 =  new Filme("Hamnet", "2h 5m", "Tragédia/Drama", 30.0f,"");

        
        FilmeData.connect();
        FilmeData.inserir(teste);
        FilmeData.listar(); 
        String alvo = teste.getNome();
        teste = FilmeData.pegar(alvo);   
        FilmeData.apagar(teste.getId());
        FilmeData.listar();
        FilmeData.inserir(teste2);
        FilmeData.listar();
        FilmeData.limparTabela();
    }
}