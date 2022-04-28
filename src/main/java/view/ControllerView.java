package view;

import controller.CadastroController;
import personalizado.JButtonPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.event.KeyEvent.*;
import static javax.swing.JComponent.*;
import static javax.swing.KeyStroke.getKeyStroke;

public class ControllerView extends JFrame {

    public static final String EVENTO = "EVENTO";

    public ControllerView() {
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setResizable(false);
        setLayout(new GridBagLayout());
        setIconImage(new ImageIcon(getClass().getResource("/imagens/loja.png")).getImage());
    }

    public JButton getBotaoSalvarPadrao(CadastroController controller) {
        JButton btnSalvar = new JButton("Salvar [F1]");
        btnSalvar.setIcon(new ImageIcon(getClass().getResource("/imagens/save.png")));
        btnSalvar.addActionListener(a -> controller.cadastraNovoItem());
        btnSalvar.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F1, 0), EVENTO);
        btnSalvar.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
        btnSalvar.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.cadastraNovoItem();
            }
        });
        return btnSalvar;
    }

    public JButton getBotaoListarPadrao(CadastroController controller) {
        JButton btnListar = new JButton("Listar [F2]");
        btnListar.setIcon(new ImageIcon(getClass().getResource("/imagens/list.png")));
        btnListar.addActionListener(a -> controller.listaItens());
        btnListar.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F2, 0), EVENTO);
        btnListar.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
        btnListar.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.listaItens();
            }
        });
        return btnListar;
    }

    public JButton getBotaoLimparPadrao(CadastroController controller) {
        JButton btnLimpar = new JButton("Limpar [F3]");
        btnLimpar.setIcon(new ImageIcon(getClass().getResource("/imagens/clear.png")));
        btnLimpar.addActionListener(a -> controller.limpaTela());
        btnLimpar.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F3, 0), EVENTO);
        btnLimpar.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
        btnLimpar.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.limpaTela();
            }
        });
        return btnLimpar;
    }

    public JButton getBotaoSairPadrao() {
        JButton btnSair = new JButton("Sair [Esc]");
        btnSair.setIcon(new ImageIcon(getClass().getResource("/imagens/exit.png")));
        btnSair.addActionListener(a -> dispose());
        btnSair.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_ESCAPE, 0), EVENTO);
        btnSair.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
        btnSair.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        return btnSair;
    }
}
