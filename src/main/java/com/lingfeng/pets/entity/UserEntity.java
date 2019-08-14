/**
 * 
 */
package com.lingfeng.pets.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Liuman
 *
 */
@Data
@ApiModel
public class UserEntity implements Serializable{
    
    /**
     * Log
     */
    private static final long serialVersionUID = -7673948831454249678L;
    
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    
    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer pageSize;
    
    /**
     * 当前行数
     */
    @ApiModelProperty(value = "当前行数")
    private Integer currentPage;
    
    /**
                 * 行数
     */
    @ApiModelProperty(value = "行数")
    private Integer currIndex;
    
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String likeName;
    
    /**
     * 用户名
     */
    @ApiModelProperty(value = " 用户名")
    private String userName;
    
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;
    
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;
    
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;
    
    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    private String address;
    
    /**
     * 身份证正照
     */
    @ApiModelProperty(value = "身份证正照")
    private String frontphotoofIDcard;
    
    /**
     * 身份证反照
     */
    @ApiModelProperty(value = "身份证反照")
    private String counterphotoofIDcard;
    
    /**
     * 积分
     */
    @ApiModelProperty(value = "积分")
    private Integer integral;
    
    /**
     * 余额
     */
    @ApiModelProperty(value = "余额")
    private double balance;
    
    /**
     * 银行卡号
     */
    @ApiModelProperty(value = "银行卡号")
    private String cardNumber;
    
    /**
     * openid
     */
    @ApiModelProperty(value = "openid")
    private String openid;
    
    /**
     * 头像
     */
     @ApiModelProperty(value = "头像")
     private String image;
     
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date create_date;
    
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date update_date;
    
    /**
     * 最后操作人
     */
    @ApiModelProperty(value = "最后操作人")
    private String lastModel_by;
    
    /**
             * 最后操作时间
     */
    @ApiModelProperty(value = "最后操作时间")
    private Date lastModel_date;
    
    /**
             * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Integer is_del;
} 
