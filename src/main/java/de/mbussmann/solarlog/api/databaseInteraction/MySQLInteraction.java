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
package me.meloni.SolarLogAPI.DatabaseInteraction;

/**
 * This class provides functionality to read and write data from or to an mySQL database.
 * @author Christoph Kohnen
 * @since 3.10.0
 */
public class MySQLInteraction {
    /**
     * The base write query, which is filled in by values later
     */
    private static final String BASE_WRITE_QUERY = "INSERT INTO `%s`.`%s` (`Timestamp`, `Value1`, `Value2`, `Value3`, `Value4`, `Value5`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s');";
    /**
     * Used date format in mySQL
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /*
     * Write data to an mySQL database
     * @param host The host of the database
     * @param user Your username
     * @param password Your password
     * @param database The desired database
     * @param table The desired table
     * @param map The data as {@link SolarMap} you want to write
     * @throws SQLException If something with the database goes wrong
     *
    public static void write(String host, String user, String password, String database, String table, SolarMap map) throws SQLException {
        Connection conn = DriverManager.getConnection(host,user,password);
        Statement st = conn.createStatement();

        map.forEach((date, objects) -> {
            String timestamp = new SimpleDateFormat(DATE_FORMAT).format(date);
            Logger.log(String.format("Writing point %s", timestamp));
            try {
                String query = String.format(BASE_WRITE_QUERY, database, table, timestamp, objects.get(0), objects.get(1), objects.get(2), objects.get(3), objects.get(4));
                st.executeUpdate(query);
            } catch (SQLException ignored) {
                Logger.warn(String.format("Unable to write %s", timestamp));
            }
        });
        conn.close();
    }

     */
}
