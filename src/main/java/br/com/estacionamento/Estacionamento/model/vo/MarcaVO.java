package br.com.estacionamento.Estacionamento.model.vo;

public class MarcaVO {
    private final Long id;
    private final String nome;

    public MarcaVO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
}