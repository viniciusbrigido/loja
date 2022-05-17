package controller;

import dto.ColunaDto;
import lombok.Getter;
import lombok.Setter;
import model.bo.CondicaoPagamento;
import service.*;
import view.CadastroCondicaoPagamentoView;
import java.util.*;
import static java.lang.String.format;
import static java.util.Optional.*;
import static javax.swing.JOptionPane.*;
import static util.ValueUtil.*;

public class CadastroCondicaoPagamentoController extends CadastroController {

    @Getter @Setter
    private CadastroCondicaoPagamentoView view;

    private List<CondicaoPagamento> condicoes;
    private CondicaoPagamentoService condicaoPagamentoService;

    public CadastroCondicaoPagamentoController(CadastroCondicaoPagamentoView view) {
        super(view);
        setView(view);
        getView().setVisible(true);
    }

    @Override
    public boolean excluiItem() {
        getCondicaoPagamentoService().apagar(getCondicoes().get(index));
        return true;
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getCondicoes().get(index));
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

        CondicaoPagamento condicao = getCondicaoPagamentoService().buscar(codigo);
        if (isNotNull(condicao) && isNotEmpty(condicao.getId())) {
            preencheCamposTela(condicao);
        } else {
            showMessageDialog(getView(), "Condição de Pagamento não encontrada.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaCondicao(new CondicaoPagamento());
            } else {
                verificaCondicaoJaCadastrada();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), ATENCAO, ERROR_MESSAGE);
            getView().getTxtDescricao().requestFocus();
        }
    }

    private void verificaCondicaoJaCadastrada() throws Exception {
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
            return;
        }

        CondicaoPagamento condicao = getCondicaoPagamentoService().buscar(codigo);
        if (isNotNull(condicao) && isNotEmpty(condicao.getId())) {
            preencheSalvaCondicao(condicao);
            return;
        }
        showMessageDialog(getView(), "Código não encontrado.\nPara cadastrar uma nova Condição de Pagamento não preencha o campo de código.", ATENCAO, ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getCondicoes().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtDescricao().setText(VAZIO);
        getView().getTxtNumDiasAtePrimeiraParcela().setText(VAZIO);
        getView().getTxtNumDiasEntreParcelas().setText(VAZIO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getCondicoes().clear();
        getCondicoes().addAll(getCondicaoPagamentoService().buscar());

        if (getCondicoes().isEmpty()) {
            showMessageDialog(getView(), "Não há Condições de Pagamento para listar.", ATENCAO, INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Condições de Pagamento", getItens(), getColunas());
    }

    private List<String> getItens() {
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

    private List<ColunaDto> getColunas() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 30));
        colunas.add(new ColunaDto("Descrição", ESQUERDA, 130));
        colunas.add(new ColunaDto("Dias Até 1ª Parcela", DIREITA, 70));
        colunas.add(new ColunaDto("Dias Entre Parcelas", DIREITA, 70));
        return colunas;
    }

    private void preencheCamposTela(CondicaoPagamento condicao) {
        getView().getTxtCodigo().setText(String.valueOf(condicao.getId()));
        getView().getTxtDescricao().setText(condicao.getNomCondicaoPagamento());
        getView().getTxtNumDiasAtePrimeiraParcela().setText(String.valueOf(condicao.getNumDiasAtePrimeiraParcela()));
        getView().getTxtNumDiasEntreParcelas().setText(String.valueOf(condicao.getNumDiasEntreParcelas()));
        getView().getTxtDescricao().requestFocus();
    }

    private void preencheSalvaCondicao(CondicaoPagamento condicao) throws Exception {
        condicao.setNomCondicaoPagamento(getView().getTxtDescricao().getText());
        condicao.setNumDiasAtePrimeiraParcela(asInteger(getView().getTxtNumDiasAtePrimeiraParcela().getText()));
        condicao.setNumDiasEntreParcelas(asInteger(getView().getTxtNumDiasEntreParcelas().getText()));
        String msg = format("Condição de Pagamento %s com sucesso.", isEmpty(condicao.getId()) ? "cadastrada" : "alterada");
        getCondicaoPagamentoService().createOrUpdate(condicao);

        showMessageDialog(getView(), msg, ATENCAO, INFORMATION_MESSAGE);
        limpaTela();
    }

    private List<CondicaoPagamento> getCondicoes() {
        if (isNull(condicoes)) {
            condicoes = new ArrayList<>();
        }
        return condicoes;
    }

    private CondicaoPagamentoService getCondicaoPagamentoService() {
        return ofNullable(condicaoPagamentoService).orElseGet(() -> condicaoPagamentoService = new CondicaoPagamentoService());
    }
}
