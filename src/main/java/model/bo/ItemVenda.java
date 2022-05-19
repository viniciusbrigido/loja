package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import static util.ValueUtil.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "item_venda")
public class ItemVenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "qtd_produto", nullable = false)
    private Double qtdProduto;
    @Column(name = "vlr_unitario", nullable = false)
    private Double vlrUnitario;
    @Column(name = "prc_desconto")
    private Double prcDesconto;

    @ManyToOne
    @JoinColumn(name = "id_venda", nullable = false)
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id_caracteristica_produto", nullable = false)
    private CaracteristicaProduto caracteristicaProduto;

    public Double getVlrBruto() {
        return qtdProduto * vlrUnitario;
    }

    public Double getVlrLiquido() {
        Double vlrBruto = getVlrBruto();
        if (isEmpty(prcDesconto)) {
            return vlrBruto;
        }
        Double vlrLiquido = vlrBruto - (vlrBruto * prcDesconto / 100);
        if (0.01 > vlrBruto) {
            vlrLiquido += 0.01;
        }
        return vlrLiquido;
    }
}