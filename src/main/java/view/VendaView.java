package view;

import controller.VendaController;
import model.bo.ItemVenda;
import personalizado.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.Cursor.getDefaultCursor;
import static java.awt.event.KeyEvent.*;
import static javax.swing.JComponent.WHEN_FOCUSED;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import static javax.swing.KeyStroke.getKeyStroke;
import static util.Cores.*;
import static util.Formatador.*;

public class VendaView extends ControllerView {

    private static final Font FONTE_LABEL = new Font("Tahoma", Font.BOLD, 12);
    private static final Font FONTE_CAMPO = new Font("Tahoma", Font.PLAIN, 22);
    private static final Font FONTE_INFORMATIVO = new Font("Tahoma", Font.PLAIN, 14);
    public static final String VALOR_ZERO = "0,00";

    private Integer larguraTela;

    private JPanel panelProdutoImagem;
    private JPanel panelProduto;
    private JPanel panelImagem;
    private JPanel panelLeitura;
    private JPanel panelInfoProduto;
    private JPanel panelNomeProduto;
    private JPanel panelTotalizadores;
    private JPanel panelInformativo;
    private JPanel panelUsuario;
    private JPanel panelDataHora;
    private JPanel panelPropaganda;
    private JPanel panelTotalBruto;
    private JPanel panelTotalLiquido;
    private JPanel panelBotoes;

    private JScrollPane scrollTabela;
    private JTable tabelaProdutos;
    private AbstractTableModel grid;

    private JLabel lblLeitura;
    private JLabel lblQuantidade;
    private JLabel lblEstoque;
    private JLabel lblPrcDesconto;
    private JLabel lblVlrUnitario;
    private JLabel lblCodigoBarras;
    private JLabel lblNomeProduto;
    private JLabel lblImagem;
    private JLabel lblPropaganda;
    private JLabel lblTotalLiquido;
    private JLabel totalVenda;
    private JLabel lblTotalBruto;
    private JLabel totalBruto;
    private JLabel lblData;
    private JLabel lblHora;
    private JLabel lblUsuario;

    private JTextField txtLeitura;
    private JTextField txtEstoque;
    private JTextFieldSomenteNumeros txtQuantidade;
    private JTextFieldPorcentagem txtPrcDesconto;
    private JTextField txtVlrUnitario;
    private JTextField txtCodigoBarras;
    private JTextField txtData;
    private JTextField txtHora;
    private JTextField txtUsuario;

    private JButton btnFinalizar;
    private JButton btnEditaItem;
    private JButton btnExcluiItem;
    private JButton btnSelecionaItem;
    private JButton btnIncluiItem;
    private JButton btnExcluiVenda;
    private JButton btnSair;

    private VendaController controller;

    public VendaView(VendaController controller) {
        setTitle("Venda");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        larguraTela = dimension.width - 200;
        setSize(new Dimension(dimension.width, dimension.height - 40));
        setPreferredSize(new Dimension(dimension.width, dimension.height - 40));
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        setLayout(new GridBagLayout());
        add(getPanelProdutoImagem(), getGbcPanelProdutoImagem());
        add(getPanelNomeProduto(), getGbcPanelNomeProduto());
        add(getScrollTabela(), getGbcPanelGrid());
        add(getPanelTotalizadores(), getGbcPanelTotalizadores());
        add(getPanelBotoes(), getGbcPanelBotoes());
    }

    private JPanel getPanelProdutoImagem() {
        if (panelProdutoImagem == null) {
            panelProdutoImagem = new JPanel();

            GridBagLayout gblPanelProduto = new GridBagLayout();
            gblPanelProduto.columnWidths = new int[]{larguraTela, 0};
            gblPanelProduto.rowHeights = new int[]{0};
            gblPanelProduto.columnWeights = new double[]{0.0, 0.0};
            gblPanelProduto.rowWeights = new double[]{0.0};
            panelProdutoImagem.setLayout(gblPanelProduto);

            GridBagConstraints gbcProduto = new GridBagConstraints();
            gbcProduto.fill = GridBagConstraints.BOTH;
            gbcProduto.gridx = 0;
            gbcProduto.gridy = 0;
            gbcProduto.weightx = 1.0;
            panelProdutoImagem.add(getPanelProduto(), gbcProduto);

            GridBagConstraints gbcImagem = new GridBagConstraints();
            gbcImagem.insets = new Insets(0, 0, 0, 5);
            gbcImagem.fill = GridBagConstraints.BOTH;
            gbcImagem.gridx = 1;
            gbcImagem.gridy = 0;
            panelProdutoImagem.add(getPanelImagem(), gbcImagem);
        }
        return panelProdutoImagem;
    }

    private JPanel getPanelImagem() {
        if (panelImagem == null) {
            panelImagem = new JPanel();
            panelImagem.setLayout(new GridBagLayout());

            GridBagConstraints gbcLblImagem = new GridBagConstraints();
            gbcLblImagem.insets = new Insets(0, 10, 0, 0);
            panelImagem.add(getLblImagem(), gbcLblImagem);
        }
        return panelImagem;
    }

    private JPanel getPanelProduto() {
        if (panelProduto == null) {
            panelProduto = new JPanel();

            GridBagLayout gblPanelProduto = new GridBagLayout();
            gblPanelProduto.columnWidths = new int[]{larguraTela};
            gblPanelProduto.rowHeights = new int[]{0, 0};
            gblPanelProduto.columnWeights = new double[]{0.0};
            gblPanelProduto.rowWeights = new double[]{0.0};
            panelProduto.setLayout(gblPanelProduto);

            GridBagConstraints gbcLeitura = new GridBagConstraints();
            gbcLeitura.fill = GridBagConstraints.BOTH;
            gbcLeitura.gridx = 0;
            gbcLeitura.gridy = 0;
            gbcLeitura.weightx = 1.0;
            panelProduto.add(getPanelLeitura(), gbcLeitura);

            GridBagConstraints gbcInfoProduto = new GridBagConstraints();
            gbcInfoProduto.fill = GridBagConstraints.BOTH;
            gbcInfoProduto.gridx = 0;
            gbcInfoProduto.gridy = 1;
            gbcInfoProduto.weightx = 1.0;
            panelProduto.add(getPanelInfoProduto(), gbcInfoProduto);
        }
        return panelProduto;
    }

