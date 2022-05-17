package model.dao;

import model.bo.Endereco;

import javax.persistence.TypedQuery;

public class EnderecoDao extends DaoBase<Endereco> {

    public EnderecoDao() {
        super(Endereco.class);
    }

    public boolean isEnderecoCadastradoComBairro(Integer codigoBairro) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT endereco FROM Endereco endereco WHERE id_bairro = :codigoBairro ");

        try {
            TypedQuery<Endereco> query = getEntityManager().createQuery(sql.toString(), Endereco.class);
            query.setParameter("codigoBairro", codigoBairro);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEnderecoCadastradoComCidade(Integer codigoCidade) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT endereco FROM Endereco endereco WHERE id_cidade = :codigoCidade ");

        try {
            TypedQuery<Endereco> query = getEntityManager().createQuery(sql.toString(), Endereco.class);
            query.setParameter("codigoCidade", codigoCidade);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
