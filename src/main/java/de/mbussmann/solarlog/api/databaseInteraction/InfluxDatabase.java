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
package de.mbussmann.solarlog.api.databaseInteraction;


/**
 * This is an interface to store information about an InfluxDB
 * @author Christoph Kohnen
 * @since 3.2.0
 */
public class InfluxDatabase {
    public final String url;
    public final String token;
    public final String bucket;
    public final String org;

    public InfluxDatabase(String url, String token, String bucket, String org) {
        this.url = url;
        this.token = token;
        this.bucket = bucket;
        this.org = org;
    }
}
