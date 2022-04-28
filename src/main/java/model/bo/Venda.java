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
@Table(name = "venda")
public class Venda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_serie")
    private String numSerie;
    @Column(name = "vlr_total")
    private Double vlrTotal;
    @Column(name = "dia_vencimento_parcela")
    private Integer diaVencimentoParcela;
    @Column(name = "dat_emissao")
    private Date datEmissao;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_condicao_pagamento")
    private CondicaoPagamento condicaoPagamento;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;
}