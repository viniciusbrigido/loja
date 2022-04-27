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
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_cliente")
    private String nomCliente;
    @Column(name = "nom_complemento")
    private String nomComplemento;
    @Column(name = "nom_email")
    private String nomEmail;
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

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
}
