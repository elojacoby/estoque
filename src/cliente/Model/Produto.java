package cliente.Model;


import java.io.Serializable;

public class Produto implements Serializable {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private String descricao;

    public Produto(int id, String nome, double preco, int quantidade, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public Produto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void cadastrar() {

    }

    @Override
    public String toString() {
        return id + ": " + nome + " - R$" + preco + " - Quantidade: " + quantidade + "Descrição: " + descricao;
    }
}