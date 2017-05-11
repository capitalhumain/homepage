package delimiter;

import java.util.logging.Logger;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {
	private static final Logger logger = Logger.getLogger(EchoClientHandler.class.getName()); 

	private int counter;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		for(int i=0; i<100; i++) {
			ctx.writeAndFlush(Unpooled.copiedBuffer(("Message Send$_").getBytes()));
		}
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		String resp = (String) msg;
		//logger.info("Receive : " + resp);
		System.out.println(resp + " -> counter: " + ++counter);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.severe("Unexpected exception cuccured : " + cause.getMessage());
		ctx.close();
	}
}
