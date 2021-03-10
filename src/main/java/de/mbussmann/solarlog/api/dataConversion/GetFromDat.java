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
package me.meloni.SolarLogAPI.DataConversion;

import me.meloni.SolarLogAPI.FileInteraction.GetFile;
import me.meloni.SolarLogAPI.FileInteraction.ReadFiles.GetFileContent;
import me.meloni.SolarLogAPI.FileInteraction.Tools.FileVersion;
import me.meloni.SolarLogAPI.Handling.Logger;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class includes functions to extract data from lines found in the backup files.
 * @author Christoph Kohnen
 * @since 0.0.1
 */
@SuppressWarnings("DuplicatedCode")
public class GetFromDat {
    /**
     * The format in which the timestamp is stored inside the .dat files
     */
    private static final String DATEFORMAT = "dd.MM.yy HH:mm:ss";

    /**
     * Get all data from a .dat file in the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     * @param file The file from which the data should be extracted
     * @return All data in the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     * @throws IOException If provided a bad file
     */
    public static Map<Date, List<Integer>> getAsMapFromDatFile(File file) throws IOException {
        String fileVersion = FileVersion.getFileVersion(file);
        List<Integer> positions = FileVersion.getPositionMatrix().get(fileVersion);
        Logger.log(Logger.INFO_LEVEL_3 + String.format("Importing data from %s using file version v%s", file.getAbsolutePath(), fileVersion));
        List<String> MinuteData = GetDataSection.getMinuteDataRows(GetFileContent.getFileContentAsList(GetFile.getPathFromFile(file)));
        Map<Date, List<Integer>> data = new HashMap<>();
        for (String item : MinuteData) {
            String[] str = item.split(";");
            List<String> values = Arrays.asList(str);

            if(values.size() == positions.get(4)) {
                try {
                    DateFormat formatter = new SimpleDateFormat(DATEFORMAT);
                    Date d = formatter.parse(values.get(2));

                    List<Integer> valuesEach = new ArrayList<>();
                    int consPac = Integer.parseInt(values.get(positions.get(0)));
                    int consYieldDay = Integer.parseInt(values.get(positions.get(1)));
                    int Pac = Integer.parseInt(values.get(positions.get(2)));
                    int yieldDay = Integer.parseInt(values.get(positions.get(3)));
                    int ownConsumption = Math.min(consPac, Pac);

                    valuesEach.add(consPac);
                    valuesEach.add(consYieldDay);
                    valuesEach.add(Pac);
                    valuesEach.add(yieldDay);
                    valuesEach.add(ownConsumption);

                    data.put(d, valuesEach);
                } catch (ParseException e) {
                    //This should never happen unless you are somehow before 1970 or after 2038 but THEN there are other more urgent problems, duh
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    //TODO remove due to relocation
    /**
     * Get all data from a .js file in the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format. Such file is usually found on a FTP-Backup server
     * @param file The file from which the data should be extracted
     * @return All data in the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     * @throws IOException If provided a bad file
     */
    public static Map<Date, List<Integer>> getAsMapFromJSFile(File file) throws IOException {
        Logger.log(Logger.INFO_LEVEL_3 + String.format("Importing data from %s", file.getAbsolutePath()));
        List<String> lines = GetFileContent.getFileContentAsList(GetFile.getPathFromFile(file));
        Map<Date, List<Integer>> data = new HashMap<>();
        for (String line : lines) {
            String alteredLine = StringUtils.substringBetween(line, "\"" , "\"");
            if(alteredLine != null) {
                alteredLine = alteredLine.replaceAll("\\|", ";");
                List<String> objects = Arrays.asList(alteredLine.split(";"));
                if(objects.size() >= 7) {
                    try {
                        DateFormat formatter = new SimpleDateFormat(DATEFORMAT);
                        Date d = formatter.parse(objects.get(0));

                        try {
                            List<Integer> valuesEach = new ArrayList<>();
                            int consPac = Integer.parseInt(objects.get(1));
                            int consYieldDay = Integer.parseInt(objects.get(3));
                            int Pac = Integer.parseInt(objects.get(4));
                            int yieldDay = Integer.parseInt(objects.get(7));
                            int ownConsumption = Math.min(consPac, Pac);

                            valuesEach.add(consPac);
                            valuesEach.add(consYieldDay);
                            valuesEach.add(Pac);
                            valuesEach.add(yieldDay);
                            valuesEach.add(ownConsumption);

                            data.put(d, valuesEach);
                        } catch (NumberFormatException ignored) {

                        }
                    } catch (ParseException ignored) {
                        Logger.warn(String.format("Unable to convert File %s", file.getName()));
                    }
                }
            }
        }
        return data;
    }
}
