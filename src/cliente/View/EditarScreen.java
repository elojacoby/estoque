package cliente.View;

import servidor.DAO.ProdutoDAO;
import servidor.Model.Produto;
import servidor.Controller.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class EditarScreen extends JPanel {
    private JTextField campoNome;
    private JTextField campoDescricao;
    private JTextField campoPreco;
    private final Produto produto;
    private final ScreenInicial screenInicial;

    public EditarScreen(Produto produto, ScreenInicial screenInicial) {
        this.produto = produto;
        this.screenInicial = screenInicial;
        configurarTela();
    }

    private void configurarTela() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(243, 240, 243));

        JLabel titulo = new JLabel("Editar Produto");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(96, 88, 172));

        add(titulo, BorderLayout.NORTH);

        JPanel painelForm = new JPanel();
        painelForm.setLayout(new GridBagLayout());
        painelForm.setBackground(new Color(243, 240, 243));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblDescricao = new JLabel("Descrição:");
        JLabel lblPreco = new JLabel("Preço:");

        campoNome = new JTextField(20);
        campoDescricao = new JTextField(20);
        campoPreco = new JTextField(10);

        // Preenche os campos com os dados do produto
        campoNome.setText(produto.getNome());
        campoDescricao.setText(produto.getDescricao());
        campoPreco.setText(String.valueOf(produto.getPreco()));

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelForm.add(lblNome, gbc);

        gbc.gridx = 1;
        painelForm.add(campoNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        painelForm.add(lblDescricao, gbc);

        gbc.gridx = 1;
        painelForm.add(campoDescricao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        painelForm.add(lblPreco, gbc);

        gbc.gridx = 1;
        painelForm.add(campoPreco, gbc);

        add(painelForm, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(243, 240, 243));

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.setBackground(new Color(96, 88, 172));
        btnSalvar.setForeground(Color.WHITE);

        btnCancelar.setBackground(new Color(200, 200, 200));
        btnCancelar.setForeground(Color.BLACK);

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelBotoes, BorderLayout.SOUTH);

        // 🔥 Ação do botão Salvar
        btnSalvar.addActionListener(e -> salvarProduto());

        // 🔙 Ação do botão Cancelar
        btnCancelar.addActionListener(e -> voltarParaListagem());
    }

    private void salvarProduto() {
        String nome = campoNome.getText().trim();
        String descricao = campoDescricao.getText().trim();
        String precoTexto = campoPreco.getText().trim();

        if (nome.isEmpty() || descricao.isEmpty() || precoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double preco = Double.parseDouble(precoTexto);

            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setPreco(preco);

            ProdutoDAO dao = new ProdutoDAO();
            dao.atualizar(produto);

            JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso!");

            voltarParaListagem();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "O campo preço deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltarParaListagem() {
        try {
            ProdutoDAO dao = new ProdutoDAO();
            TableModel modelo = new TableModel(dao.listarProdutos());
            ListarScreen listarScreen = new ListarScreen(modelo, screenInicial);
            screenInicial.trocarPainel(listarScreen);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar listagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