    private JPanel getPanelLeitura() {
        if (panelLeitura == null) {
            panelLeitura = new JPanel();

            GridBagLayout gblPanelLeitura = new GridBagLayout();
            gblPanelLeitura.columnWidths = new int[]{larguraTela};
            gblPanelLeitura.rowHeights = new int[]{0, 0};
            gblPanelLeitura.columnWeights = new double[]{0.0};
            gblPanelLeitura.rowWeights = new double[]{0.0, 0.0};
            panelLeitura.setLayout(gblPanelLeitura);

            GridBagConstraints gbcLblLeitura = new GridBagConstraints();
            gbcLblLeitura.fill = GridBagConstraints.HORIZONTAL;
            gbcLblLeitura.insets = new Insets(0, 5, 5, 5);
            gbcLblLeitura.gridx = 0;
            gbcLblLeitura.gridy = 0;
            panelLeitura.add(getLblLeitura(), gbcLblLeitura);

            GridBagConstraints gbcTxtLeitura = new GridBagConstraints();
            gbcTxtLeitura.fill = GridBagConstraints.BOTH;
            gbcTxtLeitura.insets = new Insets(0, 5, 5, 5);
            gbcTxtLeitura.gridx = 0;
            gbcTxtLeitura.gridy = 1;
            panelLeitura.add(getTxtLeitura(), gbcTxtLeitura);
        }
        return panelLeitura;
    }

    private JPanel getPanelInfoProduto() {
        if (panelInfoProduto == null) {
            panelInfoProduto = new JPanel();

            Integer tamanho = larguraTela / 5;

            GridBagLayout gblPanelInfoProduto = new GridBagLayout();
            gblPanelInfoProduto.columnWidths = new int[]{tamanho, tamanho, tamanho, tamanho, tamanho};
            gblPanelInfoProduto.rowHeights = new int[]{0, 0};
            gblPanelInfoProduto.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
            gblPanelInfoProduto.rowWeights = new double[]{0.0, 0.0};
            panelInfoProduto.setLayout(gblPanelInfoProduto);

            GridBagConstraints gbcLblQuantidade = new GridBagConstraints();
            gbcLblQuantidade.anchor = GridBagConstraints.WEST;
            gbcLblQuantidade.insets = new Insets(0, 5, 5, 5);
            gbcLblQuantidade.gridx = 0;
            gbcLblQuantidade.gridy = 0;
            panelInfoProduto.add(getLblQuantidade(), gbcLblQuantidade);

            GridBagConstraints gbcTxtQuantidade = new GridBagConstraints();
            gbcTxtQuantidade.fill = GridBagConstraints.BOTH;
            gbcTxtQuantidade.insets = new Insets(0, 5, 5, 5);
            gbcTxtQuantidade.gridx = 0;
            gbcTxtQuantidade.gridy = 1;
            panelInfoProduto.add(getTxtQuantidade(), gbcTxtQuantidade);

            GridBagConstraints gbcLblPrcDesconto = new GridBagConstraints();
            gbcLblPrcDesconto.anchor = GridBagConstraints.WEST;
            gbcLblPrcDesconto.insets = new Insets(0, 5, 5, 5);
            gbcLblPrcDesconto.gridx = 1;
            gbcLblPrcDesconto.gridy = 0;
            panelInfoProduto.add(getLblPrcDesconto(), gbcLblPrcDesconto);

            GridBagConstraints gbcTxtPrcDesconto = new GridBagConstraints();
            gbcTxtPrcDesconto.fill = GridBagConstraints.BOTH;
            gbcTxtPrcDesconto.insets = new Insets(0, 5, 5, 5);
            gbcTxtPrcDesconto.gridx = 1;
            gbcTxtPrcDesconto.gridy = 1;
            panelInfoProduto.add(getTxtPrcDesconto(), gbcTxtPrcDesconto);

            GridBagConstraints gbcLblEstoque = new GridBagConstraints();
            gbcLblEstoque.anchor = GridBagConstraints.WEST;
            gbcLblEstoque.insets = new Insets(0, 5, 5, 5);
            gbcLblEstoque.gridx = 2;
            gbcLblEstoque.gridy = 0;
            panelInfoProduto.add(getLblEstoque(), gbcLblEstoque);

            GridBagConstraints gbcTxtEstoque = new GridBagConstraints();
            gbcTxtEstoque.fill = GridBagConstraints.BOTH;
            gbcTxtEstoque.insets = new Insets(0, 5, 5, 5);
            gbcTxtEstoque.gridx = 2;
            gbcTxtEstoque.gridy = 1;
            panelInfoProduto.add(getTxtEstoque(), gbcTxtEstoque);

            GridBagConstraints gbcLblVlrUnitario = new GridBagConstraints();
            gbcLblVlrUnitario.anchor = GridBagConstraints.WEST;
            gbcLblVlrUnitario.insets = new Insets(0, 5, 5, 5);
            gbcLblVlrUnitario.gridx = 3;
            gbcLblVlrUnitario.gridy = 0;
            panelInfoProduto.add(getLblVlrUnitario(), gbcLblVlrUnitario);

            GridBagConstraints gbcTxtVlrUnitario = new GridBagConstraints();
            gbcTxtVlrUnitario.fill = GridBagConstraints.BOTH;
            gbcTxtVlrUnitario.insets = new Insets(0, 5, 5, 5);
            gbcTxtVlrUnitario.gridx = 3;
            gbcTxtVlrUnitario.gridy = 1;
            panelInfoProduto.add(getTxtVlrUnitario(), gbcTxtVlrUnitario);

            GridBagConstraints gbcLblCodigoBarras = new GridBagConstraints();
            gbcLblCodigoBarras.anchor = GridBagConstraints.WEST;
            gbcLblCodigoBarras.insets = new Insets(0, 5, 5, 5);
            gbcLblCodigoBarras.gridx = 4;
            gbcLblCodigoBarras.gridy = 0;
            panelInfoProduto.add(getLblCodigoBarras(), gbcLblCodigoBarras);

            GridBagConstraints gbcTxtCodigoBarras = new GridBagConstraints();
            gbcTxtCodigoBarras.fill = GridBagConstraints.BOTH;
            gbcTxtCodigoBarras.insets = new Insets(0, 5, 5, 5);
            gbcTxtCodigoBarras.gridx = 4;
            gbcTxtCodigoBarras.gridy = 1;
            panelInfoProduto.add(getTxtCodigoBarras(), gbcTxtCodigoBarras);
        }
        return panelInfoProduto;
    }

