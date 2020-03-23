package com.erely.nio.jdk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO服务端
 * NIO面向缓冲区 读取数据块 buffer 
 *   通过选择器（Selector）来监听各个通道（Channel）
 * @author Administrator
 *
 */
public class NioServer {
	private Selector selector;
	
	public void init() throws Exception {
		
		selector = Selector.open(); //打开选择器
		ServerSocketChannel serverChannel = ServerSocketChannel.open(); //打开一个通道
		serverChannel.configureBlocking(false);
		serverChannel.bind(new InetSocketAddress(8888));  //通道绑定监听端口
		serverChannel.register(selector, SelectionKey.OP_ACCEPT); //注册监听事件至选择器上选择器
		while(true) {
			selector.select(); //阻塞等待事件 可设置询问事件
			//有时间发生获取所有事件信息
			Iterator<SelectionKey> keys = selector.selectedKeys().iterator(); //得到所有事件信息key 每个key对应一个渠道也就是监听每个渠道的事件
			while(keys.hasNext()) {
				SelectionKey key = keys.next();
				keys.remove();
				dispatch(key);
				
			}
		}
		
	}
	
	public void dispatch(SelectionKey key) throws Exception {
		
		if(key.isAcceptable()) { //如果通道是新建立的则注册读事件
			register(key);
		}else if(key.isReadable()){ //如果通道可以进行读取时间则进行数据读取 如果需要对数据进行写应该将这个socketchannel存起来
			System.out.println("开始处理可读事件。");
			SocketChannel socketchannel = (SocketChannel) key.channel();
			ByteBuffer buffer1 = ByteBuffer.allocate(1024);
			socketchannel.read(buffer1);
			buffer1.flip();
			byte[] b = new byte[buffer1.remaining()];
			buffer1.get(b);
			System.out.println(new String(b));
			ByteBuffer buffer2 = ByteBuffer.allocate(1024);
			buffer2.put("ok".getBytes());
			buffer2.flip();
			socketchannel.write(buffer2);
		}
	}
	 private void register(SelectionKey key) {
	        ServerSocketChannel channel = (ServerSocketChannel) key.channel();   //通过key找到对应的channel
	        try {
	            SocketChannel socketChannel = channel.accept(); 
	            socketChannel.configureBlocking(false);
	            System.out.println("连接完成注册可读事件");
	            socketChannel.register(this.selector, SelectionKey.OP_READ);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	 
	 public static void main(String[] args) throws Exception {
		
		 NioServer nioServer = new NioServer();
		 nioServer.init();
		 System.out.println("启动服务器监听");
	}
}
