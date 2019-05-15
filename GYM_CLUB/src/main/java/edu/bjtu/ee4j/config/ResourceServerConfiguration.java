package edu.bjtu.ee4j.config;



import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@EnableAuthorizationServer
//@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter
{
//	  @RequestMapping("/publica")
//	  public String publico() {
//	    return "Pagina Publica";
//	  }
//	  @RequestMapping("/privada")
//	  public String privada() {
//	    return "Pagina Privada";
//	  }
//	  @RequestMapping("/admin")
//	  public String admin() {
//	    return "Pagina Administrador";
//	  }

	@Override
		public void configure(HttpSecurity http) throws Exception {
			http
			.authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**").permitAll();
//			 .anyRequest().authenticated();
			http.requestMatchers().antMatchers("/v2/services")
			.and().authorizeRequests()
			.antMatchers("/v2/services").access("hasRole('USER')")
			.and().requestMatchers().antMatchers("/v2/about")
			.and().authorizeRequests()
			.antMatchers("/v2/about").access("hasRole('ADMIN')");
		       http.requestMatchers()
		        // For org.springframework.security.web.SecurityFilterChain.matches(HttpServletRequest)
		        .requestMatchers(
		                new OrRequestMatcher(
		                        new AntPathRequestMatcher("/login"),
		                        new AntPathRequestMatcher("/logout"),
		                        new AntPathRequestMatcher("/oauth/authorize"),
		                        new AntPathRequestMatcher("/oauth/token")
		                )
		        );
		}   
	

}

