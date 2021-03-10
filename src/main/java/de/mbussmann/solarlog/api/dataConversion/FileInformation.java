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
package de.mbussmann.solarlog.api.dataConversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
/**
 * This class includes functions to get information about the device of a file.
 * @author Christoph Kohnen
 * @since 0.0.1
 */
public class FileInformation {

    /**
     * The date format which is used in the first row of a .dat file
     */
    @SuppressWarnings("SpellCheckingInspection")
    static final String DATE_FORMAT = "dd.MM.yyhh:mm:ss";

    /**
     * Get the model of a SolarLog from the first line of a .dat file
     * @param infoRow The first line of a .dat file
     * @return The model of a SolarLog
     */
    public static String getModel(String infoRow){
        return strings(infoRow).get(1);
    }

    /**
     * Get the build date of a SolarLog from the first line of a .dat file
     * @param infoRow The first line of a .dat file
     * @return The build date of the OS used
     * @throws ParseException If the file contains a date format which is different to the specified one
     */
    public static Date buildDate(String infoRow) throws ParseException {
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.parse(strings(infoRow).get(8) + strings(infoRow).get(9));
    }

    /**
     * Get the build with subversion of a SolarLog from the first line of a .dat file
     * @param infoRow The first line of a .dat file
     * @return The build of the OS used
     */
    public static String getBuild(String infoRow){
        if(infoRow != null) {
            return strings(infoRow).get(10) + " " + strings(infoRow).get(11) + " " + strings(infoRow).get(12);
        } else return null;
    }

    /**
     * Get the major build version of a SolarLog from the first line of a .dat file
     * @param infoRow The first line of a .dat file
     * @return The major build version of the OS used
     */
    public static String getBuildVersion(String infoRow){
        if(infoRow != null) {
            return strings(infoRow).get(10).substring(1,6);
        } else return null;
    }

    /**
     * Get al strings separated by spaces in the first line of a Solarlog file
     * @param infoRow The first line of a .dat file
     * @return The first line separated by spaces
     */
    private static List<String> strings(String infoRow) {
        if(infoRow != null) {
            return Arrays.asList(infoRow.split(" "));
        } else return null;
    }
}
