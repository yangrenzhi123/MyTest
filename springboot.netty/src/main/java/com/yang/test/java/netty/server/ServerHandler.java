package com.yang.test.java.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) {
		System.out.println(ctx.channel().id());

		System.out.println("server receive message :" + msg);
		ctx.channel().writeAndFlush("yes server already accept your message" + msg);
		ctx.close();
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("channelActive>>>>>>>>");
	}
}