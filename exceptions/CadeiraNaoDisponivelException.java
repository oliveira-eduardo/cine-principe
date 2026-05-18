package exceptions;

public class CadeiraNaoDisponivelException extends Exception{
    public CadeiraNaoDisponivelException() {
        super("A cadeira escolhida não está disponível, escolha outra");
    }
}