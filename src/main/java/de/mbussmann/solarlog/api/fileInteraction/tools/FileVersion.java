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
package de.mbussmann.solarlog.api.fileInteraction.tools;

import de.mbussmann.solarlog.api.dataConversion.FileInformation;
import de.mbussmann.solarlog.api.dataConversion.GetDataSection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class includes functions to determine whether a file is usable and currently supported and the formats of each version.
 * @author Christoph Kohnen
 * @since 1.0.0
 */
public class FileVersion {

    /**
     * Whether or not a .dat-file is supported to import
     * @param file The file which should be checked
     * @return Whether or not the file is supported
     * @throws IOException If a bad file is provided
     */
    public static boolean isSupported(File file) throws IOException {
        return getSupportedFileVersions().contains(getFileVersion(file));
    }

    /**
     * A list of all supported file versions
     * @return A list of all supported versions
     */
    public static List<String> getSupportedFileVersions(){
        List<String> fileVersion = new ArrayList<>();
        fileVersion.add("4.2.7");
        fileVersion.add("3.0.0");
        fileVersion.add("3.4.2");
        fileVersion.add("3.5.1");
        fileVersion.add("3.6.0");
        fileVersion.add("4.2.0");
        fileVersion.add("4.2.5");
        return fileVersion;
    }

    /**
     * Determine the file version of a .dat-file
     * @param file The file of which the version should be determined
     * @return The version of the file
     * @throws IOException if a bad file is provided
     */
    public static String getFileVersion(File file) throws IOException {
        return FileInformation.getBuildVersion(GetDataSection.getInfoRow(file));
    }

    /**
     * Get a position matrix of all supported file versions
     * @return a position matrix of all supported versions
     */
    public static Map<String, List<Integer>> getPositionMatrix() {
        Map<String, List<Integer>> matrix = new HashMap<>();
        List<Integer> version300 = new ArrayList<>();
        version300.add(6);
        version300.add(8);
        version300.add(15);
        version300.add(21);
        version300.add(39);
        matrix.put("3.0.0",version300);
        matrix.put("3.4.2",version300);
        List<Integer> version427 = new ArrayList<>();
        version427.add(6);
        version427.add(10);
        version427.add(17);
        version427.add(23);
        version427.add(212);
        matrix.put("4.2.7",version427);
        matrix.put("4.2.0",version427);
        matrix.put("4.2.5",version427);
        matrix.put("3.5.1",version427);
        matrix.put("3.6.0",version427);
        return matrix;
    }
}
