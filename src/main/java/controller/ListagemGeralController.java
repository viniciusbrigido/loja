package controller;

import dto.ColunaDto;
import model.bo.Produto;
import view.ListagemGeralView;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.*;
import static util.Formatador.*;
import static util.ValueUtil.*;

public class ListagemGeralController extends ListagemController {

    private static final String COLUNA_EDICAO = VAZIO;
    private static final String COLUNA_EXCLUSAO = VAZIO;

    private ListagemGeralView view;
    private List<String> itens;
    private List<ColunaDto> colunas;
    private Produto produto;
    private boolean isTelaChamadaNaVenda;
    private boolean isTelaChamadaNaFinalizacao;
    private VendaController vendaController;
    private FinalizacaoController finalizacaoController;
    private Integer tipoListagem;

    public ListagemGeralController(CadastroController parent, String titulo, List<String> itens, List<ColunaDto> colunas) {
        super(parent);
        this.itens = itens;
        this.colunas = colunas;
        setView(new ListagemGeralView(this, titulo));
        getView().setVisible(true);
        getView().getPanelProduto().setVisible(false);
        selecionaItemGrid();
    }

    public ListagemGeralController(FinalizacaoController finalizacaoController, String titulo, List<String> itens, List<ColunaDto> colunas, Integer tipoListagem) {
        this.itens = itens;
        this.colunas = colunas;
        this.finalizacaoController = finalizacaoController;
        this.tipoListagem = tipoListagem;
        isTelaChamadaNaFinalizacao = true;
        setView(new ListagemGeralView(this, titulo));
        getView().setVisible(true);
        getView().getPanelProduto().setVisible(false);
        selecionaItemGrid();
    }

    public ListagemGeralController(CadastroController parent, String titulo, List<String> itens) {
        super(parent);
        this.itens = itens;
        this.colunas = getColunasPadrao();
        setView(new ListagemGeralView(this, titulo));
        getView().setVisible(true);
        getView().getPanelProduto().setVisible(false);
        selecionaItemGrid();
    }

    public ListagemGeralController(VendaController vendaController, String titulo, List<String> itens, List<ColunaDto> colunas, Produto produto) {
        this.vendaController = vendaController;
        this.itens = itens;
        this.colunas = colunas;
        this.produto = produto;
        isTelaChamadaNaVenda = true;
        setView(new ListagemGeralView(this, titulo));
        getView().setVisible(true);
        getView().getPanelProduto().setVisible(isNotNull(produto));
        atualizaTelaChamadaVenda();
        selecionaItemGrid();
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
        if (getView().getTableGrid().getSelectedRow() < 0) {
            showMessageDialog(getView(), "Selecione um item para carregá-lo.", "Atenção", ERROR_MESSAGE);
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
        if (getView().getTableGrid().getSelectedRow() < 0) {
            showMessageDialog(getView(), "Selecione um item para excluir.", "Atenção", ERROR_MESSAGE);
            return;
        }
        if (showYesNoConfirmDialog(getView(), "Deseja realmente exlcuir o item?", "Atenção") == NO_OPTION) {
            return;
        }

        getItens().remove(getView().getTableGrid().getSelectedRow());
        index = getView().getTableGrid().getSelectedRow();
        getParent().excluiItem();

        if (getItens().isEmpty()) {
            getView().closeView();
            getParent().limpaTela();
        } else {
            getView().fireTableDataChanged();
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

    public ListagemGeralView getView() {
        return view;
    }

    public void setView(ListagemGeralView view) {
        this.view = view;
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
