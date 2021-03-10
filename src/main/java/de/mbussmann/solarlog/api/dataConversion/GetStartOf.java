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
package me.meloni.SolarLogAPI.DataConversion;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * This class includes functions to get the start of something.
 * @author Christoph Kohnen
 * @since 2.0.0
 */
public class GetStartOf {
    /**
     * Get the start of a day as {@link Date}
     * @param date The timestamp you want to get the start of the day
     * @return The start of the day as {@link Date}
     */
    public static Date day(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Get the start of a day as {@link Date}
     * @param date The timestamp you want to get the start of the day
     * @return The start of the day as {@link Date}
     */
    public static Date day(Date date) {
        return Date.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
