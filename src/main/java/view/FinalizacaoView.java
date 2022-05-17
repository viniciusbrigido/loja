package view;

import personalizado.JButtonTelaDeVendas;
import personalizado.JTextFieldSomenteNumeros;
import javax.swing.*;
import java.awt.*;

public class FinalizacaoView extends ControllerView {

    private static final Font FONTE_LABEL = new Font("Tahoma", Font.BOLD, 12);
    private static final Font FONTE_CAMPO = new Font("Tahoma", Font.PLAIN, 22);
    private static final Dimension DIMENSION_BOTAO = new Dimension(25, 25);

    private JPanel panelCampos;
    private JPanel panelBotoes;

    private JLabel lblVendedor;
    private JLabel lblCliente;
    private JLabel lblCondicao;

    private JTextField txtCodVendedor;
    private JTextField txtCodCliente;
    private JTextField txtCodCondicao;
    private JTextField txtNomVendedor;
    private JTextField txtNomCliente;
    private JTextField txtNomCondicao;

    private JButton btnVendedor;
    private JButton btnCliente;
    private JButton btnCondicao;
    private JButton btnFinalizar;
    private JButton btnLimpar;
    private JButton btnSair;

    public FinalizacaoView() {
        setTitle("Finalização da Venda");
        setSize(new Dimension(620, 290));
        setPreferredSize(new Dimension(620, 290));
        initialize();
    }

    private void initialize() {
        setLayout(new GridBagLayout());
        add(getPanelCampos(), getGbcPanelCampos());
        add(getPanelBotoes(), getGbcPanelBotoes());
    }

    private JPanel getPanelCampos() {
        if (panelCampos == null) {
            panelCampos = new JPanel();

            GridBagLayout gblPanelVendedor = new GridBagLayout();
            gblPanelVendedor.columnWidths = new int[]{150, 0, 400};
            gblPanelVendedor.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
            gblPanelVendedor.columnWeights = new double[]{0.0, 0.0, 0.0};
            gblPanelVendedor.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            panelCampos.setLayout(gblPanelVendedor);

            GridBagConstraints gbcLblVendedor = new GridBagConstraints();
            gbcLblVendedor.fill = GridBagConstraints.BOTH;
            gbcLblVendedor.insets = new Insets(5, 5, 0, 5);
            gbcLblVendedor.gridx = 0;
            gbcLblVendedor.gridy = 0;
            panelCampos.add(getLblVendedor(), gbcLblVendedor);

            GridBagConstraints gbcTxtCodVendedor = new GridBagConstraints();
            gbcTxtCodVendedor.fill = GridBagConstraints.HORIZONTAL;
            gbcTxtCodVendedor.insets = new Insets(5, 5, 5, 5);
            gbcTxtCodVendedor.gridx = 0;
            gbcTxtCodVendedor.gridy = 1;
            panelCampos.add(getTxtCodVendedor(), gbcTxtCodVendedor);

            GridBagConstraints gbcBtnVendedor = new GridBagConstraints();
            gbcBtnVendedor.insets = new Insets(5, 0, 5, 5);
            gbcBtnVendedor.gridx = 1;
            gbcBtnVendedor.gridy = 1;
            panelCampos.add(getBtnVendedor(), gbcBtnVendedor);

            GridBagConstraints gbcTxtNomVendedor = new GridBagConstraints();
            gbcTxtNomVendedor.fill = GridBagConstraints.HORIZONTAL;
            gbcTxtNomVendedor.insets = new Insets(5, 0, 5, 5);
            gbcTxtNomVendedor.gridx = 2;
            gbcTxtNomVendedor.gridy = 1;
            panelCampos.add(getTxtNomVendedor(), gbcTxtNomVendedor);

            GridBagConstraints gbcLblCliente = new GridBagConstraints();
            gbcLblCliente.fill = GridBagConstraints.BOTH;
            gbcLblCliente.insets = new Insets(5, 5, 0, 5);
            gbcLblCliente.gridx = 0;
            gbcLblCliente.gridy = 2;
            panelCampos.add(getLblCliente(), gbcLblCliente);

            GridBagConstraints gbcTxtCodCliente = new GridBagConstraints();
            gbcTxtCodCliente.fill = GridBagConstraints.HORIZONTAL;
            gbcTxtCodCliente.insets = new Insets(5, 5, 5, 5);
            gbcTxtCodCliente.gridx = 0;
            gbcTxtCodCliente.gridy = 3;
            panelCampos.add(getTxtCodCliente(), gbcTxtCodCliente);

            GridBagConstraints gbcBtnCliente = new GridBagConstraints();
            gbcBtnCliente.insets = new Insets(5, 0, 5, 5);
            gbcBtnCliente.gridx = 1;
            gbcBtnCliente.gridy = 3;
            panelCampos.add(getBtnCliente(), gbcBtnCliente);

            GridBagConstraints gbcTxtNomCliente = new GridBagConstraints();
            gbcTxtNomCliente.fill = GridBagConstraints.HORIZONTAL;
            gbcTxtNomCliente.insets = new Insets(5, 0, 5, 5);
            gbcTxtNomCliente.gridx = 2;
            gbcTxtNomCliente.gridy = 3;
            panelCampos.add(getTxtNomCliente(), gbcTxtNomCliente);

            GridBagConstraints gbcLblCondicao = new GridBagConstraints();
            gbcLblCondicao.fill = GridBagConstraints.BOTH;
            gbcLblCondicao.insets = new Insets(5, 5, 0, 5);
            gbcLblCondicao.gridx = 0;
            gbcLblCondicao.gridy = 4;
            panelCampos.add(getLblCondicao(), gbcLblCondicao);

            GridBagConstraints gbcTxtCodCondicao = new GridBagConstraints();
            gbcTxtCodCondicao.fill = GridBagConstraints.HORIZONTAL;
            gbcTxtCodCondicao.insets = new Insets(5, 5, 5, 5);
            gbcTxtCodCondicao.gridx = 0;
            gbcTxtCodCondicao.gridy = 5;
            panelCampos.add(getTxtCodCondicao(), gbcTxtCodCondicao);

            GridBagConstraints gbcBtnCondicao = new GridBagConstraints();
            gbcBtnCondicao.insets = new Insets(5, 0, 5, 5);
            gbcBtnCondicao.gridx = 1;
            gbcBtnCondicao.gridy = 5;
            panelCampos.add(getBtnCondicao(), gbcBtnCondicao);

            GridBagConstraints gbcTxtNomCondicao = new GridBagConstraints();
            gbcTxtNomCondicao.fill = GridBagConstraints.HORIZONTAL;
            gbcTxtNomCondicao.insets = new Insets(5, 0, 5, 5);
            gbcTxtNomCondicao.gridx = 2;
            gbcTxtNomCondicao.gridy = 5;
            panelCampos.add(getTxtNomCondicao(), gbcTxtNomCondicao);
        }
        return panelCampos;
    }

