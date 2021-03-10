package me.meloni.SolarLogAPI.FileInteraction.Tools;

import me.meloni.SolarLogAPI.Inverter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * DO NOT TOUCH
 * This class represents the object used to store data in permanent files. Changing it causes all old files to be incompatible.
 * @author ChaosMelone9
 * @version 3
 * @since 2.0.0
 */
public class FileObject implements Serializable {

    /**
     * This map contains the data and meta-information.
     * @since 1.0.0
     */
    private final Map<String, Object> object = new HashMap<>();

    /**
     * This constructor can form an FileObject from a data-map.
     * @since 1.0.0
     */
    public FileObject(Map<Inverter, Map<Date, Map<String, Integer>>> data) {
        this.object.put("data",data);
    }

    /**
     * Used to retrieve data in form of a data-map.
     * @since 2.0.0
     * @return SolarMap
     */
    @SuppressWarnings("unchecked")
    public Map<Inverter, Map<Date, Map<String, Integer>>> getData()
    {
        Object o = object.get("data");
        Map<Inverter, Map<Date, Map<String, Integer>>> map = new HashMap<>();
        if(o instanceof Map) {
            /*
            This is an unchecked operation. This CAN fail and produce a ClassCastException in runtime use, BUT if used as intended this should work.
             */
            map = (Map<Inverter, Map<Date, Map<String, Integer>>>) o;
        }
        return (map);
    }

    /**
     * Used to store meta-information.
     * @since 2.0.0
     */
    public void putInformation(String key, Object info) {
        object.put(key,info);
    }

    /**
     * Used to retrieve meta-information.
     * @since 2.0.0
     * @return Object
     */
    public Object getInformation(String key) {
        return object.get(key);
    }
}
