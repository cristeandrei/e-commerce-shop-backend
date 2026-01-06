package com.ecommerce.shop.security;

import java.util.Arrays;
import java.util.function.Consumer;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseCookie;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  Consumer<ResponseCookie.ResponseCookieBuilder> sameSiteConsumer =
      builder -> builder.sameSite("Lax").secure(false);

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) {
    var csrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();

    csrfTokenRepository.setCookieCustomizer(sameSiteConsumer);

    http.authorizeHttpRequests(
            e -> e.requestMatchers("/auth/csrfToken").permitAll().anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults())
        .logout(e -> e.logoutUrl("/auth/logout/").logoutSuccessUrl("/"))
        .csrf(c -> c.csrfTokenRepository(csrfTokenRepository))
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
    return http.build();
  }

  @Bean
  public UrlBasedCorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173/"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
    configuration.addAllowedHeader("*");
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  UserDetailsService userDetailsService(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
