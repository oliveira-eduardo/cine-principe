package data;

import service.Critica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CriticasData {

    private static final String URL_BANCO = "jdbc:sqlite:bancoFilmes.db";

    public static void inserir(int idFilme, Critica critica, double nota) {
        String sql = "INSERT INTO Criticas (filme_id, nome_critica, origem, comentario, nota) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL_BANCO);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFilme);
            pstmt.setString(2, critica.getNome_critica());
            pstmt.setString(3, critica.getOrigem());
            pstmt.setString(4, critica.getComentario());
            pstmt.setDouble(5, nota);
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro ao inserir crítica: " + e.getMessage());
        }
    }

    public static ArrayList<Critica> buscarPorFilme(int idFilme) {
        ArrayList<Critica> listaCriticas = new ArrayList<>();
        
        String sql = "SELECT * FROM Criticas WHERE filme_id = ?";

        try (Connection connection = DriverManager.getConnection(URL_BANCO);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFilme);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String nomeCritica = rs.getString("nome_critica");
                String origem = rs.getString("origem");
                String comentario = rs.getString("comentario");
                double nota = rs.getDouble("nota");
                
                Critica c = new Critica(nomeCritica, origem, comentario, nota);
                listaCriticas.add(c);
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao buscar críticas do filme: " + e.getMessage());
        }
        
        return listaCriticas;
    }
    
    public static void excluirPorFilme(int idFilme) {
        String sql = "DELETE FROM Criticas WHERE filme_id = ?";

        try (Connection connection = DriverManager.getConnection(URL_BANCO);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, idFilme);
            
        } catch (SQLException e) {
            System.out.println("Erro ao excluir críticas: " + e.getMessage());
        }
    }
}