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

import de.mbussmann.solarlog.api.fileInteraction.tools.FileObject;
import de.mbussmann.solarlog.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This class includes the fileObject function which results in a object read from a file.
 * @author Christoph Kohnen
 * @since 0.0.2
 */
public class ReadFileObject {
    /**
     * Extract an {@link FileObject} from a SolarLog file
     * @param file The file in which the {@link FileObject} is stored
     * @return a {@link FileObject} extracted from the file
     * @throws IOException If provided a bad file
     */
    public static FileObject getFileObjectFromSolarLogFile(File file) throws IOException {
        if(!(file == null) && file.exists()) {
            Logger.log(Logger.INFO_LEVEL_2 + String.format("Reading file %s. This may take a while.", file));
            FileInputStream f = new FileInputStream(file);
            ObjectInputStream s = new ObjectInputStream(f);
            try {
                FileObject o = (FileObject) s.readObject();
                s.close();
                Logger.log(Logger.INFO_LEVEL_2 + "done.");
                return o;
            } catch (ClassNotFoundException e) {
                throw new IOException(String.format("%s is not an SolarLog file!", file.getName()));
            }
        } else {
         return null;
        }
    }
}
