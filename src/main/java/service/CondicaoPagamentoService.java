package service;

import model.bo.CondicaoPagamento;
import model.dao.CondicaoPagamentoDao;
import java.util.List;
import static util.ValueUtil.*;

public class CondicaoPagamentoService implements InterfaceService<CondicaoPagamento> {
    @Override
    public void salvar(CondicaoPagamento condicaoPagamento) {
        new CondicaoPagamentoDao().create(condicaoPagamento);
    }

    @Override
    public List<CondicaoPagamento> buscar() {
        return new CondicaoPagamentoDao().retrieveAll();
    }

    @Override
    public CondicaoPagamento buscar(int codigo) {
        return new CondicaoPagamentoDao().retrieve(codigo);
    }

    @Override
    public void atualizar(CondicaoPagamento condicaoPagamento) {
        new CondicaoPagamentoDao().update(condicaoPagamento);
    }

    @Override
    public void apagar(CondicaoPagamento condicaoPagamento) {
        apagar(condicaoPagamento.getId());
    }

    @Override
    public void apagar(int codigo) {
        new CondicaoPagamentoDao().delete(codigo);
    }

    private void validate(CondicaoPagamento condicao) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(condicao.getNomCondicaoPagamento())) {
            msg.append("Descrição: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(condicao.getNumDiasAtePrimeiraParcela())) {
            msg.append("Qtd. Dias até Primeira Parcela: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(condicao.getNumDiasEntreParcelas())) {
            msg.append("Qtd. Dias entre as Parcelas: Campo com preenchimento Obrigatório.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(CondicaoPagamento condicaoPagamento) throws Exception {
        validate(condicaoPagamento);
        if (isEmpty(condicaoPagamento.getId())) {
            salvar(condicaoPagamento);
            return;
        }
        atualizar(condicaoPagamento);
    }
}
