package br.com.estacionamento.Estacionamento.model.vo;

import java.time.LocalDateTime;

public class EstadiaVO {

    private final CarroVO carro;
    private final VagaVO vaga;
    private final LocalDateTime entrada;
    private final LocalDateTime saida;

    public EstadiaVO(CarroVO carro, VagaVO vaga, LocalDateTime entrada, LocalDateTime saida) {
        this.carro = carro;
        this.vaga = vaga;
        this.entrada = entrada;
        this.saida = saida;
    }

    public CarroVO getCarro() {
        return carro;
    }

    public VagaVO getVaga() {
        return vaga;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }
}
