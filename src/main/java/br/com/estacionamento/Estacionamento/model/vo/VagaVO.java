package br.com.estacionamento.Estacionamento.model.vo;

public class VagaVO {

    private final Integer numero;
    private final boolean ocupada;

    public VagaVO(Integer numero, boolean ocupada) {
        this.numero = numero;
        this.ocupada = ocupada;
    }

    public Integer getNumero() {
        return numero;
    }

    public boolean isOcupada() {
        return ocupada;
    }
}
