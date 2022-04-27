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
@Table(name = "produto")
public class Produto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_produto")
    private String nomProduto;
    @Column(name = "vlr_produto")
    private Double vlrProduto;

    @ManyToOne
    @JoinColumn(name = "marcaId")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "tipoId")
    private Tipo tipoProduto;

    @ManyToOne
    @JoinColumn(name = "tamanhoId")
    private Tamanho tamanho;
}