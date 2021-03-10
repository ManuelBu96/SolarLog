package de.mbussmann.solarlog.api.fileInteraction.writeFiles;

import de.mbussmann.solarlog.api.fileInteraction.GetFile;
import de.mbussmann.solarlog.api.fileInteraction.tools.FileAttributes;
import de.mbussmann.solarlog.api.fileInteraction.tools.FileObject;
import de.mbussmann.solarlog.logging.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;

/**
 * This class includes a function to write an {@link FileObject} to a specified file.
 * @author Christoph Kohnen
 * @since 0.0.2
 */
public class WriteFileObject {
    /**
     * Function to write a {@link FileObject} to a {@link File}
     * @param file The {@link File} the {@link FileObject} should be written to
     * @param object The {@link FileObject} desired to write to the file system
     * @throws IOException If the process is not permitted to modify the {@link File}
     */
    public static void write(File file, FileObject object) throws IOException {
        UserDefinedFileAttributeView view = Files.getFileAttributeView(GetFile.getPathFromFile(file), UserDefinedFileAttributeView.class);
        Logger.log(Logger.INFO_LEVEL_2 + String.format("Writing file %s. This may take a while.", file));
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(object);
        s.flush();
        s.close();
        byte[] bytes = FileAttributes.fileTypeShouldBe.getBytes(StandardCharsets.UTF_8);
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        view.write(FileAttributes.fileType, writeBuffer);
        bytes = FileAttributes.fileVersionShouldBe.getBytes(StandardCharsets.UTF_8);
        writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        view.write(FileAttributes.fileVersion, writeBuffer);
        Logger.log(Logger.INFO_LEVEL_2 + "done.");
    }
}
