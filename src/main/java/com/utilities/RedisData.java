package com.utilities;

import redis.clients.jedis.Jedis;

import java.util.Map;

public class RedisData {

    Jedis jedis;

    public RedisData() {

        jedis = new Jedis(Environment.value.getRedisHost(), Environment.value.getRedisPort());
        jedis.auth(Environment.value.getRedisAuth());
        jedis.select(0);
    }


    public String getString(String key) {
        return jedis.get(key);
    }


    public Map<String, String> getHash(String key) {
        return jedis.hgetAll(key);
    }

    public String getHash(String key, String value) {
        return jedis.hgetAll(key).get(value);
    }
}