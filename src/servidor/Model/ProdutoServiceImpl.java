package servidor.Model;

import servidor.DAO.ProdutoDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class ProdutoServiceImpl extends UnicastRemoteObject implements ProdutoService {
    private ProdutoDAO dao;

    public ProdutoServiceImpl() throws RemoteException {
        super();
        try {
            dao = new ProdutoDAO();
        } catch (SQLException e) {
            throw new RemoteException("Erro ", e);
        }
    }

    @Override
    public void adicionar(Produto p) throws RemoteException {
        try {
            dao.adicionar(p);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao adicionar produto", e);
        }
    }

    @Override
    public List<Produto> listarProdutos() throws RemoteException {
        return dao.listarProdutos();

    }

    @Override
    public void atualizar(Produto p) throws RemoteException {
        try {
            dao.atualizar(p);
        } catch (SQLException e) {
            throw new RemoteException("Erro ao atualizar produto", e);
        }
    }

    @Override
    public List<Produto> buscarProdutosDaAPI() throws RemoteException {
        try {
            return servidor.Controller.ProdutoClientAPI.buscarProdutoJson();
        } catch (Exception e) {
            throw new RemoteException("Erro ao buscar produtos da API", e);
        }
    }

    @Override
    public void excluir(int id) throws RemoteException {
        dao.excluir(id);

    }
}