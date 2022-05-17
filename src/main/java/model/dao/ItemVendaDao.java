package model.dao;

import model.bo.ItemVenda;
import javax.persistence.TypedQuery;

public class ItemVendaDao extends DaoBase<ItemVenda> {

    public ItemVendaDao() {
        super(ItemVenda.class);
    }

    public boolean isProdutoJaVendido(Integer codigoProduto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT item FROM ItemVenda item ")
           .append(" INNER JOIN CaracteristicaProduto caracteristica ON (caracteristica.id = item.caracteristicaProduto.id) ")
           .append(" WHERE caracteristica.produto.id = :codigoProduto ");

        try {
            TypedQuery<ItemVenda> query = getEntityManager().createQuery(sql.toString(), ItemVenda.class);
            query.setParameter("codigoProduto", codigoProduto);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
