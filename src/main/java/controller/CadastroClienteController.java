package controller;

import dto.ColunaDto;
import model.bo.Cliente;
import model.bo.Endereco;
import service.ClienteService;
import service.EnderecoService;
import view.CadastroClienteView;
import java.util.*;
import static java.lang.Integer.*;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.Formatador.*;
import static util.ValueUtil.*;

public class CadastroClienteController extends CadastroController {

    private CadastroClienteView view;
    private List<Cliente> clientes;
    private List<Endereco> enderecos;
    private ClienteService clienteService;
    private EnderecoService enderecoService;

    public CadastroClienteController() {
        super();
        setView(new CadastroClienteView(this));
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
            showMessageDialog(getView(), "N�o h� Endere�os cadastrados.", "Aten��o", INFORMATION_MESSAGE);
            getView().dispose();
        }
    }

    private void populaCombos() {
        getView().getComboEndereco().addItem("Selecione um Endere�o");
        getEnderecos().forEach(e -> getView().getComboEndereco().addItem(format("%s - %s", formataCep(e.getNomCep()), e.getNomLogradouro())));
    }

    @Override
    public void excluiItem() {
        getClienteService().apagar(getClientes().get(index));
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getClientes().get(index));
    }

    public void buscaCliente() {
        if (isEmpty(getView().getTxtCodigo().getText())) {
            limpaTela();
            return;
        }
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", "Aten��o", ERROR_MESSAGE);
            limpaTela();
            return;
        }

        Cliente cliente = getClienteService().buscar(codigo);
        if (isNotNull(cliente) && isNotEmpty(cliente.getId())) {
            preencheCamposTela(cliente);
        } else {
            showMessageDialog(getView(), "Cliente n�o encontrado.", "Aten��o", ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaCliente(new Cliente());
            } else {
                verificaClienteJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), "Aten��o", ERROR_MESSAGE);
            getView().getTxtNome().requestFocus();
        }
    }

    private String getCpfSemMascara() {
        return getView().getTxtCpf().getText()
                .replaceAll("\\.", VAZIO)
                .replaceAll("-", VAZIO);
    }

    private String getRgSemMascara() {
        return getView().getTxtRg().getText()
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

    private void verificaClienteJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", "Aten��o", ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Cliente cliente = getClienteService().buscar(codigo);
        if (isNotNull(cliente) && isNotEmpty(cliente.getId())) {
            preencheSalvaCliente(cliente);
            return;
        }
        showMessageDialog(getView(), "C�digo n�o encontrado.\nPara cadastrar um novo Cliente n�o preencha o campo de c�digo.", "Aten��o", ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getClientes().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtNome().setText(VAZIO);
        getView().getTxtEmail().setText(VAZIO);
        getView().getTxtCpf().setText(VAZIO);
        getView().getTxtRg().setText(VAZIO);
        getView().getTxtFone().setText(VAZIO);
        getView().getTxtFone2().setText(VAZIO);
        getView().getTxtComplemento().setText(VAZIO);
        getView().getTxtData().setText(VAZIO);
        getView().getComboEndereco().setSelectedIndex(ZERO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getClientes().clear();
        getClientes().addAll(getClienteService().buscar());

        if (getClientes().isEmpty()) {
            showMessageDialog(getView(), "N�o h� Clientes para listar.", "Aten��o", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Clientes", getItens(), getColunas());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Cliente cliente : getClientes()) {
            StringBuilder sb = new StringBuilder();
            sb.append(cliente.getId()).append(SEPARADOR)
              .append(cliente.getNome()).append(SEPARADOR)
              .append(formataCpf(cliente.getNumCpf())).append(SEPARADOR)
              .append(formataTelefone(cliente.getNumFone()));
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<ColunaDto> getColunas() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("C�digo", DIREITA, 40));
        colunas.add(new ColunaDto("Nome", ESQUERDA, 160));
        colunas.add(new ColunaDto("CPF", MEIO, 60));
        colunas.add(new ColunaDto("Fone", MEIO, 40));
        return colunas;
    }

    private void preencheCamposTela(Cliente cliente) {
        getView().getTxtCodigo().setText(String.valueOf(cliente.getId()));
        getView().getTxtNome().setText(cliente.getNome());
        getView().getTxtEmail().setText(cliente.getNomEmail());
        getView().getTxtCpf().setText(cliente.getNumCpf());
        getView().getTxtRg().setText(cliente.getNumRg());
        getView().getTxtFone().setText(cliente.getNumFone());
        getView().getTxtFone2().setText(cliente.getNumFone2());
        getView().getTxtComplemento().setText(cliente.getNomComplemento());
        getView().getTxtData().setText(formataDataPadrao(cliente.getDatNascimento()));
        getView().getComboEndereco().setSelectedIndex(getEnderecos().indexOf(cliente.getEndereco()) + 1);
        getView().getTxtNome().requestFocus();
    }

    private void preencheSalvaCliente(Cliente cliente) throws Exception {
        cliente.setNome(getView().getTxtNome().getText());
        cliente.setNomEmail(getView().getTxtEmail().getText());
        cliente.setNumCpf(getCpfSemMascara());
        cliente.setNumRg(getRgSemMascara());
        cliente.setNumFone(getFoneSemMascara());
        cliente.setNumFone2(getFone2SemMascara());
        cliente.setNomComplemento(getView().getTxtComplemento().getText());
        cliente.setEndereco(getEnderecoSelecionado());
        cliente.setDatNascimento(transformaDataFormatadaEmObjeto(getView().getTxtData().getText()));
        String msg = format("Cliente %s com sucesso.", isEmpty(cliente.getId()) ? "cadastrado" : "alterado");
        getClienteService().createOrUpdate(cliente);

        showMessageDialog(getView(), msg, "Aten��o", INFORMATION_MESSAGE);
        limpaTela();
    }

    private Endereco getEnderecoSelecionado() {
        return getView().getComboEndereco().getSelectedIndex() <= 0 ? null : getEnderecos().get(getView().getComboEndereco().getSelectedIndex() - 1);
    }

    public CadastroClienteView getView() {
        return view;
    }

    public void setView(CadastroClienteView view) {
        this.view = view;
    }

    public List<Cliente> getClientes() {
        if (isNull(clientes)) {
            clientes = new ArrayList<>();
        }
        return clientes;
    }

    public List<Endereco> getEnderecos() {
        if (isNull(enderecos)) {
            enderecos = new ArrayList<>();
        }
        return enderecos;
    }

    private ClienteService getClienteService() {
        return ofNullable(clienteService).orElseGet(() -> clienteService = new ClienteService());
    }

    private EnderecoService getEnderecoService() {
        return ofNullable(enderecoService).orElseGet(() -> enderecoService = new EnderecoService());
    }
}
