import model.Filme;

public class Teste{

    public static void main(String[] args) {
        Filme teste = new Filme("Agente Secreto", "2h40m", "Thriller/Crime", 25.0f);
        Filme teste2 =  new Filme("Hamnet", "2h 5m", "Tragédia/Drama", 30.0f);

        
        MovieData.connect();
        MovieData.inserir(teste);
        MovieData.listar(); 
        String alvo = teste.getNome();
        teste = MovieData.pegar(alvo);   
        MovieData.apagar(teste.getId());
        MovieData.listar();
        MovieData.inserir(teste2);
        MovieData.listar();
        MovieData.limparTabela();
    }
}