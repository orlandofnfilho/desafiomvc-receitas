package br.com.gft.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.entites.UnidadeMedida;

@Repository
public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long> {

	List<UnidadeMedida> findByNomeContainingIgnoreCase(String nome);

	Optional<UnidadeMedida> findByNomeIgnoreCase(String nome);
}
