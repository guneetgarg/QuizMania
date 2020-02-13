package com.utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.testng.Reporter;

@Config.Sources({
        "classpath:QA.properties" // mention the property file name
})
public interface Environment extends Config {

    String url();

    String username();

    String password();

    @Key("db.hostname")
    String getDBHostname();

    @Key("db.port")
    int getDBPort();

    @Key("db.username")
    String getDBUsername();

    @Key("db.password")
    String getDBPassword();

    @Key("redis.host")
    String getRedisHost();

    @Key("redis.port")
    int getRedisPort();

    @Key("redis.auth")
    String getRedisAuth();

    public static Environment value = ConfigFactory.create(Environment.class);

}