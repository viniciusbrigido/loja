package controller;

import dto.ColunaDto;
import lombok.Getter;
import lombok.Setter;
import model.bo.Produto;
import view.FinalizacaoView;
import view.ListagemGeralView;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import static java.awt.Cursor.*;
import static java.awt.event.KeyEvent.*;
import static javax.swing.JOptionPane.*;
import static javax.swing.KeyStroke.getKeyStroke;
import static util.Formatador.*;
import static util.ValueUtil.*;

public class ListagemGeralController extends ListagemController {

    private static final String COLUNA_EDICAO = VAZIO;
    private static final String COLUNA_EXCLUSAO = VAZIO;

    @Getter @Setter
    private ListagemGeralView view;

    private List<String> itens;
    private List<ColunaDto> colunas;

    private Produto produto;
    private boolean isTelaChamadaNaVenda;
    private boolean isTelaChamadaNaFinalizacao;

    private VendaController vendaController;
    private FinalizacaoController finalizacaoController;
    private Integer tipoListagem;

    private AbstractTableModel grid;

    public ListagemGeralController(CadastroController parent, String titulo, List<String> itens, List<ColunaDto> colunas) {
        super(parent);
        this.itens = itens;
        this.colunas = colunas;
        setView(new ListagemGeralView(titulo));
        getView().setVisible(true);
        adicionaRegrasView();
        getView().getPanelProduto().setVisible(false);
        fireTableDataChanged();
        selecionaItemGrid();
    }

    public ListagemGeralController(FinalizacaoController finalizacaoController, String titulo, List<String> itens, List<ColunaDto> colunas, Integer tipoListagem) {
        this.itens = itens;
        this.colunas = colunas;
        this.finalizacaoController = finalizacaoController;
        this.tipoListagem = tipoListagem;
        isTelaChamadaNaFinalizacao = true;
        setView(new ListagemGeralView(titulo));
        getView().setVisible(true);
        adicionaRegrasView();
        getView().getPanelProduto().setVisible(false);
        fireTableDataChanged();
        selecionaItemGrid();
    }

    public ListagemGeralController(CadastroController parent, String titulo, List<String> itens) {
        super(parent);
        this.itens = itens;
        this.colunas = getColunasPadrao();
        setView(new ListagemGeralView(titulo));
        getView().setVisible(true);
        adicionaRegrasView();
        getView().getPanelProduto().setVisible(false);
        fireTableDataChanged();
        selecionaItemGrid();
    }

    public ListagemGeralController(VendaController vendaController, String titulo, List<String> itens, List<ColunaDto> colunas, Produto produto) {
        this.vendaController = vendaController;
        this.itens = itens;
        this.colunas = colunas;
        this.produto = produto;
        isTelaChamadaNaVenda = true;
        setView(new ListagemGeralView(titulo));
        getView().setVisible(true);
        adicionaRegrasView();
        getView().getPanelProduto().setVisible(isNotNull(produto));
        atualizaTelaChamadaVenda();
        fireTableDataChanged();
        selecionaItemGrid();
    }

    private void adicionaRegrasView() {
        adicionaAcaoAosBotoes();
        adicionaAcoesGrid();
    }

    private void adicionaAcaoAosBotoes() {
        adicionaAcaoBotaoCarregar();
        adicionaAcaoBotaoExcluir();
        adicionaAcaoBotaoSair();
    }

