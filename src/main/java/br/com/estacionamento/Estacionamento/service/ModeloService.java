package br.com.estacionamento.Estacionamento.service;

import br.com.estacionamento.Estacionamento.model.Marca;
import br.com.estacionamento.Estacionamento.model.Modelo;
import br.com.estacionamento.Estacionamento.model.vo.MarcaVO;
import br.com.estacionamento.Estacionamento.model.vo.ModeloRequestVO;
import br.com.estacionamento.Estacionamento.model.vo.ModeloVO;
import br.com.estacionamento.Estacionamento.repository.MarcaRepository;
import br.com.estacionamento.Estacionamento.repository.ModeloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private final MarcaRepository marcaRepository;

    public ModeloVO salvarModelo(ModeloRequestVO request) {
        Marca marca = marcaRepository.findById(request.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));

        Modelo modelo = Modelo.builder()
                .nome(request.getNome())
                .marca(marca)
                .build();

        Modelo salvo = modeloRepository.save(modelo);

        return toVO(salvo);
    }

    public List<ModeloVO> listarModelos() {
        return modeloRepository.findAll().stream()
                .map(this::toVO)
                .collect(Collectors.toList());
    }

    public ModeloVO buscarPorId(Long id) {
        Modelo modelo = modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));
        return toVO(modelo);
    }

    public ModeloVO atualizarModelo(Long id, ModeloRequestVO request) {
        Modelo existente = modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));

        Marca marca = marcaRepository.findById(request.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));

        existente.setNome(request.getNome());
        existente.setMarca(marca);

        return toVO(modeloRepository.save(existente));
    }

    public void deletarModelo(Long id) {
        modeloRepository.deleteById(id);
    }

    private ModeloVO toVO(Modelo modelo) {
        return new ModeloVO(
                modelo.getId(),
                modelo.getNome(),
                new MarcaVO(
                        modelo.getMarca().getId(),
                        modelo.getMarca().getNome()
                )
        );
    }
}
