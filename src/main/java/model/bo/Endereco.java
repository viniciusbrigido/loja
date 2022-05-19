package model.bo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom_cep", nullable = false)
    private String nomCep;
    @Column(name = "nom_logradouro", nullable = false)
    private String nomLogradouro;

    @ManyToOne
    @JoinColumn(name = "id_bairro", nullable = false)
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "id_cidade", nullable = false)
    private Cidade cidade;
}