package controller;

public abstract class ListagemController extends Controller {

    private static CadastroController parent;

    public abstract void selecionaItem();
    public abstract void excluiItem();
    public abstract void sairTela();

    public ListagemController() {
        super();
    }

    public ListagemController(CadastroController parent) {
        super();
        setParent(parent);
    }

    public static CadastroController getParent() {
        return parent;
    }

    public static void setParent(CadastroController parent) {
        ListagemController.parent = parent;
    }
}
