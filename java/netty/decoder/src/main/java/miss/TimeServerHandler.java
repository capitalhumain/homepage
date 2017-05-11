package miss;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
	private int counter;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	    ByteBuf buf = (ByteBuf) msg;
	    ByteBuf resp = null;
	    
	    try {
	    	byte[] req = new byte[buf.readableBytes()];
	    	buf.readBytes(req);
	    	String reqStr = new String(req, "UTF-8").substring(0, req.length - System.getProperty("line.separator").length());
	    	System.out.println("The time server receive order: " + reqStr + " ; the counter is : " + ++counter);
	    	String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(reqStr) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
	    	resp = Unpooled.copiedBuffer(currentTime.getBytes());
	    	ctx.writeAndFlush(resp);
	    } finally {
	    	ReferenceCountUtil.release(buf);
	    	ReferenceCountUtil.release(resp);
	    }
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.close();
	}
}
