package control;

import gui.TelaDesconto;
import gui.TelaCheckout;
import model.CupomPromocional;

public class ControlDesconto {

    private TelaDesconto tela;

    public ControlDesconto(TelaDesconto tela) {
        this.tela = tela;
    }

    public void voltar() {
        tela.getTelaProdutos().setVisible(true);
        tela.dispose();
    }

    public void avancar() {
        String perfil = (String) tela.getComboPerfil().getSelectedItem();
        String textoCupom = tela.getTxtCupom().getText().trim().toUpperCase();

        tela.setPerfilSelecionado(perfil);

        if (!textoCupom.isEmpty()) {
            try {
                CupomPromocional cupom = CupomPromocional.valueOf(textoCupom);
                tela.setCupomAplicado(cupom);
            } catch (IllegalArgumentException ex) {
                tela.exibirMensagemAviso("Cupom inválido! Verifique o código ou deixe o campo em branco.");
                return;
            }
        } else {
            tela.setCupomAplicado(null);
        }

        TelaCheckout telaCheckout = new TelaCheckout(tela);
        telaCheckout.setVisible(true);
        
        tela.dispose();
    }
}