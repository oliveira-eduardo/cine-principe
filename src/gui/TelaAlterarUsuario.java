package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import control.ControlAlterarUsuario;
import model.Base;
import model.Usuario;

public class TelaAlterarUsuario extends JFrame {

    private Base funcionario;
    private Usuario usuario;
    private ControlAlterarUsuario controlador; 

    public TelaAlterarUsuario(Base usuario, Usuario usuarioSelecionado) {
        this.funcionario = usuario;
        this.usuario = usuarioSelecionado;
        this.controlador = new ControlAlterarUsuario(this); 
        
        setTitle("Alterar Usuário - " + usuario.getNome());
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painel = new JPanel(new GridLayout(10, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JTextField txtUser = new JTextField();
        JTextField txtCpf = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        
        SpinnerModel spinnerModel = new SpinnerNumberModel(18, 0, 120, 1);
        JSpinner spinIdade = new JSpinner(spinnerModel);
        
        String[] opcoesSexo = {"Masculino", "Feminino", "Outro", "Prefiro não informar"};
        JComboBox<String> comboSexo = new JComboBox<>(opcoesSexo);
        
        JTextField txtEmail = new JTextField();
        JTextField txtNomeCartao = new JTextField();
        JTextField txtNumCartao = new JTextField();
        JTextField txtCvv = new JTextField();

        txtUser.setText(usuario.getUser());
        
        txtCpf.setText(usuario.getCpf());
        txtCpf.setEditable(false);
        txtCpf.setToolTipText("O CPF não pode ser alterado.");
        
        txtSenha.setText(usuario.getSenha());
        spinIdade.setValue(usuario.getIdade());
        comboSexo.setSelectedItem(usuario.getSexo());
        txtEmail.setText(usuario.getEmail());
        txtNomeCartao.setText(usuario.getNome_do_cartao());
        txtNumCartao.setText(usuario.getNumero_do_cartao());
        txtCvv.setText(usuario.getCodigo_verificador_do_cartao());

        JButton btnSalvar = new JButton("Salvar Alterações");

        btnSalvar.addActionListener((ActionEvent e) -> {
            boolean sucesso = controlador.salvarAlteracoes(
                txtUser.getText(),
                new String(txtSenha.getPassword()),
                (int) spinIdade.getValue(),
                (String) comboSexo.getSelectedItem(),
                txtEmail.getText(),
                txtNomeCartao.getText(),
                txtNumCartao.getText(),
                txtCvv.getText(),
                this.usuario,
                this.funcionario
            );

            if (sucesso) {
                dispose();
            }
        });

        painel.add(new JLabel("Usuário:"));               painel.add(txtUser);
        painel.add(new JLabel("CPF (Não editável):"));    painel.add(txtCpf);
        painel.add(new JLabel("Senha:"));                 painel.add(txtSenha);
        painel.add(new JLabel("Idade:"));                 painel.add(spinIdade);
        painel.add(new JLabel("Sexo:"));                  painel.add(comboSexo);
        painel.add(new JLabel("E-mail:"));                painel.add(txtEmail);
        painel.add(new JLabel("Nome no Cartão:"));        painel.add(txtNomeCartao);
        painel.add(new JLabel("Número do Cartão:"));      painel.add(txtNumCartao);
        painel.add(new JLabel("CVV:"));                   painel.add(txtCvv);
        
        painel.add(new JLabel(""));
        painel.add(btnSalvar);

        add(painel);
    }

    public void exibirMensagemAviso(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.WARNING_MESSAGE);
    }

    public void exibirMensagemErro(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void exibirMensagemInformativa(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}