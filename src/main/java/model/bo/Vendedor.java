package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "vendedor")
@AttributeOverride(name = "nome", column = @Column(name = "nom_vendedor", nullable = false))
@AttributeOverride(name = "nomComplemento", column = @Column(name = "nom_complemento", nullable = false))
@AttributeOverride(name = "nomEmail", column = @Column(name = "nom_email"))
public class Vendedor extends Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_cpf", nullable = false)
    private String numCpf;
    @Column(name = "num_fone", nullable = false)
    private String numFone;
    @Column(name = "num_fone2")
    private String numFone2;
    @Column(name = "prc_comissao_venda")
    private Double prcComissaoVenda;
    @Column(name = "prc_comissao_recebimento")
    private Double prcComissaoRecebto;
}