package controller;

import lombok.Getter;
import lombok.Setter;
import model.bo.Bairro;
import service.*;
import view.CadastroBairroView;
import java.util.*;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.ValueUtil.*;

public class CadastroBairroController extends CadastroController {

    @Getter @Setter
    private CadastroBairroView view;

    private List<Bairro> bairros;
    private BairroService bairroService;
    private EnderecoService enderecoService;

    public CadastroBairroController(CadastroBairroView view) {
        super(view);
        setView(view);
        getView().setVisible(true);
    }

    @Override
    public boolean excluiItem() {
        Bairro bairro = getBairros().get(index);
        if (getEnderecoService().isEnderecoCadastradoComBairro(bairro.getId())) {
            showMessageDialog(getView(), "O item n�o pode ser exclu�do pois j� h� um ou mais Endereços cadastrados com essa Marca.", ATENCAO, ERROR_MESSAGE);
            return false;
        }
        getBairroService().apagar(bairro);
        return true;
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getBairros().get(index));
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

        Bairro bairro = getBairroService().buscar(codigo);
        if (isNotNull(bairro) && isNotEmpty(bairro.getId())) {
            preencheCamposTela(bairro);
        } else {
            showMessageDialog(getView(), "Bairro n�o encontrado.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                cadastraBairroNovo();
            } else {
                verificaBairroJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), ATENCAO, ERROR_MESSAGE);
            getView().getTxtDescricao().requestFocus();
        }
    }

    private void verificaBairroJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "C�digo: Valor Inv�lido.", ATENCAO, ERROR_MESSAGE);
            limpaTela();
            return;
        }

        Bairro bairro = getBairroService().buscar(codigo);
        if (isNotNull(bairro) && isNotEmpty(bairro.getId())) {
            preencheSalvaBairro(bairro);
            return;
        }
        showMessageDialog(getView(), "C�digo n�o encontrado.\nPara cadastrar um novo Bairro n�o preencha o campo de c�digo.", ATENCAO, ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getBairros().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtDescricao().setText(VAZIO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getBairros().clear();
        getBairros().addAll(getBairroService().buscar());

        if (getBairros().isEmpty()) {
            showMessageDialog(getView(), "N�o h� Bairros para listar.", ATENCAO, INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Bairros", getItens());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Bairro bairro : getBairros()) {
            StringBuilder sb = new StringBuilder();
            sb.append(bairro.getId()).append(SEPARADOR)
              .append(bairro.getNomBairro());
            itens.add(sb.toString());
        }
        return itens;
    }

    private void cadastraBairroNovo() throws Exception {
        Bairro bairro = new Bairro();
        preencheSalvaBairro(bairro);
    }

    private void preencheCamposTela(Bairro bairro) {
        getView().getTxtCodigo().setText(String.valueOf(bairro.getId()));
        getView().getTxtDescricao().setText(bairro.getNomBairro());
        getView().getTxtDescricao().requestFocus();
    }

    private void preencheSalvaBairro(Bairro bairro) throws Exception {
        bairro.setNomBairro(getView().getTxtDescricao().getText());
        String msg = format("Bairro %s com sucesso.", isEmpty(bairro.getId()) ? "cadastrado" : "alterado");
        getBairroService().createOrUpdate(bairro);

        showMessageDialog(getView(), msg, ATENCAO, INFORMATION_MESSAGE);
        limpaTela();
    }

    private List<Bairro> getBairros() {
        if (isNull(bairros)) {
            bairros = new ArrayList<>();
        }
        return bairros;
    }

    private BairroService getBairroService() {
        return ofNullable(bairroService).orElseGet(() -> bairroService = new BairroService());
    }

    private EnderecoService getEnderecoService() {
        return ofNullable(enderecoService).orElseGet(() -> enderecoService = new EnderecoService());
    }
}