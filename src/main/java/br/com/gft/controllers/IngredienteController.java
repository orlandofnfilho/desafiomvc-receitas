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
import br.com.gft.services.UnidadeMedidaService;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("ingredientes/form");

		Ingrediente ingrediente;

		if (id == null) {
			ingrediente = new Ingrediente();
		} else {
			try {
				ingrediente = ingredienteService.findById(id);
			} catch (Exception e) {
				ingrediente = new Ingrediente();
				mv.addObject("message", e.getMessage());
			}
		}

		mv.addObject("ingrediente", ingrediente);
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.findAll(null));

		return mv;
	}

	@PostMapping("/edit")
	public ModelAndView edit(@Valid Ingrediente ingrediente, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("ingredientes/form");
		boolean newIngrediente = true;

		if (ingrediente.getId() != null) {
			newIngrediente = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("ingrediente", ingrediente);
			return mv;
		}

		Ingrediente ingredienteSaved = ingredienteService.insert(ingrediente);

		if (newIngrediente) {
			mv.addObject("ingrediente", new Ingrediente());
		} else {
			mv.addObject("ingrediente", ingredienteSaved);
		}

		mv.addObject("message", "Ingrediente salvo com sucesso!");
		mv.addObject("listaUnidadeMedida", unidadeMedidaService.findAll(null));
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
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir ingrediente!" + e.getMessage());
		}

		return mv;

	}

}
