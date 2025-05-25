package cliente.View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.concurrent.SubmissionPublisher;

import javax.swing.*;

public class BuscarScreen extends JPanel {
    private JTextField cmpNome;
    private JButton btnBuscar;
    private JButton btnSalvar;
    private JButton btnLimpar;

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
        JTextField cmpNome = new JTextField(20);

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

        JButton[] botoes = {btnBuscar, btnLimpar, btnSalvar};

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

        btnLimpar.addActionListener(e -> limparCampos(cmpNome));

        // Aqui no botão buscar, chama o Controller com a função de buscar na API
        // buscarProdutosDaAPI()
        btnBuscar.addActionListener(e -> {
            String nome = cmpNome.getText();
            String descricao = cmpNome.getText();
            String preco = cmpNome.getText();
            String quantidade = cmpNome.getText();

            JOptionPane.showMessageDialog(this,
                    "Produto encontrado:\nNome: " + nome +
                    "\nDescrição: " + descricao +
                    "\nPreço: " + preco +
                    "\nQuantidade: " + quantidade,
                    "Resultado da Busca", JOptionPane.INFORMATION_MESSAGE);
            
            habilitarBotaoSalvar(true);
            limparCampos(cmpNome);
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