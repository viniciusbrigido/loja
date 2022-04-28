package personalizado;

import javax.swing.*;
import java.awt.*;

public class JButtonTelaDeVendas extends JButtonPersonalizado {

    public JButtonTelaDeVendas(String descricao, String atalho, String tooltip, Icon icone) {
        setText(getTextoFormatadoBotao(descricao, atalho, 95));
        setIcon(icone);
        setToolTipText(tooltip);
        setMargin(new Insets(2, 2, 2, 2));
        setPreferredSize(new Dimension(160, 42));
        setMinimumSize(new Dimension(160, 42));
        setHorizontalAlignment(SwingUtilities.LEFT);
    }
}
