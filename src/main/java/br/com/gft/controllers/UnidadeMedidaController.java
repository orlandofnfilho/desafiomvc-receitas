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

import br.com.gft.entites.UnidadeMedida;
import br.com.gft.services.UnidadeMedidaService;

@Controller
@RequestMapping("/unidades")
public class UnidadeMedidaController {

	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@GetMapping("/edit")
	public ModelAndView edit(@RequestParam(required = false) Long id) {
		ModelAndView mv = new ModelAndView("unidades/form");

		UnidadeMedida unidadeMedida;

		if (id == null) {
			unidadeMedida = new UnidadeMedida();
		} else {
			try {
				unidadeMedida = unidadeMedidaService.findById(id);
			} catch (Exception e) {
				unidadeMedida = new UnidadeMedida();
				mv.addObject("message", e.getMessage());
			}
		}

		mv.addObject("unidadeMedida", unidadeMedida);

		return mv;
	}

	@PostMapping("/edit")
	public ModelAndView edit(@Valid UnidadeMedida unidadeMedida, BindingResult bindingResult) {
		ModelAndView mv = new ModelAndView("unidades/form");
		boolean newUnidadeMedida = true;

		if (unidadeMedida.getId() != null) {
			newUnidadeMedida = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("unidadeMedida", unidadeMedida);
			return mv;
		}

		UnidadeMedida unidadeMedidaSaved = unidadeMedidaService.insert(unidadeMedida);

		if (newUnidadeMedida) {
			mv.addObject("unidadeMedida", new UnidadeMedida());
		} else {
			mv.addObject("unidadeMedida", unidadeMedidaSaved);
		}

		mv.addObject("message", "Unidade de medida salva com sucesso!");
		return mv;

	}

	@GetMapping
	public ModelAndView list(String nome) {
		ModelAndView mv = new ModelAndView("unidades/list");
		mv.addObject("list", unidadeMedidaService.findAll(nome));
		mv.addObject("nome", nome);
		return mv;

	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/unidades");

		try {
			unidadeMedidaService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Unidade de medida excluida com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir Unidade de medida.");
		}

		return mv;

	}

}
