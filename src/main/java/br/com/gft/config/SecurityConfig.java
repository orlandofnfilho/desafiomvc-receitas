package br.com.gft.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/index").authenticated()
				.antMatchers("/unidades/**").hasAnyRole("ADMIN")
				.antMatchers("/ingredientes/**").hasAnyRole("ADMIN")
				.antMatchers("/receitas").hasAnyRole("USUARIO")
				.antMatchers("/receitas/edit").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").defaultSuccessUrl("/index", true)
				.failureUrl("/login-error").permitAll()	
				.and().logout().logoutSuccessUrl("/login?logout");
	}
}
