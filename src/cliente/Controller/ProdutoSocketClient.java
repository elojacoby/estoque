package cliente.Controller;

import servidor.Model.EnviarMensagem;
import servidor.Model.Produto;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProdutoSocketClient {

    public Object enviarMensagem(EnviarMensagem mensagem) throws Exception {
        Socket socket = new Socket("ip", 12347);
        ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());

        saida.writeObject(mensagem);
        // força o envio
        saida.flush();
        // lê a resposta enviada pelo servidor
        Object resposta = entrada.readObject();

        entrada.close();
        saida.close();
        socket.close();

        return resposta;
    }
}
