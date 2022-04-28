package controller;

import dto.ColunaDto;
import model.bo.Cidade;
import service.*;
import view.CadastroCidadeView;
import java.util.*;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.ValueUtil.*;

public class CadastroCidadeController extends CadastroController {

    private CadastroCidadeView view;
    private List<Cidade> cidades;
    private CidadeService cidadeService;
    private EnderecoService enderecoService;

    public CadastroCidadeController() {
        super();
        setView(new CadastroCidadeView(this));
        getView().setVisible(true);
    }

    @Override
    public void excluiItem() {
        Cidade cidade = getCidades().get(index);
        if (getEnderecoService().isEnderecoCadastradoComCidade(cidade.getId())) {
            showMessageDialog(getView(), "O item não pode ser excluído pois já há um ou mais Endereços cadastrados com essa Cidade.", "Atenção", ERROR_MESSAGE);
            return;
        }
        getCidadeService().apagar(cidade);
    }

    @Override
    public void preencheItem() {
        preencheCamposTela(getCidades().get(index));
    }

    public void buscaCidade() {
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

        Cidade cidade = getCidadeService().buscar(codigo);
        if (isNotNull(cidade) && isNotEmpty(cidade.getId())) {
            preencheCamposTela(cidade);
        } else {
            showMessageDialog(getView(), "Cidade não encontrada.", "Atenção", ERROR_MESSAGE);
            limpaTela();
        }
    }

    @Override
    public void cadastraNovoItem() {
        try {
            if (isEmpty(getView().getTxtCodigo().getText())) {
                preencheSalvaCidade(new Cidade());
            } else {
                verificaCidadeJaCadastrado();
            }
        } catch (Exception e) {
            showMessageDialog(getView(), e.getMessage(), "Atenção", ERROR_MESSAGE);
            getView().getTxtDescricao().requestFocus();
        }
    }

    private void verificaCidadeJaCadastrado() throws Exception {
        Integer codigo;
        try {
            codigo = Integer.parseInt(getView().getTxtCodigo().getText());
        } catch (NumberFormatException e) {
            showMessageDialog(getView(), "Código: Valor Inválido.", "Atenção", ERROR_MESSAGE);
            limpaTela();
            return;
        }
        Cidade cidade = getCidadeService().buscar(codigo);
        if (isNotNull(cidade) && isNotEmpty(cidade.getId())) {
            preencheSalvaCidade(cidade);
            return;
        }
        showMessageDialog(getView(), "Código não encontrado.\nPara cadastrar uma nova Cidade não preencha o campo de código.", "Atenção", ERROR_MESSAGE);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void limpaTela() {
        getCidades().clear();
        getView().getTxtCodigo().setText(VAZIO);
        getView().getTxtDescricao().setText(VAZIO);
        getView().getCBEstados().setSelectedIndex(ZERO);
        getView().getTxtCodigo().requestFocus();
    }

    @Override
    public void listaItens() {
        getCidades().clear();
        getCidades().addAll(getCidadeService().buscar());
        if (getCidades().isEmpty()) {
            showMessageDialog(getView(), "Não há Cidades para listar.", "Atenção", INFORMATION_MESSAGE);
            return;
        }
        new ListagemGeralController(this, "Listagem de Cidades", getItens(), getColunas());
    }

    private List<String> getItens() {
        List<String> itens = new ArrayList<>();
        for (Cidade cidade : getCidades()) {
            StringBuilder sb = new StringBuilder();
            sb.append(cidade.getId()).append(SEPARADOR)
              .append(cidade.getNomCidade()).append(SEPARADOR)
              .append(cidade.getNomCidade());
            itens.add(sb.toString());
        }
        return itens;
    }

    private List<ColunaDto> getColunas() {
        List<ColunaDto> colunas = new ArrayList<>();
        colunas.add(new ColunaDto("Código", DIREITA, 50));
        colunas.add(new ColunaDto("Descrição", ESQUERDA, 200));
        colunas.add(new ColunaDto("UF", ESQUERDA, 50));
        return colunas;
    }

    private void preencheCamposTela(Cidade cidade) {
        getView().getTxtCodigo().setText(String.valueOf(cidade.getId()));
        getView().getTxtDescricao().setText(cidade.getNomCidade());
        getView().getCBEstados().setSelectedItem(cidade.getNomUf());
        getView().getTxtDescricao().requestFocus();
    }

    private void preencheSalvaCidade(Cidade cidade) throws Exception {
        cidade.setNomCidade(getView().getTxtDescricao().getText());
        cidade.setNomUf(getView().getCBEstados().getSelectedItem().toString());
        getCidadeService().createOrUpdate(cidade);

        showMessageDialog(getView(), format("Cidade %s com sucesso.", isEmpty(cidade.getId()) ? "cadastrada" : "alterada"), "Atenção", INFORMATION_MESSAGE);
        limpaTela();
    }

    public void setView(CadastroCidadeView view) {
        this.view = view;
    }

    public CadastroCidadeView getView() {
        return view;
    }

    public List<Cidade> getCidades() {
        if (isNull(cidades)) {
            cidades = new ArrayList<>();
        }
        return cidades;
    }

    private CidadeService getCidadeService() {
        return ofNullable(cidadeService).orElseGet(() -> cidadeService = new CidadeService());
    }

    private EnderecoService getEnderecoService() {
        return ofNullable(enderecoService).orElseGet(() -> enderecoService = new EnderecoService());
    }
}
