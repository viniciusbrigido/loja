package controller;

import dto.ColunaDto;
import model.bo.*;
import service.CaracteristicaProdutoService;
import service.ProdutoService;
import view.VendaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.*;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.Formatador.*;
import static util.Formatador.formatHora;
import static util.Formatador.formataDouble;
import static util.ValueUtil.*;

public class VendaController extends Controller {

    private static final String VAZIO_NOME_PRODUTO = " ";

    private Vendedor vendedor;
    private Cliente cliente;
    private CondicaoPagamento condicao;

    private List<CaracteristicaProduto> caracteristicas;
    private List<Produto> produtos;
    private List<ItemVenda> itens;
    private CaracteristicaProduto caracteristica;

    private CaracteristicaProdutoService caracteristicaProdutoService;
    private ProdutoService produtoService;

    private Integer linhaSelecionada;

    private Double valorTotalLiquido = ZEROD;
    private Double valorTotalBruto = ZEROD;

    private VendaView view;

    public VendaController() {
        super();
        setView(new VendaView(this));
        getView().setVisible(true);
        limpaCamposProduto();
        setaInformativo();
    }

    private void setaInformativo() {
        setaUsuario();
        getView().getTxtData().setText(formataDataPadrao(new Date()));
        new Timer();
    }

    private void setaUsuario() {
        Random random = new Random();
        getView().getTxtUsuario().setText(random.nextInt() % 2 == 0 ? "Vinícius" : "Jonatas");
    }

    public void preencheItem() {
        if (getView().getTxtCodigoBarras().getText().isEmpty()) {
            showMessageDialog(getView(), "Pesquise um produto para lança-lo.", "Atenção", ERROR_MESSAGE);
            setaFocoTela();
            return;
        }
        if (getView().getTxtPrcDesconto().getDoubleValue() > 99.99) {
            showMessageDialog(getView(), "Desconto (%): Valor inválido.", "Atenção", ERROR_MESSAGE);
            setaFocoTela();
            return;
        }
        Double quantidade = isEmpty(getView().getTxtQuantidade().getText()) ? 1 : paramToDouble(getView().getTxtQuantidade().getText());
        if (quantidade > caracteristica.getQtdEstoque()) {
            showMessageDialog(getView(), "Qauntidade maior que a do produto.", "Atenção", ERROR_MESSAGE);
            setaFocoTela();
            return;
        }

        atualizaSalvaItem(quantidade);
        limpaCamposProduto();
        atualizaGridValores();
    }

    public void setaFocoPrcDesconto() {
        getView().getTxtPrcDesconto().requestFocus();
    }

    private void atualizaSalvaItem(Double quantidade) {
        if (isNull(linhaSelecionada)) {
            ItemVenda item = new ItemVenda();
            item.setCaracteristicaProduto(caracteristica);
            item.setVlrUnitario(caracteristica.getProduto().getVlrProduto());
            item.setQtdProduto(quantidade);
            item.setPrcDesconto(getView().getTxtPrcDesconto().getDoubleValue());
            getItens().add(item);
        } else {
            getItens().get(linhaSelecionada).setQtdProduto(quantidade);
            getItens().get(linhaSelecionada).setPrcDesconto(getView().getTxtPrcDesconto().getDoubleValue());
        }
    }

    public void buscaProduto() {
        linhaSelecionada = null;
        String leitura = getView().getTxtLeitura().getText();
        if (isEmpty(leitura)) {
            limpaCamposProduto();
            return;
        }
        if (isPesquisaPorNomeOuId(leitura)) {
            buscaProdutoPorNomeOuId(leitura);
        } else {
            buscaProdutoPorCodigoBarras(leitura);
        }
    }

    private boolean isPesquisaPorNomeOuId(String leitura) {
        return (isNumerico(leitura) && leitura.length() < 13) || !isNumerico(leitura.replace("*", VAZIO));
    }

