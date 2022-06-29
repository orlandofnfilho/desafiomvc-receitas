package br.com.gft.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.gft.entites.Usuario;
import br.com.gft.entites.enums.Role;
import br.com.gft.repositories.UsuarioRepository;

@Component
@Transactional
public class CreateUsers implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario("admin@gft.com", passwordEncoder.encode("Gft@1234"));
		u1.setRole(Role.ADMIN.getNome());

		Usuario u2 = new Usuario("usuario@gft.com", passwordEncoder.encode("Gft@1234"));

		if(usuarioRepo.findAll().isEmpty()) {
			usuarioRepo.save(u1);
			usuarioRepo.save(u2);
		}
		
		

	}

}
