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
@Table(name = "vendedor")
public class Vendedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_bairro")
    private String nom_vendedor;
    @Column(name = "nom_complemento")
    private String nomComplemento;
    @Column(name = "nom_email")
    private String nomEmail;
    @Column(name = "num_cpf")
    private String numCpf;
    @Column(name = "num_fone")
    private String numFone;
    @Column(name = "num_fone2")
    private String numFone2;
    @Column(name = "prc_comissao_venda ")
    private Double prcComissaoVenda;
    @Column(name = "prc_comissao_recebimento ")
    private Double prcComissaoRecebto;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}