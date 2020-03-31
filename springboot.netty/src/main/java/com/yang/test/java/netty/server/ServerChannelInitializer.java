package com.yang.test.java.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel socketChannel) {
		ChannelPipeline pipeline = socketChannel.pipeline();
		// 解码编码
//		pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
		ByteBuf delimiter = Unpooled.copiedBuffer("2".getBytes());
		pipeline.addLast(new DelimiterBasedFrameDecoder(2048, delimiter)); //业务数据内容(去除分割符之后的数据)，不能超过2048(可配置)，否则报错。
		pipeline.addLast(new IdleStateHandler(0, 0, 180)); // 心跳
		pipeline.addLast(new PacketDecoder());
		pipeline.addLast(new ServerHandler());
		pipeline.addLast(new ServerHandler2());

		// https://blog.csdn.net/guo_xl/article/details/85933678
		// https://blog.csdn.net/zhushuai1221/article/details/79709591
		
		
//        pipeline.addLast(new HttpServerCodec());// http 编解码
//        pipeline.addLast("httpAggregator",new HttpObjectAggregator(512*1024)); // http 消息聚合器 512*1024为接收的最大contentlength
//        pipeline.addLast(new HttpRequestHandler());// 请求处理器
	}
}