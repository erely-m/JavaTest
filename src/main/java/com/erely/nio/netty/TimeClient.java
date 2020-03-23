package com.erely.nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {
	/**
	 * ʹ�� 3 ���߳�ģ�������ͻ���
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new MyThread()).start();
		}
	}

	static class MyThread implements Runnable {

		@Override
		public void run() {
			connect("127.0.0.1", 9999);
		}

		public void connect(String host, int port) {
			/** ���ÿͻ��� NIO �߳���/�� */
			EventLoopGroup group = new NioEventLoopGroup();
			try {
				/**
				 * Bootstrap �� ServerBootstrap ���̳�(extends)�� AbstractBootstrap
				 * �����ͻ��˸���������,����������,���������΢��ͬ������� Channel ����Ϊ NioSocketChannel Ȼ��Ϊ�����
				 * Handler������ֱ��ʹ�������ڲ��࣬ʵ�� initChannel ���� �����ǵ����� NioSocketChannel
				 * �ɹ����ڽ��г�ʼ��ʱ,������ChannelHandler���õ�ChannelPipeline�У����ڴ�������I/O�¼�
				 */
				Bootstrap b = new Bootstrap();
				b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
						.handler(new ChannelInitializer<SocketChannel>() {
							@Override
							public void initChannel(SocketChannel ch) throws Exception {
								ch.pipeline().addLast(new TimeClientHandler());
							}
						});

				/** connect�������첽���Ӳ���������ͬ������ sync �ȴ����ӳɹ� */
				ChannelFuture channelFuture = b.connect(host, port).sync();
				System.out.println(Thread.currentThread().getName() + ",�ͻ��˷����첽����..........");

				/** �ȴ��ͻ�����·�ر� */
				channelFuture.channel().closeFuture().sync();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				/** �����˳����ͷ�NIO�߳��� */
				group.shutdownGracefully();
			}
		}
	}
}
