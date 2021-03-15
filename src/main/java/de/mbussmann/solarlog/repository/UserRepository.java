/*
 *
 * Copyright 2021 - Manuel Bußmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package de.mbussmann.solarlog.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import de.mbussmann.solarlog.boundary.dto.UserDto;
import de.mbussmann.solarlog.control.UserService;
import de.mbussmann.solarlog.entity.User;
import de.mbussmann.solarlog.entity.UserRole;
import de.mbussmann.solarlog.logging.UserEvent;
import de.mbussmann.solarlog.util.exceptions.ExceptionReason;
import de.mbussmann.solarlog.util.exceptions.UserException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * @author Manuel Bußmann
 */
@ApplicationScoped
public class UserRepository implements UserService {

    @Inject
    EntityManager em;

    @Inject
    UserEvent userEvent;

    /**
     * Boolean if Logging is active
     */
    @ConfigProperty(name = "de.mbussmann.solarlog.logging")
    boolean allowLogging;

    /**
     * Get a User by ID
     * @param id UserID
     * @return UserDTO Object
     * @throws UserException Detailed Error Message
     */
    @Override
    public UserDto getUserById(Long id) throws UserException {
        try{
            return em.createQuery("SELECT new de.mbussmann.solarlog.boundary.dto.UserDto( " +
                    "u.id, u.firstName, u.lastName, u.role, u.email) "
                    +"FROM User u where u.id = :id", UserDto.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(NoResultException e) {
            throw new UserException(ExceptionReason.GET_OWN_DATA_FAILED.getReason());
        }
    }

    /**
     * Change the own User Data
     * @param userDto UserDTO Object
     * @throws UserException Detailed Error Message
     */
    @Override
    @Transactional
    public void changeOwnUserData(UserDto userDto) throws UserException {
        User user = em.find(User.class, userDto.getId());
        if(userDto.getEmail().equals(user.getEmail())){
            try{
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                em.merge(user);
                if(allowLogging) {
                    userEvent.successUserInfoUpdateEventInfo(user.getId());
                }
            }
            catch(PersistenceException e) {
                if(allowLogging) {
                    userEvent.failedUserInfoUpdateEventInfo(user.getId());
                }
                throw new UserException(String.format(ExceptionReason.CANNOT_CHANGE_OWN_DATA.getReason(),""));
            }
        }
        else {
            if(this.isEmailPresentInDatabase(userDto.getEmail())) {
                if(allowLogging) {
                    userEvent.failedUserInfoUpdateEventInfo(user.getId());
                }
                throw new UserException(String.format(": "+ExceptionReason.CANNOT_CHANGE_OWN_DATA.getReason(),
                ExceptionReason.EMAIL_ALREADY_IN_USE.getReason()));
            }
            user.setFirstName(userDto.getFirstName());
            user.setEmail(userDto.getEmail());
            user.setLastName(userDto.getLastName());
            em.merge(user);
            if(allowLogging) {
                userEvent.successUserInfoUpdateEventInfo(user.getId());
            }
        }
    }

    /**
     * Delete a User by ID
     * @param id UserID
     * @throws UserException Detailed Error Message
     */
    @Override
    @Transactional
    public void deleteUser(Long id) throws UserException {
        User user = em.find(User.class, id);
        if(user == null) {
            if(allowLogging) {
                userEvent.failedRemoveEventInfo(id);
            }
            throw new UserException(ExceptionReason.DELETE_USER_FAILED.getReason());
        }
        if(user.getRole() == UserRole.ADMINISTRATOR) {
            Long count = em.createQuery("SELECT count(u.id) from User u where u.role = :role", Long.class)
                        .setParameter("role", UserRole.ADMINISTRATOR)
                        .getSingleResult();
            if(count == 1) {
                if(allowLogging) {
                    userEvent.failedRemoveEventInfo(user.getId());
                }
                throw new UserException(ExceptionReason.CANNOT_DELETE_LAST_ADMIN.getReason());
            }
        }
        em.remove(user);
        if(allowLogging) {
            userEvent.successRemoveEventInfo(user.getId());
        }
    }

    /**
     * Get a User Entity by UserID
     * @param id UserID
     * @return User Object
     */
    public User getEntity(Long id) {
        return em.find(User.class, id);
    }

    /**
     * Get a User Entity by E-Mail
     * @param email E-Mail as a String
     * @return User Object
     */
    @Override
    public User getUserByEmail(String email) {
        return em.createQuery("SELECT u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    /**
     * Validate if a E-Mail is present in the Database
     * @param email E-Mail as a String
     * @return Flag if present or not
     */
    private boolean isEmailPresentInDatabase(String email) {
        try{
            em.createQuery("SELECT u from User u where u.email = :email", User.class)
                    .setParameter("email", email).getSingleResult();
            return true;
        } catch(NoResultException e) {
            return false;
        }
    }

}
