package service;

import model.bo.Cidade;
import model.dao.CidadeDao;
import java.util.List;
import static util.ValueUtil.*;

public class CidadeService implements InterfaceService<Cidade> {

    @Override
    public void salvar(Cidade cidade) {
        new CidadeDao().create(cidade);
    }

    @Override
    public List<Cidade> buscar() {
        return new CidadeDao().retrieveAll();
    }

    @Override
    public Cidade buscar(int codigo) {
        return new CidadeDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Cidade cidade) {
         new CidadeDao().update(cidade);
    }

    @Override
    public void apagar(Cidade cidade) {
        apagar(cidade.getId());
    }

    @Override
    public void apagar(int codigo) {
        new CidadeDao().delete(codigo);
    }

    private void validate(Cidade cidade) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(cidade.getNomCidade())) {
            msg.append("Descrição: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(cidade.getNomUf())) {
            msg.append("UF: Campo com preenchimento Obrigatório.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Cidade cidade) throws Exception {
        validate(cidade);
        if (isEmpty(cidade.getId())) {
            salvar(cidade);
            return;
        }
        atualizar(cidade);
    }
}