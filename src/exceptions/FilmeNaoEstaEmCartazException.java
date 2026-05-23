package exceptions;

public class FilmeNaoEstaEmCartazException extends Exception{
    public FilmeNaoEstaEmCartazException() {
        super("O Filme escolhido não está em cartaz");
    }
}