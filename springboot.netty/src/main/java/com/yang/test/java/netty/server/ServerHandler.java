package com.yang.test.java.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) {
		System.out.println(ctx.channel().id());

		System.out.println("server receive message：" + msg);
		//ctx.channel().writeAndFlush("服务器收到信息：" + msg);
		
		ByteBuf resp = Unpooled.copiedBuffer(hexString2Bytes("551000A001020001218030093356D655"));
		ctx.channel().writeAndFlush(resp);
		
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

	public static byte[] hexString2Bytes(String hex) {
		if ((hex == null) || (hex.equals(""))) {
			return null;
		} else if (hex.length() % 2 != 0) {
			return null;
		} else {
			hex = hex.toUpperCase();
			int len = hex.length() / 2;
			byte[] b = new byte[len];
			char[] hc = hex.toCharArray();
			for (int i = 0; i < len; i++) {
				int p = 2 * i;
				b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
			}
			return b;
		}
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
}