package service;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.Bilhete;
import model.Produtos;

public class Compra {

    private Usuario usuario;
    private List<Bilhete> bilhetes;
    private List<Produtos> produtos;

    public Compra(Usuario usuario) {
        this.usuario = usuario;
        this.bilhetes = new ArrayList<>();
        this.produtos = new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Bilhete> getBilhetes() {
        return bilhetes;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void adicionarBilhete(Bilhete bilhete) {
        bilhetes.add(bilhete);
    }

    public void adicionarProduto(Produtos produto) {
        produtos.add(produto);
    }


    /* 
    public void calcularValorSnack(int opcao, int quantidade) {
        Produtos[] produtos = Produtos.values();
        int indice = opcao - 1;

        if (indice >= 0 && indice < produtos.length) {
            valorSnacks = produtos[indice].getPreco() * quantidade;
        }
        else{
            valorSnacks = 0;
        }
    }

    public void calcularValorSnack(int opcao, int quantidade, CupomPromocional CupomPromocional) {
        Produtos[] produtos = Produtos.values();
        int indice = opcao - 1;

        if (indice >= 0 && indice < produtos.length) {
            double valorDesconto = CupomPromocional.getDesconto();

            valorSnacks = produtos[indice].getPreco() * quantidade;

            valorSnacks = valorSnacks * (1 - valorDesconto);
        }
        
        else{
            valorSnacks = 0;
        }
    }

    public void calcularValorTotal(int numBilhetes) {
        double precoIngresso = sessao.getFilme().getValor();
        this.valorTotal = (numBilhetes * precoIngresso) + valorSnacks;
    }

    public void calcularValorTotal(int numBilhetes, CupomPromocional CupomPromocional) {
        double precoIngresso = sessao.getFilme().getValor();
        double totalIngressos = numBilhetes * precoIngresso;

        double valorDesconto = CupomPromocional.getDesconto();
        totalIngressos = totalIngressos * (1 - valorDesconto);

        this.valorTotal = totalIngressos + valorSnacks;
    }

    */

}