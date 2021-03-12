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
package de.mbussmann.solarlog.api.ftpServerInteraction;

import de.mbussmann.solarlog.api.fileInteraction.WorkingDirectory;
import de.mbussmann.solarlog.logging.Logger;

import org.apache.commons.net.ftp.FTPSClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is used to request and download JS-files directly from an SFTP-Server storing backups given the credentials.
 * @author Christoph Kohnen, Manuel Bußmann
 * @since 3.8.0
 * @
 */

public class GetFromSFTPServer {
    /**
     *
     * @param host The hostname of the FTP-Backup server
     * @param user Your username
     * @param password Your password
     * @return List of files downloaded into the {@link WorkingDirectory}
     * @throws IOException Is thrown when the FTP-Server is unreachable or the files can't be saved to your file system
     */
    @SuppressWarnings("unchecked")
    public static List<File> getJSFilesFromSFTPServer(String host, String user, String password) throws IOException {
        FTPSClient sftp = new FTPSClient();
        Logger.log(Logger.INFO_LEVEL_2 + String.format("Connecting to FTP Server at %s...", host));
        sftp.connect(host);
        int reply = sftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            sftp.disconnect();
            throw new IOException("Exception in connecting to SFTP Server");
        }
        Logger.log(Logger.INFO_LEVEL_2 + String.format("Connected. Logging in with user %s...", user));
        sftp.login(user, password);
        Logger.log(Logger.INFO_LEVEL_2 + "Logged in. Retrieving files to download...");
        FTPFile[] files = sftp.listFiles();
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
            sftp.retrieveFile(minuteFile.getName(),out);
            jsFiles.add(download);
        }
        return jsFiles;
    }
}
