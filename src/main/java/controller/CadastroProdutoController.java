package controller;

import dto.ColunaDto;
import model.bo.*;
import service.*;
import view.CadastroProdutoView;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static java.awt.Cursor.*;
import static java.awt.event.KeyEvent.*;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static javax.swing.KeyStroke.getKeyStroke;
import static util.Formatador.*;
import static util.ValueUtil.*;

public class CadastroProdutoController extends CadastroController {

    private CadastroProdutoView view;
    private List<Produto> produtos;
    private List<Marca> marcas;
    private List<Tipo> tipos;
    private List<Tamanho> tamanhos;
    private List<Cor> cores;
    private List<CaracteristicaProduto> caracteristicas;

    private ProdutoService produtoService;
    private MarcaService marcaService;
    private TipoService tipoService;
    private TamanhoService tamanhoService;
    private CaracteristicaProdutoService caracteristicaProdutoService;
    private CorService corService;

    private AbstractTableModel grid;

    private Integer codigoCaracteristica;

    public CadastroProdutoController(CadastroProdutoView view) {
        super(view);
        setView(view);
        getView().setVisible(true);
        adicionaAcaoAosBotoes();
        adicionaAcoesGrid();
        preencheListas();
        verificaMarcaTipoTamanhoCorCadastrados();
        populaCombos();
        habilitaBotaoAddCaracteristica(false);
    }

    private void adicionaAcaoAosBotoes() {
        adicionaAcaoBotaoCaracteristica();
        adicionaAcaoBotaoSalvarCaracteristica();
        adicionaAcaoBotaoEditarProduto();
        adicionaAcaoBotaoNovoProduto();
        adicionaAcaoBotaoSairCaracteristica();
    }

