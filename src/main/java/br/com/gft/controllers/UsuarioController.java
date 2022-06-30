package br.com.gft.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gft.entites.Usuario;
import br.com.gft.repositories.UsuarioRepository;

@Controller
@RequestMapping
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("usuario", new Usuario(null, null));

		return "/cadastrar";

	}

	@RequestMapping("/cadastrar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "/cadastrar";
		}
		passwordEncoder.encode(usuario.getPassword());
		repository.save(usuario);
		model.addAttribute("message", "Usuário cadastrado");

		return "/login";
	}

}
