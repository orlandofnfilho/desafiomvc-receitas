package br.com.gft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.gft.entites.Usuario;
import br.com.gft.repositories.UsuarioRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private UsuarioRepository repository;

	public void confirmReg(Usuario usuario) {

		Usuario usuarioEncontrado = repository.findByUsername(usuario.getUsername());

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("jofhgft@outlook.com");
		msg.setTo(usuarioEncontrado.getUsername());
		msg.setText("Você se cadastrou no Receitas GFT :)");
		msg.setSubject("Confirmação de cadastro");
		sender.send(msg);

	}

}
