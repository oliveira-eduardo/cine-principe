package repository;
import model.Filme;
public interface GerenciaFilme{
    void incluirFilme(Filme filme);
    void excluirFilme(Filme filme);
    void alterarFilme(Filme filme);

}