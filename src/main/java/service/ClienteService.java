package service;

import model.bo.Cliente;
import model.dao.ClienteDao;
import java.util.List;
import static util.Validate.isEmailValido;
import static util.ValueUtil.*;

public class ClienteService implements InterfaceService<Cliente> {

    @Override
    public void salvar(Cliente cliente) {
        new ClienteDao().create(cliente);
    }

    @Override
    public List<Cliente> buscar() {
        return new ClienteDao().retrieveAll();
    }

    @Override
    public Cliente buscar(int codigo) {
        return new ClienteDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Cliente cliente) {
        new ClienteDao().update(cliente);
    }

    @Override
    public void apagar(Cliente cliente) {
        apagar(cliente.getId());
    }

    @Override
    public void apagar(int codigo) {
        new ClienteDao().delete(codigo);
    }

    private void validate(Cliente cliente) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(cliente.getNomCliente())) {
            msg.append("Nome: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(cliente.getNumCpf())) {
            msg.append("CPF: Campo com preenchimento Obrigatório.\n");
        }
        if (isNull(cliente.getDatNascimento())) {
            msg.append("Data Nascimento: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(cliente.getNumFone())) {
            msg.append("Fone: Campo com preenchimento Obrigatório.\n");
        }
        if (isNull(cliente.getEndereco())) {
            msg.append("Endereço: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(cliente.getNomComplemento())) {
            msg.append("Complemento: Campo com preenchimento Obrigatório.\n");
        }
        if (isNotEmpty(cliente.getNomEmail()) && !isEmailValido(cliente.getNomEmail())) {
            msg.append("E-mail: Valor inválido.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Cliente cliente) throws Exception {
        validate(cliente);
        if (isEmpty(cliente.getId())) {
            salvar(cliente);
            return;
        }
        atualizar(cliente);
    }

    public boolean isClienteCadastradoComEndereco(Integer codigoEndereco) {
        return new ClienteDao().isClienteCadastradoComEndereco(codigoEndereco);
    }
}
