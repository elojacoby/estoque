package cliente.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

import org.json.Cookie;

import cliente.Controller.ClienteController;

public class ScreenInicial extends JFrame{
    private ClienteController controller;

    public ScreenInicial(ClienteController controller) {
        this.controller = controller;
        configurarTela();
    }

    public void configurarTela(){
        setSize(640,400);
        setLocationRelativeTo(null);
        setTitle("Gerenciador de Estoque");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(new Color(96, 88, 172));
        menu.setPreferredSize(new Dimension(150, getHeight()));

        JButton btnCadastro = new JButton("Cadastrar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnSair = new JButton("Sair");

        Dimension tamanhoBtn = new Dimension(100, 40);
        Color corBtn = new Color(249, 248, 243);
        Color foreBtn = new Color(96, 88, 172);
        menu.add(Box.createVerticalGlue());

        menu.add(btnCadastro);
        btnCadastro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCadastro.setBackground(corBtn);
        btnCadastro.setForeground(foreBtn);
        menu.add(Box.createVerticalStrut(20));
        btnCadastro.setMaximumSize(tamanhoBtn);

        menu.add(btnBuscar);
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscar.setBackground(corBtn);
        btnBuscar.setForeground(foreBtn);
        menu.add(Box.createVerticalStrut(20));
        btnBuscar.setMaximumSize(tamanhoBtn);

        menu.add(btnEditar);
        btnEditar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEditar.setBackground(corBtn);
        btnEditar.setForeground(foreBtn);
        menu.add(Box.createVerticalStrut(20));
        btnEditar.setMaximumSize(tamanhoBtn);

        menu.add(btnExcluir);
        btnExcluir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExcluir.setBackground(corBtn);
        btnExcluir.setForeground(foreBtn);
        menu.add(Box.createVerticalStrut(20));
        btnExcluir.setMaximumSize(tamanhoBtn);

        menu.add(btnSair);
        btnSair.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSair.setBackground(corBtn);
        btnSair.setForeground(foreBtn);
        btnSair.setMaximumSize(tamanhoBtn);

        // btnCadastro.addActionListener(e -> controller.abrirTelaCadastro());
        // btnBuscar.addActionListener(e -> controller.buscarProduto());
        // btnListar.addActionListener(e -> controller.listarProduto());
        // btnEditar.addActionListener(e -> controller.editarProduto());
        // btnSair.addActionListener(e -> controller.sair());

        menu.add(Box.createVerticalGlue()); 
        
        JPanel conteudo = new JPanel();
        conteudo.setBackground(new Color(243, 240, 243));

        JLabel titulo = new JLabel("Bem-Vindo ao Sistema de Estoque!");
        conteudo.add(Box.createVerticalStrut(30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(new Color(96, 88, 172));
        conteudo.add(titulo, BorderLayout.NORTH);

        add(menu, BorderLayout.WEST);
        add(conteudo, BorderLayout.CENTER);
    }
    public void exibir(){
        setVisible(true); 
    }
}