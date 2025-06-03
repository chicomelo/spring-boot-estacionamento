package br.com.estacionamento.Estacionamento.repository;

import br.com.estacionamento.Estacionamento.model.Estadia;
import br.com.estacionamento.Estacionamento.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstadiaRepository extends JpaRepository<Estadia, Long> {
    Optional<Estadia> findByCarroAndSaidaIsNull(Carro carro);
    boolean existsByCarroIdAndSaidaIsNull(Long carroId);
    List<Estadia> findBySaidaIsNull();
}