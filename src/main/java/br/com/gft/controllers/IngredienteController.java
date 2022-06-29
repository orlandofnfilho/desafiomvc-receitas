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

import br.com.gft.entites.Ingrediente;
import br.com.gft.services.IngredienteService;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;

	@GetMapping("/new")
	public ModelAndView newIngrediente() {
		ModelAndView mv = new ModelAndView("ingredientes/form");
		mv.addObject("ingrediente", new Ingrediente());

		return mv;

	}

	@PostMapping("/new")
	public ModelAndView saveIngrediente(@Valid Ingrediente ingrediente, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("ingredientes/form");

		boolean novo = true;

		if (ingrediente.getId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("ingrediente", ingrediente);
			return mv;
		}

		Ingrediente ingredienteSaved = ingredienteService.insert(ingrediente);

		if (novo) {
			mv.addObject("ingrediente", new Ingrediente());
		} else {
			mv.addObject("ingrediente", ingredienteSaved);
		}

		mv.addObject("message", "Ingrediente salvo com sucesso");

		return mv;

	}

	@GetMapping("/edit")
	public ModelAndView editIngrediente(@RequestParam Long id) {

		ModelAndView mv = new ModelAndView("ingredientes/form");
		Ingrediente ingrediente;

		try {
			ingrediente = ingredienteService.findById(id);
		} catch (Exception e) {
			ingrediente = new Ingrediente();
			mv.addObject("message", e.getMessage());
		}

		mv.addObject("ingrediente", ingrediente);

		return mv;
	}

	@GetMapping
	public ModelAndView list(String nome) {
		ModelAndView mv = new ModelAndView("ingredientes/list");
		mv.addObject("list", ingredienteService.findAll(nome));
		mv.addObject("nome", nome);
		return mv;

	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/ingredientes");

		try {
			ingredienteService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Ingrediente excluido com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir ingrediente.");
		}

		return mv;

	}

}
