package br.com.gft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
public class InMemorySecurityConfig {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.inMemoryAuthentication().withUser("admin@gft.com").password("{noop}Gft@1234")
				.roles("ADMIN", "USUARIO").and().withUser("usuario@gft.com").password("{noop}Gft@1234")
				.roles("USUARIO");
	}

}
