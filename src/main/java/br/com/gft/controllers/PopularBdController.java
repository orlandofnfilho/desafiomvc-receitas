package br.com.gft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.services.PopularBdService;

@Controller
@RequestMapping("/popular")
public class PopularBdController {

	@Autowired
	private PopularBdService popularBdService;

	@GetMapping
	public String popular(RedirectAttributes att) {

		try {
			popularBdService.popular();
			att.addFlashAttribute("success", "Banco de dados populado com sucesso!");
			return "redirect:/index";
		} catch (Exception e) {
			att.addFlashAttribute("fail", "Não foi possível popular o banco de dados, pois já foi populado.");
			return "redirect:/index";
		}

	}

}
