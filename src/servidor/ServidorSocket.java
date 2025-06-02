package servidor;

import servidor.Model.Produto;
import servidor.Model.ProdutoServiceImpl;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12347)) {
            System.out.println("Servidor TCP aguardando conexões...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Produto produto = (Produto) input.readObject();

                ProdutoServiceImpl service = new ProdutoServiceImpl();
                service.cadastrar(produto);

                System.out.println("Produto salvo no banco com sucesso: " + produto.getNome());

                input.close();
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
