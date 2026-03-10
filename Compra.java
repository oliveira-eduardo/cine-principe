public class Compra {
    private enum Produtos {
        PIPOCA(30.0),
        REFRIGERANTE(10.0),
        CHOCOLATE(8.0),
        JUJUBA(4.0);
        
        private double preco;

        Produtos(double preco) {
            this.preco = preco;
        }

        public double getPreco() {
            return preco;
        }

    }

    private Sessao sessao;      // sessão escolhida pelo usuário
    private double valor = 0.0;        // valor da compra

    public Compra(Sessao sessao) {
        this.sessao = sessao;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    public void setCadeira(String cadeira) {
        this.cadeira = cadeira;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String listarProdutos() {
        Produtos[] produtos = Produtos.values();
        String saida = "";
        for (int i = 0; i < produtos.length; i++) {
            saida += Integer.toString(i+1) + produtos[i] + Double.toString(produtos[i].getPreco());
        }
        return saida;
    }

    public double calcularValorSnack(int opcao, int quantidade) {
        Produtos[] produtos = Produtos.values();
        int indice = opcao - 1;

        if (indice >= 0 && indice < produtos.length) {
            return produtos[indice].getPreco() * quantidade;
        }
        
        return 0.0;
    }

    public void calcularValorTotal(int numBilhetes, double valorSnacks) {
        double precoIngresso = sessao.getFilme().getValor();
        this.valor = (numBilhetes * precoIngresso) + valorSnacks;
    }

}