package com.circunet.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import javax.crypto.SecretKey; import java.nio.charset.StandardCharsets; import java.time.Instant; import java.util.Date;
@Service public class JwtService {
 private final SecretKey key; private final long accessMinutes, refreshDays;
 public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.access-minutes:30}") long accessMinutes,@Value("${app.jwt.refresh-days:7}") long refreshDays){ this.key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); this.accessMinutes=accessMinutes; this.refreshDays=refreshDays; }
 public String access(long id,String role){return create(id,role,"access",accessMinutes*60);}
 public String refresh(long id,String role){return create(id,role,"refresh",refreshDays*86400);}
 private String create(long id,String role,String type,long seconds){Instant now=Instant.now();return Jwts.builder().subject(Long.toString(id)).claim("role",role).claim("type",type).issuedAt(Date.from(now)).expiration(Date.from(now.plusSeconds(seconds))).signWith(key).compact();}
 public Claims parse(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();}
}
