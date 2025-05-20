package servidor.Model;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProdutoService extends Remote {
    void adicionar(Produto p) throws RemoteException;
    List<Produto> listarProdutos() throws RemoteException;
    void atualizar(Produto p) throws RemoteException;
    void excluir(int id) throws RemoteException;
    List<Produto> buscarProdutosDaAPI() throws RemoteException;

}