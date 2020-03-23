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
 * NIO�����
 * NIO���򻺳��� ��ȡ���ݿ� buffer 
 *   ͨ��ѡ������Selector������������ͨ����Channel��
 * @author Administrator
 *
 */
public class NioServer {
	private Selector selector;
	
	public void init() throws Exception {
		
		selector = Selector.open(); //��ѡ����
		ServerSocketChannel serverChannel = ServerSocketChannel.open(); //��һ��ͨ��
		serverChannel.configureBlocking(false);
		serverChannel.bind(new InetSocketAddress(8888));  //ͨ���󶨼����˿�
		serverChannel.register(selector, SelectionKey.OP_ACCEPT); //ע������¼���ѡ������ѡ����
		while(true) {
			selector.select(); //�����ȴ��¼� ������ѯ���¼�
			//��ʱ�䷢����ȡ�����¼���Ϣ
			Iterator<SelectionKey> keys = selector.selectedKeys().iterator(); //�õ������¼���Ϣkey ÿ��key��Ӧһ������Ҳ���Ǽ���ÿ���������¼�
			while(keys.hasNext()) {
				SelectionKey key = keys.next();
				keys.remove();
				dispatch(key);
				
			}
		}
		
	}
	
	public void dispatch(SelectionKey key) throws Exception {
		
		if(key.isAcceptable()) { //���ͨ�����½�������ע����¼�
			register(key);
		}else if(key.isReadable()){ //���ͨ�����Խ��ж�ȡʱ����������ݶ�ȡ �����Ҫ�����ݽ���дӦ�ý����socketchannel������
			System.out.println("��ʼ����ɶ��¼���");
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
	        ServerSocketChannel channel = (ServerSocketChannel) key.channel();   //ͨ��key�ҵ���Ӧ��channel
	        try {
	            SocketChannel socketChannel = channel.accept(); 
	            socketChannel.configureBlocking(false);
	            System.out.println("�������ע��ɶ��¼�");
	            socketChannel.register(this.selector, SelectionKey.OP_READ);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }
	 
	 public static void main(String[] args) throws Exception {
		
		 NioServer nioServer = new NioServer();
		 nioServer.init();
		 System.out.println("��������������");
	}
}
