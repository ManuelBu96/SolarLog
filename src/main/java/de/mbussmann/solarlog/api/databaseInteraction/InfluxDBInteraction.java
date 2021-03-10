package de.mbussmann.solarlog.api.databaseInteraction;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import de.mbussmann.solarlog.api.SolarMap;

public class InfluxDBInteraction {
    InfluxDBClient db;
    private final String bucket;
    private final String org;
    public InfluxDBInteraction (String token, String bucket, String org, String url) {
        this.db = InfluxDBClientFactory.create(url, token.toCharArray());
        this.bucket = bucket;
        this.org = org;
    }

    public void write(SolarMap solarMap) {
        solarMap.getAsMap().forEach((inverter, inverterMap) -> inverterMap.forEach((date, stringIntegerMap) -> {
            Point point = Point.measurement(inverter.identifier).time(date.toInstant(), WritePrecision.S);
            stringIntegerMap.forEach(point::addField);
            try (WriteApi writeApi = db.getWriteApi()) {
                writeApi.writePoint(bucket, org, point);
            }
        }));
    }
}
