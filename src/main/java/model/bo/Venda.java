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
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "condicaoPagamentoId")
    private CondicaoPagamento condicaoPagamento;

    @ManyToOne
    @JoinColumn(name = "vendedorId")
    private Vendedor vendedor;
}