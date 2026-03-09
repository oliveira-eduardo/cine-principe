

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
        int [][]cadeirasDisponiveis = getCadeira();
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
    public String escolhaCadeira(int x,  int y){ //opção cadeira de acordo bilhete
        int [][]cadeirasDisponiveis = getCadeira();
        if(cadeirasDisponiveis[x][y] == 0){
            cadeirasDisponiveis[x][y] = 1;
            setCadeira(cadeirasDisponiveis);
            return "Cadeira escolhida com sucesso";
        }
        else{ 
            return "Cadeira ocupada!";
        }
    }
    @Override
    public String toString() {
        if(this.filme == null){
            return ""; 
        }
        else{
            return "" + this.filme.getNome() + " Horário: " + this.horario;
        }
    }    


}  
    