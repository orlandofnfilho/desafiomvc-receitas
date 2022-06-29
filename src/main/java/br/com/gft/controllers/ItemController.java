package br.com.gft.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.gft.entites.Item;
import br.com.gft.services.ItemService;

@Controller
@RequestMapping("/itens")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/new")
	public ModelAndView newItem() {
		ModelAndView mv = new ModelAndView("itens/form");

		mv.addObject("item", new Item());

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
			return mv;
		}

		Item itemSaved = itemService.insert(item);

		if (novo) {
			mv.addObject("item", new Item());
		} else {
			mv.addObject("item", itemSaved);
		}

		mv.addObject("message", "Receita salva com sucesso");

		return mv;

	}
}
