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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class includes functions to only use lines in files which contain the wanted information.
 * @author Christoph Kohnen
 * @since 0.0.1
 */
public class GetDataSection {
    /**
     * Get the info row of a .dat file
     * @param file The file you want to get the info row from
     * @return The info row of the specified file
     * @throws IOException If provided a bad file
     */
    public static String getInfoRow(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        String info = null;
        if(scanner.hasNextLine()) {
            info = scanner.nextLine();
        }
        scanner.close();
        inputStream.close();
        return info;
    }

    /**
     * Get all lines containing data in five-minute steps in from all Lines
     * @param lines All lines of a .dat file
     * @return Every line which contains data
     */
    public static List<String> getMinuteDataRows(List<String> lines) {
        List<String> minuteData = new ArrayList<>();
        for (String datum : lines) {
            if(datum.startsWith("2;0")) {
                minuteData.add(datum);
            }
        }
        return minuteData;
    }
}
