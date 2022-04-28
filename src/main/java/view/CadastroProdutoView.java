package view;

import controller.CadastroProdutoController;
import model.bo.CaracteristicaProduto;
import personalizado.*;
import util.Cores;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Cursor.getDefaultCursor;
import static java.awt.event.KeyEvent.*;
import static javax.swing.JComponent.WHEN_FOCUSED;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import static javax.swing.KeyStroke.getKeyStroke;
import static util.Formatador.formataDouble;

public class CadastroProdutoView extends ControllerView {

    private JPanel panelCodigo;
    private JPanel panelDescricaoPreco;
    private JPanel panelTipoTamanhoMarca;
    private JPanel panelBotoesProduto;
    private JPanel panelCaracteristica;
    private JPanel panelBotoesCaracteristica;

    private JScrollPane scrollTabela;
    private JTable tabelaCaracteristicas;
    private AbstractTableModel grid;

    private JLabel lblCodigo;
    private JLabel lblDescricao;
    private JLabel lblPreco;
    private JLabel lblMarca;
    private JLabel lblTipo;
    private JLabel lblTamanho;
    private JLabel lblBarra;
    private JLabel lblEstoque;
    private JLabel lblTamanhoCaracteristica;
    private JLabel lblCor;

    private JTextFieldSomenteNumeros txtCodigo;
    private JTextFieldLimitador txtDescricao;
    private JTextFieldMoeda txtPreco;
    private JComboBox<String> comboMarca;
    private JComboBox<String> comboTipo;
    private JComboBox<String> comboTamanho;
    private JComboBox<String> comboCor;
    private JTextFieldLimitador txtBarra;
    private JTextFieldLimitador txtTamanho;
    private JTextFieldPorcentagem txtEstoque;

    private JButton btnSalvarProduto;
    private JButton btnSair;
    private JButton btnLimpar;
    private JButton btnListar;
    private JButton btnCaracteristica;
    private JButton btnSalvarCaracteristica;
    private JButton btnEditarProduto;
    private JButton btnNovoProduto;
    private JButton btnSairCaracteristica;

    private CadastroProdutoController controller;

    public CadastroProdutoView(CadastroProdutoController controller) {
        setTitle("Cadastro de Produtos");
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        setSize(new Dimension(710, 450));
        setPreferredSize(new Dimension(710, 450));
        add(getPanelCodigo(), getGbcPanelCodigo());
        add(getPanelDescricaoPreco(), getGbcPanelDescricaoPreco());
        add(getPanelTipoTamanhoMarca(), getGbcPanelTipoTamanhoMarca());
        add(getPanelBotoesProduto(), getGbcPanelBotoesProduto());
        add(getScrollTabela(), getGbcPanelGrid());
        add(getPanelCaracteristica(), getGbcPanelCaracteristica());
        add(getPanelBotoesCaracteristica(), getGbcPanelBotoesCaracteristica());
    }

    private JPanel getPanelCodigo() {
        if (panelCodigo == null) {
            panelCodigo = new JPanel();

            GridBagLayout gblPanelCodigo = new GridBagLayout();
            gblPanelCodigo.columnWidths = new int[]{100, 590};
            gblPanelCodigo.rowHeights = new int[]{0, 0};
            gblPanelCodigo.columnWeights = new double[]{0.0, 0.0};
            gblPanelCodigo.rowWeights = new double[]{0.0, 0.0};
            panelCodigo.setLayout(gblPanelCodigo);

            GridBagConstraints gbcLblCodigo = new GridBagConstraints();
            gbcLblCodigo.fill = GridBagConstraints.BOTH;
            gbcLblCodigo.insets = new Insets(5, 5, 5, 5);
            gbcLblCodigo.gridx = 0;
            gbcLblCodigo.gridy = 0;
            panelCodigo.add(getLblCodigo(), gbcLblCodigo);

            GridBagConstraints gbcTxtCodigo = new GridBagConstraints();
            gbcTxtCodigo.fill = GridBagConstraints.BOTH;
            gbcTxtCodigo.insets = new Insets(0, 5, 5, 5);
            gbcTxtCodigo.gridx = 0;
            gbcTxtCodigo.gridy = 1;
            panelCodigo.add(getTxtCodigo(), gbcTxtCodigo);
        }
        return panelCodigo;
    }

