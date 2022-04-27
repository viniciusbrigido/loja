package model.dao;

import model.bo.Cidade;

public class ClienteDao extends DaoBase<Cidade> {

    public ClienteDao() {
        super(Cidade.class);
    }
}
