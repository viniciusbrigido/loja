package dto;

public class ColunaDto {
    private String nome;
    private int alinhamento;
    private int tamanho;
    private boolean id;

    public ColunaDto() {
    }

    public ColunaDto(String nome, int alinhamento, int tamanho, boolean id) {
        this.nome = nome;
        this.alinhamento = alinhamento;
        this.tamanho = tamanho;
        this.id = id;
    }

    public ColunaDto(int alinhamento, int tamanho, boolean id) {
        this.alinhamento = alinhamento;
        this.tamanho = tamanho;
        this.id = id;
    }

    public ColunaDto(String nome, int alinhamento, int tamanho) {
        this.nome = nome;
        this.alinhamento = alinhamento;
        this.tamanho = tamanho;
    }

    public ColunaDto(int alinhamento, int tamanho) {
        this.alinhamento = alinhamento;
        this.tamanho = tamanho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAlinhamento() {
        return alinhamento;
    }

    public void setAlinhamento(int alinhamento) {
        this.alinhamento = alinhamento;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }
}
