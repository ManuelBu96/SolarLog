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
package de.mbussmann.solarlog.api.solarLogInteraction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * This is used to request JSON values directly from an SolarLog. For this to work the JSOn interface must be turned on in the settings.
 * @author Christoph Kohnen
 * @since 3.3.0
 */
public class GetJsonFromSolarLog {
    /**
     * Return JSON content from the JSON interface of the SolarLog
     * @param url This is the url of your solarlog. This should end with "/getjp".
     * @return Response of the SolarLog as JSON object
     * @throws IOException Is thrown when the SolarLog is unreachable or the JSON interface is turned off
     */
    public static String getFromSolarLogInterface(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setDoOutput(true);

        String jsonInputString = "{\"801\":{\"170\":null}}";
        OutputStream outputStream = httpURLConnection.getOutputStream();
        byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
        outputStream.write(input, 0, input.length);

        BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
        httpURLConnection.disconnect();
        return response.toString();
    }
}
