package com.example.demo.server;

import com.example.demo.config.WechatConfig;
import com.example.demo.config.WechatConfigBackup;
import com.example.demo.domain.Weather;

/**
 * @author 程序员顾阳
 * @date 2022/8/24 16:17
 */
public interface PushServer {

    /**
     * 天气push
     */
    void weatherPush(WechatConfig wechatConfig);

    void weatherPushYml(WechatConfigBackup wechatConfigBackup);

    /**
     * 获取天气
     *
     * @return {@link Weather}
     */
    Weather getWeather(WechatConfig wechatConfig);

    Weather getWeatherYml(WechatConfigBackup wechatConfigBackup);

}
