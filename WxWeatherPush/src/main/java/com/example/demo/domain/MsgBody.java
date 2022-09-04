package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author 程序员顾阳
 * @date 2022/8/24 16:17
 */
@Data
@Builder
public class MsgBody {


    private TemplateMsg nowTem;

    private TemplateMsg date;

    private TemplateMsg currentCity;

    private TemplateMsg lowTem;

    private TemplateMsg highTem;

    private TemplateMsg wind;

    private TemplateMsg loveDate;

    private TemplateMsg birthday;

    private TemplateMsg weather;

    private TemplateMsg caihongpi;

    private TemplateMsg enText;

    private TemplateMsg zhText;


}
