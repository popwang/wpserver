package com.ads.wpserver.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ads.wpserver.mysql.model.WpData;
import com.ads.wpserver.mysql.model.WpOrderBuffer;
import com.ads.wpserver.mysql.service.WpDataService;
import com.ads.wpserver.mysql.service.WpDataService2;
import com.ads.wpserver.mysql.service.WpOrderBufferService;
import com.ads.wpserver.util.ObjectUtil;

@Component
public class DataServerHandler extends ChannelInboundHandlerAdapter {
	private static Logger logger = LoggerFactory.getLogger(DataServerHandler.class);

	@Autowired
	private WpDataService2 wpDataService2;
	@Autowired
	private WpDataService wpDataService;
	@Autowired
	private WpOrderBufferService wpOrderBufferService;

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
		String str = ((String) msg).trim();
		str += ",END";
		if (str != null && !"".equals(str)) {
			logger.info(ctx.channel().remoteAddress().toString() + ":" + str);
			/**
			 * 以SEND开头为有效数据
			 */
			if (str.startsWith("SEND") && str.endsWith("END")) {
				String[] strs = str.split(",");
				/**
				 * 数据格式正确，解析入库
				 */
				if (strs.length == 18) {
					WpData wp = ObjectUtil.stringToObject(strs);
					wpDataService2.saveWpData(wp);
					wpDataService.saveWpData(wp);
				}
				/**
				 * 查询有没有待发送的指令
				 */
				WpOrderBuffer order = wpOrderBufferService.selectOrderBufferByName(strs[2]);
				if(order!=null){
					System.out.println();
					ByteBuf buf = Unpooled
							.copiedBuffer(new String(order.getV_order_content().getBytes("utf-8"), "GBK").getBytes());
					ctx.write(buf);
					wpOrderBufferService.updateOrderBufferFlag(strs[2]);
					logger.error("设备编号为：" + order.getV_equipment_name() + "的第" + order.getI_id() + "号指令已发送成功！");
				}
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
