import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Sala Filme3d = new Sala();
        Sala imaxSala = new Sala();
        Sala tradicionSala = new Sala();
        //Usuario usuario = Cadastro();
       /*  Filme filme = new Filme("avengers","b","c",20.0f);
        Sessao cadeira = new Sessao(filme, "20:40");
        cadeira.cadeirasDisponiveis();
        Scanner leitor = new Scanner(System.in);
        int x, y;
        x = leitor.nextInt();
        y = leitor.nextInt();
        cadeira.escolhaCadeira(x, y);
        cadeira.cadeirasDisponiveis();
        Sala imax = new Sala();
        System.out.println(Filme3d);
        System.out.println(imax); */
        System.out.println(Filme3d.getSessoes[0]().buscarCadeirajuntas(5));
     


    }

    public static Usuario Cadastro(){
        String user;
        int idade;
        String cpf;
        String senha;
        String sexo;
        String email;
        String numero_do_cartao;
        String nome_do_cartao;
        String codigo_verificador_do_cartao;

        Scanner entrada = new Scanner(System.in);

        do{
            System.out.println("Digite seu nome de usuário:");
            user = entrada.nextLine();

        }while(!isOnlyLetter(user));

        do { 
            System.out.println("Digite seu os 11 digitos do seu CPF:");
            cpf = entrada.nextLine();            
        } while (!verificarCpf(cpf));

        System.out.println("Digite sua senha:");
        senha = entrada.nextLine();

        do { 
            System.out.println("Digite sua idade:");
            idade = entrada.nextInt();            
        } while (!verificadorIdade(idade));

        entrada.nextLine(); // Limpar o buffer do scanner
        System.out.println("Digite seu sexo:");
        sexo = entrada.nextLine();

        System.out.println("Digite seu email:");
        email = entrada.nextLine();

        System.out.println("Digite o nome do cartão:");
        nome_do_cartao = entrada.nextLine();

        System.out.println("Digite o número do cartão:");
        numero_do_cartao = entrada.nextLine();

        System.out.println("Digite o código verificador do cartão:");
        codigo_verificador_do_cartao = entrada.nextLine();
        
        entrada.close();
        Usuario usuario = new Usuario(user, cpf, senha, idade, sexo, email, nome_do_cartao, numero_do_cartao, codigo_verificador_do_cartao);
        return usuario;
    }

    public static boolean isOnlyLetter(String nome){ 
        boolean r = nome.matches("[\\p{L}\\s]+");
        return r;
    }
    public static boolean verificarCpf(String cpf){
        boolean c = cpf.trim().matches("\\d{11}");
        return c;
    }
    public static boolean verificadorIdade(int idade){
        return idade >= 1 && idade <= 130;
    }
    public static void menu(){

        System.out.print("1 - Cadastro");
        System.out.print("2 - Comprar billhete");
        System.out.print("0 - Sair");
    }
    
}