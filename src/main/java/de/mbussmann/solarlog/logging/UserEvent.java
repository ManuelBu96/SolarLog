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

package de.mbussmann.solarlog.logging;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

/**
 * @author Manuel Bußmann
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
