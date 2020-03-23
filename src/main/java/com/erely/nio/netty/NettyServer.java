package com.erely.nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	
	public void init() throws Exception {
		//���������߳���
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.SO_BACKLOG, 1024)
        .childHandler(new ChildChannelHandler());
        
        ChannelFuture f = b.bind(9999).sync();
        
        System.out.println(Thread.currentThread().getName() + ",��������ʼ�����˿ڣ��ȴ��ͻ�������.........");
        f.channel().closeFuture().sync();
	}
	public static void main(String[] args) throws Exception {
		new NettyServer().init();
	}
}
