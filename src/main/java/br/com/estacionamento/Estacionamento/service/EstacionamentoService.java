package br.com.estacionamento.Estacionamento.service;

import br.com.estacionamento.Estacionamento.model.*;
import br.com.estacionamento.Estacionamento.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstacionamentoService {

    private final CarroRepository carroRepository;
    private final VagaRepository vagaRepository;
    private final EstadiaRepository estadiaRepository;

    // ===== CRUD CARROS =====
    public Carro criarCarro(Carro carro) {
        return carroRepository.save(carro); // Sem validação de placa
    }

    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    public Carro buscarCarroPorId(Long id) {
        return carroRepository.findById(id).orElse(null);
    }

    public Carro atualizarCarro(Long id, Carro carro) {
        carro.setId(id); // Garante que o ID seja o mesmo
        return carroRepository.save(carro); // Atualiza ou cria se não existir
    }

    public void deletarCarro(Long id) {
        carroRepository.deleteById(id); // Sem validação de estadia ativa
    }

    // ===== CRUD VAGAS =====
    public Vaga criarVaga(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

    public List<Vaga> listarVagas() {
        return vagaRepository.findAll();
    }

    public List<Vaga> listarVagasDisponiveis() {
        return vagaRepository.findByOcupadaFalse();
    }

    public Vaga buscarVagaPorId(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
    }

    public Vaga atualizarVaga(Long id, Vaga vaga) {
        vaga.setId(id);
        return vagaRepository.save(vaga);
    }

    public void deletarVaga(Long id) {
        vagaRepository.deleteById(id);
    }

    public List<Vaga> listarVagasAtivas() {
        return vagaRepository.findByOcupadaFalse();
    }

    public List<Vaga> listarVagasOcupadas() {
        return vagaRepository.findByOcupadaTrue();
    }

    // ===== ESTADIAS =====
    public List<Estadia> listarEstadias() {
        return estadiaRepository.findAll();
    }
    public List<Estadia> listarEstadiasAtivas() {
        return estadiaRepository.findBySaidaIsNull();
    }

    // ===== CONTROLE ESTACIONAMENTO =====
    @Transactional
    public Estadia registrarEntrada(Long carroId, Long vagaId) {
        // Validações
        if (estadiaRepository.existsByCarroIdAndSaidaIsNull(carroId)) {
            throw new IllegalStateException("Carro já está estacionado!");
        }

        Carro carro = carroRepository.findById(carroId).orElseThrow();
        Vaga vaga = vagaRepository.findById(vagaId).orElseThrow();

        if (vaga.isOcupada()) {
            throw new IllegalStateException("Vaga já ocupada!");
        }

        // Atualiza vaga e cria estadia
        vaga.setOcupada(true);
        vagaRepository.save(vaga);

        return estadiaRepository.save(
                Estadia.builder()
                        .carro(carro)
                        .vaga(vaga)
                        .entrada(LocalDateTime.now())
                        .build()
        );
    }

    @Transactional
    public Estadia registrarSaida(Long carroId) {
        Carro carro = carroRepository.findById(carroId).orElseThrow();
        Estadia estadia = estadiaRepository.findByCarroAndSaidaIsNull(carro).orElseThrow();

        estadia.setSaida(LocalDateTime.now());
        estadia.getVaga().setOcupada(false);

        return estadiaRepository.save(estadia);
    }


}