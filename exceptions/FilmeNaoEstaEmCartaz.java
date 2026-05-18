package exceptions;

public class FilmeNaoEstaEmCartaz extends Exception{
    public FilmeNaoEstaEmCartaz() {
        super("O Filme escolhido não está em cartaz");
    }
}