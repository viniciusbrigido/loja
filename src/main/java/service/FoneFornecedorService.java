package service;

import model.bo.FoneFornecedor;
import model.dao.FoneFornecedorDao;

import java.util.List;

public class FoneFornecedorService implements InterfaceService<FoneFornecedor> {

    @Override
    public void salvar(FoneFornecedor foneFornecedor) {
        new FoneFornecedorDao().create(foneFornecedor);
    }

    @Override
    public List<FoneFornecedor> buscar() {
        return new FoneFornecedorDao().retrieveAll();
    }

    @Override
    public FoneFornecedor buscar(int codigo) {
        return new FoneFornecedorDao().retrieve(codigo);
    }

    @Override
    public void atualizar(FoneFornecedor foneFornecedor) {
        new FoneFornecedorDao().update(foneFornecedor);
    }

    @Override
    public void apagar(FoneFornecedor foneFornecedor) {
        new FoneFornecedorDao().delete(foneFornecedor);
    }

    @Override
    public void apagar(int codigo) {
        new FoneFornecedorDao().delete(codigo);
    }

    @Override
    public void createOrUpdate(FoneFornecedor foneFornecedor) {
    }

    public List<FoneFornecedor> buscaFonesPorFornecedor(Integer codigoFornecedor) {
        return new FoneFornecedorDao().buscaFonesPorFornecedor(codigoFornecedor);
    }
}
