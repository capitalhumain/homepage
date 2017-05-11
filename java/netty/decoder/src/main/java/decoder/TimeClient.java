package decoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {
	public static void main(String[] args) throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup)
			 .option(ChannelOption.SO_KEEPALIVE, true)
			 .channel(NioSocketChannel.class)
			 .handler(new ChannelInitializer<SocketChannel>() {
				 @Override
				 public void initChannel(SocketChannel channel) {
					 channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
					 channel.pipeline().addLast(new StringDecoder());
					 channel.pipeline().addLast(new TimeClientHandler());
				 }
			 });
			System.out.println("Starting connect TimeServer");
			ChannelFuture f = b.connect("localhost", 8088).sync();
			f.channel().closeFuture().sync();
			System.out.println("Finish connect TimeServer");
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
