package controller;

import model.bo.Marca;
import service.*;
import view.CadastroMarcaView;
import java.util.ArrayList;
import java.util.List;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.ValueUtil.*;
import static util.ValueUtil.VAZIO;

public class CadastroMarcaController extends CadastroController {

    private CadastroMarcaView view;
    private List<Marca> marcas;
    private MarcaService marcaService;
    private ProdutoService produtoService;

    public CadastroMarcaController(CadastroMarcaView view) {
        super(view);
        setView(view);
        getView().setVisible(true);
    }

    @Override
    public void excluiItem() {
        Marca marca = getMarcas().get(index);
        if (getProdutoService().isProdutoCadastradoComMarca(marca.getId())) {
            showMessageDialog(getView(), "O item não pode ser excluído pois já há um ou mais Endereços cadastrados com esse Bairro.", "Atenção", ERROR_MESSAGE);
            return;
        }
        getMarcaService().apagar(marca);
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getMarcas().get(index));
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
            showMessageDialog(getView(), "Código: Valor Inválido.", "Atenção", ERROR_MESSAGE);
            limpaTela();
            return;
        }

        Marca marca = getMarcaService().buscar(codigo);
        if (isNotNull(marca) && isNotEmpty(marca.getId())) {
            preencheCamposTela(marca);
        } else {
            showMessageDialog(getView(), "Marca não encontrada.", "Atenção", ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaMarca(new Marca());
            } else {
                verificaMarcaJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), "Atenção", ERROR_MESSAGE);
            getView().getTxtDescricao().requestFocus();
        }
    }

    private void verificaMarcaJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", "Atenção", ERROR_MESSAGE);
            limpaTela();
            return;
        }

        Marca marca = getMarcaService().buscar(codigo);
        if (isNotNull(marca) && isNotEmpty(marca.getId())) {
            preencheSalvaMarca(marca);
            return;
        }
        showMessageDialog(getView(), "Código não encontrado.\nPara cadastrar uma nova Marca não preencha o campo de código.", "Atenção", ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getMarcas().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtDescricao().setText(VAZIO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getMarcas().clear();
        getMarcas().addAll(getMarcaService().buscar());

        if (getMarcas().isEmpty()) {
            showMessageDialog(getView(), "Não há Marcas para listar.", "Atenção", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Marcas", getItens());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Marca marca : getMarcas()) {
            StringBuilder sb = new StringBuilder();
            sb.append(marca.getId()).append(SEPARADOR)
              .append(marca.getNomMarca());
            itens.add(sb.toString());
        }
        return itens;
    }

    private void preencheCamposTela(Marca marca) {
        getView().getTxtCodigo().setText(String.valueOf(marca.getId()));
        getView().getTxtDescricao().setText(marca.getNomMarca());
        getView().getTxtDescricao().requestFocus();
    }

    private void preencheSalvaMarca(Marca marca) throws Exception {
        marca.setNomMarca(getView().getTxtDescricao().getText());
        String msg = format("Marca %s com sucesso.", isEmpty(marca.getId()) ? "cadastrada" : "alterada");
        getMarcaService().createOrUpdate(marca);

        showMessageDialog(getView(), msg, "Atenção", INFORMATION_MESSAGE);
        limpaTela();
    }

    public CadastroMarcaView getView() {
        return view;
    }

    public void setView(CadastroMarcaView view) {
        this.view = view;
    }

    public List<Marca> getMarcas() {
        if (isNull(marcas)) {
            marcas = new ArrayList<>();
        }
        return marcas;
    }

    private MarcaService getMarcaService() {
        return ofNullable(marcaService).orElseGet(() -> marcaService = new MarcaService());
    }

    private ProdutoService getProdutoService() {
        return ofNullable(produtoService).orElseGet(() -> produtoService = new ProdutoService());
    }
}
