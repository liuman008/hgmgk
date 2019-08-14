/**
 * 
 */
package com.lingfeng.pets.WeChatPay;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author 谷春
 *
 */
public class MyConfig extends WXPayConfig{
    
    
    private byte[] certData;
    
    /**
     * appid
     */
    @Override
    public String getAppID() {
        return "wx3b7e6ad21ed259ed";
    }

    @Override
    public String getMchID() {
        return "1545378001";
    }
    
    /**
     *密钥
     */
    @Override
    public String getKey() {
        return "Aa123456789123456789123456789123";
    }
    
    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com", false);
            }
        };
    }

     

}