    private JPanel getPanelDescricaoPreco() {
        if (panelDescricaoPreco == null) {
            panelDescricaoPreco = new JPanel();

            GridBagLayout gblPanelDescricao = new GridBagLayout();
            gblPanelDescricao.columnWidths = new int[]{460, 230};
            gblPanelDescricao.rowHeights = new int[]{0, 0};
            gblPanelDescricao.columnWeights = new double[]{0.0, 0.0};
            gblPanelDescricao.rowWeights = new double[]{0.0, 0.0};
            panelDescricaoPreco.setLayout(gblPanelDescricao);

            GridBagConstraints gbcLblDescricao = new GridBagConstraints();
            gbcLblDescricao.fill = GridBagConstraints.BOTH;
            gbcLblDescricao.insets = new Insets(5, 5, 5, 5);
            gbcLblDescricao.gridx = 0;
            gbcLblDescricao.gridy = 0;
            panelDescricaoPreco.add(getLblDescricao(), gbcLblDescricao);

            GridBagConstraints gbcTxtDescricao = new GridBagConstraints();
            gbcTxtDescricao.fill = GridBagConstraints.BOTH;
            gbcTxtDescricao.insets = new Insets(0, 5, 5, 5);
            gbcTxtDescricao.gridx = 0;
            gbcTxtDescricao.gridy = 1;
            panelDescricaoPreco.add(getTxtDescricao(), gbcTxtDescricao);

            GridBagConstraints gbcLblPreco = new GridBagConstraints();
            gbcLblPreco.fill = GridBagConstraints.BOTH;
            gbcLblPreco.insets = new Insets(5, 5, 5, 5);
            gbcLblPreco.gridx = 1;
            gbcLblPreco.gridy = 0;
            panelDescricaoPreco.add(getLblPreco(), gbcLblPreco);

            GridBagConstraints gbcTxtPreco = new GridBagConstraints();
            gbcTxtPreco.fill = GridBagConstraints.BOTH;
            gbcTxtPreco.insets = new Insets(0, 5, 5, 5);
            gbcTxtPreco.gridx = 1;
            gbcTxtPreco.gridy = 1;
            panelDescricaoPreco.add(getTxtPreco(), gbcTxtPreco);
        }
        return panelDescricaoPreco;
    }

    private JPanel getPanelTipoTamanhoMarca() {
        if (panelTipoTamanhoMarca == null) {
            panelTipoTamanhoMarca = new JPanel();

            GridBagLayout gblPanelTipoTamanhoMarca = new GridBagLayout();
            gblPanelTipoTamanhoMarca.columnWidths = new int[]{230, 230, 230};
            gblPanelTipoTamanhoMarca.rowHeights = new int[]{0, 0};
            gblPanelTipoTamanhoMarca.columnWeights = new double[]{0.0, 0.0, 0.0};
            gblPanelTipoTamanhoMarca.rowWeights = new double[]{0.0, 0.0};
            panelTipoTamanhoMarca.setLayout(gblPanelTipoTamanhoMarca);

            GridBagConstraints gbcLblTipo = new GridBagConstraints();
            gbcLblTipo.fill = GridBagConstraints.BOTH;
            gbcLblTipo.insets = new Insets(5, 5, 5, 5);
            gbcLblTipo.gridx = 0;
            gbcLblTipo.gridy = 0;
            panelTipoTamanhoMarca.add(getLblTipo(), gbcLblTipo);

            GridBagConstraints gbcComboTipo = new GridBagConstraints();
            gbcComboTipo.fill = GridBagConstraints.BOTH;
            gbcComboTipo.insets = new Insets(0, 5, 5, 5);
            gbcComboTipo.gridx = 0;
            gbcComboTipo.gridy = 1;
            panelTipoTamanhoMarca.add(getComboTipo(), gbcComboTipo);

            GridBagConstraints gbcLblTamanho = new GridBagConstraints();
            gbcLblTamanho.fill = GridBagConstraints.BOTH;
            gbcLblTamanho.insets = new Insets(5, 5, 5, 5);
            gbcLblTamanho.gridx = 1;
            gbcLblTamanho.gridy = 0;
            panelTipoTamanhoMarca.add(getLblTamanho(), gbcLblTamanho);

            GridBagConstraints gbcComboTamanho = new GridBagConstraints();
            gbcComboTamanho.fill = GridBagConstraints.BOTH;
            gbcComboTamanho.insets = new Insets(0, 5, 5, 5);
            gbcComboTamanho.gridx = 1;
            gbcComboTamanho.gridy = 1;
            panelTipoTamanhoMarca.add(getComboTamanho(), gbcComboTamanho);

            GridBagConstraints gbcLblMarca = new GridBagConstraints();
            gbcLblMarca.fill = GridBagConstraints.BOTH;
            gbcLblMarca.insets = new Insets(5, 5, 5, 5);
            gbcLblMarca.gridx = 2;
            gbcLblMarca.gridy = 0;
            panelTipoTamanhoMarca.add(getLblMarca(), gbcLblMarca);

            GridBagConstraints gbcComboMarca = new GridBagConstraints();
            gbcComboMarca.fill = GridBagConstraints.BOTH;
            gbcComboMarca.insets = new Insets(0, 5, 5, 5);
            gbcComboMarca.gridx = 2;
            gbcComboMarca.gridy = 1;
            panelTipoTamanhoMarca.add(getComboMarca(), gbcComboMarca);
        }
        return panelTipoTamanhoMarca;
    }

