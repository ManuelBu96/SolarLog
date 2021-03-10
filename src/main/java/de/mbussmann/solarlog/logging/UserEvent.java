package de.mbussmann.solarlog.logging;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logging.Logger;

/**
 * @author Manuel Bussmann
 */
@ApplicationScoped
public class UserEvent {
    private static final Logger LOG = Logger.getLogger(UserEvent.class);

    /**
     * Event if Register worked
     * @param userId newUserId
     */
    public void successRegisterEventInfo(Long userId) {
        LOG.info("User with id="+userId+" registered.");
    }

    /**
     * Event if Register failed
     * @param mail Mail address from register attempt
     */
    public void failedRegisterEventInfo(String mail) {
        LOG.info("User with mail="+mail+" can not registered.");
    }

    /**
     * Event if Login worked
     * @param userId Logged in UserId
     */
    public void successLoginEventInfo(Long userId) {
        LOG.info("User with id="+userId+" logged in.");
    }

    /**
     * Event if login failed
     * @param mail Mail address from Login attempt
     */
    public void failedLoginEventInfo(String mail) {
        LOG.info("User with mail="+mail+" can not logged in.");
    }

    /**
     * Event if login failed
     * @param mail Mail address from Login attempt
     */
    public void wrongCredentialsEventInfo(String mail) {
        LOG.info("User with mail="+mail+" try to login with wrong Credentials.");
    }

    /**
     * Event if User Info Update work
     * @param userId UserId
     */
    public void successUserInfoUpdateEventInfo(Long userId) {
        LOG.info("User with id="+userId+" updated his Information.");
    }

    /**
     * Event if User Info Update failed
     * @param userId UserId
     */
    public void failedUserInfoUpdateEventInfo(Long userId) {
        LOG.info("User with id="+userId+" can not updated his Information.");
    }

    /**
     * Event if remove User worked
     * @param userId UserId
     */
    public void successRemoveEventInfo(Long userId) {
        LOG.info("User with id="+userId+" removed.");
    }

    /**
     * Event if remove User failed
     * @param userId UserId
     */
    public void failedRemoveEventInfo(Long userId) {
        LOG.info("User with id="+userId+" can not be removed.");
    }
}
