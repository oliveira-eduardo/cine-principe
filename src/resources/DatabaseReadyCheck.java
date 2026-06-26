package resources;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseReadyCheck {

    public static void verificarEInicializarBancos() {
        ajustarBanco("bancoFilmes.db", 
                     "src/resources/schema_filmes.sql", 
                     "src/resources/seed_filmes.sql");
                     
        ajustarBanco("bancoUsuarios.db", 
                     "src/resources/schema_usuarios.sql", 
                     "src/resources/seed_usuarios.sql");
    }

    private static void ajustarBanco(String dbFileName, String schemaPath, String seedPath) {
        File dbFile = new File(dbFileName);
        
        if (dbFile.exists()) {
            return; 
        }

        String url = "jdbc:sqlite:" + dbFileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            executeScript(conn, schemaPath);
            executeScript(conn, seedPath);
        } catch (Exception e) {
            if (dbFile.exists()) {
                dbFile.delete();
            }
            System.err.println("Erro ao inicializar o banco " + dbFileName + ": " + e.getMessage());
        }
    }

    private static void executeScript(Connection conn, String filePath) throws Exception {
        File scriptFile = new File(filePath);
        if (!scriptFile.exists()) {
            throw new IllegalArgumentException("Script não encontrado no caminho: " + scriptFile.getAbsolutePath());
        }

        String sql = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
        String[] statements = sql.split(";");
        
        try (Statement stmt = conn.createStatement()) {
            for (String statement : statements) {
                String cmd = statement.trim();
                if (!cmd.isEmpty()) {
                    stmt.execute(cmd);
                }
            }
        }
    }
}