    private void buscaProdutoPorNomeOuId(String leitura) {
        List<Produto> produtos = new ArrayList<>();
        if (isNumerico(leitura)) {
            Integer codigoProduto = Integer.parseInt(leitura);
            Produto produto = getProdutoService().buscar(codigoProduto);
            if (isNotNull(produto) && isNotEmpty(produto.getId())) {
                produtos.add(produto);
            }
        } else {
            StringBuilder nomeProduto = new StringBuilder();
            nomeProduto.append("%").append(leitura).append("%");
            produtos.addAll(getProdutoService().buscaPorNome(nomeProduto.toString()));
        }

        if (produtos.isEmpty()) {
            showMessageDialog(getView(), "Produto não encontrado.", "Atenção", ERROR_MESSAGE);
            limpaCamposProduto();
            return;
        }

        getProdutos().clear();
        setProdutos(produtos);
        if (produtos.size() > 1) {
            new ListagemGeralController(this, "Listagem de Produtos", getItensProdutos(), getColunasProdutos(), null);
            return;
        }

        prossegueBusca(produtos.get(ZERO).getId());
    }

    private void prossegueBusca(Integer codProduto) {
        getCaracteristicas().clear();
        setCaracteristicas(getCaracteristicaProdutoService().buscaCaracteristicasPorProduto(codProduto));
        if (getCaracteristicas().isEmpty()) {
            showMessageDialog(getView(), "Caracterítica de Produto não encontrada.", "Atenção", ERROR_MESSAGE);
            limpaCamposProduto();
            return;
        }

        if (getCaracteristicas().size() == 1) {
            setCaracteristica(getCaracteristicas().get(0));
        } else {
            new ListagemGeralController(this, "Listagem de Características de Produto", getItensCaracteristicas(), getColunasCaracteristicas(), getCaracteristicas().get(0).getProduto());
        }
    }

