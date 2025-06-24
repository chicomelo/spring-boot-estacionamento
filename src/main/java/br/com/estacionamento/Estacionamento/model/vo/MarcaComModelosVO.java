package br.com.estacionamento.Estacionamento.model.vo;

import java.util.List;

public class MarcaComModelosVO {
    private final Long id;
    private final String nome;
    private final List<ModeloVO> modelos;

    public MarcaComModelosVO(Long id, String nome, List<ModeloVO> modelos) {
        this.id = id;
        this.nome = nome;
        this.modelos = modelos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<ModeloVO> getModelos() {
        return modelos;
    }
}
