package cliente.View;

import servidor.Model.EnviarMensagem;
import servidor.Model.Produto;
import servidor.Controller.TableModel;
import cliente.Controller.ProdutoClientAPI;
import cliente.Controller.ProdutoSocketClient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class BuscarScreen extends JPanel {
    private JTextField cmpNome;
    private JButton btnBuscar;
    private JButton btnSalvar;
    private JButton btnLimpar;
    private JTable tabela;

    public BuscarScreen() {
        setBackground(new Color(243, 240, 243));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(40, 15, 15, 15));
        criarPainelBuscar();
    }

    public void criarPainelBuscar() {
        JLabel titulo = new JLabel("Buscar Produtos");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(96, 88, 172));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titulo);
        add(Box.createVerticalStrut(10));

        JLabel subTitulo = new JLabel("Direto da API");
        subTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        subTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        subTitulo.setForeground(new Color(96, 88, 172));
        subTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(subTitulo);
        add(Box.createVerticalStrut(30));

        JLabel lblNome = new JLabel("Nome do produto:");
        cmpNome = new JTextField(20);

        btnBuscar = new JButton("Buscar");
        btnSalvar = new JButton("Salvar no Banco");
        btnSalvar.setEnabled(false);
        btnLimpar = new JButton("Limpar");

        Dimension tamanhoCampo = new Dimension(200, 40);
        cmpNome.setMaximumSize(tamanhoCampo);
        cmpNome.setPreferredSize(tamanhoCampo);

        adicionarLinha(lblNome, cmpNome);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(243, 240, 243));
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));

        Dimension tamanhoBtn = new Dimension(120, 40);
        Color corBtn = new Color(249, 248, 243);
        Color foreColor = new Color(96, 88, 172);

        JButton[] botoes = { btnBuscar, btnLimpar, btnSalvar };
        for (JButton btn : botoes) {
            btn.setBackground(corBtn);
            btn.setForeground(foreColor);
            btn.setMaximumSize(tamanhoBtn);
            btn.setPreferredSize(tamanhoBtn);
        }

        painelBotoes.add(btnBuscar);
        painelBotoes.add(Box.createHorizontalStrut(15));
        painelBotoes.add(btnSalvar);
        painelBotoes.add(Box.createHorizontalStrut(15));
        painelBotoes.add(btnLimpar);
        painelBotoes.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(15));
        add(painelBotoes);

        tabela = new JTable(new TableModel());
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(500, 200));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(scrollPane);

        // Ações dos botões
        btnLimpar.addActionListener(e -> limparCampos(cmpNome));

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = cmpNome.getText();
                try {
                    ProdutoClientAPI clientAPI = new ProdutoClientAPI();
                    List<Produto> produtos = clientAPI.buscarPorNome(nome);
                    TableModel model = new TableModel(produtos);
                    tabela.setModel(model);
                    btnSalvar.setEnabled(!produtos.isEmpty());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar produto: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabela.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Selecione um produto na tabela para salvar.");
                    return;
                }

                TableModel model = (TableModel) tabela.getModel();
                Produto produtoSelecionado = model.getProdutoAt(selectedRow);

                try {
                    String qtdStr = JOptionPane.showInputDialog("Digite a quantidade:");
                    int qtd = Integer.parseInt(qtdStr);
                    produtoSelecionado.setQuantidade(qtd);

                    EnviarMensagem mensagem = new EnviarMensagem("cadastrar");
                    mensagem.setProduto(produtoSelecionado);

                    ProdutoSocketClient client = new ProdutoSocketClient();
                    Object resposta = client.enviarMensagem(mensagem);

                    JOptionPane.showMessageDialog(null, resposta.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    public void habilitarBotaoSalvar(boolean habilitar) {
        btnSalvar.setEnabled(habilitar);
    }

    private void adicionarLinha(JLabel label, JTextField campo) {
        JPanel linha = new JPanel();
        linha.setLayout(new BoxLayout(linha, BoxLayout.Y_AXIS));
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(96, 88, 172));
        linha.setBackground(new Color(243, 240, 243));

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);

        linha.add(label);
        linha.add(Box.createVerticalStrut(5));
        linha.add(campo);

        linha.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(linha);
        add(Box.createVerticalStrut(10));
    }

    private void limparCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }
}
