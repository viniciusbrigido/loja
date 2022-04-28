package personalizado;

import util.ValueUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import static util.ValueUtil.VAZIO;

public enum IconeGridVendas {
    SEM_ICONE(0, VAZIO),
    EDITAR(7, "/imagens/editar.png"),
    EXCLUIR(8, "/imagens/remover.png");

    private Integer posicaoIcone;
    private String localIcone;

    IconeGridVendas(Integer posicaoIcone, String localIcone) {
        this.posicaoIcone = posicaoIcone;
        this.localIcone = localIcone;
    }

    public static InfoIcon getInfoIcon(Integer posicaoIcone){
        return getByCodigo(posicaoIcone).getInfoIcon();
    }

    public static IconeGridVendas getByCodigo(Integer posicaoIcone){
        for (IconeGridVendas iconeGridVendas : IconeGridVendas.values()) {
            if (iconeGridVendas.posicaoIcone.compareTo(posicaoIcone) == ValueUtil.ZERO){
                return iconeGridVendas;
            }
        }
        return SEM_ICONE;
    }

    private Icon getImageIcone(){
        return new ImageIcon(IconeGridVendas.class.getResource(localIcone));
    }

    private InfoIcon getInfoIcon(){
        return new InfoIcon(getImageIcone(), posicaoIcone);
    }

    public static Collection<InfoIcon> getIcones(){
        Collection<InfoIcon> icones = new ArrayList<>();
        for (IconeGridVendas iconeGridVendas : IconeGridVendas.values()){
            icones.add(iconeGridVendas.getInfoIcon());
        }

        return icones;
    }

    public Integer getPosicaoIcone() {
        return posicaoIcone;
    }
}
