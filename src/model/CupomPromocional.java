package model;
public enum CupomPromocional {
    //mudar esses nomes
    FIDELIDADE(0.5),
    BRADESCO(0.7),
    ELO(0.8);

    private double desconto;

    CupomPromocional(double desconto) {
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }

}