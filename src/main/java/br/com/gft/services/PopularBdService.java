package br.com.gft.services;

import java.util.ArrayList;
import java.util.List;

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

	private boolean Bdpopulado;

	public void popular() throws Exception {

		if (Bdpopulado == true) {
			throw new Exception();
		} else {

			// Insert Undiades de Medidas

			List<UnidadeMedida> listaUnidadeMedidas = new ArrayList<>();
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Xícara"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Colher de sopa"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Colher de chá"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Copo"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Gramas"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Litro"));

			List<UnidadeMedida> listaSalva = unidadeMedidaService.findAll(null);

			if (listaSalva.isEmpty()) {
				unidadeMedidaService.saveAll(listaUnidadeMedidas);
			} else {
				throw new Exception();
			}

			// Insert Ingredientes

			List<Ingrediente> listaIngredientes = new ArrayList<>();

			listaIngredientes.add(new Ingrediente(null, "Açúcar"));
			listaIngredientes.add(new Ingrediente(null, "Achocolatado"));
			listaIngredientes.add(new Ingrediente(null, "Sal"));
			listaIngredientes.add(new Ingrediente(null, "Farinha de trigo"));
			listaIngredientes.add(new Ingrediente(null, "Água"));
			listaIngredientes.add(new Ingrediente(null, "Fermento"));

			List<Ingrediente> listaSalva2 = IngredienteService.findAll(null);

			if (listaSalva2.isEmpty()) {
				IngredienteService.saveAll(listaIngredientes);
			} else {
				throw new Exception();
			}

			Bdpopulado = true;

		}

	}

}
