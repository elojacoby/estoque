package cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.SwingUtilities;

import servidor.Controller.TableModel;
import servidor.Model.ProdutoService;
import cliente.Controller.ClienteController;
import cliente.View.*;

public class ClienteApp {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Localiza o servidor RMI
                Registry registro = LocateRegistry.getRegistry("localhost", 1099);
                ProdutoService service = (ProdutoService) registro.lookup("ProdutoService");

                TableModel tableModel = new TableModel();
                ScreenInicial screenInicial = new ScreenInicial(null, null, null);

                // Telas
                CadastroScreen cadastroScreen = new CadastroScreen();
                ListarScreen listarScreen = new ListarScreen(tableModel, screenInicial);
                BuscarScreen buscarScreen = new BuscarScreen();

                // Controller
                ClienteController controller = new ClienteController(
                        cadastroScreen, listarScreen, buscarScreen, service, tableModel
                );

                // Tela principal
                ScreenInicial screen = new ScreenInicial(cadastroScreen, listarScreen, buscarScreen);
                controller.setScreenInicial(screen);

                screen.exibir();

            } catch (Exception erro) {
                erro.printStackTrace();
                System.out.println("Erro: " + erro.getMessage());
            }
        });
    }
}
