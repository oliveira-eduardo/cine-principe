package control;

import gui.TelaDesconto;
import data.CriticosData;
import gui.TelaCheckout;
import model.Usuario;
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

    public void avancar(Usuario usuario) {
        String perfil = (String) tela.getComboPerfil().getSelectedItem();
        String textoCupom = tela.getTxtCupom().getText().trim().toUpperCase();

        if(perfil.equals("Crítico")) {
            try {
                if(CriticosData.pegar(usuario.getUser()) == null)
                    throw new IllegalArgumentException("Não é crítico");
                tela.setPerfilSelecionado(perfil);
            } catch (IllegalArgumentException ex) {
                tela.exibirMensagemAviso("Apenas para críticos cadastrados.");
                return;
            }
        } else {
            tela.setPerfilSelecionado(perfil);
        }

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