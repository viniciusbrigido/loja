package view;

import personalizado.JTextFieldLimitador;
import util.Cores;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class CadastroClienteView extends ControllerView {

    private JPanel panelCodigo;
    private JPanel panelNome;
    private JPanel panelCpfRgData;
    private JPanel panelFones;
    private JPanel panelEmail;
    private JPanel panelEnderecoComplemento;
    private JPanel panelBotoes;

    private JLabel lblCodigo;
    private JLabel lblNome;
    private JLabel lblEndereco;
    private JLabel lblComplemento;
    private JLabel lblEmail;
    private JLabel lblCpf;
    private JLabel lblRg;
    private JLabel lblData;
    private JLabel lblFone;
    private JLabel lblFone2;

    private JTextFieldLimitador txtNome;
    private JTextFieldLimitador txtComplemento;
    private JTextFieldLimitador txtEmail;
    private JTextField txtCpf;
    private JTextField txtRg;
    private JTextField txtFone;
    private JTextField txtFone2;
    private JTextField txtData;
    private JComboBox<String> comboEndereco;


    public CadastroClienteView() {
        setTitle("Cadastro de Clientes");
        initialize();
    }

    private void initialize() {
        setSize(new Dimension(580, 400));
        setPreferredSize(new Dimension(580, 400));
        add(getPanelCodigo(), getGbcPanelCodigo());
        add(getPanelNome(), getGbcPanelNome());
        add(getPanelCpfRgData(), getGbcPanelCpfRgData());
        add(getPanelFones(), getGbcPanelFones());
        add(getPanelEmail(), getGbcPanelEmail());
        add(getPanelEnderecoComplemento(), getGbcPanelEnderecoComplemento());
        add(getPanelBotoes(), getGbcPanelBotoes());
    }

    private JPanel getPanelCodigo() {
        if (panelCodigo == null) {
            panelCodigo = new JPanel();

            GridBagLayout gblPanelCodigo = new GridBagLayout();
            gblPanelCodigo.columnWidths = new int[]{100, 460};
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

    private JPanel getPanelNome() {
        if (panelNome == null) {
            panelNome = new JPanel();

            GridBagLayout gblPanelNome = new GridBagLayout();
            gblPanelNome.columnWidths = new int[]{560};
            gblPanelNome.rowHeights = new int[]{0, 0};
            gblPanelNome.columnWeights = new double[]{0.0};
            gblPanelNome.rowWeights = new double[]{0.0, 0.0};
            panelNome.setLayout(gblPanelNome);

            GridBagConstraints gbcLblNome = new GridBagConstraints();
            gbcLblNome.fill = GridBagConstraints.BOTH;
            gbcLblNome.insets = new Insets(5, 5, 5, 5);
            gbcLblNome.gridx = 0;
            gbcLblNome.gridy = 0;
            panelNome.add(getLblNome(), gbcLblNome);

            GridBagConstraints gbcTxtNome = new GridBagConstraints();
            gbcTxtNome.fill = GridBagConstraints.BOTH;
            gbcTxtNome.insets = new Insets(0, 5, 5, 5);
            gbcTxtNome.gridx = 0;
            gbcTxtNome.gridy = 1;
            panelNome.add(getTxtNome(), gbcTxtNome);
        }
        return panelNome;
    }

    private JPanel getPanelCpfRgData() {
        if (panelCpfRgData == null) {
            panelCpfRgData = new JPanel();

            GridBagLayout gblPanelCpfRgData = new GridBagLayout();
            gblPanelCpfRgData.columnWidths = new int[]{200, 200, 160};
            gblPanelCpfRgData.rowHeights = new int[]{0, 0};
            gblPanelCpfRgData.columnWeights = new double[]{0.0, 0.0, 0.0};
            gblPanelCpfRgData.rowWeights = new double[]{0.0, 0.0, 0.0};
            panelCpfRgData.setLayout(gblPanelCpfRgData);

            GridBagConstraints gbcLblCpf = new GridBagConstraints();
            gbcLblCpf.fill = GridBagConstraints.BOTH;
            gbcLblCpf.insets = new Insets(5, 5, 5, 5);
            gbcLblCpf.gridx = 0;
            gbcLblCpf.gridy = 0;
            panelCpfRgData.add(getLblCpf(), gbcLblCpf);

            GridBagConstraints gbcTxtCpf = new GridBagConstraints();
            gbcTxtCpf.fill = GridBagConstraints.BOTH;
            gbcTxtCpf.insets = new Insets(0, 5, 5, 5);
            gbcTxtCpf.gridx = 0;
            gbcTxtCpf.gridy = 1;
            panelCpfRgData.add(getTxtCpf(), gbcTxtCpf);

            GridBagConstraints gbcLblRg = new GridBagConstraints();
            gbcLblRg.fill = GridBagConstraints.BOTH;
            gbcLblRg.insets = new Insets(5, 5, 5, 5);
            gbcLblRg.gridx = 1;
            gbcLblRg.gridy = 0;
            panelCpfRgData.add(getLblRg(), gbcLblRg);

            GridBagConstraints gbcTxtRg = new GridBagConstraints();
            gbcTxtRg.fill = GridBagConstraints.BOTH;
            gbcTxtRg.insets = new Insets(0, 5, 5, 5);
            gbcTxtRg.gridx = 1;
            gbcTxtRg.gridy = 1;
            panelCpfRgData.add(getTxtRg(), gbcTxtRg);

            GridBagConstraints gbcLblData = new GridBagConstraints();
            gbcLblData.fill = GridBagConstraints.BOTH;
            gbcLblData.insets = new Insets(5, 5, 5, 5);
            gbcLblData.gridx = 2;
            gbcLblData.gridy = 0;
            panelCpfRgData.add(getLblData(), gbcLblData);

            GridBagConstraints gbcTxtData = new GridBagConstraints();
            gbcTxtData.fill = GridBagConstraints.BOTH;
            gbcTxtData.insets = new Insets(0, 5, 5, 5);
            gbcTxtData.gridx = 2;
            gbcTxtData.gridy = 1;
            panelCpfRgData.add(getTxtData(), gbcTxtData);
        }
        return panelCpfRgData;
    }

    private JPanel getPanelFones() {
        if (panelFones == null) {
            panelFones = new JPanel();

            GridBagLayout gblPanelFones = new GridBagLayout();
            gblPanelFones.columnWidths = new int[]{280, 280};
            gblPanelFones.rowHeights = new int[]{0, 0};
            gblPanelFones.columnWeights = new double[]{0.0, 0.0};
            gblPanelFones.rowWeights = new double[]{0.0, 0.0};
            panelFones.setLayout(gblPanelFones);

            GridBagConstraints gbcLblFone = new GridBagConstraints();
            gbcLblFone.fill = GridBagConstraints.BOTH;
            gbcLblFone.insets = new Insets(5, 5, 5, 5);
            gbcLblFone.gridx = 0;
            gbcLblFone.gridy = 0;
            panelFones.add(getLblFone(), gbcLblFone);

            GridBagConstraints gbcTxtFone = new GridBagConstraints();
            gbcTxtFone.fill = GridBagConstraints.BOTH;
            gbcTxtFone.insets = new Insets(0, 5, 5, 5);
            gbcTxtFone.gridx = 0;
            gbcTxtFone.gridy = 1;
            panelFones.add(getTxtFone(), gbcTxtFone);

            GridBagConstraints gbcLblFone2 = new GridBagConstraints();
            gbcLblFone2.fill = GridBagConstraints.BOTH;
            gbcLblFone2.insets = new Insets(5, 5, 5, 5);
            gbcLblFone2.gridx = 1;
            gbcLblFone2.gridy = 0;
            panelFones.add(getLblFone2(), gbcLblFone2);

            GridBagConstraints gbcTxtFone2 = new GridBagConstraints();
            gbcTxtFone2.fill = GridBagConstraints.BOTH;
            gbcTxtFone2.insets = new Insets(0, 5, 5, 5);
            gbcTxtFone2.gridx = 1;
            gbcTxtFone2.gridy = 1;
            panelFones.add(getTxtFone2(), gbcTxtFone2);
        }
        return panelFones;
    }

    private JPanel getPanelEmail() {
        if (panelEmail == null) {
            panelEmail = new JPanel();

            GridBagLayout gblPanelEmail = new GridBagLayout();
            gblPanelEmail.columnWidths = new int[]{560};
            gblPanelEmail.rowHeights = new int[]{0, 0};
            gblPanelEmail.columnWeights = new double[]{0.0};
            gblPanelEmail.rowWeights = new double[]{0.0, 0.0};
            panelEmail.setLayout(gblPanelEmail);

            GridBagConstraints gbcLblEmail = new GridBagConstraints();
            gbcLblEmail.fill = GridBagConstraints.BOTH;
            gbcLblEmail.insets = new Insets(5, 5, 5, 5);
            gbcLblEmail.gridx = 0;
            gbcLblEmail.gridy = 0;
            panelEmail.add(getLblEmail(), gbcLblEmail);

            GridBagConstraints gbcTxtEmail = new GridBagConstraints();
            gbcTxtEmail.fill = GridBagConstraints.BOTH;
            gbcTxtEmail.insets = new Insets(0, 5, 5, 5);
            gbcTxtEmail.gridx = 0;
            gbcTxtEmail.gridy = 1;
            panelEmail.add(getTxtEmail(), gbcTxtEmail);
        }
        return panelEmail;
    }

    private JPanel getPanelEnderecoComplemento() {
        if (panelEnderecoComplemento == null) {
            panelEnderecoComplemento = new JPanel();

            GridBagLayout gblPanelEnderecoComplemento = new GridBagLayout();
            gblPanelEnderecoComplemento.columnWidths = new int[]{200, 360};
            gblPanelEnderecoComplemento.rowHeights = new int[]{0, 0};
            gblPanelEnderecoComplemento.columnWeights = new double[]{0.0, 0.0};
            gblPanelEnderecoComplemento.rowWeights = new double[]{0.0, 0.0};
            panelEnderecoComplemento.setLayout(gblPanelEnderecoComplemento);

            GridBagConstraints gbcLblEndereco = new GridBagConstraints();
            gbcLblEndereco.fill = GridBagConstraints.BOTH;
            gbcLblEndereco.insets = new Insets(5, 5, 5, 5);
            gbcLblEndereco.gridx = 0;
            gbcLblEndereco.gridy = 0;
            panelEnderecoComplemento.add(getLblEndereco(), gbcLblEndereco);

            GridBagConstraints gbcComboEndereco = new GridBagConstraints();
            gbcComboEndereco.fill = GridBagConstraints.BOTH;
            gbcComboEndereco.insets = new Insets(0, 5, 5, 5);
            gbcComboEndereco.gridx = 0;
            gbcComboEndereco.gridy = 1;
            panelEnderecoComplemento.add(getComboEndereco(), gbcComboEndereco);

            GridBagConstraints gbcLblComplemento = new GridBagConstraints();
            gbcLblComplemento.fill = GridBagConstraints.BOTH;
            gbcLblComplemento.insets = new Insets(5, 5, 5, 5);
            gbcLblComplemento.gridx = 1;
            gbcLblComplemento.gridy = 0;
            panelEnderecoComplemento.add(getLblComplemento(), gbcLblComplemento);

            GridBagConstraints gbcTxtComplemento = new GridBagConstraints();
            gbcTxtComplemento.fill = GridBagConstraints.BOTH;
            gbcTxtComplemento.insets = new Insets(0, 5, 5, 5);
            gbcTxtComplemento.gridx = 1;
            gbcTxtComplemento.gridy = 1;
            panelEnderecoComplemento.add(getTxtComplemento(), gbcTxtComplemento);
        }
        return panelEnderecoComplemento;
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

    private GridBagConstraints getGbcPanelNome() {
        final GridBagConstraints gbcPanelNome = new GridBagConstraints();
        gbcPanelNome.insets = new Insets(0, 5, 0, 5);
        gbcPanelNome.fill = GridBagConstraints.BOTH;
        gbcPanelNome.gridx = 0;
        gbcPanelNome.gridy = 1;

        return gbcPanelNome;
    }

    private GridBagConstraints getGbcPanelCpfRgData() {
        final GridBagConstraints gbcPanelCpfRgData = new GridBagConstraints();
        gbcPanelCpfRgData.insets = new Insets(0, 5, 0, 5);
        gbcPanelCpfRgData.fill = GridBagConstraints.BOTH;
        gbcPanelCpfRgData.gridx = 0;
        gbcPanelCpfRgData.gridy = 2;

        return gbcPanelCpfRgData;
    }

    private GridBagConstraints getGbcPanelFones() {
        final GridBagConstraints gbcPanelFones = new GridBagConstraints();
        gbcPanelFones.insets = new Insets(0, 5, 0, 5);
        gbcPanelFones.fill = GridBagConstraints.BOTH;
        gbcPanelFones.gridx = 0;
        gbcPanelFones.gridy = 3;

        return gbcPanelFones;
    }

    private GridBagConstraints getGbcPanelEmail() {
        final GridBagConstraints gbcPanelEmail = new GridBagConstraints();
        gbcPanelEmail.insets = new Insets(0, 5, 0, 5);
        gbcPanelEmail.fill = GridBagConstraints.BOTH;
        gbcPanelEmail.gridx = 0;
        gbcPanelEmail.gridy = 4;

        return gbcPanelEmail;
    }

    private GridBagConstraints getGbcPanelEnderecoComplemento() {
        final GridBagConstraints gbcPanelEnderecoCommplemento = new GridBagConstraints();
        gbcPanelEnderecoCommplemento.insets = new Insets(0, 5, 0, 5);
        gbcPanelEnderecoCommplemento.fill = GridBagConstraints.BOTH;
        gbcPanelEnderecoCommplemento.gridx = 0;
        gbcPanelEnderecoCommplemento.gridy = 5;

        return gbcPanelEnderecoCommplemento;
    }

    private GridBagConstraints getGbcPanelBotoes() {
        final GridBagConstraints gbcPanelBotoes = new GridBagConstraints();
        gbcPanelBotoes.fill = GridBagConstraints.BOTH;
        gbcPanelBotoes.gridx = 0;
        gbcPanelBotoes.gridy = 6;

        return gbcPanelBotoes;
    }

    public JTextFieldLimitador getTxtNome() {
        if (txtNome == null) {
            txtNome = new JTextFieldLimitador(100);
            txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtNome;
    }

    public JTextFieldLimitador getTxtComplemento() {
        if (txtComplemento == null) {
            txtComplemento = new JTextFieldLimitador(100);
            txtComplemento.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtComplemento;
    }

    public JTextFieldLimitador getTxtEmail() {
        if (txtEmail == null) {
            txtEmail = new JTextFieldLimitador(100);
            txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtEmail;
    }

    public JTextField getTxtCpf() {
        if (txtCpf == null) {
            try {
                txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtCpf;
    }

    public JTextField getTxtRg() {
        if (txtRg == null) {
            try {
                txtRg = new JFormattedTextField(new MaskFormatter("##.###.###-#"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtRg.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtRg;
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

    public JTextField getTxtFone2() {
        if (txtFone2 == null) {
            try {
                txtFone2 = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtFone2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtFone2;
    }

    public JTextField getTxtData() {
        if (txtData == null) {
            try {
                txtData = new JFormattedTextField(new MaskFormatter("##/##/####"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtData.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtData;
    }

    public JComboBox<String> getComboEndereco() {
        if (comboEndereco == null) {
            comboEndereco = new JComboBox();
            comboEndereco.setFont(new Font("Tahoma", Font.PLAIN, 11));
            comboEndereco.setBackground(Cores.WHITE);
        }
        return comboEndereco;
    }

    public JLabel getLblCodigo() {
        if (lblCodigo == null) {
            lblCodigo = new JLabel("Código:");
        }
        return lblCodigo;
    }

    public JLabel getLblNome() {
        if (lblNome == null) {
            lblNome = new JLabel("Nome:*");
        }
        return lblNome;
    }

    public JLabel getLblComplemento() {
        if (lblComplemento == null) {
            lblComplemento = new JLabel("Complemento:*");
        }
        return lblComplemento;
    }

    public JLabel getLblEndereco() {
        if (lblEndereco == null) {
            lblEndereco = new JLabel("Endereço:*");
        }
        return lblEndereco;
    }

    public JLabel getLblEmail() {
        if (lblEmail == null) {
            lblEmail = new JLabel("E-mail:");
        }
        return lblEmail;
    }

    public JLabel getLblCpf() {
        if (lblCpf == null) {
            lblCpf = new JLabel("CPF:*");
        }
        return lblCpf;
    }

    public JLabel getLblRg() {
        if (lblRg == null) {
            lblRg = new JLabel("RG:");
        }
        return lblRg;
    }

    public JLabel getLblData() {
        if (lblData == null) {
            lblData = new JLabel("Data Nascimento:*");
        }
        return lblData;
    }

    public JLabel getLblFone() {
        if (lblFone == null) {
            lblFone = new JLabel("Fone:*");
        }
        return lblFone;
    }

    public JLabel getLblFone2() {
        if (lblFone2 == null) {
            lblFone2 = new JLabel("Fone 2:");
        }
        return lblFone2;
    }
}