package com.yvonne.advanced.zookeeper;

/**
 * @author Yvonne Wang
 * @date 2021/4/220:14
 */
public class ZkConfigTest {
    public static void main(String[] args) {
        ZkConfigMag mag = new ZkConfigMag();
        Config config = mag.downLoadConfigFromDB();
        System.out.println("....加载数据库配置...."+config.toString());
        mag.syncConfigToZk();
        System.out.println("....同步配置文件到zookeeper....");

        //歇会，这样看比较清晰
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mag.upLoadConfigToDB("cwhcc", "passwordcc");
        System.out.println("....修改配置文件...."+config.toString());
        mag.syncConfigToZk();
        System.out.println("....同步配置文件到zookeeper....");


    }
}
