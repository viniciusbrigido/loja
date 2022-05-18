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
@Table(name = "cliente")
@AttributeOverride(name = "nome", column = @Column(name = "nom_cliente", nullable = false))
@AttributeOverride(name = "nomComplemento", column = @Column(name = "nom_complemento", nullable = false))
@AttributeOverride(name = "nomEmail", column = @Column(name = "nom_email"))
public class Cliente extends Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_cpf", nullable = false)
    private String numCpf;
    @Column(name = "num_rg", nullable = false)
    private String numRg;
    @Column(name = "num_fone", nullable = false)
    private String numFone;
    @Column(name = "num_fone2")
    private String numFone2;
    @Column(name = "dat_nascimento", nullable = false)
    private Date datNascimento;
}