    public JPanel getPanelNomeProduto() {
        if (panelNomeProduto == null) {
            panelNomeProduto = new JPanel();
            panelNomeProduto.setBackground(Color.LIGHT_GRAY);
            panelNomeProduto.setLayout(new GridBagLayout());
            panelNomeProduto.setBorder(null);

            GridBagConstraints gbcLblNomeProduto = new GridBagConstraints();
            gbcLblNomeProduto.insets = new Insets(0, 0, 0, 0);
            gbcLblNomeProduto.fill = GridBagConstraints.HORIZONTAL;
            gbcLblNomeProduto.weighty = 0.0;
            gbcLblNomeProduto.weightx = 1.0;
            panelNomeProduto.add(getLblNomeProduto(), gbcLblNomeProduto);
        }
        return panelNomeProduto;
    }

    public JPanel getPanelTotalizadores() {
        if (panelTotalizadores == null) {
            panelTotalizadores = new JPanel();

            GridBagLayout gblPanelTodosDados = new GridBagLayout();
            gblPanelTodosDados.rowHeights = new int[]{0, 0, 0, 0, 21, 31};
            gblPanelTodosDados.columnWidths = new int[]{100, 525, 0, 0};
            gblPanelTodosDados.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 0.0};
            gblPanelTodosDados.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
            panelTotalizadores.setLayout(gblPanelTodosDados);
            panelTotalizadores.setBorder(null);

            GridBagConstraints gbcPanelInformativo = new GridBagConstraints();
            gbcPanelInformativo.gridheight = 5;
            gbcPanelInformativo.insets = new Insets(0, 0, 0, 5);
            gbcPanelInformativo.fill = GridBagConstraints.BOTH;
            gbcPanelInformativo.gridx = 0;
            gbcPanelInformativo.gridy = 1;
            gbcPanelInformativo.weightx = 1.0;
            panelTotalizadores.add(getPanelInformativo(), gbcPanelInformativo);

            GridBagConstraints gbcPanelPropaganda = new GridBagConstraints();
            gbcPanelPropaganda.gridheight = 5;
            gbcPanelPropaganda.insets = new Insets(0, 60, 0, 5);
            gbcPanelPropaganda.fill = GridBagConstraints.BOTH;
            gbcPanelPropaganda.gridx = 1;
            gbcPanelPropaganda.gridy = 1;
            panelTotalizadores.add(getPanelPropaganda(), gbcPanelPropaganda);

            GridBagConstraints gbcPanelTotalBruto = new GridBagConstraints();
            gbcPanelTotalBruto.insets = new Insets(2, 2, 0, 5);
            gbcPanelTotalBruto.fill = GridBagConstraints.BOTH;
            gbcPanelTotalBruto.weightx = 0.0;
            gbcPanelTotalBruto.gridx = 2;
            gbcPanelTotalBruto.gridy = 5;
            panelTotalizadores.add(getPanelTotalBruto(), gbcPanelTotalBruto);

            GridBagConstraints gbcPanelTotalLiquido = new GridBagConstraints();
            gbcPanelTotalLiquido.insets = new Insets(0, 2, 0, 0);
            gbcPanelTotalLiquido.fill = GridBagConstraints.BOTH;
            gbcPanelTotalLiquido.gridheight = 5;
            gbcPanelTotalLiquido.weightx = 0.0;
            gbcPanelTotalLiquido.gridx = 3;
            gbcPanelTotalLiquido.gridy = 1;
            panelTotalizadores.add(getPanelTotalLiquido(), gbcPanelTotalLiquido);
        }
        return panelTotalizadores;
    }

    private JPanel getPanelInformativo() {
        if (panelInformativo == null) {
            panelInformativo = new JPanel();
            panelInformativo.setLayout(new GridBagLayout());

            GridBagConstraints gbcPanelUsuario = new GridBagConstraints();
            gbcPanelUsuario.fill = GridBagConstraints.BOTH;
            gbcPanelUsuario.insets = new Insets(0, 5, 5, 5);
            gbcPanelUsuario.gridx = 0;
            gbcPanelUsuario.gridy = 0;
            gbcPanelUsuario.weightx = 1.0;
            panelInformativo.add(getPanelUsuario(), gbcPanelUsuario);

            GridBagConstraints gbcPanelDataHora = new GridBagConstraints();
            gbcPanelDataHora.fill = GridBagConstraints.BOTH;
            gbcPanelDataHora.insets = new Insets(0, 5, 0, 5);
            gbcPanelDataHora.gridx = 0;
            gbcPanelDataHora.gridy = 1;
            gbcPanelDataHora.weightx = 1.0;
            panelInformativo.add(getPanelDataHora(), gbcPanelDataHora);
        }
        return panelInformativo;
    }

    private JPanel getPanelUsuario() {
        if (panelUsuario == null) {
            panelUsuario = new JPanel();

            GridBagLayout gblPanelUsuario = new GridBagLayout();
            gblPanelUsuario.columnWidths = new int[]{0};
            gblPanelUsuario.rowHeights = new int[]{0, 0};
            gblPanelUsuario.columnWeights = new double[]{0.0};
            gblPanelUsuario.rowWeights = new double[]{0.0, 0.0};
            panelUsuario.setLayout(gblPanelUsuario);

            GridBagConstraints gbcLblUsuario = new GridBagConstraints();
            gbcLblUsuario.fill = GridBagConstraints.HORIZONTAL;
            gbcLblUsuario.insets = new Insets(0, 5, 0, 5);
            gbcLblUsuario.gridx = 0;
            gbcLblUsuario.gridy = 0;
            panelUsuario.add(getLblUsuario(), gbcLblUsuario);

            GridBagConstraints gbcTxtUsuario = new GridBagConstraints();
            gbcTxtUsuario.fill = GridBagConstraints.BOTH;
            gbcTxtUsuario.insets = new Insets(0, 5, 0, 5);
            gbcTxtUsuario.gridx = 0;
            gbcTxtUsuario.gridy = 1;
            gbcTxtUsuario.weightx = 1.0;
            panelUsuario.add(getTxtUsuario(), gbcTxtUsuario);
        }
        return panelUsuario;
    }

    private JPanel getPanelDataHora() {
        if (panelDataHora == null) {
            panelDataHora = new JPanel();

            GridBagLayout gblPanelDataHora = new GridBagLayout();
            gblPanelDataHora.columnWidths = new int[]{0, 0};
            gblPanelDataHora.rowHeights = new int[]{0, 0};
            gblPanelDataHora.columnWeights = new double[]{0.0, 0.0};
            gblPanelDataHora.rowWeights = new double[]{0.0, 0.0};
            panelDataHora.setLayout(gblPanelDataHora);

            GridBagConstraints gbcLblData = new GridBagConstraints();
            gbcLblData.fill = GridBagConstraints.HORIZONTAL;
            gbcLblData.insets = new Insets(0, 5, 0, 5);
            gbcLblData.gridx = 0;
            gbcLblData.gridy = 0;
            panelDataHora.add(getLblData(), gbcLblData);

            GridBagConstraints gbcTxtData = new GridBagConstraints();
            gbcTxtData.fill = GridBagConstraints.BOTH;
            gbcTxtData.insets = new Insets(0, 5, 0, 5);
            gbcTxtData.gridx = 0;
            gbcTxtData.gridy = 1;
            gbcTxtData.weightx = 0.5;
            panelDataHora.add(getTxtData(), gbcTxtData);

            GridBagConstraints gbcLblHora = new GridBagConstraints();
            gbcLblHora.fill = GridBagConstraints.HORIZONTAL;
            gbcLblHora.insets = new Insets(0, 5, 0, 5);
            gbcLblHora.gridx = 1;
            gbcLblHora.gridy = 0;
            panelDataHora.add(getLblHora(), gbcLblHora);

            GridBagConstraints gbcTxtHora = new GridBagConstraints();
            gbcTxtHora.fill = GridBagConstraints.BOTH;
            gbcTxtHora.insets = new Insets(0, 5, 0, 5);
            gbcTxtHora.gridx = 1;
            gbcTxtHora.gridy = 1;
            gbcTxtHora.weightx = 0.5;
            panelDataHora.add(getTxtHora(), gbcTxtHora);
        }
        return panelDataHora;
    }

    private JPanel getPanelPropaganda() {
        if (panelPropaganda == null) {
            panelPropaganda = new JPanel();
            panelPropaganda.setLayout(new GridBagLayout());

            GridBagConstraints gbcLblPropaganda = new GridBagConstraints();
            gbcLblPropaganda.insets = new Insets(0, 10, 0, 0);
            panelPropaganda.add(getLblPropaganda(), gbcLblPropaganda);
        }
        return panelPropaganda;
    }

    private JPanel getPanelTotalBruto() {
        if (panelTotalBruto == null) {
            panelTotalBruto = new JPanel();

            panelTotalBruto.setBackground(Color.LIGHT_GRAY);
            panelTotalBruto.setLayout(new GridBagLayout());
            panelTotalBruto.setBorder(new LineBorder(CINZA));

            GridBagConstraints gbcLblTotalBruto = new GridBagConstraints();
            gbcLblTotalBruto.insets = new Insets(0, 5, 0, 0);
            gbcLblTotalBruto.anchor = GridBagConstraints.NORTHWEST;
            gbcLblTotalBruto.weightx = 1.0;
            panelTotalBruto.add(getLblTotalBruto(), gbcLblTotalBruto);

            GridBagConstraints gbcTotalBruto = new GridBagConstraints();
            gbcTotalBruto.insets = new Insets(0, 0, 0, 5);
            gbcTotalBruto.anchor = GridBagConstraints.EAST;
            gbcTotalBruto.weightx = 1.0;
            gbcTotalBruto.weighty = 1.0;
            gbcTotalBruto.gridx = 0;
            gbcTotalBruto.gridy = 1;
            panelTotalBruto.add(getTotalBruto(), gbcTotalBruto);
        }
        return panelTotalBruto;
    }

    private JPanel getPanelTotalLiquido() {
        if (panelTotalLiquido == null) {
            panelTotalLiquido = new JPanel();
            panelTotalLiquido.setBackground(Color.LIGHT_GRAY);
            panelTotalLiquido.setLayout(new GridBagLayout());
            panelTotalLiquido.setBorder(new LineBorder(CINZA));

            GridBagConstraints gbcLblTotalLiquido = new GridBagConstraints();
            gbcLblTotalLiquido.insets = new Insets(0, 5, 0, 0);
            gbcLblTotalLiquido.anchor = GridBagConstraints.WEST;
            gbcLblTotalLiquido.weightx = 1.0;
            panelTotalLiquido.add(getLblTotalLiquido(), gbcLblTotalLiquido);

            GridBagConstraints gbcTotalVenda = new GridBagConstraints();
            gbcTotalVenda.insets = new Insets(0, 0, 0, 5);
            gbcTotalVenda.anchor = GridBagConstraints.SOUTHEAST;
            gbcTotalVenda.weightx = 1.0;
            gbcTotalVenda.weighty = 1.0;
            gbcTotalVenda.gridx = 0;
            gbcTotalVenda.gridy = 1;
            panelTotalLiquido.add(getTotalVenda(), gbcTotalVenda);
        }
        return panelTotalLiquido;
    }

    public JPanel getPanelBotoes() {
        if (panelBotoes == null) {
            panelBotoes = new JPanel();
            panelBotoes.setLayout(new GridBagLayout());

            GridBagConstraints gbcBtnFinalizar = new GridBagConstraints();
            gbcBtnFinalizar.gridy = 0;
            gbcBtnFinalizar.gridx = 0;
            panelBotoes.add(getBtnFinalizar(), gbcBtnFinalizar);

            GridBagConstraints gbcBtnEditaItem = new GridBagConstraints();
            gbcBtnEditaItem.insets = new Insets(0, 5, 0, 0);
            gbcBtnEditaItem.gridy = 0;
            gbcBtnEditaItem.gridx = 1;
            panelBotoes.add(getBtnEditaItem(), gbcBtnEditaItem);

            GridBagConstraints gbcBtnExcluiItem = new GridBagConstraints();
            gbcBtnExcluiItem.insets = new Insets(0, 5, 0, 0);
            gbcBtnExcluiItem.gridy = 0;
            gbcBtnExcluiItem.gridx = 2;
            panelBotoes.add(getBtnExcluiItem(), gbcBtnExcluiItem);

            GridBagConstraints gbcBtnSelecionaItem = new GridBagConstraints();
            gbcBtnSelecionaItem.insets = new Insets(0, 5, 0, 0);
            gbcBtnSelecionaItem.gridy = 0;
            gbcBtnSelecionaItem.gridx = 3;
            panelBotoes.add(getBtnSelecionaItem(), gbcBtnSelecionaItem);

            GridBagConstraints gbcBtnIncluiItem = new GridBagConstraints();
            gbcBtnIncluiItem.insets = new Insets(0, 5, 0, 0);
            gbcBtnIncluiItem.gridy = 0;
            gbcBtnIncluiItem.gridx = 4;
            panelBotoes.add(getBtnIncluiItem(), gbcBtnIncluiItem);

            GridBagConstraints gbcBtnExcluiVenda = new GridBagConstraints();
            gbcBtnExcluiVenda.insets = new Insets(0, 5, 0, 0);
            gbcBtnExcluiVenda.gridy = 0;
            gbcBtnExcluiVenda.gridx = 5;
            panelBotoes.add(getBtnExcluiVenda(), gbcBtnExcluiVenda);

            GridBagConstraints gbcBtnSair = new GridBagConstraints();
            gbcBtnSair.insets = new Insets(0, 5, 0, 0);
            gbcBtnSair.gridy = 0;
            gbcBtnSair.gridx = 6;
            panelBotoes.add(getBtnSair(), gbcBtnSair);
        }
        return panelBotoes;
    }

    public JScrollPane getScrollTabela() {
        if (scrollTabela == null) {
            scrollTabela = new JScrollPane();
            scrollTabela.setBorder(null);
            scrollTabela.setViewportView(getTabelaProdutos());
        }
        return scrollTabela;
    }

    public JTable getTabelaProdutos() {
        if (tabelaProdutos == null) {
            tabelaProdutos = new JTable();

            GridBagLayout gblPanelGrid = new GridBagLayout();
            gblPanelGrid.columnWidths = new int[]{700};
            gblPanelGrid.rowHeights = new int[]{0};
            gblPanelGrid.columnWeights = new double[]{0.0};
            gblPanelGrid.rowWeights = new double[]{0.0};
            tabelaProdutos.setLayout(gblPanelGrid);
            tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tabelaProdutos.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
            tabelaProdutos.getTableHeader().setBackground(new Color(255, 255, 255));
            tabelaProdutos.setFont(new Font("Tahoma", Font.PLAIN, 16));
            tabelaProdutos.setModel(getGrid());
            tabelaProdutos.setRowHeight(20);

            TableColumnModel columnModel = tabelaProdutos.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);
            columnModel.getColumn(1).setPreferredWidth(225);
            columnModel.getColumn(2).setPreferredWidth(125);
            columnModel.getColumn(3).setPreferredWidth(50);
            columnModel.getColumn(4).setPreferredWidth(100);
            columnModel.getColumn(5).setPreferredWidth(100);
            columnModel.getColumn(6).setPreferredWidth(100);

            DefaultTableCellRenderer dtcrEditar = new DefaultTableCellRenderer();
            dtcrEditar.setIcon(new ImageIcon(getClass().getResource("/imagens/editar.png")));
            columnModel.getColumn(7).setCellRenderer(dtcrEditar);
            columnModel.getColumn(7).setMaxWidth(20);

            DefaultTableCellRenderer dtcrExcluir = new DefaultTableCellRenderer();
            dtcrExcluir.setIcon(new ImageIcon(getClass().getResource("/imagens/remover.png")));
            columnModel.getColumn(8).setCellRenderer(dtcrExcluir);
            columnModel.getColumn(8).setMaxWidth(20);

            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                VendaTableCellRenderer vendaTableCellRenderer = new VendaTableCellRenderer();
                if (i == 2) {
                    vendaTableCellRenderer.setHorizontalAlignment(VendaTableCellRenderer.CENTER);
                } else if (Number.class.isAssignableFrom(tabelaProdutos.getColumnClass(i))) {
                    vendaTableCellRenderer.setHorizontalAlignment(VendaTableCellRenderer.RIGHT);
                }
                columnModel.getColumn(i).setCellRenderer(
                        VendaCellRenderer.class.isAssignableFrom(tabelaProdutos.getColumnClass(i)) ? VendaCellRenderer.criarIconTableCellRendered(i) : vendaTableCellRenderer);
            }

            tabelaProdutos.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_F2, 0), EVENTO);
            tabelaProdutos.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.editaItem();
                }
            });

            tabelaProdutos.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    int colunaSelecionada = ((JTable) e.getSource()).getSelectedColumn();

                    if (colunaSelecionada == 7) {
                        controller.editaItem();
                    } else if (colunaSelecionada == 8) {
                        controller.excluiItem();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(getDefaultCursor());
                }
            });

