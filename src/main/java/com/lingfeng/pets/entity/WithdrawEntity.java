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
public class WithdrawEntity implements Serializable{
    
    /**
     * Log
     */
    private static final long serialVersionUID = -5618762744279991480L;
    
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private String id;
    
    
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private String userId;
    
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private double account;
    
    /**
     * 状态 
     */
    @ApiModelProperty(value = "状态")
    private Integer state;
    
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
