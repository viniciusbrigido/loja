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
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "id_tamanho")
    private Tamanho tamanho;
}