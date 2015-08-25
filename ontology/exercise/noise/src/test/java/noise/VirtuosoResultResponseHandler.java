package noise;

import java.util.List;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;

public class VirtuosoResultResponseHandler extends AbstractResponseHandler<List<String> > {
    
    public List<String> handleEntity(HttpEntity entity) throws IOException {
        return null;
    }
}
