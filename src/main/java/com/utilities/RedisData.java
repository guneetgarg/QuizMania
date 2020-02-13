package com.utilities;

import redis.clients.jedis.Jedis;

public class RedisData {

    public RedisData() {

        String redisHost = "redis-14580.redis01.stg.pch.com";
        Integer redisPort = 14580;

        Jedis jedis = new Jedis(Environment.value.getRedisHost(), Environment.value.getRedisPort());
        jedis.auth(Environment.value.getRedisAuth());
        jedis.select(0);
    }


    public void getRedisData(){

    }

}