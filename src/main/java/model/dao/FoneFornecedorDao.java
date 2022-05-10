package model.dao;

import model.bo.FoneFornecedor;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class FoneFornecedorDao extends DaoBase<FoneFornecedor> {

    public FoneFornecedorDao() {
        super(FoneFornecedor.class);
    }

    public List<FoneFornecedor> buscaFonesPorFornecedor(Integer codigoFornecedor) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT fone FROM FoneFornecedor fone WHERE id_fornecedor = :codigoFornecedor");

        try {
            TypedQuery<FoneFornecedor> query = getEntityManager().createQuery(sql.toString(), FoneFornecedor.class);
            query.setParameter("codigoFornecedor", codigoFornecedor);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
