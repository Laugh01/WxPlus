package com.example.demo.domain;

import lombok.Data;

/**
 *
 * @author 程序员顾阳
 * @date 2022/8/24 16:17
 */
@Data
public class MsgHead {


    private String touser;

    private String template_id ;

    private String url = "http://weixin.qq.com/download";

    private String topcolor = "#FF0000";

    private MsgBody data;
}
