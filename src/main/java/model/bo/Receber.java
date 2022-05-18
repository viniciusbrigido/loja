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
@Table(name = "receber")
public class Receber implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vlr_emissao")
    private Double vlrEmissao;
    @Column(name = "vlr_acrescimo")
    private Double vlrAcrescimo;
    @Column(name = "vlr_desconto")
    private Double vlrDesconto;
    @Column(name = "vlr_pago")
    private Double vlrPago;
    @Column(name = "nom_status")
    private String nomStatus;
    @Column(name = "dat_emissao")
    private Date datEmissao;
    @Column(name = "dat_vencimento")
    private Date datVencimento;
    @Column(name = "dat_pagamento")
    private Date datPagamento;

    @ManyToOne
    @JoinColumn(name = "id_venda", nullable = false)
    private Venda venda;
}