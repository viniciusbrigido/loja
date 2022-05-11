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
@AttributeOverride(name = "nome", column = @Column(name = "nom_fornecedor"))
@AttributeOverride(name = "nomComplemento", column = @Column(name = "nom_complemento"))
@AttributeOverride(name = "nomEmail", column = @Column(name = "nom_email"))
public class Fornecedor extends Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_razao_social")
    private String nomRazaoSocial;
    @Column(name = "num_cnpj")
    private String numCnpj;
    @Column(name = "nom_inscricao_estadual")
    private String numInscEstadual;
}