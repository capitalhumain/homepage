package app;

import ratpack.registry.Registry;
import ratpack.handling.Context;
import ratpack.server.RatpackServer;

/**
 * An exercise creates a Java version of Learning Ratpack Example 2.20
 * 
 * Author: Dan Woods
 * Converter: TzuYi Chao
 **/
public class Main {
    public static void main(String[] args) throws Exception {
        RatpackServer.start(serverSpec ->
            serverSpec.handlers(chain ->
                chain.prefix("api", apiChain -> {
                    apiChain.all(new UserAgentVersioningHandler());
                    apiChain.all((Context context) -> {
                        UserAgentVersioningHandler.ClientVersion clientVersion = null;
                        if(context instanceof Registry) {
                            clientVersion = context.get(UserAgentVersioningHandler.ClientVersion.class);
                        }
                        if(clientVersion == UserAgentVersioningHandler.ClientVersion.V1) {
                            context.render("V1 Model");
                        } else if(clientVersion == UserAgentVersioningHandler.ClientVersion.V2) {
                            context.render("V2 Model");
                        } else if(clientVersion == UserAgentVersioningHandler.ClientVersion.V3) {
                            context.render("V3 Model");
                        } else {
                            context.render("No Model");
                        }
                    });
                })
            )
        );
    }
}
