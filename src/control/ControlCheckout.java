package control;

import gui.TelaCheckout;
import model.Bilhete;
import model.Critico;
import model.Estudante;
import model.Usuario;
import model.CupomPromocional;

import java.util.ArrayList;

public class ControlCheckout {

    private TelaCheckout tela;

    public ControlCheckout(TelaCheckout tela) {
        this.tela = tela;
        processarValoresIniciais();
    }

    private void processarValoresIniciais() {
        String perfil = tela.getPerfil();
        ArrayList<Bilhete> bilhetes = tela.getBilhetes();
        double valorTotalProdutos = tela.getValorTotalProdutos();
        CupomPromocional cupom = tela.getCupom();

        Usuario usuarioBase = bilhetes.get(0).getUsuario();

        Usuario usuarioFinal;
        if (perfil.equals("Estudante")) {
            usuarioFinal = new Estudante(usuarioBase.getUser(), usuarioBase.getCpf(), usuarioBase.getSenha(), usuarioBase.getIdade(), usuarioBase.getSexo(), usuarioBase.getEmail(), usuarioBase.getNome_do_cartao(), usuarioBase.getNumero_do_cartao(), usuarioBase.getCodigo_verificador_do_cartao());
        } else if (perfil.equals("Crítico")) {
            //critico precisa de uma origem/orgao
            usuarioFinal = new Critico(usuarioBase.getUser(), usuarioBase.getCpf(), usuarioBase.getSenha(), usuarioBase.getIdade(), usuarioBase.getSexo(), usuarioBase.getEmail(), usuarioBase.getNome_do_cartao(), usuarioBase.getNumero_do_cartao(), usuarioBase.getCodigo_verificador_do_cartao(), "ANCINE");
        } else {
            usuarioFinal = usuarioBase;
        }

        tela.setUsuarioFinal(usuarioFinal);

        double valorTotalBilhetes = usuarioFinal.comprarBilhetes(bilhetes);
        tela.setValorTotalBilhetes(valorTotalBilhetes);

        double valorFinalCalculado;
        if (cupom != null) {
            valorFinalCalculado = usuarioFinal.realizarCompra(valorTotalBilhetes, valorTotalProdutos, cupom);
        } else {
            valorFinalCalculado = usuarioFinal.realizarCompra(valorTotalBilhetes, valorTotalProdutos);
        }
        
        tela.setValorFinalCalculado(valorFinalCalculado);
    }

    public void finalizarCompra() {
        Usuario usuarioFinal = tela.getUsuarioFinal();
        ArrayList<Bilhete> bilhetes = tela.getBilhetes();

        if (usuarioFinal instanceof Critico) {
            // TelaCritica telaCritica = new TelaCritica(compraAtual);
            // telaCritica.setVisible(true);
            tela.dispose();
            
        } else {
            String numCartao = usuarioFinal.getNumero_do_cartao();
            String cartaoMascarado = "****";
            
            if (numCartao != null && numCartao.length() >= 4) {
                cartaoMascarado = "********" + numCartao.substring(numCartao.length() - 4);
            }

            StringBuilder recibo = new StringBuilder();
            recibo.append("Compra realizada com sucesso!\n");
            recibo.append("Cobrado no cartão de final: ").append(cartaoMascarado).append("\n\n");
            recibo.append("------------------------------\n");

            for (int i = 0; i < bilhetes.size(); i++) {
                Bilhete b = bilhetes.get(i);
                recibo.append("Bilhete #").append(i + 1).append("\n");
                recibo.append("Usuário: ").append(b.getUsuario().getUser()).append("\n");
                recibo.append("Sala: ").append(b.getSala().getNomeDaSala()).append("\n");
                recibo.append("Sessão: ").append(b.getIndiceDaSessao() + 1).append("\n");
                recibo.append("Cadeira: ").append(b.getCadeira()).append("\n");
                recibo.append("------------------------------\n");
            }

            tela.exibirRecibo(recibo.toString());
            
            tela.dispose();
        }
    }
}