package cliente;

import javax.swing.SwingUtilities;

import servidor.Controller.TableModel;
import cliente.Controller.ClienteController;
import cliente.View.*;

public class ClienteApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TableModel tableModel = new TableModel();
                ScreenInicial screenInicial = new ScreenInicial(null, null, null);

                // Telas
                CadastroScreen cadastroScreen = new CadastroScreen();
                ListarScreen listarScreen = new ListarScreen(tableModel, screenInicial);
                BuscarScreen buscarScreen = new BuscarScreen();

                // Controller sem ProdutoService
                ClienteController controller = new ClienteController(
                        cadastroScreen, listarScreen, buscarScreen, tableModel);

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
