package controller;

import model.bo.FoneFornecedor;
import model.bo.Fornecedor;
import service.FoneFornecedorService;
import view.CadastroFoneFornecedorView;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import static java.awt.Cursor.*;
import static java.util.Optional.ofNullable;
import static javax.swing.JOptionPane.*;
import static util.Formatador.*;
import static util.ValueUtil.*;

public class CadastroFoneFornecedorController extends CadastroController {

    private CadastroFoneFornecedorView view;
    private Fornecedor fornecedor;
    private List<FoneFornecedor> fones;
    private FoneFornecedorService foneFornecedorService;
    private AbstractTableModel grid;

    public CadastroFoneFornecedorController(CadastroFoneFornecedorView view, Fornecedor fornecedor) {
        super(view);
        this.fornecedor = fornecedor;
        setView(view);
        getView().setVisible(true);
        setFones(getFoneFornecedorService().buscaFonesPorFornecedor(fornecedor.getId()));
        adicionaAcoesGrid();
        preencheFornecedor();
        getView().getTxtFone().requestFocus();
    }

    private void adicionaAcoesGrid() {
        getView().getTabelaFones().setModel(getGrid());
        getView().getTabelaFones().addMouseListener(getMouseListenerTableGrid());
        adicionaRegrasAlinhamentoGrid();
    }

    private void adicionaRegrasAlinhamentoGrid() {
        DefaultTableCellRenderer meio = new DefaultTableCellRenderer();
        meio.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel columnModel = getView().getTabelaFones().getColumnModel();
        columnModel.getColumn(0).setCellRenderer(meio);

        DefaultTableCellRenderer dcrExcluir = new DefaultTableCellRenderer();
        dcrExcluir.setIcon(new ImageIcon(getClass().getResource("/imagens/remover.png")));
        columnModel.getColumn(1).setCellRenderer(dcrExcluir);
        columnModel.getColumn(1).setMaxWidth(20);

        getView().getTabelaFones().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                getView().setCursor(isIconeExcluirSelecionado(e) ? new Cursor(HAND_CURSOR) : getDefaultCursor());
            }
        });
    }

    private boolean isIconeExcluirSelecionado(MouseEvent e) {
        int colunaSelecionada = ((JTable) e.getSource()).columnAtPoint(e.getPoint());
        return colunaSelecionada == 1;
    }

    public AbstractTableModel getGrid() {
        if (grid == null) {

            grid = new AbstractTableModel() {
                public final String[] columnNames = {"Fone", ""};

                public int getColumnCount() {
                    return columnNames.length;
                }

                public String getColumnName(int column) {
                    return columnNames[column];
                }

                public int getRowCount() {
                    return getFones().size();
                }

                public Object getValueAt(int row, int column) {
                    FoneFornecedor fone = getFones().get(row);

                    switch (column) {
                        case 0:
                            return formataTelefone(fone.getNumFone());
                        default:
                            return null;
                    }
                }
            };
        }
        return grid;
    }

    private MouseListener getMouseListenerTableGrid() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int colunaSelecionada = ((JTable) e.getSource()).getSelectedColumn();
                int index = ((JTable) e.getSource()).getSelectedRow();

                switch (colunaSelecionada) {
                    case 1:
                        excluiFornecedor(index);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getView().setCursor(getDefaultCursor());
            }
        };
    }

    public void fireTableDataChanged() {
        getGrid().fireTableDataChanged();
    }

    private void preencheFornecedor() {
        if (isNull(fornecedor)) {
            return;
        }
        getView().getTxtFornecedor().setText(String.format("%s - %s", fornecedor.getId(), fornecedor.getNome()));
    }

    public void excluiFornecedor(int index) {
        if (showYesNoConfirmDialog(getView(), "Deseja realmente excluir o item?", "Atenção") == NO_OPTION) {
            return;
        }
        getFoneFornecedorService().apagar(getFones().get(index));
        getFones().remove(index);
        limpaTela();
    }

    @Override
    public void cadastraNovoItem() {
        if (isEmpty(getFoneSemMascara())) {
            showMessageDialog(getView(), "Fone: Campo com preenchimento Obrigatório.", "Atenção", ERROR_MESSAGE);
            getView().getTxtFone().requestFocus();
            return;
        }
        if (getFoneSemMascara().length() != 11) {
            showMessageDialog(getView(), "Fone: Valor Inválido.", "Atenção", ERROR_MESSAGE);
            limpaTela();
            return;
        }
        FoneFornecedor fone = new FoneFornecedor();
        fone.setFornecedor(fornecedor);
        fone.setNumFone(getFoneSemMascara());
        getFones().add(fone);
        getFoneFornecedorService().salvar(fone);

        showMessageDialog(getView(), "Fone cadastrado com sucesso.", "Atenção", INFORMATION_MESSAGE);
        limpaTela();
    }

    private String getFoneSemMascara() {
        return getView().getTxtFone().getText()
                .replaceAll("\\(", VAZIO)
                .replaceAll("\\)", VAZIO)
                .replaceAll("-", VAZIO);
    }

    @Override
    public void limpaTela() {
        fireTableDataChanged();
        getView().getTxtFone().setText(VAZIO);
        getView().getTxtFone().requestFocus();
    }

    @Override
    public void listaItens() {
    }

    @Override
    public void preencheItem() {
    }

    @Override
    public void excluiItem() {
    }

    @Override
    public void buscaPorCodigo() {
    }

    public List<FoneFornecedor> getFones() {
        if (isNull(fones)) {
            fones = new ArrayList<>();
        }
        return fones;
    }

    public void setFones(List<FoneFornecedor> fones) {
        this.fones = fones;
    }

    public CadastroFoneFornecedorView getView() {
        return view;
    }

    public void setView(CadastroFoneFornecedorView view) {
        this.view = view;
    }

    private FoneFornecedorService getFoneFornecedorService() {
        return ofNullable(foneFornecedorService).orElseGet(() -> foneFornecedorService = new FoneFornecedorService());
    }
}
