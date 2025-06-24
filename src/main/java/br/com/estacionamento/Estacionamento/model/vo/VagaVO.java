package br.com.estacionamento.Estacionamento.model.vo;

public class VagaVO {

    private final Long id;
    private final Integer numero;
    private final boolean ocupada;

    public VagaVO(Long id, Integer numero, boolean ocupada) {
        this.id = id;
        this.numero = numero;
        this.ocupada = ocupada;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumero() {
        return numero;
    }

    public boolean isOcupada() {
        return ocupada;
    }
}
