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
@AttributeOverride(name = "nome", column = @Column(name = "nom_cliente"))
@AttributeOverride(name = "nomComplemento", column = @Column(name = "nom_complemento"))
@AttributeOverride(name = "nomEmail", column = @Column(name = "nom_email"))
public class Cliente extends Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "num_cpf")
    private String numCpf;
    @Column(name = "num_rg")
    private String numRg;
    @Column(name = "num_fone")
    private String numFone;
    @Column(name = "num_fone2")
    private String numFone2;
    @Column(name = "dat_nascimento")
    private Date datNascimento;
}
