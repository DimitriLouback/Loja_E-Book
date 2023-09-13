package br.edu.iff.bsi.LojaEBook.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import br.edu.iff.bsi.LojaEBook.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/").permitAll()
				.requestMatchers("/CRUDs","/cliente/**","/funcionario/**","/cargo/**","/e-book/**","/colecao-e-book/**","/compra/**","/h2-console/**","/swagger-ui/**").hasRole("ADM")
				.requestMatchers("/webjars/**","/css/**","/image/**","/js/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		UserDetails adm =
				 User.withDefaultPasswordEncoder()
					.username("adm")
					.password("root123")
					.roles("ADM")
					.build();

		return new InMemoryUserDetailsManager(user, adm);
	}
	
	@Autowired
	private UsuarioDetailsService usuarioDetailsService;
	
	//		@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
