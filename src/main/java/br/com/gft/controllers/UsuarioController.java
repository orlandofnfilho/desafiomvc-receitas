package br.com.gft.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gft.entites.Usuario;
import br.com.gft.repositories.UsuarioRepository;
import br.com.gft.services.EmailService;

@Controller
@RequestMapping
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	@GetMapping("/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("usuario", new Usuario(null, null));

		return "/cadastrar";

	}

	@PostMapping("/cadastrar")
	public String salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "/cadastrar";
		}
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		repository.save(usuario);
		emailService.confirmReg(usuario);
		model.addAttribute("message", "Usuário cadastrado");

		return "/login";
	}

}
