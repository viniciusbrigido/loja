package view;

import javax.swing.*;
import java.awt.*;

public class ListagemGeralView extends JFrame {

    private JPanel panelProduto;
    private JScrollPane scrollTabela;
    private JTable tableGrid;
    private JPanel panelBotoes;

    private JLabel lblProduto;
    private JLabel lblPreco;

    private JTextField txtProduto;
    private JTextField txtPreco;

    private JButton btnCarregar;
    private JButton btnExcluir;
    private JButton btnSair;

    public ListagemGeralView(String titulo) {
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setResizable(true);
        setLayout(new GridBagLayout());
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

    public JTable getTableGrid() {
        if (tableGrid == null) {
            tableGrid = new JTable();

            GridBagLayout gblPanelGrid = new GridBagLayout();
            gblPanelGrid.columnWidths = new int[]{300};
            gblPanelGrid.rowHeights = new int[]{0};
            gblPanelGrid.columnWeights = new double[]{0.0};
            gblPanelGrid.rowWeights = new double[]{0.0};
            tableGrid.setLayout(gblPanelGrid);
            tableGrid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        return tableGrid;
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
            panelBotoes.add(getBtnSair(), gbcBtnSair);
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
            btnCarregar = new JButton("Carregar [F2]");
            btnCarregar.setIcon(new ImageIcon(getClass().getResource("/imagens/select.png")));
        }
        return btnCarregar;
    }

    public JButton getBtnExcluir() {
        if (btnExcluir == null) {
            btnExcluir = new JButton("Excluir [F3]");
            btnExcluir.setIcon(new ImageIcon(getClass().getResource("/imagens/bin.png")));
        }
        return btnExcluir;
    }

    public JButton getBtnSair() {
        if (btnSair == null) {
            btnSair = new JButton("Sair [Esc]");
            btnSair.setIcon(new ImageIcon(getClass().getResource("/imagens/exit.png")));
        }
        return btnSair;
    }

    public void closeView() {
        dispose();
    }
}
