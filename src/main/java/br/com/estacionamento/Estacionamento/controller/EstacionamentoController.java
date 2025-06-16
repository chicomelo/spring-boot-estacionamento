package br.com.estacionamento.Estacionamento.controller;

import br.com.estacionamento.Estacionamento.model.*;
import br.com.estacionamento.Estacionamento.service.EstacionamentoService;
import br.com.estacionamento.Estacionamento.model.vo.CarroVO;
import br.com.estacionamento.Estacionamento.model.vo.EstadiaVO;
import br.com.estacionamento.Estacionamento.model.vo.VagaVO;
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
    public ResponseEntity<CarroVO> criarCarro(@RequestBody Carro carro) {
        return ResponseEntity.ok(service.criarCarro(carro));
    }

    @GetMapping("/carros")
    public ResponseEntity<List<CarroVO>> listarCarros() {
        return ResponseEntity.ok(service.listarCarros());
    }

    @GetMapping("/carros/{id}")
    public ResponseEntity<CarroVO> buscarCarro(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarCarroPorId(id));
    }

    @PutMapping("/carros/{id}")
    public ResponseEntity<CarroVO> atualizarCarro(@PathVariable Long id, @RequestBody Carro carro) {
        return ResponseEntity.ok(service.atualizarCarro(id, carro));
    }

    @DeleteMapping("/carros/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id) {
        service.deletarCarro(id);
        return ResponseEntity.noContent().build();
    }

    // ---- CRUD Completo Vagas ----
    @PostMapping("/vagas")
    public ResponseEntity<VagaVO> criarVaga(@RequestBody Vaga vaga) {
        return ResponseEntity.ok(service.criarVaga(vaga));
    }

    @GetMapping("/vagas")
    public ResponseEntity<List<VagaVO>> listarVagas() {
        return ResponseEntity.ok(service.listarVagas());
    }

    @GetMapping("/vagas/ativas")
    public ResponseEntity<List<VagaVO>> listarVagasAtivas() {
        return ResponseEntity.ok(service.listarVagasAtivas());
    }

    @GetMapping("/vagas/ocupadas")
    public ResponseEntity<List<VagaVO>> listarVagasOcupadas() {
        return ResponseEntity.ok(service.listarVagasOcupadas());
    }

    @GetMapping("/vagas/{id}")
    public ResponseEntity<VagaVO> buscarVagaPorId(@PathVariable Long id) {
        Vaga vaga = service.buscarVagaPorId(id);
        return ResponseEntity.ok(new VagaVO(vaga.getNumero(), vaga.isOcupada()));
    }

    @PutMapping("/vagas/{id}")
    public ResponseEntity<VagaVO> atualizarVaga(@PathVariable Long id, @RequestBody Vaga vaga) {
        return ResponseEntity.ok(service.atualizarVaga(id, vaga));
    }

    @DeleteMapping("/vagas/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        service.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }

    // ---- CRUD Estadias + Controle Estacionamento ----
    @GetMapping("/estadias")
    public ResponseEntity<List<EstadiaVO>> listarEstadias() {
        return ResponseEntity.ok(service.listarEstadias());
    }

    @GetMapping("/estacionamento/ativos")
    public ResponseEntity<List<EstadiaVO>> listarEstadiasAtivas() {
        return ResponseEntity.ok(service.listarEstadiasAtivas());
    }

    @PostMapping("/estacionamento/entrada")
    public ResponseEntity<EstadiaVO> registrarEntrada(@RequestParam Long carroId, @RequestParam Long vagaId) {
        return ResponseEntity.ok(service.registrarEntrada(carroId, vagaId));
    }

    @PostMapping("/estacionamento/saida")
    public ResponseEntity<EstadiaVO> registrarSaida(@RequestParam Long carroId) {
        return ResponseEntity.ok(service.registrarSaida(carroId));
    }
}
