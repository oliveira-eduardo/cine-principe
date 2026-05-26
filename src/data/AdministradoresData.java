package data;

import model.Administrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdministradoresData {

    private static final String URL_BANCO = "jdbc:sqlite:bancoUsuarios.db";


    public static void inserir(Administrador admin) {
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "INSERT INTO Administradores (id, nome, idade, email, salario, senha) VALUES (" 
                         + admin.getId() + ", '" 
                         + admin.getNome() + "', " 
                         + admin.getIdade() + ", '" 
                         + admin.getEmail() + "', " 
                         + admin.getSalario() + ", '" 
                         + admin.getSenha() + "')";
                         
            statement.execute(sql);
            System.out.println("Administrador inserido com sucesso!");
            
        } catch (SQLException e) {
            System.out.println("Erro ao inserir administrador: " + e.getMessage());
        }
    }


    public static Administrador pegar(String email) {
        Administrador admin = null;
        
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "SELECT * FROM Administradores WHERE email = '" + email + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int idade = resultSet.getInt("idade");
                String emailAdmin = resultSet.getString("email");
                double salario = resultSet.getDouble("salario");
                String senha = resultSet.getString("senha");
                
                admin = new Administrador(nome, idade, emailAdmin, senha, salario, id);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar administrador: " + e.getMessage());
        }
        
        return admin;
    }


    public static void alterar(Administrador admin) {
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "UPDATE Administradores SET "
                         + "nome = '" + admin.getNome() + "', "
                         + "idade = " + admin.getIdade() + ", "
                         + "email = '" + admin.getEmail() + "', "
                         + "salario = " + admin.getSalario() + ", "
                         + "senha = '" + admin.getSenha() + "' "
                         + "WHERE id = " + admin.getId();
                         
            int linhasAfetadas = statement.executeUpdate(sql); 
            
            if (linhasAfetadas > 0) {
                System.out.println("Administrador atualizado com sucesso!");
            } else {
                System.out.println("Nenhum administrador encontrado com o ID: " + admin.getId());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao alterar administrador: " + e.getMessage());
        }
    }

    
    public static void excluir(int id) {
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "DELETE FROM Administradores WHERE id = " + id;
            
            int linhasAfetadas = statement.executeUpdate(sql);
            
            if (linhasAfetadas > 0) {
                System.out.println("Administrador excluído com sucesso!");
            } else {
                System.out.println("Nenhum administrador encontrado com o ID: " + id);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao excluir administrador: " + e.getMessage());
        }
    }
}