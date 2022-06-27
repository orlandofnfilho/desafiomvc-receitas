package br.com.gft.services;

import java.util.ArrayList;
import java.util.Arrays;
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

			List<UnidadeMedida> listaUnidadeMedidas = new ArrayList<>();
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Xícara"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Colher de sopa"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Colher de chá"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Copo"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Gramas"));
			listaUnidadeMedidas.add(new UnidadeMedida(null, "Litro"));

			unidadeMedidaService.saveAll(listaUnidadeMedidas);

			List<Ingrediente> listaIngredientes = new ArrayList<>();
			listaIngredientes.add(new Ingrediente(null, "Achocolatado",
					Arrays.asList(listaUnidadeMedidas.get(0), listaUnidadeMedidas.get(1), listaUnidadeMedidas.get(4))));
			listaIngredientes.add(new Ingrediente(null, "Açúcar",
					Arrays.asList(listaUnidadeMedidas.get(1), listaUnidadeMedidas.get(2), listaUnidadeMedidas.get(4))));
			listaIngredientes.add(new Ingrediente(null, "Leite",
					Arrays.asList(listaUnidadeMedidas.get(0), listaUnidadeMedidas.get(1))));
			listaIngredientes.add(new Ingrediente(null, "Água",
					Arrays.asList(listaUnidadeMedidas.get(0), listaUnidadeMedidas.get(3), listaUnidadeMedidas.get(5))));
			listaIngredientes.add(new Ingrediente(null, "Farinha de Trigo",
					Arrays.asList(listaUnidadeMedidas.get(0), listaUnidadeMedidas.get(1), listaUnidadeMedidas.get(4))));
			listaIngredientes.add(new Ingrediente(null, "Fermento",
					Arrays.asList(listaUnidadeMedidas.get(0), listaUnidadeMedidas.get(1), listaUnidadeMedidas.get(4))));

			IngredienteService.saveAll(listaIngredientes);

			Bdpopulado = true;

		}

	}

}
