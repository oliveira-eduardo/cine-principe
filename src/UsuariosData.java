import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Usuario;

public class UsuariosData {

    public static void connect() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoUsuarios.db")) {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists UsuariosData(id integer primary key autoincrement, user text unique, cpf text unique, senha text, idade integer, sexo text, email text unique, nome_do_cartao text, numero_do_cartao text, codigo_verificador_do_cartao text)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inserir(Usuario usuario) {
        String sql = "insert into UsuariosData(user, cpf, senha, idade, sexo, email, nome_do_cartao ,numero_do_cartao, codigo_verificador_do_cartao) values ('" + usuario.getUser() + "','" + usuario.getCpf() + "', '" + usuario.getSenha() + "', '" + usuario.getIdade() + "', '" + usuario.getSexo() + "', '" + usuario.getEmail() + "', '" + usuario.getNome_do_cartao() + "', '" + usuario.getNumero_do_cartao() + "', " + usuario.getCodigo_verificador_do_cartao() + ")";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoUsuarios.db"); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    usuario.setId(idGerado);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void alterar(Usuario usuario) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoUsuarios.db")) {
            Statement statement = connection.createStatement();
            statement.execute("update UsuariosData set user = '" + usuario.getUser() + "', cpf = '" + usuario.getCpf() + "', senha = '" + usuario.getSenha() + "', idade = " + usuario.getIdade() + "', sexo = " + usuario.getSexo() + "', email = " + usuario.getEmail() + "', nome_do_cartao = " + usuario.getNome_do_cartao() + "', numero_do_cartao = " + usuario.getNumero_do_cartao() + "', codigo_verificador_do_cartao = " + usuario.getCodigo_verificador_do_cartao() + " where id = " + usuario.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listar() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoUsuarios.db")) {
            PreparedStatement stmt = connection.prepareStatement("select * from UsuariosData");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String user = resultSet.getString("user");
                String cpf = resultSet.getString("cpf");
                String senha = resultSet.getString("senha");
                Integer idade = resultSet.getInt("idade");
                String sexo = resultSet.getString("sexo");
                String email = resultSet.getString("email");
                String nome_do_cartao = resultSet.getString("nome_do_cartao");
                String numero_do_cartao = resultSet.getString("numero_do_cartao");
                String codigo_verificador_do_cartao = resultSet.getString("codigo_verificador_do_cartao");
                System.out.println(id + " - " + user + " - " + cpf + " - " + senha + " - " + idade + " - " + sexo + " - " + email + " - " + nome_do_cartao + " - " + numero_do_cartao + " - " + codigo_verificador_do_cartao);
            }
            System.out.println("\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Usuario pegar(String user) {
        String sql = "select * from UsuariosData where user = '" + user + "'";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoUsuarios.db"); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                return new Usuario(
                        resultSet.getString("user"),
                        resultSet.getString("cpf"),
                        resultSet.getString("senha"),
                        resultSet.getInt("idade"),
                        resultSet.getString("sexo"),
                        resultSet.getString("email"),
                        resultSet.getString("nome_do_cartao"),
                        resultSet.getString("numero_do_cartao"),
                        resultSet.getString("codigo_verificador_do_cartao")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void apagar(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoUsuarios.db")) {
            Statement statement = connection.createStatement();
            statement.execute("delete from UsuariosData where id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void limparTabela() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoUsuarios.db")) {
            Statement statement = connection.createStatement();

            statement.execute("delete from UsuariosData");
            statement.execute("delete from sqlite_sequence where name='UsuariosData'");
        } catch (SQLException e) {
            System.out.println("Erro ao limpar o banco: " + e.getMessage());
        }
    }

}