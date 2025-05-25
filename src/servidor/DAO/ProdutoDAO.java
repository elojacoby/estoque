package servidor.DAO;

import servidor.Model.Produto;

import java.sql.*;
import java.util.*;

public class ProdutoDAO {

    public ProdutoDAO() throws SQLException {
        new ConexaoDB();
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = """
                CREATE TABLE IF NOT EXISTS produtos(
                id SERIAL PRIMARY KEY,
                nome VARCHAR(200),
                preco DOUBLE PRECISION,
                quantidade INTEGER,
                descricao VARCHAR (300)
                )
                """;
        try (Connection conn = ConexaoDB.getConexao();
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cadastrar(Produto p) throws SQLException {
        String sql = "INSERT INTO produtos (nome, preco, quantidade, descricao) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getQuantidade());
            stmt.setString(4, p.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Produto p) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ?, descricao = ? WHERE id=?";
        try (Connection conn = ConexaoDB.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getQuantidade());
            stmt.setDouble(3, p.getPreco());
            stmt.setString(4, p.getDescricao());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = ConexaoDB.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = ConexaoDB.getConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setDescricao(rs.getString("descricao"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

}