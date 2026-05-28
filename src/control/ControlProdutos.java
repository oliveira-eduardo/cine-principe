package control;

import model.Produtos;
import java.util.HashMap;
import java.util.Map;

public class ControlProdutos {

    private Map<Produtos, Integer> itensSelecionados;
    private double valorTotalProdutos;

    public ControlProdutos() {
        this.itensSelecionados = new HashMap<>();
        this.valorTotalProdutos = 0.0;
    }

    public double calcularSubtotal(int[] quantidadesSelecionadas) {
        this.valorTotalProdutos = 0.0;
        Produtos[] listaProdutos = Produtos.values();

        for (int i = 0; i < listaProdutos.length; i++) {
            this.valorTotalProdutos += quantidadesSelecionadas[i] * listaProdutos[i].getPreco();
        }
        
        return this.valorTotalProdutos;
    }

    public void processarSelecao(int[] quantidadesSelecionadas) {
        this.itensSelecionados.clear();
        Produtos[] listaProdutos = Produtos.values();
        
        for (int i = 0; i < listaProdutos.length; i++) {
            if (quantidadesSelecionadas[i] > 0) {
                this.itensSelecionados.put(listaProdutos[i], quantidadesSelecionadas[i]);
            }
        }
    }

    public Map<Produtos, Integer> getItensSelecionados() {
        return itensSelecionados;
    }

    public double getValorTotalProdutos() {
        return valorTotalProdutos;
    }
}