package servidor.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.*;
import servidor.Model.Produto;

public class TableModel extends AbstractTableModel {
    private List<Produto> lista;

    private final int col_Nome = 0;
    private final int col_Descricao = 1;
    private final int col_Preco = 2;
    private final int col_Quantidade = 3;
    private final String[] columnNomes = {"Nome", "Descrição", "Preço", "Quantidade"};

    public TableModel() {
        this.lista = new ArrayList<>();
    }

    // 🔥 Construtor com lista
    public TableModel(List<Produto> lista) {
        this.lista = new ArrayList<>(lista);
    }

    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnNomes.length;
    }

    public void setLista(List<Produto> lista) {
        this.lista = new ArrayList<>(lista); 
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int row, int col) {
        Produto produto = this.lista.get(row);

        switch (col) {
            case col_Nome:
                return produto.getNome();
            case col_Descricao:
                return produto.getDescricao();
            case col_Preco:
                return produto.getPreco();
            case col_Quantidade:
                return produto.getQuantidade();
            default:
                return "";
        }
    }
    public void addProduto(Produto produto) {
        this.lista.add(produto);
        fireTableRowsInserted(this.lista.size()-1, this.lista.size()-1);
    }

    public void updateProduto(Produto produtoAtualizado) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == produtoAtualizado.getId()) {
                lista.set(i, produtoAtualizado);
                fireTableRowsUpdated(i, i);
                return;
            }
        }
    }

    public void removeLinha(int row) {
        lista.remove(row);
        fireTableRowsDeleted(row, row);
    }
}