//            tabelaProdutos

            tabelaProdutos.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    setCursor(isIconeExcluirEditarSelecionado(e) ? new Cursor(Cursor.HAND_CURSOR) : getDefaultCursor());
                }
            });
        }
        return tabelaProdutos;
    }

    class VendaTableCellRenderer extends DefaultTableCellRenderer {
        public VendaTableCellRenderer() {
            super();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return VendaCellRenderer.defineCorLinhaGrid(table, isSelected, row, component);
        }
    }

    private boolean isIconeExcluirEditarSelecionado(MouseEvent e) {
        int colunaSelecionada = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
        return colunaSelecionada == 7 || colunaSelecionada == 8;
    }

    public AbstractTableModel getGrid() {
        if (grid == null) {

            grid = new AbstractTableModel() {
                public final String[] columnNames = {"Código", "Produto", "Código de Barras", "Quantidade", "Valor Unitário", "Percentual (%)", "Valor Total", "", ""};
                private Class<?>[] columnClasses = new Class<?>[] {Number.class, String.class, String.class, Number.class, Number.class, Number.class, Number.class, VendaCellRenderer.class, VendaCellRenderer.class};

                public int getColumnCount() {
                    return columnNames.length;
                }

                public String getColumnName(int column) {
                    return columnNames[column];
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnClasses[columnIndex];
                }

                public int getRowCount() {
                    return controller.getItens().size();
                }

                public Object getValueAt(int row, int column) {
                    ItemVenda item = controller.getItens().get(row);

                    switch (column) {
                        case 0:
                            return item.getCaracteristicaProduto().getProduto().getId();
                        case 1:
                            return item.getCaracteristicaProduto().getProduto().getNomProduto();
                        case 2:
                            return item.getCaracteristicaProduto().getCodBarras();
                        case 3:
                            return item.getQtdProduto();
                        case 4:
                            return formataDouble(item.getVlrUnitario());
                        case 5:
                            return formataDouble(item.getPrcDesconto());
                        case 6:
                            //TODO Rever
//                            return formataDouble(item.getVlrBruto());
                            return formataDouble(item.getVlrUnitario());
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

    private GridBagConstraints getGbcPanelProdutoImagem() {
        final GridBagConstraints gbcPanelProdutoImagem = new GridBagConstraints();
        gbcPanelProdutoImagem.insets = new Insets(10, 5, 5, 5);
        gbcPanelProdutoImagem.fill = GridBagConstraints.BOTH;
        gbcPanelProdutoImagem.gridx = 0;
        gbcPanelProdutoImagem.gridy = 0;
        gbcPanelProdutoImagem.weightx = 1.0;

        return gbcPanelProdutoImagem;
    }

    private GridBagConstraints getGbcPanelNomeProduto() {
        final GridBagConstraints gbcPanelNomeProduto = new GridBagConstraints();
        gbcPanelNomeProduto.insets = new Insets(5, 0, 5, 0);
        gbcPanelNomeProduto.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelNomeProduto.gridx = 0;
        gbcPanelNomeProduto.gridy = 1;

        return gbcPanelNomeProduto;
    }

    private GridBagConstraints getGbcPanelGrid() {
        final GridBagConstraints gbcPanelGrid = new GridBagConstraints();
        gbcPanelGrid.insets = new Insets(10, 10, 10, 10);
        gbcPanelGrid.fill = GridBagConstraints.BOTH;
        gbcPanelGrid.gridx = 0;
        gbcPanelGrid.gridy = 2;
        gbcPanelGrid.weightx = 1.0;
        gbcPanelGrid.weighty = 50.0;

        return gbcPanelGrid;
    }

    private GridBagConstraints getGbcPanelTotalizadores() {
        final GridBagConstraints gbcPanelTotalizadores = new GridBagConstraints();
        gbcPanelTotalizadores.insets = new Insets(10, 5, 5, 5);
        gbcPanelTotalizadores.fill = GridBagConstraints.BOTH;
        gbcPanelTotalizadores.gridx = 0;
        gbcPanelTotalizadores.gridy = 3;

        return gbcPanelTotalizadores;
    }

    private GridBagConstraints getGbcPanelBotoes() {
        final GridBagConstraints gbcPanelBotoes = new GridBagConstraints();
        gbcPanelBotoes.insets = new Insets(10, 5, 10, 5);
        gbcPanelBotoes.fill = GridBagConstraints.HORIZONTAL;
        gbcPanelBotoes.weightx = 1.0;
        gbcPanelBotoes.gridx = 0;
        gbcPanelBotoes.gridy = 4;

        return gbcPanelBotoes;
    }

    public JLabel getLblLeitura() {
        if (lblLeitura == null) {
            lblLeitura = new JLabel("<html><b>Leitura:</b> (Código de Barras, Código ou Descrição do Produto)</html>");
            lblLeitura.setFont(new Font("Tahoma", Font.PLAIN, 12));
        }
        return lblLeitura;
    }

    public JLabel getLblQuantidade() {
        if (lblQuantidade == null) {
            lblQuantidade = new JLabel("Quantidade");
            lblQuantidade.setFont(FONTE_LABEL);
        }
        return lblQuantidade;
    }

    public JLabel getLblEstoque() {
        if (lblEstoque == null) {
            lblEstoque = new JLabel("Estoque");
            lblEstoque.setFont(FONTE_LABEL);
        }
        return lblEstoque;
    }

    public JLabel getLblPrcDesconto() {
        if (lblPrcDesconto == null) {
            lblPrcDesconto = new JLabel("Desconto (%)");
            lblPrcDesconto.setFont(FONTE_LABEL);
        }
        return lblPrcDesconto;
    }

    public JLabel getLblVlrUnitario() {
        if (lblVlrUnitario == null) {
            lblVlrUnitario = new JLabel("Valor Unitário (R$)");
            lblVlrUnitario.setFont(FONTE_LABEL);
        }
        return lblVlrUnitario;
    }

    public JLabel getLblCodigoBarras() {
        if (lblCodigoBarras == null) {
            lblCodigoBarras = new JLabel("Código de Barras");
            lblCodigoBarras.setFont(FONTE_LABEL);
        }
        return lblCodigoBarras;
    }

    public JLabel getLblUsuario() {
        if (lblUsuario == null) {
            lblUsuario = new JLabel("Usuário");
            lblUsuario.setFont(FONTE_LABEL);
        }
        return lblUsuario;
    }

    public JLabel getLblData() {
        if (lblData == null) {
            lblData = new JLabel("Data");
            lblData.setFont(FONTE_LABEL);
        }
        return lblData;
    }

    public JLabel getLblHora() {
        if (lblHora == null) {
            lblHora = new JLabel("Hora");
            lblHora.setFont(FONTE_LABEL);
        }
        return lblHora;
    }

    public JLabel getLblNomeProduto() {
        if (lblNomeProduto == null) {
            lblNomeProduto = new JLabel();
            lblNomeProduto.setText(" ");
            lblNomeProduto.setPreferredSize(new Dimension(0, 60));
            lblNomeProduto.setHorizontalAlignment(SwingConstants.CENTER);
            lblNomeProduto.setFont(new Font("Tahoma", Font.BOLD, 44));
        }
        return lblNomeProduto;
    }

    private JLabel getLblImagem() {
        if (lblImagem == null) {
            lblImagem = new JLabel();
            lblImagem.setText("");
            lblImagem.setFont(FONTE_LABEL);
            lblImagem.setIcon(new ImageIcon(getClass().getResource("/imagens/logo-venda.png")));
        }
        return lblImagem;
    }

    private JLabel getLblPropaganda() {
        if (lblPropaganda == null) {
            lblPropaganda = new JLabel();
            lblPropaganda.setText("");
            lblPropaganda.setFont(FONTE_LABEL);
            lblPropaganda.setIcon(new ImageIcon(getClass().getResource("/imagens/propaganda.png")));
        }
        return lblPropaganda;
    }

    private JLabel getLblTotalLiquido() {
        if (lblTotalLiquido == null) {
            lblTotalLiquido = new JLabel("Total Líquido:");
            lblTotalLiquido.setFont(FONTE_LABEL);
        }
        return lblTotalLiquido;
    }

    public JLabel getTotalVenda() {
        if (totalVenda == null) {
            totalVenda = new JLabel(VALOR_ZERO);
            totalVenda.setForeground(VERDE_DESCRICAO);
            totalVenda.setPreferredSize(new Dimension(230, 45));
            totalVenda.setHorizontalAlignment(SwingConstants.RIGHT);
            totalVenda.setFont(new Font("Dialog", Font.BOLD, 42));
        }
        return totalVenda;
    }

    private JLabel getLblTotalBruto() {
        if (lblTotalBruto == null) {
            lblTotalBruto = new JLabel("Total Bruto:");
            lblTotalBruto.setFont(FONTE_LABEL);
        }
        return lblTotalBruto;
    }

    public JLabel getTotalBruto() {
        if (totalBruto == null) {
            totalBruto = new JLabel(VALOR_ZERO);
            totalBruto.setPreferredSize(new Dimension(142, 30));
            totalBruto.setHorizontalAlignment(SwingConstants.RIGHT);
            totalBruto.setFont(new Font("Tahoma", Font.BOLD, 24));
        }
        return totalBruto;
    }

    public JTextField getTxtLeitura() {
        if (txtLeitura == null) {
            txtLeitura = new JTextField();
            txtLeitura.setFont(FONTE_CAMPO);
            txtLeitura.addActionListener(a -> controller.buscaProduto());
        }
        return txtLeitura;
    }

    public JTextField getTxtEstoque() {
        if (txtEstoque == null) {
            txtEstoque = new JTextField();
            txtEstoque.setFont(FONTE_CAMPO);
            txtEstoque.setHorizontalAlignment(SwingConstants.RIGHT);
            txtEstoque.setEnabled(false);
        }
        return txtEstoque;
    }

    public JTextFieldPorcentagem getTxtPrcDesconto() {
        if (txtPrcDesconto == null) {
            txtPrcDesconto = new JTextFieldPorcentagem();
            txtPrcDesconto.setFont(FONTE_CAMPO);
            txtPrcDesconto.setHorizontalAlignment(SwingConstants.RIGHT);
            txtPrcDesconto.addActionListener(a -> controller.preencheItem());
        }
        return txtPrcDesconto;
    }

    public JTextFieldSomenteNumeros getTxtQuantidade() {
        if (txtQuantidade == null) {
            txtQuantidade = new JTextFieldSomenteNumeros(3);
            txtQuantidade.setFont(FONTE_CAMPO);
            txtQuantidade.setHorizontalAlignment(SwingConstants.RIGHT);
            txtQuantidade.addActionListener(a -> controller.setaFocoPrcDesconto());
        }
        return txtQuantidade;
    }

    public JTextField getTxtVlrUnitario() {
        if (txtVlrUnitario == null) {
            txtVlrUnitario = new JTextField();
            txtVlrUnitario.setFont(FONTE_CAMPO);
            txtVlrUnitario.setHorizontalAlignment(SwingConstants.RIGHT);
            txtVlrUnitario.setEnabled(false);
        }
        return txtVlrUnitario;
    }

    public JTextField getTxtCodigoBarras() {
        if (txtCodigoBarras == null) {
            txtCodigoBarras = new JTextField();
            txtCodigoBarras.setFont(FONTE_CAMPO);
            txtCodigoBarras.setEnabled(false);
        }
        return txtCodigoBarras;
    }

    public JTextField getTxtData() {
        if (txtData == null) {
            txtData = new JTextField();
            txtData.setFont(FONTE_INFORMATIVO);
            txtData.setEnabled(false);
        }
        return txtData;
    }

    public JTextField getTxtHora() {
        if (txtHora == null) {
            txtHora = new JTextField();
            txtHora.setFont(FONTE_INFORMATIVO);
            txtHora.setEnabled(false);
        }
        return txtHora;
    }

    public JTextField getTxtUsuario() {
        if (txtUsuario == null) {
            txtUsuario = new JTextField();
            txtUsuario.setFont(FONTE_INFORMATIVO);
            txtUsuario.setEnabled(false);
        }
        return txtUsuario;
    }

    public JButton getBtnFinalizar() {
        if (btnFinalizar == null) {
            btnFinalizar = new JButtonTelaDeVendas("Finalizar", "F1", "Clique para finalizar a venda", new ImageIcon(getClass().getResource("/imagens/finalizar.png")));
            btnFinalizar.addActionListener(a -> controller.finalizar());
            btnFinalizar.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F1, 0), EVENTO);
            btnFinalizar.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnFinalizar.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.finalizar();
                }
            });
        }
        return btnFinalizar;
    }

    public JButton getBtnEditaItem() {
        if (btnEditaItem == null) {
            btnEditaItem = new JButtonTelaDeVendas("Edita Item", "F2", "Clique para editar o item", new ImageIcon(getClass().getResource("/imagens/edita.png")));
            btnEditaItem.addActionListener(a -> controller.editaItem());
            btnEditaItem.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F2, 0), EVENTO);
            btnEditaItem.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnEditaItem.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.editaItem();
                }
            });
        }
        return btnEditaItem;
    }

    public JButton getBtnExcluiItem() {
        if (btnExcluiItem == null) {
            btnExcluiItem = new JButtonTelaDeVendas("Exclui Item", "F3", "Clique para excluir um item", new ImageIcon(getClass().getResource("/imagens/excluir.png")));
            btnExcluiItem.addActionListener(a -> controller.excluiItem());
            btnExcluiItem.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F3, 0), EVENTO);
            btnExcluiItem.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnExcluiItem.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.excluiItem();
                }
            });
        }
        return btnExcluiItem;
    }

    public JButton getBtnSelecionaItem() {
        if (btnSelecionaItem == null) {
            btnSelecionaItem = new JButtonTelaDeVendas("Seleciona Item", "F4", "Clique para selecionar um item", new ImageIcon(getClass().getResource("/imagens/selecione.png")));
            btnSelecionaItem.addActionListener(a -> controller.selecionaItem());
            btnSelecionaItem.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F4, 0), EVENTO);
            btnSelecionaItem.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnSelecionaItem.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.selecionaItem();
                }
            });
        }
        return btnSelecionaItem;
    }

    public JButton getBtnIncluiItem() {
        if (btnIncluiItem == null) {
            btnIncluiItem = new JButtonTelaDeVendas("Inclui Item", "F5", "Clique para incluir um item", new ImageIcon(getClass().getResource("/imagens/inclui.png")));
            btnIncluiItem.addActionListener(a -> controller.incluiItem());
            btnIncluiItem.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F5, 0), EVENTO);
            btnIncluiItem.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnIncluiItem.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.incluiItem();
                }
            });
        }
        return btnIncluiItem;
    }

    public JButton getBtnExcluiVenda() {
        if (btnExcluiVenda == null) {
            btnExcluiVenda = new JButtonTelaDeVendas("Excluir Venda", "F6", "Clique para excluir a venda", new ImageIcon(getClass().getResource("/imagens/excluir.png")));
            btnExcluiVenda.addActionListener(a -> controller.excluiVenda());
            btnExcluiVenda.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F6, 0), EVENTO);
            btnExcluiVenda.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnExcluiVenda.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.excluiVenda();
                }
            });
        }
        return btnExcluiVenda;
    }

    public JButton getBtnSair() {
        if (btnSair == null) {
            btnSair = new JButtonTelaDeVendas("Sair", "Esc", "Clique para fechar", new ImageIcon(getClass().getResource("/imagens/sair.png")));
            btnSair.addActionListener(a -> dispose());
            btnSair.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_ESCAPE, 0), EVENTO);
            btnSair.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, 0), EVENTO);
            btnSair.getActionMap().put(EVENTO, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }
        return btnSair;
    }
}