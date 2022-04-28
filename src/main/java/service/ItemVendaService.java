package service;

import model.bo.ItemVenda;
import model.dao.ItemVendaDao;

import java.util.List;

public class ItemVendaService implements InterfaceService<ItemVenda> {

    @Override
    public void salvar(ItemVenda itemVenda) {
        new ItemVendaDao().create(itemVenda);
    }

    @Override
    public List<ItemVenda> buscar() {
        return null;
    }

    @Override
    public ItemVenda buscar(int codigo) {
        return null;
    }

    @Override
    public void atualizar(ItemVenda itensVenda) {
    }

    @Override
    public void apagar(ItemVenda itensVenda) {
    }

    @Override
    public void apagar(int codigo) {
    }

    @Override
    public void createOrUpdate(ItemVenda itemVenda) throws Exception {
    }
}
