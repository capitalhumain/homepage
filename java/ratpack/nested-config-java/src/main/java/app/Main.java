package app;

import ratpack.server.RatpackServerSpec;
import ratpack.server.ServerConfigBuilder;
import ratpack.server.ServerConfig;
import ratpack.registry.Registry;
import ratpack.handling.Context;
import ratpack.server.RatpackServer;
import static ratpack.jackson.Jackson.json;

import app.config.ApplicationConfig;

import java.nio.file.Paths;

/**
 * An exercise creates a Java version of Learning Ratpack Example 4-21
 *
 * Author: Dan Woods
 * Converter: TzuYi Chao
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        RatpackServer.start((RatpackServerSpec serverSpec) -> {
            serverSpec.serverConfig((ServerConfigBuilder builder) ->
                // Example: /etc/dbconfig.json
                // builder.baseDir(Paths.get("/etc"))
                //       .json("dbconfig.json")
                builder.json(Paths.get(ClassLoader.getSystemResource("dbconfig.json").toURI()))
                       .require("", ApplicationConfig.class)
            );
            serverSpec.handlers(chain ->
                chain.get("config", ctx -> {
                    ApplicationConfig applicationConfig = ctx.get(ApplicationConfig.class);
                    ctx.render(json(applicationConfig));
                })
            );
        });
    }
}
