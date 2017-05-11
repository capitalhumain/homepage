package delimiter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class DelimiterEchoServer {
    private int port;
    
    public DelimiterEchoServer(int port) {
    	this.port = port;
    }
    
    public void run() throws Exception {
    	EventLoopGroup bossGroup = new NioEventLoopGroup();
    	EventLoopGroup workerGroup = new NioEventLoopGroup();
    	
    	try {
    		ServerBootstrap b = new ServerBootstrap();
    		b.group(bossGroup, workerGroup)
    		 .channel(NioServerSocketChannel.class)
    		 .childHandler(new ChannelInitializer<SocketChannel>() {
    			 @Override
    			 public void initChannel(SocketChannel channel) throws Exception {
    				 ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
    				 channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
    				 channel.pipeline().addLast(new StringDecoder());
    				 channel.pipeline().addLast(new EchoServerHandler());
    			 }
    		 })
    		 .option(ChannelOption.SO_BACKLOG, 1024);
    		
    		ChannelFuture f = b.bind(port).sync();
    		f.channel().closeFuture().sync();
    	} finally {
    		bossGroup.shutdownGracefully();
    		workerGroup.shutdownGracefully();
    	}
    }
	
	public static void main(String[] args) throws Exception {
		System.out.println("Server Starting...");
		new DelimiterEchoServer(8088).run();
	}
}