    private void adicionaAcaoBotaoCarregar() {
        getView().getBtnCarregar().addActionListener(a -> selecionaItem());
        getView().getBtnCarregar().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F1, ZERO), EVENTO);
        getView().getBtnCarregar().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnCarregar().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionaItem();
            }
        });
        if (isTelaChamadaNaVendaOuFinalizacao()) {
            getView().getBtnCarregar().setText("Selecionar [F1]");
        }
    }

    private void adicionaAcaoBotaoExcluir() {
        getView().getBtnExcluir().addActionListener(a -> excluiItem());
        getView().getBtnExcluir().getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F3, ZERO), EVENTO);
        getView().getBtnExcluir().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getBtnExcluir().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluiItem();
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

    private void adicionaAcoesGrid() {
        getView().getTableGrid().setModel(getGrid());
        adicionaRegrasAlinhamentoGrid();
    }

    private void adicionaRegrasAlinhamentoGrid() {
        TableColumnModel columnModel = getView().getTableGrid().getColumnModel();
        for (ColunaDto dto : getColunas()) {
            DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
            defaultTableCellRenderer.setHorizontalAlignment(dto.getAlinhamento());
            int coluna = getColunas().indexOf(dto);

            columnModel.getColumn(coluna).setCellRenderer(defaultTableCellRenderer);
            columnModel.getColumn(coluna).setPreferredWidth(dto.getTamanho());
        }

        getView().getTableGrid().getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        getView().getTableGrid().getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionaItem();
            }
        });

        if (!isTelaChamadaNaVendaOuFinalizacao()) {
            DefaultTableCellRenderer dtcrEditar = new DefaultTableCellRenderer();
            dtcrEditar.setIcon(new ImageIcon(getClass().getResource("/imagens/editar.png")));
            columnModel.getColumn(getColunaEditar()).setCellRenderer(dtcrEditar);
            columnModel.getColumn(getColunaEditar()).setMaxWidth(20);

            DefaultTableCellRenderer dtcrExcluir = new DefaultTableCellRenderer();
            dtcrExcluir.setIcon(new ImageIcon(getClass().getResource("/imagens/remover.png")));
            columnModel.getColumn(getColunaExcluir()).setCellRenderer(dtcrExcluir);
            columnModel.getColumn(getColunaExcluir()).setMaxWidth(20);

            getView().getTableGrid().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    int colunaSelecionada = ((JTable) e.getSource()).getSelectedColumn();

                    if (colunaSelecionada == getColunaEditar()) {
                        selecionaItem();
                    } else if (colunaSelecionada == getColunaExcluir()) {
                        excluiItem();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    getView().setCursor(getDefaultCursor());
                }
            });

            getView().getTableGrid().addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    getView().setCursor(isIconeExcluirEditarSelecionado(e) ? new Cursor(HAND_CURSOR) : getDefaultCursor());
                }
            });
        }
    }

    private boolean isIconeExcluirEditarSelecionado(MouseEvent e) {
        int colunaSelecionada = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
        return colunaSelecionada == getColunaEditar() || colunaSelecionada == getColunaExcluir();
    }

    public AbstractTableModel getGrid() {
        if (grid == null) {
            grid = new AbstractTableModel() {
                public final String[] columnNames = getNomeColunas();

                public int getColumnCount() {
                    return columnNames.length;
                }

                public String getColumnName(int column) {
                    return columnNames[column];
                }

                public int getRowCount() {
                    return getItens().size();
                }

                public Object getValueAt(int row, int column) {
                    List<String> linhas = getItens();
                    String[] linha = linhas.get(row).split(SEPARADOR);
                    if (column == getColunaEditar() || column == getColunaExcluir()) {
                        return null;
                    }
                    return linha[column];
                }
            };
        }
        return grid;
    }

    private int getColunaEditar() {
        return getColunas().size();
    }

    private int getColunaExcluir() {
        return getColunas().size() + 1;
    }

    public void fireTableDataChanged() {
        getGrid().fireTableDataChanged();
    }

    private void selecionaItemGrid() {
        getView().getTableGrid().requestFocus();
        getView().getTableGrid().setRowSelectionInterval(ZERO, ZERO);
    }

    public List<ColunaDto> getColunasPadrao() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 50));
        colunas.add(new ColunaDto("Descrição", ESQUERDA, 250));
        return colunas;
    }

    @Override
    public void selecionaItem() {
        if (getView().getTableGrid().getSelectedRow() < ZERO) {
            showMessageDialog(getView(), "Selecione um item para carregá-lo.", ATENCAO, ERROR_MESSAGE);
            return;
        }
        Integer selectedRow = getView().getTableGrid().getSelectedRow();
        if (isTelaChamadaNaVenda) {
            selecionaItemVenda(selectedRow);
        } else if (isTelaChamadaNaFinalizacao) {
            selecionaItemFinalizacao(selectedRow);
        } else {
            selecionaItemTelasListagem(selectedRow);
        }
        getView().closeView();
    }

    private void selecionaItemVenda(Integer selectedRow) {
        if (isNull(produto)) {
            vendaController.setProduto(selectedRow);
        } else {
            vendaController.setCaracteristica(selectedRow);
        }
    }

    private void selecionaItemFinalizacao(Integer selectedRow) {
        if (isTelaChamadaNaFinalizacaoPeloVendedor()) {
            finalizacaoController.preencheVendedor(selectedRow);
        } else if (isTelaChamadaNaFinalizacaoPeloCliente()) {
            finalizacaoController.preencheCliente(selectedRow);
        } else {
            finalizacaoController.preencheCondicao(selectedRow);
        }
    }

    private void selecionaItemTelasListagem(Integer selectedRow) {
        index = selectedRow;
        getParent().preencheItem();
    }

    private boolean isTelaChamadaNaFinalizacaoPeloVendedor() {
        return TIPO_LISTAGEM_VENDEDOR.equals(tipoListagem);
    }

    private boolean isTelaChamadaNaFinalizacaoPeloCliente() {
        return TIPO_LISTAGEM_CLIENTE.equals(tipoListagem);
    }

    @Override
    public void excluiItem() {
        if (getView().getTableGrid().getSelectedRow() < ZERO) {
            showMessageDialog(getView(), "Selecione um item para excluir.", ATENCAO, ERROR_MESSAGE);
            return;
        }
        if (showYesNoConfirmDialog(getView(), "Deseja realmente excluir o item?", ATENCAO) == NO_OPTION) {
            return;
        }

        index = getView().getTableGrid().getSelectedRow();
        boolean isExclusaoCompleta = getParent().excluiItem();
        if (isExclusaoCompleta) {
            getItens().remove(getView().getTableGrid().getSelectedRow());
        }

        if (getItens().isEmpty()) {
            getView().closeView();
            getParent().limpaTela();
        } else {
            fireTableDataChanged();
        }
    }

    @Override
    public void sairTela() {
        if (isTelaChamadaNaVenda) {
            vendaController.limpaCamposProduto();
        }
        getView().dispose();
    }

    private void atualizaTelaChamadaVenda() {
        if (isNull(produto)) {
            return;
        }
        getView().setVisible(true);
        getView().setSize(new Dimension(650, 450));
        getView().setPreferredSize(new Dimension(650, 450));
        prencheCamposProduto(produto);
        getView().getBtnExcluir().setVisible(false);
    }

    private void prencheCamposProduto(Produto produto) {
        getView().getTxtProduto().setText(String.format("%s - %s", produto.getId(), produto.getNomProduto()));
        getView().getTxtPreco().setText(formataDouble(produto.getVlrProduto()));
    }

    public List<String> getItens() {
        return itens;
    }

    public List<ColunaDto> getColunas() {
        return colunas;
    }

    public boolean isTelaChamadaNaVendaOuFinalizacao() {
        return isTelaChamadaNaVenda || isTelaChamadaNaFinalizacao;
    }

    public String[] getNomeColunas() {
        List<String> nomeColunas = new ArrayList<>();
        colunas.forEach(coluna -> nomeColunas.add(coluna.getNome()));
        if (!isTelaChamadaNaVenda && !isTelaChamadaNaFinalizacao) {
            nomeColunas.add(COLUNA_EDICAO);
            nomeColunas.add(COLUNA_EXCLUSAO);
        }
        return nomeColunas.toArray(new String[nomeColunas.size()]);
    }
}
