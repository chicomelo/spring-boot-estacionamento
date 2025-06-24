package br.com.estacionamento.Estacionamento.service;

import br.com.estacionamento.Estacionamento.model.Marca;
import br.com.estacionamento.Estacionamento.model.vo.MarcaComModelosVO;
import br.com.estacionamento.Estacionamento.model.vo.MarcaVO;
import br.com.estacionamento.Estacionamento.model.vo.ModeloVO;
import br.com.estacionamento.Estacionamento.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public Marca salvarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    public List<MarcaVO> listarMarcas() {
        return marcaRepository.findAll().stream()
                .map(marca -> new MarcaVO(marca.getId(), marca.getNome()))
                .collect(Collectors.toList());
    }

    public List<MarcaComModelosVO> listarMarcasComModelos() {
        return marcaRepository.findAll().stream()
                .map(marca -> new MarcaComModelosVO(
                        marca.getId(),
                        marca.getNome(),
                        marca.getModelos().stream()
                                .map(modelo -> new ModeloVO(
                                        modelo.getId(),
                                        modelo.getNome(),
                                        null // evitar recursividade
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public Marca buscarPorId(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
    }

    public Marca atualizarMarca(Long id, Marca marcaAtualizada) {
        Marca existente = marcaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Marca não encontrada"));
        existente.setNome(marcaAtualizada.getNome());
        return marcaRepository.save(existente);
    }

    public void deletarMarca(Long id) {
        marcaRepository.deleteById(id);
    }
}
