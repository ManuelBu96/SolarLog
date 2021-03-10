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
package me.meloni.SolarLogAPI.FTPServerInteraction;

import me.meloni.SolarLogAPI.FileInteraction.WorkingDirectory;
import me.meloni.SolarLogAPI.Handling.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is used to request and download JS-files directly from an FTP-Server storing backups given the credentials.
 * @author Christoph Kohnen
 * @since 3.8.0
 */
public class GetFromFTPServer {
    /**
     *
     * @param host The hostname of the FTP-Backup server
     * @param user Your username
     * @param password Your password
     * @return List of files downloaded into the {@link WorkingDirectory}
     * @throws IOException Is thrown when the FTP-Server is unreachable or the files can't be saved to your file system
     */
    public static List<File> getJSFilesFromFTPServer(String host, String user, String password) throws IOException {
        FTPClient ftp = new FTPClient();
        Logger.log(Logger.INFO_LEVEL_2 + String.format("Connecting to FTP Server at %s...", host));
        ftp.connect(host);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }
        Logger.log(Logger.INFO_LEVEL_2 + String.format("Connected. Logging in with user %s...", user));
        ftp.login(user, password);
        Logger.log(Logger.INFO_LEVEL_2 + "Logged in. Retrieving files to download...");
        FTPFile[] files = ftp.listFiles();
        List<FTPFile> minuteFiles = new ArrayList<>();
        for (FTPFile file : files) {
            if(file.getName().startsWith("min") && !(file.getName().contains("day") || file.getName().contains("cur"))) {
                minuteFiles.add(file);
            }
        }

        List<File> jsFiles = new ArrayList<>();
        Logger.log(Logger.INFO_LEVEL_2 + String.format("Downloading %s files, this may take a while.", minuteFiles.size()));
        for (int i = 0; i < minuteFiles.size(); i++) {
            FTPFile minuteFile = minuteFiles.get(i);
            Logger.log(Logger.INFO_LEVEL_3 + String.format("Downloading %s (%s of %s)", minuteFile.getName(), i, minuteFiles.size()));
            File download = new File(WorkingDirectory.getDirectory(), minuteFile.getName());
            FileOutputStream out = new FileOutputStream(download);
            ftp.retrieveFile(minuteFile.getName(),out);
            jsFiles.add(download);
        }
        return jsFiles;
    }
}
