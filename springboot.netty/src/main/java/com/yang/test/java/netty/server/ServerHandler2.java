package com.yang.test.java.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler2 extends SimpleChannelInboundHandler<Object> {

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) {
		System.out.println(123);
	}
}