package com.user.basicusermanagement.config.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.user.basicusermanagement.infra.exception.UserException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service 
public class JwtService {

  private static final String SECRET_KEY = "A54B234C1221DF341243126DD51A9C3B451AC9921B67D12A892";
  private final Long refreshExpiration = 1000*60*15L; //15 min for valid token

  public String extractUsername(String jwtToken){
    return extractClaim(jwtToken, Claims::getSubject);
  }

  public <T> T extractClaim(String jwtToken, Function<Claims,T> claimResolver){
    final Claims claims = extractAllClaims(jwtToken);
    return claimResolver.apply(claims);
  }

  private Claims extractAllClaims(String jwtToken){
    return Jwts.parserBuilder()
                .setSigningKey(getSignInKey()).build()
                .parseClaimsJws(jwtToken).getBody();
  }

  private Key getSignInKey() {
    byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyByte);
  }

  public String generateToken(UserDetails userDetails) throws UserException{
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(Map<String,Object> extractClaims,UserDetails userDetails) throws UserException{
    return Jwts.builder().setClaims(extractClaims).setSubject(userDetails.getUsername())
    .setIssuedAt(new Date(System.currentTimeMillis()))
    .setExpiration(new Date(System.currentTimeMillis()+refreshExpiration)) 
    // If the account is not active for more than 15 minutes, token will be expired
    // client needed to login again for authentication
    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
    .compact();
  }
  
  public boolean isTokenValid(String token, UserDetails userDetails){
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && ! isTokenExpired(token));
  }

  public boolean isTokenExpired(String token){
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
  

}
