/**
 * 
 */
package com.lingfeng.pets.config;

/**
 * @author 谷春
 *
 */
public enum ResponseCode {
    
    //1.定义枚举值
    ERROR(500,"ERROR"),

    SUCCESS(200,"SUCCESS"),

    NEED_REGISTER(10,"NEED_REGISTER"),
    
    NOT_OPENID(1,"NOT_OPENID"),

    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");
    
 

    //2.定义枚举属性
    private final int code;

    private final String desc;

    //3.定义构造函数
    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    //4.定义get方法
    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }

}
