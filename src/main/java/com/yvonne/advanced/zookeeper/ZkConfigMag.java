package com.yvonne.advanced.zookeeper;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author Yvonne Wang
 * @date 2021/4/220:13
 */
public class ZkConfigMag {
    private Config config;
    /**
     * 从数据库加载配置
     */
    public Config downLoadConfigFromDB(){
        //getDB
        config = new Config("nm", "pw");
        return config;
    }

    /**
     * 配置文件上传到数据库
     */
    public void upLoadConfigToDB(String nm, String pw){
        if(config==null){
            config = new Config();
        }
        config.setUserNm(nm);
        config.setUserPw(pw);
        //updateDB
    }

    /**
     * 配置文件同步到zookeeper
     */
    public void syncConfigToZk(){
        ZkClient zk = new ZkClient("localhost:2181");
        if(!zk.exists("/zkConfig")){
            zk.createPersistent("/zkConfig",true);
        }
        zk.writeData("/zkConfig", config);
        zk.close();
    }

}
