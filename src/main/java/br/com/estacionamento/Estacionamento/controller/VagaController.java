package br.com.estacionamento.Estacionamento.controller;

import br.com.estacionamento.Estacionamento.model.Vaga;
import br.com.estacionamento.Estacionamento.model.vo.VagaVO;
import br.com.estacionamento.Estacionamento.service.VagaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vagas")
@RequiredArgsConstructor
public class VagaController {

    private final VagaService vagaService;

    @PostMapping
    public ResponseEntity<VagaVO> criarVaga(@RequestBody Vaga vaga) {
        return ResponseEntity.ok(vagaService.criarVaga(vaga));
    }

    @GetMapping
    public ResponseEntity<List<VagaVO>> listarVagas() {
        return ResponseEntity.ok(vagaService.listarVagas());
    }

    @GetMapping("/ativas")
    public ResponseEntity<List<VagaVO>> listarVagasAtivas() {
        return ResponseEntity.ok(vagaService.listarVagasAtivas());
    }

    @GetMapping("/ocupadas")
    public ResponseEntity<List<VagaVO>> listarVagasOcupadas() {
        return ResponseEntity.ok(vagaService.listarVagasOcupadas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaVO> buscarVagaPorId(@PathVariable Long id) {
        Vaga vaga = vagaService.buscarVagaPorId(id);
        return ResponseEntity.ok(new VagaVO(vaga.getId(), vaga.getNumero(), vaga.isOcupada()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagaVO> atualizarVaga(@PathVariable Long id, @RequestBody Vaga vaga) {
        return ResponseEntity.ok(vagaService.atualizarVaga(id, vaga));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVaga(@PathVariable Long id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }
}
