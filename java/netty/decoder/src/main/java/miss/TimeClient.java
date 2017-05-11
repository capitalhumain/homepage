package miss;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
	
	
    public static void main(String[] args) throws Exception {
    	EventLoopGroup workerGroup = new NioEventLoopGroup();
    	
    	try {
    		Bootstrap b = new Bootstrap();
    		b.group(workerGroup)
    		 .channel(NioSocketChannel.class)
    		 .option(ChannelOption.SO_KEEPALIVE, true)
    		 .handler(new ChannelInitializer<SocketChannel>() {
    			@Override
    			public void initChannel(SocketChannel channel) {
    				channel.pipeline().addLast(new TimeClientHandler(100));
    			}
    		 });
    		ChannelFuture f = b.connect("localhost", 8088).sync();
    		f.channel().closeFuture().sync();
    	} finally {
    		workerGroup.shutdownGracefully();
    	}
    }
}
