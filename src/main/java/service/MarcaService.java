package service;

import model.bo.Marca;
import model.dao.MarcaDao;
import java.util.List;
import static util.ValueUtil.*;

public class MarcaService implements InterfaceService<Marca> {

    @Override
    public void salvar(Marca marca) {
        new MarcaDao().create(marca);
    }

    @Override
    public List<Marca> buscar() {
        return new MarcaDao().retrieveAll();
    }

    @Override
    public Marca buscar(int codigo) {
        return new MarcaDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Marca marca) {
        new MarcaDao().update(marca);
    }

    @Override
    public void apagar(Marca marca) {
        apagar(marca.getId());
    }

    @Override
    public void apagar(int codigo) {
        new MarcaDao().delete(codigo);
    }

    private void validate(Marca marca) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(marca.getNomMarca())) {
            msg.append("Descrição: Campo com preenchimento Obrigatório.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Marca marca) throws Exception {
        validate(marca);
        if (isEmpty(marca.getId())) {
            salvar(marca);
            return;
        }
        atualizar(marca);
    }
}
