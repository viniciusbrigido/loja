package controller;

import dto.ColunaDto;
import model.bo.*;
import service.*;
import view.FinalizacaoView;
import java.sql.Time;
import java.util.*;
import static java.lang.Integer.*;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.Formatador.*;
import static util.ValueUtil.*;

public class FinalizacaoController extends Controller {

    private List<ItemVenda> itens;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;
    private List<CondicaoPagamento> condicoes;

    private VendaService vendaService;
    private ItemVendaService itemVendaService;
    private VendedorService vendedorService;
    private ClienteService clienteService;
    private CondicaoPagamentoService condicaoPagamentoService;
    private CaracteristicaProdutoService caracteristicaProdutoService;

    private VendaController vendaController;
    private Vendedor vendedor;
    private Cliente cliente;
    private CondicaoPagamento condicao;

    private FinalizacaoView view;

    public FinalizacaoController(VendaController vendaController) {
        this.vendaController = vendaController;
        itens = vendaController.getItens();
        setView(new FinalizacaoView(this));
        getView().setVisible(true);
        preencheCamposTela();
        setaFocoInicial();
    }

    private void preencheCamposTela() {
        if (isNotNull(vendaController.getVendedor())) {
            preencheVendedor(vendaController.getVendedor());
        }
        if (isNotNull(vendaController.getCliente())) {
            preencheCliente(vendaController.getCliente());
        }
        if (isNotNull(vendaController.getCondicao())) {
            preencheCondicao(vendaController.getCondicao());
        }
    }

    private void setaFocoInicial() {
        if (isNull(vendedor)) {
            getView().getTxtCodVendedor().requestFocus();
        } else if (isNull(cliente)) {
            getView().getTxtCodCliente().requestFocus();
        } else if (isNull(condicao)) {
            getView().getTxtCodCondicao().requestFocus();
        } else {
            getView().getBtnFinalizar().requestFocus();
        }
    }

    private void limpaVendedor() {
        getView().getTxtCodVendedor().setText(VAZIO);
        getView().getTxtNomVendedor().setText(VAZIO);
        vendedor = null;
    }

    private void limpaVendedorSetaFoco() {
        limpaVendedor();
        getView().getTxtCodVendedor().requestFocus();
    }

    private void limpaCliente() {
        getView().getTxtCodCliente().setText(VAZIO);
        getView().getTxtNomCliente().setText(VAZIO);
        cliente = null;
    }

    private void limpaClienteSetaFoco() {
        limpaCliente();
        getView().getTxtCodCliente().requestFocus();
    }

    private void limpaCondicao() {
        getView().getTxtCodCondicao().setText(VAZIO);
        getView().getTxtNomCondicao().setText(VAZIO);
        condicao = null;
    }

    private void limpaCondicaoSetaFoco() {
        limpaCondicao();
        getView().getTxtCodCondicao().requestFocus();
    }

    public void limpaTela() {
        limpaCliente();
        limpaCondicao();
        limpaVendedorSetaFoco();
    }

    public void sairTela() {
        getView().dispose();
        vendaController.setVendedor(vendedor);
        vendaController.setCliente(cliente);
        vendaController.setCondicao(condicao);
        vendaController.limpaCamposProduto();
    }

    private void preencheVendedor(Vendedor vendedor) {
        getView().getTxtCodVendedor().setText(String.valueOf(vendedor.getId()));
        getView().getTxtNomVendedor().setText(vendedor.getNomVendedor());
        this.vendedor = vendedor;
    }

    private void preencheCliente(Cliente cliente) {
        getView().getTxtCodCliente().setText(String.valueOf(cliente.getId()));
        getView().getTxtNomCliente().setText(cliente.getNomCliente());
        this.cliente = cliente;
    }

    private void preencheCondicao(CondicaoPagamento condicao) {
        getView().getTxtCodCondicao().setText(String.valueOf(condicao.getId()));
        getView().getTxtNomCondicao().setText(condicao.getNomCondicaoPagamento());
        this.condicao = condicao;
    }

    private void preencheVendedorSetaFoco(Vendedor vendedor) {
        preencheVendedor(vendedor);
        getView().getTxtCodCliente().requestFocus();
    }

    private void preencheClienteSetaFoco(Cliente cliente) {
        preencheCliente(cliente);
        getView().getTxtCodCondicao().requestFocus();
    }

    private void preencheCondicaoSetaFoco(CondicaoPagamento condicao) {
        preencheCondicao(condicao);
        getView().getBtnFinalizar().requestFocus();
    }

    public void preencheVendedor(Integer index) {
        preencheVendedorSetaFoco(getVendedores().get(index));
    }

    public void preencheCliente(Integer index) {
        preencheClienteSetaFoco(getClientes().get(index));
    }

