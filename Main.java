import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Sala filme3d = new Sala();
        Sala imaxSala = new Sala();
        Sala tradicionalSala = new Sala();
        
        filme3d.setNomeDaSala("Sala 3D");
        imaxSala.setNomeDaSala("Sala Imax");
        tradicionalSala.setNomeDaSala("Sala tradicional");

        Usuario cliente = null;
        int opcao = -1;

        do {
            menu();
            opcao = leitor.nextInt();

            switch(opcao) {
                case 1:
                    cliente = Cadastro(leitor);
                    break;

                case 2:
                    System.out.println("Escolha a sala:");
                    System.out.println("1 " + imaxSala.getNomeDaSala());
                    System.out.println("2 " + tradicionalSala.getNomeDaSala());
                    System.out.println("3 " + filme3d.getNomeDaSala());
                    
                    int escolhaSala = leitor.nextInt();
                    Sala salaSelecionada = null;

                    if(escolhaSala == 1) salaSelecionada = imaxSala;
                    else if(escolhaSala == 2) salaSelecionada = tradicionalSala;
                    else if(escolhaSala == 3) salaSelecionada = filme3d;
                    else {
                        System.out.println("Escolha uma opção valida");
                        break;
                    }

                    System.out.println("Sessões disponiveis " + salaSelecionada.getNomeDaSala() + ":");
                    System.out.println(salaSelecionada.mostrarSala());


                    System.out.println("Digite a sessão:");
                    int numSessao = leitor.nextInt() - 1;

                    System.out.print("Valor do bilhete: " + salaSelecionada.getSessoes()[numSessao].getFilme().getValor() + "\n" +"Digite a quantidade de bilhetes: ");
                    int quantBilhete = leitor.nextInt();

                    if (numSessao >= 0 && numSessao < 7 && salaSelecionada.getSessoes()[numSessao] != null && quantBilhete > 0) {
                        Sessao sessaoAtiva = salaSelecionada.getSessoes()[numSessao];

                        if(quantBilhete > 1){
                            String sugestao = sessaoAtiva.buscarCadeirajuntas(quantBilhete);
                            System.out.println("\nSugestao de cadeira: " + sugestao);
                            System.out.println(" Cadeiras para: " + sessaoAtiva.getFilme().getNome());
                            sessaoAtiva.cadeirasDisponiveis();

                        }
                        else{
                            System.out.println("Cadeiras para: " + sessaoAtiva.getFilme().getNome());
                            sessaoAtiva.cadeirasDisponiveis();

                        }
                        Bilhete[] ticket = new Bilhete[quantBilhete];
                        for(int i =0; i < quantBilhete; i++){
                            System.out.println("Escolha linha e a Coluna em numeros:");
                            int x = leitor.nextInt();
                            int y = leitor.nextInt();

                            if (sessaoAtiva.escolhaCadeira(x, y)) {
                                if(cliente != null){
                                    String cadeira = Integer.toString(x) + Integer.toString(y);
                                    ticket[i] = new Bilhete(cliente, salaSelecionada, numSessao, cadeira);                                   
                                }
                                else{
                                    System.out.println("Sem Cadastro");
                                    Cadastro(leitor);                
                                }
                            }
                            else{
                                System.out.println("Cadeira ocupada");
                                i--;
                            }
                            
                        }
                        Scanner leitorCompra = new Scanner(System.in);
                        System.out.println("\nDeseja comprar snack?");
                        Compra compra = new Compra(sessaoAtiva);
                        System.out.println(compra.listarProdutos());
                        int item, qntd;
                        item = leitorCompra.nextInt();
                        System.out.println("Digite a quantidade: ");
                        qntd = leitorCompra.nextInt();
                        compra.calcularValorSnack(item, qntd);
                        compra.calcularValorTotal(quantBilhete);
                        System.out.println("Valor total da compra: " + compra.getValorTotal());
                        for(int i = 0; i < quantBilhete; i++){
                            System.out.printf("Bilhete %d: " + ticket[i].gerarBilhete() + "\n", i);
                        }


                    } else {
                        System.out.println("Sessão invalida");
                    }
                    break;
                         
                case 0:
                    break;

                default:
                    System.out.println("Opção invalida");
            }
        } while (opcao != 0);

        leitor.close();
    }


    public static Usuario Cadastro(Scanner entrada){
        String user;
        int idade;
        String cpf;
        String senha;
        String sexo;
        String email;
        String numero_do_cartao;
        String nome_do_cartao;
        String codigo_verificador_do_cartao;
        
        entrada.nextLine();
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

        System.out.print("1 - Cadastro\n");
        System.out.print("2 - Comprar billhete\n");
        System.out.print("0 - Sair\n");
    }
    
}