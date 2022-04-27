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
@Table(name = "fornecedor")
public class Fornecedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_fornecedor")
    private String nomFornecedor;
    @Column(name = "nom_complemento")
    private String nomComplemento;
    @Column(name = "nom_email")
    private String nomEmail;
    @Column(name = "nom_razao_social")
    private String nomRazaoSocial;
    @Column(name = "num_cnpj")
    private String numCnpj;
    @Column(name = "nom_inscricao_estadual")
    private String numInscEstadual;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}