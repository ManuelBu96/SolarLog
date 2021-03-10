package de.mbussmann.solarlog.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;

import javax.enterprise.context.ApplicationScoped;

/**
 * Class for JWT Generation.
 *
 * @author Manuel Bußmann
 */
@ApplicationScoped
public class JWTUtility {



    
    /** 
     * Generates JsonWebToken based on provided information.
     * 
     * @param firstName the first name of the User
     * @param lastName the last name of the user
     * @param role the Role of the User
     * @param duration the amount of time in which the token is valid
     * @param issuer the issuer of the token
     * @return String the JWT
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public String generateToken(Long id, String firstName, String lastName,String role, Long duration, String issuer)
            throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String privateKeyLocation = "/privatekey.pem";
        PrivateKey privateKey = readPrivateKey(privateKeyLocation);

        JwtClaimsBuilder claimsBuilder = Jwt.claims();
        long currentTimeInSecs = this.currentTimeInSecs();

        claimsBuilder.issuer(issuer);
        claimsBuilder.subject("onlineforum");
        claimsBuilder.issuedAt(currentTimeInSecs);
        claimsBuilder.expiresAt(currentTimeInSecs + duration);
        claimsBuilder.groups(role);
        claimsBuilder.claim("id", id.toString());
        claimsBuilder.claim("firstName", firstName);
        claimsBuilder.claim("lastName", lastName);

        return claimsBuilder.jws().keyId(privateKeyLocation).sign(privateKey);
    }

    
    /**
     * Returns a {@link PrivateKey} Object representing the key in the path provided.  
     * 
     * @param pemResName Path to the private Key file
     * @return PrivateKey
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private PrivateKey readPrivateKey(final String pemResName) throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        try (InputStream contentIS = JWTUtility.class.getResourceAsStream(pemResName)) {
            byte[] tmp = new byte[4096];
            int length = contentIS.read(tmp);
            return decodePrivateKey(new String(tmp, 0, length, "UTF-8"));
        }
    }

    
    /**
     * Converts the private Key to the Java Implementation of a private Key 
     * 
     * @param pemEncoded String representation of the key
     * @return PrivateKey
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private PrivateKey decodePrivateKey(final String pemEncoded)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] encodedBytes = toEncodedBytes(pemEncoded);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    
    /**
     * Returns the cryptographic Key as a Byte[] 
     * 
     * @param pemEncoded Cryptographic Key in String format
     * @return byte[] the cryptographic key
     */
    private byte[] toEncodedBytes(final String pemEncoded) {
        final String normalizedPem = removeBeginEnd(pemEncoded);
        return Base64.getDecoder().decode(normalizedPem);
    }

    
    /** 
     * Returns the cryptographic Key String represantation without the Begin and End Delimiter
     * @param pem the Cryptographic Key in String format
     * @return String
     */
    private String removeBeginEnd(String pem) {
        pem = pem.replaceAll("-----BEGIN (.*)-----", "");
        pem = pem.replaceAll("-----END (.*)----", "");
        pem = pem.replaceAll("\r\n", "");
        pem = pem.replaceAll("\n", "");
        return pem.trim();
    }

    
    /** 
     * Returns the current Time in seconds since 01.01.1970
     * @return int the Time in seconds
     */
    private int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
    
}
