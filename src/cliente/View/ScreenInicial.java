package cliente.View;

import java.awt.*;
import javax.swing.*;

public class ScreenInicial extends JFrame {
    private JPanel painelConteudo;
    private CadastroScreen painelCadastro;
    private BuscarScreen painelBusca;

    public ScreenInicial() {
        configurarTela();
    }

    public void configurarTela() {
        setSize(640, 400);
        setLocationRelativeTo(null);
        setTitle("Gerenciador de Estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(96, 88, 172));
        menu.setPreferredSize(new Dimension(150, 400));

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnListar = new JButton("Listar");
        JButton btnVoltar = new JButton("Voltar");

        Dimension tamanhoBtn = new Dimension(120, 40);
        Color corBtn = new Color(249, 248, 243);
        Color foreBtn = new Color(96, 88, 172);

        JButton[] botoes = {btnCadastrar, btnBuscar, btnEditar, btnExcluir, btnListar, btnVoltar};

        menu.add(Box.createVerticalGlue());

        for (JButton btn : botoes) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setBackground(corBtn);
            btn.setForeground(foreBtn);
            btn.setMaximumSize(tamanhoBtn);
            menu.add(btn);
            menu.add(Box.createVerticalStrut(20));
        }
        menu.add(Box.createVerticalGlue());

        painelConteudo = new JPanel();
        painelConteudo.setBackground(new Color(243, 240, 243));
        painelConteudo.setLayout(new BorderLayout());

        exibirTelaInicial();

        painelCadastro = new CadastroScreen();
        painelBusca = new BuscarScreen();

        btnCadastrar.addActionListener(e -> trocarPainel(painelCadastro));
        btnBuscar.addActionListener(e -> trocarPainel(painelBusca));


        btnVoltar.addActionListener(e -> exibirTelaInicial());

        add(menu, BorderLayout.WEST);
        add(painelConteudo, BorderLayout.CENTER);
    }

    public void exibirTelaInicial() {
        painelConteudo.removeAll();
        painelConteudo.setLayout(new BorderLayout());

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(new Color(243, 240, 243));
        painelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Margem superior de 20px

        JLabel titulo = new JLabel("Bem-Vindo ao Sistema de Estoque!");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(96, 88, 172));

        painelTitulo.add(titulo, BorderLayout.CENTER);
        painelConteudo.add(painelTitulo, BorderLayout.NORTH);

        try {
            ImageIcon imgFundo = new ImageIcon(getClass().getResource("/recursos/imagens/estoqueImg.jpg"));
            Image img = imgFundo.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            imgFundo = new ImageIcon(img);

            JLabel labelImagem = new JLabel(imgFundo);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
            painelConteudo.add(labelImagem, BorderLayout.CENTER);
        } catch (Exception e) {
            painelConteudo.add(new JLabel("Imagem não encontrada.", SwingConstants.CENTER), BorderLayout.CENTER);
        }

        painelConteudo.revalidate();
        painelConteudo.repaint();
    }

    private void trocarPainel(JPanel novoPainel) {
        painelConteudo.removeAll();
        painelConteudo.add(novoPainel, BorderLayout.CENTER);
        painelConteudo.revalidate();
        painelConteudo.repaint();
    }

    public void exibir() {
        setVisible(true);
    }
}