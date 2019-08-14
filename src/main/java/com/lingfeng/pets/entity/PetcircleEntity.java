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
public class PetcircleEntity implements Serializable{
    
    /**
     * Log
     */
    private static final long serialVersionUID = 8744341390331463231L;
    
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
     * 当前页数
     */
    @ApiModelProperty (value =  "当前页数")
    private Integer currentPage;
    /**
     * 页数
     */
    @ApiModelProperty(value = "页数")
    private Integer pageSize;
    
    
    /**
     *用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像 ")
    private String headPortrait;
    
    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;
    
    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    private String image;
    
    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;
    
    /**
     * 发布人id
     */
    @ApiModelProperty(value = "发布人id")
    private String userId;
    
    /**
     * 点赞数
     */
    @ApiModelProperty(value = "点赞数")
    private Integer pointRatio;
    
    /**
     * 点赞数
     */
    @ApiModelProperty(value = "评论数")
    private Integer commentNum;
    
    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private double longitude;
    
    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private double latitude;
    
    /**
     * 距离
     */
    @ApiModelProperty(value = "距离")
    private double distance;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String create_date;
    
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
         * 是否删除
    */
    @ApiModelProperty(value = "是否热门")
    private Integer is_popular;
}
