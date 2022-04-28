package view;

import controller.ListagemController;
import controller.ListagemGeralController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.event.KeyEvent.*;
import static javax.swing.JComponent.WHEN_FOCUSED;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import static javax.swing.KeyStroke.getKeyStroke;

public class ListagemView extends JFrame {

    public static final String EVENTO = "EVENTO";

    public ListagemView() {
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setResizable(true);
        setLayout(new GridBagLayout());
    }

    public JButton getBotaoCarregarPadrao(ListagemController controller) {
        JButton btnCarregar = new JButton("Carregar [F2]");
        btnCarregar.setIcon(new ImageIcon(getClass().getResource("/imagens/select.png")));
        btnCarregar.addActionListener(a -> controller.selecionaItem());
        btnCarregar.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F2, 0), EVENTO);
        btnCarregar.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
        btnCarregar.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.selecionaItem();
            }
        });
        return btnCarregar;
    }

    public JButton getBotaoExcluirPadrao(ListagemController controller) {
        JButton btnExcluir = new JButton("Excluir [F3]");
        btnExcluir.setIcon(new ImageIcon(getClass().getResource("/imagens/bin.png")));
        btnExcluir.addActionListener(a -> controller.excluiItem());
        btnExcluir.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F3, 0), EVENTO);
        btnExcluir.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
        btnExcluir.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.excluiItem();
            }
        });
        return btnExcluir;
    }

    public JButton getBotaoSairPadrao(ListagemGeralController controller) {
        JButton btnSair = new JButton("Sair [Esc]");
        btnSair.setIcon(new ImageIcon(getClass().getResource("/imagens/exit.png")));
        btnSair.addActionListener(a -> controller.sairTela());
        btnSair.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_ESCAPE, 0), EVENTO);
        btnSair.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
        btnSair.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sairTela();
            }
        });
        return btnSair;
    }
}
