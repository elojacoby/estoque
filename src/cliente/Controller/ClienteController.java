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
    }
}