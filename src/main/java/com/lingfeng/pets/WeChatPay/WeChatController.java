/**
 * 
 */
package com.lingfeng.pets.WeChatPay;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.lingfeng.pets.entity.BillEntity;
import com.lingfeng.pets.entity.OrderEntity;
import com.lingfeng.pets.entity.ReleasePetEntity;
import com.lingfeng.pets.entity.UserEntity;
import com.lingfeng.pets.service.BillService;
import com.lingfeng.pets.service.OrderService;
import com.lingfeng.pets.service.ReleasePetService;
import com.lingfeng.pets.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author 谷春
 *
 */
@RestController("WeChatController")
@RequestMapping("/api")
@CrossOrigin
@Api(value = "/weChatController", description = "微信支付的接口")
public class WeChatController {

    /**
     * 注入订单表的实现类
     */
    @Autowired
    private OrderService orderService;
    
    /***
     * 注入账单表的实现类
     */
    @Autowired
    private BillService billService;
    
    /**
     * 注入用户表的实现类
     */
    @Autowired
    private UserService userService;
    
    /**
     * 注入发布表的实现类
     */
    @Autowired
    private ReleasePetService releasePetService;
    
    /**
     * 
     * http://lingfeng.natapp1.cc/api/wxProPayNotify/anon
     * 回调地址  https://www.gzlinfeng.com/pets
     */                                 
    public static String NOTIFY_URL = "https://www.gzlinfeng.com/pets/api/wxProPayNotify/anon";

    /**
     * 成功的标识
     */
    private final static String SUCCESS = "SUCCESS";

    /**
     * 返回状态码的变量名
     *
     */
    private final static String RETURN_CODE = "result_code"; 
    
    private String context = null;
    
    private String releaseId = null;

    /**
     * 功能描述: <小程序回调>
     * 
     * @return:
     * @auther: majker
     * @date: 2019/3/10
     **/
    @PostMapping("/wxProPayNotify/anon")
    public void wxProPayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("微信支付回调");
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        String resultxml = new String(outSteam.toByteArray(), "utf-8");
        
