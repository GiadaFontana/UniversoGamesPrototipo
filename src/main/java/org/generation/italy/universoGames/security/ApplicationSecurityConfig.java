package org.generation.italy.universoGames.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.generation.italy.universoGames.auth.AuthService;

@Configuration
@EnableWebSecurity
// Configurazione base di SpringSecurity, andiamo a definirne uno nostro
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter { 
	
	// Oggetto che serve per criptografare le password
	private final PasswordEncoder passwordEncoder;
	// Il servizio che ci fornisce gli utenti
	private final AuthService authService;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, AuthService authService) {
		this.passwordEncoder = passwordEncoder;
		this.authService = authService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // Andate a vedere cos'è il csrf, in poche parole, la disabilito se mi serve
		// utilizzare l'app anche come sistema rest per applicazioni "esterne"
		.authorizeRequests().antMatchers("/",  "/css/**", "/js/**", "/assets/**", "/commenti-news/**",
				"/signup", "/login", "/recensioni.html",  "/news.html", "/altro.html", 
				"/homepage.html", "/signup.html", "/fail.html", "/forbidden.html" )
				.permitAll()
				// tutti possono accedere a questi percorsi
				/* sono organizzati in questo modo, il primo è per l'"Index", dopodichè ci sono i vari percorsi come alle
				   cartelle js e css, che avranno bisogno sempre di esere caricate se uno accde alla pagina, i blocchi si mettono
				   alle pagine. Per recensioni e news è diverso il discorso, quella è la chiamata fatta dal controller
				   e ha gli asterischi perchè c'è la possibilita di fare chiamate con /id dopo l'indirizzo. Ancora altro discorso
				   per login e signup poichè sono controller, ma non vanno autorizzate chiamate con info ulteriori alla chiamata
				   perchè non fanno chiamate con id specifici.
				*/
//				.antMatchers("/altro.html").hasAnyRole(Roles.ADMIN, Roles.USER)
				.antMatchers(HttpMethod.GET, "/recensioni/**",
											 "/news/**",
											 "/commenti-recensione/**",
											 "/secured/**"
//											 "/accountmanager/**"
											 )
											 .permitAll()
				.antMatchers(HttpMethod.POST, "/recensioni",
											  "/news").hasRole(Roles.ADMIN)
				.antMatchers(HttpMethod.DELETE, "/recensioni/**",
												"/news/**").hasRole(Roles.ADMIN)
				.antMatchers(HttpMethod.PUT, "/recensioni",
											 "/news").hasRole(Roles.ADMIN)
				.antMatchers(HttpMethod.POST, "/commenti-recensione").hasAnyRole(Roles.ADMIN, Roles.USER) //FIXME da riportare con giusta authorization
				.antMatchers(HttpMethod.DELETE, "/commenti-recensione/**").hasAnyRole(Roles.ADMIN, Roles.USER) //FIXME da riportare con giusta authorization
				.antMatchers(HttpMethod.PUT, "/commenti-recensione/**").hasAnyRole(Roles.ADMIN, Roles.USER) //FIXME da riportare con giusta authorization
				.antMatchers("/account.html").hasAnyRole(Roles.ADMIN, Roles.USER)
//				.antMatchers("/accountmanager/**").hasAnyRole(Roles.ADMIN) // solo gli admin accedono a /management/**
				.anyRequest().authenticated() // tutte le (altre) richieste richiedono authenticazione
				.and()
				.exceptionHandling()
				.accessDeniedPage("/forbidden.html")
				.and()
				// SpringSecurity di base ci fornisce una pagina di default, ma andiamo a
				// cambiarla con una nostra
				.formLogin()
				.loginPage("/login.html") // indirizzo a cui arriveranno le richieste login
				.loginProcessingUrl("/login") //nella pagina .html di login il form viene inviato con method='post' facendo
				//la chiamata /login, e da qua con quella chiamata inviata processa i dati per capirre se le credenziali siano
				//corrette
				.permitAll() // Giustamente tutti devono riuscire ad accedere
				.defaultSuccessUrl("/homepage.html", true) // se riesce ad accedere lo rimando ad index.html
				.failureUrl("/fail.html")
				.and()
				// configuriamo anche la pagina per il logout
				.logout().logoutUrl("/logout")
				.logoutSuccessUrl("/homepage.html")// indirizzo per fare logout
				.clearAuthentication(true).logoutSuccessUrl("/homepage.html")
				;
				
	}
	
	

	@Bean 
	// questo oggetto servirà a spring security per andare a cercare gli utenti da un service
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(authService);
		
		return provider;
	}
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

}
