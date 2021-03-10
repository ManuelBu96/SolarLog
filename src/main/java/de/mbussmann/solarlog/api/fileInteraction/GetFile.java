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
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class includes functions to get a {@link File} from {@link Path} and to get the {@link Path} of a {@link File}.
 * @author Christoph Kohnen
 * @since 0.0.1
 */
public class GetFile {
    /**
     * Return a {@link File} from a {@link Path}
     * @param path The {@link Path} of the {@link File}
     * @return The {@link Path} converted to a {@link File}
     */
    public static File getFileFromPath(String path) {
        return new File(path);
    }

    /**
     * Return a {@link Path} from a {@link File}
     * @param file The {@link File} for the {@link Path}
     * @return The {@link File} converted to a {@link Path}
     */
    public static Path getPathFromFile(File file) {
        return Paths.get(file.getPath());
    }
}
