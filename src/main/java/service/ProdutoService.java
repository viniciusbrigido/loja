package service;

import model.bo.Produto;
import model.dao.ProdutoDao;

import java.util.List;
import static util.ValueUtil.*;

public class ProdutoService implements InterfaceService<Produto> {

    @Override
    public void salvar(Produto produto) {
        new ProdutoDao().create(produto);
    }

    @Override
    public List<Produto> buscar() {
        return new ProdutoDao().retrieveAll();
    }

    @Override
    public Produto buscar(int codigo) {
        return new ProdutoDao().retrieve(codigo);
    }

    @Override
    public void atualizar(Produto produto) {
        new ProdutoDao().update(produto);
    }

    @Override
    public void apagar(Produto produto) {
        apagar(produto.getId());
    }

    @Override
    public void apagar(int codigo) {
        new ProdutoDao().delete(codigo);
    }

    private void validate(Produto produto) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isEmpty(produto.getNomProduto())) {
            msg.append("Descrição: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(produto.getVlrProduto())) {
            msg.append("Preço: Campo com preenchimento Obrigatório.\n");
        }
        if (isNull(produto.getMarca())) {
            msg.append("Marca: Campo com preenchimento Obrigatório.\n");
        }
        if (isNull(produto.getTamanho())) {
            msg.append("Tamanho: Campo com preenchimento Obrigatório.\n");
        }
        if (isNull(produto.getTipo())) {
            msg.append("Tipo: Campo com preenchimento Obrigatório.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    @Override
    public void createOrUpdate(Produto produto) throws Exception {
        validate(produto);
        if (isEmpty(produto.getId())) {
            salvar(produto);
            return;
        }
        atualizar(produto);
    }

    public List<Produto> buscaPorNome(String descricao) {
        return new ProdutoDao().buscaPorNome(descricao);
    }

    public boolean isProdutoCadastradoComMarca(Integer codigoMarca) {
        return new ProdutoDao().isProdutoCadastradoComMarca(codigoMarca);
    }

    public boolean isProdutoCadastradoComTipo(Integer codigoTipo) {
        return new ProdutoDao().isProdutoCadastradoComTipo(codigoTipo);
    }

    public boolean isProdutoCadastradoComTamanho(Integer codigoTamanho) {
        return new ProdutoDao().isProdutoCadastradoComTamanho(codigoTamanho);
    }
}
