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
@Table(name = "fone_fornecedor")
public class FoneFornecedor implements Serializable {

    @Id
    @Column(name = "num_fone")
    private String numFone;

    @OneToOne
    @JoinColumn(name = "id_fornecedor", nullable = false)
    private Fornecedor fornecedor;
}