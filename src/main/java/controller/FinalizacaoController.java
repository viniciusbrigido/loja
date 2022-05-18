package controller;

import dto.ColunaDto;
import lombok.Getter;
import lombok.Setter;
import model.bo.*;
import service.*;
import view.FinalizacaoView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Time;
import java.util.*;
import static java.awt.event.KeyEvent.*;
import static java.lang.Integer.*;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static javax.swing.KeyStroke.getKeyStroke;
import static util.Formatador.*;
import static util.ValueUtil.*;

public class FinalizacaoController extends Controller {

    @Getter @Setter
    private FinalizacaoView view;

    private List<ItemVenda> itens;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;
    private List<CondicaoPagamento> condicoes;

    private VendaService vendaService;
    private ItemVendaService itemVendaService;
    private VendedorService vendedorService;
    private ClienteService clienteService;
    private ReceberService receberService;
    private CondicaoPagamentoService condicaoPagamentoService;
    private CaracteristicaProdutoService caracteristicaProdutoService;

    private VendaController vendaController;
    private Vendedor vendedor;
    private Cliente cliente;
    private CondicaoPagamento condicao;

    public FinalizacaoController(FinalizacaoView view, List<ItemVenda> itens, VendaController vendaController) {
        setView(view);
        getView().setVisible(true);
        adicionaAcaoCamposCodigo();
        adicionaAcaoAosBotoes();
        this.vendaController = vendaController;
        this.itens = itens;
        preencheCamposTela();
        setaFocoInicial();
    }

    private void adicionaAcaoCamposCodigo() {
        adicionaAcaoCampoCodigoVendedor();
        adicionaAcaoCampoCodigoCliente();
        adicionaAcaoCampoCodigoCondicao();
    }

