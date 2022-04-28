package controller;

public abstract class CadastroController extends Controller {

    public CadastroController() {
        super();
    }

    public abstract void preencheItem();
    public abstract void excluiItem();
    public abstract void cadastraNovoItem();
    public abstract void limpaTela();
    public abstract void listaItens();
}
