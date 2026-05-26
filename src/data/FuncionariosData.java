package data;

import model.Funcionario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionariosData {

    private static final String URL_BANCO = "jdbc:sqlite:bancoUsuarios.db";

    
    public static void inserir(Funcionario funcionario) {
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "INSERT INTO Funcionarios (nome, idade, email, salario, senha) VALUES ('" 
                         + funcionario.getNome() + "', " 
                         + funcionario.getIdade() + ", '" 
                         + funcionario.getEmail() + "', " 
                         + funcionario.getSalario() + ", '" 
                         + funcionario.getSenha() + "')";
                         
            statement.execute(sql);
            System.out.println("Funcionário inserido com sucesso!");
            
        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    
    public static Funcionario pegar(String email) {
        Funcionario funcionario = null;
        
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "SELECT * FROM Funcionarios WHERE email = '" + email + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");
                String emailFunc = resultSet.getString("email");
                double salario = resultSet.getDouble("salario");
                String senha = resultSet.getString("senha");
                
                funcionario = new Funcionario(nome, idade, emailFunc, salario, senha);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionário: " + e.getMessage());
        }
        
        return funcionario;
    }


    public static void alterar(Funcionario funcionario) {
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "UPDATE Funcionarios SET "
                         + "nome = '" + funcionario.getNome() + "', "
                         + "idade = " + funcionario.getIdade() + ", "
                         + "salario = " + funcionario.getSalario() + ", "
                         + "senha = '" + funcionario.getSenha() + "' "
                         + "WHERE email = '" + funcionario.getEmail() + "'";
                         
            int linhasAfetadas = statement.executeUpdate(sql);
            
            if (linhasAfetadas > 0) {
                System.out.println("Funcionário atualizado com sucesso!");
            } else {
                System.out.println("Nenhum funcionário encontrado com o email: " + funcionario.getEmail());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao alterar funcionário: " + e.getMessage());
        }
    }

    
    public static void excluir(String email) {
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "DELETE FROM Funcionarios WHERE email = '" + email + "'";
            
            int linhasAfetadas = statement.executeUpdate(sql);
            
            if (linhasAfetadas > 0) {
                System.out.println("Funcionário excluído com sucesso!");
            } else {
                System.out.println("Nenhum funcionário encontrado com o email: " + email);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }
}