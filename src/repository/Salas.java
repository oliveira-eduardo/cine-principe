package repository;
public enum Salas{

    SALACOMUM("COMUM", 1.0),
    SALA3D("3D", 1.2),
    SALAXD("IMAX", 1.3),
    SALAXD3D("IMAX/3D", 1.4);

    private String tipoSala;
    private double multiplicadorValor;

    private Salas(String tipoSala, double multiplicadorValor) {
        this.tipoSala = tipoSala;
        this.multiplicadorValor = multiplicadorValor;
    }

        
    public String getTipoSala() {
        return tipoSala;
    }

    public double getMultiplicadorValor() {
        return multiplicadorValor;
    }

    public void setMultiplicadorValor(double multiplicadorValor) {
        this.multiplicadorValor = multiplicadorValor;
    }

    public static Salas obterPorTipo(String nomeBuscado) {
            for (Salas sala : Salas.values()) {
                if (sala.getTipoSala().equals(nomeBuscado)) {
                    return sala;
            }
        }
        throw new IllegalArgumentException("Nenhuma sala encontrada com o nome: " + nomeBuscado);
    }
       
}