    private List<String> getItensProdutos() {
        List<String> itens = new ArrayList<>();
        for (Produto produto : getProdutos()) {
            StringBuilder sb = new StringBuilder();
            sb.append(produto.getId()).append(SEPARADOR)
               .append(produto.getNomProduto()).append(SEPARADOR)
               .append(formataDouble(produto.getVlrProduto())).append(SEPARADOR)
               .append(produto.getTipo().getNomTipo()).append(SEPARADOR)
               .append(produto.getTamanho().getNomTamanho()).append(SEPARADOR)
               .append(produto.getMarca().getNomMarca());
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<ColunaDto> getColunasProdutos() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 30));
        colunas.add(new ColunaDto("Descrição", ESQUERDA, 90));
        colunas.add(new ColunaDto("Valor(R$)", DIREITA, 50));
        colunas.add(new ColunaDto("Tipo", ESQUERDA, 50));
        colunas.add(new ColunaDto("Tamanho", ESQUERDA, 50));
        colunas.add(new ColunaDto("Marca", ESQUERDA, 50));
        return colunas;
    }

    private List<String> getItensCaracteristicas() {
        List<String> itens = new ArrayList<>();
        for (CaracteristicaProduto caracteristica : getCaracteristicas()) {
            StringBuilder sb = new StringBuilder();
            sb.append(caracteristica.getCodBarras()).append(SEPARADOR)
               .append(formataDouble(caracteristica.getQtdEstoque())).append(SEPARADOR)
               .append(caracteristica.getCor().getNomCor());
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<ColunaDto> getColunasCaracteristicas() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código de Barras", MEIO, 200));
        colunas.add(new ColunaDto("Estoque", DIREITA, 100));
        colunas.add(new ColunaDto("Cor", ESQUERDA, 100));
        return colunas;
    }

    private void buscaProdutoPorCodigoBarras(String leitura) {
        String codigoBarras;
        Integer qtdProduto;
        if (leitura.contains("*")) {
            String[] linha = leitura.split("\\*");
            codigoBarras = linha[0];
            qtdProduto = Integer.parseInt(linha[1]);
        } else {
            codigoBarras = leitura;
            qtdProduto = null;
        }

        CaracteristicaProduto caracteristica = getCaracteristicaProdutoService().buscaPorCodigoBarras(codigoBarras);
        if (isNull(caracteristica) || isEmpty(caracteristica.getId())) {
            showMessageDialog(getView(), "Caracterítica de Produto não encontrada.", "Atenção", ERROR_MESSAGE);
            limpaCamposProduto();
            return;
        }
        setCaracteristica(caracteristica);
        if (isNotEmpty(qtdProduto)) {
            getView().getTxtQuantidade().setText(String.valueOf(qtdProduto));
            setaFocoPrcDesconto();
        }
    }

    private void setaFocoTela() {
        if (isEmpty(getView().getTxtCodigoBarras().getText())) {
            getView().getTxtLeitura().requestFocus();
        } else {
            getView().getTxtQuantidade().requestFocus();
        }
    }

    public void editaItem() {
        if (getView().getTabelaProdutos().getSelectedRow() < 0) {
            showMessageDialog(getView(), "Selecione um item para editar.", "Atenção", ERROR_MESSAGE);
            return;
        }
        linhaSelecionada = getView().getTabelaProdutos().getSelectedRow();
        ItemVenda item = getItens().get(linhaSelecionada);
        preencheDadosCaracteristica(item.getCaracteristicaProduto());
        Double qtdProduto = item.getQtdProduto();
        getView().getTxtQuantidade().setText(qtdProduto.toString());
        getView().getTxtPrcDesconto().setText(formataDouble(item.getPrcDesconto()));

        getView().getTxtQuantidade().requestFocus();
    }

    public void excluiItem() {
        if (getView().getTabelaProdutos().getSelectedRow() < 0) {
            showMessageDialog(getView(), "Selecione um item para excluir.", "Atenção", ERROR_MESSAGE);
            limpaCamposProduto();
            return;
        }
        if (showYesNoConfirmDialog(getView(), "Deseja realmente excluir o item?", "Atenção") != YES_OPTION) {
            limpaCamposProduto();
            return;
        }

        getItens().remove(getView().getTabelaProdutos().getSelectedRow());
        atualizaGridValores();
        limpaCamposProduto();
    }

    private void atualizaTotalizadores() {
        Double valorTotalBruto = ZEROD;
        Double valorTotalLiquido = ZEROD;
        for (ItemVenda item : getItens()) {
            valorTotalBruto += item.getVlrBruto();
            valorTotalLiquido += item.getVlrLiquido();
        }

        this.valorTotalLiquido = valorTotalLiquido;
        this.valorTotalBruto = valorTotalBruto;

        getView().getTotalBruto().setText(formataDouble(valorTotalBruto));
        getView().getTotalVenda().setText(formataDouble(valorTotalLiquido));
    }

    private void atualizaGridValores() {
        atualizaTotalizadores();
        getView().fireTableDataChanged();
    }

    public void limpaCamposProduto() {
        linhaSelecionada = null;
        getView().getTxtLeitura().setText(VAZIO);
        getView().getTxtQuantidade().setText(VAZIO);
        getView().getTxtVlrUnitario().setText(VAZIO);
        getView().getTxtEstoque().setText(VAZIO);
        getView().getTxtPrcDesconto().setText(VAZIO);
        getView().getTxtCodigoBarras().setText(VAZIO);
        getView().getLblNomeProduto().setText(VAZIO_NOME_PRODUTO);

        getView().getTxtLeitura().requestFocus();
    }

    public void setProduto(Integer index) {
        prossegueBusca(getProdutos().get(index).getId());
    }

    public void setCaracteristica(Integer index) {
        setCaracteristica(getCaracteristicas().get(index));
    }

    public void setCaracteristica(CaracteristicaProduto caracteristica) {
        this.caracteristica = caracteristica;
        preencheDadosCaracteristica(caracteristica);
    }

    public void preencheDadosCaracteristica(CaracteristicaProduto caracteristica) {
        getView().getTxtLeitura().setText(VAZIO);
        if (isNull(caracteristica) || isEmpty(caracteristica.getId())) {
            getView().getTxtVlrUnitario().setText(VAZIO);
            getView().getTxtEstoque().setText(VAZIO);
            getView().getTxtCodigoBarras().setText(VAZIO);
            getView().getLblNomeProduto().setText(VAZIO_NOME_PRODUTO);
            getView().getTxtLeitura().requestFocus();
            return;
        }
        getView().getLblNomeProduto().setText(caracteristica.getProduto().getNomProduto());
        getView().getTxtVlrUnitario().setText(formataDouble(caracteristica.getProduto().getVlrProduto()));
        getView().getTxtEstoque().setText(formataDouble(caracteristica.getQtdEstoque()));
        getView().getTxtCodigoBarras().setText(caracteristica.getCodBarras());
        getView().getTxtQuantidade().requestFocus();
    }

    public void finalizar() {
        if (getItens().isEmpty()) {
            showMessageDialog(getView(), "Não há itens para finalizar a venda.", "Atenção", ERROR_MESSAGE);
            setaFocoTela();
            return;
        }
        new FinalizacaoController(this);
    }

    public void selecionaItem() {
        if (getItens().isEmpty()) {
            setaFocoTela();
            return;
        }
        getView().getTabelaProdutos().requestFocus();
        getView().getTabelaProdutos().setRowSelectionInterval(ZERO, ZERO);
    }

    public void incluiItem() {
        limpaCamposProduto();
    }

    public void limpaTransacao() {
        limpaCamposProduto();
        caracteristica = null;
        vendedor = null;
        cliente = null;
        condicao = null;
        getItens().clear();
        atualizaGridValores();
    }

    public void excluiVenda() {
        if (!getItens().isEmpty() && showYesNoConfirmDialog(getView(), "Deseja realmente excluir a venda?", "Atenção") != YES_OPTION) {
            limpaCamposProduto();
            return;
        }
        limpaTransacao();
    }

    public List<ItemVenda> getItens() {
        if (isNull(itens)) {
            itens = new ArrayList<>();
        }
        return itens;
    }

    public List<CaracteristicaProduto> getCaracteristicas() {
        if (isNull(caracteristicas)) {
            caracteristicas = new ArrayList<>();
        }
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicaProduto> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<Produto> getProdutos() {
        if (isNull(produtos)) {
            produtos = new ArrayList<>();
        }
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public VendaView getView() {
        return view;
    }

    public void setView(VendaView view) {
        this.view = view;
    }

    public Double getValorTotalLiquido() {
        return valorTotalLiquido;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public CondicaoPagamento getCondicao() {
        return condicao;
    }

    public void setCondicao(CondicaoPagamento condicao) {
        this.condicao = condicao;
    }

    private CaracteristicaProdutoService getCaracteristicaProdutoService() {
        return ofNullable(caracteristicaProdutoService).orElseGet(() -> caracteristicaProdutoService = new CaracteristicaProdutoService());
    }

    private ProdutoService getProdutoService() {
        return ofNullable(produtoService).orElseGet(() -> produtoService = new ProdutoService());
    }

    public class Timer implements ActionListener {
        private javax.swing.Timer timer;

        public Timer() {
            disparaRelogio();
        }

        public void disparaRelogio() {
            if (timer == null) {
                timer = new javax.swing.Timer(1000, this);
                timer.setInitialDelay(0);
                timer.start();
            } else if (!timer.isRunning()) {
                timer.restart();
            }
        }

        public void actionPerformed(ActionEvent ae) {
            getView().getTxtHora().setText(formatHora(new Time(new Date().getTime())));
        }
    }
}