        // 解析xml成map
        Map<String, String> resultMap = WXPayUtil.xmlToMap(resultxml);
        System.out.println("resultMap=="+resultMap);
        // 过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator it = resultMap.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = resultMap.get(parameter);
            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
        // String xmlResult = IOUtils.toString(request.getInputStream(),
        // request.getCharacterEncoding());
        String orderNo = resultMap.get("out_trade_no");
        if (resultMap.get(RETURN_CODE).equals(SUCCESS)) {
           System.out.println("111111111111111111111111111111111111111111111111111111111");
            //String context = resultMap.get("context");
            String openId = resultMap.get("openid");
            //String releaseId = resultMap.get("releaseId");
            System.out.println("context="+context+"releaseId="+releaseId);
            //根据发布id修改有效状态
            ReleasePetEntity releasePetEntity = new ReleasePetEntity();
            releasePetEntity.setId(releaseId);
            releasePetEntity.setIs_effective(1);
            releasePetService.updateReleasePet(releasePetEntity);
            /**
             * 通过订单号 修改数据库中的记录，此处省略n行代码
             */
            OrderEntity orderEntity = new OrderEntity();
            System.out.println("orderNo=="+orderNo);
            orderEntity.setId(orderNo);
            orderEntity.setStatus(2);
            orderEntity.setUpdate_date(new Date());
            orderService.updateOrder(orderEntity);
            //根据openId查询用户id
            UserEntity userEntity  = userService.selectUserByopenId(openId);
            //新增账单表信息
            BillEntity billEntity = new BillEntity();
            billEntity.setId(UUID.randomUUID().toString());
            billEntity.setMoney(resultMap.get("total_fee"));
            billEntity.setContent(context);
            billEntity.setIs_output(1);
            billEntity.setCreate_date(new Date());
            billEntity.setUserId(userEntity.getId());
            billService.insertBill(billEntity);
        }else {
            /**
             * 通过订单号 修改数据库中的记录，此处省略n行代码
             */
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setId(orderNo);
            orderEntity.setStatus(3);
            orderService.updateOrder(orderEntity);
        }
        String result = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信统一下单接口
     * 
     * @return
     * @throws Exception
     * @throws InvalidKeyException
     */
    @PostMapping("/doUnifiedOrder")   @Timed
    @ApiOperation(value = "", notes = "接口说明：要传参数 openId , accout , type")
    public Map<String, String> doUnifiedOrder(HttpServletRequest request, HttpServletResponse response)
            throws InvalidKeyException, Exception {
        String oid = UUID.randomUUID().toString().replace("-", "");
        // openid
        String openId = request.getParameter("openId");
        // 金额
        String accout = request.getParameter("accout");
        String type = request.getParameter("type");
        String orderId = request.getParameter("orderId");
        context = request.getParameter("context");
        releaseId = request.getParameter("orderId");
        System.out.println("openId=" + openId + "accout=" + accout + "orderId=" + orderId);
        Map resultMap = new HashMap();
        if (!StringUtils.isNotBlank(openId)) {
            resultMap.put("code", 1);
            resultMap.put("msg", "用户open_id为空");
            // return ServerResponse.createByErrorCodeMessage(resultMap);
        }
        MyConfig config = null;
        WXPay wxpay = null;
        try {
            config = new MyConfig();
            wxpay = new WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // 生成的随机字符串
        String nonce_str = WXPayUtil.generateNonceStr();
        // 获取客户端的ip地址
        // 获取本机的ip地址
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String spbill_create_ip = addr.getHostAddress();
        // 支付金额，需要转成字符串类型，否则后面的签名会失败 分为单位
        int total_fee = 1;
        // 商品描述
         String body = "宠物寄养支付";
        // 商户订单号
        // String out_trade_no= WXPayUtil.generateNonceStr();
        
        Map<String, String> requestMap = new HashMap<String, String>();
        requestMap.put("appid", config.getAppID());
        requestMap.put("mch_id", config.getMchID());
        requestMap.put("notify_url", NOTIFY_URL);
        requestMap.put("trade_type", "JSAPI");
        requestMap.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
        requestMap.put("out_trade_no", oid);
        requestMap.put("body",body);
        requestMap.put("total_fee", String.valueOf(total_fee*1));
        requestMap.put("openid", openId);
        requestMap.put("spbill_create_ip", PayUtils.getHttpServletRequestRemoteHost(request));
        requestMap.put("sign", PayUtils.getSign(requestMap, "Aa123456789123456789123456789123"));
        
        if (!StringUtils.isNotBlank(requestMap.get("sign"))) {
            resultMap.put("code", 500);
            resultMap.put("msg", "签名失败！！！！");
        }else {
         // 生成预订单
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setAccout(accout);
            orderEntity.setId(oid);
            orderEntity.setOpenId(openId);
            orderEntity.setOrderId(orderId);
            orderEntity.setCreate_date(new Date());
            orderEntity.setStatus(1);
            orderEntity.setId(UUID.randomUUID().toString());
            orderEntity.setType(type);
            orderService.insertOrder(orderEntity);
        }
        String requestXml = PayUtils.mapToXML(requestMap);
        
        
        //System.out.println("data:" + data.toString());

        //String requestXml = PayUtils.mapToXML(requestMap);
        
        System.out.println("requestXml:" + requestXml);
        
        // Map<String, String> rMap = wxpay.unifiedOrder(data);
        //建立与页面的连接
        URL url = new URL("https://api.mch.weixin.qq.com/pay/unifiedorder");
        //打开连接
        URLConnection connection = url.openConnection();
        //设置参数
        connection.setUseCaches(false);
        connection.setConnectTimeout(1000 * 5);
        connection.setReadTimeout(1000 * 5);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded ");
        
        String responseXml = null; // 应答XML
        
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(requestXml.getBytes());

            try (InputStream inputStream = connection.getInputStream()) {
                byte[] res = IOUtils.readBytes(inputStream);
                responseXml = new String(res);
            }
        }
        
        System.out.println("responseXml:" + responseXml);
        //将得到的数据转换成map
        Map<String, String> readStringXmlOut = PayUtils.readStringXmlOut(responseXml);
        //设置时间戳
        readStringXmlOut.put("timeStamp", new Date().getTime() + "");

        String paysign = "appId=" + readStringXmlOut.get("appid") + "&nonceStr=" + readStringXmlOut.get("nonce_str")
                + "&package=prepay_id=" + readStringXmlOut.get("prepay_id") + "&signType=MD5&timeStamp="
                + readStringXmlOut.get("timeStamp") + "&key=" + "Aa123456789123456789123456789123";

        String hashMD5 = DigestUtils.md5DigestAsHex(paysign.getBytes()).toUpperCase();

        readStringXmlOut.put("paysign", hashMD5);
        
        return readStringXmlOut;
        
    }
    
}
