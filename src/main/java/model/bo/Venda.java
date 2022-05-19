package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;
import static util.ValueUtil.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "venda")
public class Venda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_serie")
    private String numSerie;
    @Column(name = "vlr_total", nullable = false)
    private Double vlrTotal;
    @Column(name = "dia_vencimento_parcela")
    private Integer diaVencimentoParcela;
    @Column(name = "dat_emissao", nullable = false)
    private Date datEmissao;
    @Column(name = "hor_emissao", nullable = false)
    private Time horEmissao;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_condicao_pagamento", nullable = false)
    private CondicaoPagamento condicaoPagamento;

    @ManyToOne
    @JoinColumn(name = "id_vendedor", nullable = false)
    private Vendedor vendedor;

    @OneToMany(mappedBy = "venda")
    @OrderBy("id")
    private List<ItemVenda> itens;

    public List<ItemVenda> getItens() {
        if (isNull(itens)) {
            itens = new ArrayList<>();
        }
        return itens;
    }
}