package com.example.demo;

import com.example.demo.config.WechatConfig;
import com.example.demo.server.PushServer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {

    @Resource
    private PushServer pushServer;
    @Resource
    private WechatConfig wechatConfig;

    @Test
    void contextLoads() {
        pushServer.weatherPush(wechatConfig);
    }


}