    private void adicionaAcaoBotaoCaracteristica() {
        getView().getBtnAdicionarCaracteristica().addActionListener(a -> adicionaCaracteristica());
        getView().getBtnAdicionarCaracteristica().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F4, ZERO), EVENTO);
        getView().getBtnAdicionarCaracteristica().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnAdicionarCaracteristica().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionaCaracteristica();
            }
        });
    }

    private void adicionaAcaoBotaoSalvarCaracteristica() {
        getView().getBtnSalvarCaracteristica().addActionListener(a -> salvaCaracteristica());
        getView().getBtnSalvarCaracteristica().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F5, ZERO), EVENTO);
        getView().getBtnSalvarCaracteristica().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnSalvarCaracteristica().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaCaracteristica();
            }
        });
    }

    private void adicionaAcaoBotaoEditarProduto() {
        getView().getBtnEditarProduto().addActionListener(a -> editaProduto());
        getView().getBtnEditarProduto().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F6, ZERO), EVENTO);
        getView().getBtnEditarProduto().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnEditarProduto().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editaProduto();
            }
        });
    }

    private void adicionaAcaoBotaoNovoProduto() {
        getView().getBtnNovoProduto().addActionListener(a -> novoProduto());
        getView().getBtnNovoProduto().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F7, ZERO), EVENTO);
        getView().getBtnNovoProduto().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnNovoProduto().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoProduto();
            }
        });
    }

    private void adicionaAcaoBotaoSairCaracteristica() {
        getView().getBtnSairCaracteristica().addActionListener(a -> getView().dispose());
        getView().getBtnSairCaracteristica().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_ESCAPE, ZERO), EVENTO);
        getView().getBtnSairCaracteristica().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnSairCaracteristica().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getView().dispose();
            }
        });
    }

    private void adicionaAcoesGrid() {
        getView().getTabelaCaracteristicas().setModel(getGrid());
        getView().getTabelaCaracteristicas().addMouseListener(getMouseListenerTableGrid());
        adicionaRegrasAlinhamentoGrid();
    }

    private void adicionaRegrasAlinhamentoGrid() {
        DefaultTableCellRenderer dtcrRight = new DefaultTableCellRenderer();
        dtcrRight.setHorizontalAlignment(SwingConstants.RIGHT);

        TableColumnModel columnModel = getView().getTabelaCaracteristicas().getColumnModel();
        columnModel.getColumn(0).setCellRenderer(dtcrRight);
        columnModel.getColumn(1).setCellRenderer(dtcrRight);
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(160);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(150);

        DefaultTableCellRenderer dtcrEditar = new DefaultTableCellRenderer();
        dtcrEditar.setIcon(new ImageIcon(getClass().getResource("/imagens/editar.png")));
        columnModel.getColumn(4).setCellRenderer(dtcrEditar);
        columnModel.getColumn(4).setMaxWidth(20);

        DefaultTableCellRenderer dtcrExcluir = new DefaultTableCellRenderer();
        dtcrExcluir.setIcon(new ImageIcon(getClass().getResource("/imagens/remover.png")));
        columnModel.getColumn(5).setCellRenderer(dtcrExcluir);
        columnModel.getColumn(5).setMaxWidth(20);

        getView().getTabelaCaracteristicas().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                getView().setCursor(isIconeExcluirEditarSelecionado(e) ? new Cursor(HAND_CURSOR) : getDefaultCursor());
            }
        });
    }

    private boolean isIconeExcluirEditarSelecionado(MouseEvent e) {
        int colunaSelecionada = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
        return colunaSelecionada == 4 || colunaSelecionada == 5;
    }

    public AbstractTableModel getGrid() {
        if (grid == null) {
            grid = new AbstractTableModel() {
                public final String[] columnNames = {"Código Barra", "Estoque", "Tamanho", "Cor", "", ""};

                public int getColumnCount() {
                    return columnNames.length;
                }

                public String getColumnName(int column) {
                    return columnNames[column];
                }

                public int getRowCount() {
                    return getCaracteristicas().size();
                }

                public Object getValueAt(int row, int column) {
                    CaracteristicaProduto caracteristica = getCaracteristicas().get(row);

                    switch (column) {
                        case 0:
                            return caracteristica.getCodBarras();
                        case 1:
                            return formataDouble(caracteristica.getQtdEstoque());
                        case 2:
                            return caracteristica.getNumTamanho();
                        case 3:
                            return caracteristica.getCor().getNomCor();
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

                if (colunaSelecionada == 4) {
                    editaCaracteristica();
                } else if (colunaSelecionada == 5) {
                    excluiCaracteristica();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getView().setCursor(getDefaultCursor());
            }
        };
    }

    private void preencheListas() {
        getMarcas().addAll(getMarcaService().buscar());
        getTipos().addAll(getTipoService().buscar());
        getTamanhos().addAll(getTamanhoService().buscar());
        getCores().addAll(getCorService().buscar());
    }

    private void verificaMarcaTipoTamanhoCorCadastrados() {
        if (getMarcas().isEmpty()) {
            showMessageDialog(getView(), "Não há Marcas cadastradas.", ATENCAO, INFORMATION_MESSAGE);
            getView().dispose();
            return;
        }
        if (getTipos().isEmpty()) {
            showMessageDialog(getView(), "Não há Tipos de Produto cadastrados.", ATENCAO, INFORMATION_MESSAGE);
            getView().dispose();
            return;
        }
        if (getTamanhos().isEmpty()) {
            showMessageDialog(getView(), "Não há Tamanhos cadastrados.", ATENCAO, INFORMATION_MESSAGE);
            getView().dispose();
            return;
        }
        if (getCores().isEmpty()) {
            showMessageDialog(getView(), "Não há Cores cadastradas.", ATENCAO, INFORMATION_MESSAGE);
            getView().dispose();
        }
    }

    private void populaCombos() {
        getView().getComboMarca().addItem("Selecione uma Marca");
        getMarcas().forEach(m -> getView().getComboMarca().addItem(m.getNomMarca()));

        getView().getComboTipo().addItem("Selecione um Tipo");
        getTipos().forEach(t -> getView().getComboTipo().addItem(t.getNomTipo()));

        getView().getComboTamanho().addItem("Selecione um Tamanho");
        getTamanhos().forEach(t -> getView().getComboTamanho().addItem(t.getNomTamanho()));

        getView().getComboCor().addItem("Selecione uma Cor");
        getCores().forEach(c -> getView().getComboCor().addItem(c.getNomCor()));
    }

    @Override
    public void excluiItem() {
        Produto produto = getProdutos().get(index);
        List<CaracteristicaProduto> caracteristicas = getCaracteristicaProdutoService().buscaCaracteristicasPorProduto(produto.getId());
        caracteristicas.forEach(caracteristica -> getCaracteristicaProdutoService().apagar(caracteristica));
        getProdutoService().apagar(produto);
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getProdutos().get(index));
    }

    @Override
    public void buscaPorCodigo() {
        if (isEmpty(getView().getTxtCodigo().getText())) {
            limpaTela();
            return;
        }
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
            return;
        }

        Produto produto = getProdutoService().buscar(codigo);
        if (isNotNull(produto) && isNotEmpty(produto.getId())) {
            preencheCamposTela(produto);
            habilitaBotaoAddCaracteristica(true);
            limpaCamposCaracteristica();
        } else {
            showMessageDialog(getView(), "Produto não encontrado.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaProduto(new Produto());
            } else {
                verificaProdutoJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), ATENCAO, ERROR_MESSAGE);
            getView().getTxtDescricao().requestFocus();
        }
    }

    private void verificaProdutoJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Produto produto = getProdutoService().buscar(codigo);
        if (isNotNull(produto) && isNotEmpty(produto.getId())) {
            preencheSalvaProduto(produto);
            return;
        }
        showMessageDialog(getView(), "Código não encontrado.\nPara cadastrar um novo Produto não preencha o campo de código.", ATENCAO, ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getProdutos().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtDescricao().setText(VAZIO);
        getView().getTxtPreco().setText(VAZIO);
        getView().getComboMarca().setSelectedIndex(ZERO);
        getView().getComboTipo().setSelectedIndex(ZERO);
        getView().getComboTamanho().setSelectedIndex(ZERO);
        habilitaBotaoAddCaracteristica(false);
        getView().getTxtCodigo().requestFocus();
        getCaracteristicas().clear();
        fireTableDataChanged();
    }

    @Override
    public void listaItens() {
        getProdutos().clear();
        getProdutos().addAll(getProdutoService().buscar());

        if (getProdutos().isEmpty()) {
            showMessageDialog(getView(), "Não há Produtos para listar.", ATENCAO, INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Produtos", getItens(), getColunas());
    }

    private List<String> getItens() {
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

    private List<ColunaDto> getColunas() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 30));
        colunas.add(new ColunaDto("Descrição", ESQUERDA, 90));
        colunas.add(new ColunaDto("Valor(R$)", DIREITA, 50));
        colunas.add(new ColunaDto("Tipo", ESQUERDA, 50));
        colunas.add(new ColunaDto("Tamanho", ESQUERDA, 50));
        colunas.add(new ColunaDto("Marca", ESQUERDA, 50));
        return colunas;
    }

    private void preencheCamposTela(Produto produto) {
        getView().getTxtCodigo().setText(String.valueOf(produto.getId()));
        getView().getTxtDescricao().setText(produto.getNomProduto());
        getView().getTxtPreco().setValue(BigDecimal.valueOf(produto.getVlrProduto()));
        getView().getComboTamanho().setSelectedIndex(getTamanhos().indexOf(produto.getTamanho()) + 1);
        getView().getComboTipo().setSelectedIndex(getTipos().indexOf(produto.getTipo()) + 1);
        getView().getComboMarca().setSelectedIndex(getMarcas().indexOf(produto.getMarca()) + 1);
        getView().getTxtDescricao().requestFocus();
    }

    private void preencheSalvaProduto(Produto produto) throws Exception {
        produto.setNomProduto(getView().getTxtDescricao().getText());
        produto.setVlrProduto(getView().getTxtPreco().getDoubleValue());
        produto.setTamanho(getTamanhoSelecionado());
        produto.setTipo(getTipoSelecionado());
        produto.setMarca(getMarcaSelecionada());
        String msg = format("Produto %s com sucesso.", isEmpty(produto.getId()) ? "cadastrado" : "alterado");
        getProdutoService().createOrUpdate(produto);

        showMessageDialog(getView(), msg, ATENCAO, INFORMATION_MESSAGE);
        limpaTela();
    }

    private Tamanho getTamanhoSelecionado() {
        return getView().getComboTamanho().getSelectedIndex() <= ZERO ? null : getTamanhos().get(getView().getComboTamanho().getSelectedIndex() - 1);
    }

    private Tipo getTipoSelecionado() {
        return getView().getComboTipo().getSelectedIndex() <= ZERO ? null : getTipos().get(getView().getComboTipo().getSelectedIndex() - 1);
    }

    private Marca getMarcaSelecionada() {
        return getView().getComboMarca().getSelectedIndex() <= ZERO ? null : getMarcas().get(getView().getComboMarca().getSelectedIndex() - 1);
    }

    private Cor getCorSelecionada() {
        return getView().getComboCor().getSelectedIndex() <= ZERO ? null : getCores().get(getView().getComboCor().getSelectedIndex() - 1);
    }

    private void habilitaBotaoAddCaracteristica(boolean isHabilitar) {
        getView().getBtnAdicionarCaracteristica().setEnabled(isHabilitar);
    }

    public void adicionaCaracteristica() {
        getView().setSize(new Dimension(710, 480));
        getView().setPreferredSize(new Dimension(710, 480));
        escondeCamposProduto(false);
        desabilitaCamposProduto(false);
    }

    public void salvaCaracteristica() {
        Produto produto = getProdutoService().buscar(Integer.parseInt(getView().getTxtCodigo().getText()));
        CaracteristicaProduto caracteristicaProduto = new CaracteristicaProduto();
        if (isNotEmpty(codigoCaracteristica)) {
            caracteristicaProduto.setId(codigoCaracteristica);
        }
        caracteristicaProduto.setProduto(produto);
        caracteristicaProduto.setCor(getCorSelecionada());
        caracteristicaProduto.setCodBarras(getView().getTxtBarra().getText());
        caracteristicaProduto.setNumTamanho(getView().getTxtTamanho().getText());
        caracteristicaProduto.setQtdEstoque(asInteger(getView().getTxtEstoque().getText()).doubleValue());

        try {
            String msg = format("Característica do Produto %s com sucesso.", isEmpty(codigoCaracteristica) ? "cadastrada" : "alterada");
            getCaracteristicaProdutoService().createOrUpdate(caracteristicaProduto);
            showMessageDialog(getView(), msg, ATENCAO, INFORMATION_MESSAGE);
            limpaCamposCaracteristica();
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), ATENCAO, ERROR_MESSAGE);
            getView().getTxtBarra().requestFocus();
        }
    }

    public void editaCaracteristica() {
        adicionaCaracteristica();
        CaracteristicaProduto caracteristica = getCaracteristicaSelecionada();
        codigoCaracteristica = caracteristica.getId();
        getView().getComboCor().setSelectedIndex(getCores().indexOf(caracteristica.getCor()) + 1);
        getView().getTxtBarra().setText(caracteristica.getCodBarras());
        getView().getTxtEstoque().setText(String.valueOf(caracteristica.getQtdEstoque().intValue()));
        getView().getTxtTamanho().setText(caracteristica.getNumTamanho());
        getView().getTxtBarra().requestFocus();
    }

    public void excluiCaracteristica() {
        getCaracteristicaProdutoService().apagar(getCaracteristicaSelecionada());
        limpaCamposCaracteristica();
    }

    private CaracteristicaProduto getCaracteristicaSelecionada() {
        return getCaracteristicas().get(getView().getTabelaCaracteristicas().getSelectedRow());
    }

    public void editaProduto() {
        retornaValoresPadraoTela();
        getView().getTxtDescricao().requestFocus();
        habilitaBotaoAddCaracteristica(true);
    }

    public void novoProduto() {
        retornaValoresPadraoTela();
        limpaTela();
    }

    private void escondeCamposProduto(boolean isMostrar) {
        getView().getPanelBotoesProduto().setVisible(isMostrar);
        getView().getPanelCaracteristica().setVisible(!isMostrar);
        getView().getPanelBotoesCaracteristica().setVisible(!isMostrar);
    }

    private void desabilitaCamposProduto(boolean isHabilitar) {
        getView().getTxtCodigo().setEnabled(isHabilitar);
        getView().getTxtDescricao().setEnabled(isHabilitar);
        getView().getTxtPreco().setEnabled(isHabilitar);
        getView().getComboTipo().setEnabled(isHabilitar);
        getView().getComboTamanho().setEnabled(isHabilitar);
        getView().getComboMarca().setEnabled(isHabilitar);

        if (!isHabilitar) {
            getView().getTxtBarra().requestFocus();
        }
    }

    private void retornaValoresPadraoTela() {
        codigoCaracteristica = null;
        getView().setSize(new Dimension(710, 450));
        getView().setPreferredSize(new Dimension(710, 450));
        escondeCamposProduto(true);
        desabilitaCamposProduto(true);
    }

    private void limpaCamposCaracteristica() {
        codigoCaracteristica = null;
        getView().getComboCor().setSelectedIndex(ZERO);
        getView().getTxtBarra().setText(VAZIO);
        getView().getTxtTamanho().setText(VAZIO);
        getView().getTxtEstoque().setText(VAZIO);
        getView().getTxtBarra().requestFocus();
        buscaCaracteristicas();
    }

    private void buscaCaracteristicas() {
        getCaracteristicas().clear();
        getCaracteristicas().addAll(getCaracteristicaProdutoService().buscaCaracteristicasPorProduto(Integer.parseInt(getView().getTxtCodigo().getText())));
        fireTableDataChanged();
    }

    public void setView(CadastroProdutoView view) {
        this.view = view;
    }

    public CadastroProdutoView getView() {
        return view;
    }

    public List<Produto> getProdutos() {
        if (isNull(produtos)) {
            produtos = new ArrayList<>();
        }
        return produtos;
    }

    public List<Tamanho> getTamanhos() {
        if (isNull(tamanhos)) {
            tamanhos = new ArrayList<>();
        }
        return tamanhos;
    }

    public List<Tipo> getTipos() {
        if (isNull(tipos)) {
            tipos = new ArrayList<>();
        }
        return tipos;
    }

    public List<Marca> getMarcas() {
        if (isNull(marcas)) {
            marcas = new ArrayList<>();
        }
        return marcas;
    }

    public List<Cor> getCores() {
        if (isNull(cores)) {
            cores = new ArrayList<>();
        }
        return cores;
    }

    public List<CaracteristicaProduto> getCaracteristicas() {
        if (isNull(caracteristicas)) {
            caracteristicas = new ArrayList<>();
        }
        return caracteristicas;
    }

    private ProdutoService getProdutoService() {
        return ofNullable(produtoService).orElseGet(() -> produtoService = new ProdutoService());
    }

    private MarcaService getMarcaService() {
        return ofNullable(marcaService).orElseGet(() -> marcaService = new MarcaService());
    }

    private TipoService getTipoService() {
        return ofNullable(tipoService).orElseGet(() -> tipoService = new TipoService());
    }

    private TamanhoService getTamanhoService() {
        return ofNullable(tamanhoService).orElseGet(() -> tamanhoService = new TamanhoService());
    }

    private CaracteristicaProdutoService getCaracteristicaProdutoService() {
        return ofNullable(caracteristicaProdutoService).orElseGet(() -> caracteristicaProdutoService = new CaracteristicaProdutoService());
    }

    private CorService getCorService() {
        return ofNullable(corService).orElseGet(() -> corService = new CorService());
    }
}
