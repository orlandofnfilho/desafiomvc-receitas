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
	private ItemService itemService;

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("itens/form");

		Item item;

		if (id == null) {
			item = new Item();
		} else {
			try {
				item = itemService.findById(id);
			} catch (Exception e) {
				item = new Item();
				mv.addObject("message", e.getMessage());
			}
		}

		mv.addObject("item", item);
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.findAll(null));

		return mv;
	}

	@PostMapping("/edit")
	public ModelAndView edit(@Valid Item item, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("itens/form");
		boolean newItem = true;

		if (item.getId() != null) {
			newItem = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("ingrediente", item);
			return mv;
		}

		Item itemSaved = itemService.insert(item);

		if (newItem) {
			mv.addObject("item", new Item());
		} else {
			mv.addObject("item", itemSaved);
		}

		mv.addObject("message", "Item salvo com sucesso!");
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.findAll(null));
		return mv;

	}

	@GetMapping
	public ModelAndView list(String nome) {
		ModelAndView mv = new ModelAndView("itens/list");
		mv.addObject("list", ingredienteService.findAll(nome));
		mv.addObject("nome", nome);
		return mv;

	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/itens");

		try {
			ingredienteService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Ingrediente excluido com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir ingrediente.");
		}

		return mv;

	}

}
