package com.yang.test.java.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		int size = in.readableBytes();
		if (size > 0) {
			System.out.println("size：" + size);
			byte[] b = new byte[size];
			in.readBytes(b);

			out.add(new String(b));
		}
	}
}