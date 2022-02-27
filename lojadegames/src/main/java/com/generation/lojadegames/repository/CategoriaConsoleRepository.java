package com.generation.lojadegames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.lojadegames.model.CategoriaConsole;

@Repository // Anotação que indica que esta interface é a camada Repository.
public interface CategoriaConsoleRepository extends JpaRepository<CategoriaConsole, Long> {

	public List<CategoriaConsole> findAllByConsoleContainingIgnoreCase(String console);
}
