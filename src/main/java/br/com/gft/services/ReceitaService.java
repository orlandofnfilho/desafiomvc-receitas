package br.com.gft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.entites.Receita;
import br.com.gft.repositories.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	public Receita insert(Receita receita) {
		return receitaRepository.save(receita);
	}

	public Receita findById(Long id) throws Exception {
		Optional<Receita> receita = receitaRepository.findById(id);
		if (receita.isEmpty()) {
			throw new Exception("Receita não econtrada.");
		}
		return receita.get();

	}

	public List<Receita> findAll(String nome, String nomeIngrediente) {
		if (nome != null)
			return listByName(nome);
		if (nomeIngrediente != null)
			return listByIngrediente(nomeIngrediente);

		return receitaRepository.findAll();
	}

	public List<Receita> listByName(String nome) {
		return receitaRepository.findByNomeContainingIgnoreCase(nome);
	}

	public List<Receita> listByIngrediente(String nomeIngrediente) {
		return receitaRepository.findByItens_IngredienteNome(nomeIngrediente);
	}

	public void delete(Long id) {
		receitaRepository.deleteById(id);
	}

	public void saveAll(List<Receita> list) {
		receitaRepository.saveAll(list);
	}
}
