package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Filme;

public class FilmeData {

    public static void connect() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db")) {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists FilmeData(id integer primary key autoincrement, nome text unique, duracao text, sinopse text, valor float, nomeImagem text)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void inserir(Filme filme) {
        if (pegar(filme.getNome()) != null) {
            return;
        }
        String sql = "insert into FilmeData(nome, duracao, sinopse, valor, nomeImagem) values ('" + filme.getNome() + "','" + filme.getDuracao() + "', '" + filme.getSinopse() + "', " + filme.getValor() + ", '" + filme.getNomeImagem() + "')";
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
            statement.execute("update FilmeData set nome = '" + filme.getNome() + "', duracao = '" + filme.getDuracao() + "', sinopse = '" + filme.getSinopse() + "', valor = " + filme.getValor() + ", nomeImagem = " + filme.getNomeImagem() + ", nome where id = " + filme.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void listar() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db")) {
            PreparedStatement stmt = connection.prepareStatement("select * from FilmeData");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String duracao = resultSet.getString("duracao");
                String sinopse = resultSet.getString("sinopse");
                float valor = resultSet.getFloat("valor");
                String nomeImagem = resultSet.getString("nomeImagem");
                System.out.println(id + " - " + nome + " - " + duracao + " - " + sinopse + " - " + valor + " - " + nomeImagem);
            }
            System.out.println("\n");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Filme pegar(String nome) {
        String sql = "select * from FilmeData where nome = '" + nome + "'";

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db"); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                return new Filme(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("duracao"),
                        resultSet.getString("sinopse"),
                        resultSet.getFloat("valor"),
                        resultSet.getString("nomeImagem")
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
            statement.execute("delete from FilmeData where id = " + id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int contarFilmes() {
        String sql = "select count(*) from FilmeData";
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db"); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet resultSet = stmt.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    // usar apenas para testes
    public static void limparTabela() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:bancoFilmes.db")) {
            Statement statement = connection.createStatement();

            statement.execute("delete from FilmeData");
            statement.execute("delete from sqlite_sequence where name='FilmeData'");
        } catch (SQLException e) {
            System.out.println("Erro ao limpar o banco: " + e.getMessage());
        }
    }

}
