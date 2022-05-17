package controller;

import model.bo.Cor;
import service.CaracteristicaProdutoService;
import service.CorService;
import view.CadastroCorView;
import java.util.*;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.ValueUtil.*;

public class CadastroCorController extends CadastroController {

    private CadastroCorView view;
    private List<Cor> cores;
    private CorService corService;
    private CaracteristicaProdutoService caracteristicaProdutoService;

    public CadastroCorController(CadastroCorView view) {
        super(view);
        setView(view);
        getView().setVisible(true);
    }

    @Override
    public void excluiItem() {
        Cor cor = getCores().get(index);
        if (getCaracteristicaProdutoService().isCaracteristicaCadastradaComCor(cor.getId())) {
            showMessageDialog(getView(), "O item n�o pode ser exclu�do pois j� h� um ou mais Caracter�sticas de Produto cadastradas com essa Cor.", ATENCAO, ERROR_MESSAGE);
            return;
        }
        getCorService().apagar(cor);
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getCores().get(index));
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
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Cor cor = getCorService().buscar(codigo);
        if (isNotNull(cor) && isNotEmpty(cor.getId())) {
            preencheCamposTela(cor);
        } else {
            showMessageDialog(getView(), "Cor n�o encontrada.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                cadastraCorNova();
            } else {
                verificaCorJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), ATENCAO, ERROR_MESSAGE);
            getView().getTxtDescricao().requestFocus();
        }
    }


    private void verificaCorJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Cor cor = getCorService().buscar(codigo);
        if (isNotNull(cor) && isNotEmpty(cor.getId())) {
            preencheSalvaCor(cor);
            return;
        }
        showMessageDialog(getView(), "C�digo n�o encontrado.\nPara cadastrar uma nova Cor n�o preencha o campo de c�digo.", ATENCAO, ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getCores().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtDescricao().setText(VAZIO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getCores().clear();
        getCores().addAll(getCorService().buscar());

        if (getCores().isEmpty()) {
            showMessageDialog(getView(), "N�o h� Cores para listar.", ATENCAO, INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Cores", getItens());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Cor cor : getCores()) {
            StringBuilder sb = new StringBuilder();
            sb.append(cor.getId()).append(SEPARADOR)
              .append(cor.getNomCor());
            itens.add(sb.toString());
        }
        return itens;
    }

    private void cadastraCorNova() throws Exception {
        Cor cor = new Cor();
        preencheSalvaCor(cor);
    }

    private void preencheCamposTela(Cor cor) {
        getView().getTxtCodigo().setText(String.valueOf(cor.getId()));
        getView().getTxtDescricao().setText(cor.getNomCor());
        getView().getTxtDescricao().requestFocus();
    }

    private void preencheSalvaCor(Cor cor) throws Exception {
        cor.setNomCor(getView().getTxtDescricao().getText());
        String msg = format("Cor %s com sucesso.", isEmpty(cor.getId()) ? "cadastrada" : "alterada");
        getCorService().createOrUpdate(cor);

        showMessageDialog(getView(), msg, ATENCAO, INFORMATION_MESSAGE);
        limpaTela();
    }

    public CadastroCorView getView() {
        return view;
    }

    public void setView(CadastroCorView view) {
        this.view = view;
    }

    public List<Cor> getCores() {
        if (isNull(cores)) {
            cores = new ArrayList<>();
        }
        return cores;
    }

    private CorService getCorService() {
        return ofNullable(corService).orElseGet(() -> corService = new CorService());
    }

    private CaracteristicaProdutoService getCaracteristicaProdutoService() {
        return ofNullable(caracteristicaProdutoService).orElseGet(() -> caracteristicaProdutoService = new CaracteristicaProdutoService());
    }
}
