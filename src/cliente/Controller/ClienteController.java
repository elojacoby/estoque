package cliente.Controller;

import java.rmi.RemoteException;
import java.util.List;
import javax.swing.JOptionPane;
import servidor.Controller.TableModel;
import servidor.Model.Produto;
import servidor.Model.ProdutoService;
import cliente.View.*;

public class ClienteController {
    private ScreenInicial screen;
    private final BuscarScreen buscarScreen;
    private final CadastroScreen cadastroScreen;
    private final ListarScreen listarScreen;
    private final ProdutoService produtoService;
    private final TableModel tableModel;

    public ClienteController(CadastroScreen cadastroScreen, ListarScreen listarScreen, BuscarScreen buscarScreen,
                              ProdutoService produtoService, TableModel tableModel) {
        if (produtoService == null) {
            throw new IllegalArgumentException("ProdutoService não pode ser nulo");
        }
        this.cadastroScreen = cadastroScreen;
        this.listarScreen = listarScreen;
        this.buscarScreen = buscarScreen;
        this.produtoService = produtoService;
        this.tableModel = tableModel;
    }

    public void setScreenInicial(ScreenInicial screen) {
        this.screen = screen;
    }

    public void carregarProdutos() {
        try {
            List<Produto> produtos = produtoService.listarProdutos();
            tableModel.setLista(produtos);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    // public void cadastrarProduto(String nome, double preco, String descricao, int quantidade) {
    //     Produto produto = new Produto(nome, preco, quantidade, descricao);
    //     produtoService.cadastrar(produto); 
    // }

    public TableModel getTableModel() {
        return tableModel;
    }
}
