package personalizado;

import javax.swing.*;

public class InfoIcon {
    private Icon icone;
    Object valor;
    private String texto;
    private String tooltip;

    public InfoIcon(Icon icone, Object valor, String texto, String tooltip) {
        super();
        this.icone = icone;
        this.valor = valor;
        this.texto = texto;
        this.tooltip = tooltip;
    }

    public InfoIcon(Icon icone, Object valor, String texto) {
        this(icone, valor, texto, null);
    }

    public InfoIcon(Icon icone, Object valor) {
        this(icone, valor, null, null);
    }

    public Icon getIcone() {
        return icone;
    }

    public Object getValor() {
        return valor;
    }

    public String getTooltip() {
        return tooltip;
    }

    public String getTexto() {
        return texto;
    }
}
