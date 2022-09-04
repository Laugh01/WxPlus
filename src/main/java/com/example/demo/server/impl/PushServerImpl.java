package com.example.demo.server.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.WechatConfigBackup;
import com.example.demo.domain.MsgBody;
import com.example.demo.domain.MsgHead;
import com.example.demo.domain.TemplateMsg;
import com.example.demo.domain.Weather;
import com.example.demo.server.PushServer;

import com.example.demo.server.TokenServer;
import com.example.demo.util.DateUtil;
import com.example.demo.util.HttpUtil;
import com.example.demo.util.TianApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author 程序员顾阳
 * @date 2022/8/24 16:17
 */
@Component
@Slf4j
public class PushServerImpl implements PushServer {

    @Resource
    private TokenServer tokenServer;
    @Resource
    private TianApiUtil tianApiUtil;


    /**
     * 日期：{{date.DATA}}
     * 天气：{{weather.DATA}}
     * 当前城市：{{currentCity.DATA}}
     * 当前温度：{{nowTem.DATA}}
     * 最低温度：{{lowTem.DATA}}
     * 最高温度：{{highTem.DATA}}
     * 风速：{{wind.DATA}}
     * 今天使我们恋爱的：{{loveDate.DATA}} 天
     * 距离xx的生日还有：{{birthday.DATA}} 天
     * {{caihongpi.DATA}}
     * {{zhText.DATA}}
     * {{enText.DATA}}
     */

    // 自定义模板=================================
    @Override
    public void weatherPush(WechatConfig wechatConfig) {
        String token = tokenServer.getToken();
        if (null == token) {
            token = tokenServer.doGetToken();
        }
        Weather weather = getWeather(wechatConfig);
        MsgBody msgBody = MsgBody.builder()
                //日期星期
                .date(TemplateMsg.builder().value(weather.getDate() + " " + DateUtil.getDayOfTheWeek()).color("#00BFFF").build())
                //天气
                .weather(TemplateMsg.builder().value(weather.getWeather()).color("#00FFFF").build())
                //当前城市
                .weatherRegion(TemplateMsg.builder().value(wechatConfig.weatherRegion).color("#00BFFF").build())
                //当前湿度
                .humidity(TemplateMsg.builder().value(weather.getHumidity().split("%")[0] + "°C").color("#00BFFF").build())
                //当前温度
                .nowTem(TemplateMsg.builder().value(weather.getTemp() + "°C").color("#EE212D").build())
                //最高温度
                .highTem(TemplateMsg.builder().value(weather.getHigh() + "°C").color("#173177").build())
                //最低温度
                .lowTem(TemplateMsg.builder().value(weather.getLow() + "°C").color("#FF6347").build())
                //风速
                .wind(TemplateMsg.builder().value(weather.getWind()).color("#B95EA3").build())
                //过年 日期
                .newYearDate(TemplateMsg.builder().value(DateUtil.calculationBirthday(wechatConfig.newYearDate)).color("#FFA500").build())
                //恋爱 日期
                .loveDate(TemplateMsg.builder().value(DateUtil.calculationLoveDate(wechatConfig.inforLoveDate)).color("#FFA500").build())
                //生日 日期
                .birthday(TemplateMsg.builder().value(DateUtil.calculationBirthday(wechatConfig.birthday)).color("#0081ff").build())

                .caihongpi(TemplateMsg.builder().value(tianApiUtil.getCaiHongPi()).color("#BA8072").build())
                .zhText(TemplateMsg.builder().value(tianApiUtil.getEnsentence().get("zh")).color("#FA8072").build())
                .enText(TemplateMsg.builder().value(tianApiUtil.getEnsentence().get("en")).color("#C71585").build())
                .build();


        MsgHead msgHead = new MsgHead();
        msgHead.setData(msgBody);
        msgHead.setTemplate_id(wechatConfig.templateId);
        msgHead.setTouser(wechatConfig.toUser);
        String data = JSONObject.toJSONString(msgHead);
        String url = wechatConfig.wxTemplateUrl + token;
        try {
            String result = HttpUtil.sendJson(url, data);
            log.info("push result:{}", result);
        } catch (Exception e) {
            log.error("push weather msg error ", e);
        }
    }

