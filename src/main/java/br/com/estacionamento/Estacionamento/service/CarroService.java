package br.com.estacionamento.Estacionamento.service;

import br.com.estacionamento.Estacionamento.model.Carro;
import br.com.estacionamento.Estacionamento.model.Marca;
import br.com.estacionamento.Estacionamento.model.Modelo;
import br.com.estacionamento.Estacionamento.model.vo.CarroRequestVO;
import br.com.estacionamento.Estacionamento.model.vo.CarroVO;
import br.com.estacionamento.Estacionamento.model.vo.MarcaVO;
import br.com.estacionamento.Estacionamento.model.vo.ModeloVO;
import br.com.estacionamento.Estacionamento.repository.CarroRepository;
import br.com.estacionamento.Estacionamento.repository.ModeloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository carroRepository;
    private final ModeloRepository modeloRepository;

    public CarroVO criarCarro(CarroRequestVO request) {
        Modelo modelo = modeloRepository.findById(request.getModeloId())
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));

        Carro carro = Carro.builder()
                .placa(request.getPlaca())
                .cor(request.getCor())
                .modelo(modelo)
                .build();

        Carro salvo = carroRepository.save(carro);
        return toCarroVO(salvo);
    }

    public List<CarroVO> listarCarros() {
        return carroRepository.findAll().stream()
                .map(this::toCarroVO)
                .collect(Collectors.toList());
    }

    public CarroVO buscarCarroPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
        return toCarroVO(carro);
    }

    public CarroVO atualizarCarro(Long id, CarroRequestVO request) {
        Modelo modelo = modeloRepository.findById(request.getModeloId())
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));

        Carro carro = Carro.builder()
                .id(id)
                .placa(request.getPlaca())
                .cor(request.getCor())
                .modelo(modelo)
                .build();

        Carro atualizado = carroRepository.save(carro);
        return toCarroVO(atualizado);
    }

    public void deletarCarro(Long id) {
        carroRepository.deleteById(id);
    }

    private CarroVO toCarroVO(Carro carro) {
        Modelo modelo = carro.getModelo();
        Marca marca = modelo.getMarca();

        ModeloVO modeloVO = new ModeloVO(
                modelo.getId(),
                modelo.getNome(),
                new MarcaVO(marca.getId(), marca.getNome())
        );

        return new CarroVO(
                carro.getId(),
                carro.getPlaca(),
                modeloVO,
                carro.getCor()
        );
    }
}
