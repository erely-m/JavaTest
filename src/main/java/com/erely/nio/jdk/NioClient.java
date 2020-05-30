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
        socketChannel = SocketChannel.open(); // 通道打开
        socketChannel.configureBlocking(false);
        if (socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888))) { // 尝试连接
            socketChannel.register(selector, SelectionKey.OP_READ); // 连接成功注册可写事件
            System.out.println("连接成功准备写入数据");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello".getBytes());
            socketChannel.write(buffer);
            System.out.println("数据写入成功");
        } else {
            System.out.println("连接失败注册连接事件");
            socketChannel.register(selector, SelectionKey.OP_CONNECT); // 注册连接事件

        }
        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();
                SocketChannel sc = (SocketChannel) key.channel();
                // OP_CONNECT 两种情况，链接成功或失败这个方法都会返回true
                if (key.isConnectable()) {
                    // 由于非阻塞模式，connect只管发起连接请求，finishConnect()方法会阻塞到链接结束并返回是否成功
                    // 另外还有一个isConnectionPending()返回的是是否处于正在连接状态(还在三次握手中)
                    if (socketChannel.finishConnect()) {
                        System.out.println("连接成功，注册监听事件");
                        sc.register(selector, SelectionKey.OP_READ);
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        buffer.put("hello".getBytes());
                        buffer.flip();
                        socketChannel.write(buffer);
                        System.out.println("数据发送完成");
                        break;
                    } else {
                        // 链接失败，进程推出或直接抛出IOException
                        System.exit(1);
                    }
                }
                if (key.isReadable()) {
                    // 读取服务端的响应
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(buffer);
                    String content = "";
                    if (readBytes > 0) {
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        content += new String(bytes);
                    } else if (readBytes < 0) {
                        // 对端链路关闭
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
