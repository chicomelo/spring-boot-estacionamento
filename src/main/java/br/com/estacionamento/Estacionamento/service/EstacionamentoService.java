package br.com.estacionamento.Estacionamento.service;

import br.com.estacionamento.Estacionamento.model.*;
import br.com.estacionamento.Estacionamento.repository.*;
import br.com.estacionamento.Estacionamento.model.vo.CarroVO;
import br.com.estacionamento.Estacionamento.model.vo.EstadiaVO;
import br.com.estacionamento.Estacionamento.model.vo.VagaVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstacionamentoService {

    private final CarroRepository carroRepository;
    private final VagaRepository vagaRepository;
    private final EstadiaRepository estadiaRepository;

    // ===== CRUD CARROS =====
    public CarroVO criarCarro(Carro carro) {
        Carro novo = carroRepository.save(carro);
        return new CarroVO(novo.getPlaca(), novo.getModelo(), novo.getCor());
    }

    public List<CarroVO> listarCarros() {
        return carroRepository.findAll().stream()
                .map(c -> new CarroVO(c.getPlaca(), c.getModelo(), c.getCor()))
                .collect(Collectors.toList());
    }

    public CarroVO buscarCarroPorId(Long id) {
        Carro carro = carroRepository.findById(id).orElseThrow();
        return new CarroVO(carro.getPlaca(), carro.getModelo(), carro.getCor());
    }

    public CarroVO atualizarCarro(Long id, Carro carro) {
        carro.setId(id);
        Carro atualizado = carroRepository.save(carro);
        return new CarroVO(atualizado.getPlaca(), atualizado.getModelo(), atualizado.getCor());
    }

    public void deletarCarro(Long id) {
        carroRepository.deleteById(id);
    }

    // ===== CRUD VAGAS =====
    public VagaVO criarVaga(Vaga vaga) {
        Vaga nova = vagaRepository.save(vaga);
        return new VagaVO(nova.getNumero(), nova.isOcupada());
    }

    public List<VagaVO> listarVagas() {
        return vagaRepository.findAll().stream()
                .map(v -> new VagaVO(v.getNumero(), v.isOcupada()))
                .collect(Collectors.toList());
    }

    public List<VagaVO> listarVagasDisponiveis() {
        return vagaRepository.findByOcupadaFalse().stream()
                .map(v -> new VagaVO(v.getNumero(), v.isOcupada()))
                .collect(Collectors.toList());
    }

    public Vaga buscarVagaPorId(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
    }

    public VagaVO atualizarVaga(Long id, Vaga vaga) {
        vaga.setId(id);
        Vaga atualizada = vagaRepository.save(vaga);
        return new VagaVO(atualizada.getNumero(), atualizada.isOcupada());
    }

    public void deletarVaga(Long id) {
        vagaRepository.deleteById(id);
    }

    public List<VagaVO> listarVagasAtivas() {
        return vagaRepository.findByOcupadaFalse().stream()
                .map(v -> new VagaVO(v.getNumero(), v.isOcupada()))
                .collect(Collectors.toList());
    }

    public List<VagaVO> listarVagasOcupadas() {
        return vagaRepository.findByOcupadaTrue().stream()
                .map(v -> new VagaVO(v.getNumero(), v.isOcupada()))
                .collect(Collectors.toList());
    }

    // ===== ESTADIAS =====
    public List<EstadiaVO> listarEstadias() {
        return estadiaRepository.findAll().stream()
                .map(e -> new EstadiaVO(
                        new CarroVO(e.getCarro().getPlaca(), e.getCarro().getModelo(), e.getCarro().getCor()),
                        new VagaVO(e.getVaga().getNumero(), e.getVaga().isOcupada()),
                        e.getEntrada(),
                        e.getSaida()
                )).collect(Collectors.toList());
    }

    public List<EstadiaVO> listarEstadiasAtivas() {
        return estadiaRepository.findBySaidaIsNull().stream()
                .map(e -> new EstadiaVO(
                        new CarroVO(e.getCarro().getPlaca(), e.getCarro().getModelo(), e.getCarro().getCor()),
                        new VagaVO(e.getVaga().getNumero(), e.getVaga().isOcupada()),
                        e.getEntrada(),
                        e.getSaida()
                )).collect(Collectors.toList());
    }

    // ===== CONTROLE ESTACIONAMENTO =====
    @Transactional
    public EstadiaVO registrarEntrada(Long carroId, Long vagaId) {
        if (estadiaRepository.existsByCarroIdAndSaidaIsNull(carroId)) {
            throw new IllegalStateException("Carro já está estacionado!");
        }

        Carro carro = carroRepository.findById(carroId).orElseThrow();
        Vaga vaga = vagaRepository.findById(vagaId).orElseThrow();

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
                new CarroVO(nova.getCarro().getPlaca(), nova.getCarro().getModelo(), nova.getCarro().getCor()),
                new VagaVO(nova.getVaga().getNumero(), nova.getVaga().isOcupada()),
                nova.getEntrada(),
                nova.getSaida()
        );
    }

    @Transactional
    public EstadiaVO registrarSaida(Long carroId) {
        Carro carro = carroRepository.findById(carroId).orElseThrow();
        Estadia estadia = estadiaRepository.findByCarroAndSaidaIsNull(carro).orElseThrow();

        estadia.setSaida(LocalDateTime.now());
        estadia.getVaga().setOcupada(false);

        Estadia atualizada = estadiaRepository.save(estadia);

        return new EstadiaVO(
                new CarroVO(atualizada.getCarro().getPlaca(), atualizada.getCarro().getModelo(), atualizada.getCarro().getCor()),
                new VagaVO(atualizada.getVaga().getNumero(), atualizada.getVaga().isOcupada()),
                atualizada.getEntrada(),
                atualizada.getSaida()
        );
    }
}
