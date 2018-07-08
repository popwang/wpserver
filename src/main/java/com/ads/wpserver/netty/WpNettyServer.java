package com.ads.wpserver.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ads.wpserver.util.NettyServerConfig;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@Component
public class WpNettyServer {
	
	private static Logger logger = LoggerFactory.getLogger(WpNettyServer.class);
	
	@Autowired
	private NettyServerConfig config;
	@Autowired
	private ApplicationContext ctx;

    public void starter() throws Exception {
    	int PORT = config.getServer().getPort();
    	int POOL_SIZE = config.getServer().getPoolSize();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .option(ChannelOption.SO_BACKLOG, POOL_SIZE)
             .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
             .handler(new LoggingHandler(LogLevel.ERROR))
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ChannelPipeline p = ch.pipeline();
                     ByteBuf delimiter = Unpooled.copiedBuffer(",END".getBytes());
                     p.addLast(new DelimiterBasedFrameDecoder(300, delimiter));
                     p.addLast(new StringDecoder());
                     p.addLast((DataServerHandler)ctx.getBean("dataServerHandler"));
                 }
             });
            ChannelFuture f = b.bind(PORT).sync();
            logger.info("服务已经启动，端口号 :"+PORT);
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
