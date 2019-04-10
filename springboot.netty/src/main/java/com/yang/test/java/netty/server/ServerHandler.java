package com.yang.test.java.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) {
		System.out.println(ctx.channel().id());

		System.out.println("server receive message：" + msg);
		ctx.channel().writeAndFlush("服务器收到信息：" + msg);
		// ctx.close(); //会主动关闭通道
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("channelActive>>>>>>>>");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().id() + "断开连接");
	}
}