package service;

import model.bo.CaracteristicaProduto;
import model.dao.CaracteristicaProdutoDao;
import java.util.List;
import static util.ValueUtil.*;

public class CaracteristicaProdutoService implements InterfaceService<CaracteristicaProduto>{
    @Override
    public void salvar(CaracteristicaProduto caracteristicaProduto) {
        new CaracteristicaProdutoDao().create(caracteristicaProduto);
    }

    @Override
    public List<CaracteristicaProduto> buscar() {
        return new CaracteristicaProdutoDao().retrieveAll();
    }

    @Override
    public CaracteristicaProduto buscar(int codigo) {
        return new CaracteristicaProdutoDao().retrieve(codigo);
    }

    @Override
    public void atualizar(CaracteristicaProduto caracteristicaProduto) {
        new CaracteristicaProdutoDao().update(caracteristicaProduto);
    }

    @Override
    public void apagar(CaracteristicaProduto caracteristicaProduto) {
        apagar(caracteristicaProduto.getId());
    }

    @Override
    public void apagar(int codigo) {
        new CaracteristicaProdutoDao().delete(codigo);
    }

    private void validate(CaracteristicaProduto caracteristicaProduto) throws Exception {
        StringBuilder msg = new StringBuilder();
        if (isNull(caracteristicaProduto.getProduto())) {
            msg.append("Produto: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(caracteristicaProduto.getCodBarras())) {
            msg.append("Código de Barras: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(caracteristicaProduto.getQtdEstoque())) {
            msg.append("Estoque: Campo com preenchimento Obrigatório.\n");
        }
        if (isEmpty(caracteristicaProduto.getNumTamanho())) {
            msg.append("Tamanho: Campo com preenchimento Obrigatório.\n");
        }
        if (isNull(caracteristicaProduto.getCor())) {
            msg.append("Cor: Campo com preenchimento Obrigatório.");
        }
        if (isEmpty(msg.toString()) && isCaracteristicaJaCadastradaComCodigoBarras(caracteristicaProduto)) {
            msg.append("Código de Barras: Já existe uma Característica de Produto cadastrada com esse Código de Barras.");
        }

        if (isNotEmpty(msg.toString())) {
            throw new Exception(msg.toString());
        }
    }

    private boolean isCaracteristicaJaCadastradaComCodigoBarras(CaracteristicaProduto caracteristicaProduto) {
        CaracteristicaProduto caracteristica = buscaPorCodigoBarras(caracteristicaProduto.getCodBarras());
        return isNotNull(caracteristica) && caracteristica.getId() != caracteristicaProduto.getId();
    }

    @Override
    public void createOrUpdate(CaracteristicaProduto caracteristicaProduto) throws Exception {
        validate(caracteristicaProduto);
        if (isEmpty(caracteristicaProduto.getId())) {
            salvar(caracteristicaProduto);
            return;
        }
        atualizar(caracteristicaProduto);
    }

    public boolean isCaracteristicaCadastradaComCor(Integer codigoCor) {
        return new CaracteristicaProdutoDao().isCaracteristicaCadastradaComCor(codigoCor);
    }

    public List<CaracteristicaProduto> buscaCaracteristicasPorProduto(Integer codigoProduto) {
        return new CaracteristicaProdutoDao().buscaCaracteristicasPorProduto(codigoProduto);
    }

    public CaracteristicaProduto buscaPorCodigoBarras(String codigoBarra) {
        return new CaracteristicaProdutoDao().buscaPorCodigoBarras(codigoBarra);
    }
}
