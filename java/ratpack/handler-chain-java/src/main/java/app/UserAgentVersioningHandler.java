package app;

import java.util.Map;
import java.util.HashMap;

import ratpack.registry.Registry;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import static ratpack.jackson.Jackson.json;

public class UserAgentVersioningHandler implements Handler {
    private static final String ERROR_MSG = "Unsupported User Agent";

    enum ClientVersion {
        V1("Microservice Client v1.0"),
        V2("Microservice Client v2.0"),
        V3("Microservice Client v3.0");

        String versionString;
        ClientVersion(String versionString) {
            this.versionString = versionString;
        }

        static ClientVersion fromString(String versionString) {
            for(ClientVersion val : ClientVersion.values()) {
                if(val.versionString.equals(versionString)) {
                    return val;
                }
            }
            return null;
        }
    }

    @Override
    public void handle(Context context) throws Exception {
        String userAgent = context.getRequest().getHeaders().get("User-Agent");
        ClientVersion clientVersion = ClientVersion.fromString(userAgent);
        if(null == clientVersion) {
            renderError(context);
        } else {
            context.next(Registry.single(ClientVersion.class, clientVersion));
        }
    }

    private static void renderError(Context context) throws Exception {
        context.getResponse().status(400);
        context.byContent(spec ->
            spec.json(() -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("error", true);
                    result.put("message", ERROR_MSG);
                    context.render(json(result));
                } ).html(() -> context.render("<h1>400 Bad Request</h1><br/><div>" + ERROR_MSG + "</div>" ))
                .noMatch(() -> context.render(ERROR_MSG) )
        );
    }
}
