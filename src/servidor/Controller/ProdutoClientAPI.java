package servidor.Controller;

import servidor.Model.Produto;
import servidor.Model.ProdutoService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoClientAPI {
    private ProdutoService produtoService;
    private Scanner scanner;

      public ProdutoClientAPI() {
        try {
            System.out.println("Conectando ao servidor RMI...");
            
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            produtoService = (ProdutoService) registry.lookup("Estoque");
            
            System.out.println("Conectado ao servidor RMI com sucesso!");
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println("Erro ao conectar no servidor RMI:");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    excluirProduto();
                    break;
                case 5:
                    buscarProdutosDaAPI();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Atualizar Produto");
        System.out.println("4. Excluir Produto");
        System.out.println("5. Buscar Produtos da API Externa");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarProduto() {
        try {
            System.out.println("\n=== CADASTRAR PRODUTO ===");
            
            System.out.print("Código: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine();
            
            System.out.print("Preço: ");
            double preco = scanner.nextDouble();
            
            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();
            
            Produto produto = new Produto(id, nome, preco, quantidade, descricao);
            
            produtoService.adicionar(produto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    private void listarProdutos() {
        try {
            System.out.println("\n=== LISTA DE PRODUTOS ===");
            
            List<Produto> produtos = produtoService.listarProdutos();
            
            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto cadastrado.");
            } else {
                for (Produto produto : produtos) {
                    System.out.println(produto);
                    System.out.println("---------------------");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    private void atualizarProduto() {
        try {
            System.out.println("\n=== ATUALIZAR PRODUTO ===");
            
            System.out.print("Digite o código do produto a ser atualizado: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            List<Produto> produtos = produtoService.listarProdutos();
            Produto produtoParaAtualizar = null;
            
            for (Produto p : produtos) {
                if (p.getId() == id) {
                    produtoParaAtualizar = p;
                    break;
                }
            }
            
            if (produtoParaAtualizar == null) {
                System.out.println("Produto não encontrado.");
                return;
            }
            
            System.out.println("\nDados atuais do produto:");
            System.out.println(produtoParaAtualizar);
            
            System.out.println("\nDigite os novos dados:");
            
            System.out.print("Nome (" + produtoParaAtualizar.getNome() + "): ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                produtoParaAtualizar.setNome(nome);
            }
            
            System.out.print("Descrição (" + produtoParaAtualizar.getDescricao() + "): ");
            String descricao = scanner.nextLine();
            if (!descricao.isEmpty()) {
                produtoParaAtualizar.setDescricao(descricao);
            }
            
            System.out.print("Preço (" + produtoParaAtualizar.getPreco() + "): ");
            String precoStr = scanner.nextLine();
            if (!precoStr.isEmpty()) {
                produtoParaAtualizar.setPreco(Double.parseDouble(precoStr));
            }
            
            System.out.print("Quantidade (" + produtoParaAtualizar.getQuantidade() + "): ");
            String quantidadeStr = scanner.nextLine();
            if (!quantidadeStr.isEmpty()) {
                produtoParaAtualizar.setQuantidade(Integer.parseInt(quantidadeStr));
            }
            
            produtoService.atualizar(produtoParaAtualizar);
            System.out.println("Produto atualizado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    private void excluirProduto() {
        try {
            System.out.println("\n=== EXCLUIR PRODUTO ===");
            
            System.out.print("Digite o código do produto a ser excluído: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            produtoService.excluir(id);
            System.out.println("Produto excluído com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
        }
    }

    private void buscarProdutosDaAPI() {
        try {
            System.out.println("\n=== BUSCAR PRODUTOS DA API EXTERNA ===");
            
            List<Produto> produtosAPI = produtoService.buscarProdutosDaAPI();
            
            if (produtosAPI.isEmpty()) {
                System.out.println("Nenhum produto encontrado na API externa.");
                return;
            }
            
            System.out.println("\nProdutos encontrados na API externa:");
            for (int i = 0; i < produtosAPI.size(); i++) {
                System.out.println("Índice: " + i);
                System.out.println(produtosAPI.get(i));
                System.out.println("---------------------");
            }
            
            System.out.print("\nDeseja importar algum produto? (S/N): ");
            String opcao = scanner.nextLine();
            
            if (opcao.equalsIgnoreCase("S")) {
                System.out.print("Digite o índice do produto a ser importado: ");
                int indice = scanner.nextInt();
                scanner.nextLine();
                
                if (indice >= 0 && indice < produtosAPI.size()) {
                    Produto produtoSelecionado = produtosAPI.get(indice);
                    
                    System.out.print("Código do produto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    produtoSelecionado.setId(id);
                    
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();
                    produtoSelecionado.setQuantidade(quantidade);
                    
                    produtoService.adicionar(produtoSelecionado);
                    System.out.println("Produto importado com sucesso!");
                } else {
                    System.out.println("Índice inválido!");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar produtos na API: " + e.getMessage());
        }
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

        String json = jsonBuilder.toString();
        JSONArray produtosArray = new JSONObject(json).getJSONArray("products");
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

    public static void main(String[] args) {
        ProdutoClientAPI cliente = new ProdutoClientAPI();
        cliente.iniciar();
    }
}