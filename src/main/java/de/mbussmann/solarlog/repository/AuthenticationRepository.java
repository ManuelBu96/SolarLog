package de.mbussmann.solarlog.repository;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import de.mbussmann.solarlog.logging.UserEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import de.mbussmann.solarlog.boundary.dto.AuthenticationDto;
import de.mbussmann.solarlog.boundary.dto.RegistrationDto;
import de.mbussmann.solarlog.control.AuthenticationService;
import de.mbussmann.solarlog.entity.User;
import de.mbussmann.solarlog.entity.UserRole;
import de.mbussmann.solarlog.security.JWTUtility;
import de.mbussmann.solarlog.security.PasswordUtility;
import de.mbussmann.solarlog.util.exceptions.AuthenticationException;
import de.mbussmann.solarlog.util.exceptions.ExceptionReason;

/**
 * @author Manuel Bußmann
 */
@ApplicationScoped
public class AuthenticationRepository implements AuthenticationService {

    @Inject
    JWTUtility jwtUtility;

    @Inject
    PasswordUtility passwordUtility;

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    @ConfigProperty(name = "mp.jwt.duration")
    Long duration;

    @Inject
    EntityManager em;

    @Inject
    UserEvent userEvent;

    /**
     * Login into the System
     * @param user User who wants to Logn
     * @return generatedSecurity Token
     * @throws AuthenticationException Detailed Error Message
     */
    @Override
    @Transactional
    public String login(AuthenticationDto user) throws AuthenticationException {
        try {
            User possibleUser = em.createQuery("SELECT u from User u where u.email = :email", User.class)
                    .setParameter("email", user.getEmail()).getSingleResult();
            if (this.passwordUtility.isPasswordValid(user.getPassword(), possibleUser.getPassword())) {
                userEvent.successLoginEventInfo(possibleUser.getId());
                return this.jwtUtility.generateToken(possibleUser.getId(),possibleUser.getFirstName(), possibleUser.getLastName(),
                        possibleUser.getRole().toString(), duration, issuer);
            } else {
                userEvent.wrongCredentialsEventInfo(user.getEmail());
                throw new AuthenticationException(ExceptionReason.USERNAME_OR_PASSWORD_INCORRECT.getReason());
            }
        } catch (NoResultException ex) {
            userEvent.failedLoginEventInfo(user.getEmail());
            throw new AuthenticationException(ExceptionReason.USERNAME_OR_PASSWORD_INCORRECT.getReason());
        } catch (NoSuchAlgorithmException e) {
            userEvent.failedLoginEventInfo(user.getEmail());
            throw new AuthenticationException(ExceptionReason.JWT_GENERATION_ERROR.getReason());
        } catch (InvalidKeySpecException e) {
            userEvent.failedLoginEventInfo(user.getEmail());
            throw new AuthenticationException(ExceptionReason.JWT_GENERATION_ERROR.getReason());
        } catch (IOException e) {
            userEvent.failedLoginEventInfo(user.getEmail());
            throw new AuthenticationException(ExceptionReason.JWT_GENERATION_ERROR.getReason());
        }
    }

    /**
     * Register a new User in the System
     * @param newUser User who wants to register
     * @throws AuthenticationException Detailed Error Message
     */
    @Override
    @Transactional
    public void registerNewUser(RegistrationDto newUser) throws AuthenticationException {
        try{
            User user = new User();
            user.setEmail(newUser.getEmail());
            user.setPassword(this.passwordUtility.hashPassword(newUser.getPassword()));
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setRole(UserRole.USER);
            em.persist(user);
            userEvent.successRegisterEventInfo(user.getId());
        }catch(PersistenceException e) {
            userEvent.failedRegisterEventInfo(newUser.getEmail());
            throw new AuthenticationException(ExceptionReason.EMAIL_ALREADY_IN_USE.getReason());
        }
    }
    
}
