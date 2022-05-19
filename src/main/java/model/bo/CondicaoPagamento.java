package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "condicao_pagamento")
public class CondicaoPagamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_condicao_pagamento", nullable = false)
    private String nomCondicaoPagamento;
    @Column(name = "num_dias_ate_primeira_parcela", nullable = false)
    private Integer numDiasAtePrimeiraParcela;
    @Column(name = "num_dias_entre_parcela", nullable = false)
    private Integer numDiasEntreParcelas;
}