package view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class CadastroFoneFornecedorView extends ControllerView {

    private JScrollPane jScrollPaneGrid;
    private JTable tabelaFones;

    private JPanel panelFornecedor;
    private JPanel panelFoneSalvar;
    private JPanel panelFone;
    private JPanel panelSalvar;
    private JPanel panelBotoes;

    private JLabel lblFornecedor;
    private JLabel lblFone;

    private JTextField txtFornecedor;
    private JTextField txtFone;

    public CadastroFoneFornecedorView() {
        setTitle("Cadastro de Fones de Fornecedor");
        initialize();
    }

    private void initialize() {
        setSize(new Dimension(510, 400));
        setPreferredSize(new Dimension(510, 400));
        add(getPanelFornecedor(), getGbcPanelFornecedor());
        add(getPanelFoneSalvar(), getGbcPanelFoneSalvar());
        add(getjScrollPaneGrid(), getGbcPanelGrid());
        add(getPanelBotoes(), getGbcPanelBotoes());
    }

    private JPanel getPanelFornecedor() {
        if (panelFornecedor == null) {
            panelFornecedor = new JPanel();

            GridBagLayout gblPanelDescricao = new GridBagLayout();
            gblPanelDescricao.columnWidths = new int[]{0};
            gblPanelDescricao.rowHeights = new int[]{0, 0};
            gblPanelDescricao.columnWeights = new double[]{0.0};
            gblPanelDescricao.rowWeights = new double[]{0.0, 0.0};
            panelFornecedor.setLayout(gblPanelDescricao);

            GridBagConstraints gbcLblFornecedor = new GridBagConstraints();
            gbcLblFornecedor.fill = GridBagConstraints.BOTH;
            gbcLblFornecedor.insets = new Insets(5, 5, 5, 5);
            gbcLblFornecedor.gridx = 0;
            gbcLblFornecedor.gridy = 0;
            panelFornecedor.add(getLblFornecedor(), gbcLblFornecedor);

            GridBagConstraints gbcTxtFornecedor = new GridBagConstraints();
            gbcTxtFornecedor.fill = GridBagConstraints.BOTH;
            gbcTxtFornecedor.weightx = 100;
            gbcTxtFornecedor.insets = new Insets(0, 5, 5, 5);
            gbcTxtFornecedor.gridx = 0;
            gbcTxtFornecedor.gridy = 1;
            panelFornecedor.add(getTxtFornecedor(), gbcTxtFornecedor);
        }
        return panelFornecedor;
    }

    private JPanel getPanelFoneSalvar() {
        if (panelFoneSalvar == null) {
            panelFoneSalvar = new JPanel();

            GridBagLayout gblPanelCodigo = new GridBagLayout();
            gblPanelCodigo.columnWidths = new int[]{0, 0};
            gblPanelCodigo.rowHeights = new int[]{0};
            gblPanelCodigo.columnWeights = new double[]{0.0, 0.0};
            gblPanelCodigo.rowWeights = new double[]{0.0};
            panelFoneSalvar.setLayout(gblPanelCodigo);

            GridBagConstraints gbcPanelFone = new GridBagConstraints();
            gbcPanelFone.fill = GridBagConstraints.BOTH;
            gbcPanelFone.insets = new Insets(0, 0, 5, 5);
            gbcPanelFone.weightx = 100.0;
            gbcPanelFone.gridx = 0;
            gbcPanelFone.gridy = 0;
            panelFoneSalvar.add(getPanelFone(), gbcPanelFone);

            GridBagConstraints gbcPanelSalvar = new GridBagConstraints();
            gbcPanelSalvar.fill = GridBagConstraints.BOTH;
            gbcPanelSalvar.insets = new Insets(0, 5, 5, 5);
            gbcPanelSalvar.gridx = 1;
            gbcPanelSalvar.gridy = 0;
            panelFoneSalvar.add(getPanelSalvar(), gbcPanelSalvar);
        }
        return panelFoneSalvar;
    }

    private JPanel getPanelFone() {
        if (panelFone == null) {
            panelFone = new JPanel();

            GridBagLayout gblPanelCodigo = new GridBagLayout();
            gblPanelCodigo.columnWidths = new int[]{0};
            gblPanelCodigo.rowHeights = new int[]{0, 0};
            gblPanelCodigo.columnWeights = new double[]{0.0};
            gblPanelCodigo.rowWeights = new double[]{0.0, 0.0};
            panelFone.setLayout(gblPanelCodigo);

            GridBagConstraints gbcLblFone = new GridBagConstraints();
            gbcLblFone.fill = GridBagConstraints.BOTH;
            gbcLblFone.insets = new Insets(5, 5, 5, 5);
            gbcLblFone.gridx = 0;
            gbcLblFone.gridy = 0;
            panelFone.add(getLblFone(), gbcLblFone);

            GridBagConstraints gbcTxtFone = new GridBagConstraints();
            gbcTxtFone.fill = GridBagConstraints.BOTH;
            gbcTxtFone.insets = new Insets(0, 5, 5, 5);
            gbcTxtFone.weightx = 100.0;
            gbcTxtFone.gridx = 0;
            gbcTxtFone.gridy = 1;
            panelFone.add(getTxtFone(), gbcTxtFone);
        }
        return panelFone;
    }

    private JPanel getPanelSalvar() {
        if (panelSalvar == null) {
            panelSalvar = new JPanel();

            GridBagLayout gblPanelCodigo = new GridBagLayout();
            gblPanelCodigo.columnWidths = new int[]{0};
            gblPanelCodigo.rowHeights = new int[]{0};
            gblPanelCodigo.columnWeights = new double[]{0.0};
            gblPanelCodigo.rowWeights = new double[]{0.0};
            panelSalvar.setLayout(gblPanelCodigo);

            GridBagConstraints gbcBtnSalvar = new GridBagConstraints();
            gbcBtnSalvar.fill = GridBagConstraints.BOTH;
            gbcBtnSalvar.insets = new Insets(25, 5, 5, 5);
            gbcBtnSalvar.gridx = 0;
            gbcBtnSalvar.gridy = 0;
            panelSalvar.add(getBtnSalvar(), gbcBtnSalvar);
        }
        return panelSalvar;
    }

    public JScrollPane getjScrollPaneGrid() {
        if (jScrollPaneGrid == null) {
            jScrollPaneGrid = new JScrollPane();
            jScrollPaneGrid.setViewportView(getTabelaFones());
        }
        return jScrollPaneGrid;
    }

    public JTable getTabelaFones() {
        if (tabelaFones == null) {
            tabelaFones = new JTable();

            GridBagLayout gblPanelGrid = new GridBagLayout();
            gblPanelGrid.columnWidths = new int[]{300};
            gblPanelGrid.rowHeights = new int[]{0};
            gblPanelGrid.columnWeights = new double[]{0.0};
            gblPanelGrid.rowWeights = new double[]{0.0};
            tabelaFones.setLayout(gblPanelGrid);
            tabelaFones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        return tabelaFones;
    }

    private JPanel getPanelBotoes() {
        if (panelBotoes == null) {
            panelBotoes = new JPanel();

            GridBagLayout gblPanelBotoes = new GridBagLayout();
            gblPanelBotoes.columnWidths = new int[]{0};
            gblPanelBotoes.rowHeights = new int[]{0};
            gblPanelBotoes.columnWeights = new double[]{0.0};
            gblPanelBotoes.rowWeights = new double[]{0.0};
            panelBotoes.setLayout(gblPanelBotoes);

            GridBagConstraints gbcBtnSair = new GridBagConstraints();
            gbcBtnSair.insets = new Insets(10, 5, 5, 5);
            gbcBtnSair.gridx = 0;
            gbcBtnSair.gridy = 0;
            panelBotoes.add(getBtnSair(), gbcBtnSair);
        }
        return panelBotoes;
    }

    private GridBagConstraints getGbcPanelFornecedor() {
        final GridBagConstraints gbcPanelFornecedor = new GridBagConstraints();
        gbcPanelFornecedor.fill = GridBagConstraints.BOTH;
        gbcPanelFornecedor.weightx = 100.0;
        gbcPanelFornecedor.gridx = 0;
        gbcPanelFornecedor.gridy = 0;

        return gbcPanelFornecedor;
    }

    private GridBagConstraints getGbcPanelFoneSalvar() {
        final GridBagConstraints gbcPanelFoneSalvar = new GridBagConstraints();
        gbcPanelFoneSalvar.fill = GridBagConstraints.BOTH;
        gbcPanelFoneSalvar.gridx = 0;
        gbcPanelFoneSalvar.gridy = 1;

        return gbcPanelFoneSalvar;
    }

    private GridBagConstraints getGbcPanelGrid() {
        final GridBagConstraints gbcPanelGrid = new GridBagConstraints();
        gbcPanelGrid.fill = GridBagConstraints.BOTH;
        gbcPanelGrid.weightx = 100.0;
        gbcPanelGrid.weighty = 50.0;
        gbcPanelGrid.gridwidth = 3;
        gbcPanelGrid.gridx = 0;
        gbcPanelGrid.gridy = 2;
        gbcPanelGrid.insets = new Insets(10, 10, 5, 10);

        return gbcPanelGrid;
    }

    private GridBagConstraints getGbcPanelBotoes() {
        final GridBagConstraints gbcPanelBotoes = new GridBagConstraints();
        gbcPanelBotoes.fill = GridBagConstraints.BOTH;
        gbcPanelBotoes.gridx = 0;
        gbcPanelBotoes.gridy = 3;

        return gbcPanelBotoes;
    }

    public JTextField getTxtFornecedor() {
        if (txtFornecedor == null) {
            txtFornecedor = new JTextField();
            txtFornecedor.setFont(new Font("Tahoma", Font.PLAIN, 14));
            txtFornecedor.setEnabled(false);
        }
        return txtFornecedor;
    }

    public JTextField getTxtFone() {
        if (txtFone == null) {
            try {
                txtFone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtFone.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtFone;
    }

    public JLabel getLblFone() {
        if (lblFone == null) {
            lblFone = new JLabel("Fone:");
        }
        return lblFone;
    }

    public JLabel getLblFornecedor() {
        if (lblFornecedor == null) {
            lblFornecedor = new JLabel("Fornecedor:");
        }
        return lblFornecedor;
    }
}
