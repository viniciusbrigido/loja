package view;

import personalizado.JTextFieldLimitador;
import util.Cores;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class CadastroFornecedorView extends ControllerView {

    private JPanel panelCodigo;
    private JPanel panelNome;
    private JPanel panelRazaoSocial;
    private JPanel panelCnpjInscricaoEstadual;
    private JPanel panelEmail;
    private JPanel panelEnderecoComplemento;
    private JPanel panelBotaoCadastrarFone;
    private JPanel panelBotoes;

    private JLabel lblCodigo;
    private JLabel lblNome;
    private JLabel lblRazaoSocial;
    private JLabel lblEndereco;
    private JLabel lblComplemento;
    private JLabel lblEmail;
    private JLabel lblCnpj;
    private JLabel lblInscricaoEstadual;

    private JTextFieldLimitador txtNome;
    private JTextFieldLimitador txtRazaoSocial;
    private JTextFieldLimitador txtComplemento;
    private JTextFieldLimitador txtEmail;
    private JTextField txtCnpj;
    private JTextFieldLimitador txtInscricaoEstadual;
    private JComboBox<String> comboEndereco;

    private JButton btnCadastroFone;

    public CadastroFornecedorView() {
        setTitle("Cadastro de Fornecedores");
        initialize();
    }

    private void initialize() {
        setSize(new Dimension(580, 440));
        setPreferredSize(new Dimension(580, 440));
        add(getPanelCodigo(), getGbcPanelCodigo());
        add(getPanelNome(), getGbcPanelNome());
        add(getPanelRazaoSocial(), getGbcPanelRazaoSocial());
        add(getPanelCnpjInscricaoEstadual(), getGbcPanelCnpjInscricaoEstadual());
        add(getPanelEmail(), getGbcPanelEmail());
        add(getPanelEnderecoComplemento(), getGbcPanelEnderecoCommplemento());
        add(getPanelBotaoCadastrarFone(), getGbcPanelBotaoFone());
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

    private JPanel getPanelRazaoSocial() {
        if (panelRazaoSocial == null) {
            panelRazaoSocial = new JPanel();

            GridBagLayout gblPanelRazaoSocial = new GridBagLayout();
            gblPanelRazaoSocial.columnWidths = new int[]{560};
            gblPanelRazaoSocial.rowHeights = new int[]{0, 0};
            gblPanelRazaoSocial.columnWeights = new double[]{0.0};
            gblPanelRazaoSocial.rowWeights = new double[]{0.0, 0.0};
            panelRazaoSocial.setLayout(gblPanelRazaoSocial);

            GridBagConstraints gbcLblRazaoSocial = new GridBagConstraints();
            gbcLblRazaoSocial.fill = GridBagConstraints.BOTH;
            gbcLblRazaoSocial.insets = new Insets(5, 5, 5, 5);
            gbcLblRazaoSocial.gridx = 0;
            gbcLblRazaoSocial.gridy = 0;
            panelRazaoSocial.add(getLblRazaoSocial(), gbcLblRazaoSocial);

            GridBagConstraints gbcTxtRazaoSocial = new GridBagConstraints();
            gbcTxtRazaoSocial.fill = GridBagConstraints.BOTH;
            gbcTxtRazaoSocial.insets = new Insets(0, 5, 5, 5);
            gbcTxtRazaoSocial.gridx = 0;
            gbcTxtRazaoSocial.gridy = 1;
            panelRazaoSocial.add(getTxtRazaoSocial(), gbcTxtRazaoSocial);
        }
        return panelRazaoSocial;
    }

    private JPanel getPanelCnpjInscricaoEstadual() {
        if (panelCnpjInscricaoEstadual == null) {
            panelCnpjInscricaoEstadual = new JPanel();

            GridBagLayout gblPanelCnpjRgData = new GridBagLayout();
            gblPanelCnpjRgData.columnWidths = new int[]{280, 280};
            gblPanelCnpjRgData.rowHeights = new int[]{0, 0};
            gblPanelCnpjRgData.columnWeights = new double[]{0.0, 0.0};
            gblPanelCnpjRgData.rowWeights = new double[]{0.0, 0.0};
            panelCnpjInscricaoEstadual.setLayout(gblPanelCnpjRgData);

            GridBagConstraints gbcLblCnpj = new GridBagConstraints();
            gbcLblCnpj.fill = GridBagConstraints.BOTH;
            gbcLblCnpj.insets = new Insets(5, 5, 5, 5);
            gbcLblCnpj.gridx = 0;
            gbcLblCnpj.gridy = 0;
            panelCnpjInscricaoEstadual.add(getLblCnpj(), gbcLblCnpj);

            GridBagConstraints gbcTxtCnpj = new GridBagConstraints();
            gbcTxtCnpj.fill = GridBagConstraints.BOTH;
            gbcTxtCnpj.insets = new Insets(0, 5, 5, 5);
            gbcTxtCnpj.gridx = 0;
            gbcTxtCnpj.gridy = 1;
            panelCnpjInscricaoEstadual.add(getTxtCnpj(), gbcTxtCnpj);

            GridBagConstraints gbcLblInscricaoEstadual = new GridBagConstraints();
            gbcLblInscricaoEstadual.fill = GridBagConstraints.BOTH;
            gbcLblInscricaoEstadual.insets = new Insets(5, 5, 5, 5);
            gbcLblInscricaoEstadual.gridx = 1;
            gbcLblInscricaoEstadual.gridy = 0;
            panelCnpjInscricaoEstadual.add(getLblInscricaoEstadual(), gbcLblInscricaoEstadual);

            GridBagConstraints gbcTxtInscricaoEstadual = new GridBagConstraints();
            gbcTxtInscricaoEstadual.fill = GridBagConstraints.BOTH;
            gbcTxtInscricaoEstadual.insets = new Insets(0, 5, 5, 5);
            gbcTxtInscricaoEstadual.gridx = 1;
            gbcTxtInscricaoEstadual.gridy = 1;
            panelCnpjInscricaoEstadual.add(getTxtInscricaoEstadual(), gbcTxtInscricaoEstadual);
        }
        return panelCnpjInscricaoEstadual;
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

    private JPanel getPanelBotaoCadastrarFone() {
        if (panelBotaoCadastrarFone == null) {
            panelBotaoCadastrarFone = new JPanel();

            GridBagLayout gblPanelBotaoCadastrarFone = new GridBagLayout();
            gblPanelBotaoCadastrarFone.columnWidths = new int[]{0};
            gblPanelBotaoCadastrarFone.rowHeights = new int[]{0};
            gblPanelBotaoCadastrarFone.columnWeights = new double[]{0.0};
            gblPanelBotaoCadastrarFone.rowWeights = new double[]{0.0};
            panelBotaoCadastrarFone.setLayout(gblPanelBotaoCadastrarFone);

            GridBagConstraints gbcBtnCadastroFone = new GridBagConstraints();
            gbcBtnCadastroFone.insets = new Insets(10, 5, 5, 5);
            gbcBtnCadastroFone.gridx = 0;
            gbcBtnCadastroFone.gridy = 0;
            panelBotaoCadastrarFone.add(getBtnCadastroFone(), gbcBtnCadastroFone);
        }
        return panelBotaoCadastrarFone;
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

    private GridBagConstraints getGbcPanelRazaoSocial() {
        final GridBagConstraints gbcPanelazaoSocial = new GridBagConstraints();
        gbcPanelazaoSocial.insets = new Insets(0, 5, 0, 5);
        gbcPanelazaoSocial.fill = GridBagConstraints.BOTH;
        gbcPanelazaoSocial.gridx = 0;
        gbcPanelazaoSocial.gridy = 2;

        return gbcPanelazaoSocial;
    }

    private GridBagConstraints getGbcPanelCnpjInscricaoEstadual() {
        final GridBagConstraints gbcPanelCnpjInscricaoEstadual = new GridBagConstraints();
        gbcPanelCnpjInscricaoEstadual.insets = new Insets(0, 5, 0, 5);
        gbcPanelCnpjInscricaoEstadual.fill = GridBagConstraints.BOTH;
        gbcPanelCnpjInscricaoEstadual.gridx = 0;
        gbcPanelCnpjInscricaoEstadual.gridy = 3;

        return gbcPanelCnpjInscricaoEstadual;
    }

    private GridBagConstraints getGbcPanelEmail() {
        final GridBagConstraints gbcPanelEmail = new GridBagConstraints();
        gbcPanelEmail.insets = new Insets(0, 5, 0, 5);
        gbcPanelEmail.fill = GridBagConstraints.BOTH;
        gbcPanelEmail.gridx = 0;
        gbcPanelEmail.gridy = 4;

        return gbcPanelEmail;
    }

    private GridBagConstraints getGbcPanelEnderecoCommplemento() {
        final GridBagConstraints gbcPanelEnderecoCommplemento = new GridBagConstraints();
        gbcPanelEnderecoCommplemento.insets = new Insets(0, 5, 0, 5);
        gbcPanelEnderecoCommplemento.fill = GridBagConstraints.BOTH;
        gbcPanelEnderecoCommplemento.gridx = 0;
        gbcPanelEnderecoCommplemento.gridy = 5;

        return gbcPanelEnderecoCommplemento;
    }

    private GridBagConstraints getGbcPanelBotaoFone() {
        final GridBagConstraints gbcPanelBotaoFone = new GridBagConstraints();
        gbcPanelBotaoFone.fill = GridBagConstraints.BOTH;
        gbcPanelBotaoFone.gridx = 0;
        gbcPanelBotaoFone.gridy = 6;

        return gbcPanelBotaoFone;
    }

    private GridBagConstraints getGbcPanelBotoes() {
        final GridBagConstraints gbcPanelBotoes = new GridBagConstraints();
        gbcPanelBotoes.fill = GridBagConstraints.BOTH;
        gbcPanelBotoes.gridx = 0;
        gbcPanelBotoes.gridy = 7;

        return gbcPanelBotoes;
    }

    public JTextFieldLimitador getTxtNome() {
        if (txtNome == null) {
            txtNome = new JTextFieldLimitador(100);
            txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtNome;
    }

    public JTextFieldLimitador getTxtRazaoSocial() {
        if (txtRazaoSocial == null) {
            txtRazaoSocial = new JTextFieldLimitador(100);
            txtRazaoSocial.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtRazaoSocial;
    }

    public JTextFieldLimitador getTxtComplemento() {
        if (txtComplemento == null) {
            txtComplemento = new JTextFieldLimitador(45);
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

    public JTextField getTxtCnpj() {
        if (txtCnpj == null) {
            try {
                txtCnpj = new JFormattedTextField(new MaskFormatter("##.###.###/####-##"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            txtCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtCnpj;
    }

    public JTextFieldLimitador getTxtInscricaoEstadual() {
        if (txtInscricaoEstadual == null) {
            txtInscricaoEstadual = new JTextFieldLimitador(14);
            txtInscricaoEstadual.setFont(new Font("Tahoma", Font.PLAIN, 14));
        }
        return txtInscricaoEstadual;
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

    public JLabel getLblRazaoSocial() {
        if (lblRazaoSocial == null) {
            lblRazaoSocial = new JLabel("Razão Social:*");
        }
        return lblRazaoSocial;
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

    public JLabel getLblCnpj() {
        if (lblCnpj == null) {
            lblCnpj = new JLabel("CNPJ:*");
        }
        return lblCnpj;
    }

    public JLabel getLblInscricaoEstadual() {
        if (lblInscricaoEstadual == null) {
            lblInscricaoEstadual = new JLabel("Inscrição Estadual:*");
        }
        return lblInscricaoEstadual;
    }

    public JButton getBtnCadastroFone() {
        if (btnCadastroFone == null) {
            btnCadastroFone = new JButton("Cadastrar Telefone [SHIFT + F1]");
            btnCadastroFone.setIcon(new ImageIcon(getClass().getResource("/imagens/phone.png")));
        }
        return btnCadastroFone;
    }
}