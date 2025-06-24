package br.com.estacionamento.Estacionamento.service;

import br.com.estacionamento.Estacionamento.model.Vaga;
import br.com.estacionamento.Estacionamento.model.vo.VagaVO;
import br.com.estacionamento.Estacionamento.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;

    public VagaVO criarVaga(Vaga vaga) {
        Vaga nova = vagaRepository.save(vaga);
        return new VagaVO(nova.getId(), nova.getNumero(), nova.isOcupada());
    }

    public List<VagaVO> listarVagas() {
        return vagaRepository.findAll().stream()
                .map(v -> new VagaVO(v.getId(), v.getNumero(), v.isOcupada()))
                .collect(Collectors.toList());
    }

    public List<VagaVO> listarVagasAtivas() {
        return vagaRepository.findByOcupadaFalse().stream()
                .map(v -> new VagaVO(v.getId(), v.getNumero(), v.isOcupada()))
                .collect(Collectors.toList());
    }

    public List<VagaVO> listarVagasOcupadas() {
        return vagaRepository.findByOcupadaTrue().stream()
                .map(v -> new VagaVO(v.getId(), v.getNumero(), v.isOcupada()))
                .collect(Collectors.toList());
    }

    public Vaga buscarVagaPorId(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
    }

    public VagaVO atualizarVaga(Long id, Vaga vaga) {
        vaga.setId(id);
        Vaga atualizada = vagaRepository.save(vaga);
        return new VagaVO(atualizada.getId(), atualizada.getNumero(), atualizada.isOcupada());
    }

    public void deletarVaga(Long id) {
        vagaRepository.deleteById(id);
    }
}
