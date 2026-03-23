public enum Produtos {
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

        public String listarProdutos() {
        Produtos[] produtos = Produtos.values();
        String saida = "";
        for (int i = 0; i < produtos.length; i++) {
            saida += Integer.toString(i+1) + " " + produtos[i] + " " + Double.toString(produtos[i].getPreco()) + "\n";

        }
        return saida;
    }

    }