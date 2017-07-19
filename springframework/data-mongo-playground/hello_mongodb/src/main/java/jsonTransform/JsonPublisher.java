package jsonTransform;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.json.JSONObject;

/**
 * Created by tzuyichao on 17/07/2017.
 */
public class JsonPublisher {
    protected VelocityEngine velocity;

    public JsonPublisher() {
        // init velocity
        // default resource loader is a file loader on the current directory
        velocity = new VelocityEngine();
        velocity.init();
    }

    public String publish(String templatePath, String jsonString) throws IOException {
        // translate json string to velocity context
        // (we only need to convey the properties of the root object)
        JSONObject jsonObj = new JSONObject(jsonString);
        VelocityContext context = new VelocityContext();
        for (String key : jsonObj.keySet()) {
            context.put(key, jsonObj.get(key));
        }

        Writer writer = new StringWriter();
        velocity.mergeTemplate(templatePath, "UTF-8", context, writer);
        writer.flush();

        return writer.toString();
    }

    public static void main(String args[]) {
        try {
            String str = "{ \"name\": \"Alice\", \"age\": 30, \"friends\": " +
                    "[ { \"name\":\"Bob\", \"age\":21 }, { \"name\":\"Carol\", \"age\":19 } ], " +
                    "\"address\": { \"streetAddress\": \"100 Wall Street\", \"city\": \"New York\" } }";
            String result = new JsonPublisher().publish("./src/main/resources/jsonTransform/template.vm", str);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