    private void adicionaAcaoCampoCodigoVendedor() {
        getView().getTxtCodVendedor().addActionListener(a -> buscaVendedor());
        getView().getTxtCodVendedor().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_F4, ZERO), EVENTO);
        getView().getTxtCodVendedor().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaVendedores();
            }
        });
    }

    private void adicionaAcaoCampoCodigoCliente() {
        getView().getTxtCodCliente().addActionListener(a -> buscaCliente());
        getView().getTxtCodCliente().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_F4, ZERO), EVENTO);
        getView().getTxtCodCliente().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaClientes();
            }
        });
    }

    private void adicionaAcaoCampoCodigoCondicao() {
        getView().getTxtCodCondicao().addActionListener(a -> buscaCondicao());
        getView().getTxtCodCondicao().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_F4, ZERO), EVENTO);
        getView().getTxtCodCondicao().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaCondicoes();
            }
        });
    }

    private void adicionaAcaoAosBotoes() {
        adicionaAcaoBotaoVendedor();
        adicionaAcaoBotaoCliente();
        adicionaAcaoBotaoCondicao();
        adicionaAcaoBotaoFinalizar();
        adicionaAcaoBotaoLimpar();
        adicionaAcaoBotaoSair();
    }

    private void adicionaAcaoBotaoVendedor() {
        getView().getBtnVendedor().addActionListener(a -> listaVendedores());
        getView().getBtnVendedor().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnVendedor().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaVendedores();
            }
        });
    }

    private void adicionaAcaoBotaoCliente() {
        getView().getBtnCliente().addActionListener(a -> listaClientes());
        getView().getBtnCliente().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnCliente().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaClientes();
            }
        });
    }

    private void adicionaAcaoBotaoCondicao() {
        getView().getBtnCondicao().addActionListener(a -> listaCondicoes());
        getView().getBtnCondicao().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnCondicao().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaCondicoes();
            }
        });
    }

    private void adicionaAcaoBotaoFinalizar() {
        getView().getBtnFinalizar().addActionListener(a -> finalizar());
        getView().getBtnFinalizar().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F1, ZERO), EVENTO);
        getView().getBtnFinalizar().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnFinalizar().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizar();
            }
        });
    }

    private void adicionaAcaoBotaoLimpar() {
        getView().getBtnLimpar().addActionListener(a -> limpaTela());
        getView().getBtnLimpar().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F2, ZERO), EVENTO);
        getView().getBtnLimpar().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnLimpar().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpaTela();
            }
        });
    }

    private void adicionaAcaoBotaoSair() {
        getView().getBtnSair().addActionListener(a -> sairTela());
        getView().getBtnSair().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_ESCAPE, ZERO), EVENTO);
        getView().getBtnSair().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnSair().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sairTela();
            }
        });
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

    private void limpaTela() {
        limpaCliente();
        limpaCondicao();
        limpaVendedorSetaFoco();
    }

    private void sairTela() {
        getView().dispose();
        vendaController.setVendedor(vendedor);
        vendaController.setCliente(cliente);
        vendaController.setCondicao(condicao);
        vendaController.limpaCamposProduto();
    }

    private void preencheVendedor(Vendedor vendedor) {
        getView().getTxtCodVendedor().setText(String.valueOf(vendedor.getId()));
        getView().getTxtNomVendedor().setText(vendedor.getNome());
        this.vendedor = vendedor;
    }

    private void preencheCliente(Cliente cliente) {
        getView().getTxtCodCliente().setText(String.valueOf(cliente.getId()));
        getView().getTxtNomCliente().setText(cliente.getNome());
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

    private void buscaVendedor() {
        if (isEmpty(getView().getTxtCodVendedor().getText())) {
            limpaVendedor();
            return;
        }
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodVendedor().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", ATENCAO, ERROR_MESSAGE);
            limpaVendedorSetaFoco();
            return;
        }
        Vendedor vendedor = getVendedorService().buscar(codigo);
        if (isNotNull(vendedor) && isNotEmpty(vendedor.getId())) {
            preencheVendedorSetaFoco(vendedor);
        } else {
            showMessageDialog(getView(), "Vendedor não encontrado.", ATENCAO, ERROR_MESSAGE);
            limpaVendedorSetaFoco();
        }
    }

    private void buscaCliente() {
        if (isEmpty(getView().getTxtCodCliente().getText())) {
            limpaCliente();
            return;
        }
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodCliente().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", ATENCAO, ERROR_MESSAGE);
            limpaClienteSetaFoco();
            return;
        }
        Cliente cliente = getClienteService().buscar(codigo);
        if (isNotNull(cliente) && isNotEmpty(cliente.getId())) {
            preencheClienteSetaFoco(cliente);
        } else {
            showMessageDialog(getView(), "Cliente não encontrado.", ATENCAO, ERROR_MESSAGE);
            limpaClienteSetaFoco();
        }
    }

    private void buscaCondicao() {
        if (isEmpty(getView().getTxtCodCondicao().getText())) {
            limpaCondicao();
            return;
        }
        Integer codigo;
        try {
            codigo = parseInt(getView().getTxtCodCondicao().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", ATENCAO, ERROR_MESSAGE);
            limpaCondicaoSetaFoco();
            return;
        }
        CondicaoPagamento condicao = getCondicaoPagamentoService().buscar(codigo);
        if (isNotNull(condicao) && isNotEmpty(condicao.getId())) {
            preencheCondicaoSetaFoco(condicao);
        } else {
            showMessageDialog(getView(), "Condição de Pagamento não encontrada.", ATENCAO, ERROR_MESSAGE);
            limpaCondicaoSetaFoco();
        }
    }

    private void listaVendedores() {
        getVendedores().clear();
        getVendedores().addAll(getVendedorService().buscar());

        if (getVendedores().isEmpty()) {
            showMessageDialog(getView(), "Não há Vendedores para listar.", ATENCAO, INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Vendedores", getItensVendedor(), getColunasVendedor(), TIPO_LISTAGEM_VENDEDOR);
    }

    private void listaClientes() {
        getClientes().clear();
        getClientes().addAll(getClienteService().buscar());

        if (getClientes().isEmpty()) {
            showMessageDialog(getView(), "Não há Clientes para listar.", ATENCAO, INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Clientes", getItensCliente(), getColunasCliente(), TIPO_LISTAGEM_CLIENTE);
    }

    private void listaCondicoes() {
        getCondicoes().clear();
        getCondicoes().addAll(getCondicaoPagamentoService().buscar());

        if (getCondicoes().isEmpty()) {
            showMessageDialog(getView(), "Não há Condições de Pagamento para listar.", ATENCAO, INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Condições de Pagamento", getItensConsicaoPagamento(), getColunasCondicaoPagamento(), TIPO_LISTAGEM_CONDICAO);
    }

    private List<String> getItensVendedor() {
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

    private List<String> getItensCliente() {
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
        colunas.add(new ColunaDto("Código", DIREITA, 40));
        colunas.add(new ColunaDto("Nome", ESQUERDA, 160));
        colunas.add(new ColunaDto("CPF", MEIO, 60));
        colunas.add(new ColunaDto("Fone", MEIO, 40));
        return colunas;
    }

    private List<ColunaDto> getColunasCliente() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 40));
        colunas.add(new ColunaDto("Nome", ESQUERDA, 160));
        colunas.add(new ColunaDto("CPF", MEIO, 60));
        colunas.add(new ColunaDto("Fone", MEIO, 40));
        return colunas;
    }

    private List<ColunaDto> getColunasCondicaoPagamento() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 30));
        colunas.add(new ColunaDto("Descrição", ESQUERDA, 130));
        colunas.add(new ColunaDto("Dias Até 1ª Parcela", DIREITA, 70));
        colunas.add(new ColunaDto("Dias Entre Parcelas", DIREITA, 70));
        return colunas;
    }

    private void finalizar() {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(getView().getTxtCodVendedor().getText())) {
            msg.append("Vendedor: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(getView().getTxtCodCondicao().getText())) {
            msg.append("Condição de Pagamento: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(getView().getTxtCodCliente().getText())) {
            msg.append("Cliente: Campo com preenchimento Obrigatório.\n");
        }
        if (isNotEmpty(msg.toString())) {
            showMessageDialog(getView(), msg.toString(), ATENCAO, ERROR_MESSAGE);
            return;
        }

        Venda venda = new Venda();
        venda.setNumSerie(VAZIO);
        venda.setVendedor(vendedor);
        venda.setCliente(cliente);
        venda.setCondicaoPagamento(condicao);
        venda.setDatEmissao(new Date());
        venda.setHorEmissao(new Time(new Date().getTime()));
        venda.setVlrTotal(vendaController.getValorTotalLiquido());
        venda.setDiaVencimentoParcela(condicao.getNumDiasAtePrimeiraParcela());

        venda = getVendaService().salvarRetornandoVenda(venda);
        atualizaSalvaItensVenda(venda);
        criaRecebimento(venda);

        showMessageDialog(getView(), "Venda efetuada com sucesso!", ATENCAO, INFORMATION_MESSAGE);
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

    private void criaRecebimento(Venda venda) {
        Receber receber = new Receber();
        receber.setDatEmissao(new Date());
        receber.setVlrEmissao(venda.getVlrTotal());
        receber.setVlrDesconto(ZEROD);
        receber.setVenda(venda);

        getReceberService().salvar(receber);
    }

    private void atualizaCaracteristicaProduto(ItemVenda item) {
        CaracteristicaProduto caracteristica = item.getCaracteristicaProduto();
        caracteristica.setQtdEstoque(caracteristica.getQtdEstoque() - item.getQtdProduto());
        getCaracteristicaProdutoService().atualizar(caracteristica);
    }

    private List<ItemVenda> getItens() {
        if (isNull(itens)) {
            itens = new ArrayList<>();
        }
        return itens;
    }

    private List<Vendedor> getVendedores() {
        if (isNull(vendedores)) {
            vendedores = new ArrayList<>();
        }
        return vendedores;
    }

    private List<Cliente> getClientes() {
        if (isNull(clientes)) {
            clientes = new ArrayList<>();
        }
        return clientes;
    }

    private List<CondicaoPagamento> getCondicoes() {
        if (isNull(condicoes)) {
            condicoes = new ArrayList<>();
        }
        return condicoes;
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

    private ReceberService getReceberService() {
        return ofNullable(receberService).orElseGet(() -> receberService = new ReceberService());
    }

    private CondicaoPagamentoService getCondicaoPagamentoService() {
        return ofNullable(condicaoPagamentoService).orElseGet(() -> condicaoPagamentoService = new CondicaoPagamentoService());
    }

    private CaracteristicaProdutoService getCaracteristicaProdutoService() {
        return ofNullable(caracteristicaProdutoService).orElseGet(() -> caracteristicaProdutoService = new CaracteristicaProdutoService());
    }
}