    public JPanel getPanelBotoes() {
        if (panelBotoes == null) {
            panelBotoes = new JPanel();
            panelBotoes.setLayout(new GridBagLayout());

            GridBagConstraints gbcBtnFinalizar = new GridBagConstraints();
            gbcBtnFinalizar.gridy = 0;
            gbcBtnFinalizar.gridx = 0;
            panelBotoes.add(getBtnFinalizar(), gbcBtnFinalizar);

            GridBagConstraints gbcBtnLimpar = new GridBagConstraints();
            gbcBtnLimpar.insets = new Insets(0, 5, 0, 0);
            gbcBtnLimpar.gridy = 0;
            gbcBtnLimpar.gridx = 1;
            panelBotoes.add(getBtnLimpar(), gbcBtnLimpar);

            GridBagConstraints gbcBtnSair = new GridBagConstraints();
            gbcBtnSair.insets = new Insets(0, 5, 0, 0);
            gbcBtnSair.gridy = 0;
            gbcBtnSair.gridx = 2;
            panelBotoes.add(getBtnSair(), gbcBtnSair);
        }
        return panelBotoes;
    }

    private GridBagConstraints getGbcPanelCampos() {
        final GridBagConstraints gbcPanelCampos = new GridBagConstraints();
        gbcPanelCampos.fill = GridBagConstraints.BOTH;
        gbcPanelCampos.gridx = 0;
        gbcPanelCampos.gridy = 0;

        return gbcPanelCampos;
    }

    private GridBagConstraints getGbcPanelBotoes() {
        final GridBagConstraints gbcPanelBotoes = new GridBagConstraints();
        gbcPanelBotoes.insets = new Insets(10, 0, 0, 0);
        gbcPanelBotoes.fill = GridBagConstraints.BOTH;
        gbcPanelBotoes.gridx = 0;
        gbcPanelBotoes.gridy = 1;

        return gbcPanelBotoes;
    }

