package com.erely.zk;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.util.ArrayList;
import java.util.List;

public class ZKtest {

    public static void main(String[] args) throws Exception {
        ZkClient zk = new ZkClient("127.0.0.1:2181", 100000,100000);

        zk.setZkSerializer(new MySerializer());
        List<String> list = zk.getChildren("/");
        System.out.println("所有节点为：");
        for(String str:list){
            System.out.println(str);
        }
		//添加权限信息
		List<ACL> acls = new ArrayList<ACL>();
		/*
		 * scheme字段里面的值
		 * world：有个单一的ID，anyone，表示任何人。
			auth：不使用任何ID，表示任何通过验证的用户（是通过ZK验证的用户？连接到此ZK服务器的用户？）。
			digest：使用 用户名：密码 字符串生成MD5哈希值作为ACL标识符ID。
			权限的验证通过直接发送用户名密码字符串的方式完成，
			ip：使用客户端主机ip地址作为一个ACL标识符，ACL表达式是以 addr/bits
			这种格式表示的。ZK服务器会将addr的前bits位与客户端地址的前bits位来进行匹配验证权限。
			super: 超级权限
		 *
		 */
        //创建id 以及id的权限
		Id id = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin123"));
		//给次id授权 所有权限
		ACL acl1 = new ACL(ZooDefs.Perms.ALL, id);

		Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest("admin:admin000"));
		ACL acl2 = new ACL(ZooDefs.Perms.READ, id1);
		acls.add(acl1);
		acls.add(acl2);
		if(!zk.exists("/test3")){

			zk.create("/test3", "这是test3", acls, CreateMode.PERSISTENT);

		}else{
			System.out.println("test3节点存在");
		}

		zk.addAuthInfo("digest", "admin:admin000".getBytes());
		String str1 = zk.readData("/test3");
		System.out.println(str1);
		zk.delete("/test3");

    }
}
