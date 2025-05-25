package cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import servidor.Model.ProdutoService;
import cliente.Controller.*;
import cliente.View.ScreenInicial;

public class ClienteApp {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ProdutoService stub = (ProdutoService) registry.lookup("Estoque");
        } catch (Exception e) {
            System.out.println("Cliente RMI falhou" + e);
        }

        ScreenInicial screen = new ScreenInicial();
        ClienteController controller = new ClienteController(screen);
        screen.exibir();
    }
}