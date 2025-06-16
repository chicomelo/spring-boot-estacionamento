package br.com.estacionamento.Estacionamento.model.vo;

public class CarroVO {

    private final String placa;
    private final String modelo;
    private final String cor;

    public CarroVO(String placa, String modelo, String cor) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }
}
