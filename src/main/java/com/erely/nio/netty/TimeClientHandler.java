package com.erely.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
	/**
	 * ���ͻ��˺ͷ���� TCP ��·�����ɹ�֮��Netty �� NIO �̻߳���� channelActive ����
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String reqMsg = "���ǿͻ��� " + Thread.currentThread().getName();
		byte[] reqMsgByte = reqMsg.getBytes("UTF-8");
		ByteBuf reqByteBuf = Unpooled.buffer(reqMsgByte.length);
		/**
		 * writeBytes����ָ����Դ��������ݴ��䵽������ ���� ChannelHandlerContext �� writeAndFlush
		 * ��������Ϣ���͸�������
		 */
		reqByteBuf.writeBytes(reqMsgByte);
		ctx.writeAndFlush(reqByteBuf);
	}

	/**
	 * ������˷���Ӧ����Ϣʱ��channelRead ���������ã��� Netty �� ByteBuf �ж�ȡ����ӡӦ����Ϣ
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "UTF-8");
		System.out.println(Thread.currentThread().getName() + ",Server return Message��" + body);
		ctx.close();
	}

	/**
	 * �������쳣ʱ����ӡ�쳣 ��־���ͷſͻ�����Դ
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		/** �ͷ���Դ */
		ctx.close();
	}
}
