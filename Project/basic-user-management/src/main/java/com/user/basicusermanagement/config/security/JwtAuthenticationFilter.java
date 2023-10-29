package com.user.basicusermanagement.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.user.basicusermanagement.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserRepository userRepository;

  @Override
  protected void doFilterInternal(
    @NonNull HttpServletRequest request, 
    @NonNull HttpServletResponse response, 
    @NonNull FilterChain filterChain)
  throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
          filterChain.doFilter(request, response);
          return;
        }

        final String jwtToken = authHeader.substring(7);              //  authHeader = "Bearer {JwtToken...}"
        final String username = jwtService.extractUsername(jwtToken); // Decode token

        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
          UserDetails userDetails = userRepository.findByUsername(username).orElseThrow(()->new EntityNotFoundException());
          if(jwtService.isTokenValid(jwtToken, userDetails)){
          UsernamePasswordAuthenticationToken authToken = 
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
          }
        }
        filterChain.doFilter(request, response);
  }
}
