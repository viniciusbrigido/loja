package personalizado;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Collection;

public class IconTableCellRenderer extends JLabel implements TableCellRenderer {
    private Collection<InfoIcon> infoIcones;
    private InfoIcon iconeDefault;

    public IconTableCellRenderer(Collection<InfoIcon> infoIcones) {
        this.infoIcones = infoIcones;

        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
    }

    public IconTableCellRenderer(Collection<InfoIcon> infoIcones, InfoIcon iconeDefault) {
        this(infoIcones);
        this.iconeDefault = iconeDefault;
    }

    public IconTableCellRenderer() {
        this(null);
    }

    public void setInfoIcones(Collection<InfoIcon> infoIcones) {
        this.infoIcones = infoIcones;
    }

    public Collection<InfoIcon> getInfoIcones() {
        return infoIcones;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        InfoIcon infoIcone = retorneInfoIconePeloValor(value);

        if (infoIcone != null) {
            if (infoIcone.getTexto() != null) {
                setText(infoIcone.getTexto());
            }
            if (infoIcone.getTooltip() != null) {
                setToolTipText(infoIcone.getTooltip());
            }
            if (infoIcone.getIcone() != null) {
                setIcon(infoIcone.getIcone());
            }
        }

        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }

        return this;
    }

    private InfoIcon retorneInfoIconePeloValor(Object valor) {
        for (InfoIcon icone : infoIcones) {
            if (icone.valor.equals(valor)) {
                return icone;
            }
        }
        return iconeDefault;
    }

    @Override
    public void validate() {
    }

    @Override
    public void revalidate() {
    }

    @Override
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    }

    @Override
    public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
    }
}
