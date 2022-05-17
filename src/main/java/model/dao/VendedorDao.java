package model.dao;

import model.bo.Vendedor;
import javax.persistence.TypedQuery;

public class VendedorDao extends DaoBase<Vendedor> {

    public VendedorDao() {
        super(Vendedor.class);
    }

    public boolean isVendedorCadastradoComEndereco(Integer codigoEndereco) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT vendedor FROM Vendedor vendedor WHERE id_endereco = :codigoEndereco ");

        try {
            TypedQuery<Vendedor> query = getEntityManager().createQuery(sql.toString(), Vendedor.class);
            query.setParameter("codigoEndereco", codigoEndereco);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
