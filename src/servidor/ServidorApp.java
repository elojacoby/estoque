package servidor;

import servidor.Model.ProdutoService;
import servidor.Model.ProdutoServiceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorApp {
    public static void main(String[] args) {
        try {
            // Cria a instância do serviço
            ProdutoService service = new ProdutoServiceImpl();

            // Cria o registro RMI na porta 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Faz o bind do serviço com o nome "ProdutoService"
            registry.rebind("ProdutoService", service);

            System.out.println("Servidor pronto! Serviço ProdutoService registrado.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}
