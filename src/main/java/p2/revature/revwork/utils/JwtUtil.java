package p2.revature.revwork.utils;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;


@Component
public class JwtUtil {

	private static JWTVerifier jwtVerifier;
	
    private JwtUtil() {
    }

    static {
    	jwtVerifier = null;
    }
	 
   public int getId(String token) throws UnsupportedEncodingException {
	   
//	   Integer id = null;
//	   String arr[] = token.split("\\.");
//	   String payload = arr[1];
//   		String jsonString = new String(Base64.decodeBase64(payload), "UTF-8");
//   		String[] split = jsonString.split(",");
//   		String stringId = split[3].substring(5);
//		 id = Integer.parseInt(stringId); 
//
//	   return id;
	   
		int id = -1;
		
		try {
//		    Algorithm algorithm = Algorithm.HMAC256("secret"); //use more secure key
//		    JWTVerifier verifier = JWT.require(algorithm)
//		        //.withIssuer("auth0")
//		        .build(); //Reusable verifier instance
			
			JWTVerifier verifier = getJwtVerifierr();
			
		    DecodedJWT jwt = verifier.verify(token);
		    
		    Map<String, Claim> claims = jwt.getClaims();  
		    
		    if ( claims.containsKey("id") ){

		    	
		    	Claim c = claims.get("id");
		    	
		    	id = c.asInt();
		    }
		    
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
		}
		
		
		return id;
	   
	   
   }
   
	
    @Bean
    @Scope("prototype")
    public JWTCreator.Builder getJwtBuilder() {
        return JWT.create();
    }
	
    @Bean
    @Scope("singleton")
    public JWTVerifier getJwtVerifierr() {
    	if ( jwtVerifier == null ) {
    		Algorithm algorithm = Algorithm.HMAC256("michael"); //use more secure key
    		
    		jwtVerifier = JWT.require(algorithm)
			        .withIssuer("auth0")
			        .build(); //Reusable verifier instance
    	}
    	
    	return jwtVerifier;
    }
    
}
