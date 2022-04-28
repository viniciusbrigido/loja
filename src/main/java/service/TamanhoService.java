package service;

import model.bo.Tamanho;
import model.dao.TamanhoDao;
import java.util.List;
import static util.ValueUtil.*;

public class TamanhoService implements InterfaceService<Tamanho> {

    @Override
    public void salvar(Tamanho tamanho) {
        new TamanhoDao().create(tamanho);
    }

    @Override
    public List<Tamanho> buscar() {
        return new TamanhoDao().retrieveAll();
    }

    @Override
    public Tamanho buscar(int codigo) {
        return new TamanhoDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Tamanho tamanho) {
        new TamanhoDao().update(tamanho);
    }

    @Override
    public void apagar(Tamanho tamanho) {
        apagar(tamanho.getId());
    }

    @Override
    public void apagar(int codigo) {
        new TamanhoDao().delete(codigo);
    }

    private void validate(Tamanho tamanho) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(tamanho.getNomTamanho())) {
            msg.append("Descrição: Campo com preenchimento Obrigatório.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Tamanho tamanho) throws Exception {
        validate(tamanho);
        if (isEmpty(tamanho.getId())) {
            salvar(tamanho);
            return;
        }
        atualizar(tamanho);
    }
}