    public JPanel getPanelBotoesProduto() {
        if (panelBotoesProduto == null) {
            panelBotoesProduto = new JPanel();

            GridBagLayout gblPanelBotoes = new GridBagLayout();
            gblPanelBotoes.columnWidths = new int[]{0, 0, 0, 0, 0};
            gblPanelBotoes.rowHeights = new int[]{0};
            gblPanelBotoes.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
            gblPanelBotoes.rowWeights = new double[]{0.0};
            panelBotoesProduto.setLayout(gblPanelBotoes);

            GridBagConstraints gbcBtnSalvarProduto = new GridBagConstraints();
            gbcBtnSalvarProduto.insets = new Insets(10, 5, 5, 5);
            gbcBtnSalvarProduto.gridx = 0;
            gbcBtnSalvarProduto.gridy = 0;
            panelBotoesProduto.add(getBtnSalvarProduto(), gbcBtnSalvarProduto);

            GridBagConstraints gbcBtnListar = new GridBagConstraints();
            gbcBtnListar.insets = new Insets(10, 5, 5, 5);
            gbcBtnListar.gridx = 1;
            gbcBtnListar.gridy = 0;
            panelBotoesProduto.add(getBtnListar(), gbcBtnListar);

            GridBagConstraints gbcBtnLimpar = new GridBagConstraints();
            gbcBtnLimpar.insets = new Insets(10, 5, 5, 5);
            gbcBtnLimpar.gridx = 2;
            gbcBtnLimpar.gridy = 0;
            panelBotoesProduto.add(getBtnLimpar(), gbcBtnLimpar);

            GridBagConstraints gbcBtnCaracteristica = new GridBagConstraints();
            gbcBtnCaracteristica.fill = GridBagConstraints.BOTH;
            gbcBtnCaracteristica.insets = new Insets(10, 5, 5, 5);
            gbcBtnCaracteristica.gridx = 3;
            gbcBtnCaracteristica.gridy = 0;
            panelBotoesProduto.add(getBtnCaracteristica(), gbcBtnCaracteristica);

            GridBagConstraints gbcBtnSair = new GridBagConstraints();
            gbcBtnSair.insets = new Insets(10, 5, 5, 5);
            gbcBtnSair.gridx = 4;
            gbcBtnSair.gridy = 0;
            panelBotoesProduto.add(getBtnSair(), gbcBtnSair);
        }
        return panelBotoesProduto;
    }

