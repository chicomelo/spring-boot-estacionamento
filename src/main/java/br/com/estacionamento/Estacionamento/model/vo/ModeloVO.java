package br.com.estacionamento.Estacionamento.model.vo;

public class ModeloVO {

    private final Long id;
    private final String nome;
    private final MarcaVO marca;

    public ModeloVO(Long id, String nome, MarcaVO marca) {
        this.id = id;
        this.nome = nome;
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public MarcaVO getMarca() {
        return marca;
    }
}
