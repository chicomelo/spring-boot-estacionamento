package br.com.estacionamento.Estacionamento.controller;

import br.com.estacionamento.Estacionamento.model.vo.EstadiaVO;
import br.com.estacionamento.Estacionamento.service.EstadiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estacionamento")
@RequiredArgsConstructor
public class EstadiaController {

    private final EstadiaService estadiaService;

    @PostMapping("/entrada")
    public ResponseEntity<EstadiaVO> registrarEntrada(@RequestParam Long carroId, @RequestParam Long vagaId) {
        return ResponseEntity.ok(estadiaService.registrarEntrada(carroId, vagaId));
    }

    @PostMapping("/saida")
    public ResponseEntity<EstadiaVO> registrarSaida(@RequestParam Long carroId) {
        return ResponseEntity.ok(estadiaService.registrarSaida(carroId));
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<EstadiaVO>> listarEstadiasAtivas() {
        return ResponseEntity.ok(estadiaService.listarEstadiasAtivas());
    }

    @GetMapping("/todas")
    public ResponseEntity<List<EstadiaVO>> listarEstadias() {
        return ResponseEntity.ok(estadiaService.listarEstadias());
    }
}
