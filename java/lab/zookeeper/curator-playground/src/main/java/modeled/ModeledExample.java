package modeled;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.TypeAndPath;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.async.AsyncCuratorFramework;
import org.apache.curator.x.async.modeled.JacksonModelSerializer;
import org.apache.curator.x.async.modeled.ModelSpec;
import org.apache.curator.x.async.modeled.ModeledFramework;
import org.apache.curator.x.async.modeled.ZPath;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ModeledExample {
    private static final String zkConnectString = "localhost:2181";
    private static final String FooPathFormat = "/zk-modeled/foo/{id}";

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(zkConnectString)
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();

        ZPath path = ZPath.parseWithIds(FooPathFormat);
        ModelSpec<Foo> fooModelSpec = ModelSpec.builder(path, JacksonModelSerializer.build(Foo.class)).build();

        ModeledFramework<Foo> fooClient = ModeledFramework.wrap(AsyncCuratorFramework.wrap(client), fooModelSpec);

        Foo foo1 = new Foo(UUID.randomUUID().toString(), "Foo No.1");
        fooClient.set(foo1)
            .thenAccept(dataPath -> System.out.println(dataPath));

        Thread.sleep(10000);
    }
}
