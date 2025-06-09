package cliente.Controller;

import servidor.Model.Produto;

import org.json.JSONArray;
import org.json.JSONObject;

import comum.ProdutoService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ProdutoClientAPI {

    public List<Produto> buscarPorNome(String nome) throws Exception {
        List<Produto> todos = buscarProdutoJson();
        List<Produto> filtrados = new ArrayList<>();

        for (Produto p : todos) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    public static List<Produto> buscarProdutoJson() throws Exception {
        URI uri = URI.create("https://dummyjson.com/products");
        URL url = uri.toURL();

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder jsonBuilder = new StringBuilder();
        String linha;

        while ((linha = reader.readLine()) != null) {
            jsonBuilder.append(linha);
        }
        reader.close();

        JSONArray produtosArray = new JSONObject(jsonBuilder.toString()).getJSONArray("products");
        List<Produto> lista = new ArrayList<>();

        for (int i = 0; i < produtosArray.length(); i++) {
            JSONObject obj = produtosArray.getJSONObject(i);
            Produto p = new Produto();
            p.setNome(obj.getString("title"));
            p.setDescricao(obj.getString("description"));
            p.setPreco(obj.getDouble("price"));
            p.setQuantidade(0);
            lista.add(p);
        }

        return lista;
    }
}
