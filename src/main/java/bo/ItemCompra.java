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
@Table(name = "item_compra")
public class ItemCompra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "qtd_produto")
    private Double qtdProduto;
    @Column(name = "vlr_unitario")
    private Double vlrUnitario;
    @Column(name = "nom_bairro")
    private Double prcDesconto;

    @ManyToOne
    @JoinColumn(name = "compraId")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "caracteristicaProdutoId")
    private CaracteristicaProduto caracteristicaProduto;
}