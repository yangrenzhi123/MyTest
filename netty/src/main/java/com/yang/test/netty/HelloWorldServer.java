package com.yang.test.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class HelloWorldServer {

	public static void main(String[] args) throws Exception {
		testTcp();
	}
	
	public static void testTcp() {
		int port = 8080;

		EventLoopGroup bossGroup = new NioEventLoopGroup(2);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap sbs = new ServerBootstrap().group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port)).childHandler(new ChannelInitializer<SocketChannel>() {
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("decoder", new StringDecoder());
					ch.pipeline().addLast("encoder", new StringEncoder());
					ch.pipeline().addLast(new HelloWorldServerHandler());
				};
			}).option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, true);
			// 绑定端口，开始接收进来的连接
			ChannelFuture future = sbs.bind(port).sync();

			System.out.println("Server start listen at " + port);
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
    public static void testUdp() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new UdpServerHandler());
            Channel channel = bootstrap.bind(8080).sync().channel();
            channel.closeFuture().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
	
	private static class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
		
		
        protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
            // 因为Netty对UDP进行了封装，所以接收到的是DatagramPacket对象。
            String req = msg.content().toString(CharsetUtil.UTF_8);
            System.out.println(req);

            if ("hello!!!".equals(req)) {
                ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(
                        "结果：", CharsetUtil.UTF_8), msg.sender()));
            }
        }

		@Override
		protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
			System.out.println(1122);
			
            // 因为Netty对UDP进行了封装，所以接收到的是DatagramPacket对象。
            String req = msg.content().toString(CharsetUtil.UTF_8);
            System.out.println(req);

            if ("hello!!!".equals(req)) {
                ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(
                        "结果：", CharsetUtil.UTF_8), msg.sender()));
            }
		}
    }

}