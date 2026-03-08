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
       Sala();

    }

    public static void Sala(){ 
        Sala sala1 = new Sala(Sessao());
        Sala sala2 = new Sala(Sessao());
        Sala sala3 = new Sala(Sessao());

        Scanner leitor = new Scanner(System.in);

        System.out.println("Sala 1 teste");
        System.out.println(sala1);
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Sala 2 teste");
        System.out.println(sala2);

        /* switch (leitor) {
            case :
                
                break;
            default:
                throw new AssertionError();
        }
 */
    }

    public static Sessao [] Sessao(){
        Random gerador = new Random();
        Sessao [] sessoes = new Sessao[7];
        Filme semFilme = new Filme("Estamos sem sessão esse horário ","","",0.0f);
        String[] horarios = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00", "20:00"};

        for(int i = 0; i < 7; i++){
            int quantidadeSessoes = gerador.nextInt(8);       
            if(quantidadeSessoes >= 7){
                sessoes [i] = new Sessao(semFilme, horarios[i]);
            }
            else{
                sessoes [i] = new Sessao(Filmes(quantidadeSessoes), horarios[i]);
            }
        }
        return sessoes;
    }


    public static Filme Filmes(int num){

        Filme [] filmes = new Filme [7];

        filmes [0] = new Filme("Agente Secreto", "2h40m", "Thriller/Crime", 25.0f);
        filmes [1] = new Filme("Hamnet", "2h 5m", "Tragédia/Drama", 30.0f);
        filmes [2] = new Filme("Valor Sentimental", "2h 13m", "Drama/Tragicomedy", 20.0f);
        filmes [3] = new Filme("Uma Batalha Após a Outra", "2h 42m", "Drama/Ação e suspense", 22.0f);
        filmes [4] = new Filme("Sonhos de Trem", "1h 43m", " Drama", 18.0f);
        filmes [5] = new Filme("Pecadores", "2h 17", "Terror/Ação", 20.0f);
        filmes [6] = new Filme("A única saida", "2h 19m", "Comédia/Thriller", 25.0f);

        return filmes[num];

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