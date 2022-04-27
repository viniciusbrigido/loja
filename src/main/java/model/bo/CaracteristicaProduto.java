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
@Table(name = "caracteristica_produto")
public class CaracteristicaProduto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_tamanho")
    private String numTamanho;
    @Column(name = "qtd_estoque")
    private Double qtdEstoque;
    @Column(name = "cod_barras")
    private String codBarras;

    @ManyToOne
    @JoinColumn(name = "produtoId")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "corId")
    private Cor cor;
}