package gui;

import model.Base;
import model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaAlterarUsuario extends JFrame {

    private Base funcionario;
    private Usuario usuario;

    public TelaAlterarUsuario(Base usuario, Usuario usuarioSelecionado) {
        this.funcionario = usuario;
        this.usuario = usuarioSelecionado;
        
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
            try {
                String user = txtUser.getText();
                String senha = new String(txtSenha.getPassword());
                int idade = (int) spinIdade.getValue();
                String sexo = (String) comboSexo.getSelectedItem();
                String email = txtEmail.getText();
                String nomeCartao = txtNomeCartao.getText();
                String numCartao = txtNumCartao.getText();
                String cvv = txtCvv.getText();

                if (user.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Usuário e Senha são obrigatórios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                usuario.setUser(user);
                usuario.setSenha(senha);
                usuario.setIdade(idade);
                usuario.setSexo(sexo);
                usuario.setEmail(email);
                usuario.setNome_do_cartao(nomeCartao);
                usuario.setNumero_do_cartao(numCartao);
                usuario.setCodigo_verificador_do_cartao(cvv);

                funcionario.alterarUsuario(usuario);

                JOptionPane.showMessageDialog(this, "Dados alterados com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose(); 

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao processar as alterações.", "Erro", JOptionPane.ERROR_MESSAGE);
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
}