package model.dao;

import model.bo.CaracteristicaProduto;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CaracteristicaProdutoDao extends DaoBase<CaracteristicaProduto> {

    public CaracteristicaProdutoDao() {
        super(CaracteristicaProduto.class);
    }

    public List<CaracteristicaProduto> buscaCaracteristicasPorProduto(Integer codigoProduto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT caracteristica ")
           .append(" FROM caracteristica_produto caracteristica ")
           .append(" WHERE caracteristica.produto.id = :codigoProduto ");

        try {
            TypedQuery<CaracteristicaProduto> query = getEntityManager().createQuery(sql.toString(), CaracteristicaProduto.class);
            query.setParameter("codigoProduto", codigoProduto);
            return query.getResultList();
        } catch (Exception e) {
        }
        return new ArrayList<>();
    }

    public CaracteristicaProduto buscaPorCodigoBarras(String codigoBarra) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT caracteristica ")
           .append(" FROM caracteristica_produto caracteristica ")
           .append(" WHERE caracteristica.codBarras = :codigoBarra ");

        try {
            TypedQuery<CaracteristicaProduto> query = getEntityManager().createQuery(sql.toString(), CaracteristicaProduto.class);
            query.setParameter("codigoBarra", codigoBarra);
            return query.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }
}
