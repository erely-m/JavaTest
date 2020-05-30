package com.erely.nio.jdk;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NioClient {

    private SocketChannel socketChannel;
    private Selector selector = null;
    static Charset charset = Charset.forName("UTF-8");

    public void init() throws Exception {

        selector = Selector.open();
        socketChannel = SocketChannel.open(); // ͨ����
        socketChannel.configureBlocking(false);
        if (socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888))) { // ��������
            socketChannel.register(selector, SelectionKey.OP_READ); // ���ӳɹ�ע���д�¼�
            System.out.println("���ӳɹ�׼��д������");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello".getBytes());
            socketChannel.write(buffer);
            System.out.println("����д��ɹ�");
        } else {
            System.out.println("����ʧ��ע�������¼�");
            socketChannel.register(selector, SelectionKey.OP_CONNECT); // ע�������¼�

        }
        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                SocketChannel sc = (SocketChannel) key.channel();
                // OP_CONNECT ������������ӳɹ���ʧ������������᷵��true
                if (key.isConnectable()) {
                    // ���ڷ�����ģʽ��connectֻ�ܷ�����������finishConnect()���������������ӽ����������Ƿ�ɹ�
                    // ���⻹��һ��isConnectionPending()���ص����Ƿ�����������״̬(��������������)
                    if (socketChannel.finishConnect()) {
                        System.out.println("���ӳɹ���ע������¼�");
                        sc.register(selector, SelectionKey.OP_READ);
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.put("hello".getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                        System.out.println("���ݷ������");
                        break;
                    } else {
                        // ����ʧ�ܣ������Ƴ���ֱ���׳�IOException
                        System.exit(1);
                    }
                }
                if (key.isReadable()) {
                    // ��ȡ����˵���Ӧ
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(buffer);
                    String content = "";
                    if (readBytes > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        content += new String(bytes);
                    } else if (readBytes < 0) {
                        // �Զ���·�ر�
                        key.channel();
                        sc.close();
                    }
                    System.out.println(content);
                    key.interestOps(SelectionKey.OP_READ);
                }

            }
            break;
        }

    }

    public static void main(String[] args) throws Exception {
        while (true) {
            NioClient client = new NioClient();
            client.init();
            Thread.sleep(100);
        }
    }
}
