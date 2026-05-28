package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.LineBorder;

import control.ControlCadastroUsuario;
import model.Usuario;

public class TelaCadastroUsuario extends JFrame {

    private ControlCadastroUsuario controller;

    public TelaCadastroUsuario() {
        this.controller = new ControlCadastroUsuario(); 

        setTitle("CINE PRÍNCIPE - Novo Usuário");
        setSize(480, 580); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        
        Color fundoCreme = new Color(244, 240, 233);       
        Color terracotaDestaque = new Color(166, 84, 55);  
        Color grafiteTexto = new Color(28, 28, 28);        
        Color cinzaMuted = new Color(125, 125, 125);       
        Color cinzaLinhaSutil = new Color(215, 210, 202);

        
        JPanel painelBase = new JPanel(new BorderLayout(0, 15));
        painelBase.setBackground(fundoCreme);
        painelBase.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        
        JPanel parteSuperior = new JPanel(new BorderLayout());
        parteSuperior.setOpaque(false);
        parteSuperior.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, cinzaLinhaSutil),
            BorderFactory.createEmptyBorder(0, 0, 12, 0)
        ));

        JLabel lblTituloTela = new JLabel("CADASTRO DE NOVO USUÁRIO", JLabel.CENTER);
        lblTituloTela.setFont(new Font("Serif", Font.PLAIN, 16));
        lblTituloTela.setForeground(grafiteTexto);
        parteSuperior.add(lblTituloTela, BorderLayout.CENTER);
        painelBase.add(parteSuperior, BorderLayout.NORTH);

        
        JPanel painel = new JPanel(new GridLayout(10, 2, 12, 12));
        painel.setOpaque(false);

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

        
        JTextField[] camposTexto = {txtUser, txtCpf, txtSenha, txtEmail, txtNomeCartao, txtNumCartao, txtCvv};
        for (JTextField txt : camposTexto) {
            txt.setFont(new Font("Arial", Font.PLAIN, 13));
            txt.setForeground(grafiteTexto);
            txt.setBackground(Color.WHITE);
            txt.putClientProperty("Component.arc", 6);
            txt.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(cinzaLinhaSutil, 1, true),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)
            ));
        }

        
        comboSexo.setFont(new Font("Arial", Font.PLAIN, 13));
        comboSexo.setForeground(grafiteTexto);
        comboSexo.setBackground(Color.WHITE);
        comboSexo.putClientProperty("Component.arc", 6);

        spinIdade.setBackground(Color.WHITE);
        if (spinIdade.getEditor() instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinIdade.getEditor();
            editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
            editor.getTextField().setFont(new Font("Arial", Font.BOLD, 13));
            editor.getTextField().setForeground(grafiteTexto);
        }

        
        JButton btnCadastrar = new JButton("FINALIZAR CADASTRO");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setBackground(terracotaDestaque);
        btnCadastrar.setFocusPainted(false);
        btnCadastrar.setBorderPainted(false);
        btnCadastrar.putClientProperty("JButton.buttonType", "square");
        btnCadastrar.putClientProperty("Component.arc", 6);
        btnCadastrar.setPreferredSize(new Dimension(0, 36));

        
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

        
        String[] labelsTexto = {
            "Usuário:", "CPF:", "Senha:", "Idade:", "Sexo:",
            "E-mail:", "Nome no Cartão:", "Número do Cartão:", "CVV:"
        };
        
        JLabel[] componentsLabels = new JLabel[labelsTexto.length];
        for (int i = 0; i < labelsTexto.length; i++) {
            componentsLabels[i] = new JLabel(labelsTexto[i]);
            componentsLabels[i].setFont(new Font("Arial", Font.BOLD, 13));
            componentsLabels[i].setForeground(grafiteTexto);
        }

        
        painel.add(componentsLabels[0]);   painel.add(txtUser);
        painel.add(componentsLabels[1]);   painel.add(txtCpf);
        painel.add(componentsLabels[2]);   painel.add(txtSenha);
        painel.add(componentsLabels[3]);   painel.add(spinIdade);
        painel.add(componentsLabels[4]);   painel.add(comboSexo);
        painel.add(componentsLabels[5]);   painel.add(txtEmail);
        painel.add(componentsLabels[6]);   painel.add(txtNomeCartao);
        painel.add(componentsLabels[7]);   painel.add(txtNumCartao);
        painel.add(componentsLabels[8]);   painel.add(txtCvv);
        
        painel.add(new JLabel(""));
        painel.add(btnCadastrar);

        painelBase.add(painel, BorderLayout.CENTER);
        add(painelBase);
    }
}