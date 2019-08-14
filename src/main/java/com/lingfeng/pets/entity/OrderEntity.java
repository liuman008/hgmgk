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
public class OrderEntity implements Serializable{
    
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    /**
     *类型
     */
    @ApiModelProperty(value = "类型")
    private String type;
    
    /**
     * 关联订单id
     */
    @ApiModelProperty(value = "关联订单id")
    private String orderId;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;
    
    /**
     * openId
     */
    @ApiModelProperty(value = "openId")
    private String openId;
    
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private String accout;
    
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    
    
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

}
