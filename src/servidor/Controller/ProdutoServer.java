package servidor.Controller;

import servidor.Model.ProdutoServiceImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class ProdutoServer {
    public static void main(String[] args) {
        try {
            // Criar instância do serviço
            ProdutoServiceImpl obj = new ProdutoServiceImpl();
            
            // Criar registry na porta 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("Registry RMI criado na porta 1099");
            
            // Registrar o serviço
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("Estoque", obj); // Nome alterado para "Estoque" para combinar com o cliente
            
            System.out.println("Serviço 'Estoque' registrado com sucesso");
            System.out.println("Servidor RMI pronto e aguardando conexões...");
            
            // Manter o servidor ativo
            while(true) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.err.println("Erro no servidor RMI:");
            e.printStackTrace();
        }
    }
}