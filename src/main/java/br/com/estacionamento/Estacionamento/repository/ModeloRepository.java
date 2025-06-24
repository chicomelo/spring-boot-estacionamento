package br.com.estacionamento.Estacionamento.repository;

import br.com.estacionamento.Estacionamento.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeloRepository extends JpaRepository<Modelo, Long> {}