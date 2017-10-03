package com.komarov.travelFood;

import com.komarov.travelFood.to.Journey;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Никита on 14.09.2017.
 */
public class AnonymousClientPool {
    private static Map<String, Journey> clientPool = new HashMap<>();

    public synchronized static void setInClientPool(String ip, Journey journey) {
        clientPool.put(ip, journey);
    }

    public static Journey getJourney(String ip) {
        return clientPool.get(ip);
    }

    public static int getClientPoolSize() {
        return clientPool.size();
    }
}
