package com.erely.zk;


import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

public class ZkCuratorLocker {

    private static String zookeeperUrl = "182.92.87.55:2181";
    private static String lockPath = "/lock";


    public static void main(String[] args) {
        final CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperUrl, new RetryNTimes(10, 5000));
        client.start();

        System.out.println(client.getState());
        System.out.println("zk client start success");

        final InterProcessMutex mutex = new InterProcessMutex(client, lockPath);

        for (int i = 0; i < 5; i++) {
            Runnable myRunnable = new Runnable() {
                public void run() {
                    doWithLock(client, mutex);
                }
            };
            Thread thread = new Thread(myRunnable, "Thread-" + i);
            thread.start();
        }

    }

    private static void doWithLock(CuratorFramework client, InterProcessMutex mutex) {
        try {
            String name = Thread.currentThread().getName();
            if (mutex.acquire(60, TimeUnit.SECONDS)) {

                System.out.println(name + " hold lock");

                System.out.println(client.getChildren().forPath(lockPath));

                Thread.sleep(5000L);
                System.out.println(name + " release lock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