    public JPanel getPanelCaracteristica() {
        if (panelCaracteristica == null) {
            panelCaracteristica = new JPanel();
            panelCaracteristica.setVisible(false);

            GridBagLayout gblPanelCaracteristica = new GridBagLayout();
            gblPanelCaracteristica.columnWidths = new int[]{172, 173, 172, 173};
            gblPanelCaracteristica.rowHeights = new int[]{0, 0};
            gblPanelCaracteristica.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
            gblPanelCaracteristica.rowWeights = new double[]{0.0, 0.0};
            panelCaracteristica.setLayout(gblPanelCaracteristica);

            GridBagConstraints gbcLblBarra = new GridBagConstraints();
            gbcLblBarra.fill = GridBagConstraints.BOTH;
            gbcLblBarra.insets = new Insets(5, 5, 5, 5);
            gbcLblBarra.gridx = 0;
            gbcLblBarra.gridy = 0;
            panelCaracteristica.add(getLblBarra(), gbcLblBarra);

            GridBagConstraints gbcTxtBarra = new GridBagConstraints();
            gbcTxtBarra.fill = GridBagConstraints.BOTH;
            gbcTxtBarra.insets = new Insets(0, 5, 5, 5);
            gbcTxtBarra.gridx = 0;
            gbcTxtBarra.gridy = 1;
            panelCaracteristica.add(getTxtBarra(), gbcTxtBarra);

            GridBagConstraints gbcLblEstoque = new GridBagConstraints();
            gbcLblEstoque.fill = GridBagConstraints.BOTH;
            gbcLblEstoque.insets = new Insets(5, 5, 5, 5);
            gbcLblEstoque.gridx = 1;
            gbcLblEstoque.gridy = 0;
            panelCaracteristica.add(getLblEstoque(), gbcLblEstoque);

            GridBagConstraints gbcTxtEstoque = new GridBagConstraints();
            gbcTxtEstoque.fill = GridBagConstraints.BOTH;
            gbcTxtEstoque.insets = new Insets(0, 5, 5, 5);
            gbcTxtEstoque.gridx = 1;
            gbcTxtEstoque.gridy = 1;
            panelCaracteristica.add(getTxtEstoque(), gbcTxtEstoque);

            GridBagConstraints gbcLblTamanhoCaracteristica = new GridBagConstraints();
            gbcLblTamanhoCaracteristica.fill = GridBagConstraints.BOTH;
            gbcLblTamanhoCaracteristica.insets = new Insets(5, 5, 5, 5);
            gbcLblTamanhoCaracteristica.gridx = 2;
            gbcLblTamanhoCaracteristica.gridy = 0;
            panelCaracteristica.add(getLblTamanhoCaracteristica(), gbcLblTamanhoCaracteristica);

            GridBagConstraints gbcTxtTamanho = new GridBagConstraints();
            gbcTxtTamanho.fill = GridBagConstraints.BOTH;
            gbcTxtTamanho.insets = new Insets(0, 5, 5, 5);
            gbcTxtTamanho.gridx = 2;
            gbcTxtTamanho.gridy = 1;
            panelCaracteristica.add(getTxtTamanho(), gbcTxtTamanho);

            GridBagConstraints gbcLblCor = new GridBagConstraints();
            gbcLblCor.fill = GridBagConstraints.BOTH;
            gbcLblCor.insets = new Insets(5, 5, 5, 5);
            gbcLblCor.gridx = 3;
            gbcLblCor.gridy = 0;
            panelCaracteristica.add(getLblCor(), gbcLblCor);

            GridBagConstraints gbcComboCor = new GridBagConstraints();
            gbcComboCor.fill = GridBagConstraints.BOTH;
            gbcComboCor.insets = new Insets(0, 5, 5, 5);
            gbcComboCor.gridx = 3;
            gbcComboCor.gridy = 1;
            panelCaracteristica.add(getComboCor(), gbcComboCor);
        }
        return panelCaracteristica;
    }

    public JScrollPane getScrollTabela() {
        if (scrollTabela == null) {
            scrollTabela = new JScrollPane();
            scrollTabela.setViewportView(getTabelaCaracteristicas());
        }
        return scrollTabela;
    }

