package com.erely.zk;

import org.I0Itec.zkclient.IZkDataListener;

public class ZkWatchTest implements IZkDataListener {
    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {
    }
    @Override
    public void handleDataDeleted(String dataPath) throws Exception {

    }
}
