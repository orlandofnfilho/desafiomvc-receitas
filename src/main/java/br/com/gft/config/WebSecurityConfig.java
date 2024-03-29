package br.com.gft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.gft.entites.enums.Role;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/image/**", "/cadastrar", "/login", "/error").permitAll()
				.antMatchers("/index").authenticated()
				.antMatchers("/unidades/**").hasAuthority(Role.ADMIN.getNome())
				.antMatchers("/ingredientes/**").hasAuthority(Role.ADMIN.getNome())
				.antMatchers("/receitas/edit").hasAuthority(Role.ADMIN.getNome())
				.antMatchers("/itens/edit").hasAuthority(Role.ADMIN.getNome())
				.antMatchers("/itens").hasAuthority(Role.ADMIN.getNome())
				.antMatchers("/receitas").hasAnyAuthority(Role.ADMIN.getNome(), Role.USUARIO.getNome())
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login")
				.defaultSuccessUrl("/index", true).failureUrl("/login-error").permitAll()
				.and()
				.logout()
				.logoutSuccessUrl("/login?logout")
				.and()
				.rememberMe()
				.userDetailsService(userDetailsService);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

}
