package service;

import model.bo.Endereco;
import model.dao.EnderecoDao;
import java.util.List;
import static util.ValueUtil.*;

public class EnderecoService implements InterfaceService<Endereco> {

    @Override
    public void salvar(Endereco endereco) {
        new EnderecoDao().create(endereco);
    }

    @Override
    public List<Endereco> buscar() {
        return new EnderecoDao().retrieveAll();
    }

    @Override
    public Endereco buscar(int codigo) {
        return new EnderecoDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Endereco endereco) {
        new EnderecoDao().update(endereco);
    }

    @Override
    public void apagar(Endereco endereco) {
        apagar(endereco.getId());
    }

    @Override
    public void apagar(int codigo) {
        new EnderecoDao().delete(codigo);
    }

    private void validate(Endereco endereco) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(endereco.getNomLogradouro())) {
            msg.append("Logradouro: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(endereco.getNomCep())) {
            msg.append("CEP: Campo com preenchimento Obrigatório.\n");
        }
        if (isNull(endereco.getBairro())) {
            msg.append("Bairro: Campo com preenchimento Obrigatório.\n");
        }
        if (isNull(endereco.getCidade())) {
            msg.append("Cidade: Campo com preenchimento Obrigatório.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Endereco endereco) throws Exception {
        validate(endereco);
        if (isEmpty(endereco.getId())) {
            salvar(endereco);
            return;
        }
        atualizar(endereco);
    }

    public boolean isEnderecoCadastradoComBairro(int codigoBairro) {
        return false;
//        return new EnderecoDao().isEnderecoCadastradoComBairro(codigoBairro);
    }

    public boolean isEnderecoCadastradoComCidade(int codigoCidade) {
        return false;
//        return new EnderecoDao().isEnderecoCadastradoComCidade(codigoCidade);
    }
}
