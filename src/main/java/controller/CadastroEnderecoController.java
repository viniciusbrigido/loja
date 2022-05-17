package controller;

import dto.ColunaDto;
import lombok.Getter;
import lombok.Setter;
import model.bo.*;
import service.*;
import view.CadastroEnderecoView;
import java.util.*;
import static java.lang.Integer.*;
import static java.lang.String.format;
import static java.util.Optional.*;
import static javax.swing.JOptionPane.*;
import static util.Formatador.*;
import static util.ValueUtil.*;

public class CadastroEnderecoController extends CadastroController {

    @Getter @Setter
    private CadastroEnderecoView view;

    private List<Endereco> enderecos;
    private List<Cidade> cidades;
    private List<Bairro> bairros;
    private EnderecoService enderecoService;
    private CidadeService cidadeService;
    private BairroService bairroService;
    private ClienteService clienteService;
    private VendedorService vendedorService;
    private FornecedorService fornecedorService;

    public CadastroEnderecoController(CadastroEnderecoView view) {
        super(view);
        setView(view);
        getView().setVisible(true);
        preencheListas();
        verificaBairroCidadeCadastrados();
        populaCombos();
    }

    private void preencheListas() {
        getCidades().addAll(getCidadeService().buscar());
        getBairros().addAll(getBairroService().buscar());
    }

    private void verificaBairroCidadeCadastrados() {
        if (getBairros().isEmpty()) {
            showMessageDialog(getView(), "Não há Bairros cadastrados.", ATENCAO, INFORMATION_MESSAGE);
            getView().dispose();
            return;
        }
        if (getCidades().isEmpty()) {
            showMessageDialog(getView(), "Não há Cidades cadastradas.", ATENCAO, INFORMATION_MESSAGE);
            getView().dispose();
        }
    }

    private void populaCombos() {
        getView().getComboBairro().addItem("Selecione um Bairro");
        getBairros().forEach(b -> getView().getComboBairro().addItem(b.getNomBairro()));

        getView().getComboCidade().addItem("Selecione uma Cidade");
        getCidades().forEach(c -> getView().getComboCidade().addItem(c.getNomCidade()));
    }

    @Override
    public boolean excluiItem() {
        Endereco endereco = getEnderecos().get(index);
        if (getClienteService().isClienteCadastradoComEndereco(endereco.getId())) {
            showMessageDialog(getView(), "O item não pode ser excluído pois já há um ou mais Clientes cadastrados com esse Endereço.", ATENCAO, ERROR_MESSAGE);
            return false;
        }
        if (getFornecedorService().isFornecedorCadastradoComEndereco(endereco.getId())) {
            showMessageDialog(getView(), "O item não pode ser excluído pois já há um ou mais Fornecedores cadastrados com esse Endereço.", ATENCAO, ERROR_MESSAGE);
            return false;
        }
        if (getVendedorService().isVendedorCadastradoComEndereco(endereco.getId())) {
            showMessageDialog(getView(), "O item não pode ser excluído pois já há um ou mais Vendedores cadastrados com esse Endereço.", ATENCAO, ERROR_MESSAGE);
            return false;
        }
        getEnderecoService().apagar(endereco);
        return true;
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getEnderecos().get(index));
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
            showMessageDialog(getView(), "Código: Valor Inválido.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
            return;
        }

