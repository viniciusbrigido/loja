package model.dao;

import model.bo.CaracteristicaProduto;
import model.bo.Produto;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CaracteristicaProdutoDao extends DaoBase<CaracteristicaProduto> {

    public CaracteristicaProdutoDao() {
        super(CaracteristicaProduto.class);
    }

    public List<CaracteristicaProduto> buscaCaracteristicasPorProduto(Integer codigoProduto) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT caracteristica FROM CaracteristicaProduto caracteristica WHERE id_produto = :codigoProduto");

        try {
            TypedQuery<CaracteristicaProduto> query = getEntityManager().createQuery(sql.toString(), CaracteristicaProduto.class);
            query.setParameter("codigoProduto", codigoProduto);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public CaracteristicaProduto buscaPorCodigoBarras(String codigoBarra) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT caracteristica FROM CaracteristicaProduto caracteristica WHERE codBarras = :codigoBarra");

        try {
            TypedQuery<CaracteristicaProduto> query = getEntityManager().createQuery(sql.toString(), CaracteristicaProduto.class);
            query.setParameter("codigoBarra", codigoBarra);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isCaracteristicaCadastradaComCor(Integer codigoCor) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT caracteristica FROM CaracteristicaProduto caracteristica WHERE id_cor = :codigoCor");

        try {
            TypedQuery<CaracteristicaProduto> query = getEntityManager().createQuery(sql.toString(), CaracteristicaProduto.class);
            query.setParameter("codigoCor", codigoCor);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
