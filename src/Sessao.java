public class Sessao {
    private int [][] cadeira = new int[10][15];
    private String horario;
    private Filme filme;

    public Sessao(Filme filme, String horario) {
        this.filme = filme;
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int[][] getCadeira() {
        return cadeira;
    }

    public void setCadeira(int[][] cadeira) {
        this.cadeira = cadeira;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public void cadeirasDisponiveis(){ 
        String [] lugares = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for(int i = 0; i < 10; i++){
            System.out.print(lugares[i]+ " | "); 
            for(int j = 0; j<15; j++){
                System.out.print(cadeira[i][j]+" ");
            }
            System.out.print(" | " + lugares[i]);
            System.out.println("");
        }
    }
    public void limparCadeira(int x, int y){
        int [][]cadeirasDisponiveis = getCadeira();
        cadeirasDisponiveis[x][y] = 0;
        setCadeira(cadeirasDisponiveis);
    }

    public boolean traducaoCadeira(String assento){
        String cadeira = assento.trim().toUpperCase();
        int linha, coluna;

        linha = cadeira.charAt(0) - 'A';
        coluna = cadeira.charAt(1) - '0';

        return escolhaCadeira(linha, coluna);
    }


    public boolean escolhaCadeira(int x,  int y){ //opção cadeira de acordo bilhete
        int [][]cadeirasDisponiveis = getCadeira();
        if(cadeirasDisponiveis[x][y] == 0){
            cadeirasDisponiveis[x][y] = 1;
            setCadeira(cadeirasDisponiveis);
            return true;
        }
        else{ 
            return false;
        }
    }

    public String buscarCadeirajuntas(int numeroDeBilhetes){
        int [][]cadeirasDisponiveis = getCadeira();
        String sugestao = "";
        int contador = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 15 - numeroDeBilhetes + 1; j++){
                boolean cadeirasJuntas = true;
                for(int k = 0; k < numeroDeBilhetes; k++){
                    if(cadeirasDisponiveis[i][j+k] == 1){
                        cadeirasJuntas = false;
                        break;
                    }
                }
                if(cadeirasJuntas){
                    sugestao += "|";

                    for(int k = 0; k < numeroDeBilhetes; k++){
                        sugestao += (char)('A' + i) + Integer.toString(j+k+1) + " ";
                    }
                    sugestao += "|";
                    contador++;
                    if (contador == 3){
                        return sugestao;
                    }
                }   
            }
        }
        return sugestao;
    }

    public String mostrarSessao() {
        if(this.filme == null){
            return ""; 
        }
        else{
            return "" + this.filme.getNome() + " Horário: " + this.horario;
        }
    }    


}  
    