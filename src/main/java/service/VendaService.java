package service;

import model.bo.Venda;
import model.dao.VendaDao;
import java.util.List;
import static util.ValueUtil.*;

public class VendaService implements InterfaceService<Venda> {

    @Override
    public void salvar(Venda venda) {
        new VendaDao().create(venda);
    }

    public Venda salvarRetornandoVenda(Venda venda) {
        return new VendaDao().createReturnId(venda);
    }

    @Override
    public List<Venda> buscar() {
        return new VendaDao().retrieveAll();
    }

    @Override
    public Venda buscar(int codigo) {
        return new VendaDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Venda venda) {
        new VendaDao().update(venda);
    }

    @Override
    public void apagar(Venda venda) {
        apagar(venda.getId());
    }

    @Override
    public void apagar(int codigo) {
        new VendaDao().delete(codigo);
    }

    @Override
    public void createOrUpdate(Venda venda) throws Exception {
        if (isEmpty(venda.getId())) {
            salvar(venda);
            return;
        }
        atualizar(venda);
    }
}