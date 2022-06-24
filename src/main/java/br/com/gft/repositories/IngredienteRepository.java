package br.com.gft.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.entites.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {

	List<Ingrediente> findByNomeContainingIgnoreCase(String nome);

	Optional<Ingrediente> findByNomeIgnoreCase(String nome);

}