    public void preencheCondicao(Integer index) {
        preencheCondicaoSetaFoco(getCondicoes().get(index));
    }

    public void buscaVendedor() {
        if (isEmpty(getView().getTxtCodVendedor().getText())) {
            limpaVendedor();
            return;
        }
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodVendedor().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", "Aten��o", ERROR_MESSAGE);
            limpaVendedorSetaFoco();
            return;
        }
        Vendedor vendedor = getVendedorService().buscar(codigo);
        if (isNotNull(vendedor) && isNotEmpty(vendedor.getId())) {
            preencheVendedorSetaFoco(vendedor);
        } else {
            showMessageDialog(getView(), "Vendedor n�o encontrado.", "Aten��o", ERROR_MESSAGE);
            limpaVendedorSetaFoco();
        }
    }

    public void buscaCliente() {
        if (isEmpty(getView().getTxtCodCliente().getText())) {
            limpaCliente();
            return;
        }
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodCliente().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", "Aten��o", ERROR_MESSAGE);
            limpaClienteSetaFoco();
            return;
        }
        Cliente cliente = getClienteService().buscar(codigo);
        if (isNotNull(cliente) && isNotEmpty(cliente.getId())) {
            preencheClienteSetaFoco(cliente);
        } else {
            showMessageDialog(getView(), "Cliente n�o encontrado.", "Aten��o", ERROR_MESSAGE);
            limpaClienteSetaFoco();
        }
    }

    public void buscaCondicao() {
        if (isEmpty(getView().getTxtCodCondicao().getText())) {
            limpaCondicao();
            return;
        }
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodCondicao().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", "Aten��o", ERROR_MESSAGE);
            limpaCondicaoSetaFoco();
            return;
        }
        CondicaoPagamento condicao = getCondicaoPagamentoService().buscar(codigo);
        if (isNotNull(condicao) && isNotEmpty(condicao.getId())) {
            preencheCondicaoSetaFoco(condicao);
        } else {
            showMessageDialog(getView(), "Condi��o de Pagamento n�o encontrada.", "Aten��o", ERROR_MESSAGE);
            limpaCondicaoSetaFoco();
        }
    }

    public void listaVendedores() {
        getVendedores().clear();
        getVendedores().addAll(getVendedorService().buscar());

        if (getVendedores().isEmpty()) {
            showMessageDialog(getView(), "N�o h� Vendedores para listar.", "Aten��o", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Vendedores", getItensVendedor(), getColunasVendedor(), TIPO_LISTAGEM_VENDEDOR);
    }

    public void listaClientes() {
        getClientes().clear();
        getClientes().addAll(getClienteService().buscar());

        if (getClientes().isEmpty()) {
            showMessageDialog(getView(), "N�o h� Clientes para listar.", "Aten��o", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Clientes", getItensCliente(), getColunasCliente(), TIPO_LISTAGEM_CLIENTE);
    }

    public void listaCondicoes() {
        getCondicoes().clear();
        getCondicoes().addAll(getCondicaoPagamentoService().buscar());

        if (getCondicoes().isEmpty()) {
            showMessageDialog(getView(), "N�o h� Condi��es de Pagamento para listar.", "Aten��o", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Condi��es de Pagamento", getItensConsicaoPagamento(), getColunasCondicaoPagamento(), TIPO_LISTAGEM_CONDICAO);
    }

    private List<String> getItensVendedor() {
        List<String> itens = new ArrayList<>();
        for (Vendedor vendedor : getVendedores()) {
            StringBuilder sb = new StringBuilder();
            sb.append(vendedor.getId()).append(SEPARADOR)
              .append(vendedor.getNomVendedor()).append(SEPARADOR)
              .append(formataCpf(vendedor.getNumCpf())).append(SEPARADOR)
              .append(formataTelefone(vendedor.getNumFone()));
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<String> getItensCliente() {
        List<String> itens = new ArrayList<>();
        for (Cliente cliente : getClientes()) {
            StringBuilder sb = new StringBuilder();
            sb.append(cliente.getId()).append(SEPARADOR)
              .append(cliente.getNomCliente()).append(SEPARADOR)
              .append(formataCpf(cliente.getNumCpf())).append(SEPARADOR)
              .append(formataTelefone(cliente.getNumFone()));
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<String> getItensConsicaoPagamento() {
        List<String> itens = new ArrayList<>();
        for (CondicaoPagamento condicao : getCondicoes()) {
            StringBuilder sb = new StringBuilder();
            sb.append(condicao.getId()).append(SEPARADOR)
              .append(condicao.getNomCondicaoPagamento()).append(SEPARADOR)
              .append(condicao.getNumDiasAtePrimeiraParcela()).append(SEPARADOR)
              .append(condicao.getNumDiasEntreParcelas());
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<ColunaDto> getColunasVendedor() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("C�digo", DIREITA, 40));
        colunas.add(new ColunaDto("Nome", ESQUERDA, 160));
        colunas.add(new ColunaDto("CPF", MEIO, 60));
        colunas.add(new ColunaDto("Fone", MEIO, 40));
        return colunas;
    }

    private List<ColunaDto> getColunasCliente() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("C�digo", DIREITA, 40));
        colunas.add(new ColunaDto("Nome", ESQUERDA, 160));
        colunas.add(new ColunaDto("CPF", MEIO, 60));
        colunas.add(new ColunaDto("Fone", MEIO, 40));
        return colunas;
    }

    public List<ColunaDto> getColunasCondicaoPagamento() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("C�digo", DIREITA, 30));
        colunas.add(new ColunaDto("Descri��o", ESQUERDA, 130));
        colunas.add(new ColunaDto("Dias At� 1� Parcela", DIREITA, 70));
        colunas.add(new ColunaDto("Dias Entre Parcelas", DIREITA, 70));
        return colunas;
    }

    public void finalizar() {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(getView().getTxtCodVendedor().getText())) {
            msg.append("Vendedor: Campo com preenchimento Obrigat�rio.\n");
        }
        if (isEmpty(getView().getTxtCodCondicao().getText())) {
            msg.append("Condi��o de Pagamento: Campo com preenchimento Obrigat�rio.\n");
        }
        if (isEmpty(getView().getTxtCodCliente().getText())) {
            msg.append("Cliente: Campo com preenchimento Obrigat�rio.\n");
        }
        if (isNotEmpty(msg.toString())) {
            showMessageDialog(getView(), msg.toString(), "Aten��o", ERROR_MESSAGE);
            return;
        }

        Venda venda = new Venda();
        venda.setNumSerie(VAZIO);
        venda.setVendedor(vendedor);
        venda.setCliente(cliente);
        venda.setCondicaoPagamento(condicao);
        venda.setDatEmissao(new Date());
        venda.setVlrTotal(vendaController.getValorTotalLiquido());

        venda = getVendaService().salvarRetornandoVenda(venda);
        atualizaSalvaItensVenda(venda);

        showMessageDialog(getView(), "Venda efetuada com sucesso!", "Aten��o", INFORMATION_MESSAGE);
        getView().dispose();
        vendaController.limpaTransacao();
    }

    private void atualizaSalvaItensVenda(Venda venda) {
        getItens().forEach(item -> {
            atualizaCaracteristicaProduto(item);
            item.setVenda(venda);
            getItensVendaService().salvar(item);
        });
    }

    private void atualizaCaracteristicaProduto(ItemVenda item) {
        CaracteristicaProduto caracteristica = item.getCaracteristicaProduto();
        caracteristica.setQtdEstoque(caracteristica.getQtdEstoque() - item.getQtdProduto());
        getCaracteristicaProdutoService().atualizar(caracteristica);
    }

    public List<ItemVenda> getItens() {
        if (isNull(itens)) {
            itens = new ArrayList<>();
        }
        return itens;
    }

    public List<Vendedor> getVendedores() {
        if (isNull(vendedores)) {
            vendedores = new ArrayList<>();
        }
        return vendedores;
    }

    public List<Cliente> getClientes() {
        if (isNull(clientes)) {
            clientes = new ArrayList<>();
        }
        return clientes;
    }

    public List<CondicaoPagamento> getCondicoes() {
        if (isNull(condicoes)) {
            condicoes = new ArrayList<>();
        }
        return condicoes;
    }

    public FinalizacaoView getView() {
        return view;
    }

    public void setView(FinalizacaoView view) {
        this.view = view;
    }

    private VendaService getVendaService() {
        return ofNullable(vendaService).orElseGet(() -> vendaService = new VendaService());
    }

    private ItemVendaService getItensVendaService() {
        return ofNullable(itemVendaService).orElseGet(() -> itemVendaService = new ItemVendaService());
    }

    private VendedorService getVendedorService() {
        return ofNullable(vendedorService).orElseGet(() -> vendedorService = new VendedorService());
    }

    private ClienteService getClienteService() {
        return ofNullable(clienteService).orElseGet(() -> clienteService = new ClienteService());
    }

    private CondicaoPagamentoService getCondicaoPagamentoService() {
        return ofNullable(condicaoPagamentoService).orElseGet(() -> condicaoPagamentoService = new CondicaoPagamentoService());
    }

    private CaracteristicaProdutoService getCaracteristicaProdutoService() {
        return ofNullable(caracteristicaProdutoService).orElseGet(() -> caracteristicaProdutoService = new CaracteristicaProdutoService());
    }
}