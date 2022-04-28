package view;

import controller.CadastroEnderecoController;
import personalizado.JTextFieldSomenteNumeros;
import util.Cores;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class CadastroEnderecoView extends ControllerView {

    private JPanel panelCodigo;
    private JPanel panelCepLogradouro;
    private JPanel panelBairroCidade;
    private JPanel panelBotoes;

    private JLabel lblCodigo;
    private JLabel lblCep;
    private JLabel lblLogradouro;
    private JLabel lblBairro;
    private JLabel lblCidade;

    private JTextFieldSomenteNumeros txtCodigo;
    private JTextField txtCep;
    private JTextField txtLogradouro;

    private JComboBox<String> comboBairro;
    private JComboBox<String> comboCidade;

    private JButton btnSalvar;
    private JButton btnSair;
    private JButton btnLimpar;
    private JButton btnListar;

    private CadastroEnderecoController controller;

    public CadastroEnderecoView(CadastroEnderecoController controller) {
        setTitle("Cadastro de Endereços");
        this.controller = controller;
        initialize();
    }

    private void initialize() {
        setSize(new Dimension(510, 250));
        setPreferredSize(new Dimension(510, 250));
        add(getPanelCodigo(), getGbcPanelCodigo());
        add(getPanelCepLogradouro(), getGbcPanelCepLogradouro());
        add(getPanelBairroCidade(), getGbcPanelBairroCidade());
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

    private JPanel getPanelCepLogradouro() {
        if (panelCepLogradouro == null) {
            panelCepLogradouro = new JPanel();

            GridBagLayout gblPanelLogradouro = new GridBagLayout();
            gblPanelLogradouro.columnWidths = new int[]{140, 350};
            gblPanelLogradouro.rowHeights = new int[]{0, 0};
            gblPanelLogradouro.columnWeights = new double[]{0.0, 0.0};
            gblPanelLogradouro.rowWeights = new double[]{0.0, 0.0};
            panelCepLogradouro.setLayout(gblPanelLogradouro);

            GridBagConstraints gbcLblCep = new GridBagConstraints();
            gbcLblCep.fill = GridBagConstraints.BOTH;
            gbcLblCep.insets = new Insets(5, 5, 5, 5);
            gbcLblCep.gridx = 0;
            gbcLblCep.gridy = 0;
            panelCepLogradouro.add(getLblCep(), gbcLblCep);

            GridBagConstraints gbcTxtCep = new GridBagConstraints();
            gbcTxtCep.fill = GridBagConstraints.BOTH;
            gbcTxtCep.insets = new Insets(0, 5, 5, 4);
            gbcTxtCep.gridx = 0;
            gbcTxtCep.gridy = 1;
            panelCepLogradouro.add(getTxtCep(), gbcTxtCep);

            GridBagConstraints gbcLblLogradouro = new GridBagConstraints();
            gbcLblLogradouro.fill = GridBagConstraints.BOTH;
            gbcLblLogradouro.insets = new Insets(5, 5, 5, 5);
            gbcLblLogradouro.gridx = 1;
            gbcLblLogradouro.gridy = 0;
            panelCepLogradouro.add(getLblLogradouro(), gbcLblLogradouro);

            GridBagConstraints gbcTxtLogradouro = new GridBagConstraints();
            gbcTxtLogradouro.fill = GridBagConstraints.BOTH;
            gbcTxtLogradouro.insets = new Insets(0, 5, 5, 5);
            gbcTxtLogradouro.gridx = 1;
            gbcTxtLogradouro.gridy = 1;
            panelCepLogradouro.add(getTxtLogradouro(), gbcTxtLogradouro);
        }
        return panelCepLogradouro;
    }

    private JPanel getPanelBairroCidade() {
        if (panelBairroCidade == null) {
            panelBairroCidade = new JPanel();

            GridBagLayout gblPanelBairro = new GridBagLayout();
            gblPanelBairro.columnWidths = new int[]{245, 245};
            gblPanelBairro.rowHeights = new int[]{0, 0};
            gblPanelBairro.columnWeights = new double[]{0.0, 0.0};
            gblPanelBairro.rowWeights = new double[]{0.0, 0.0};
            panelBairroCidade.setLayout(gblPanelBairro);

            GridBagConstraints gbcLblBairro = new GridBagConstraints();
            gbcLblBairro.fill = GridBagConstraints.BOTH;
            gbcLblBairro.insets = new Insets(5, 5, 5, 5);
            gbcLblBairro.gridx = 0;
            gbcLblBairro.gridy = 0;
            panelBairroCidade.add(getLblBairro(), gbcLblBairro);

            GridBagConstraints gbcComboBairro = new GridBagConstraints();
            gbcComboBairro.fill = GridBagConstraints.BOTH;
            gbcComboBairro.insets = new Insets(0, 5, 5, 5);
            gbcComboBairro.gridx = 0;
            gbcComboBairro.gridy = 1;
            panelBairroCidade.add(getComboBairro(), gbcComboBairro);

            GridBagConstraints gbcLblCidade = new GridBagConstraints();
            gbcLblCidade.fill = GridBagConstraints.BOTH;
            gbcLblCidade.insets = new Insets(5, 5, 5, 5);
            gbcLblCidade.gridx = 1;
            gbcLblCidade.gridy = 0;
            panelBairroCidade.add(getLblCidade(), gbcLblCidade);

            GridBagConstraints gbcComboCidade = new GridBagConstraints();
            gbcComboCidade.fill = GridBagConstraints.BOTH;
            gbcComboCidade.insets = new Insets(0, 5, 5, 5);
            gbcComboCidade.gridx = 1;
            gbcComboCidade.gridy = 1;
            panelBairroCidade.add(getComboCidade(), gbcComboCidade);
        }
        return panelBairroCidade;
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

    private GridBagConstraints getGbcPanelCepLogradouro() {
        final GridBagConstraints gbcPanelCepLogradouro = new GridBagConstraints();
        gbcPanelCepLogradouro.insets = new Insets(0, 5, 0, 5);
        gbcPanelCepLogradouro.fill = GridBagConstraints.BOTH;
        gbcPanelCepLogradouro.gridx = 0;
        gbcPanelCepLogradouro.gridy = 1;

        return gbcPanelCepLogradouro;
    }

    private GridBagConstraints getGbcPanelBairroCidade() {
        final GridBagConstraints gbcPanelBairroCidade = new GridBagConstraints();
        gbcPanelBairroCidade.insets = new Insets(0, 5, 0, 5);
        gbcPanelBairroCidade.fill = GridBagConstraints.BOTH;
        gbcPanelBairroCidade.gridx = 0;
        gbcPanelBairroCidade.gridy = 2;

        return gbcPanelBairroCidade;
    }

    private GridBagConstraints getGbcPanelBotoes() {
        final GridBagConstraints gbcPanelBotoes = new GridBagConstraints();
        gbcPanelBotoes.fill = GridBagConstraints.BOTH;
        gbcPanelBotoes.gridx = 0;
        gbcPanelBotoes.gridy = 3;

        return gbcPanelBotoes;
    }

    public JTextField getTxtCodigo() {
        if (txtCodigo == null) {
            txtCodigo = new JTextFieldSomenteNumeros();
            txtCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
            txtCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
            txtCodigo.addActionListener(a -> controller.buscaEndereco());
        }
        return txtCodigo;
    }

    public JTextField getTxtCep() {
        if (txtCep == null) {
            try {
                txtCep = new JFormattedTextField(new MaskFormatter("##.###-###"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtCep;
    }

    public JTextField getTxtLogradouro() {
        if (txtLogradouro == null) {
            txtLogradouro = new JTextField();
            txtLogradouro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtLogradouro;
    }

    public JComboBox<String> getComboBairro() {
        if (comboBairro == null) {
            comboBairro = new JComboBox();
            comboBairro.setFont(new Font("Tahoma", Font.PLAIN, 11));
            comboBairro.setBackground(Cores.WHITE);
        }
        return comboBairro;
    }

    public JComboBox<String> getComboCidade() {
        if (comboCidade == null) {
            comboCidade = new JComboBox();
            comboCidade.setFont(new Font("Tahoma", Font.PLAIN, 11));
            comboCidade.setBackground(Cores.WHITE);
        }
        return comboCidade;
    }

    public JLabel getLblCodigo() {
        if (lblCodigo == null) {
            lblCodigo = new JLabel("Código:");
        }
        return lblCodigo;
    }

    public JLabel getLblCep() {
        if (lblCep == null) {
            lblCep = new JLabel("CEP:*");
        }
        return lblCep;
    }

    public JLabel getLblLogradouro() {
        if (lblLogradouro == null) {
            lblLogradouro = new JLabel("Logradouro:*");
        }
        return lblLogradouro;
    }

    public JLabel getLblBairro() {
        if (lblBairro == null) {
            lblBairro = new JLabel("Bairro:*");
        }
        return lblBairro;
    }

    public JLabel getLblCidade() {
        if (lblCidade == null) {
            lblCidade = new JLabel("Cidade:*");
        }
        return lblCidade;
    }

    public JButton getBtnSalvar() {
        if (btnSalvar == null) {
            btnSalvar = getBotaoSalvarPadrao(controller);
        }
        return btnSalvar;
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

    public JButton getBtnSair() {
        if (btnSair == null) {
            btnSair = getBotaoSairPadrao();
        }
        return btnSair;
    }
}
