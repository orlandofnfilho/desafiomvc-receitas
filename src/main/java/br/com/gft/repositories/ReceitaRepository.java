package br.com.gft.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gft.entites.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

	List<Receita> findByNomeContainingIgnoreCase(String nome);

	Optional<Receita> findByNomeIgnoreCase(String nome);

	List<Receita> findByItens_IngredienteNome(String nomeIngrediente);
}
