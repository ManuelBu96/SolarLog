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

package de.mbussmann.solarlog.util.exceptions;

/**
 * Enum for different Exception Reasons to give a clear error message to the use
 * @author Manuel Bußmann
 */
public enum ExceptionReason {
    // Authentication
    EMAIL_ALREADY_IN_USE("EMAIL_ALREADY_IN_USE"),
    USERNAME_OR_PASSWORD_INCORRECT("USERNAME_OR_PASSWORD_INCORRECT"),
    JWT_GENERATION_ERROR("JWT_GENERATION_ERROR"),
    // User Exception
    GET_OWN_DATA_FAILED("GET_OWN_DATA_FAILED"),
    CANNOT_CHANGE_OWN_DATA("CANNOT_CHANGE_OWN_DATA%s"),
    CANNOT_CHANGE_USER_ROLE("CANNOT_CHANG_USER_ROLE"),
    CANNOT_DELETE_LAST_ADMIN("CANNOT_DELETE_LAST_ADMIN"),
    DELETE_USER_FAILED("DELETE_USER_FAILED");


    private final String reason;

    private ExceptionReason(final String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
