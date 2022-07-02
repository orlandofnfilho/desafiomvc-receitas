package br.com.gft.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gft.entites.Ingrediente;
import br.com.gft.entites.Item;
import br.com.gft.entites.Receita;
import br.com.gft.entites.UnidadeMedida;

@Service
public class PopularBdService {

	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@Autowired
	private IngredienteService IngredienteService;

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private ItemService itemService;

	private boolean Bdpopulado;

	public void popular() throws Exception {

		if (Bdpopulado == true) {
			throw new Exception();
		} else {

			// Insert Undiades de Medidas

			List<UnidadeMedida> listaUnidadeMedidas = new ArrayList<>();

			UnidadeMedida un1 = new UnidadeMedida(null, "Fatias");
			UnidadeMedida un2 = new UnidadeMedida(null, "Lata");
			UnidadeMedida un3 = new UnidadeMedida(null, "Caixa");
			UnidadeMedida un4 = new UnidadeMedida(null, "À Gosto");
			UnidadeMedida un5 = new UnidadeMedida(null, "Colher de Sopa");
			UnidadeMedida un6 = new UnidadeMedida(null, "Unidade");

			listaUnidadeMedidas.add(un1);
			listaUnidadeMedidas.add(un2);
			listaUnidadeMedidas.add(un3);
			listaUnidadeMedidas.add(un4);
			listaUnidadeMedidas.add(un5);
			listaUnidadeMedidas.add(un6);

			List<UnidadeMedida> listaSalva = unidadeMedidaService.findAll(null);

			if (listaSalva.isEmpty()) {
				unidadeMedidaService.saveAll(listaUnidadeMedidas);
			} else {
				throw new Exception();
			}

			// Insert Ingredientes

			List<Ingrediente> listaIngredientes = new ArrayList<>();

			Ingrediente in1 = new Ingrediente(null, "Margarina", null);
			Ingrediente in2 = new Ingrediente(null, "Pão de forma sem casca", null);
			Ingrediente in3 = new Ingrediente(null, "Molho de tomate", null);
			Ingrediente in4 = new Ingrediente(null, "Presunto", null);
			Ingrediente in5 = new Ingrediente(null, "Requeijão", null);
			Ingrediente in6 = new Ingrediente(null, "Queijo Mussarela", null);
			Ingrediente in7 = new Ingrediente(null, "Creme de leite", null);
			Ingrediente in8 = new Ingrediente(null, "Tomate grande cortado", null);
			Ingrediente in9 = new Ingrediente(null, "Orégano", null);

			listaIngredientes.add(in1);
			listaIngredientes.add(in2);
			listaIngredientes.add(in3);
			listaIngredientes.add(in4);
			listaIngredientes.add(in5);
			listaIngredientes.add(in6);
			listaIngredientes.add(in7);
			listaIngredientes.add(in8);
			listaIngredientes.add(in9);

			List<Ingrediente> listaSalva2 = IngredienteService.findAll(null);

			if (listaSalva2.isEmpty()) {
				IngredienteService.saveAll(listaIngredientes);
			} else {
				throw new Exception();
			}
			// Insert Receitas

			List<Receita> listaReceitas = new ArrayList<>();

			Receita r1 = new Receita(null, "Misto quente de forno", 20,
					"https://img.itdg.com.br/tdg/images/recipes/000/138/603/100602/100602_original.jpg?mode=crop&width=710&height=400",
					"Unte um refratário com margarina. Forre o fundo com 6 fatias de pão de forma. Colocar metade do molho de tomate temperado, presunto, camada de requeijão, metade da mussarela, restante do pão de forma, molho de tomate, creme de leite, mussarela, tomate em rodelas, orégano. Leve o refratário ao forno até a mussarela derreter (fiz no micro-ondas)",
					null);

			listaReceitas.add(r1);

			List<Receita> listaSalva3 = receitaService.findAll(null, null);

			if (listaSalva3.isEmpty()) {
				receitaService.saveAll(listaReceitas);
			} else {
				throw new Exception();
			}

			Item item1 = new Item(null, r1, in1, new BigDecimal("1"), un5);
			Item item2 = new Item(null, r1, in2, new BigDecimal("12"), un1);
			Item item3 = new Item(null, r1, in3, new BigDecimal("0.5"), un2);
			Item item4 = new Item(null, r1, in4, new BigDecimal("6"), un1);
			Item item5 = new Item(null, r1, in5, new BigDecimal("4"), un5);
			Item item6 = new Item(null, r1, in6, new BigDecimal("12"), un1);
			Item item7 = new Item(null, r1, in7, new BigDecimal("0.5"), un3);
			Item item8 = new Item(null, r1, in8, new BigDecimal("1"), un6);
			Item item9 = new Item(null, r1, in9, new BigDecimal("0"), un4);

			List<Item> listaItem = new ArrayList<>();
			listaItem.addAll(Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9));

			List<Item> listaSalva4 = itemService.findAll();

			if (listaSalva4.isEmpty()) {
				itemService.saveAll(listaItem);
			} else {
				throw new Exception();
			}

			Bdpopulado = true;

		}

	}

}
