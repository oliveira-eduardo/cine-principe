package model;
import java.util.ArrayList;
import repository.Salas;

public class Usuario {
    private int id;
    private String user;
    private String cpf;
    private String senha;
    private int idade;
    private String sexo;
    private String email;
    private String nome_do_cartao;
    private String numero_do_cartao;
    private String codigo_verificador_do_cartao;

    public Usuario(String user, String cpf, String senha, int idade, String sexo, String email, String nome_do_cartao, String numero_do_cartao, String codigo_verificador_do_cartao) {
        this.user = user;
        this.cpf = cpf;
        this.senha = senha;
        this.idade = idade;
        this.sexo = sexo;
        this.email = email;
        this.nome_do_cartao = nome_do_cartao;
        this.numero_do_cartao = numero_do_cartao;
        this.codigo_verificador_do_cartao = codigo_verificador_do_cartao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome_do_cartao(String nome_do_cartao) {
        this.nome_do_cartao = nome_do_cartao;
    }

    public void setNumero_do_cartao(String numero_do_cartao) {
        this.numero_do_cartao = numero_do_cartao;
    }

    public void setCodigo_verificador_do_cartao(String codigo_verificador_do_cartao) {
        this.codigo_verificador_do_cartao = codigo_verificador_do_cartao;
    }

    public String getUser() {
        return user;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

    public String getNome_do_cartao() {
        return nome_do_cartao;
    }

    public String getNumero_do_cartao() {
        return numero_do_cartao;
    }

    public String getCodigo_verificador_do_cartao() {
        return codigo_verificador_do_cartao;
    }

    public double comprarBilhetes(ArrayList<Bilhete> bilhetes){
        double valorDoFilme = bilhetes.get(0).getSessao().getFilme().getValor();

        Salas tipoDeSala = Salas.obterPorTipo(bilhetes.get(0).getSala().getNomeDaSala());
        double multiplicadorDaSala = tipoDeSala.getMultiplicadorValor();
        
        return bilhetes.size() * (valorDoFilme * multiplicadorDaSala);
    }

    public double realizarCompra(double valorBilhetes, double valorProdutos) {
        return (valorBilhetes + valorProdutos);
    }

    public double realizarCompra(double valorBilhetes, double valorProdutos, CupomPromocional cupom) {
        return (valorBilhetes + valorProdutos) * cupom.getDesconto();
    }

    public String mostrarUsuario(){
        return "nome: " + getUser() + " cpf: " + getCpf() + " senha: " + getSenha() + " idade: " + getIdade()
        + " sexo: " + getSexo() + " email: " + getEmail() + " nome no cartão: " + getNome_do_cartao() + " numero no cartao: " + getNumero_do_cartao()
        + " codigo do cartao: " + getCodigo_verificador_do_cartao();
    }
}