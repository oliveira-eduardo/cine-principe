package data;

import model.Critico;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CriticosData {

    private static final String URL_BANCO = "jdbc:sqlite:bancoUsuarios.db";

    public static void inserir(Critico critico) {
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "INSERT INTO Criticos (user, cpf, senha, idade, sexo, email, nome_do_cartao, numero_do_cartao, codigo_verificador_do_cartao, origem) VALUES (" 
                         + "'" + critico.getUser() + "', " 
                         + "'" + critico.getCpf() + "', " 
                         + "'" + critico.getSenha() + "', " 
                         + critico.getIdade() + ", " 
                         + "'" + critico.getSexo() + "', " 
                         + "'" + critico.getEmail() + "', " 
                         + "'" + critico.getNome_do_cartao() + "', " 
                         + "'" + critico.getNumero_do_cartao() + "', " 
                         + "'" + critico.getCodigo_verificador_do_cartao() + "', " 
                         + "'" + critico.getOrigem() + "')";
                         
            statement.execute(sql);
            System.out.println("Crítico inserido com sucesso!");
            
        } catch (SQLException e) {
            System.out.println("Erro ao inserir crítico: " + e.getMessage());
        }
    }

    public static Critico pegar(String loginDigitado) {
        Critico criticoEncontrado = null;
        
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "SELECT * FROM Criticos WHERE user = '" + loginDigitado + "' OR cpf = '" + loginDigitado + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            
            if (resultSet.next()) {
                String user = resultSet.getString("user");
                String cpf = resultSet.getString("cpf");
                String senha = resultSet.getString("senha");
                int idade = resultSet.getInt("idade");
                String sexo = resultSet.getString("sexo");
                String email = resultSet.getString("email");
                String nomeCartao = resultSet.getString("nome_do_cartao");
                String numCartao = resultSet.getString("numero_do_cartao");
                String cvv = resultSet.getString("codigo_verificador_do_cartao");
                String origem = resultSet.getString("origem");
                
                criticoEncontrado = new Critico(user, cpf, senha, idade, sexo, email, nomeCartao, numCartao, cvv, origem);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar crítico: " + e.getMessage());
        }
        
        return criticoEncontrado; 
    }

    public static void alterar(Critico critico) {
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "UPDATE Criticos SET "
                         + "cpf = '" + critico.getCpf() + "', "
                         + "senha = '" + critico.getSenha() + "', "
                         + "idade = " + critico.getIdade() + ", "
                         + "sexo = '" + critico.getSexo() + "', "
                         + "email = '" + critico.getEmail() + "', "
                         + "nome_do_cartao = '" + critico.getNome_do_cartao() + "', "
                         + "numero_do_cartao = '" + critico.getNumero_do_cartao() + "', "
                         + "codigo_verificador_do_cartao = '" + critico.getCodigo_verificador_do_cartao() + "', "
                         + "origem = '" + critico.getOrigem() + "' "
                         + "WHERE user = '" + critico.getUser() + "'";
                         
            int linhasAfetadas = statement.executeUpdate(sql); 
            
            if (linhasAfetadas > 0) {
                System.out.println("Crítico atualizado com sucesso!");
            } else {
                System.out.println("Nenhum crítico encontrado com o user: " + critico.getUser());
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao alterar crítico: " + e.getMessage());
        }
    }

    public static void excluir(String user) {
        try (Connection connection = DriverManager.getConnection(URL_BANCO)) {
            Statement statement = connection.createStatement();
            
            String sql = "DELETE FROM Criticos WHERE user = '" + user + "'";
            
            int linhasAfetadas = statement.executeUpdate(sql);
            
            if (linhasAfetadas > 0) {
                System.out.println("Crítico excluído com sucesso!");
            } else {
                System.out.println("Nenhum crítico encontrado com o user: " + user);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao excluir crítico: " + e.getMessage());
        }
    }
}