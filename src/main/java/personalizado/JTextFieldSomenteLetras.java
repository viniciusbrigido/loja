package personalizado;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static util.ValueUtil.VAZIO;

public class JTextFieldSomenteLetras extends JTextField {
    private int maximoCaracteres = -1;

    public JTextFieldSomenteLetras() {
        super();
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                jTextFieldKeyTyped(evt);
            }
        });
    }

    public JTextFieldSomenteLetras(int maximo) {
        super();
        setMaximoCaracteres(maximo);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                jTextFieldKeyTyped(evt);
            }
        });
    }

    private void jTextFieldKeyTyped(KeyEvent evt) {
        String caracteres = "0987654321";

        if (caracteres.contains(evt.getKeyChar() + VAZIO)) {
            evt.consume();
        }
        if (getText().length() >= getMaximoCaracteres() && getMaximoCaracteres() != -1) {
            evt.consume();
            setText(getText().substring(0, getMaximoCaracteres()));
        }
    }

    public int getMaximoCaracteres() {
        return maximoCaracteres;
    }

    public void setMaximoCaracteres(int maximoCaracteres) {
        this.maximoCaracteres = maximoCaracteres;
    }
}
