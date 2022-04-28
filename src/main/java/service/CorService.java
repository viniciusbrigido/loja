package service;

import model.bo.Cor;
import model.dao.CorDao;
import java.util.List;
import static util.ValueUtil.*;

public class CorService implements InterfaceService<Cor> {

    @Override
    public void salvar(Cor cor) {
        new CorDao().create(cor);
    }

    @Override
    public List<Cor> buscar() {
        return new CorDao().retrieveAll();
    }

    @Override
    public Cor buscar(int codigo) {
        return new CorDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Cor cor) {
        new CorDao().update(cor);
    }

    @Override
    public void apagar(Cor cor) {
        apagar(cor.getId());
    }

    @Override
    public void apagar(int codigo) {
        new CorDao().delete(codigo);
    }

    private void validate(Cor cor) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(cor.getNomCor())) {
            msg.append("Descrição: Campo com preenchimento Obrigatório.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Cor cor) throws Exception {
        validate(cor);
        if (isEmpty(cor.getId())) {
            salvar(cor);
            return;
        }
        atualizar(cor);
    }
}
