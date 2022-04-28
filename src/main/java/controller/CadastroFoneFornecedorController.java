package controller;

import model.bo.FoneFornecedor;
import model.bo.Fornecedor;
import service.FoneFornecedorService;
import view.CadastroFoneFornecedorView;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.ValueUtil.*;

public class CadastroFoneFornecedorController extends CadastroController {

    private CadastroFoneFornecedorView view;
    private Fornecedor fornecedor;
    private List<FoneFornecedor> fones;
    private FoneFornecedorService foneFornecedorService;

    public CadastroFoneFornecedorController(Fornecedor fornecedor) {
        super();
        this.fornecedor = fornecedor;
        setFones(getFoneFornecedorService().buscaFonesPorFornecedor(fornecedor.getId()));
        setView(new CadastroFoneFornecedorView(this));
        getView().setVisible(true);
        preencheFornecedor();
        getView().getTxtFone().requestFocus();
    }

    private void preencheFornecedor() {
        if (isNull(fornecedor)) {
            return;
        }
        getView().getTxtFornecedor().setText(String.format("%s - %s", fornecedor.getId(), fornecedor.getNomFornecedor()));
    }

    public void excluiFornecedor(int index) {
        if (showYesNoConfirmDialog(getView(), "Deseja realmente excluir o item?", "Atenção") == NO_OPTION) {
            return;
        }
        getFoneFornecedorService().apagar(getFones().get(index));
        getFones().remove(index);
        limpaTela();
    }

    @Override
    public void cadastraNovoItem() {
        if (isEmpty(getFoneSemMascara())) {
            showMessageDialog(getView(), "Fone: Campo com preenchimento Obrigatório.", "Atenção", ERROR_MESSAGE);
            getView().getTxtFone().requestFocus();
            return;
        }
        if (getFoneSemMascara().length() != 11) {
            showMessageDialog(getView(), "Fone: Valor Inválido.", "Atenção", ERROR_MESSAGE);
            limpaTela();
            return;
        }
        FoneFornecedor fone = new FoneFornecedor();
        fone.setFornecedor(fornecedor);
        fone.setNumFone(getFoneSemMascara());
        getFones().add(fone);
        getFoneFornecedorService().salvar(fone);

        showMessageDialog(getView(), "Fone cadastrado com sucesso.", "Atenção", INFORMATION_MESSAGE);
        limpaTela();
    }

    private String getFoneSemMascara() {
        return getView().getTxtFone().getText()
                .replaceAll("\\(", VAZIO)
                .replaceAll("\\)", VAZIO)
                .replaceAll("-", VAZIO);
    }

    @Override
    public void limpaTela() {
        getView().fireTableDataChanged();
        getView().getTxtFone().setText(VAZIO);
        getView().getTxtFone().requestFocus();
    }

    @Override
    public void listaItens() {
    }

    @Override
    public void preencheItem() {
    }

    @Override
    public void excluiItem() {
    }

    public List<FoneFornecedor> getFones() {
        if (isNull(fones)) {
            fones = new ArrayList<>();
        }
        return fones;
    }

    public void setFones(List<FoneFornecedor> fones) {
        this.fones = fones;
    }

    public CadastroFoneFornecedorView getView() {
        return view;
    }

    public void setView(CadastroFoneFornecedorView view) {
        this.view = view;
    }

    private FoneFornecedorService getFoneFornecedorService() {
        return ofNullable(foneFornecedorService).orElseGet(() -> foneFornecedorService = new FoneFornecedorService());
    }
}