    public JPanel getPanelBotoesCaracteristica() {
        if (panelBotoesCaracteristica == null) {
            panelBotoesCaracteristica = new JPanel();
            panelBotoesCaracteristica.setVisible(false);

            GridBagLayout gblPanelBotoes = new GridBagLayout();
            gblPanelBotoes.columnWidths = new int[]{0, 0, 0, 0};
            gblPanelBotoes.rowHeights = new int[]{0};
            gblPanelBotoes.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
            gblPanelBotoes.rowWeights = new double[]{0.0};
            panelBotoesCaracteristica.setLayout(gblPanelBotoes);

            GridBagConstraints gbcBtnSalvarCaracteristica = new GridBagConstraints();
            gbcBtnSalvarCaracteristica.fill = GridBagConstraints.BOTH;
            gbcBtnSalvarCaracteristica.insets = new Insets(10, 5, 5, 5);
            gbcBtnSalvarCaracteristica.gridx = 0;
            gbcBtnSalvarCaracteristica.gridy = 0;
            panelBotoesCaracteristica.add(getBtnSalvarCaracteristica(), gbcBtnSalvarCaracteristica);

            GridBagConstraints gbcBtnEditarProduto = new GridBagConstraints();
            gbcBtnEditarProduto.fill = GridBagConstraints.BOTH;
            gbcBtnEditarProduto.insets = new Insets(10, 5, 5, 5);
            gbcBtnEditarProduto.gridx = 1;
            gbcBtnEditarProduto.gridy = 0;
            panelBotoesCaracteristica.add(getBtnEditarProduto(), gbcBtnEditarProduto);

            GridBagConstraints gbcBtnNovoProduto = new GridBagConstraints();
            gbcBtnNovoProduto.fill = GridBagConstraints.BOTH;
            gbcBtnNovoProduto.insets = new Insets(10, 5, 5, 5);
            gbcBtnNovoProduto.gridx = 2;
            gbcBtnNovoProduto.gridy = 0;
            panelBotoesCaracteristica.add(getBtnNovoProduto(), gbcBtnNovoProduto);

            GridBagConstraints gbcBtnSairCaracteristica = new GridBagConstraints();
            gbcBtnSairCaracteristica.fill = GridBagConstraints.BOTH;
            gbcBtnSairCaracteristica.insets = new Insets(10, 5, 5, 5);
            gbcBtnSairCaracteristica.gridx = 3;
            gbcBtnSairCaracteristica.gridy = 0;
            panelBotoesCaracteristica.add(getBtnSairCaracteristica(), gbcBtnSairCaracteristica);
        }
        return panelBotoesCaracteristica;
    }

