package com.erely.zk;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

public class MySerializer implements ZkSerializer {

    public Object deserialize(byte[] abyte0) throws ZkMarshallingError {
        String str = null;
        try {
            str = new String(abyte0,"utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

    public byte[] serialize(Object obj) throws ZkMarshallingError {
        byte[] b = null;
        try {
            b = String.valueOf(obj).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return b;
    }
}
