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

import de.mbussmann.solarlog.api.fileInteraction.WorkingDirectory;
import de.mbussmann.solarlog.logging.Logger;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * This class includes functions to get the content of a tar archive.
 * @author Christoph Kohnen
 * @since 1.0.0
 * @version 2
 */
public class GetFromTar {

    /**
     * Function to un-tar a tarball
     * @param inputFile Tarball of which the files should be extracted from
     * @param outputDir The directory in which these files should be extracted
     * @return A {@link List} of files extracted from the tarball
     * @throws Exception If the process is not permitted to write into the folder or the tarball contains a bad file
     */
    private static List<File> unTar(final File inputFile, final File outputDir) throws Exception {
        Logger.log(Logger.INFO_LEVEL_3 + String.format("UnTaring %s to dir %s.", inputFile.getAbsolutePath(), outputDir.getAbsolutePath()));

        final List<File> unTaredFiles = new LinkedList<>();
        final InputStream is = new FileInputStream(inputFile);
        final TarArchiveInputStream debInputStream = (TarArchiveInputStream) new ArchiveStreamFactory().createArchiveInputStream("tar", is);
        TarArchiveEntry entry;
        while ((entry = (TarArchiveEntry)debInputStream.getNextEntry()) != null) {
            final File outputFile = new File(outputDir, entry.getName());
            if (entry.isDirectory()) {
                Logger.log(Logger.INFO_LEVEL_3 + String.format("Attempting to write output directory %s.", outputFile.getAbsolutePath()));
                if (!outputFile.exists()) {
                    Logger.log(Logger.INFO_LEVEL_3 + String.format("Attempting to create output directory %s.", outputFile.getAbsolutePath()));
                    if (!outputFile.mkdirs()) {
                        throw new IllegalStateException(String.format("Couldn't create directory %s.", outputFile.getAbsolutePath()));
                    }
                }
            } else {
                if(!outputFile.toPath().normalize().startsWith(outputDir.toPath())) {
                    throw new Exception("Bad Zip Entry!");
                }
                if(!outputFile.exists()) {
                    final OutputStream outputFileStream = new FileOutputStream(outputFile);
                    IOUtils.copy(debInputStream, outputFileStream);
                    outputFileStream.close();
                }
            }
            unTaredFiles.add(outputFile);
        }
        debInputStream.close();

        return unTaredFiles;
    }

    /**
     * Function to unGZip a file
     * @param inputFile The GZip archive which should be unGZipped
     * @param outputDir The directory in which the files should be extracted
     * @return A list of extracted files
     * @throws Exception If provided a bad file
     */
    private static File unGzip(final File inputFile, final File outputDir) throws Exception {
        Logger.log(Logger.INFO_LEVEL_3 + String.format("UnGZipping %s to dir %s.", inputFile.getAbsolutePath(), outputDir.getAbsolutePath()));

        final File outputFile = new File(outputDir, inputFile.getName().substring(0, inputFile.getName().length() - 3));
        if(!outputFile.toPath().normalize().startsWith(outputDir.toPath())) {
            throw new Exception("Bad Zip Entry!");
        }

        if(!outputFile.exists()) {
            final GZIPInputStream in = new GZIPInputStream(new FileInputStream(inputFile));
            final FileOutputStream out = new FileOutputStream(outputFile);

            IOUtils.copy(in, out);

            in.close();
            out.close();
        }
        return outputFile;
    }

    /**
     * Extract all files from a tar-archive and return valid .dat-files
     * @param tar The GZipped tarball from which the files should be extracted
     * @return All valid .dat-files inside the GZipped tarball
     * @throws Exception If provided a bad file or the GZipped tarball contains a bad file
     */
    public static List<File> getValidDatFilesFromTarArchive(File tar) throws Exception {
        String tarDirectory = FilenameUtils.removeExtension(FilenameUtils.removeExtension(tar.getName()));
        File outputDirectory = new File(WorkingDirectory.getDirectory(), tarDirectory);
        if(!outputDirectory.exists()){
            boolean b = outputDirectory.mkdir();
            if(!b) {
                throw new ArchiveException("cant create directory");
            }
        }
        return Validate.getValidDatFiles(unTar(unGzip(tar,outputDirectory),outputDirectory));
    }

    /**
     * Extract all files from a tar-archives and return valid .dat-files
     * @param tars The GZipped tarballs from which the files should be extracted
     * @return All valid .dat-files inside the GZipped tarballs
     * @throws Exception If provided a bad file or the GZipped tarballs contains a bad file
     */
    public static List<File> getValidDatFilesFromTarArchives(List<File> tars) throws Exception {
        List<File> validFiles = new ArrayList<>();
        for (File tar : tars) {
            Logger.log(Logger.INFO_LEVEL_3 + String.format("Extracting data from %s", tar.getAbsolutePath()));
            validFiles.addAll(getValidDatFilesFromTarArchive(tar));
        }
        return validFiles;
    }
}
