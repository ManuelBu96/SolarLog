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
package de.mbussmann.solarlog.logging;


/**
 * This class includes functions to log objects to some form of output.
 * @author Christoph Kohnen
 * @since 0.0.1
 */
public class Logger {
    /**
     * Reset to defaults for terminal output
     */
    public static final String ANSI_RESET = "\u001B[0m";
    /**
     * Color output black
     */
    public static final String ANSI_BLACK = "\u001B[30m";
    /**
     * Color output red
     */
    public static final String ANSI_RED = "\u001B[31m";
    /**
     * Color output green
     */
    public static final String ANSI_GREEN = "\u001B[32m";
    /**
     * Color output yellow
     */
    public static final String ANSI_YELLOW = "\u001B[33m";
    /**
     * Color output blue
     */
    public static final String ANSI_BLUE = "\u001B[34m";
    /**
     * Color output purple
     */
    public static final String ANSI_PURPLE = "\u001B[35m";
    /**
     * Color output cyan
     */
    public static final String ANSI_CYAN = "\u001B[36m";
    /**
     * Color output white
     */
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Prefix for level 1 logging
     */
    public static String INFO_LEVEL_1 = ANSI_PURPLE + " ===> ";
    /**
     * Prefix for level 2 logging
     */
    public static String INFO_LEVEL_2 = ANSI_GREEN + " >>> ";
    /**
     * Prefix for level  3 logging
     */
    public static String INFO_LEVEL_3 = ANSI_WHITE + "> ";

    /**
     * Prefix for warn logging
     */
    public static String WARN_PREFIX = ANSI_RED + "[WARNING] ";

    /**
     * Whether or not logging is enabled. Can be set with setEnabled()
     */
    private static boolean enabled = false;

    /**
     * Logs something without line breakup
     * @param o Desired output
     */
    public static void logWithoutBreakup(Object o) {
        if(enabled) {
         System.out.print(o + ANSI_RESET);
        }
    }

    /**
     * Sets the active status of logging. Defaults to off
     * @param enabled Whether or not logging should be on
     */
    public static void setEnabled(boolean enabled) {
        Logger.enabled = enabled;
    }

    /**
     * Option to colorize logging. Defaults to on
     * @param colored Whether or not logging should be colorized
     */
    public static void setColored(boolean colored) {
        if(colored) {
            Logger.INFO_LEVEL_1 = ANSI_PURPLE + " ===> ";
            Logger.INFO_LEVEL_2 = ANSI_GREEN + " >>> ";
            Logger.INFO_LEVEL_3 = ANSI_WHITE + "> ";
            Logger.WARN_PREFIX = ANSI_RED + "[WARNING] ";
        } else {
            Logger.INFO_LEVEL_1 = " ===> ";
            Logger.INFO_LEVEL_2 = " >>> ";
            Logger.INFO_LEVEL_3 = "> ";
            Logger.WARN_PREFIX = "[WARNING] ";
        }
    }

    /**
     * Logs something if enabled
     * @param o Desired output
     */
    public static void log(Object o) {
        if(enabled) {
            info(o);
        }
    }

    /**
     * Displays a warning message
     * @param o Desired warning message
     */
    public static void warn(Object o) {
        info(WARN_PREFIX + o);
    }

    /**
     * Displays a message
     * @param o Desired message
     */
    public static void info(Object o) {
        System.out.println(o + ANSI_RESET);
    }
}
