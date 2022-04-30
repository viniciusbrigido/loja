package model.dao;

import model.bo.Produto;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao extends DaoBase<Produto> {

    public ProdutoDao() {
        super(Produto.class);
    }

    public List<Produto> buscaPorNome(String descricao) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT produto ")
           .append(" FROM produto produto ")
           .append(" WHERE produto.nomProduto LIKE :descricao ");

        try {
            TypedQuery<Produto> query = getEntityManager().createQuery(sql.toString(), Produto.class);
            query.setParameter("descricao", descricao);
            return query.getResultList();
        } catch (Exception e) {
        }
        return new ArrayList<>();
    }
}
