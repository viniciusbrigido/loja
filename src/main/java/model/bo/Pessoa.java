package model.bo;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class Pessoa {
    private String nome;
    private String nomComplemento;
    private String nomEmail;
    private Integer codEndereco;
}
