package com.utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({
        "classpath:QA.properties" // mention the property file name
})
public interface Environment extends Config {

    public static Environment value = ConfigFactory.create(Environment.class);

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

    //******************** REDIS ********************
    @Key("redis.host")
    String getRedisHost();

    @Key("redis.port")
    int getRedisPort();

    @Key("redis.auth")
    String getRedisAuth();

    //******************** SERVER ********************
    @Key("server.host")
    String getServerHost();

    @Key("server.user")
    String getServerUser();

    @Key("server.password")
    String getServerPassword();

    //******************** Cron ********************
    @Key("cron.build.command")
    String getBuildCommand();

    @Key("cron.env")
    String getCronEnv();
}