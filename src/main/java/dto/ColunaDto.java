package dto;

import lombok.*;

@AllArgsConstructor
public class ColunaDto {
    @Getter @Setter
    private String nome;
    @Getter
    private int alinhamento;
    @Getter
    private int tamanho;

    public ColunaDto(int alinhamento, int tamanho) {
        this.alinhamento = alinhamento;
        this.tamanho = tamanho;
    }
}
