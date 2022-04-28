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
        return null;
    }

    @Override
    public FoneFornecedor buscar(int codigo) {
        return null;
    }

    @Override
    public void atualizar(FoneFornecedor foneFornecedor) {
    }

    @Override
    public void apagar(FoneFornecedor foneFornecedor) {
        new FoneFornecedorDao().delete(foneFornecedor);
    }

    @Override
    public void apagar(int codigo) {
    }

    @Override
    public void createOrUpdate(FoneFornecedor foneFornecedor) {
    }

    public List<FoneFornecedor> buscaFonesPorFornecedor(int codigoFornecedor) {
        return null;
//        return new FoneFornecedorDao().buscaFonesPorFornecedor(codigoFornecedor);
    }
}
