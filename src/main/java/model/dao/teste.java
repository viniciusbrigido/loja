package model.dao;

import model.bo.*;

public class teste {

    public static void main(String[] args) {
        Endereco endereco = new Endereco();
        endereco.setNomCep("564456546");
        endereco.setNomLogradouro("rua tal");
        endereco.setBairro(new BairroDao().retrieve(1));
        endereco.setCidade(new CidadeDao().retrieve(1));
    }
}