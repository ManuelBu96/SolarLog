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
package de.mbussmann.solarlog.api.fileInteraction.tools;

/**
 * This class stores values for file attributes.
 * @author Christoph Kohnen
 * @since 3.5.0
 */
public class FileAttributes {
    /**
     * The path of the filetype attribute
     */
    public static final String fileType = "de.mbussmann.solarlog.SolarLogFile";
    /**
     * The path of the fileversion attribute
     */
    public static final String fileVersion = "de.mbussmann.solarlog.SolarLogFile.FileVersion";

    /**
     * The value of the fileversion attribute
     */
    public static final String fileVersionShouldBe = "2";
    /**
     * The value of the filetype attribute
     */
    public static final String fileTypeShouldBe = "true";
}
