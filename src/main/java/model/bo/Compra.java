package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import static util.ValueUtil.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "compra")
public class Compra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_nf")
    private Integer numNF;
    @Column(name = "num_serie_nf")
    private String numSerieNF;
    @Column(name = "vlr_total")
    private Double vlrTotal;
    @Column(name = "dat_compra", nullable = false)
    private Date datCompra;

    @ManyToOne
    @JoinColumn(name = "id_condicao_pagamento", nullable = false)
    private CondicaoPagamento condicaoPagamento;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;

    @OneToMany(mappedBy = "compra")
    @OrderBy("id")
    private List<ItemCompra> itens;

    public List<ItemCompra> getItens() {
        if (isNull(itens)) {
            itens = new ArrayList<>();
        }
        return itens;
    }
}