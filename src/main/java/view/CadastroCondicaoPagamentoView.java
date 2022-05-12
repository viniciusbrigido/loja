package view;

import personalizado.JTextFieldLimitador;
import personalizado.JTextFieldSomenteNumeros;
import javax.swing.*;
import java.awt.*;

public class CadastroCondicaoPagamentoView extends ControllerView {
    private JPanel panelCodigo;
    private JPanel panelDescricao;
    private JPanel panelDiasParcelas;
    private JPanel panelBotoes;

    private JLabel lblCodigo;
    private JLabel lblDescricao;
    private JLabel lblNumDiasAtePrimeiraParcela;
    private JLabel lblNumDiasEntreParcelas;

    private JTextFieldLimitador txtDescricao;
    private JTextFieldSomenteNumeros txtNumDiasAtePrimeiraParcela;
    private JTextFieldSomenteNumeros txtNumDiasEntreParcelas;

    public CadastroCondicaoPagamentoView() {
        setTitle("Cadastro de Condições de Pagamento");
        initialize();
    }

    private void initialize() {
        setSize(new Dimension(510, 240));
        setPreferredSize(new Dimension(510, 240));
        add(getPanelCodigo(), getGbcPanelCodigo());
        add(getPanelDescricao(), getGbcPanelDescricao());
        add(getPanelDiasParcelas(), getGbcPanelDiasParcelas());
        add(getPanelBotoes(), getGbcPanelBotoes());
    }

