package model.dao;

import model.bo.Fornecedor;
import javax.persistence.TypedQuery;

public class FornecedorDao extends DaoBase<Fornecedor> {

    public FornecedorDao() {
        super(Fornecedor.class);
    }

    public boolean isFornecedorCadastradoComEndereco(Integer codigoEndereco) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT fornecedor FROM Fornecedor fornecedor WHERE id_endereco = :codigoEndereco ");

        try {
            TypedQuery<Fornecedor> query = getEntityManager().createQuery(sql.toString(), Fornecedor.class);
            query.setParameter("codigoEndereco", codigoEndereco);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
