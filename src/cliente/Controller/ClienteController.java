package cliente.Controller;

import servidor.Controller.TableModel;
import cliente.View.*;

public class ClienteController {
    private ScreenInicial screen;
    private final BuscarScreen buscarScreen;
    private final CadastroScreen cadastroScreen;
    private final ListarScreen listarScreen;
    private final TableModel tableModel;

    public ClienteController(CadastroScreen cadastroScreen, ListarScreen listarScreen, BuscarScreen buscarScreen,
            TableModel tableModel) {
        this.cadastroScreen = cadastroScreen;
        this.listarScreen = listarScreen;
        this.buscarScreen = buscarScreen;
        this.tableModel = tableModel;
    }

    public void setScreenInicial(ScreenInicial screen) {
        this.screen = screen;
    }

    public TableModel getTableModel() {
        return tableModel;
    }
}
