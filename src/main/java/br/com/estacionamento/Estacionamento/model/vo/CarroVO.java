package br.com.estacionamento.Estacionamento.model.vo;

public class CarroVO {

    private final Long id;
    private final String placa;
    private final ModeloVO modelo; // modelo completo com nome + marca
    private final String cor;

    public CarroVO(Long id, String placa, ModeloVO modelo, String cor) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
    }

    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public ModeloVO getModelo() {
        return modelo;
    }

    public String getCor() {
        return cor;
    }
}
