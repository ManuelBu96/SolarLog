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
package de.mbussmann.solarlog.api.dataConversion;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This class is for converting data into a {@link JSONObject}. This might be useful if the data is needed outside of java for example.
 * @author Christoph Kohnen
 * @since 3.6.0
 */
public class ConvertJson {
    /**
     * Converts data from the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format into an {@link JSONObject}
     * @param map The data which should be converted
     * @return The data as {@link JSONObject}
     */
    @SuppressWarnings("unchecked")
    public static JSONObject convertMapToJson(Map<Date, List<Integer>> map) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<Date, List<Integer>> entry : map.entrySet()) {
            Date date = entry.getKey();
            List<Integer> integers = entry.getValue();
            Instant timestamp = date.toInstant();
            JSONArray values = new JSONArray();
            values.addAll(integers);
            jsonObject.put(timestamp, values);
        }
        return jsonObject;
    }
}
