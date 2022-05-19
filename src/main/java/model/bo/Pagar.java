package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "pagar")
public class Pagar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vlr_emitido")
    private Double vlrEmitido;
    @Column(name = "vlr_acrescimo")
    private Double vlrAcrescimo;
    @Column(name = "vlr_desconto")
    private Double vlrDesconto;
    @Column(name = "vlr_pago")
    private Double vlrPago;
    @Column(name = "nom_status")
    private String nomStatus;
    @Column(name = "dat_vencimeto")
    private Date datVencimento;
    @Column(name = "dat_pagamento")
    private Date datPagamento;
    @Column(name = "dat_emissao")
    private Date datEmissao;

    @ManyToOne
    @JoinColumn(name = "id_compra", nullable = false)
    private Compra compra;
}