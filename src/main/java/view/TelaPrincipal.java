package view;

import controller.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.beans.PropertyVetoException;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.KeyEvent.*;
import static java.lang.Short.*;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.KeyStroke.*;

public class TelaPrincipal extends JFrame {

    private JMenuBar menu;

    private JMenu menuCadastros;
    private JMenuItem menuItemBairro;
    private JMenuItem menuItemCidade;
    private JMenuItem menuItemEndereco;
    private JMenuItem menuItemMarca;
    private JMenuItem menuItemTamanho;
    private JMenuItem menuItemTipoProduto;
    private JMenuItem menuItemCor;
    private JMenuItem menuItemProduto;
    private JMenuItem menuItemCondicao;
    private JMenuItem menuItemCliente;
    private JMenuItem menuItemFornecedor;
    private JMenuItem menuItemVendedor;

    private JMenu menuMovimentos;
    private JMenuItem menuItemVenda;

    private JMenu menuRelatorios;
    private JMenu menuAjuda;
    private JMenu menuPessoa;

    private JPanel contentPane;
    private JDesktopPane desktop;

    public TelaPrincipal() {
        setTitle("Loja Brigido's e Severino's");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(LEADING).addGap(0, 633, MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(LEADING).addGap(0, 398, MAX_VALUE));
        pack();
        setLocationRelativeTo(null);
        setContentPane(getJContentPane());
    }

