package com.yang.test.java.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel socketChannel) {
		// 解码编码
		//socketChannel.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
		socketChannel.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
		socketChannel.pipeline().addLast(new PacketDecoder());
		socketChannel.pipeline().addLast(new ServerHandler());
		
		
		//https://blog.csdn.net/guo_xl/article/details/85933678
		//https://blog.csdn.net/zhushuai1221/article/details/79709591
	}
}