package br.com.estacionamento.Estacionamento.service;

import br.com.estacionamento.Estacionamento.model.*;
import br.com.estacionamento.Estacionamento.model.vo.*;
import br.com.estacionamento.Estacionamento.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstadiaService {

    private final CarroRepository carroRepository;
    private final VagaRepository vagaRepository;
    private final EstadiaRepository estadiaRepository;

    public List<EstadiaVO> listarEstadias() {
        return estadiaRepository.findAll().stream()
                .map(e -> new EstadiaVO(
                        toCarroVO(e.getCarro()),
                        toVagaVO(e.getVaga()),
                        e.getEntrada(),
                        e.getSaida()
                )).collect(Collectors.toList());
    }

    public List<EstadiaVO> listarEstadiasAtivas() {
        return estadiaRepository.findBySaidaIsNull().stream()
                .map(e -> new EstadiaVO(
                        toCarroVO(e.getCarro()),
                        toVagaVO(e.getVaga()),
                        e.getEntrada(),
                        e.getSaida()
                )).collect(Collectors.toList());
    }

    @Transactional
    public EstadiaVO registrarEntrada(Long carroId, Long vagaId) {
        if (estadiaRepository.existsByCarroIdAndSaidaIsNull(carroId)) {
            throw new IllegalStateException("Carro já está estacionado!");
        }

        Carro carro = carroRepository.findById(carroId)
                .orElseThrow(() -> new IllegalStateException("Carro não encontrado."));
        Vaga vaga = vagaRepository.findById(vagaId)
                .orElseThrow(() -> new IllegalStateException("Vaga não encontrada."));

        if (vaga.isOcupada()) {
            throw new IllegalStateException("Vaga já ocupada!");
        }

        vaga.setOcupada(true);
        vagaRepository.save(vaga);

        Estadia nova = estadiaRepository.save(
                Estadia.builder()
                        .carro(carro)
                        .vaga(vaga)
                        .entrada(LocalDateTime.now())
                        .build()
        );

        return new EstadiaVO(
                toCarroVO(nova.getCarro()),
                toVagaVO(nova.getVaga()),
                nova.getEntrada(),
                nova.getSaida()
        );
    }

    @Transactional
    public EstadiaVO registrarSaida(Long carroId) {
        Carro carro = carroRepository.findById(carroId)
                .orElseThrow(() -> new IllegalStateException("Carro não encontrado."));

        Estadia estadia = estadiaRepository.findByCarroAndSaidaIsNull(carro)
                .orElseThrow(() -> new IllegalStateException("Este carro não está estacionado."));

        estadia.setSaida(LocalDateTime.now());
        estadia.getVaga().setOcupada(false);

        Estadia atualizada = estadiaRepository.save(estadia);

        return new EstadiaVO(
                toCarroVO(atualizada.getCarro()),
                toVagaVO(atualizada.getVaga()),
                atualizada.getEntrada(),
                atualizada.getSaida()
        );
    }

    private CarroVO toCarroVO(Carro carro) {
        Modelo modelo = carro.getModelo();
        Marca marca = modelo.getMarca();
        MarcaVO marcaVO = new MarcaVO(marca.getId(), marca.getNome());
        ModeloVO modeloVO = new ModeloVO(modelo.getId(), modelo.getNome(), marcaVO);
        return new CarroVO(carro.getId(), carro.getPlaca(), modeloVO, carro.getCor());
    }

    private VagaVO toVagaVO(Vaga vaga) {
        return new VagaVO(vaga.getId(), vaga.getNumero(), vaga.isOcupada());
    }
}
