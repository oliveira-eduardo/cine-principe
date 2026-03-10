import java.util.Scanner;

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

        public void setPreco(double preco) {
            this.preco = preco;   
        }
    }

    private Sala sala;      // sessão escolhida pelo usuário
    private String cadeira;     // cadeira que foi comprada
    private float valor;        // valor da compra

    public Compra(Sala sala, String cadeira, float valor) {
        this.sala = sala;
        this.cadeira = cadeira;
        this.valor = valor;
    }

    public String getCadeira() {
        return cadeira;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public void setCadeira(String cadeira) {
        this.cadeira = cadeira;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public static void escolherSessao() {
        
    }

    public static int numero_de_bilhetes(int numBilhetes){
        return numBilhetes;
    }

    public String listarProdutos() {
        return Produtos.PIPOCA.toString() + Produtos.CHOCOLATE.toString() + Produtos.REFRIGERANTE.toString() + Produtos.JUJUBA.toString();
    }

    //esse metodo pode ser melhorado
    public double escolherProduto(int opcao, int quantidade){
        switch(opcao) {
            case 1:
                return Produtos.PIPOCA.getPreco() * quantidade;
            case 2:
                return Produtos.CHOCOLATE.getPreco() * quantidade;
            case 3:
                return Produtos.REFRIGERANTE.getPreco() * quantidade;
            case 4:
                return Produtos.JUJUBA.getPreco() * quantidade;
            default:
                return 0;
        }
    }

    public double calcularValor(int numero_de_bilhetes, int indiceDaSessao, double valorSnack){
        return numero_de_bilhetes * sala.getSessoes()[indiceDaSessao].getFilme().getValor() + valorSnack;
    }

}