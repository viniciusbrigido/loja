package controller;

import model.bo.Tamanho;
import service.ProdutoService;
import service.TamanhoService;
import view.CadastroTamanhoView;
import java.util.*;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.ValueUtil.*;
import static util.ValueUtil.VAZIO;

public class CadastroTamanhoController extends CadastroController {

    private CadastroTamanhoView view;
    private List<Tamanho> tamanhos;
    private TamanhoService tamanhoService;
    private ProdutoService produtoService;

    public CadastroTamanhoController() {
        super();
        setView(new CadastroTamanhoView(this));
        getView().setVisible(true);
    }

    @Override
    public void excluiItem() {
        Tamanho tamanho = getTamanhos().get(index);
        if (getProdutoService().isProdutoCadastradoComTamanho(tamanho.getId())) {
            showMessageDialog(getView(), "O item n�o pode ser exclu�do pois j� h� um ou mais Produtos cadastrados com esse Tamanho.", "Aten��o", ERROR_MESSAGE);
            return;
        }
        getTamanhoService().apagar(tamanho);
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getTamanhos().get(index));
    }

    public void buscaTamanho() {
        if (isEmpty(getView().getTxtCodigo().getText())) {
            limpaTela();
            return;
        }
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", "Aten��o", ERROR_MESSAGE);
            limpaTela();
            return;
        }

        Tamanho tamanho = getTamanhoService().buscar(codigo);
        if (isNotNull(tamanho) && isNotEmpty(tamanho.getId())) {
            preencheCamposTela(tamanho);
        } else {
            showMessageDialog(getView(), "Tamanho n�o encontrado.", "Aten��o", ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaTamanho(new Tamanho());
            } else {
                verificaTamanhoJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), "Aten��o", ERROR_MESSAGE);
            getView().getTxtDescricao().requestFocus();
        }
    }

    private void verificaTamanhoJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", "Aten��o", ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Tamanho tamanho = getTamanhoService().buscar(codigo);
        if (isNotNull(tamanho) && isNotEmpty(tamanho.getId())) {
            preencheSalvaTamanho(tamanho);
            return;
        }
        showMessageDialog(getView(), "C�digo n�o encontrado.\nPara cadastrar um novo Tamanho n�o preencha o campo de c�digo.", "Aten��o", ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getTamanhos().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtDescricao().setText(VAZIO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getTamanhos().clear();
        getTamanhos().addAll(getTamanhoService().buscar());

        if (getTamanhos().isEmpty()) {
            showMessageDialog(getView(), "N�o h� Tamanhos para listar.", "Aten��o", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Tamanhos", getItens());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Tamanho tamanho : getTamanhos()) {
            StringBuilder sb = new StringBuilder();
            sb.append(tamanho.getId()).append(SEPARADOR)
              .append(tamanho.getNomTamanho());
            itens.add(sb.toString());
        }
        return itens;
    }

    private void preencheCamposTela(Tamanho tamanho) {
        getView().getTxtCodigo().setText(String.valueOf(tamanho.getId()));
        getView().getTxtDescricao().setText(tamanho.getNomTamanho());
        getView().getTxtDescricao().requestFocus();
    }

    private void preencheSalvaTamanho(Tamanho tamanho) throws Exception {
        tamanho.setNomTamanho(getView().getTxtDescricao().getText());
        getTamanhoService().createOrUpdate(tamanho);

        showMessageDialog(getView(), format("Tamanho %s com sucesso.", isEmpty(tamanho.getId()) ? "cadastrado" : "alterado"), "Aten��o", INFORMATION_MESSAGE);
        limpaTela();
    }

    public void setView(CadastroTamanhoView view) {
        this.view = view;
    }

    public CadastroTamanhoView getView() {
        return view;
    }

    public List<Tamanho> getTamanhos() {
        if (isNull(tamanhos)) {
            tamanhos = new ArrayList<>();
        }
        return tamanhos;
    }

    private TamanhoService getTamanhoService() {
        return ofNullable(tamanhoService).orElseGet(() -> tamanhoService = new TamanhoService());
    }

    private ProdutoService getProdutoService() {
        return ofNullable(produtoService).orElseGet(() -> produtoService = new ProdutoService());
    }
}