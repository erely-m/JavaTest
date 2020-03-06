package com.erely.netty.client;

import java.io.*;
import java.net.Socket;

public class SocketTest {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9000);
        InputStream in = socket.getInputStream();
        OutputStream  out = socket.getOutputStream();
        out.write("TEST".getBytes());
        byte[] b = new byte[1024];

        while (in.read(b) > 0) {
            System.out.println(new String(b));
        }
        socket.close();
    }
}
