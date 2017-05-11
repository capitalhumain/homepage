package miss;

import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
	private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
	
	private byte[] req;
	private int count = 100;
	
	public TimeClientHandler(int count) {
		req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
		this.count = count;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		ByteBuf message = null;
		for(int i=0; i<count; i++) {
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		try {
			byte[] req = new byte[buf.readableBytes()];
			buf.readBytes(req);
			String reqStr = new String(req, "UTF-8");
			System.out.println("Now is body: " + reqStr);
		} finally {
			ReferenceCountUtil.release(buf);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		logger.warning("Unexpecting exception from downstream: " + cause.getMessage());
		ctx.close();
	}
}
