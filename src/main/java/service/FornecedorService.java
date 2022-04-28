package service;

import model.bo.Fornecedor;
import model.dao.FornecedorDao;
import java.util.List;
import static util.Validate.isEmailValido;
import static util.ValueUtil.*;

public class FornecedorService implements InterfaceService<Fornecedor> {

    @Override
    public void salvar(Fornecedor fornecedor) {
        new FornecedorDao().create(fornecedor);
    }

    @Override
    public List<Fornecedor> buscar() {
        return new FornecedorDao().retrieveAll();
    }

    @Override
    public Fornecedor buscar(int codigo) {
        return new FornecedorDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Fornecedor fornecedor) {
        new FornecedorDao().update(fornecedor);
    }

    @Override
    public void apagar(Fornecedor fornecedor) {
        apagar(fornecedor.getId());
    }

    @Override
    public void apagar(int codigo) {
        new FornecedorDao().delete(codigo);
    }

    private void validate(Fornecedor fornecedor) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(fornecedor.getNomFornecedor())) {
            msg.append("Nome: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(fornecedor.getNomRazaoSocial())) {
            msg.append("Razão Social: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(fornecedor.getNumCnpj())) {
            msg.append("CNPJ: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(fornecedor.getNumInscEstadual())) {
            msg.append("Inscrição Estadual: Campo com preenchimento Obrigatório.\n");
        }
        if (isNull(fornecedor.getEndereco())) {
            msg.append("Endereço: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(fornecedor.getNomComplemento())) {
            msg.append("Complemento: Campo com preenchimento Obrigatório.\n");
        }
        if (isNotEmpty(fornecedor.getNomEmail()) && !isEmailValido(fornecedor.getNomEmail())) {
            msg.append("E-mail: Valor inválido.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Fornecedor fornecedor) throws Exception {
        validate(fornecedor);
        if (isEmpty(fornecedor.getId())) {
            salvar(fornecedor);
            return;
        }
        atualizar(fornecedor);
    }

    public boolean isFornecedorCadastradoComEndereco(int codigoEndereco) {
        return false;
//        return new FornecedorDao().isFornecedorCadastradoComEndereco(codigoEndereco);
    }
}