    @Override
    public Weather getWeather(WechatConfig wechatConfig) {
        try {
            String url = wechatConfig.weatherUrl + wechatConfig.weatherRegion;
            String result = HttpUtil.sendGet(url);
            JSONObject jsonObject = JSON.parseObject(result);
            Integer code = jsonObject.getInteger("code");
            String msg = jsonObject.getString("msg");
            log.info("url:{}", url);
            if (code != 0) {
                throw new Exception("调用天气失败," + msg);
            }
            JSONObject data = jsonObject.getJSONObject("data");
            List<Weather> list = JSONObject.parseArray(data.getJSONArray("list").toJSONString(), Weather.class);
            log.info("result:{}", JSONObject.toJSONString(result));
            return list.get(0);
        } catch (Exception e) {
            log.info("get weather error", e);
            return null;
        }
    }

    // Yml模板=================================
    @Override
    public void weatherPushYml(WechatConfigBackup wechatConfigBackup) {
        String token = tokenServer.getToken();
        if (null == token) {
            token = tokenServer.doGetToken();
        }
        Weather weather = getWeatherYml(wechatConfigBackup);
        MsgBody msgBody = MsgBody.builder()
                //日期星期
                .date(TemplateMsg.builder().value(weather.getDate() + " " + DateUtil.getDayOfTheWeek()).color("#00BFFF").build())
                //天气
                .weather(TemplateMsg.builder().value(weather.getWeather()).color("#00FFFF").build())
                //当前城市
                .weatherRegion(TemplateMsg.builder().value(wechatConfigBackup.weatherRegion).color("#00BFFF").build())
                //当前湿度
                .humidity(TemplateMsg.builder().value(weather.getHumidity().split("%")[0] + "°C").color("#00BFFF").build())
                //当前温度
                .nowTem(TemplateMsg.builder().value(weather.getTemp() + "°C").color("#EE212D").build())
                //最高温度
                .highTem(TemplateMsg.builder().value(weather.getHigh() + "°C").color("#173177").build())
                //最低温度
                .lowTem(TemplateMsg.builder().value(weather.getLow() + "°C").color("#FF6347").build())
                //风速
                .wind(TemplateMsg.builder().value(weather.getWind()).color("#B95EA3").build())
                //过年 日期
                .newYearDate(TemplateMsg.builder().value(DateUtil.calculationBirthday(wechatConfigBackup.newYearDate)).color("#FFA500").build())
                //恋爱 日期
                .loveDate(TemplateMsg.builder().value(DateUtil.calculationLoveDate(wechatConfigBackup.inforLoveDate)).color("#FFA500").build())
                //生日 日期
                .birthday(TemplateMsg.builder().value(DateUtil.calculationBirthday(wechatConfigBackup.birthday)).color("#0081ff").build())

                .caihongpi(TemplateMsg.builder().value(tianApiUtil.getCaiHongPi()).color("#BA8072").build())
                .zhText(TemplateMsg.builder().value(tianApiUtil.getEnsentence().get("zh")).color("#FA8072").build())
                .enText(TemplateMsg.builder().value(tianApiUtil.getEnsentence().get("en")).color("#C71585").build())
                .build();


        MsgHead msgHead = new MsgHead();
        msgHead.setData(msgBody);
        msgHead.setTemplate_id(wechatConfigBackup.templateId);
        msgHead.setTouser(wechatConfigBackup.toUser);
        String data = JSONObject.toJSONString(msgHead);
        String url = wechatConfigBackup.wxTemplateUrl + token;
        try {
            String result = HttpUtil.sendJson(url, data);
            log.info("push result:{}", result);
        } catch (Exception e) {
            log.error("push weather msg error ", e);
        }
    }

    @Override
    public Weather getWeatherYml(WechatConfigBackup wechatConfigBackup) {
        try {
            String url = wechatConfigBackup.weatherUrl + wechatConfigBackup.weatherRegion;
            String result = HttpUtil.sendGet(url);
            JSONObject jsonObject = JSON.parseObject(result);
            Integer code = jsonObject.getInteger("code");
            String msg = jsonObject.getString("msg");
            log.info("url:{}", url);
            if (code != 0) {
                throw new Exception("调用天气失败," + msg);
            }
            JSONObject data = jsonObject.getJSONObject("data");
            List<Weather> list = JSONObject.parseArray(data.getJSONArray("list").toJSONString(), Weather.class);
            log.info("result:{}", JSONObject.toJSONString(result));
            return list.get(0);
        } catch (Exception e) {
            log.info("get weather error", e);
            return null;
        }
    }
}
