package br.com.estacionamento.Estacionamento.repository;

import br.com.estacionamento.Estacionamento.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}