package com.erely.nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	/**
	 * �յ��ͻ�����Ϣ���Զ�����
	 *
	 * @param ctx
	 * @param msg
	 * @throws Exception
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		/**
		 * �� msg תΪ Netty �� ByteBuf �������� JDK �е� java.nio.ByteBuffer������ ButeBuf ���ܸ�ǿ�������
		 */
		ByteBuf buf = (ByteBuf) msg;
		/**
		 * readableBytes����ȡ�������ɶ��ֽ���,Ȼ�󴴽��ֽ����� �Ӷ��������� java.nio.ByteBuffer
		 * ʱ��ֻ��äĿ�Ĵ����ض���С���ֽ����飬���� 1024
		 */
		byte[] reg = new byte[buf.readableBytes()];
		/**
		 * readBytes�����������ֽ����鸴�Ƶ��½��� byte ������ Ȼ���ֽ�����תΪ�ַ���
		 */
		buf.readBytes(reg);
		String body = new String(reg, "UTF-8");
		System.out.println(Thread.currentThread().getName() + ",The server receive  order : " + body);

		/**
		 * �ظ���Ϣ copiedBuffer������һ���µĻ�����������Ϊ����Ĳ��� ͨ�� ChannelHandlerContext �� write
		 * ��������Ϣ�첽���͸��ͻ���
		 */
		String respMsg = "I am Server����Ϣ���� success!";
		ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
		ctx.write(respByteBuf);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		/**
		 * flush������Ϣ���Ͷ����е���Ϣд�뵽 SocketChannel �з��͸��Է���Ϊ��Ƶ���Ļ��� Selector ������Ϣ���� Netty ��
		 * write ��������ֱ�ӽ���Ϣд�� SocketChannel �У����� write ֻ�ǰѴ����͵���Ϣ�ŵ����ͻ��������У���ͨ������ flush
		 * �����������ͻ���������Ϣȫ��д�뵽 SocketChannel ��
		 */
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		/** �������쳣ʱ���ر� ChannelHandlerContext���ͷź���������ľ������Դ */
		ctx.close();
	}
}
