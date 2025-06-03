package br.com.estacionamento.Estacionamento.repository;

import br.com.estacionamento.Estacionamento.model.Estadia;
import br.com.estacionamento.Estacionamento.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByOcupadaFalse();
    List<Vaga> findByOcupadaTrue();
}

