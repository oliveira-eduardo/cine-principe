import java.util.Scanner;

public class Compra {
    enum Produtos {
        PIPOCA,
        REFRIGERANTE,
        CHOCOLATE,
        JUJUBA        
    }

    private Sessao sessao;      // sessão escolhida pelo usuário
    private String cadeira;     // cadeira que foi comprada
    private float valor;        // valor da compra

    public Compra() {
        
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public String getCadeira() {
        return cadeira;
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
    public static int numero_de_bilhetes(){
        Scanner leitor = new Scanner(System.in);
        System.out.println("Quantos bilhetes deseja comprar?");
        int numero_de_bilhetes = leitor.nextInt();
        return numero_de_bilhetes;
    }
    public static void escolherProduto(){
        Scanner leitor = new Scanner(System.in);
        System.out.println("Deseja comprar algum produto? (S/N)");
        String resposta = leitor.nextLine();

    }
    public static float calcularvalor(int numero_de_bilhetes, Sala sala, int indiceDaSessao){
        return numero_de_bilhetes * sala.getSessoes()[indiceDaSessao].getFilme().getValor();
    }

}