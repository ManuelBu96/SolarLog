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
package me.meloni.SolarLogAPI;

import me.meloni.SolarLogAPI.DataConversion.Entries;
import me.meloni.SolarLogAPI.DataConversion.GetStartOf;
import me.meloni.SolarLogAPI.FileInteraction.Tools.FileObject;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * This is intended to be the main interface for this api. It houses functions to do all features of this API with ease.
 * It contains enough functions to manipulate it too your likes.
 * Because it contains a {@link Map} storing the data, some functions are very similar and / or directly mirrored to it.
 * Please do not use this to store data permanently in any way. For that there is an {@link FileObject} which is intended for exactly that.
 * There is also functionality to export directly as {@link JSONObject}
 * @author Christoph Kohnen
 * @since 2.0.0
 */
@SuppressWarnings("unused")
public class InverterMap {
    /**
     * The main object to store data. This is according to the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     */
    private Map<Date, Map<String, Integer>> data = new HashMap<>();

    private final Inverter inverter;

    /**
     * Instantiates using the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     * @author Christoph Kohnen
     * @param map The data in {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     */
    public InverterMap(Map<Date, Map<String, Integer>> map, Inverter inverter) { this.data = map; this.inverter = inverter;}

    /**
     * Instantiates an empty {@link SolarMap}
     * @author Christoph Kohnen
     */
    public InverterMap(Inverter inverter) {
        this.inverter = inverter;
    }




    /**
     * Set all data in this {@link SolarMap} from a {@link Map} using the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     * @author Christoph Kohnen
     * @param map The map from which this {@link SolarMap} should be set
     */
    public void setFromMap(Map<Date, Map<String, Integer>> map) {
        data = map;
    }

    /**
     * Set values on a specific timestamp
     * @author Christoph Kohnen
     * @param timestamp The timestamp on which
     * @param values The values for that timestamp
     */
    public void setOnDate(Date timestamp, Map<String, Integer> values) {
        data.put(timestamp, values);
    }

    /**
     * Add all data from a {@link Map} using the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     * @author Cristoph Kohnen
     * @param map The map from which all data should be added
     */
    public void addFromMap(Map<Date, Map<String, Integer>> map) {
        map.forEach((date, values) -> data.putIfAbsent(date, values));
    }

    /**
     * Add all data from another {@link SolarMap}
     * @author Cristoph Kohnen
     * @param inverterMap The {@link SolarMap} from which all data should be added
     */
    public void addFromInverterMap(InverterMap inverterMap) {
        addFromMap(inverterMap.getAsMap());
    }

    /**
     * Same as addFromSolarMap() for consistency with {@link Map}
     * @author Cristoph Kohnen
     * @param inverterMap The {@link SolarMap} which should be merged into this
     */
    public void merge(InverterMap inverterMap) {
        addFromInverterMap(inverterMap);
    }



    /**
     * Get the {@link SolarMap} in {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     * @author Cristoph Kohnen
     * @return All data in the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     */
    public Map<Date, Map<String, Integer>> getAsMap() {
        return data;
    }

    /**
     * Get values from a specific timestamp
     * @author Cristoph Kohnen
     * @param timestamp The timestamp of which data should be retrieved
     * @return The values for the specified timestamp
     */
    public Map<String, Integer> getValuesOnDate(Date timestamp) {
        return data.get(timestamp);
    }

    /**
     * Get values from a specific timestamp
     * @author Cristoph Kohnen
     * @param timestamp The timestamp of which the value should be retrieved
     * @param value The index of the value that should be retrieved
     * @return The value on that timestamp
     */
    public Integer getValueOnDate(Date timestamp, String value) {
        return getValuesOnDate(timestamp).get(value);
    }