    public JLabel getLblVendedor() {
        if (lblVendedor == null) {
            lblVendedor = new JLabel("Vendedor");
            lblVendedor.setFont(FONTE_LABEL);
        }
        return lblVendedor;
    }

    public JLabel getLblCliente() {
        if (lblCliente == null) {
            lblCliente = new JLabel("Cliente");
            lblCliente.setFont(FONTE_LABEL);
        }
        return lblCliente;
    }

    public JLabel getLblCondicao() {
        if (lblCondicao == null) {
            lblCondicao = new JLabel("Condição Pagto.");
            lblCondicao.setFont(FONTE_LABEL);
        }
        return lblCondicao;
    }

    public JTextField getTxtCodVendedor() {
        if (txtCodVendedor == null) {
            txtCodVendedor = new JTextFieldSomenteNumeros();
            txtCodVendedor.setFont(FONTE_CAMPO);
            txtCodVendedor.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return txtCodVendedor;
    }

    public JTextField getTxtCodCliente() {
        if (txtCodCliente == null) {
            txtCodCliente = new JTextFieldSomenteNumeros();
            txtCodCliente.setFont(FONTE_CAMPO);
            txtCodCliente.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return txtCodCliente;
    }

    public JTextField getTxtCodCondicao() {
        if (txtCodCondicao == null) {
            txtCodCondicao = new JTextFieldSomenteNumeros();
            txtCodCondicao.setFont(FONTE_CAMPO);
            txtCodCondicao.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        return txtCodCondicao;
    }

    public JTextField getTxtNomVendedor() {
        if (txtNomVendedor == null) {
            txtNomVendedor = new JTextField();
            txtNomVendedor.setFont(FONTE_CAMPO);
            txtNomVendedor.setEnabled(false);
        }
        return txtNomVendedor;
    }

    public JTextField getTxtNomCliente() {
        if (txtNomCliente == null) {
            txtNomCliente = new JTextField();
            txtNomCliente.setFont(FONTE_CAMPO);
            txtNomCliente.setEnabled(false);
        }
        return txtNomCliente;
    }

    public JTextField getTxtNomCondicao() {
        if (txtNomCondicao == null) {
            txtNomCondicao = new JTextField();
            txtNomCondicao.setFont(FONTE_CAMPO);
            txtNomCondicao.setEnabled(false);
        }
        return txtNomCondicao;
    }

    public JButton getBtnVendedor() {
        if (btnVendedor == null) {
            btnVendedor = new JButton(new ImageIcon(getClass().getResource("/imagens/lupa.png")));
            btnVendedor.setPreferredSize(DIMENSION_BOTAO);
            btnVendedor.setMinimumSize(DIMENSION_BOTAO);
            btnVendedor.setFocusable(false);
        }
        return btnVendedor;
    }

    public JButton getBtnCliente() {
        if (btnCliente == null) {
            btnCliente = new JButton(new ImageIcon(getClass().getResource("/imagens/lupa.png")));
            btnCliente.setPreferredSize(DIMENSION_BOTAO);
            btnCliente.setMinimumSize(DIMENSION_BOTAO);
            btnCliente.setFocusable(false);
        }
        return btnCliente;
    }

    public JButton getBtnCondicao() {
        if (btnCondicao == null) {
            btnCondicao = new JButton(new ImageIcon(getClass().getResource("/imagens/lupa.png")));
            btnCondicao.setPreferredSize(DIMENSION_BOTAO);
            btnCondicao.setMinimumSize(DIMENSION_BOTAO);
            btnCondicao.setFocusable(false);
        }
        return btnCondicao;
    }

    public JButton getBtnFinalizar() {
        if (btnFinalizar == null) {
            btnFinalizar = new JButtonTelaDeVendas("Finalizar", "F1", "Clique para finalizar a venda", new ImageIcon(getClass().getResource("/imagens/finalizar.png")));
        }
        return btnFinalizar;
    }

    public JButton getBtnLimpar() {
        if (btnLimpar == null) {
            btnLimpar = new JButtonTelaDeVendas("Limpar", "F2", "Clique para limpar a tela", new ImageIcon(getClass().getResource("/imagens/excluir.png")));
        }
        return btnLimpar;
    }

    public JButton getBtnSair() {
        if (btnSair == null) {
            btnSair = new JButtonTelaDeVendas("Sair", "Esc", "Clique para fechar", new ImageIcon(getClass().getResource("/imagens/sair.png")));
        }
        return btnSair;
    }
}