package model.dao;

import model.bo.Produto;
import model.bo.Vendedor;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDao extends DaoBase<Produto> {

    public ProdutoDao() {
        super(Produto.class);
    }

    public List<Produto> buscaPorNome(String descricao) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT produto FROM Produto produto WHERE produto.nomProduto LIKE :descricao");

        try {
            TypedQuery<Produto> query = getEntityManager().createQuery(sql.toString(), Produto.class);
            query.setParameter("descricao", descricao);
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean isProdutoCadastradoComMarca(Integer codigoMarca) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT produto FROM Produto produto WHERE id_marca = :codigoMarca");

        try {
            TypedQuery<Produto> query = getEntityManager().createQuery(sql.toString(), Produto.class);
            query.setParameter("codigoMarca", codigoMarca);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProdutoCadastradoComTipo(Integer codigoTipo) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT produto FROM Produto produto WHERE id_tipo = :codigoTipo");

        try {
            TypedQuery<Produto> query = getEntityManager().createQuery(sql.toString(), Produto.class);
            query.setParameter("codigoTipo", codigoTipo);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProdutoCadastradoComTamanho(Integer codigoTamanho) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT produto FROM Produto produto WHERE id_tamanho = :codigoTamanho");

        try {
            TypedQuery<Produto> query = getEntityManager().createQuery(sql.toString(), Produto.class);
            query.setParameter("codigoTamanho", codigoTamanho);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
