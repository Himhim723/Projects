package com.user.basicusermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.*;

import org.springframework.beans.factory.annotation.Autowired;

import com.user.basicusermanagement.config.security.JwtAuthenticationFilter;
import com.user.basicusermanagement.model.enums.Permission;
import com.user.basicusermanagement.model.enums.Role;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{

  @Autowired
  private JwtAuthenticationFilter jwtAuthFilter;
  @Autowired
  private AuthenticationProvider authenProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http.csrf(AbstractHttpConfigurer::disable)
         .authorizeHttpRequests(req ->
              req.requestMatchers("/v1/user/signUp").permitAll()
                 .requestMatchers("/v1/user/authenticate").permitAll()
                 .requestMatchers("/v1/user/**").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                 .requestMatchers("/v1/admin/**").hasAnyRole(Role.ADMIN.name())
                 .requestMatchers(GET, "/v1/admin/**").hasAnyAuthority(Permission.ADMIN_READ.name())
                 .requestMatchers(POST, "/v1/admin/**").hasAnyAuthority(Permission.ADMIN_CREATE.name())
                 .requestMatchers(PUT, "/v1/admin/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name())
                 .requestMatchers(DELETE, "/v1/admin/**").hasAnyAuthority(Permission.ADMIN_DELETE.name())
                 .anyRequest()
                 .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(ALWAYS))
                .authenticationProvider(authenProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
  }

}
  