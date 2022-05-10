package controller;

import model.bo.*;
import service.*;
import view.CadastroTipoView;
import java.util.*;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.ValueUtil.*;
import static util.ValueUtil.VAZIO;

public class CadastroTipoController extends CadastroController {

    private CadastroTipoView view;
    private List<Tipo> tipos;
    private TipoService tipoService;
    private ProdutoService produtoService;

    public CadastroTipoController() {
        super();
        setView(new CadastroTipoView(this));
        getView().setVisible(true);
    }

    @Override
    public void excluiItem() {
        Tipo tipo = getTipos().get(index);
        if (getProdutoService().isProdutoCadastradoComTipo(tipo.getId())) {
            showMessageDialog(getView(), "O item n�o pode ser exclu�do pois j� h� um ou mais Produtos cadastrados com esse Tipo de Produto.", "Aten��o", ERROR_MESSAGE);
            return;
        }
        getTipoService().apagar(tipo);
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getTipos().get(index));
    }

    public void buscaTipoProduto() {
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
        Tipo tipo = getTipoService().buscar(codigo);
        if (isNotNull(tipo) && isNotEmpty(tipo.getId())) {
            preencheCamposTela(tipo);
        } else {
            showMessageDialog(getView(), "Tipo de Produto n�o encontrado.", "Aten��o", ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaTipo(new Tipo());
            } else {
                verificaTipoJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), "Aten��o", ERROR_MESSAGE);
            getView().getTxtDescricao().requestFocus();
        }
    }

    private void verificaTipoJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", "Aten��o", ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Tipo tipo = getTipoService().buscar(codigo);
        if (isNotNull(tipo) && isNotEmpty(tipo.getId())) {
            preencheSalvaTipo(tipo);
            return;
        }
        showMessageDialog(getView(), "C�digo n�o encontrado.\nPara cadastrar um novo Tipo de Produto n�o preencha o campo de c�digo.", "Aten��o", ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getTipos().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtDescricao().setText(VAZIO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getTipos().clear();
        getTipos().addAll(getTipoService().buscar());

        if (getTipos().isEmpty()) {
            showMessageDialog(getView(), "N�o h� Tipos de Produto para listar.", "Aten��o", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Tipos de Produto", getItens());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Tipo tipo : getTipos()) {
            StringBuilder sb = new StringBuilder();
            sb.append(tipo.getId()).append(SEPARADOR)
              .append(tipo.getNomTipo());
            itens.add(sb.toString());
        }
        return itens;
    }

    private void preencheCamposTela(Tipo tipo) {
        getView().getTxtCodigo().setText(String.valueOf(tipo.getId()));
        getView().getTxtDescricao().setText(tipo.getNomTipo());
        getView().getTxtDescricao().requestFocus();
    }

    private void preencheSalvaTipo(Tipo tipo) throws Exception {
        tipo.setNomTipo(getView().getTxtDescricao().getText());
        String msg = format("Tipo de Produto %s com sucesso.", isEmpty(tipo.getId()) ? "cadastrado" : "alterado");
        getTipoService().createOrUpdate(tipo);

        showMessageDialog(getView(), msg, "Aten��o", INFORMATION_MESSAGE);
        limpaTela();
    }

    public void setView(CadastroTipoView view) {
        this.view = view;
    }

    public CadastroTipoView getView() {
        return view;
    }

    public List<Tipo> getTipos() {
        if (isNull(tipos)) {
            tipos = new ArrayList<>();
        }
        return tipos;
    }

    private TipoService getTipoService() {
        return ofNullable(tipoService).orElseGet(() -> tipoService = new TipoService());
    }

    private ProdutoService getProdutoService() {
        return ofNullable(produtoService).orElseGet(() -> produtoService = new ProdutoService());
    }
}
