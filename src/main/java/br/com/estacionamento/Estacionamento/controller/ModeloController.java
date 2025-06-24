package br.com.estacionamento.Estacionamento.controller;

import br.com.estacionamento.Estacionamento.model.vo.ModeloRequestVO;
import br.com.estacionamento.Estacionamento.model.vo.ModeloVO;
import br.com.estacionamento.Estacionamento.service.ModeloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modelos")
@RequiredArgsConstructor
public class ModeloController {

    private final ModeloService modeloService;

    @PostMapping
    public ResponseEntity<ModeloVO> criarModelo(@RequestBody ModeloRequestVO request) {
        ModeloVO modeloCriado = modeloService.salvarModelo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(modeloCriado);
    }

    @GetMapping
    public ResponseEntity<List<ModeloVO>> listar() {
        return ResponseEntity.ok(modeloService.listarModelos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeloVO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(modeloService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModeloVO> atualizarModelo(@PathVariable Long id, @RequestBody ModeloRequestVO request) {
        return ResponseEntity.ok(modeloService.atualizarModelo(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarModelo(@PathVariable Long id) {
        modeloService.deletarModelo(id);
        return ResponseEntity.noContent().build();
    }
}
