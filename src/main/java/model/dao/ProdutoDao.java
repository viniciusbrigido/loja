package model.dao;

import model.bo.Produto;

public class ProdutoDao extends DaoBase<Produto> {

    public ProdutoDao() {
        super(Produto.class);
    }
}
