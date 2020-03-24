package com.erely.nio.jdk;

import kafka.network.SocketServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class BIOTest {

    private static ArrayList<Socket> list = new ArrayList();
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        handler();
        while (true) {
            Socket client = serverSocket.accept(); //堵塞点
            list.add(client);
        }
    }
    public static void handler(){
        while(true){
            if(list.size() == 0) continue;

            for(Socket socket:list){
                //...具体处理
            }
        }
    }

}
