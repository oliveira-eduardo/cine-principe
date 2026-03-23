public enum CupomPromocional {
    //mudar esses nomes
    FIDELIDADE(0.5),
    BRADESCO(0.3),
    ELO(0.2);

    private double desconto;

    CupomPromocional(double desconto) {
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }

}