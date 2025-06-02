package cliente.Controller;

import servidor.Model.Produto;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProdutoSocketClient {
    public void enviarProdutoParaServidor(Produto produto) throws Exception {
        Socket socket = new Socket("localhost", 12347);
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

        output.writeObject(produto);
        output.flush();

        output.close();
        socket.close();
    }
}
