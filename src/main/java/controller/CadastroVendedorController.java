package controller;

import dto.ColunaDto;
import model.bo.*;
import service.VendedorService;
import service.EnderecoService;
import view.CadastroVendedorView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.Formatador.*;
import static util.ValueUtil.*;
import static util.ValueUtil.isNull;

public class CadastroVendedorController extends CadastroController {

    private CadastroVendedorView view;
    private List<Vendedor> vendedores;
    private List<Endereco> enderecos;
    private VendedorService vendedorService;
    private EnderecoService enderecoService;

    public CadastroVendedorController(CadastroVendedorView view) {
        super(view);
        setView(view);
        getView().setVisible(true);
        preencheListas();
        verificaEnderecoCadastrado();
        populaCombos();
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
        getVendedorService().apagar(getVendedores().get(index));
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getVendedores().get(index));
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
        Vendedor vendedor = getVendedorService().buscar(codigo);
        if (isNotNull(vendedor) && isNotEmpty(vendedor.getId())) {
            preencheCamposTela(vendedor);
        } else {
            showMessageDialog(getView(), "Vendedor não encontrado.", "Atenção", ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaVendedor(new Vendedor());
            } else {
                verificaVendedorJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), "Atenção", ERROR_MESSAGE);
            getView().getTxtNome().requestFocus();
        }
    }

    private String getCpfSemMascara() {
        return getView().getTxtCpf().getText()
                .replaceAll("\\.", VAZIO)
                .replaceAll("-", VAZIO);
    }

    private String getFoneSemMascara() {
        return getView().getTxtFone().getText()
                .replaceAll("\\(", VAZIO)
                .replaceAll("\\)", VAZIO)
                .replaceAll("-", VAZIO);
    }

    private String getFone2SemMascara() {
        return getView().getTxtFone2().getText()
                .replaceAll("\\(", VAZIO)
                .replaceAll("\\)", VAZIO)
                .replaceAll("-", VAZIO);
    }


    private void verificaVendedorJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", "Atenção", ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Vendedor vendedor = getVendedorService().buscar(codigo);
        if (isNotNull(vendedor) && isNotEmpty(vendedor.getId())) {
            preencheSalvaVendedor(vendedor);
            return;
        }
        showMessageDialog(getView(), "Código não encontrado.\nPara cadastrar um novo Vendedor não preencha o campo de código.", "Atenção", ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getVendedores().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtNome().setText(VAZIO);
        getView().getTxtEmail().setText(VAZIO);
        getView().getTxtCpf().setText(VAZIO);
        getView().getTxtFone().setText(VAZIO);
        getView().getTxtFone2().setText(VAZIO);
        getView().getTxtComplemento().setText(VAZIO);
        getView().getComboEndereco().setSelectedIndex(ZERO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getVendedores().clear();
        getVendedores().addAll(getVendedorService().buscar());

        if (getVendedores().isEmpty()) {
            showMessageDialog(getView(), "Não há Vendedores para listar.", "Atenção", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Vendedores", getItens(), getColunas());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Vendedor vendedor : getVendedores()) {
            StringBuilder sb = new StringBuilder();
            sb.append(vendedor.getId()).append(SEPARADOR)
              .append(vendedor.getNome()).append(SEPARADOR)
              .append(formataCpf(vendedor.getNumCpf())).append(SEPARADOR)
              .append(formataTelefone(vendedor.getNumFone()));
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<ColunaDto> getColunas() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 40));
        colunas.add(new ColunaDto("Nome", ESQUERDA, 160));
        colunas.add(new ColunaDto("CPF", MEIO, 60));
        colunas.add(new ColunaDto("Fone", MEIO, 40));
        return colunas;
    }

    private void preencheCamposTela(Vendedor vendedor) {
        getView().getTxtCodigo().setText(String.valueOf(vendedor.getId()));
        getView().getTxtNome().setText(vendedor.getNome());
        getView().getTxtEmail().setText(vendedor.getNomEmail());
        getView().getTxtCpf().setText(vendedor.getNumCpf());
        getView().getTxtFone().setText(vendedor.getNumFone());
        getView().getTxtFone2().setText(vendedor.getNumFone2());
        getView().getTxtComissaoRecebimento().setValue(BigDecimal.valueOf(vendedor.getPrcComissaoRecebto()));
        getView().getTxtComissaoVenda().setValue(BigDecimal.valueOf(vendedor.getPrcComissaoVenda()));
        getView().getTxtComplemento().setText(vendedor.getNomComplemento());
        getView().getComboEndereco().setSelectedIndex(getEnderecos().indexOf(vendedor.getEndereco()) + 1);
        getView().getTxtNome().requestFocus();
    }

    private void preencheSalvaVendedor(Vendedor vendedor) throws Exception {
        vendedor.setNome(getView().getTxtNome().getText());
        vendedor.setNomEmail(getView().getTxtEmail().getText());
        vendedor.setNumCpf(getCpfSemMascara());
        vendedor.setNumFone(getFoneSemMascara());
        vendedor.setNumFone2(getFone2SemMascara());
        vendedor.setNomComplemento(getView().getTxtComplemento().getText());
        vendedor.setPrcComissaoRecebto(getView().getTxtComissaoRecebimento().getDoubleValue());
        vendedor.setPrcComissaoVenda(getView().getTxtComissaoVenda().getDoubleValue());
        vendedor.setEndereco(getEnderecoSelecionado());
        String msg = format("Vendedor %s com sucesso.", isEmpty(vendedor.getId()) ? "cadastrado" : "alterado");
        getVendedorService().createOrUpdate(vendedor);

        showMessageDialog(getView(), msg, "Atenção", INFORMATION_MESSAGE);
        limpaTela();
    }

    private Endereco getEnderecoSelecionado() {
        return getView().getComboEndereco().getSelectedIndex() <= ZERO ? null : getEnderecos().get(getView().getComboEndereco().getSelectedIndex() - 1);
    }

    public CadastroVendedorView getView() {
        return view;
    }

    public void setView(CadastroVendedorView view) {
        this.view = view;
    }

    public List<Vendedor> getVendedores() {
        if (isNull(vendedores)) {
            vendedores = new ArrayList<>();
        }
        return vendedores;
    }

    public List<Endereco> getEnderecos() {
        if (isNull(enderecos)) {
            enderecos = new ArrayList<>();
        }
        return enderecos;
    }

    private VendedorService getVendedorService() {
        return ofNullable(vendedorService).orElseGet(() -> vendedorService = new VendedorService());
    }

    private EnderecoService getEnderecoService() {
        return ofNullable(enderecoService).orElseGet(() -> enderecoService = new EnderecoService());
    }
}