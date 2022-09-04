package com.example.demo;

import com.example.demo.config.WechatConfig;
import com.example.demo.config.WechatConfigBackup;
import com.example.demo.server.PushServer;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 程序员顾阳
 * @date 2022/8/24 16:17
 */
public class PushTest extends DemoApplicationTests {
    @Resource
    private PushServer pushServer;
    @Resource
    WechatConfig wechatConfig;
    @Resource
    WechatConfigBackup wechatConfigBackup;

    @Test
    public void push() {
        template();
    }

    // 自定义模板（适合一次性发送多个女朋友使用）
    public void template() {

        // 判断 使用的是自定义模板还是yml固定模板
        if (wechatConfig.getId() == 1) {
            pushServer.weatherPushYml(wechatConfigBackup);
            return;
        }
        // ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ Yml固定模板 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

        // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 自定义模板 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
        Map<String, String[]> map = new HashMap<>();
        // 请在Map集合中填写相对应参数
        map.put("toUser", new String[]{"oDJ2s6SHA4V-QiXq1-VSjcL-LJUo", "oDJ2s6SG0D-YOJ5qZzJDGenRNEdo"});
        map.put("templateId", new String[]{"E8n-BjklGxrdVbp41QOe_1BoxyC9JSYUqPLJhO9FieM"});
        map.put("weatherRegion", new String[]{"福州", "宁德"});
        map.put("inforLoveDate", new String[]{"2000-01-01"});
        map.put("birthday", new String[]{"2000-01-01"});

        for (int i = 0; i < map.get("toUser").length; i++) {
            // 对方扫码ID
            wechatConfig.setToUser(map.get("toUser")[i]);

            // 发送的模板ID
            wechatConfig.setTemplateId(map.get("templateId").length <= 1 ? map.get("templateId")[0] : map.get("templateId")[i]);

            // 发送天气的城市
            wechatConfig.setWeatherRegion(map.get("weatherRegion").length <= 1 ? map.get("weatherRegion")[0] : map.get("weatherRegion")[i]);

            // 恋爱日期
            wechatConfig.setInforLoveDate(map.get("inforLoveDate").length <= 1 ? map.get("inforLoveDate")[0] : map.get("inforLoveDate")[i]);

            // 生日日期
            wechatConfig.setBirthday(map.get("birthday").length <= 1 ? map.get("birthday")[0] : map.get("birthday")[i]);

            pushServer.weatherPush(wechatConfig);
        }

    }
}
