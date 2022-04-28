package controller;

import javax.swing.*;
import java.awt.*;
import static javax.swing.JOptionPane.*;

public abstract class Controller {

    public final String SEPARADOR = "separador";
    public final Integer DIREITA = SwingConstants.RIGHT;
    public final Integer ESQUERDA = SwingConstants.LEFT;
    public final Integer MEIO = SwingConstants.CENTER;

    public final Integer TIPO_LISTAGEM_VENDEDOR = 1;
    public final Integer TIPO_LISTAGEM_CLIENTE = 2;
    public final Integer TIPO_LISTAGEM_CONDICAO = 3;

    public static Integer index;

    public int showYesNoConfirmDialog(Component parentComponent, Object message, String title) {
        Object[] options = {"Sim", "N�o"};
        return showOptionDialog(parentComponent, message, title, CLOSED_OPTION, QUESTION_MESSAGE, null, options, null);
    }
}