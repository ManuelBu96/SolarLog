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

package de.mbussmann.solarlog.control;

import de.mbussmann.solarlog.boundary.dto.UserDto;
import de.mbussmann.solarlog.entity.User;
import de.mbussmann.solarlog.util.exceptions.UserException;

/**
 * @author Manuel Bußmann
 */
public interface UserService {
    UserDto getUserById(Long id) throws UserException;
    void changeOwnUserData(UserDto userDto) throws UserException;
    void deleteUser(Long id)  throws UserException;
    User getEntity(Long id);
    User getUserByEmail(String email);

}
