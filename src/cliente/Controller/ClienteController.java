package cliente.Controller;

import cliente.Model.ProdutoDTO;
import cliente.View.*;

public class ClienteController{
    private ScreenInicial screen;
    
    public void iniciar(){
        screen = new ScreenInicial(this);
        screen.exibir();
    }
}