package com.yang.test.java.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel socketChannel) {
		// 解码编码
		// socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
		socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));

		ByteBuf delimiter = Unpooled.copiedBuffer("2".getBytes());
		socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(2048, delimiter));
		socketChannel.pipeline().addLast(new IdleStateHandler(0, 0, 180)); // 心跳
		socketChannel.pipeline().addLast(new PacketDecoder());
		socketChannel.pipeline().addLast(new ServerHandler());
		socketChannel.pipeline().addLast(new ServerHandler2());

		// https://blog.csdn.net/guo_xl/article/details/85933678
		// https://blog.csdn.net/zhushuai1221/article/details/79709591
	}
}