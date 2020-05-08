package com.leesin.nacosdemo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @description:
 * @author: Leesin Dong
 * @date: Created in 2020/5/7 0007 9:50
 * @modified By:
 */
public class NacosSdkDemo {
    public static void main(String[] args) throws NacosException {
        String serverAddr = "localhost:8848";
        String dataId = "example";
        String groupId = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig(dataId, groupId, 3000);
        System.out.printf(config);

    //    能实现监听
        configService.addListener(dataId, groupId, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            //远程数据发生变化，会触发回调
            public void receiveConfigInfo(String s) {
                System.out.printf("configinfo:"+s);
            }
        });
    }
}
