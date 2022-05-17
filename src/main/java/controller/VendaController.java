package controller;

import dto.ColunaDto;
import model.bo.*;
import personalizado.VendaCellRenderer;
import service.CaracteristicaProdutoService;
import service.ProdutoService;
import view.FinalizacaoView;
import view.VendaView;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.*;
import java.util.List;
import static java.awt.Cursor.getDefaultCursor;
import static java.awt.event.KeyEvent.*;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static javax.swing.KeyStroke.getKeyStroke;
import static util.Formatador.*;
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

    private AbstractTableModel grid;

    private VendaView view;

    public VendaController(VendaView view) {
        super();
        setView(view);
        getView().setVisible(true);
        adicionaAcoesGrid();
        adicionaAcoesCampos();
        adicionaAcaoAosBotoes();
        limpaCamposProduto();
        setaInformativo();
    }

    private void adicionaAcoesCampos() {
        adicionaAcaoLeitura();
        adicionaAcaoPrcDesconto();
        adicionaAcaoQuantidade();
    }

    private void adicionaAcaoLeitura() {
        getView().getTxtLeitura().addActionListener(a -> buscaProduto());
    }

    private void adicionaAcaoPrcDesconto() {
        getView().getTxtPrcDesconto().addActionListener(a -> preencheItem());
    }

    private void adicionaAcaoQuantidade() {
        getView().getTxtQuantidade().addActionListener(a -> setaFocoPrcDesconto());
    }

    private void adicionaAcaoAosBotoes() {
        adicionaAcaoBotaoFinalizar();
        adicionaAcaoBotaoEditaItem();
        adicionaAcaoBotaoExcluiItem();
        adicionaAcaoBotaoSelecionaItem();
        adicionaAcaoBotaoIncluiItem();
        adicionaAcaoBotaoExcluiVenda();
        adicionaAcaoBotaoSair();
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

    private void adicionaAcaoBotaoEditaItem() {
        getView().getBtnEditaItem().addActionListener(a -> editaItem());
        getView().getBtnEditaItem().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F2, ZERO), EVENTO);
        getView().getBtnEditaItem().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnEditaItem().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editaItem();
            }
        });
    }

    private void adicionaAcaoBotaoExcluiItem() {
        getView().getBtnExcluiItem().addActionListener(a -> excluiItem());
        getView().getBtnExcluiItem().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F3, ZERO), EVENTO);
        getView().getBtnExcluiItem().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnExcluiItem().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluiItem();
            }
        });
    }

    private void adicionaAcaoBotaoSelecionaItem() {
        getView().getBtnSelecionaItem().addActionListener(a -> selecionaItem());
        getView().getBtnSelecionaItem().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F4, ZERO), EVENTO);
        getView().getBtnSelecionaItem().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnSelecionaItem().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionaItem();
            }
        });
    }

    private void adicionaAcaoBotaoIncluiItem() {
        getView().getBtnIncluiItem().addActionListener(a -> incluiItem());
        getView().getBtnIncluiItem().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F5, ZERO), EVENTO);
        getView().getBtnIncluiItem().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnIncluiItem().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incluiItem();
            }
        });
    }

    private void adicionaAcaoBotaoExcluiVenda() {
        getView().getBtnExcluiVenda().addActionListener(a -> excluiVenda());
        getView().getBtnExcluiVenda().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F6, ZERO), EVENTO);
        getView().getBtnExcluiVenda().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnExcluiVenda().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluiVenda();
            }
        });
    }

    private void adicionaAcaoBotaoSair() {
        getView().getBtnSair().addActionListener(a -> getView().dispose());
        getView().getBtnSair().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_ESCAPE, ZERO), EVENTO);
        getView().getBtnSair().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnSair().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getView().dispose();
            }
        });
    }

    private void adicionaAcoesGrid() {
        getView().getTabelaProdutos().setModel(getGrid());
        getView().getTabelaProdutos().addMouseListener(getMouseListenerTableGrid());
        getView().getTabelaProdutos().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_F2, ZERO), EVENTO);
        getView().getTabelaProdutos().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editaItem();
            }
        });
        adicionaRegrasAlinhamentoGrid();
    }

    private void adicionaRegrasAlinhamentoGrid() {
        TableColumnModel columnModel = getView().getTabelaProdutos().getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(225);
        columnModel.getColumn(2).setPreferredWidth(125);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(100);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(100);

        DefaultTableCellRenderer dtcrEditar = new DefaultTableCellRenderer();
        dtcrEditar.setIcon(new ImageIcon(getClass().getResource("/imagens/editar.png")));
        columnModel.getColumn(7).setCellRenderer(dtcrEditar);
        columnModel.getColumn(7).setMaxWidth(20);

        DefaultTableCellRenderer dtcrExcluir = new DefaultTableCellRenderer();
        dtcrExcluir.setIcon(new ImageIcon(getClass().getResource("/imagens/remover.png")));
        columnModel.getColumn(8).setCellRenderer(dtcrExcluir);
        columnModel.getColumn(8).setMaxWidth(20);

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            VendaTableCellRenderer vendaTableCellRenderer = new VendaTableCellRenderer();
            if (i == 2) {
                vendaTableCellRenderer.setHorizontalAlignment(VendaTableCellRenderer.CENTER);
            } else if (Number.class.isAssignableFrom(getView().getTabelaProdutos().getColumnClass(i))) {
                vendaTableCellRenderer.setHorizontalAlignment(VendaTableCellRenderer.RIGHT);
            }
            columnModel.getColumn(i).setCellRenderer(VendaCellRenderer.class.isAssignableFrom(getView().getTabelaProdutos().getColumnClass(i)) ? VendaCellRenderer.criarIconTableCellRendered(i) : vendaTableCellRenderer);
        }

        getView().getTabelaProdutos().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                getView().setCursor(isIconeExcluirEditarSelecionado(e) ? new Cursor(Cursor.HAND_CURSOR) : getDefaultCursor());
            }
        });
    }

    class VendaTableCellRenderer extends DefaultTableCellRenderer {
        public VendaTableCellRenderer() {
            super();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return VendaCellRenderer.defineCorLinhaGrid(table, isSelected, row, component);
        }
    }

    private boolean isIconeExcluirEditarSelecionado(MouseEvent e) {
        int colunaSelecionada = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
        return colunaSelecionada == 7 || colunaSelecionada == 8;
    }

    public AbstractTableModel getGrid() {
        if (grid == null) {
            grid = new AbstractTableModel() {
                public final String[] columnNames = {"Código", "Produto", "Código de Barras", "Quantidade", "Valor Unitário", "Percentual (%)", "Valor Total", "", ""};
                private Class<?>[] columnClasses = new Class<?>[] {Number.class, String.class, String.class, Number.class, Number.class, Number.class, Number.class, VendaCellRenderer.class, VendaCellRenderer.class};

                public int getColumnCount() {
                    return columnNames.length;
                }

                public String getColumnName(int column) {
                    return columnNames[column];
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnClasses[columnIndex];
                }

                public int getRowCount() {
                    return getItens().size();
                }

                public Object getValueAt(int row, int column) {
                    ItemVenda item = getItens().get(row);

                    switch (column) {
                        case 0:
                            return item.getCaracteristicaProduto().getProduto().getId();
                        case 1:
                            return item.getCaracteristicaProduto().getProduto().getNomProduto();
                        case 2:
                            return item.getCaracteristicaProduto().getCodBarras();
                        case 3:
                            return item.getQtdProduto().intValue();
                        case 4:
                            return formataDouble(item.getVlrUnitario());
                        case 5:
                            return formataDouble(item.getPrcDesconto());
                        case 6:
                            return formataDouble(item.getVlrLiquido());
                        default:
                            return "";
                    }
                }
            };
        }
        return grid;
    }

    public void fireTableDataChanged() {
        getGrid().fireTableDataChanged();
    }

    private MouseListener getMouseListenerTableGrid() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int colunaSelecionada = ((JTable) e.getSource()).getSelectedColumn();

                if (colunaSelecionada == 7) {
                    editaItem();
                } else if (colunaSelecionada == 8) {
                    excluiItem();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getView().setCursor(getDefaultCursor());
            }
        };
    }

    private void setaInformativo() {
        setaUsuario();
        getView().getTxtData().setText(formataDataPadrao(new Date()));
        new Timer();
    }

    private void setaUsuario() {
        Random random = new Random();
        getView().getTxtUsuario().setText(random.nextInt() % 2 == ZERO ? "Vinícius" : "Jonatas");
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
            setCaracteristica(getCaracteristicas().get(ZERO));
        } else {
            new ListagemGeralController(this, "Listagem de Características de Produto", getItensCaracteristicas(), getColunasCaracteristicas(), getCaracteristicas().get(ZERO).getProduto());
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
        if (getView().getTabelaProdutos().getSelectedRow() < ZERO) {
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
        if (getView().getTabelaProdutos().getSelectedRow() < ZERO) {
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
        fireTableDataChanged();
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
        new FinalizacaoController(new FinalizacaoView(),this);
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