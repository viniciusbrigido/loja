package service;

import model.bo.Receber;
import model.dao.ReceberDao;
import java.util.List;
import static util.ValueUtil.*;

public class ReceberService implements InterfaceService<Receber> {

    @Override
    public void salvar(Receber receber) {
        new ReceberDao().create(receber);
    }

    @Override
    public List<Receber> buscar() {
        return new ReceberDao().retrieveAll();
    }

    @Override
    public Receber buscar(int codigo) {
        return new ReceberDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Receber receber) {
        new ReceberDao().update(receber);
    }

    @Override
    public void apagar(Receber receber) {
        apagar(receber.getId());
    }

    @Override
    public void apagar(int codigo) {
        new ReceberDao().delete(codigo);
    }

    @Override
    public void createOrUpdate(Receber receber) throws Exception {
        if (isEmpty(receber.getId())) {
            salvar(receber);
            return;
        }
        atualizar(receber);
    }
}
