package com.yang.test.netty;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@SuppressWarnings("deprecation")
@Sharable
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter {

	KafkaProducer<String, String> producer;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//System.out.println("server channelRead..");
		//System.out.println(ctx.channel().remoteAddress() + "->Server :" + msg.toString());
		
		//this.getProducer().send(new ProducerRecord<String, String>("test", "hello, kafka"));
		ctx.write("server write" + msg);
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	public KafkaProducer<String, String> getProducer() {
		return producer;
	}

	public void setProducer(KafkaProducer<String, String> producer) {
		this.producer = producer;
	}
}