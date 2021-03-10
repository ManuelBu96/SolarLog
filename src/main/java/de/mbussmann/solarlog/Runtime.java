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
package de.mbussmann.solarlog;

import de.mbussmann.solarlog.logging.Logger;

/**
 * This class is called when the jar package is executed via runtime.
 * @author Christoph Kohnen
 * @since 0.0.0
 */
public class Runtime {
    /**
     * This function is called, when the jar package is executed via runtime.
     * It only displays a message that this API is not intended for direct execution.
     * @param args arguments passed by runtime
     */
    public static void main(String[] args) {
        Logger.info("Oh no, looks like you tried to run this package inside the commandline. \nHowever this is not supposed to run but be used only as a dependency. \nSee https://github.com/ChaosMelone9/SolarLogAPI for more info.");
    }
}
