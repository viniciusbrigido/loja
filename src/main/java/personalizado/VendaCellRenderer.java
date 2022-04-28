package personalizado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Collection;
import java.util.Objects;

public class VendaCellRenderer extends IconTableCellRenderer {

    private VendaCellRenderer(Collection<InfoIcon> infoIcones, InfoIcon iconeDefault) {
        super(infoIcones, iconeDefault);
    }

    public static IconTableCellRenderer criarIconTableCellRendered(Integer posicaoIcone) {
        return new VendaCellRenderer(IconeGridVendas.getIcones(), IconeGridVendas.getInfoIcon(posicaoIcone));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        return defineCorLinhaGrid(table, isSelected, row, component);
    }

    public static Component defineCorLinhaGrid(JTable table, boolean isSelected, int row, Component component) {
        ((JLabel) component).setBorder(new EmptyBorder(2, 6, 2, 6));
        if (isSelected) {
            return component;
        }

        Color color = ((row % 2) != 0) ? new Color(0xCC, 0xE7, 0xFB) : UIManager.getColor("Table.cellBackground");
        component.setBackground(Objects.nonNull(color) ? color : table.getBackground());
        return component;
    }
}
