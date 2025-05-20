package servidor.DAO;
import java.sql.*;

public class ConexaoDB {
    private static String url = "jdbc:postgresql://localhost:5432/Produtos";
    private static String usuario = "postgres";
    private static String senha = "eloisa1108";

    public ConexaoDB() {
    }

    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }

}