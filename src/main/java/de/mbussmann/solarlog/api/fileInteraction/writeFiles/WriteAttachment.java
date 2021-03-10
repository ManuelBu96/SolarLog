package de.mbussmann.solarlog.api.fileInteraction.writeFiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;

/**
 * This class includes a function to write an attachment from a {@link InputStream} to the file system
 * @author Christoph Kohnen
 * @since 3.1.1
 */
public class WriteAttachment {
    /**
     * Writes an attachment from an {@link InputStream} to a specified location on the file system
     * @param inputStream The stream from which to extract the attachment
     * @param path The location, where the stream should end up
     * @throws IOException If the file does already exist
     */
    public static void write(InputStream inputStream, File path) throws IOException {
        if(!path.exists()) {
            FileOutputStream output = new FileOutputStream(path);
            byte[] buffer = new byte[4096];
            int byteRead;
            while ((byteRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, byteRead);
            }
            output.close();
        } else {
            throw new FileAlreadyExistsException(String.format("File %s already exists!", path));
        }
    }
}
