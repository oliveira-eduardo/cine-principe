package gui;

import data.UsuariosData;
import model.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaCadastroUsuario extends JFrame {

    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela, não o app todo
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // GridLayout 
        JPanel painel = new JPanel(new GridLayout(10, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JTextField txtUser = new JTextField();
        JTextField txtCpf = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        
        // JSpinner para a idade
        SpinnerModel spinnerModel = new SpinnerNumberModel(18, 0, 120, 1);
        JSpinner spinIdade = new JSpinner(spinnerModel);
        
        // JComboBox para o sexo
        String[] opcoesSexo = {"Masculino", "Feminino", "Outro", "Prefiro não informar"};
        JComboBox<String> comboSexo = new JComboBox<>(opcoesSexo);
        
        JTextField txtEmail = new JTextField();
        JTextField txtNomeCartao = new JTextField();
        JTextField txtNumCartao = new JTextField();
        JTextField txtCvv = new JTextField();

        JButton btnCadastrar = new JButton("Finalizar Cadastro");

        //Botão
        btnCadastrar.addActionListener((ActionEvent e) -> {
            try {
                String user = txtUser.getText();
                String cpf = txtCpf.getText();
                String senha = new String(txtSenha.getPassword()); // JPasswordField retorna char[], precisamos converter
                int idade = (int) spinIdade.getValue();
                String sexo = (String) comboSexo.getSelectedItem();
                String email = txtEmail.getText();
                String nomeCartao = txtNomeCartao.getText();
                String numCartao = txtNumCartao.getText();
                String cvv = txtCvv.getText();

                if (user.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Usuário, CPF e Senha são obrigatórios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Usuario novoUsuario = new Usuario(user, cpf, senha, idade, sexo, email, nomeCartao, numCartao, cvv);
                UsuariosData.inserir(novoUsuario);

                JOptionPane.showMessageDialog(this, 
                    "Cadastro realizado com sucesso!\n\nDados:\n" + novoUsuario.mostrarUsuario(), 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                dispose(); 

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao processar os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        //Montando o layout
        painel.add(new JLabel("Usuário:"));               painel.add(txtUser);
        painel.add(new JLabel("CPF:"));                   painel.add(txtCpf);
        painel.add(new JLabel("Senha:"));                 painel.add(txtSenha);
        painel.add(new JLabel("Idade:"));                 painel.add(spinIdade);
        painel.add(new JLabel("Sexo:"));                  painel.add(comboSexo);
        painel.add(new JLabel("E-mail:"));                painel.add(txtEmail);
        painel.add(new JLabel("Nome no Cartão:"));        painel.add(txtNomeCartao);
        painel.add(new JLabel("Número do Cartão:"));      painel.add(txtNumCartao);
        painel.add(new JLabel("CVV:"));                   painel.add(txtCvv);
        
        painel.add(new JLabel(""));
        painel.add(btnCadastrar);

        add(painel);
    }
}