package com.komarov.travelFood;

import com.komarov.travelFood.model.autorizedUser.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Никита on 20.11.2017.
 */
public class AutorizedClientPool {
    private static Map<String, User> clientPool = new HashMap<>();

    public synchronized static void setInClientPool(String ip, User user) {
        clientPool.put(ip, user);
    }

    public static User getClient(String ip) {
        return clientPool.get(ip);
    }

    public static int getClientPoolSize() {
        return clientPool.size();
    }
}