    public JTable getTabelaCaracteristicas() {
        if (tabelaCaracteristicas == null) {
            tabelaCaracteristicas = new JTable();

            GridBagLayout gblPanelGrid = new GridBagLayout();
            gblPanelGrid.columnWidths = new int[]{700};
            gblPanelGrid.rowHeights = new int[]{0};
            gblPanelGrid.columnWeights = new double[]{0.0};
            gblPanelGrid.rowWeights = new double[]{0.0};
            tabelaCaracteristicas.setLayout(gblPanelGrid);
            tabelaCaracteristicas.setModel(getGrid());
            tabelaCaracteristicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
            dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);

            TableColumnModel columnModel = tabelaCaracteristicas.getColumnModel();
            columnModel.getColumn(0).setCellRenderer(dtcrRight);
            columnModel.getColumn(1).setCellRenderer(dtcrRight);
            columnModel.getColumn(0).setPreferredWidth(150);
            columnModel.getColumn(1).setPreferredWidth(160);
            columnModel.getColumn(2).setPreferredWidth(200);
            columnModel.getColumn(3).setPreferredWidth(150);

            DefaultTableCellRenderer dtcrEditar = new DefaultTableCellRenderer();
            dtcrEditar.setIcon(new ImageIcon(getClass().getResource("/imagens/editar.png")));
            columnModel.getColumn(4).setCellRenderer(dtcrEditar);
            columnModel.getColumn(4).setMaxWidth(20);

            DefaultTableCellRenderer dtcrExcluir = new DefaultTableCellRenderer();
            dtcrExcluir.setIcon(new ImageIcon(getClass().getResource("/imagens/remover.png")));
            columnModel.getColumn(5).setCellRenderer(dtcrExcluir);
            columnModel.getColumn(5).setMaxWidth(20);

            tabelaCaracteristicas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    int colunaSelecionada = ((JTable) e.getSource()).getSelectedColumn();

                    if (colunaSelecionada == 4) {
                        controller.editaCaracteristica();
                    } else if (colunaSelecionada == 5) {
                        controller.excluiCaracteristica();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(getDefaultCursor());
                }
            });

            tabelaCaracteristicas.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    setCursor(isIconeExcluirEditarSelecionado(e) ? new Cursor(HAND_CURSOR) : getDefaultCursor());
                }
            });
        }
        return tabelaCaracteristicas;
    }

    private boolean isIconeExcluirEditarSelecionado(MouseEvent e) {
        int colunaSelecionada = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
        return colunaSelecionada == 4 || colunaSelecionada == 5;
    }

    public AbstractTableModel getGrid() {
        if (grid == null) {

            grid = new AbstractTableModel() {
                public final String[] columnNames = {"Código Barra", "Estoque", "Tamanho", "Cor", "", ""};

                public int getColumnCount() {
                    return columnNames.length;
                }

                public String getColumnName(int column) {
                    return columnNames[column];
                }

                public int getRowCount() {
                    return controller.getCaracteristicas().size();
                }

                public Object getValueAt(int row, int column) {
                    CaracteristicaProduto caracteristica = controller.getCaracteristicas().get(row);

                    switch (column) {
                        case 0:
                            return caracteristica.getCodBarras();
                        case 1:
                            return formataDouble(caracteristica.getQtdEstoque());
                        case 2:
                            return caracteristica.getNumTamanho();
                        case 3:
                            return caracteristica.getCor().getNomCor();
                        default:
                            return "";
                    }
                }
            };
        }
        return grid;
    }

    public void fireTableDataChanged() {
        getGrid().fireTableDataChanged();
    }

    private GridBagConstraints getGbcPanelCodigo() {
        final GridBagConstraints gbcPanelCodigo = new GridBagConstraints();
        gbcPanelCodigo.insets = new Insets(0, 5, 0, 5);
        gbcPanelCodigo.fill = GridBagConstraints.BOTH;
        gbcPanelCodigo.gridx = 0;
        gbcPanelCodigo.gridy = 0;

        return gbcPanelCodigo;
    }

    private GridBagConstraints getGbcPanelDescricaoPreco() {
        final GridBagConstraints gbcPanelDescricaoPreco = new GridBagConstraints();
        gbcPanelDescricaoPreco.insets = new Insets(0, 5, 0, 5);
        gbcPanelDescricaoPreco.fill = GridBagConstraints.BOTH;
        gbcPanelDescricaoPreco.gridx = 0;
        gbcPanelDescricaoPreco.gridy = 1;

        return gbcPanelDescricaoPreco;
    }

    private GridBagConstraints getGbcPanelTipoTamanhoMarca() {
        final GridBagConstraints gbcPanelTipo = new GridBagConstraints();
        gbcPanelTipo.insets = new Insets(0, 5, 0, 5);
        gbcPanelTipo.fill = GridBagConstraints.BOTH;
        gbcPanelTipo.gridx = 0;
        gbcPanelTipo.gridy = 2;

        return gbcPanelTipo;
    }

    private GridBagConstraints getGbcPanelBotoesProduto() {
        final GridBagConstraints gbcPanelBotoesProduto = new GridBagConstraints();
        gbcPanelBotoesProduto.fill = GridBagConstraints.BOTH;
        gbcPanelBotoesProduto.gridx = 0;
        gbcPanelBotoesProduto.gridy = 3;

        return gbcPanelBotoesProduto;
    }

    private GridBagConstraints getGbcPanelGrid() {
        final GridBagConstraints gbcPanelGrid = new GridBagConstraints();
        gbcPanelGrid.fill = GridBagConstraints.BOTH;
        gbcPanelGrid.gridx = 0;
        gbcPanelGrid.gridy = 4;
        gbcPanelGrid.weightx = 100.0;
        gbcPanelGrid.weighty = 50.0;
        gbcPanelGrid.gridwidth = 4;
        gbcPanelGrid.insets = new Insets(10, 10, 5, 10);

        return gbcPanelGrid;
    }

    private GridBagConstraints getGbcPanelCaracteristica() {
        final GridBagConstraints gbcPanelCaracteristica = new GridBagConstraints();
        gbcPanelCaracteristica.fill = GridBagConstraints.BOTH;
        gbcPanelCaracteristica.gridx = 0;
        gbcPanelCaracteristica.gridy = 5;

        return gbcPanelCaracteristica;
    }

    private GridBagConstraints getGbcPanelBotoesCaracteristica() {
        final GridBagConstraints gbcPanelBotoesCaracteristica = new GridBagConstraints();
        gbcPanelBotoesCaracteristica.fill = GridBagConstraints.BOTH;
        gbcPanelBotoesCaracteristica.gridx = 0;
        gbcPanelBotoesCaracteristica.gridy = 6;

        return gbcPanelBotoesCaracteristica;
    }

    public JTextField getTxtCodigo() {
        if (txtCodigo == null) {
            txtCodigo = new JTextFieldSomenteNumeros();
            txtCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
            txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
            txtCodigo.addActionListener(a -> controller.buscaProduto());
        }
        return txtCodigo;
    }

    public JTextFieldLimitador getTxtDescricao() {
        if (txtDescricao == null) {
            txtDescricao = new JTextFieldLimitador(100);
            txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtDescricao;
    }

    public JTextFieldMoeda getTxtPreco() {
        if (txtPreco == null) {
            txtPreco = new JTextFieldMoeda();
            txtPreco.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtPreco;
    }

    public JComboBox<String> getComboMarca() {
        if (comboMarca == null) {
            comboMarca = new JComboBox();
            comboMarca.setFont(new Font("Tahoma", Font.PLAIN, 11));
            comboMarca.setBackground(Cores.WHITE);
        }
        return comboMarca;
    }

    public JComboBox<String> getComboTamanho() {
        if (comboTamanho == null) {
            comboTamanho = new JComboBox();
            comboTamanho.setFont(new Font("Tahoma", Font.PLAIN, 11));
            comboTamanho.setBackground(Cores.WHITE);
        }
        return comboTamanho;
    }

    public JComboBox<String> getComboTipo() {
        if (comboTipo == null) {
            comboTipo = new JComboBox();
            comboTipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
            comboTipo.setBackground(Cores.WHITE);
        }
        return comboTipo;
    }

    public JComboBox<String> getComboCor() {
        if (comboCor == null) {
            comboCor = new JComboBox();
            comboCor.setFont(new Font("Tahoma", Font.PLAIN, 11));
            comboCor.setBackground(Cores.WHITE);
        }
        return comboCor;
    }

    public JTextFieldLimitador getTxtTamanho() {
        if (txtTamanho == null) {
            txtTamanho = new JTextFieldLimitador(3);
            txtTamanho.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtTamanho;
    }

    public JTextFieldPorcentagem getTxtEstoque() {
        if (txtEstoque == null) {
            txtEstoque = new JTextFieldPorcentagem();
            txtEstoque.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtEstoque;
    }

    public JTextFieldLimitador getTxtBarra() {
        if (txtBarra == null) {
            txtBarra = new JTextFieldLimitador(13);
            txtBarra.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtBarra;
    }

    public JLabel getLblCodigo() {
        if (lblCodigo == null) {
            lblCodigo = new JLabel("Código:");
        }
        return lblCodigo;
    }

    public JLabel getLblDescricao() {
        if (lblDescricao == null) {
            lblDescricao = new JLabel("Descrição:*");
        }
        return lblDescricao;
    }

    public JLabel getLblPreco() {
        if (lblPreco == null) {
            lblPreco = new JLabel("Preço:*");
        }
        return lblPreco;
    }

    public JLabel getLblMarca() {
        if (lblMarca == null) {
            lblMarca = new JLabel("Marca:*");
        }
        return lblMarca;
    }

    public JLabel getLblTipo() {
        if (lblTipo == null) {
            lblTipo = new JLabel("Tipo:*");
        }
        return lblTipo;
    }

    public JLabel getLblTamanho() {
        if (lblTamanho == null) {
            lblTamanho = new JLabel("Tamanho:*");
        }
        return lblTamanho;
    }

    public JLabel getLblBarra() {
        if (lblBarra == null) {
            lblBarra = new JLabel("Código de Barras:*");
        }
        return lblBarra;
    }

    public JLabel getLblEstoque() {
        if (lblEstoque == null) {
            lblEstoque = new JLabel("Qtd. Estoque:*");
        }
        return lblEstoque;
    }

    public JLabel getLblTamanhoCaracteristica() {
        if (lblTamanhoCaracteristica == null) {
            lblTamanhoCaracteristica = new JLabel("Tamanho:*");
        }
        return lblTamanhoCaracteristica;
    }

    public JLabel getLblCor() {
        if (lblCor == null) {
            lblCor = new JLabel("Cor:*");
        }
        return lblCor;
    }

    public JButton getBtnSalvarProduto() {
        if (btnSalvarProduto == null) {
            btnSalvarProduto = getBotaoSalvarPadrao(controller);
        }
        return btnSalvarProduto;
    }

    public JButton getBtnListar() {
        if (btnListar == null) {
            btnListar = getBotaoListarPadrao(controller);
        }
        return btnListar;
    }

    public JButton getBtnLimpar() {
        if (btnLimpar == null) {
            btnLimpar = getBotaoLimparPadrao(controller);
        }
        return btnLimpar;
    }

    public JButton getBtnCaracteristica() {
        if (btnCaracteristica == null) {
            btnCaracteristica = new JButton("Add Característica [F4]");
            btnCaracteristica.setIcon(new ImageIcon(getClass().getResource("/imagens/product.png")));
            btnCaracteristica.addActionListener(a -> controller.adicionaCaracteristica());
            btnCaracteristica.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F4, 0), EVENTO);
            btnCaracteristica.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnCaracteristica.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.adicionaCaracteristica();
                }
            });
        }
        return btnCaracteristica;
    }

    public JButton getBtnSalvarCaracteristica() {
        if (btnSalvarCaracteristica == null) {
            btnSalvarCaracteristica = new JButton("Salvar [F5]");
            btnSalvarCaracteristica.setIcon(new ImageIcon(getClass().getResource("/imagens/save.png")));
            btnSalvarCaracteristica.addActionListener(a -> controller.salvaCaracteristica());
            btnSalvarCaracteristica.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F5, 0), EVENTO);
            btnSalvarCaracteristica.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnSalvarCaracteristica.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.salvaCaracteristica();
                }
            });
        }
        return btnSalvarCaracteristica;
    }

    public JButton getBtnEditarProduto() {
        if (btnEditarProduto == null) {
            btnEditarProduto = new JButton("Editar Produto [F6]");
            btnEditarProduto.setIcon(new ImageIcon(getClass().getResource("/imagens/product.png")));
            btnEditarProduto.addActionListener(a -> controller.editaProduto());
            btnEditarProduto.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F6, 0), EVENTO);
            btnEditarProduto.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnEditarProduto.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.editaProduto();
                }
            });
        }
        return btnEditarProduto;
    }

    public JButton getBtnNovoProduto() {
        if (btnNovoProduto == null) {
            btnNovoProduto = new JButton("Novo Produto [F7]");
            btnNovoProduto.setIcon(new ImageIcon(getClass().getResource("/imagens/product.png")));
            btnNovoProduto.addActionListener(a -> controller.novoProduto());
            btnNovoProduto.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F7, 0), EVENTO);
            btnNovoProduto.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnNovoProduto.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.novoProduto();
                }
            });
        }
        return btnNovoProduto;
    }

    public JButton getBtnSair() {
        if (btnSair == null) {
            btnSair = getBotaoSairPadrao();
        }
        return btnSair;
    }

    public JButton getBtnSairCaracteristica() {
        if (btnSairCaracteristica == null) {
            btnSairCaracteristica = getBotaoSairPadrao();
        }
        return btnSairCaracteristica;
    }
}
