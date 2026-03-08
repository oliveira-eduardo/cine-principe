

public class Sessao {
    private int[][] cadeira = new int[10][15];
    private String horario;
    private Filme filme;

    public Sessao(Filme filme, String horario) {
        this.filme = filme;
        this.horario = horario;
    }

    public int[][] getCadeira() {
        return cadeira;
    }

    public void setCadeira(int[][] cadeira) {
        this.cadeira = cadeira;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public void cadeirasDisponiveis(){ 
        int [][]cadeirasDisponiveis = getCadeira();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j<15; j++){
                System.out.print(cadeira[i][j]+" ");
            }
            System.out.println("");
        }
    }
    public void escolhaCadeira(int x,  int y){ //opção cadeira de acordo bilhete
        int [][]cadeirasDisponiveis = getCadeira();
        cadeirasDisponiveis[x][y] = 1;
        setCadeira(cadeira);


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
    