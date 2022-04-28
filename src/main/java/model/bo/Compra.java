package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
    @Column(name = "dat_compra")
    private Date datCompra;

    @ManyToOne
    @JoinColumn(name = "id_condicao_pagamento")
    private CondicaoPagamento condicaoPagamento;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;
}