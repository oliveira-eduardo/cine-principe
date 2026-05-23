package exceptions;

public class FilmeNaoDisponivelNaHoraExeception extends Exception{
    public FilmeNaoDisponivelNaHoraExeception() {
        super("O Filme não está mais disponível neste horário, escolha outro");
    }
}