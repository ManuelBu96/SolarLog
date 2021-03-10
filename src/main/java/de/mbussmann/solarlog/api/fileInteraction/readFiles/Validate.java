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
package de.mbussmann.solarlog.api.fileInteraction.readFiles;

import de.mbussmann.solarlog.api.fileInteraction.GetFile;
import de.mbussmann.solarlog.api.fileInteraction.tools.FileAttributes;
import de.mbussmann.solarlog.api.fileInteraction.tools.FileVersion;
import de.mbussmann.solarlog.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;
import java.util.List;

/**
 * This class includes functions to validate a .dat or data file
 * @author Christoph Kohnen
 * @since 0.0.1
 */
public class Validate {
    /**
     * Whether or not a .dat file is valid
     * @param file The file which should be checked
     * @return Whether or not it is valid
     * @throws IOException If provided a bad file
     */
    public static boolean isValidDatFile(File file) throws IOException {
        boolean valid = false;
        if(file.getName().contains(".dat") & file.getName().contains("backup_data") & file.canRead()) {
                if(FileVersion.isSupported(file)){
                        valid = true;
                }
        }
        return valid;
    }

    /**
     * Filter valid .dat files from a list
     * @param files The files which should be checked
     * @return All valid files inside the provided list
     */
    public static List<File> getValidDatFiles(List<File> files) {
        List<File> ValidFiles = new ArrayList<>();
        int i = 0;
        int i2 = 0;
        for (File file : files) {
            i++;
            Logger.logWithoutBreakup(Logger.INFO_LEVEL_3 + String.format("Validating file %s (%s of %s)", file, i, files.size()));
            try {
                if(isValidDatFile(file)) {
                    ValidFiles.add(file);
                    i2++;
                    Logger.logWithoutBreakup("    yes.\n");
                } else {
                    Logger.logWithoutBreakup("    no.\n");
                }
            } catch (IOException e) {
                Logger.logWithoutBreakup("    error.\n");
                Logger.warn(String.format("Cannot validate file %s", file.getName()));
            }
        }
        Logger.log(Logger.INFO_LEVEL_3 + String.format("Done. Checked %s found %s", i, i2));
        return ValidFiles;
    }

    /**
     * Whether or not a SolarLog file is valid
     * @param file The file which should be checked
     * @return Whether or not the provided file is valid
     * @throws IOException If provided a bad file
     */
    public static boolean isValidSolarLogFile(File file) throws IOException {
        if(file.getName().contains(".solarlog")) {
            UserDefinedFileAttributeView view = Files.getFileAttributeView(GetFile.getPathFromFile(file), UserDefinedFileAttributeView.class);
            ByteBuffer readBuffer = ByteBuffer.allocate(view.size(FileAttributes.fileType));
            view.read(FileAttributes.fileType, readBuffer);
            readBuffer.flip();
            String fileType = new String(readBuffer.array(), StandardCharsets.UTF_8);
            readBuffer = ByteBuffer.allocate(view.size(FileAttributes.fileVersion));
            view.read(FileAttributes.fileType, readBuffer);
            readBuffer.flip();
            String fileVersion = new String(readBuffer.array(), StandardCharsets.UTF_8);
            return fileType.equals(FileAttributes.fileTypeShouldBe) && fileVersion.equals(FileAttributes.fileVersion);
        } else {
            return false;
        }
    }
}
