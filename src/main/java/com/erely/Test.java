package com.erely;

import org.apache.shiro.crypto.hash.Sha256Hash;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\GIT\\Erely\\DubboCode\\111.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String name = null;
        while ((name =reader.readLine()) != null){
//            System.out.println(name);
            File file1 = new File("E:\\GIT\\Erely\\DubboCode\\"+name);
            file1.delete();
        }
        reader.close();
    }
}
