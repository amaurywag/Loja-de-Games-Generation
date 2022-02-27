package com.generation.lojadegames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojadegames.model.CategoriaConsole;
import com.generation.lojadegames.repository.CategoriaConsoleRepository;

@RestController
@RequestMapping("/categoriaconsole")
@CrossOrigin("*") // Informa que esta classe irá aceitar requisições de qualquer origem.
public class CategoriaConsoleController {

	// injetar a classe repositório, dentro da classe controller
	@Autowired // injeção de dependências do Spring (?)
	private CategoriaConsoleRepository repository;

	@GetMapping
	public ResponseEntity<List<CategoriaConsole>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaConsole> GetById(@PathVariable long id) {
		return repository.findById(id).map(response -> ResponseEntity.ok(response))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/console/{console}")
	public ResponseEntity<List<CategoriaConsole>> GetByConsole(@PathVariable String console) {
		return ResponseEntity.ok(repository.findAllByConsoleContainingIgnoreCase(console));
	}

	@PostMapping
	public ResponseEntity<CategoriaConsole> postConsole(@Valid @RequestBody CategoriaConsole console) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(console));
	}

	@PutMapping
	public ResponseEntity<CategoriaConsole> putConsole(@Valid @RequestBody CategoriaConsole console) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(console));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
