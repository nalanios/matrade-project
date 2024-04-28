//Security configuration using Spring Security 

package com.Team3.MaitreD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
import com.Team3.MaitreD.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private final RSAKeyProperties keys;
	// Get public and private RSA keys for JWT encryption and decryption
	public SecurityConfiguration(RSAKeyProperties keys){
        this.keys = keys;
    }
	// encode user passwords
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // Authenticate users
    @Bean
    AuthenticationManager authManager(UserDetailsService detailsService){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(detailsService);
        daoProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoProvider);
    }
    // Spring Security settings
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		//Sets security requirements for access to routes/endpoints
    	http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers( "/", "/css/**", "/js/**", "/imgs/**", "/favicon.ico").permitAll();
					auth.requestMatchers("/login", "/register", "/check-roles", "/search", "/customer/**", "/restaurant/**", "/h2-console/**", "/get-image/**", "/reservation/cancel/{reservationID}", "/reservation/modify", "reservation/update-information/{reservationID}").permitAll();
					auth.requestMatchers("/profile/{username}/information", "/restaurant-reservations/{username}").hasRole("RESTAURANT");
					auth.requestMatchers("/customer/{username}/information", "/reserve", "/reservation/**").hasRole("CUSTOMER");
					auth.anyRequest().authenticated();
					
				});
		//Need this to see h2 database at localhost:8080/h2-console
		http.headers((headers) ->
        headers
        .frameOptions((frameOptions) -> frameOptions.disable()));
		//Incorporate OAuth2 to authenticate jwts		
		http
				.oauth2ResourceServer((oauth2ResourceServer) -> oauth2ResourceServer
				.jwt((jwt) -> jwt.decoder(jwtDecoder())
				.jwtAuthenticationConverter(jwtAuthenticationConverter())));
		//Make app stateless, using jwt instead of sessions
		http
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
    // Decode JWT when sent from client side in order to validate
    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
    }
    // Encode JWT before sending to client
    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
    // JWT authorities configuration for use with Spring Secutity 'roles'
    // Adds 'ROLE' prefix to roles in the token 
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


