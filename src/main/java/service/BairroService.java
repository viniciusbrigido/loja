package service;

import model.bo.Bairro;
import model.dao.BairroDao;
import java.util.List;
import static util.ValueUtil.*;

public class BairroService implements InterfaceService<Bairro> {

    @Override
    public void salvar(Bairro bairro) {
        new BairroDao().create(bairro);
    }

    @Override
    public List<Bairro> buscar() {
        return new BairroDao().retrieveAll();
    }

    @Override
    public Bairro buscar(int codigo) {
        return new BairroDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Bairro bairro) {
        new BairroDao().update(bairro);
    }

    @Override
    public void apagar(Bairro bairro) {
        apagar(bairro.getId());
    }

    @Override
    public void apagar(int codigo) {
        new BairroDao().delete(codigo);
    }

    private void validate(Bairro bairro) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(bairro.getNomBairro())) {
            msg.append("Descrição: Campo com preenchimento Obrigatório.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Bairro bairro) throws Exception {
        validate(bairro);
        if (isEmpty(bairro.getId())) {
            salvar(bairro);
            return;
        }
        atualizar(bairro);
    }
}
