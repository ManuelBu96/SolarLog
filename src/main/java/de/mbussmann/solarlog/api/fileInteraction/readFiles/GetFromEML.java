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
package me.meloni.SolarLogAPI.FileInteraction.ReadFiles;

import me.meloni.SolarLogAPI.Handling.Logger;
import me.meloni.SolarLogAPI.MailInteraction.GetTarFromMessage;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * This class provides functions to get tar archives from EML files.
 * @author Christoph Kohnen
 * @since 3.0.5
 */
public class GetFromEML {
    /**
     * Extract a tar archive from an EML file
     * @param emlFile The file of which the archive should be extracted
     * @return A tar archive in the working directory
     * @throws IOException If the process is not permitted to manipulate files inside the working directory
     * @throws MessagingException If no tar archive is contained within the file
     */
    public static File getTarFromEML(File emlFile) throws IOException, MessagingException {
        Properties props = System.getProperties();
        Session mailSession = Session.getDefaultInstance(props, null);
        InputStream source = new FileInputStream(emlFile);
        MimeMessage message = new MimeMessage(mailSession, source);
        return GetTarFromMessage.getTarArchiveFromMessage(message);
    }

    /**
     * Extract tar archives from EML files
     * @param emlFiles List of files of which the archives should be extracted
     * @return A list of tar archives in the working directory
     * @throws IOException If the process is not permitted to manipulate files inside the working directory
     */
    public static List<File> getTarsFromEMLS(List<File> emlFiles) throws IOException {
        List<File> tars = new ArrayList<>();
        int i = 0;
        for (File emlFile : emlFiles) {
            i++;
            Logger.log(Logger.INFO_LEVEL_3 + String.format("Extracting tars from %s (%s of %s)",emlFile, i, emlFiles.size()));
            try {
                tars.add(getTarFromEML(emlFile));
            } catch (MessagingException e) {
            Logger.warn(String.format("Cannot extract tar from %s", emlFile.getName()));
            }
        }
        return tars;
    }
}
