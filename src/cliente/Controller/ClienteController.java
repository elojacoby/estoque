package cliente.Controller;

import cliente.Model.ProdutoDTO;
import cliente.View.*;
import servidor.Model.Produto;

public class ClienteController{
    private ScreenInicial screen;
    private BuscarScreen buscarScreen;
    private Produto produtoAtual;
    
    public ClienteController(ScreenInicial screen){
        this.screen = screen;
    }
    public void setBuscarScreen(BuscarScreen buscarScreen) {
        this.buscarScreen = buscarScreen;
        configurarListenersBusca();
    }
    private void configurarListenersBusca() {
        buscarScreen.setBuscarListener(e -> buscarProduto());
        // buscarScreen.setSalvarListener(e -> salvarProduto());
        buscarScreen.setLimparListener(e -> buscarScreen.limparCampos());
        // buscarScreen.setVoltarListener(e -> voltarParaInicial());
    }
    private void buscarProduto() {
        String nome = buscarScreen.getNomeProduto();
        // Implemente a busca na API aqui
        // produtoAtual = apiService.buscarProduto(nome);
        
        if (produtoAtual != null) {
            buscarScreen.mostrarResultadoBusca(
                produtoAtual.getNome(),
                produtoAtual.getDescricao(),
                produtoAtual.getPreco(),
                produtoAtual.getQuantidade()
            );
        } else {
            buscarScreen.mostrarMensagem("Produto não encontrado!");
        }
    }
}