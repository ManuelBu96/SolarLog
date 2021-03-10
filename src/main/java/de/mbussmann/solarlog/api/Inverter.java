package de.mbussmann.solarlog.api;

import java.util.HashMap;
import java.util.Map;

public class Inverter {
    public String type;
    public String identifier;
    public int strings;
    public int function;
    public int functionType;
    public boolean temperature;

    public Inverter(String type, String identifier, int strings, int function, int functionType, boolean temperature) {
        this.type = type;
        this.identifier = identifier;
        this.strings = strings;
        this.function = function;
        this.functionType = functionType;
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("identifier", identifier);
        map.put("strings", String.valueOf(strings));
        map.put("function", String.valueOf(function));
        map.put("functionType", String.valueOf(functionType));
        map.put("temperature", String.valueOf(temperature));
        return map.toString();
    }
}
