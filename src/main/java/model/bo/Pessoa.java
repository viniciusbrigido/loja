package model.bo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public class Pessoa {
    private String nome;
    private String nomComplemento;
    private String nomEmail;

    @ManyToOne
    @JoinColumn(name = "id_endereco", nullable = false)
    private Endereco endereco;
}
