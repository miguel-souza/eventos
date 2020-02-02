package br.com.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eventos.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

}
