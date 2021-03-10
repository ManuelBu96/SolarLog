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
package de.mbussmann.solarlog.api.mailInteraction;

import de.mbussmann.solarlog.api.fileInteraction.WorkingDirectory;
import de.mbussmann.solarlog.api.fileInteraction.writeFiles.WriteAttachment;
import de.mbussmann.solarlog.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
/**
 * This class provides a way to extract an tar archive from a {@link Message}
 * @author Christoph Kohnen
 * @since 3.0.5
 */
public class GetTarFromMessage {
    /**
     * Extract an attachment out of a message. Only intended for the message to be one of the weekly backup mails.
     * Therefore it is not very stable when getting unfiltered input.
     * @param message This message should contain an attachment.
     * @return This is the file found in the {@link WorkingDirectory}
     * @throws MessagingException This is thrown when the message does not contain an attachment
     * @throws IOException This is thrown when the attachment is not compatible with the host file system
     */
    public static File getTarArchiveFromMessage(Message message) throws MessagingException, IOException {
        Multipart multiPart = (Multipart) message.getContent();

        for (int i = 0; i < multiPart.getCount(); i++) {
            MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
            if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                Logger.log(Logger.INFO_LEVEL_3 + String.format("Found attachment \"%s\"", part.getFileName()));

                File output = new File(WorkingDirectory.getDirectory(), part.getFileName());

                try {
                    WriteAttachment.write(part.getInputStream(), output);
                    return output;
                } catch (FileAlreadyExistsException e) {
                    return output;
                }
            }
        }
        return null;
    }
}
