package repository;
public enum Salas{

    SALACOMUM("Sala comum", 1.0),
    SALA3D("Sala 3D", 1.2),
    SALAXD("Sala XD", 1.3),
    SALAXD3D("Sala XD/3D", 1.4);

    private String tipoSala;
    private double multiplicadorValor;

    private Salas(String tipoSala, double multiplicadorValor) {
        this.tipoSala = tipoSala;
        this.multiplicadorValor = multiplicadorValor;
    }

        
    public String getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(String tipoSala) {
        this.tipoSala = tipoSala;
    }

    public double getMultiplicadorValor() {
        return multiplicadorValor;
    }

    public void setMultiplicadorValor(double multiplicadorValor) {
        this.multiplicadorValor = multiplicadorValor;
    }
       
}