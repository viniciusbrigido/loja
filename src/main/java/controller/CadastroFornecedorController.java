package controller;

import dto.ColunaDto;
import model.bo.Endereco;
import model.bo.Fornecedor;
import service.FornecedorService;
import service.EnderecoService;
import view.CadastroFoneFornecedorView;
import view.CadastroFornecedorView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_F1;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static javax.swing.KeyStroke.getKeyStroke;
import static util.Formatador.*;
import static util.ValueUtil.*;
import static util.ValueUtil.isNull;

public class CadastroFornecedorController extends CadastroController {

    private CadastroFornecedorView view;
    private List<Fornecedor> fornecedores;
    private List<Endereco> enderecos;
    private FornecedorService fornecedorService;
    private EnderecoService enderecoService;

    public CadastroFornecedorController(CadastroFornecedorView view) {
        super(view);
        setView(view);
        getView().setVisible(true);
        adicionaAcaoBotaoFoneFornecedor();
        preencheListas();
        verificaEnderecoCadastrado();
        populaCombos();
    }

    private void adicionaAcaoBotaoFoneFornecedor() {
        getView().getBtnCadastroFone().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F1, InputEvent.SHIFT_DOWN_MASK), EVENTO);
        getView().getBtnCadastroFone().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnCadastroFone().addActionListener(a -> cadastraFone());
        getView().getBtnCadastroFone().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastraFone();
            }
        });
    }

    private void preencheListas() {
        getEnderecos().addAll(getEnderecoService().buscar());
    }

    private void verificaEnderecoCadastrado() {
        if (getEnderecos().isEmpty()) {
            showMessageDialog(getView(), "Não há Endereços cadastrados.", "Atenção", INFORMATION_MESSAGE);
            getView().dispose();
        }
    }

    private void populaCombos() {
        getView().getComboEndereco().addItem("Selecione um Endereço");
        getEnderecos().forEach(e -> getView().getComboEndereco().addItem(format("%s - %s", formataCep(e.getNomCep()), e.getNomLogradouro())));
    }

    @Override
    public void excluiItem() {
        getFornecedorService().apagar(getFornecedores().get(index));
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getFornecedores().get(index));
    }

    @Override
    public void buscaPorCodigo() {
        if (isEmpty(getView().getTxtCodigo().getText())) {
            limpaTela();
            return;
        }
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", "Atenção", ERROR_MESSAGE);
            limpaTela();
            return;
        }

        Fornecedor fornecedor = getFornecedorService().buscar(codigo);
        if (isNotNull(fornecedor) && isNotEmpty(fornecedor.getId())) {
            preencheCamposTela(fornecedor);
        } else {
            showMessageDialog(getView(), "Fornecedor não encontrado.", "Atenção", ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaFornecedor(new Fornecedor());
            } else {
                verificaFornecedorJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), "Atenção", ERROR_MESSAGE);
            getView().getTxtNome().requestFocus();
        }
    }

    private void verificaFornecedorJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", "Atenção", ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Fornecedor fornecedor = getFornecedorService().buscar(codigo);
        if (isNotNull(fornecedor) && isNotEmpty(fornecedor.getId())) {
            preencheSalvaFornecedor(fornecedor);
            return;
        }
        showMessageDialog(getView(), "Código não encontrado.\nPara cadastrar um novo Fornecedor não preencha o campo de código.", "Atenção", ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getFornecedores().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtNome().setText(VAZIO);
        getView().getTxtRazaoSocial().setText(VAZIO);
        getView().getTxtEmail().setText(VAZIO);
        getView().getTxtCnpj().setText(VAZIO);
        getView().getTxtInscricaoEstadual().setText(VAZIO);
        getView().getTxtComplemento().setText(VAZIO);
        getView().getComboEndereco().setSelectedIndex(ZERO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getFornecedores().clear();
        getFornecedores().addAll(getFornecedorService().buscar());

        if (getFornecedores().isEmpty()) {
            showMessageDialog(getView(), "Não há Fornecedores para listar.", "Atenção", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Fornecedores", getItens(), getColunas());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Fornecedor fornecedor : getFornecedores()) {
            StringBuilder sb = new StringBuilder();
            sb.append(fornecedor.getId()).append(SEPARADOR)
              .append(fornecedor.getNomRazaoSocial()).append(SEPARADOR)
              .append(fornecedor.getNome()).append(SEPARADOR)
              .append(fornecedor.getNumCnpj()).append(SEPARADOR)
              .append(fornecedor.getEndereco().getNomCep());
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<ColunaDto> getColunas() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 40));
        colunas.add(new ColunaDto("Razão Social", ESQUERDA, 80));
        colunas.add(new ColunaDto("Nome", ESQUERDA, 80));
        colunas.add(new ColunaDto("CNPJ", MEIO, 60));
        colunas.add(new ColunaDto("CEP", MEIO, 40));
        return colunas;
    }

    private void preencheCamposTela(Fornecedor fornecedor) {
        getView().getTxtCodigo().setText(String.valueOf(fornecedor.getId()));
        getView().getTxtNome().setText(fornecedor.getNome());
        getView().getTxtRazaoSocial().setText(fornecedor.getNomRazaoSocial());
        getView().getTxtEmail().setText(fornecedor.getNomEmail());
        getView().getTxtComplemento().setText(fornecedor.getNomComplemento());
        getView().getComboEndereco().setSelectedIndex(getEnderecos().indexOf(fornecedor.getEndereco()) + 1);
        getView().getTxtCnpj().setText(fornecedor.getNumCnpj());
        getView().getTxtInscricaoEstadual().setText(fornecedor.getNumInscEstadual());
        getView().getTxtNome().requestFocus();
    }

    private void preencheSalvaFornecedor(Fornecedor fornecedor) throws Exception {
        fornecedor.setNome(getView().getTxtNome().getText());
        fornecedor.setNomRazaoSocial(getView().getTxtRazaoSocial().getText());
        fornecedor.setNomEmail(getView().getTxtEmail().getText());
        fornecedor.setNomComplemento(getView().getTxtComplemento().getText());
        fornecedor.setEndereco(getEnderecoSelecionado());
        fornecedor.setNumCnpj(getView().getTxtCnpj().getText());
        fornecedor.setNumInscEstadual(getView().getTxtInscricaoEstadual().getText());
        String msg = format("Fornecedor %s com sucesso.", isEmpty(fornecedor.getId()) ? "cadastrado" : "alterado");
        getFornecedorService().createOrUpdate(fornecedor);

        showMessageDialog(getView(), msg, "Atenção", INFORMATION_MESSAGE);
        limpaTela();
    }

    private Endereco getEnderecoSelecionado() {
        return getView().getComboEndereco().getSelectedIndex() <= ZERO ? null : getEnderecos().get(getView().getComboEndereco().getSelectedIndex() - 1);
    }

    public void cadastraFone() {
        if (isEmpty(getView().getTxtCodigo().getText())) {
            showMessageDialog(getView(), "Filtre um Fornecedor para cadastrar os telefones.", "Atenção", ERROR_MESSAGE);
            getView().getTxtCodigo().requestFocus();
            return;
        }

        Fornecedor fornecedor = getFornecedorService().buscar(Integer.parseInt(getView().getTxtCodigo().getText()));
        if (isNull(fornecedor) || isEmpty(fornecedor.getId())) {
            showMessageDialog(getView(), "Fornecedor não encontrado.", "Atenção", ERROR_MESSAGE);
            limpaTela();
            return;
        }
        new CadastroFoneFornecedorController(new CadastroFoneFornecedorView(), fornecedor);
    }

    public CadastroFornecedorView getView() {
        return view;
    }

    public void setView(CadastroFornecedorView view) {
        this.view = view;
    }

    public List<Fornecedor> getFornecedores() {
        if (isNull(fornecedores)) {
            fornecedores = new ArrayList<>();
        }
        return fornecedores;
    }

    public List<Endereco> getEnderecos() {
        if (isNull(enderecos)) {
            enderecos = new ArrayList<>();
        }
        return enderecos;
    }

    private FornecedorService getFornecedorService() {
        return ofNullable(fornecedorService).orElseGet(() -> fornecedorService = new FornecedorService());
    }

    private EnderecoService getEnderecoService() {
        return ofNullable(enderecoService).orElseGet(() -> enderecoService = new EnderecoService());
    }
}
