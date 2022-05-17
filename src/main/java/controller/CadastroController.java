package controller;

import view.ControllerView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_ENTER;
import static javax.swing.JComponent.WHEN_FOCUSED;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import static javax.swing.KeyStroke.getKeyStroke;
import static util.ValueUtil.ZERO;

public abstract class CadastroController extends Controller {

    private ControllerView view;

    public abstract void preencheItem();
    public abstract boolean excluiItem();
    public abstract void cadastraNovoItem();
    public abstract void limpaTela();
    public abstract void listaItens();
    public abstract void buscaPorCodigo();

    public CadastroController(ControllerView view) {
        this.view = view;
        adicionaAcoesAosBotoesPadroes();
        adicionaPesquisaPorCodigo();
    }

    private void adicionaAcoesAosBotoesPadroes() {
        adicionaAcoesBotaoSalvarPadrao(view.getBtnSalvar());
        adicionaAcoesBotaoListarPadrao(view.getBtnListar());
        adicionaAcoesBotaoLimparPadrao(view.getBtnLimpar());
        adicionaAcoesBotaoSairPadrao(view.getBtnSair());
    }

    private void adicionaAcoesBotaoSalvarPadrao(JButton btnSalvar) {
        btnSalvar.addActionListener(a -> cadastraNovoItem());
        btnSalvar.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F1, ZERO), EVENTO);
        btnSalvar.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        btnSalvar.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastraNovoItem();
            }
        });
    }

    private void adicionaAcoesBotaoListarPadrao(JButton btnListar) {
        btnListar.addActionListener(a -> listaItens());
        btnListar.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F2, ZERO), EVENTO);
        btnListar.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        btnListar.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaItens();
            }
        });
    }

    private void adicionaAcoesBotaoLimparPadrao(JButton btnLimpar) {
        btnLimpar.addActionListener(a -> limpaTela());
        btnLimpar.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_F3, ZERO), EVENTO);
        btnLimpar.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        btnLimpar.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpaTela();
            }
        });
    }

    private void adicionaAcoesBotaoSairPadrao(JButton btnSair) {
        btnSair.addActionListener(a -> view.dispose());
        btnSair.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(getKeyStroke(VK_ESCAPE, ZERO), EVENTO);
        btnSair.getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_ENTER, ZERO), EVENTO);
        btnSair.getActionMap().put(EVENTO, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void adicionaPesquisaPorCodigo() {
        view.getTxtCodigo().addActionListener(a -> buscaPorCodigo());
    }
}
