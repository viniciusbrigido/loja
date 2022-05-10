package model.dao;

import model.bo.Cliente;

import javax.persistence.TypedQuery;

public class ClienteDao extends DaoBase<Cliente> {

    public ClienteDao() {
        super(Cliente.class);
    }

    public boolean isClienteCadastradoComEndereco(Integer codigoEndereco) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT cliente FROM Cliente cliente WHERE id_endereco = :codigoEndereco");

        try {
            TypedQuery<Cliente> query = getEntityManager().createQuery(sql.toString(), Cliente.class);
            query.setParameter("codigoEndereco", codigoEndereco);
            return !query.getResultList().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
