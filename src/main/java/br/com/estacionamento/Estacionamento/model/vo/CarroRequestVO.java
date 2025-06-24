package br.com.estacionamento.Estacionamento.model.vo;

import lombok.Data;

@Data
public class CarroRequestVO {
    private String placa;
    private String cor;
    private Long modeloId;
}
