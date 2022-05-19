package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static util.ValueUtil.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "fornecedor")
@AttributeOverride(name = "nome", column = @Column(name = "nom_fornecedor", nullable = false))
@AttributeOverride(name = "nomComplemento", column = @Column(name = "nom_complemento", nullable = false))
@AttributeOverride(name = "nomEmail", column = @Column(name = "nom_email"))
public class Fornecedor extends Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_razao_social", nullable = false)
    private String nomRazaoSocial;
    @Column(name = "num_cnpj", nullable = false)
    private String numCnpj;
    @Column(name = "nom_inscricao_estadual", nullable = false)
    private String numInscEstadual;

    @OneToMany(mappedBy = "fornecedor")
    @OrderBy("numFone")
    private List<FoneFornecedor> fones;

    public List<FoneFornecedor> getFones() {
        if (isNull(fones)) {
            fones = new ArrayList<>();
        }
        return fones;
    }
}