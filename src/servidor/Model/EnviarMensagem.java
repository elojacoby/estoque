package servidor.Model;

import java.io.Serializable;

// precisa ser Serializable pq objeto precisa ser transportado por um ObjectOutputStream
public class EnviarMensagem implements Serializable {
    // representa a operacao, se vai ser cadastro, listar....
    private String operacao;
    private Produto produto;
    // para a atualizacao e excluir pelo id
    private int id;
    // buscar produtos da api
    private String nomeBusca;

    public EnviarMensagem(String operacao) {
        this.operacao = operacao;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

}
