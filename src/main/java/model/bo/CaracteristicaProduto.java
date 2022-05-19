package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "caracteristica_produto")
public class CaracteristicaProduto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_tamanho", nullable = false)
    private String numTamanho;
    @Column(name = "qtd_estoque", nullable = false)
    private Double qtdEstoque;
    @Column(name = "cod_barras", nullable = false)
    private String codBarras;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_cor", nullable = false)
    private Cor cor;
}