    private JPanel getJContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(getDesktop(), java.awt.BorderLayout.CENTER);
        return contentPane;
    }

    public JMenuBar getMenu() {
        if (menu == null) {
            menu = new JMenuBar();

            menu.add(getMenuCadastros());
            menu.add(getMenuMovimentos());
            menu.add(getMenuRelatorios());
            menu.add(getMenuAjuda());
        }
        return menu;
    }

    public JMenu getMenuCadastros() {
        if (menuCadastros == null) {
            menuCadastros = new JMenu();
            menuCadastros.setFont(new Font("Tahoma", Font.BOLD, 11));
            menuCadastros.setText("1 - Cadastros");

            menuCadastros.add(getMenuItemBairro());
            menuCadastros.add(getMenuItemCidade());
            menuCadastros.add(getMenuItemEndereco());
            menuCadastros.addSeparator();
            menuCadastros.add(getMenuItemMarca());
            menuCadastros.add(getMenuItemTamanho());
            menuCadastros.add(getMenuItemTipoProduto());
            menuCadastros.add(getMenuItemCor());
            menuCadastros.add(getMenuItemProduto());
            menuCadastros.add(getMenuItemCondicao());
            menuCadastros.addSeparator();
            menuCadastros.add(getMenuPessoa());
        }
        return menuCadastros;
    }

    private JMenu getMenuMovimentos() {
        if (menuMovimentos == null) {
            menuMovimentos = new JMenu();
            menuMovimentos.setFont(new Font("Tahoma", Font.BOLD, 11));
            menuMovimentos.setText("2 - Movimentos");

            menuMovimentos.add(getMenuItemVenda());
        }
        return menuMovimentos;
    }

    private JMenu getMenuRelatorios() {
        if (menuRelatorios == null) {
            menuRelatorios = new JMenu();
            menuRelatorios.setFont(new Font("Tahoma", Font.BOLD, 11));
            menuRelatorios.setText("3 - Relatórios");
        }
        return menuRelatorios;
    }

    private JMenu getMenuAjuda() {
        if (menuAjuda == null) {
            menuAjuda = new JMenu();
            menuAjuda.setFont(new Font("Tahoma", Font.BOLD, 11));
            menuAjuda.setText("4 - Ajuda");
        }
        return menuAjuda;
    }

    public JMenuItem getMenuItemBairro() {
        if (menuItemBairro == null) {
            menuItemBairro = new JMenuItem();
            menuItemBairro.setText("Bairro");
            menuItemBairro.setAccelerator(getKeyStroke(VK_F1, CTRL_DOWN_MASK));
            menuItemBairro.addActionListener(a -> new CadastroBairroController());
            menuItemBairro.setIcon(new ImageIcon(getClass().getResource("/imagens/neighborhood.png")));
        }
        return menuItemBairro;
    }

    public JMenuItem getMenuItemCidade() {
        if (menuItemCidade == null) {
            menuItemCidade = new JMenuItem();
            menuItemCidade.setText("Cidade");
            menuItemCidade.setAccelerator(getKeyStroke(VK_F2, CTRL_DOWN_MASK));
            menuItemCidade.addActionListener(a -> new CadastroCidadeController());
            menuItemCidade.setIcon(new ImageIcon(getClass().getResource("/imagens/city.png")));
        }
        return menuItemCidade;
    }

    public JMenuItem getMenuItemEndereco() {
        if (menuItemEndereco == null) {
            menuItemEndereco = new JMenuItem();
            menuItemEndereco.setText("Endereço");
            menuItemEndereco.setAccelerator(getKeyStroke(VK_F3, CTRL_DOWN_MASK));
            menuItemEndereco.addActionListener(a -> new CadastroEnderecoController());
            menuItemEndereco.setIcon(new ImageIcon(getClass().getResource("/imagens/adress.png")));
        }
        return menuItemEndereco;
    }

    public JMenuItem getMenuItemMarca() {
        if (menuItemMarca == null) {
            menuItemMarca = new JMenuItem();
            menuItemMarca.setText("Marca");
            menuItemMarca.setAccelerator(getKeyStroke(VK_F4, CTRL_DOWN_MASK));
            menuItemMarca.addActionListener(a -> new CadastroMarcaController());
            menuItemMarca.setIcon(new ImageIcon(getClass().getResource("/imagens/logo.png")));
        }
        return menuItemMarca;
    }

    public JMenuItem getMenuItemTamanho() {
        if (menuItemTamanho == null) {
            menuItemTamanho = new JMenuItem();
            menuItemTamanho.setText("Tamanho");
            menuItemTamanho.setAccelerator(getKeyStroke(VK_F5, CTRL_DOWN_MASK));
            menuItemTamanho.addActionListener(a -> new CadastroTamanhoController());
            menuItemTamanho.setIcon(new ImageIcon(getClass().getResource("/imagens/size.png")));
        }
        return menuItemTamanho;
    }

    public JMenuItem getMenuItemTipoProduto() {
        if (menuItemTipoProduto == null) {
            menuItemTipoProduto = new JMenuItem();
            menuItemTipoProduto.setText("Tipo de Produto");
            menuItemTipoProduto.setAccelerator(getKeyStroke(VK_F6, CTRL_DOWN_MASK));
            menuItemTipoProduto.addActionListener(a -> new CadastroTipoController());
            menuItemTipoProduto.setIcon(new ImageIcon(getClass().getResource("/imagens/type.png")));
        }
        return menuItemTipoProduto;
    }

    public JMenuItem getMenuItemCor() {
        if (menuItemCor == null) {
            menuItemCor = new JMenuItem();
            menuItemCor.setText("Cor");
            menuItemCor.setAccelerator(getKeyStroke(VK_F7, CTRL_DOWN_MASK));
            menuItemCor.addActionListener(a -> new CadastroCorController());
            menuItemCor.setIcon(new ImageIcon(getClass().getResource("/imagens/color.png")));
        }
        return menuItemCor;
    }

    public JMenuItem getMenuItemProduto() {
        if (menuItemProduto == null) {
            menuItemProduto = new JMenuItem();
            menuItemProduto.setText("Produto");
            menuItemProduto.setAccelerator(getKeyStroke(VK_F8, CTRL_DOWN_MASK));
            menuItemProduto.addActionListener(a -> new CadastroProdutoController());
            menuItemProduto.setIcon(new ImageIcon(getClass().getResource("/imagens/product.png")));
        }
        return menuItemProduto;
    }

    public JMenuItem getMenuItemCondicao() {
        if (menuItemCondicao == null) {
            menuItemCondicao = new JMenuItem();
            menuItemCondicao.setText("Condição de Pagamento");
            menuItemCondicao.setAccelerator(getKeyStroke(VK_F9, CTRL_DOWN_MASK));
            menuItemCondicao.addActionListener(a -> new CadastroCondicaoPagamentoController());
            menuItemCondicao.setIcon(new ImageIcon(getClass().getResource("/imagens/payment.png")));
        }
        return menuItemCondicao;
    }

    private JMenu getMenuPessoa() {
        if (menuPessoa == null) {
            menuPessoa = new JMenu();
            menuPessoa.setFont(new Font("Tahoma", Font.BOLD, 11));
            menuPessoa.setText("Pessoa");
            menuPessoa.setIcon(new ImageIcon(getClass().getResource("/imagens/client.png")));

            menuPessoa.add(getMenuItemCliente());
            menuPessoa.add(getMenuItemFornecedor());
            menuPessoa.add(getMenuItemVendedor());
        }
        return menuPessoa;
    }

    public JMenuItem getMenuItemCliente() {
        if (menuItemCliente == null) {
            menuItemCliente = new JMenuItem();
            menuItemCliente.setText("Cliente");
            menuItemCliente.setAccelerator(getKeyStroke(VK_F10, CTRL_DOWN_MASK));
            menuItemCliente.addActionListener(a -> new CadastroClienteController());
            menuItemCliente.setIcon(new ImageIcon(getClass().getResource("/imagens/client.png")));
        }
        return menuItemCliente;
    }

    public JMenuItem getMenuItemFornecedor() {
        if (menuItemFornecedor == null) {
            menuItemFornecedor = new JMenuItem();
            menuItemFornecedor.setText("Fornecedor");
            menuItemFornecedor.setAccelerator(getKeyStroke(VK_F11, CTRL_DOWN_MASK));
            menuItemFornecedor.addActionListener(a -> new CadastroFornecedorController());
            menuItemFornecedor.setIcon(new ImageIcon(getClass().getResource("/imagens/client.png")));
        }
        return menuItemFornecedor;
    }

    public JMenuItem getMenuItemVendedor() {
        if (menuItemVendedor == null) {
            menuItemVendedor = new JMenuItem();
            menuItemVendedor.setText("Vendedor");
            menuItemVendedor.setAccelerator(getKeyStroke(VK_F12, CTRL_DOWN_MASK));
            menuItemVendedor.addActionListener(a -> new CadastroVendedorController());
            menuItemVendedor.setIcon(new ImageIcon(getClass().getResource("/imagens/client.png")));
        }
        return menuItemVendedor;
    }

    public JMenuItem getMenuItemVenda() {
        if (menuItemVenda == null) {
            menuItemVenda = new JMenuItem();
            menuItemVenda.setText("Venda");
            menuItemVenda.setAccelerator(getKeyStroke(VK_F1, SHIFT_DOWN_MASK));
            menuItemVenda.addActionListener(a -> new VendaController());
            menuItemVenda.setIcon(new ImageIcon(getClass().getResource("/imagens/client.png")));
        }
        return menuItemVenda;
    }

    public JDesktopPane getDesktop() {
        if (desktop != null) {
            return desktop;
        }

        desktop = new JDesktopPane();
        desktop.setBackground(Color.GRAY);
        desktop.setVisible(true);
        return desktop;
    }


    public static void main(String args[]) {
        new TelaPrincipal().setVisible(true);
    }
}
