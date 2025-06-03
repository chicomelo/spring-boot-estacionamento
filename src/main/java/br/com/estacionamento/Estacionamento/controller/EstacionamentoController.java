package br.com.estacionamento.Estacionamento.controller;

import br.com.estacionamento.Estacionamento.model.*;
import br.com.estacionamento.Estacionamento.service.EstacionamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EstacionamentoController {

    private final EstacionamentoService service;

    // ---- CRUD Completo Carros ----
    @PostMapping("/carros")
    public ResponseEntity<Carro> criarCarro(@RequestBody Carro carro) {
        return ResponseEntity.ok(service.criarCarro(carro));
    }

    @GetMapping("/carros")
    public ResponseEntity<List<Carro>> listarCarros() {
        return ResponseEntity.ok(service.listarCarros());
    }

    @GetMapping("/carros/{id}")
    public ResponseEntity<Carro> buscarCarro(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarCarroPorId(id));
    }

    @PutMapping("/carros/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carro) {
        return ResponseEntity.ok(service.atualizarCarro(id, carro));
    }

    @DeleteMapping("/carros/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id) {
        service.deletarCarro(id);
        return ResponseEntity.noContent().build();
    }

    // ---- CRUD Completo Vagas ----
    @PostMapping("/vagas")
    public ResponseEntity<Vaga> criarVaga(@RequestBody Vaga vaga) {
        return ResponseEntity.ok(service.criarVaga(vaga));
    }

    @GetMapping("/vagas")
    public ResponseEntity<List<Vaga>> listarVagas() {
        return ResponseEntity.ok(service.listarVagas());
    }

    @GetMapping("/vagas/ativas")
    public ResponseEntity<List<Vaga>> listarVagasAtivas() {
        return ResponseEntity.ok(service.listarVagasAtivas());
    }

    @GetMapping("/vagas/ocupadas")
    public ResponseEntity<List<Vaga>> listarVagasOcupadas() {
        return ResponseEntity.ok(service.listarVagasOcupadas());
    }

    @GetMapping("/vagas/{id}")
    public ResponseEntity<Vaga> buscarVagaPorId(@PathVariable Long id) {
        Vaga vaga = service.buscarVagaPorId(id);
        return ResponseEntity.ok(vaga);
    }

    @PutMapping("/vagas/{id}")
    public ResponseEntity<Vaga> atualizarVaga(@PathVariable Long id, @RequestBody Vaga vaga) {
        return ResponseEntity.ok(service.atualizarVaga(id, vaga));
    }

    @DeleteMapping("/vagas/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        service.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }

    // ---- CRUD Estadias + Controle Estacionamento ----
    @GetMapping("/estadias")
    public ResponseEntity<List<Estadia>> listarEstadias() {
        return ResponseEntity.ok(service.listarEstadias());
    }

    @GetMapping("/estacionamento/ativos")
    public ResponseEntity<List<Estadia>> listarEstadiasAtivas() {
        return ResponseEntity.ok(service.listarEstadiasAtivas());
    }

    @PostMapping("/estacionamento/entrada")
    public ResponseEntity<Estadia> registrarEntrada(@RequestParam Long carroId, @RequestParam Long vagaId) {
        return ResponseEntity.ok(service.registrarEntrada(carroId, vagaId));
    }

    @PostMapping("/estacionamento/saida")
    public ResponseEntity<Estadia> registrarSaida(@RequestParam Long carroId) {
        return ResponseEntity.ok(service.registrarSaida(carroId));
    }
}