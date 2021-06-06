package fr.univlyon1.m1if.m1if03.helper;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.*;


public class HelperToken {

   public static DecodedJWT verifier( String token) 
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            com.auth0.jwt.JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .acceptLeeway(1)
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (JWTVerificationException exception){ 
            return null;
            //Invalid signature/claims
        }
    }

    public static String returnPseudo( String token) 
    {
        DecodedJWT jwt = verifier(token);
        if (jwt != null) {
            String pseudo = jwt.getSubject();
            return pseudo;
        }
        return null;
    }
}