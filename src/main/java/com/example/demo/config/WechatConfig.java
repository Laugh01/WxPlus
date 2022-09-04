package com.example.demo.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author 程序员顾阳
 * 自定义模板时使用这个Config类
 * @date 2022/8/24 16:17
 */
@Data
@Configuration
public class WechatConfig {
    //0：使用自定义模板，禁用yml固定模板  1:使用yml固定模板 禁用自定义模板
    public Integer id = 1;

    @Value("${wechat.accessTokenUrl}")
    public String accessTokenUrl;

    @Value("${wechat.wxTemplateUrl}")
    public String wxTemplateUrl;

    // 申请测试公众号的appId
    @Value("${wechat.appId}")
    public String appId;

    // 申请测试公众号的appSecret
    @Value("${wechat.appSecret}")
    public String appSecret;

    // 天气Url
    @Value("${weather.url}")
    public String weatherUrl;

    // 申请的天行api参数值
    @Value("${tianApi}")
    public String tianApi;

    // 过年日期
    @Value("${infor.newYearDate}")
    public String newYearDate;

    // 对面扫码ID
    public String toUser;

    // 发送模板ID
    public String templateId;

    // 需要发送的天气城市（例如 福州）
    public String weatherRegion;

    // 当前天气湿度
    public String humidity;

    // 恋爱日期（格式 2000-01-01）
    public String inforLoveDate;

    // 生日（格式 2000-01-01）
    public String birthday;

}
