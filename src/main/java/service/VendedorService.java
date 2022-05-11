package service;

import model.bo.Vendedor;
import model.dao.VendedorDao;

import java.util.List;
import static util.Validate.isEmailValido;
import static util.ValueUtil.*;

public class VendedorService implements InterfaceService<Vendedor> {

    @Override
    public void salvar(Vendedor vendedor) {
        new VendedorDao().create(vendedor);
    }

    @Override
    public List<Vendedor> buscar() {
        return new VendedorDao().retrieveAll();
    }

    @Override
    public Vendedor buscar(int codigo) {
        return new VendedorDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Vendedor vendedor) {
        new VendedorDao().update(vendedor);
    }

    @Override
    public void apagar(Vendedor vendedor) {
        apagar(vendedor.getId());
    }

    @Override
    public void apagar(int codigo) {
        new VendedorDao().delete(codigo);
    }

    private void validate(Vendedor vendedor) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(vendedor.getNome())) {
            msg.append("Nome: Campo com preenchimento Obrigat�rio.\n");
        }
        if (isEmpty(vendedor.getNumCpf())) {
            msg.append("CPF: Campo com preenchimento Obrigat�rio.\n");
        }
        if (isEmpty(vendedor.getNumFone())) {
            msg.append("Fone: Campo com preenchimento Obrigat�rio.\n");
        }
        if (isEmpty(vendedor.getPrcComissaoVenda())) {
            msg.append("Comiss�o Venda: Campo com preenchimento Obrigat�rio.\n");
        } else if (vendedor.getPrcComissaoVenda() >= 100) {
            msg.append("Comiss�o Venda: O valor deve ser menor que 100.\n");
        }
        if (isEmpty(vendedor.getPrcComissaoRecebto())) {
            msg.append("Comiss�o Recebimento: Campo com preenchimento Obrigat�rio.\n");
        } else if (vendedor.getPrcComissaoRecebto() >= 100) {
            msg.append("Comiss�o Recebimento: O valor deve ser menor que 100.\n");
        }
        if (isNull(vendedor.getEndereco())) {
            msg.append("Endere�o: Campo com preenchimento Obrigat�rio.\n");
        }
        if (isEmpty(vendedor.getNomComplemento())) {
            msg.append("Complemento: Campo com preenchimento Obrigat�rio.\n");
        }
        if (isNotEmpty(vendedor.getNomEmail()) && !isEmailValido(vendedor.getNomEmail())) {
            msg.append("E-mail: Valor inv�lido.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Vendedor vendedor) throws Exception {
        validate(vendedor);
        if (isEmpty(vendedor.getId())) {
            salvar(vendedor);
            return;
        }
        atualizar(vendedor);
    }

    public boolean isVendedorCadastradoComEndereco(Integer codigoEndereco) {
        return new VendedorDao().isVendedorCadastradoComEndereco(codigoEndereco);
    }
}
