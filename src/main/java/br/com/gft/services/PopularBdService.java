package br.com.gft.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.entites.Ingrediente;
import br.com.gft.entites.UnidadeMedida;

@Service
public class PopularBdService {

	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@Autowired
	private IngredienteService IngredienteService;

	@Autowired
	private ReceitaService receitaService;

	private boolean Bdpopulado;

	public void popular() throws Exception {

		if (Bdpopulado == true) {
			throw new Exception();
		} else {

			List<UnidadeMedida> listaUnidadeMedidas = new ArrayList<>();
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Xícara"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Colher de sopa"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Colher de chá"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Litro"));

			unidadeMedidaService.saveAll(listaUnidadeMedidas);

			List<Ingrediente> listaIngredientes = new ArrayList<>();

			Bdpopulado = true;

		}

	}

}
