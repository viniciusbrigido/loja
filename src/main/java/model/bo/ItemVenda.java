package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "item_venda")
public class ItemVenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "qtd_produto")
    private Double qtdProduto;
    @Column(name = "vlr_unitario")
    private Double vlrUnitario;
    @Column(name = "prc_desconto")
    private Double prcDesconto;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "id_caracteristica_produto")
    private CaracteristicaProduto caracteristicaProduto;
}