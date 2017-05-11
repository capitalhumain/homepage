package delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class DelimiterEchoClient {
	public static void main(String[] args) throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup)
			 .option(ChannelOption.TCP_NODELAY, true)
			 .channel(NioSocketChannel.class)
			 .handler(new ChannelInitializer<SocketChannel>() {
				 @Override
				 public void initChannel(SocketChannel channel) throws Exception {
					 ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
					 channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
					 channel.pipeline().addLast(new StringDecoder());
					 channel.pipeline().addLast(new EchoClientHandler());
				 }
			 });
			ChannelFuture f = b.connect("localhost", 8088).sync();
			f.channel().closeFuture().await(); //.sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}