    /**
     * Get Only Data from a specific timeframe
     * @author Cristoph Kohnen
     * @param firstTimestamp The start of the timeframe
     * @param lastTimestamp The end of the timeframe
     * @return All data between the two specified timestamps
     */
    public InverterMap getInTimeframe(Date firstTimestamp, Date lastTimestamp) {
        Map<Date, Map<String, Integer>> map = new HashMap<>();
        data.forEach((date, values) -> {
            if (date.after(firstTimestamp) & date.before(lastTimestamp)) {
                map.put(date,values);
            }
        });
        return new InverterMap(map, this.inverter);
    }




    /**
     * Get on specific timestamp
     * @author Cristoph Kohnen
     * @param timestamp The timestamp of which data should be retrieved
     * @return The values for the specified timestamp
     */
    public Map<String, Integer> get(Date timestamp) {
        return data.get(timestamp);
    }

    /**
     * Get value on specific timestamp
     * @author Cristoph Kohnen
     * @param timestamp The timestamp of which the value should be retrieved
     * @param value The index of the value that should be retrieved
     * @return The value on that timestamp
     */
    public int getValue(Date timestamp, String value) {
        return get(timestamp).get(value);
    }

    /**
     * Whether or not a specific timestamp is included
     * @author Cristoph Kohnen
     * @param timestamp The timestamp that should be checked
     * @return Whether or not the specified timestamp is included
     */
    public boolean containsKey(Date timestamp) {
        return data.containsKey(timestamp);
    }

    /**
     * Whether or not a specific timestamp is included
     * @author Cristoph Kohnen
     * @param timestamp The timestamp that should be checked
     * @return Whether or not the specified timestamp is included
     */
    public boolean includes(Date timestamp) {
        return containsKey(timestamp);
    }

    /**
     * Whether or not a day is included
     * @author Cristoph Kohnen
     * @param day The day that should be checked
     * @return Whether or not the specified day is included
     */
    public boolean includesDay(Date day) {
        return includes(GetStartOf.day(day));
    }

    /**
     * Whether or not a day is included
     * @author Cristoph Kohnen
     * @param day The day that should be checked
     * @return Whether or not the specified day is included
     */
    public boolean includesDay(LocalDate day) {
        return includes(GetStartOf.day(day));
    }

    /**
     * Whether or not a month is included
     * @author Cristoph Kohnen
     * @param month The month that should be checked
     * @return Whether or not the specified month is included
     */
    public boolean includesMonth(YearMonth month) {
        for (Date date : Entries.getDaysPerMonth(month)) {
            if(includesDay(date)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Whether or not a year is included
     * @author Cristoph Kohnen
     * @param year The year that should be checked
     * @return Whether or not the specified year is included
     */
    public boolean includesYear(Year year) {
        for (YearMonth month : Entries.getMonthsPerYear(year)) {
            if (includesMonth(month)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The number of all stored timestamps
     * @author Cristoph Kohnen
     * @return The amount of all stored timestamps
     */
    public int size() {
        return data.size();
    }

    /**
     * Whether or not the map is empty
     * @author Cristoph Kohnen
     * @return Whether or not the {@link SolarMap} is empty
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    public Inverter getInverter() {
        return inverter;
    }

    /**
     * Clears the {@link SolarMap}
     * @author Cristoph Kohnen
     */
    public void clear() {
        data.clear();
    }

    /**
     * Get values as collection
     * @author Cristoph Kohnen
     * @return All values as a {@link Collection}
     */
    public Collection<Map<String, Integer>> values() {
        return data.values();
    }

    /**
     * Overrides toString() to something human readable
     * @author Cristoph Kohnen
     * @return Useful information to string
     */
    @Override
    public String toString() {
        return data.toString();
    }

    /**
     * Same as getAsMap().forEach()
     * @author Cristoph Kohnen
     */
    public void forEach(BiConsumer<Date, Map<String, Integer>> consumer) {
        data.forEach(consumer);
    }

    /**
     * Compares the contents of two SolarMaps
     * @author Cristoph Kohnen
     * @return Whether or not the data is the same as of another {@link SolarMap}
     */
    public boolean equals(SolarMap solarMap) {
        return data.equals(solarMap.getAsMap());
    }
}
