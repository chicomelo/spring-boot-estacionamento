package br.com.estacionamento.Estacionamento.controller;

import br.com.estacionamento.Estacionamento.model.vo.CarroRequestVO;
import br.com.estacionamento.Estacionamento.model.vo.CarroVO;
import br.com.estacionamento.Estacionamento.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
@RequiredArgsConstructor
public class CarroController {

    private final CarroService carroService;

    @PostMapping
    public ResponseEntity<CarroVO> criarCarro(@RequestBody CarroRequestVO request) {
        return ResponseEntity.ok(carroService.criarCarro(request));
    }

    @GetMapping
    public ResponseEntity<List<CarroVO>> listarCarros() {
        return ResponseEntity.ok(carroService.listarCarros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroVO> buscarCarro(@PathVariable Long id) {
        return ResponseEntity.ok(carroService.buscarCarroPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroVO> atualizarCarro(@PathVariable Long id, @RequestBody CarroRequestVO request) {
        return ResponseEntity.ok(carroService.atualizarCarro(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id) {
        carroService.deletarCarro(id);
        return ResponseEntity.noContent().build();
    }
}
