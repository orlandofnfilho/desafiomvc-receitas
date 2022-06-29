package br.com.gft.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.entites.Item;
import br.com.gft.services.IngredienteService;
import br.com.gft.services.ItemService;
import br.com.gft.services.ReceitaService;
import br.com.gft.services.UnidadeMedidaService;

@Controller
@RequestMapping("/itens")
public class ItemController {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@Autowired
	private ItemService itemService;

	@GetMapping("/new")
	public ModelAndView newItem() {
		ModelAndView mv = new ModelAndView("itens/form");
		mv.addObject("item", new Item());
		mv.addObject("listaReceita", receitaService.findAll(null));
		mv.addObject("listaIngrediente", ingredienteService.findAll(null));
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.findAll(null));
		return mv;

	}

	@PostMapping("/new")
	public ModelAndView saveItem(@Valid Item item, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("itens/form");

		boolean novo = true;

		if (item.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("item", item);
			mv.addObject("listaReceita", receitaService.findAll(null));
			mv.addObject("listaIngrediente", ingredienteService.findAll(null));
			mv.addObject("listaUnidadeMedida", unidadeMedidaService.findAll(null));
			return mv;
		}

		Item itemSaved = itemService.insert(item);

		if (novo) {
			mv.addObject("item", new Item());
			mv.addObject("listaReceita", receitaService.findAll(null));
			mv.addObject("listaIngrediente", ingredienteService.findAll(null));
			mv.addObject("listaUnidadeMedida", unidadeMedidaService.findAll(null));
		} else {
			mv.addObject("item", itemSaved);
			mv.addObject("listaReceita", receitaService.findAll(null));
			mv.addObject("listaIngrediente", ingredienteService.findAll(null));
			mv.addObject("listaUnidadeMedida", unidadeMedidaService.findAll(null));
		}
		mv.addObject("message", "Receita salva com sucesso");

		return mv;

	}



	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/receitas");

		try {
			itemService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Item excluido com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir item.");
		}

		return mv;

	}
}
