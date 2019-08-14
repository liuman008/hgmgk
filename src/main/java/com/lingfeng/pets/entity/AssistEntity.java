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
 * @author 谷春
 *
 */
@Data
@ApiModel
public class AssistEntity implements Serializable{
    
    /**
     * Log
     */
    private static final long serialVersionUID = -3532887613713159396L;
    
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    /**
     * 行数
     */
    @ApiModelProperty(value = "行数")
    private Integer currIndex;
    
    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer pageSize;
    
    /**
     * 当前页数
     */
    @ApiModelProperty (value =  "当前页数")
    private Integer currentPage;
    
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;
    
    /**
     * 名字
     */
    @ApiModelProperty(value = "名字")
    private String name;
    
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String image;
    
    
    /**
     * 经验
     */
    @ApiModelProperty(value = "经验")
    private String experience;
    
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;
    
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;
    
    /**
     *备注 
     */
    @ApiModelProperty(value = "备注 ")
    private String remark;
    
    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    private String wechatNumber;
    
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;
    
    /**
     * 发布id
     */
    @ApiModelProperty(value = "发布id")
    private String releaseId;
    
    /**
     * 评分
     */
    @ApiModelProperty(value = "评分")
    private Integer score;
    
    /**
     * 文字评价
     */
    @ApiModelProperty(value = "文字评价")
    private String writtenWords;
    
    /**
     * 服务费
     */
    @ApiModelProperty(value = "服务费")
    private  Double serviceCharge;
    
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
    
    /**
         * 状态
    */
    @ApiModelProperty(value = "状态")
    private Integer state;

}
