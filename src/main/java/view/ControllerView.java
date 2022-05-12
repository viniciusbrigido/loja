package view;

import personalizado.JTextFieldSomenteNumeros;
import javax.swing.*;
import java.awt.*;

public class ControllerView extends JFrame {

    public static final String EVENTO = "EVENTO";

    private JTextFieldSomenteNumeros txtCodigo;

    private JButton btnSalvar;
    private JButton btnSair;
    private JButton btnLimpar;
    private JButton btnListar;

    public ControllerView() {
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setResizable(false);
        setLayout(new GridBagLayout());
        setIconImage(new ImageIcon(getClass().getResource("/imagens/loja.png")).getImage());
    }

    public JButton getBtnSalvar() {
        if (btnSalvar == null) {
            btnSalvar = new JButton("Salvar [F1]");
            btnSalvar.setIcon(new ImageIcon(getClass().getResource("/imagens/save.png")));
        }
        return btnSalvar;
    }

    public JButton getBtnListar() {
        if (btnListar == null) {
            btnListar = new JButton("Listar [F2]");
            btnListar.setIcon(new ImageIcon(getClass().getResource("/imagens/list.png")));
        }
        return btnListar;
    }

    public JButton getBtnLimpar() {
        if (btnLimpar == null) {
            btnLimpar = new JButton("Limpar [F3]");
            btnLimpar.setIcon(new ImageIcon(getClass().getResource("/imagens/clear.png")));
        }
        return btnLimpar;
    }

    public JButton getBtnSair() {
        if (btnSair == null) {
            btnSair = new JButton("Sair [Esc]");
            btnSair.setIcon(new ImageIcon(getClass().getResource("/imagens/exit.png")));
        }
        return btnSair;
    }

    public JTextFieldSomenteNumeros getTxtCodigo() {
        if (txtCodigo == null) {
            txtCodigo = new JTextFieldSomenteNumeros();
            txtCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
            txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtCodigo;
    }
}
