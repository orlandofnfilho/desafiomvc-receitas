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
			UnidadeMedida un7 = new UnidadeMedida(null, "Gramas");

			listaUnidadeMedidas.add(un1);
			listaUnidadeMedidas.add(un2);
			listaUnidadeMedidas.add(un3);
			listaUnidadeMedidas.add(un4);
			listaUnidadeMedidas.add(un5);
			listaUnidadeMedidas.add(un6);
			listaUnidadeMedidas.add(un7);

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
			Ingrediente in10 = new Ingrediente(null, "Açúcar", null);
			Ingrediente in11 = new Ingrediente(null, "Chocolate em pó", null);
			Ingrediente in12 = new Ingrediente(null, "Ovo inteiro", null);
			Ingrediente in13 = new Ingrediente(null, "Coco ralado", null);
			Ingrediente in14 = new Ingrediente(null, "Fermento em pó", null);

			listaIngredientes
					.addAll(Arrays.asList(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14));

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

			Receita r2 = new Receita(null, "Bolo de chocolate no liquidificador", 60,
					"https://img.itdg.com.br/tdg/images/recipes/000/041/549/326126/326126_original.jpg?mode=crop&width=710&height=400",
					"Coloque no liquidificador todos os ingredientes e bata por 3 minutos. Unte a forma com margarina e polvilhe com chocolate em pó. Coloque a massa na forma. Asse em forno a 180°, pré-aquecido, por 40 minutos, ou até que furando com um palito, ele saia seco.",
					null);

			listaReceitas.add(r1);
			listaReceitas.add(r2);

			List<Receita> listaSalva3 = receitaService.findAll(null, null);

			if (listaSalva3.isEmpty()) {
				receitaService.saveAll(listaReceitas);
			} else {
				throw new Exception();

			}

			// Insert Itens

			Item item1 = new Item(null, r1, in1, new BigDecimal("1"), un5);
			Item item2 = new Item(null, r1, in2, new BigDecimal("12"), un1);
			Item item3 = new Item(null, r1, in3, new BigDecimal("0.5"), un2);
			Item item4 = new Item(null, r1, in4, new BigDecimal("6"), un1);
			Item item5 = new Item(null, r1, in5, new BigDecimal("4"), un5);
			Item item6 = new Item(null, r1, in6, new BigDecimal("12"), un1);
			Item item7 = new Item(null, r1, in7, new BigDecimal("0.5"), un3);
			Item item8 = new Item(null, r1, in8, new BigDecimal("1"), un6);
			Item item9 = new Item(null, r1, in9, new BigDecimal("0"), un4);

			Item item10 = new Item(null, r2, in10, new BigDecimal("8"), un5);
			Item item11 = new Item(null, r2, in11, new BigDecimal("8"), un5);
			Item item12 = new Item(null, r2, in12, new BigDecimal("6"), un6);
			Item item13 = new Item(null, r2, in13, new BigDecimal("100"), un7);
			Item item14 = new Item(null, r2, in14, new BigDecimal("1"), un5);

			List<Item> listaItem = new ArrayList<>();
			listaItem.addAll(Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10,
					item11, item12, item13, item14));

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
