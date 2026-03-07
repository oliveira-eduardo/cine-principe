import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       
        //Usuario usuario = Cadastro();
        /*Filme filme = new Filme("avengers","b","c","d");
        Sessao cadeira = new Sessao(filme, "20:40");
        cadeira.cadeirasDisponiveis();
        Scanner leitor = new Scanner(System.in);
        int x, y;
        x = leitor.nextInt();
        y = leitor.nextInt();
        cadeira.escolhaCadeira(x, y);
        cadeira.cadeirasDisponiveis();
        */
       Catalogo();
    }

    public static void Catalogo(){
        Random gerador = new Random();
        String[] horarios = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};

        for(int i = 0; i < 7; i++){
            int quantidadeSessoes = gerador.nextInt(8);       
            if(quantidadeSessoes < 1){
                System.out.println("Horário: " + horarios[i] + " Sem sessão nesse horário ");
            }
            else{
                System.out.println("Horário: " + horarios[i]);
                Filmes(quantidadeSessoes); 
            }
        }
    }


    public static void Filmes(int num){

        Filme f1 = new Filme("Agente Secreto", "2h40m", "Thriller/Crime", "25.0");
        Filme f2 = new Filme("Hamnet", "2h 5m", "Tragédia/Drama", "30.0");
        Filme f3 = new Filme("Valor Sentimental", "2h 13m", "Drama/Tragicomedy", "20.0");
        Filme f4 = new Filme("Uma Batalha Após a Outra", "2h 42m", "Drama/Ação e suspense", "22.0");
        Filme f5 = new Filme("Sonhos de Trem", "1h 43m", " Drama", "18.0");
        Filme f6 = new Filme("Pecadores", "2h 17", "Terror/Ação", "20.0");
        Filme f7 = new Filme("A única saida", "2h 19m", "Comédia/Thriller", "25.0");

        switch (num) {
            case 1:
                System.out.print(f1);
                break;
            case 2:
                System.out.print(f2);
                break;
            case 3:
                System.out.print(f3);
                break;
            case 4:
                System.out.print(f4);
                break;
            case 5:
                System.out.print(f5);
                break;
            case 6:
                System.out.print(f6);
                break;
            case 7:
                System.out.print(f7);
                break;
        }
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
        System.out.println(usuario);
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

}