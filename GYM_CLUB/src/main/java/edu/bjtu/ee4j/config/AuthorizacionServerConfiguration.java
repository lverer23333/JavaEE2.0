package edu.bjtu.ee4j.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


@Configuration
@EnableAuthorizationServer

public class AuthorizacionServerConfiguration  extends AuthorizationServerConfigurerAdapter  {

	  @Autowired
	  @Qualifier("authenticationManagerBean")
	  private AuthenticationManager authenticationManager;
	  
	  @Autowired
	  private TokenStore tokenStore;

	  @Override
	    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	       // 允许表单登录
	       security.allowFormAuthenticationForClients();
	    }

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
		    .withClient("client")
            .authorizedGrantTypes("client_credentials","password", "authorization_code", "refresh_token", "implicit")
            .scopes("read","write")   
            .redirectUris("http://www.baidu.com")
            .secret(passwordEncoder().encode("password"));          

	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
	 @Override
	 public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	     endpoints
	             .authenticationManager(authenticationManager)	        
	             .tokenStore(tokenStore)
	             .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
	             
	 }
	 
	  @Bean
	  public TokenStore tokenStore() {
	      return new InMemoryTokenStore();
	  }
//	
}