        Endereco endereco = getEnderecoService().buscar(codigo);
        if (isNotNull(endereco) && isNotEmpty(endereco.getId())) {
            preencheCamposTela(endereco);
        } else {
            showMessageDialog(getView(), "Cidade não encontrada.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaEndereco(new Endereco());
            } else {
                verificaEnderecoJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), ATENCAO, ERROR_MESSAGE);
            getView().getTxtLogradouro().requestFocus();
        }
    }

    private void verificaEnderecoJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Endereco endereco = getEnderecoService().buscar(codigo);
        if (isNotNull(endereco) && isNotEmpty(endereco.getId())) {
            preencheSalvaEndereco(endereco);
            return;
        }
        showMessageDialog(getView(), "Código não encontrado.\nPara cadastrar um novo Endereço não preencha o campo de código.", ATENCAO, ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getEnderecos().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtCep().setText(VAZIO);
        getView().getTxtLogradouro().setText(VAZIO);
        limpaBairro();
        limpaCidade();
        getView().getTxtCodigo().requestFocus();
    }

    private void limpaBairro() {
        getView().getComboBairro().setSelectedIndex(ZERO);
    }

    private void limpaCidade() {
        getView().getComboCidade().setSelectedIndex(ZERO);
    }

    @Override
    public void listaItens() {
        getEnderecos().clear();
        getEnderecos().addAll(getEnderecoService().buscar());

        if (getEnderecos().isEmpty()) {
            showMessageDialog(getView(), "Não há Endereços para listar.", ATENCAO, INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Endereços", getItens(), getColunas());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Endereco endereco : getEnderecos()) {
            StringBuilder sb = new StringBuilder();
            sb.append(endereco.getId()).append(SEPARADOR)
              .append(endereco.getNomLogradouro()).append(SEPARADOR)
              .append(formataCep(endereco.getNomCep())).append(SEPARADOR)
              .append(endereco.getBairro().getNomBairro()).append(SEPARADOR)
              .append(endereco.getCidade().getNomCidade());
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<ColunaDto> getColunas() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 30));
        colunas.add(new ColunaDto("Logradouro", ESQUERDA, 110));
        colunas.add(new ColunaDto("CEP", MEIO, 40));
        colunas.add(new ColunaDto("Bairro", ESQUERDA, 60));
        colunas.add(new ColunaDto("Cidade", ESQUERDA, 60));
        return colunas;
    }

    private void preencheCamposTela(Endereco endereco) {
        getView().getTxtCodigo().setText(String.valueOf(endereco.getId()));
        getView().getTxtCep().setText(formataCep(endereco.getNomCep()));
        getView().getTxtLogradouro().setText(endereco.getNomLogradouro());
        getView().getComboBairro().setSelectedIndex(getBairros().indexOf(endereco.getBairro()) + 1);
        getView().getComboCidade().setSelectedIndex(getCidades().indexOf(endereco.getCidade()) + 1);
        getView().getTxtLogradouro().requestFocus();
    }

    private void preencheSalvaEndereco(Endereco endereco) throws Exception {
        endereco.setNomLogradouro(getView().getTxtLogradouro().getText());
        endereco.setNomCep(getCepSemFormatacao());
        endereco.setBairro(getBairroSelecionado());
        endereco.setCidade(getCidadeSelecionada());
        String msg = format("Endereço %s com sucesso.", isEmpty(endereco.getId()) ? "cadastrado" : "alterado");
        getEnderecoService().createOrUpdate(endereco);

        showMessageDialog(getView(), msg, ATENCAO, INFORMATION_MESSAGE);
        limpaTela();
    }

    private Bairro getBairroSelecionado() {
        return getView().getComboBairro().getSelectedIndex() <= ZERO ? null : getBairros().get(getView().getComboBairro().getSelectedIndex() - 1);
    }

    private Cidade getCidadeSelecionada() {
        return getView().getComboCidade().getSelectedIndex() <= ZERO ? null : getCidades().get(getView().getComboCidade().getSelectedIndex() - 1);
    }

    private String getCepSemFormatacao() {
        return getView().getTxtCep().getText()
                .replaceAll("\\.", VAZIO)
                .replaceAll("-", VAZIO);
    }

    private List<Endereco> getEnderecos() {
        if (isNull(enderecos)) {
            enderecos = new ArrayList<>();
        }
        return enderecos;
    }

    private List<Cidade> getCidades() {
        if (isNull(cidades)) {
            cidades = new ArrayList<>();
        }
        return cidades;
    }

    private List<Bairro> getBairros() {
        if (isNull(bairros)) {
            bairros = new ArrayList<>();
        }
        return bairros;
    }

    private EnderecoService getEnderecoService() {
        return ofNullable(enderecoService).orElseGet(() -> enderecoService = new EnderecoService());
    }

    private CidadeService getCidadeService() {
        return ofNullable(cidadeService).orElseGet(() -> cidadeService = new CidadeService());
    }

    private BairroService getBairroService() {
        return ofNullable(bairroService).orElseGet(() -> bairroService = new BairroService());
    }

    private ClienteService getClienteService() {
        return ofNullable(clienteService).orElseGet(() -> clienteService = new ClienteService());
    }

    private VendedorService getVendedorService() {
        return ofNullable(vendedorService).orElseGet(() -> vendedorService = new VendedorService());
    }

    private FornecedorService getFornecedorService() {
        return ofNullable(fornecedorService).orElseGet(() -> fornecedorService = new FornecedorService());
    }
}
