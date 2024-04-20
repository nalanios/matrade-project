package com.Team3.MaitreD.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.http.SessionCreationPolicy;

import com.Team3.MaitreD.utils.LoginSuccessHandler;
import com.Team3.MaitreD.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
	
	private final RSAKeyProperties keys;
	
	@Autowired 
	private LoginSuccessHandler loginSuccessHandler;
	
	public SecurityConfiguration(RSAKeyProperties keys){
        this.keys = keys;
    }
	
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authManager(UserDetailsService detailsService){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(detailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoProvider);
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers( "/", "/css/**", "/js/**", "/images/**").permitAll();
					auth.requestMatchers("/login", "/register", "/h2-console/**").permitAll();
					//auth.requestMatchers("/auth/**").permitAll();
//					auth.requestMatchers("/admin/**").hasRole("ADMIN");
                    auth.requestMatchers("/check-roles").permitAll();
                    auth.requestMatchers("/customer/profile").permitAll(); //temporary for redirect. TODO: implement CUSTOMER ROLE only access
                    auth.requestMatchers("/customer/home").permitAll(); //temporary for redirect. TODO: implement CUSTOMER ROLE only access
					auth.requestMatchers("/restaurant/profile").permitAll(); //temporary for redirect. TODO: implement RESTAURANT ROLE only access
					auth.requestMatchers("/restaurant/home").permitAll(); //temporary for redirect. TODO: implement RESTAURANT ROLE only access
					//auth.anyRequest().authenticated();
					
				});
		//http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
		
		http.headers((headers) ->
        headers
        .frameOptions((frameOptions) -> frameOptions.disable()));
//				.formLogin(formLogin -> formLogin.loginPage("/login")
//				.loginProcessingUrl("/login")
//				.successHandler(loginSuccessHandler));
		http
				.oauth2ResourceServer((oauth2ResourceServer) -> oauth2ResourceServer
				.jwt((jwt) -> jwt.decoder(jwtDecoder())
				.jwtAuthenticationConverter(jwtAuthenticationConverter())));
					
		http
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}

    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }

    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter(){
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtConverter;
    }
}


