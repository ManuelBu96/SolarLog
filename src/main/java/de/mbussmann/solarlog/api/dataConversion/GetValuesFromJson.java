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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This class includes functions to extract all values from a JSON string found in the JSOn interface of a SolarLog
 * @author Christoph Kohnen
 * @since 3.3.0
 */
public class GetValuesFromJson {
    /**
     * The date format used by SolarLog
     */
    private static final String DATEFORMAT = "dd.MM.yy HH:mm:ss";

    /**
     * Get all values from one JSON string according to the manual
     * @param jsonInput The JSON string gotten from the SolarLog JSON interface
     * @return All values put into a map
     * @throws ParseException If the string is not JSON parsable
     */
    public static Map<String, String> getMapFromJsonString(String jsonInput) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        Object object = jsonParser.parse(jsonInput);
        JSONObject jsonObject1 = (JSONObject) object;
        JSONObject jsonObject2 = (JSONObject) jsonObject1.get("801");
        JSONObject jsonObject3 = (JSONObject) jsonObject2.get("170");

        Map<String, String> data = new HashMap<>();

        data.put("lastUpdateTime", jsonObject3.get("100").toString());
        data.put("Pac", jsonObject3.get("101").toString());
        data.put("Pdc", jsonObject3.get("102").toString());
        data.put("Uac", jsonObject3.get("103").toString());
        data.put("DC_voltage", jsonObject3.get("104").toString());
        data.put("yieldDay", jsonObject3.get("105").toString());
        data.put("yieldYesterday", jsonObject3.get("106").toString());
        data.put("yieldMonth", jsonObject3.get("107").toString());
        data.put("yieldYear", jsonObject3.get("108").toString());
        data.put("yieldTotal", jsonObject3.get("109").toString());
        data.put("consPac", jsonObject3.get("110").toString());
        data.put("consYieldDay", jsonObject3.get("111").toString());
        data.put("consYieldYesterday", jsonObject3.get("112").toString());
        data.put("consYieldMonth", jsonObject3.get("113").toString());
        data.put("consYieldYear", jsonObject3.get("114").toString());
        data.put("consYieldTotal", jsonObject3.get("115").toString());
        data.put("totalPower", jsonObject3.get("116").toString());

        return data;
    }

    /**
     * Extract all important values from a JSON string according to the manual
     * @param jsonInput The JSON string gotten from the SolarLog JSON interface
     * @return All values in the {@link Map}<{@link Date}, {@link List}<{@link Integer}>> format
     * @throws org.json.simple.parser.ParseException If the string is not JSON parsable
     * @throws java.text.ParseException If the timestamp is somehow incorrect
     */
    public static Map<Date, List<Integer>> getAsMap(String jsonInput) throws org.json.simple.parser.ParseException, java.text.ParseException {
        Map< String, String> data = getMapFromJsonString(jsonInput);

        DateFormat formatter = new SimpleDateFormat(DATEFORMAT);
        Date d = formatter.parse(data.get("lastUpdateTime"));

        List<Integer> values = new ArrayList<>();
        int consPac = Integer.parseInt(data.get("consPac"));
        int consYieldDay = Integer.parseInt(data.get("consYieldDay"));
        int Pac = Integer.parseInt(data.get("Pac"));
        int yieldDay = Integer.parseInt(data.get("yieldDay"));
        int ownConsumption = Math.min(Pac, consPac);

        values.add(consPac);
        values.add(consYieldDay);
        values.add(Pac);
        values.add(yieldDay);
        values.add(ownConsumption);

        Map<Date, List<Integer>> returnValue = new HashMap<>();
        returnValue.put(d, values);
        return returnValue;
    }
}
