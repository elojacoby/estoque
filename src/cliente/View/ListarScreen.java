package cliente.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import cliente.View.ScreenInicial;
import servidor.Controller.TableModel;
import servidor.Model.EnviarMensagem;
import servidor.Model.Produto;

public class ListarScreen extends JPanel {
    private JTable tabela;
    private TableModel modelo;
    private ScreenInicial screenInicial; // Referência à janela principal

    public ListarScreen(TableModel modelo, ScreenInicial screenInicial) {
        this.modelo = modelo;
        this.screenInicial = screenInicial;
        initUI();
    }

    // ISSO AQUI É PRA CONEXAO COM O SERVIDOR
    // EnviarMensagem msg = new EnviarMensagem("listar");

    // Produto p = new Produto();
    // EnviarMensagem msg = new EnviarMensagem("atualizar");
    // msg.setProduto(p);

    // EnviarMensagem msg = new EnviarMensagem("excluir");
    // msg.setId();

    private void initUI() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tabela = new JTable(modelo);
        configurarTabela();

        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void configurarTabela() {
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabela.setGridColor(Color.LIGHT_GRAY);

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = tabela.getSelectedRow();
                    if (row != -1) {
                        int id = (int) tabela.getValueAt(row, 0);
                        String nome = (String) tabela.getValueAt(row, 1);
                        double preco = (double) tabela.getValueAt(row, 2);
                        int quantidade = (int) tabela.getValueAt(row, 3);
                        String descricao = (String) tabela.getValueAt(row, 4);

                        Produto produtoSelecionado = new Produto(id, nome, preco, quantidade, descricao);

                        // Cria a tela de edição
                        EditarScreen editarScreen = new EditarScreen(produtoSelecionado, screenInicial);

                        // Troca o painel usando o método da ScreenInicial
                        screenInicial.trocarPainel(editarScreen);
                    }
                }
            }
        });
    }
}