    private JPanel getPanelCodigo() {
        if (panelCodigo == null) {
            panelCodigo = new JPanel();

            GridBagLayout gblPanelCodigo = new GridBagLayout();
            gblPanelCodigo.columnWidths = new int[]{100, 390};
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

    private JPanel getPanelDescricao() {
        if (panelDescricao == null) {
            panelDescricao = new JPanel();

            GridBagLayout gblPanelDescricao = new GridBagLayout();
            gblPanelDescricao.columnWidths = new int[]{490};
            gblPanelDescricao.rowHeights = new int[]{0, 0};
            gblPanelDescricao.columnWeights = new double[]{0.0};
            gblPanelDescricao.rowWeights = new double[]{0.0, 0.0};
            panelDescricao.setLayout(gblPanelDescricao);

            GridBagConstraints gbcLblDescricao = new GridBagConstraints();
            gbcLblDescricao.fill = GridBagConstraints.BOTH;
            gbcLblDescricao.insets = new Insets(5, 5, 5, 5);
            gbcLblDescricao.gridx = 0;
            gbcLblDescricao.gridy = 0;
            panelDescricao.add(getLblDescricao(), gbcLblDescricao);

            GridBagConstraints gbcTxtDescricao = new GridBagConstraints();
            gbcTxtDescricao.fill = GridBagConstraints.BOTH;
            gbcTxtDescricao.insets = new Insets(0, 5, 5, 5);
            gbcTxtDescricao.gridx = 0;
            gbcTxtDescricao.gridy = 1;
            panelDescricao.add(getTxtDescricao(), gbcTxtDescricao);
        }
        return panelDescricao;
    }

    private JPanel getPanelDiasParcelas() {
        if (panelDiasParcelas == null) {
            panelDiasParcelas = new JPanel();

            GridBagLayout gblPanelDiasParcelas = new GridBagLayout();
            gblPanelDiasParcelas.columnWidths = new int[]{245, 245};
            gblPanelDiasParcelas.rowHeights = new int[]{0, 0};
            gblPanelDiasParcelas.columnWeights = new double[]{0.0, 0.0};
            gblPanelDiasParcelas.rowWeights = new double[]{0.0, 0.0};
            panelDiasParcelas.setLayout(gblPanelDiasParcelas);

            GridBagConstraints gbcLblNumDiasAtePrimeiraParcela = new GridBagConstraints();
            gbcLblNumDiasAtePrimeiraParcela.fill = GridBagConstraints.BOTH;
            gbcLblNumDiasAtePrimeiraParcela.insets = new Insets(5, 5, 5, 5);
            gbcLblNumDiasAtePrimeiraParcela.gridx = 0;
            gbcLblNumDiasAtePrimeiraParcela.gridy = 0;
            panelDiasParcelas.add(getLblNumDiasAtePrimeiraParcela(), gbcLblNumDiasAtePrimeiraParcela);

            GridBagConstraints gbcTxtNumDiasAtePrimeiraParcela = new GridBagConstraints();
            gbcTxtNumDiasAtePrimeiraParcela.fill = GridBagConstraints.BOTH;
            gbcTxtNumDiasAtePrimeiraParcela.insets = new Insets(0, 5, 5, 5);
            gbcTxtNumDiasAtePrimeiraParcela.gridx = 0;
            gbcTxtNumDiasAtePrimeiraParcela.gridy = 1;
            panelDiasParcelas.add(getTxtNumDiasAtePrimeiraParcela(), gbcTxtNumDiasAtePrimeiraParcela);

            GridBagConstraints gbcLblNumDiasEntreParcelas = new GridBagConstraints();
            gbcLblNumDiasEntreParcelas.fill = GridBagConstraints.BOTH;
            gbcLblNumDiasEntreParcelas.insets = new Insets(5, 5, 5, 5);
            gbcLblNumDiasEntreParcelas.gridx = 1;
            gbcLblNumDiasEntreParcelas.gridy = 0;
            panelDiasParcelas.add(getLblNumDiasEntreParcelas(), gbcLblNumDiasEntreParcelas);

            GridBagConstraints gbcTxtNumDiasEntreParcelas  = new GridBagConstraints();
            gbcTxtNumDiasEntreParcelas.fill = GridBagConstraints.BOTH;
            gbcTxtNumDiasEntreParcelas.insets = new Insets(0, 5, 5, 5);
            gbcTxtNumDiasEntreParcelas.gridx = 1;
            gbcTxtNumDiasEntreParcelas.gridy = 1;
            panelDiasParcelas.add(getTxtNumDiasEntreParcelas(), gbcTxtNumDiasEntreParcelas);
        }
        return panelDiasParcelas;
    }

    private JPanel getPanelBotoes() {
        if (panelBotoes == null) {
            panelBotoes = new JPanel();

            GridBagLayout gblPanelBotoes = new GridBagLayout();
            gblPanelBotoes.columnWidths = new int[]{0, 0, 0, 0};
            gblPanelBotoes.rowHeights = new int[]{0};
            gblPanelBotoes.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
            gblPanelBotoes.rowWeights = new double[]{0.0};
            panelBotoes.setLayout(gblPanelBotoes);

            GridBagConstraints gbcBtnSalvar = new GridBagConstraints();
            gbcBtnSalvar.insets = new Insets(10, 5, 5, 5);
            gbcBtnSalvar.gridx = 0;
            gbcBtnSalvar.gridy = 0;
            panelBotoes.add(getBtnSalvar(), gbcBtnSalvar);

            GridBagConstraints gbcBtnListar = new GridBagConstraints();
            gbcBtnListar.insets = new Insets(10, 5, 5, 5);
            gbcBtnListar.gridx = 1;
            gbcBtnListar.gridy = 0;
            panelBotoes.add(getBtnListar(), gbcBtnListar);

            GridBagConstraints gbcBtnLimpar = new GridBagConstraints();
            gbcBtnLimpar.insets = new Insets(10, 5, 5, 5);
            gbcBtnLimpar.gridx = 2;
            gbcBtnLimpar.gridy = 0;
            panelBotoes.add(getBtnLimpar(), gbcBtnLimpar);

            GridBagConstraints gbcBtnSair = new GridBagConstraints();
            gbcBtnSair.insets = new Insets(10, 5, 5, 5);
            gbcBtnSair.gridx = 3;
            gbcBtnSair.gridy = 0;
            panelBotoes.add(getBtnSair(), gbcBtnSair);
        }
        return panelBotoes;
    }

    private GridBagConstraints getGbcPanelCodigo() {
        final GridBagConstraints gbcPanelCodigo = new GridBagConstraints();
        gbcPanelCodigo.insets = new Insets(0, 5, 0, 5);
        gbcPanelCodigo.fill = GridBagConstraints.BOTH;
        gbcPanelCodigo.gridx = 0;
        gbcPanelCodigo.gridy = 0;

        return gbcPanelCodigo;
    }

    private GridBagConstraints getGbcPanelDescricao() {
        final GridBagConstraints gbcPanelDescricao = new GridBagConstraints();
        gbcPanelDescricao.insets = new Insets(0, 5, 0, 5);
        gbcPanelDescricao.fill = GridBagConstraints.BOTH;
        gbcPanelDescricao.gridx = 0;
        gbcPanelDescricao.gridy = 1;

        return gbcPanelDescricao;
    }

    private GridBagConstraints getGbcPanelDiasParcelas() {
        final GridBagConstraints gbcPanelDiasParcelas = new GridBagConstraints();
        gbcPanelDiasParcelas.fill = GridBagConstraints.BOTH;
        gbcPanelDiasParcelas.gridx = 0;
        gbcPanelDiasParcelas.gridy = 2;

        return gbcPanelDiasParcelas;
    }

    private GridBagConstraints getGbcPanelBotoes() {
        final GridBagConstraints gbcPanelBotoes = new GridBagConstraints();
        gbcPanelBotoes.fill = GridBagConstraints.BOTH;
        gbcPanelBotoes.gridx = 0;
        gbcPanelBotoes.gridy = 3;

        return gbcPanelBotoes;
    }

    public JTextFieldLimitador getTxtDescricao() {
        if (txtDescricao == null) {
            txtDescricao = new JTextFieldLimitador(40);
            txtDescricao.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtDescricao;
    }

    public JTextField getTxtNumDiasAtePrimeiraParcela() {
        if (txtNumDiasAtePrimeiraParcela == null) {
            txtNumDiasAtePrimeiraParcela = new JTextFieldSomenteNumeros(3);
            txtNumDiasAtePrimeiraParcela.setHorizontalAlignment(SwingConstants.RIGHT);
            txtNumDiasAtePrimeiraParcela.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtNumDiasAtePrimeiraParcela;
    }

    public JTextField getTxtNumDiasEntreParcelas() {
        if (txtNumDiasEntreParcelas == null) {
            txtNumDiasEntreParcelas = new JTextFieldSomenteNumeros(3);
            txtNumDiasEntreParcelas.setHorizontalAlignment(SwingConstants.RIGHT);
            txtNumDiasEntreParcelas.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtNumDiasEntreParcelas;
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

    public JLabel getLblNumDiasAtePrimeiraParcela() {
        if (lblNumDiasAtePrimeiraParcela == null) {
            lblNumDiasAtePrimeiraParcela = new JLabel("Qtd. Dias até Primeira Parcela:*");
        }
        return lblNumDiasAtePrimeiraParcela;
    }

    public JLabel getLblNumDiasEntreParcelas() {
        if (lblNumDiasEntreParcelas == null) {
            lblNumDiasEntreParcelas = new JLabel("Qtd. Dias entre as Parcelas:*");
        }
        return lblNumDiasEntreParcelas;
    }
}
