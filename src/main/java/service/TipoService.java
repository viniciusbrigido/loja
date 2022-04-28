package service;

import model.bo.Tipo;
import model.dao.TipoDao;
import java.util.List;
import static util.ValueUtil.*;

public class TipoService implements InterfaceService<Tipo> {

    @Override
    public void salvar(Tipo tipoProduto) {
        new TipoDao().create(tipoProduto);
    }

    @Override
    public List<Tipo> buscar() {
        return new TipoDao().retrieveAll();
    }

    @Override
    public Tipo buscar(int codigo) {
        return new TipoDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Tipo tipo) {
        new TipoDao().update(tipo);
    }

    @Override
    public void apagar(Tipo tipoProduto) {
        apagar(tipoProduto.getId());
    }

    @Override
    public void apagar(int codigo) {
        new TipoDao().delete(codigo);
    }

    private void validate(Tipo tipo) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(tipo.getNomTipo())) {
            msg.append("Descrição: Campo com preenchimento Obrigatório.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Tipo tipo) throws Exception {
        validate(tipo);
        if (isEmpty(tipo.getId())) {
            salvar(tipo);
            return;
        }
        atualizar(tipo);
    }
}
