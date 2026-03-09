import java.util.Scanner;

public class Compra {
    enum Produtos {
        PIPOCA,
        REFRIGERANTE,
        CHOCOLATE,
        JUJUBA        
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

    public static int escolherSessao() {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Escolha a sessão desejada:");
        int opcao = leitor.nextInt();

        return opcao;
    }
    public static int numero_de_bilhetes(int numBilhetes){
        return numBilhetes;
    }
    public static void escolherProduto(){
        Scanner leitor = new Scanner(System.in);
        System.out.println("Deseja comprar algum produto? (S/N)");
        String resposta = leitor.nextLine();

    }
    public float calcularvalor(int numero_de_bilhetes, int indiceDaSessao){
        return numero_de_bilhetes * sala.getSessoes()[indiceDaSessao].getFilme().getValor();
    }

}