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

import control.ControlCadastroUsuario;
import model.Usuario;

public class TelaCadastroUsuario extends JFrame {

    private ControlCadastroUsuario controller;

    public TelaCadastroUsuario() {
        this.controller = new ControlCadastroUsuario(); 

        setTitle("Cadastro de Usuário");
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

        JButton btnCadastrar = new JButton("Finalizar Cadastro");

        btnCadastrar.addActionListener((ActionEvent e) -> {
            try {
                String user = txtUser.getText();
                String cpf = txtCpf.getText();
                String senha = new String(txtSenha.getPassword());
                int idade = (int) spinIdade.getValue();
                String sexo = (String) comboSexo.getSelectedItem();
                String email = txtEmail.getText();
                String nomeCartao = txtNomeCartao.getText();
                String numCartao = txtNumCartao.getText();
                String cvv = txtCvv.getText();

                Usuario usuarioCriado = controller.registarUsuario(user, cpf, senha, idade, sexo, email, nomeCartao, numCartao, cvv);

                JOptionPane.showMessageDialog(this, 
                    "Cadastro realizado com sucesso!\n\nDados:\n" + usuarioCriado.mostrarUsuario(), 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                dispose(); 


            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao processar os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

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