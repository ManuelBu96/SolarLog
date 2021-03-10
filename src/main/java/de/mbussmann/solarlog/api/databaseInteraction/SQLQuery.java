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
 * This class provides functionality to write an SQL query to a file
 * @author Christoph Kohnen
 * @since 3.10.1
 */
public class SQLQuery {
    /**
     * The base write query, which is filled in by values later
     */
    private static final String BASE_WRITE_QUERY = "INSERT INTO `%s`.`%s` (`%s`, `%s`, `%s`, `%s`, `%s`, `%s`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s');";
    /**
     * Used date format in mySQL
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /*
     * Write a SQL query to a file and retrieve it
     * @param database The desired database
     * @param table The desired table
     * @param key The name of the key
     * @param value1 The name of the first value
     * @param value2 The name of the second value
     * @param value3 The name of the third value
     * @param value4 The name of the fourth value
     * @param value5 The name of the fifth value
     * @param solarMap The data as {@link SolarMap}
     * @return The query written to a file
     * @throws IOException If the file does already exist
     *
    public static File getWriteQuery(String database, String table, String key, String value1, String value2, String value3, String value4, String value5, SolarMap solarMap) throws IOException {
        String filename = String.format("Query-%s.txt", solarMap.getId());
        File file = new File(WorkingDirectory.getDirectory(), filename);
        if (!file.createNewFile()) {
            throw new FileAlreadyExistsException("File already exists!");
        }
        FileWriter writer = new FileWriter(file);
        for (String writeQueryLine : writeQueryLines(database, table, key, value1, value2, value3, value4, value5, solarMap)) {
            writer.write(writeQueryLine + "\n");
        }
        writer.close();
        return file;
    }

     */

    /*
     * Get all lines for an SQL query as {@link List}
     * @param database The desired database
     * @param table The desired table
     * @param key The name of the key
     * @param value1 The name of the first value
     * @param value2 The name of the second value
     * @param value3 The name of the third value
     * @param value4 The name of the fourth value
     * @param value5 The name of the fifth value
     * @param solarMap The data as {@link SolarMap}
     * @return The query as each line put into a {@link List}
     *
    private static List<String> writeQueryLines(String database, String table, String key, String value1, String value2, String value3, String value4, String value5, SolarMap solarMap) {
        List<String> lines = new ArrayList<>();
        lines.add(String.format("USE %S;", database));
        solarMap.forEach((date, values) -> lines.add(String.format(BASE_WRITE_QUERY, database, table, key, value1, value2, value3, value4, value5, new SimpleDateFormat(DATE_FORMAT).format(date), values.get(0), values.get(1), values.get(2), values.get(3), values.get(4))));
        return lines;
    }

     */
}
