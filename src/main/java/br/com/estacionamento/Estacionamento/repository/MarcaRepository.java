package br.com.estacionamento.Estacionamento.repository;

import br.com.estacionamento.Estacionamento.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {}