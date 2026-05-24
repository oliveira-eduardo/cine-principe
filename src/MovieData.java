
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Filme;

public class MovieData {

    public static void connect() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db")) {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists MovieData(id integer primary key autoincrement, nome text, duracao text, sinopse text, valor float)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inserir(Filme filme) {
        String sql = "insert into MovieData(nome, duracao, sinopse, valor) values ('" + filme.getNome() + "','" + filme.getDuracao() + "', '" + filme.getSinopse() + "', " + filme.getValor() + ")";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db"); PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idGerado = generatedKeys.getInt(1);
                    filme.setId(idGerado);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void alterar(Filme filme) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db")) {
            Statement statement = connection.createStatement();
            statement.execute("update MovieData set nome = '" + filme.getNome() + "', duracao = '" + filme.getDuracao() + "', sinopse = '" + filme.getSinopse() + "', valor = " + filme.getValor() + " where id = " + filme.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listar() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db")) {
            PreparedStatement stmt = connection.prepareStatement("select * from MovieData");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String duracao = resultSet.getString("duracao");
                String sinopse = resultSet.getString("sinopse");
                float valor = resultSet.getFloat("valor");
                System.out.println(id + " - " + nome + " - " + duracao + " - " + sinopse + " - " + valor);
            }
            System.out.println("\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Filme pegar(String nome) {
        String sql = "select * from MovieData where nome = '" + nome + "'";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db"); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                return new Filme(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("duracao"),
                        resultSet.getString("sinopse"),
                        resultSet.getFloat("valor")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void apagar(int id) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db")) {
            Statement statement = connection.createStatement();
            statement.execute("delete from MovieData where id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // usar apenas para testes
    public static void limparTabela() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db")) {
            Statement statement = connection.createStatement();

            statement.execute("delete from MovieData");
            statement.execute("delete from sqlite_sequence where name='MovieData'");
        } catch (SQLException e) {
            System.out.println("Erro ao limpar o banco: " + e.getMessage());
        }
    }

}
