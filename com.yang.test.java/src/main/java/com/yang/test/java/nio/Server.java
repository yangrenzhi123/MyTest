package com.yang.test.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class Server implements Runnable {

	// 第一个端口
	private Integer port = 8099;
	// 第一个服务器通道
	private ServerSocketChannel serverSocketChannel;
	// 连接
	private SocketChannel socketChannel;
	// 选择器，主要用来监控各个通道的事件
	private Selector selector;

	public Server() {
		init();
	}

	public void init() {
		try {
			// 打开第一个服务器通道
			this.serverSocketChannel = ServerSocketChannel.open();
			// 告诉程序现在不是阻塞方式的
			this.serverSocketChannel.configureBlocking(false);
			// 获取现在与该通道关联的套接字
			this.serverSocketChannel.socket().bind(new InetSocketAddress("192.168.1.102", this.port));

			// 创建选择器
			this.selector = SelectorProvider.provider().openSelector();
			this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void accept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

		socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		// OP_READ用于读取操作的操作集位
		socketChannel.register(this.selector, SelectionKey.OP_READ);
	}

	public void read(SelectionKey key) throws Exception {
		ByteBuffer buf = ByteBuffer.allocate(512);

		// 通过选择键来找到之前注册的通道
		SocketChannel socketChannel = (SocketChannel) key.channel();

		try {
			// 从通道里面读取数据到缓冲区并返回读取字节数
			int count = socketChannel.read(buf);

			if (count == -1) {
				// 取消这个通道的注册
//				socketChannel.close();
				key.cancel();
				//System.out.println("关闭通道");
				return;
			}
		} catch (Exception e) {
			socketChannel.close(); // 通道未解除注册，导致死循环
			throw e;
		} finally {
			//System.out.println("关闭通道");
		}

		// 将数据从缓冲区中拿出来
		String input = new String(buf.array()).trim();

		//System.out.println("您的输入为：" + input);
	}

	public void run() {
		while (true) {
			try {
				this.selector.select();
				//System.out.println("开始处理");

				// 返回此选择器的已选择键集
				Iterator selectorKeys = this.selector.selectedKeys().iterator();
				while (selectorKeys.hasNext()) {
					SelectionKey key = (SelectionKey) selectorKeys.next();
					selectorKeys.remove();
					if (!key.isValid()) { // 选择键无效
						continue;
					}
					if (key.isAcceptable()) {
						//System.out.println("accept");
						accept(key);
					} else if (key.isReadable()) {
						//System.out.println("read");
						read(key);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		Thread thread = new Thread(server);
		thread.start();
	}
}