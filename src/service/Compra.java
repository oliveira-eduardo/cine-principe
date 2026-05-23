package service;
import model.Produtos;
import model.CupomPromocional;
public class Compra {

    private Sessao sessao;      // sessão escolhida pelo usuário
    private double valorTotal = 0.0;        // valor da compra
    private double valorSnacks = 0.0;

    public Compra(Sessao sessao) {
        this.sessao = sessao;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValor(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void calcularValorSnack(int opcao, int quantidade) {
        Produtos[] produtos = Produtos.values();
        int indice = opcao - 1;

        if (indice >= 0 && indice < produtos.length) {
            valorSnacks = produtos[indice].getPreco() * quantidade;
        }
        else{
            valorSnacks = 0;
        }
    }

    public void calcularValorSnack(int opcao, int quantidade, CupomPromocional CupomPromocional) {
        Produtos[] produtos = Produtos.values();
        int indice = opcao - 1;

        if (indice >= 0 && indice < produtos.length) {
            double valorDesconto = CupomPromocional.getDesconto();

            valorSnacks = produtos[indice].getPreco() * quantidade;

            valorSnacks = valorSnacks * (1 - valorDesconto);
        }
        
        else{
            valorSnacks = 0;
        }
    }

    public void calcularValorTotal(int numBilhetes) {
        double precoIngresso = sessao.getFilme().getValor();
        this.valorTotal = (numBilhetes * precoIngresso) + valorSnacks;
    }

    public void calcularValorTotal(int numBilhetes, CupomPromocional CupomPromocional) {
        double precoIngresso = sessao.getFilme().getValor();
        double totalIngressos = numBilhetes * precoIngresso;

        double valorDesconto = CupomPromocional.getDesconto();
        totalIngressos = totalIngressos * (1 - valorDesconto);

        this.valorTotal = totalIngressos + valorSnacks;
    }

}