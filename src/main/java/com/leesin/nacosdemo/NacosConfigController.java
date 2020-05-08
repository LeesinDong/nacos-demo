package com.leesin.nacosdemo;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Leesin Dong
 * @date: Created in 2020/5/2 21:59
 * @modified By:
 */

@NacosPropertySource(dataId = "example",groupId = "DEFAULT_GROUP",autoRefreshed = true)
@RestController
public class NacosConfigController {
    /*
    * 当前的info这个属性，会去nacos-server找到对应的info这个属性
    * 高可用行
    * hello Nacos表示本地属性（降级属性） ，简单说就是兜底的
    * */
    //这里是info 远程就要配置 info:xxx
    @NacosValue(value = "${info:Hell Nacos}",autoRefreshed = true)
    private String info;
    @GetMapping("/get")
    public String get() {
        return info;
    }
}
