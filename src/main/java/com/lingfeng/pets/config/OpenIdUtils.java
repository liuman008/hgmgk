package com.lingfeng.pets.config;


import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**  
 * Created by     IntelliJ IDEA
 *
 * @author :      ShaoXiangDong
 * Date         :       2018/1/19
 * Version      :       1.0
 * Company      :       众美力
 */
public class OpenIdUtils {


     public  static JSONObject getUserWXLoginInfo(String wxCode,String appid,String appsecret) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String,String> requestUrlParam = new HashMap<String,String>();
        requestUrlParam.put("appid", appid);  //开发者设置中的appId
        requestUrlParam.put("secret", appsecret); //开发者设置中的appSecret
        requestUrlParam.put("js_code", wxCode); //小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");    //默认参数
        //发送post请求读取调用微信 https://api.weixin.qq.com/sns/jscode2session 接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(HttpUtils.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }   
}
