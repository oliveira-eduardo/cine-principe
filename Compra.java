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

    public void calcularValorTotal(int numBilhetes) {
        double precoIngresso = sessao.getFilme().getValor();
        this.valorTotal = (numBilhetes * precoIngresso) + valorSnacks;
    }

}