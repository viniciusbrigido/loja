package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "item_compra")
public class ItemCompra implements Serializable {
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
    @JoinColumn(name = "id_compra", nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_caracteristica_produto", nullable = false)
    private CaracteristicaProduto caracteristicaProduto;
}