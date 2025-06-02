package cliente.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class CadastroScreen extends JPanel {

    public CadastroScreen() {
        setBackground(new Color(243, 240, 243));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 15, 15, 15));

        criarPainelCadastro();
    }

    public void criarPainelCadastro() {

        JLabel titulo = new JLabel("Cadastro de Produtos");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(96, 88, 172));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(titulo);
        add(Box.createVerticalStrut(10));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField(20);

        JLabel lblDescricao = new JLabel("Descrição:");
        JTextField txtDescricao = new JTextField(20);

        JLabel lblPreco = new JLabel("Preço:");
        JTextField txtPreco = new JTextField(20);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        JTextField txtQuantidade = new JTextField(20);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnLimpar = new JButton("Limpar");

        Dimension tamanhoCampo = new Dimension(300, 40);

        txtNome.setMaximumSize(tamanhoCampo);
        txtNome.setPreferredSize(tamanhoCampo);

        txtDescricao.setMaximumSize(tamanhoCampo);
        txtDescricao.setPreferredSize(tamanhoCampo);

        txtPreco.setMaximumSize(tamanhoCampo);
        txtPreco.setPreferredSize(tamanhoCampo);

        txtQuantidade.setMaximumSize(tamanhoCampo);
        txtQuantidade.setPreferredSize(tamanhoCampo);

        adicionarLinha(lblNome, txtNome);
        adicionarLinha(lblDescricao, txtDescricao);
        adicionarLinha(lblPreco, txtPreco);
        adicionarLinha(lblQuantidade, txtQuantidade);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(243, 240, 243));
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));

        Dimension tamanhoBtn = new Dimension(120, 40);
        Color corBtn = new Color(249, 248, 243);
        Color foreColor = new Color(96, 88, 172);

        JButton[] botoes = { btnCadastrar, btnLimpar };

        for (JButton btn : botoes) {
            btn.setBackground(corBtn);
            btn.setForeground(foreColor);
            btn.setMaximumSize(tamanhoBtn);
            btn.setPreferredSize(tamanhoBtn);
        }

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(Box.createHorizontalStrut(15));
        painelBotoes.add(btnLimpar);

        painelBotoes.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(15));
        add(painelBotoes);

        btnLimpar.addActionListener(e -> limparCampos(txtNome, txtDescricao, txtPreco, txtQuantidade));

        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText();
            String descricao = txtDescricao.getText();
            String preco = txtPreco.getText();
            String quantidade = txtQuantidade.getText();

            JOptionPane.showMessageDialog(this,
                    "Produto cadastrado:\nNome: " + nome + "\nDescrição: " + descricao
                            + "\nPreço: " + preco + "\nQuantidade: " + quantidade);

            limparCampos(txtNome, txtDescricao, txtPreco, txtQuantidade);
        });
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
