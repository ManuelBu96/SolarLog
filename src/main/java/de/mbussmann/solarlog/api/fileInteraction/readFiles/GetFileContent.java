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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
/**
 * This class includes functions to get the content of a file.
 * @author Christoph Kohnen
 * @since 0.0.1
 */
public class GetFileContent {
    /**
     * Get the contents of a file as UTF-8 character encoding text as a whole
     * @param path The path of the file
     * @return The content as String
     * @throws IOException If a bad file is provided
     */
    public static String getFileContentAsString(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    /**
     * Get the contents of a file as UTF-8 character encoding per line
     * @param path The path of the file
     * @return The content as a list of lines as string
     * @throws IOException If a bad file is provided
     */
    public static List<String> getFileContentAsList(Path path) throws IOException {
        return Arrays.asList(getFileContentAsString(path).split("\n"));
    }
}
