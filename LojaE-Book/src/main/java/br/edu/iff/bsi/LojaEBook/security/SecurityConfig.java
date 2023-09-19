package br.edu.iff.bsi.LojaEBook.security;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import br.edu.iff.bsi.LojaEBook.service.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/","/cadastro/**").permitAll()
				.requestMatchers("/cargo/**", "/h2-console/**", "/swagger-ui/**")
				.hasRole("FuncionarioNv1")
				.requestMatchers("/funcionario/**")
				.hasAnyRole("FuncionarioNv2","FuncionarioNv1")
				.requestMatchers("/cliente/**")
				.hasAnyRole("FuncionarioNv3","FuncionarioNv2","FuncionarioNv1")
				.requestMatchers("/compra/**")
				.hasAnyRole("FuncionarioNv4","FuncionarioNv3","FuncionarioNv2","FuncionarioNv1")
				.requestMatchers("/CRUDs", "/e-book/**", "/colecao-e-book/**")
				.hasAnyRole("FuncionarioNv5","FuncionarioNv4","FuncionarioNv3","FuncionarioNv2","FuncionarioNv1")
				.requestMatchers("/carrinho/**", "/editarPerfil/**").hasRole("Cliente")
				.requestMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
				.anyRequest().authenticated())
				.formLogin((form) -> form.permitAll())
				.logout((logout) -> logout.logoutSuccessUrl("/").permitAll());
		http.csrf().disable();

		http.headers().frameOptions().disable();
		
		return http.build();
	}

	/*
	 * @Bean public UserDetailsService userDetailsService() { UserDetails user =
	 * User.withDefaultPasswordEncoder().username("user").password("p").roles(
	 * "USER") .build(); UserDetails adm =
	 * User.withDefaultPasswordEncoder().username("adm").password("p").roles("ADM").
	 * build();
	 * 
	 * return new InMemoryUserDetailsManager(user, adm); }
	 */

	


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http,PasswordEncoder passwordEncoder, UsuarioDetailsService userDetailsService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userDetailsService).passwordEncoder(passwordEncoder).and().build();
	}

	@Bean
    public SessionAuthenticationStrategy sessionAuthStrategy() {
        return new RegisterSessionAuthenticationStrategy(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public ServletListenerRegistrationBean<?> servletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<>( new HttpSessionEventPublisher() );
    }

}
