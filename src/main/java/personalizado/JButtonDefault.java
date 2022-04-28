package personalizado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.awt.Font.PLAIN;
import static java.util.Objects.isNull;
import static util.Cores.*;
import static util.FocusUtil.*;

public class JButtonDefault extends JButton {
    private static final int TAMANHO_FONTE = 13;
    private Color cor;
    private Color corFoco;

    public JButtonDefault(Color cor, Color corFoco) {
        super();
        this.cor = cor;
        this.corFoco = corFoco;
        setBackground(cor);
        setContentAreaFilled(false);
        setFont(new Font("Tahoma", PLAIN, TAMANHO_FONTE));
        acaoMouse();
    }

    @Override
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(true);
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(WHITE);
    }

    @Override
    public void setEnabled(boolean b) {
        super.setEnabled(b);
        setBackground(isEnabled() ? cor : DARK_GRAY);
    }

    @Override
    public void transferFocus() {
        super.transferFocus();
        setBackground(cor);
    }

    @Override
    public void transferFocusBackward() {
        super.transferFocusBackward();
        setBackground(cor);
    }

    private void acaoMouse() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                removeFocoBotaoAnterior();

                if (isEnabled()) {
                    setBackground(corFoco);
                    requestFocus();
                }

                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeFocoBotaoAnterior();

                if (isEnabled()) {
                    setBackground(cor);
                    requestFocus();
                }

                super.mouseExited(e);
            }
        });
    }

    private void removeFocoBotaoAnterior() {
        final Component componenteComFocoAnterior = getFocusOwner();
        if (isNull(componenteComFocoAnterior) || !(componenteComFocoAnterior instanceof JButtonDefault)) {
            return;
        }

        if (!isEnabled()) {
            return;
        }

        final JButtonDefault botaoComFocoAnterior = (JButtonDefault) componenteComFocoAnterior;
        botaoComFocoAnterior.setBackground(botaoComFocoAnterior.cor);
        botaoComFocoAnterior.requestFocus(false);
    }

    public void setFocusButton(final boolean isNext) {
        if (isNext) {
            setFocusNextButton();
        } else {
            setFocusPreviousButton();
        }
    }

    private void setFocusNextButton() {
        final JButtonDefault botao = (JButtonDefault) getFocusNextComponent(this);
        botao.setBackground(botao.corFoco);
    }

    private void setFocusPreviousButton() {
        final JButtonDefault botao = (JButtonDefault) getFocusPreviousComponent(this);
        botao.setBackground(botao.corFoco);
    }

    public static String getTextoFormatadoBotao(final String descricao, final String atalho, final int pixelsMarginAtalho) {
        final StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<body>");
        html.append("<div>");
        html.append(descricao);
        html.append("</div>");
        html.append("<div style='text-align:right; width:").append(pixelsMarginAtalho).append("px;'>");
        html.append(atalho);
        html.append("</div>");
        html.append("</body>");
        html.append("</html>");

        return html.toString();
    }
}
