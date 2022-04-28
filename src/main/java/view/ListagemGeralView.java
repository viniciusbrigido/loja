package view;

import controller.ListagemGeralController;
import dto.ColunaDto;
import personalizado.JTextFieldLimitador;
import personalizado.JTextFieldMoeda;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import static java.awt.Cursor.getDefaultCursor;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_F2;
import static javax.swing.JComponent.WHEN_FOCUSED;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import static javax.swing.KeyStroke.getKeyStroke;

public class ListagemGeralView extends ListagemView {

    private JPanel panelProduto;
    private JScrollPane scrollTabela;
    private JTable tableGrid;
    private AbstractTableModel grid;
    private JPanel panelBotoes;

    private JLabel lblProduto;
    private JLabel lblPreco;

    private JTextField txtProduto;
    private JTextField txtPreco;

    private JButton btnCarregar;
    private JButton btnExcluir;
    private JButton btnSair;

    private ListagemGeralController controller;

    public ListagemGeralView(ListagemGeralController controller, String titulo) {
        this.controller = controller;
        setTitle(titulo);
        initialize();
    }

    private void initialize() {
        setSize(new Dimension(650, 400));
        setPreferredSize(new Dimension(650, 400));
        add(getPanelProduto(), getGbcPanelProduto());
        add(getScrollTabela(), getGbcPanelGrid());
        add(getPanelBotoes(), getGbcPanelBotoes());
    }

    public AbstractTableModel getGrid() {
        if (grid == null) {

            grid = new AbstractTableModel() {
                public final String[] columnNames = controller.getNomeColunas();

                public int getColumnCount() {
                    return columnNames.length;
                }

                public String getColumnName(int column) {
                    return columnNames[column];
                }

                public int getRowCount() {
                    return controller.getItens().size();
                }

                public Object getValueAt(int row, int column) {
                    List<String> linhas = controller.getItens();
                    String[] linha = linhas.get(row).split(controller.SEPARADOR);
                    if (column == getColunaEditar() || column == getColunaExcluir()) {
                        return null;
                    }
                    return linha[column];
                }
            };
        }
        return grid;
    }

    public void fireTableDataChanged() {
        getGrid().fireTableDataChanged();
    }

