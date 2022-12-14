package com.example.demo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程序员顾阳
 * yml 固定模板
 * 使用yml模板时 请讲自定义模板的Config类 注释掉
 * @date 2022/8/24 16:17
 */

@Configuration
public class WechatConfigBackup {

    @Value("${wechat.appId}")
    public String appId;

    @Value("${wechat.appSecret}")
    public String appSecret;

    @Value("${wechat.accessTokenUrl}")
    public String accessTokenUrl;

    @Value("${wechat.wxTemplateUrl}")
    public String wxTemplateUrl;

    @Value("${wechat.toUser}")
    public String toUser;

    @Value("${wechat.templateId}")
    public String templateId;

    @Value("${weather.url}")
    public String weatherUrl;

    @Value("${weather.region}")
    public String weatherRegion;

    @Value("${infor.newYearDate}")
    public String newYearDate;

    @Value("${infor.loveDate}")
    public String inforLoveDate;

    @Value("${infor.birthday}")
    public String birthday;

    @Value("${tianApi}")
    public String tianApi;
}
