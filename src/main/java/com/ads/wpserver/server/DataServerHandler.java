package com.ads.wpserver.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataServerHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger = LoggerFactory.getLogger(DataServerHandler.class);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		logger.info(ctx.channel().remoteAddress().toString() + " 客户端连接成功!");
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		super.channelUnregistered(ctx);
		logger.info(ctx.channel().remoteAddress().toString() + " 客户端断开连接！");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// ByteBuf buf = (ByteBuf)msg;
		// String str = this.getMessage(buf);
		String str = ((String) msg).trim();
		str += ",END";
		if (str != null && !"".equals(str)) {
			logger.info(ctx.channel().remoteAddress().toString() + ":" + str);
			/**
			 * 以SEND开头为有效数据
			 */
			if (str.startsWith("SEND") && str.endsWith("END")) {
				
			}
		} else {
			logger.info("没有数据...");
		}
		
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		super.handlerRemoved(ctx);
		logger.info(ctx.channel().remoteAddress().toString() + "连接已移除！");
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
		logger.info(ctx.channel().remoteAddress().toString() + "连接已关闭！");
	}

	public String getMessage(ByteBuf buf) {
		byte[] con = new byte[buf.readableBytes()];
		buf.readBytes(con);
		try {
			return new String(con, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
