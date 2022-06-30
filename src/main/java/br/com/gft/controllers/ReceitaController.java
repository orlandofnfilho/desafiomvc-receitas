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

import br.com.gft.entites.Receita;
import br.com.gft.services.IngredienteService;
import br.com.gft.services.ItemService;
import br.com.gft.services.ReceitaService;
import br.com.gft.services.UnidadeMedidaService;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@Autowired
	private ItemService itemService;

	@GetMapping("/new")
	public ModelAndView newReceita() {
		ModelAndView mv = new ModelAndView("receitas/form");
		mv.addObject("receita", new Receita());
		mv.addObject("listaIngrediente", ingredienteService.findAll(null));
		mv.addObject("listaUndiadeMedida", unidadeMedidaService.findAll(null));
		return mv;

	}

	@PostMapping("/new")
	public ModelAndView saveReceita(@Valid Receita receita, BindingResult bindingResult) {

		ModelAndView mv = new ModelAndView("receitas/form");

		boolean novo = true;

		if (receita.getReceitaId() != null) {
			novo = false;
		}

		if (bindingResult.hasErrors()) {
			mv.addObject("receita", receita);
			return mv;
		}

		Receita receitaSaved = receitaService.insert(receita);

		if (novo) {
			mv.addObject("receita", new Receita());
		} else {
			mv.addObject("receita", receitaSaved);
		}

		mv.addObject("listaItem", receita.getItens());
		mv.addObject("listaIngrediente", ingredienteService.findAll(null));
		mv.addObject("listaUndiadeMedida", unidadeMedidaService.findAll(null));
		mv.addObject("message", "Receita salva com sucesso");

		return mv;

	}

	@GetMapping("/edit")
	public ModelAndView editReceita(@RequestParam Long id) {

		ModelAndView mv = new ModelAndView("receitas/edit");
		Receita receita;

		try {
			receita = receitaService.findById(id);
		} catch (Exception e) {
			receita = new Receita();
			mv.addObject("message", e.getMessage());
		}

		mv.addObject("receita", receita);
		mv.addObject("listaItem", receita.getItens());
		mv.addObject("listaIngrediente", ingredienteService.findAll(null));
		mv.addObject("listaUndiadeMedida", unidadeMedidaService.findAll(null));

		return mv;
	}

	@GetMapping
	public ModelAndView list(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String nomeIngrediente) {
		ModelAndView mv = new ModelAndView("receitas/list");
		mv.addObject("list", receitaService.findAll(nome, nomeIngrediente));
		mv.addObject("listaItem", itemService.findAll());
		mv.addObject("listaIngrediente", ingredienteService.findAll(null));
		mv.addObject("listaUndiadeMedida", unidadeMedidaService.findAll(null));
		mv.addObject("nome", nome);
		mv.addObject("nomeIngrediente", nomeIngrediente);
		return mv;

	}

	@GetMapping("/delete")
	public ModelAndView delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		ModelAndView mv = new ModelAndView("redirect:/receitas");

		try {
			receitaService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Receita excluida com sucesso!");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Erro ao excluir receita.");
		}

		return mv;

	}

}
