package com.github.gtvnv.libraryapi.config;

import com.github.gtvnv.libraryapi.security.JwtCustomAuthenticationFilter;
import com.github.gtvnv.libraryapi.security.LoginSocialSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // 1. Libera arquivos estáticos
                        .requestMatchers("/css/**", "/js/**", "/img/**", "/images/**").permitAll()
                        // 2. Libera a página inicial e a página de login
                        .requestMatchers("/", "/index", "/login").permitAll()
                        // 3. Todo o resto exige estar logado
                        .anyRequest().authenticated()
                )
                // Configuração do Login com Usuário/Senha
                .formLogin((form) -> form
                        .loginPage("/login") // Usa seu HTML customizado
                        .defaultSuccessUrl("/cadastro-autor", true) // Força ir para cadastro-autor
                        .permitAll()
                )
                // Configuração do Login com Google (Faltava isso!)
                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/login") // Para o Spring saber que o botão do Google está lá
                        .defaultSuccessUrl("/cadastro-autor", true)
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/v2/api-docs/**",
                "/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/webjars/**"
        );
    }

    // CONFIGURA O PREFIXO ROLE
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    // CONFIGURA, NO TOKEN JWT, O PREFIXO SCOPE
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("");

        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }
}
