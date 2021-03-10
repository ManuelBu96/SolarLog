/*
Copyright 2020 - 2021 Christoph Kohnen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package de.mbussmann.solarlog.api.databaseInteraction;

/**
 * This is an interface to store information about a mySQL database
 * @author Christoph Kohnen
 * @since 3.10.0
 */
public class SQLDatabase {
    /**
     * The host of the database
     */
    private final String host;
    /**
     * Your username
     */
    private final String user;
    /**
     * Your password
     */
    private final String password;
    /**
     * The desired database
     */
    private final String database;
    /**
     * The desired table
     */
    private final String table;

    /**
     * Instantiate using host, credentials, database and table
     * @param host The host of the database
     * @param user Your username
     * @param password Your password
     * @param database The desired database
     * @param table The desired table
     */
    public SQLDatabase(String host, String user, String password, String database, String table) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
        this.table = table;
    }

    /**
     * Get the host of the database
     * @return The host of the database
     */
    public String getHost() {
        return host;
    }

    /**
     * Get the user trying to use the database
     * @return The user trying to use the database
     */
    public String getUser() {
        return user;
    }

    /**
     * Get the users password
     * @return The users password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the desired database
     * @return The desired database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Get the desired table
     * @return The desired table
     */
    public String getTable() {
        return table;
    }
}
