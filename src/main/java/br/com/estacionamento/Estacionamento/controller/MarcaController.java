package br.com.estacionamento.Estacionamento.controller;

import br.com.estacionamento.Estacionamento.model.Marca;
import br.com.estacionamento.Estacionamento.model.vo.MarcaComModelosVO;
import br.com.estacionamento.Estacionamento.model.vo.MarcaVO;
import br.com.estacionamento.Estacionamento.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
public class MarcaController {

    private final MarcaService marcaService;

    @PostMapping
    public ResponseEntity<Marca> criar(@RequestBody Marca marca) {
        return ResponseEntity.ok(marcaService.salvarMarca(marca));
    }

    // Listar somente marcas simples (sem modelos)
    @GetMapping
    public ResponseEntity<List<MarcaVO>> listarSimples() {
        return ResponseEntity.ok(marcaService.listarMarcas());
    }

    // Listar marcas com seus modelos
    @GetMapping("/com-modelos")
    public ResponseEntity<List<MarcaComModelosVO>> listarComModelos() {
        return ResponseEntity.ok(marcaService.listarMarcasComModelos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizarMarca(@PathVariable Long id, @RequestBody Marca marca) {
        return ResponseEntity.ok(marcaService.atualizarMarca(id, marca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMarca(@PathVariable Long id) {
        marcaService.deletarMarca(id);
        return ResponseEntity.noContent().build();
    }
}
