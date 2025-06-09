package servidor;

import servidor.Model.EnviarMensagem;
import servidor.Model.Produto;
import servidor.Model.ProdutoServiceImpl;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12347, 50, InetAddress.getByName("192.168.0.10"))) {
            System.out.println("Servidor TCP aguardando conexões...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                try {

                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

                    ProdutoServiceImpl service = new ProdutoServiceImpl();
                    EnviarMensagem msg = (EnviarMensagem) input.readObject();

                    switch (msg.getOperacao()) {
                        case "cadastrar":
                            service.cadastrar(msg.getProduto());
                            output.writeObject("Produto cadastrado com sucesso!");
                            break;
                        case "listar":
                            output.writeObject(service.listarProdutos());
                            break;
                        case "excluir":
                            service.excluir(msg.getId());
                            output.writeObject("Produto excluído!");
                            break;
                        case "atualizar":
                            service.atualizar(msg.getProduto());
                            output.writeObject("Produto atualizado!");
                            break;
                        case "buscarApi":
                            output.writeObject(service.buscarProdutosDaAPI());
                            break;
                        default:
                            output.writeObject("Operação inválida");
                    }
                    input.close();
                    output.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
