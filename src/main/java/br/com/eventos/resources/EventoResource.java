package br.com.eventos.resources;

import java.util.List;
import java.util.Optional;

import javax.persistence.metamodel.StaticMetamodel;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import br.com.eventos.models.Evento;
import br.com.eventos.repository.EventoRepository;

@RestController
@RequestMapping("/evento")
public class EventoResource {
	
	@Autowired
	EventoRepository eventoRepository;
	
	@GetMapping(produces = "application/json")
	public List<Evento> buscarEventos(){
		return eventoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Evento cadastrarEvento(@Valid @RequestBody Evento evento) {
		return eventoRepository.save(evento);
	}
	
	@PutMapping("/{codigo}")
	public Evento alterarEvento(@Valid @PathVariable("codigo") Long codigo, @RequestBody Evento evento) {
		Evento eventoExistente = eventoRepository.findById(codigo).get();
			eventoExistente.setNome(evento.getNome());
			eventoExistente.setLocal(evento.getLocal());
			eventoExistente.setData(evento.getLocal());
			eventoExistente.setHorario(evento.getHorario());
			return eventoRepository.save(eventoExistente);
	}
	
	@DeleteMapping("/{codigo}")
	public Evento excluirEvento(@PathVariable("codigo") Long codigo) {
		Evento eventoExcluir = eventoRepository.findById(codigo).get();
		eventoRepository.delete(eventoExcluir);
		return eventoExcluir;
	}
	
}
