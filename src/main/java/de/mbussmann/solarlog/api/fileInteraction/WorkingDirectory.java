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
package me.meloni.SolarLogAPI.FileInteraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * This class provides a way to get a directory intended for short time data storage on the file system.
 * @author Christoph Kohnen
 * @since 3.1.2
 */
public class WorkingDirectory {
    /**
     * The WorkingDirectory as {@link File}.
     * Is instantiated blank and set upon first request.
     */
    private static File workingDirectory = null;

    /**
     * Request the directory. If it doesn't exist yet, it will be created following the name pattern "yyyy-MM-dd_HH-mm-ss".
     * @return the directory as {@link File}
     * @throws IOException If the process is not permitted to create a folder at the desired location
     */
    public static File getDirectory() throws IOException {
        if(workingDirectory == null) {
            Date time = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String run = dateFormat.format(time);
            workingDirectory = new File(Paths.get("").toAbsolutePath().toString(), run);
            if(!workingDirectory.mkdirs()) {
             throw new IOException(String.format("can't create %s", run));
            }
        }
        return workingDirectory;
    }

    /**
     * Clears the contents of the directory. The directory itself won't be deleted.
     * @throws IOException If the process is not permitted to delete files inside the directory
     */
    public static void clear() throws IOException {
        File file = getDirectory();
        for (File subFile : file.listFiles()) {
            if(subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                if(!subFile.delete()) {
                    throw new IOException("Can't delete " + subFile);
                }
            }
        }
    }

    /**
     * Sets the location of the working directory
     * @param directory The existing new location
     * @throws FileNotFoundException If the directory does not exist
     * @throws NotDirectoryException If the {@link File} is no directory
     */
    public static void setDirectory(File directory) throws FileNotFoundException, NotDirectoryException {
        if(directory.isDirectory()) {
            if(!directory.exists()) {
                throw new FileNotFoundException();
            } else workingDirectory = directory;
        } else throw new NotDirectoryException(String.format("%s is no directory.",directory.getName()));
    }

    /**
     * Method to delete a folder and its files
     * @param file The folder which should be deleted
     * @throws IOException If the process is not permitted to delete the file
     */
    private static void deleteFolder(File file) throws IOException {
        for (File subFile : file.listFiles()) {
            if(subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                if(!subFile.delete()) {
                    throw new IOException("Can't delete " + subFile.getName());
                }
            }
        }
        if(!file.delete()) {
            throw new IOException("Can't delete " + file.getName());
        }
    }
}