    public JTable getTableGrid() {
        if (tableGrid == null) {
            tableGrid = new JTable();

            GridBagLayout gblPanelGrid = new GridBagLayout();
            gblPanelGrid.columnWidths = new int[]{300};
            gblPanelGrid.rowHeights = new int[]{0};
            gblPanelGrid.columnWeights = new double[]{0.0};
            gblPanelGrid.rowWeights = new double[]{0.0};
            tableGrid.setLayout(gblPanelGrid);
            tableGrid.setModel(getGrid());
            tableGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            TableColumnModel columnModel = tableGrid.getColumnModel();
            for (ColunaDto dto : controller.getColunas()) {
                DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
                defaultTableCellRenderer.setHorizontalAlignment(dto.getAlinhamento());
                int coluna = controller.getColunas().indexOf(dto);

                columnModel.getColumn(coluna).setCellRenderer(defaultTableCellRenderer);
                columnModel.getColumn(coluna).setPreferredWidth(dto.getTamanho());
            }

            if (controller.isTelaChamadaNaVendaOuFinalizacao()) {
                tableGrid.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
                tableGrid.getActionMap().put(EVENTO, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.selecionaItem();
                    }
                });

            } else {
                DefaultTableCellRenderer dtcrEditar = new DefaultTableCellRenderer();
                dtcrEditar.setIcon(new ImageIcon(getClass().getResource("/imagens/editar.png")));
                columnModel.getColumn(getColunaEditar()).setCellRenderer(dtcrEditar);
                columnModel.getColumn(getColunaEditar()).setMaxWidth(20);

                DefaultTableCellRenderer dtcrExcluir = new DefaultTableCellRenderer();
                dtcrExcluir.setIcon(new ImageIcon(getClass().getResource("/imagens/remover.png")));
                columnModel.getColumn(getColunaExcluir()).setCellRenderer(dtcrExcluir);
                columnModel.getColumn(getColunaExcluir()).setMaxWidth(20);

                tableGrid.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        int colunaSelecionada = ((JTable) e.getSource()).getSelectedColumn();

                        if (colunaSelecionada == getColunaEditar()) {
                            controller.selecionaItem();
                        } else if (colunaSelecionada == getColunaExcluir()) {
                            controller.excluiItem();
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        setCursor(getDefaultCursor());
                    }
                });

                tableGrid.addMouseMotionListener(new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        setCursor(isIconeExcluirEditarSelecionado(e) ? new Cursor(HAND_CURSOR) : getDefaultCursor());
                    }
                });
            }
        }
        return tableGrid;
    }

    private int getColunaEditar() {
        return controller.getColunas().size();
    }

    private int getColunaExcluir() {
        return controller.getColunas().size() + 1;
    }

    private boolean isIconeExcluirEditarSelecionado(MouseEvent e) {
        int colunaSelecionada = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
        return colunaSelecionada == getColunaEditar() || colunaSelecionada == getColunaExcluir();
    }

    public JScrollPane getScrollTabela() {
        if (scrollTabela == null) {
            scrollTabela = new JScrollPane();
            scrollTabela.setViewportView(getTableGrid());
        }
        return scrollTabela;
    }

    private JPanel getPanelBotoes() {
        if (panelBotoes == null) {
            panelBotoes = new JPanel();

            GridBagLayout gblPanelBotoes = new GridBagLayout();
            gblPanelBotoes.columnWidths = new int[]{0, 0, 0};
            gblPanelBotoes.rowHeights = new int[]{0};
            gblPanelBotoes.columnWeights = new double[]{0.0, 0.0, 0.0};
            gblPanelBotoes.rowWeights = new double[]{0.0};
            panelBotoes.setLayout(gblPanelBotoes);

            GridBagConstraints gbcBtnCarregar = new GridBagConstraints();
            gbcBtnCarregar.insets = new Insets(10, 5, 5, 5);
            gbcBtnCarregar.gridx = 0;
            gbcBtnCarregar.gridy = 0;
            panelBotoes.add(getBtnCarregar(), gbcBtnCarregar);

            GridBagConstraints gbcBtnExcluir = new GridBagConstraints();
            gbcBtnExcluir.insets = new Insets(10, 5, 5, 5);
            gbcBtnExcluir.gridx = 1;
            gbcBtnExcluir.gridy = 0;
            panelBotoes.add(getBtnExcluir(), gbcBtnExcluir);

            GridBagConstraints gbcBtnSair = new GridBagConstraints();
            gbcBtnSair.insets = new Insets(10, 5, 5, 5);
            gbcBtnSair.gridx = 2;
            gbcBtnSair.gridy = 0;
            panelBotoes.add(getbtnSair(), gbcBtnSair);
        }
        return panelBotoes;
    }

    public JPanel getPanelProduto() {
        if (panelProduto == null) {
            panelProduto = new JPanel();

            GridBagLayout gblPanelProduto = new GridBagLayout();
            gblPanelProduto.columnWidths = new int[]{0, 0};
            gblPanelProduto.rowHeights = new int[]{0, 0};
            gblPanelProduto.columnWeights = new double[]{0.0, 0.0};
            gblPanelProduto.rowWeights = new double[]{0.0, 0.0};
            panelProduto.setLayout(gblPanelProduto);

            GridBagConstraints gbcLblProduto = new GridBagConstraints();
            gbcLblProduto.fill = GridBagConstraints.BOTH;
            gbcLblProduto.insets = new Insets(5, 5, 5, 5);
            gbcLblProduto.gridx = 0;
            gbcLblProduto.gridy = 0;
            panelProduto.add(getLblProduto(), gbcLblProduto);

            GridBagConstraints gbcTxtProduto = new GridBagConstraints();
            gbcTxtProduto.fill = GridBagConstraints.BOTH;
            gbcTxtProduto.insets = new Insets(0, 5, 5, 5);
            gbcTxtProduto.gridx = 0;
            gbcTxtProduto.gridy = 1;
            gbcTxtProduto.weightx = 0.8;
            panelProduto.add(getTxtProduto(), gbcTxtProduto);

            GridBagConstraints gbcLblPreco = new GridBagConstraints();
            gbcLblPreco.fill = GridBagConstraints.BOTH;
            gbcLblPreco.insets = new Insets(5, 5, 5, 5);
            gbcLblPreco.gridx = 1;
            gbcLblPreco.gridy = 0;
            panelProduto.add(getLblPreco(), gbcLblPreco);

            GridBagConstraints gbcTxtPreco = new GridBagConstraints();
            gbcTxtPreco.fill = GridBagConstraints.BOTH;
            gbcTxtPreco.insets = new Insets(0, 5, 5, 5);
            gbcTxtPreco.gridx = 1;
            gbcTxtPreco.gridy = 1;
            gbcTxtPreco.weightx = 0.2;
            panelProduto.add(getTxtPreco(), gbcTxtPreco);
        }
        return panelProduto;
    }

    private GridBagConstraints getGbcPanelProduto() {
        final GridBagConstraints gbcPanelProduto = new GridBagConstraints();
        gbcPanelProduto.insets = new Insets(5, 5, 5, 5);
        gbcPanelProduto.fill = GridBagConstraints.BOTH;
        gbcPanelProduto.gridx = 0;
        gbcPanelProduto.gridy = 0;

        return gbcPanelProduto;
    }

    private GridBagConstraints getGbcPanelGrid() {
        final GridBagConstraints gbcPanelGrid = new GridBagConstraints();
        gbcPanelGrid.fill = GridBagConstraints.BOTH;
        gbcPanelGrid.weightx = 100.0;
        gbcPanelGrid.weighty = 50.0;
        gbcPanelGrid.gridwidth = 3;
        gbcPanelGrid.gridx = 0;
        gbcPanelGrid.gridy = 1;
        gbcPanelGrid.insets = new Insets(10, 10, 5, 10);

        return gbcPanelGrid;
    }

    private GridBagConstraints getGbcPanelBotoes() {
        final GridBagConstraints gbcPanelBotoes = new GridBagConstraints();
        gbcPanelBotoes.fill = GridBagConstraints.BOTH;
        gbcPanelBotoes.weightx = 100.0;
        gbcPanelBotoes.gridx = 0;
        gbcPanelBotoes.gridy = 2;

        return gbcPanelBotoes;
    }

    public JTextField getTxtProduto() {
        if (txtProduto == null) {
            txtProduto = new JTextField();
            txtProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
            txtProduto.setEnabled(false);
        }
        return txtProduto;
    }

    public JTextField getTxtPreco() {
        if (txtPreco == null) {
            txtPreco = new JTextField();
            txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
            txtPreco.setEnabled(false);
        }
        return txtPreco;
    }

    public JLabel getLblProduto() {
        if (lblProduto == null) {
            lblProduto = new JLabel("Produto:");
        }
        return lblProduto;
    }

    public JLabel getLblPreco() {
        if (lblPreco == null) {
            lblPreco = new JLabel("Preço:");
        }
        return lblPreco;
    }

    public JButton getBtnCarregar() {
        if (btnCarregar == null) {
            btnCarregar = getBotaoCarregarPadrao(controller);
            if (controller.isTelaChamadaNaVendaOuFinalizacao()) {
                btnCarregar.setText("Selecionar [F2]");
            }
        }
        return btnCarregar;
    }

    public JButton getBtnExcluir() {
        if (btnExcluir == null) {
            btnExcluir = getBotaoExcluirPadrao(controller);
        }
        return btnExcluir;
    }

    public JButton getbtnSair() {
        if (btnSair == null) {
            btnSair = getBotaoSairPadrao(controller);
        }
        return btnSair;
    }

    public void closeView() {
        dispose();
    }
}
