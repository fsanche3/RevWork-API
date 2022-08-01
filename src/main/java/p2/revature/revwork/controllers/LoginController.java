package p2.revature.revwork.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import p2.revature.revwork.utils.JwtUtil;
import p2.revature.revworkboot.api.LoginApi;
import p2.revature.revworkboot.models.Usernameandpassword;

@RestController
@RequestMapping(path="/login")
public class LoginController implements LoginApi{

	private JwtUtil jwtUtil;
	
	public LoginController(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	
	@Override
	@PostMapping()
	public ResponseEntity<String> loginPost(@Valid Usernameandpassword body) {
		
		String username = body.getUsername();
		
		
		String token = "";
		try {
			JWTCreator.Builder builder = jwtUtil.getJwtBuilder();
			Algorithm algorithm = Algorithm.HMAC256("michael");
			String[] sa = {"freelancer"};
			token = builder
	        .withIssuer("auth0")
	        .withArrayClaim("roles",sa)
	        .withClaim("username",username)
			.withClaim("fullname","John Doe")
	        .sign(algorithm);
			
			System.out.println("username"+ username);
			System.out.println("password: "+ body.getPassword());
			System.out.println("token: "+ token);
			
			
		}
		catch (JWTCreationException exception){
			
		}
		
		
	
		return new ResponseEntity<>(token,HttpStatus.OK);
	}
	

}
